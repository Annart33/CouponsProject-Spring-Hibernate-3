package com.anna.coupons.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.anna.coupons.entities.CustomerEntity;
import com.anna.coupons.enums.ErrorType;
import com.anna.coupons.exceptions.ApplicationException;

@Repository
public class CustomerDao {

	@PersistenceContext(unitName = "couponsproject")
	private EntityManager entityManager;

	@Transactional(propagation = Propagation.REQUIRED)
	public void createCustomer(CustomerEntity customer) throws ApplicationException {
		try {
			entityManager.persist(customer);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "Error in CustomerDao, createCustomer(); FAILED");

		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public CustomerEntity getCustomerByCustomerId(Long customerId) throws ApplicationException {
		try {
			return entityManager.find(CustomerEntity.class, customerId);

		}

		catch (Exception e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "Error in customerDao, getCustomer(); Failed");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteCustomer(long customerId) throws ApplicationException {
		CustomerEntity customer = getCustomerByCustomerId(customerId);
		try {
			entityManager.remove(customer);
		}

		catch (Exception e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "Error in customerDao, deleteCustomer(); Failed");
		}

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateCustomer(CustomerEntity customer) throws ApplicationException {
		try {
			entityManager.merge(customer);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "Error in customerDao, updateCustomer(); Failed");
		}

	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<CustomerEntity> getAllCustomers() throws ApplicationException {

		List<CustomerEntity> customerList = new ArrayList<CustomerEntity>();

		try {
			Query query = entityManager.createQuery("SELECT customer FROM CustomerEntity As customer");
			customerList = query.getResultList();
		}

		catch (Exception e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR,
					"Error in customerDao, getAllCustomers(); Failed");
		}
		return customerList;

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Long login(String customerEmail, String customerPassword) throws ApplicationException {

		try {
			Query query = entityManager.createQuery(
					"SELECT customer.customerId FROM CustomerEntity as customer WHERE customerEmail =:customerEmail AND customerPassword =:customerPassword");
			query.setParameter("customerEmail", customerEmail);
			query.setParameter("customerPassword", customerPassword);
			Long customerId = (Long) query.getSingleResult();

			return customerId;

		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "Error in customerDao, login(); Failed");
		}

	}

	public boolean isCustomerExistByEmail(String customerEmail) throws ApplicationException {
		try {
			Query query = entityManager
					.createQuery("SELECT customer FROM CustomerEntity as customer WHERE customerEmail =:customerEmail");
			query.setParameter("customerEmail", customerEmail);
			if (query.getFirstResult() == 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR,
					"Error in customerDao, isCustomerExistByEmail(); Failed");
		}

	}

	@SuppressWarnings("unused")
	public boolean isCustomerExistByName(String customerName) throws ApplicationException {
		try {
			Query query = entityManager
					.createQuery("SELECT customer FROM CustomerEntity as customer WHERE customerName =:customerName");
			query.setParameter("customerName", customerName);
			if (query != null) {
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR,
					"Error in customerDao, isCustomerExistByName(); Failed");
		}
	}

}