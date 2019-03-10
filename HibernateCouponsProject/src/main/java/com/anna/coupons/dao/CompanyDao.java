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

import com.anna.coupons.entities.CompanyEntity;
import com.anna.coupons.entities.CouponEntity;
import com.anna.coupons.enums.ErrorType;
import com.anna.coupons.exceptions.ApplicationException;

@Repository
public class CompanyDao {

	@PersistenceContext(unitName = "couponsproject")
	private EntityManager entityManager;

	CouponDao couponDao = new CouponDao();

	@Transactional(propagation = Propagation.REQUIRED)
	public void createCompany(CompanyEntity company) throws ApplicationException {
		try {
			entityManager.persist(company);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "Error in CompanyDao, creatCompany(); FAILED");

		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public CompanyEntity getCompanyByCompanyId(Long companyId) throws ApplicationException {
		try {
			return entityManager.find(CompanyEntity.class, companyId);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR,
					"Error in CompanyDao, getCompanyByComapnyId(); FAILED");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteCompany(long companyId) throws ApplicationException {
		CompanyEntity company = getCompanyByCompanyId(companyId);
		List<CouponEntity> coupons = couponDao.getCouponByCompanyId(companyId);
		try {
			entityManager.remove(company);
			entityManager.remove(coupons);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "Error in companyDao, deleteCompany(); Failed");
		}

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateCompany(CompanyEntity company) throws ApplicationException {
		try {
			entityManager.merge(company);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "Error in companyDao, updateCompany(); Failed");
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<CompanyEntity> getAllComapnies() throws ApplicationException {

		List<CompanyEntity> companyList = new ArrayList<CompanyEntity>();

		try {
			Query query = entityManager.createQuery("SELECT company FROM CompanyEntity AS company");
			companyList = query.getResultList();
		}

		catch (Exception e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "Error in companyDao, getAllCompanies(); Failed");
		}
		return companyList;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Long login(String companyEmail, String companyPassword) throws ApplicationException {

		try {
			Query query = entityManager.createQuery(
					"SELECT company.companyId FROM CompanyEntity as company WHERE companyEmail =:companyEmail AND companyPassword =:companyPassword");
			query.setParameter("companyEmail", companyEmail);
			query.setParameter("companyPassword", companyPassword);
			Long companyId =  (Long) query.getSingleResult();

			return companyId;

		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "Error in CompanyDao, login(); FAILED");
		}
	}
}
