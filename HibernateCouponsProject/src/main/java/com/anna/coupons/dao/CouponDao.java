package com.anna.coupons.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.anna.coupons.entities.CouponEntity;
import com.anna.coupons.entities.CustomerEntity;
import com.anna.coupons.enums.CouponType;
import com.anna.coupons.enums.ErrorType;
import com.anna.coupons.exceptions.ApplicationException;

@Repository
public class CouponDao {

	@Autowired
	private CustomerDao customerDao;

	@PersistenceContext(unitName = "couponsproject")
	private EntityManager entityManager;

	@Transactional(propagation = Propagation.REQUIRED)
	public Long createCoupon(CouponEntity coupon) throws ApplicationException {
		try {
			entityManager.persist(coupon);
			Query query = entityManager
					.createQuery("SELECT coupon.couponId FROM CouponEntity as coupon WHERE coupon =:coupon");
			query.setParameter("coupon", coupon);
			Long couponId = (Long) query.getSingleResult();

			return couponId;
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "Error in CouponDao, creatCoupon(); FAILED");

		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public CouponEntity getCouponByCouponId(Long couponId) throws ApplicationException {
		try {
			return entityManager.find(CouponEntity.class, couponId);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR,
					"Error in couponDao, getCouponByCouopnId(); FAILED");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteCoupon(long couponId) throws ApplicationException {
		CouponEntity coupon = getCouponByCouponId(couponId);
		// List<CouponCustomerEntity> couponCustomer =
		// getCouponCustomerByCouponId(couponId);
		try {
			entityManager.remove(coupon);
			// if (couponCustomer!=0) {
			// entityManager.remove(couponCustomer);
			// }
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "Error in couponDao, deleteCoupon(); Failed");
		}
	}

	// @Transactional(propagation = Propagation.REQUIRED)
	// public CouponCustomerEntity getCouponCustomerByCouponId(Long couponId) throws
	// ApplicationException {
	// try {
	// return entityManager.find(CouponCustomerEntity.class, couponId);
	// } catch (Exception e) {
	// throw new ApplicationException(e, ErrorType.SYSTEM_ERROR,
	// "Error in CouponDao, getCouponCustomerByCouponId(); FAILED");
	// }
	// }

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteCouponsByCompany(long companyId) throws ApplicationException {
		try {
			Query query = entityManager.createQuery("DELETE FROM CouponEntity AS coupon WHERE companyId =:companyId");
			query.setParameter("companyId", companyId);
			query.executeUpdate();

		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR,
					"Error in couponDao, deleteCoupopnByCompany(); Failed");
		}
	}

	// @Transactional(propagation = Propagation.REQUIRED)
	// public void deleteCouponsByEndDate(String couponEndDate) throws
	// ApplicationException {
	// List<CouponEntity> coupon = getCouponUpToDate(couponEndDate);
	// try {
	// entityManager.remove(coupon);
	// } catch (Exception e) {
	// throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "Error in
	// couponDao, deleteCouponsByEndDate(); Failed");
	// }
	// }

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateCoupon(CouponEntity coupon) throws ApplicationException {
		try {
			entityManager.merge(coupon);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "Error in couponDao, updateCoupon(); Failed");
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<CouponEntity> getAllCoupons() throws ApplicationException {
		List<CouponEntity> couponList = new ArrayList<CouponEntity>();

		try {
			Query query = entityManager.createQuery("SELECT coupon FROM CouponEntity As coupon");
			couponList = query.getResultList();
		}

		catch (Exception e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "Error in couponDao, getAllCompanies(); Failed");
		}
		return couponList;
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<CouponEntity> getCouponByCouponType(CouponType couponType) throws ApplicationException {
		List<CouponEntity> couponList = new ArrayList<CouponEntity>();

		try {
			Query query = entityManager
					.createQuery("SELECT coupon FROM CouponEntity As coupon WHERE couponType =:couponType");
			query.setParameter("couponType", couponType);
			couponList = query.getResultList();
		}

		catch (Exception e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR,
					"Error in couponDao, getCouponByCouponType(); Failed");
		}
		return couponList;
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<CouponEntity> getCouponsUpToPrice(int couponPrice) throws ApplicationException {
		List<CouponEntity> couponList = new ArrayList<CouponEntity>();

		try {
			Query query = entityManager
					.createQuery("SELECT coupon FROM CouponEntity As coupon WHERE couponPrice <=:couponPrice");
			query.setParameter("couponPrice", couponPrice);
			couponList = query.getResultList();
		}

		catch (Exception e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR,
					"Error in couponDao, getCouponsUpToPrice(); Failed");
		}
		return couponList;
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<CouponEntity> getCouponUpToDate(String couponEndDate) throws ApplicationException {

		List<CouponEntity> couponList = new ArrayList<CouponEntity>();

		try {
			Query query = entityManager
					.createQuery("SELECT coupon FROM CouponEntity As coupon WHERE couponEndDate <=:couponEndDate");
			query.setParameter("couponEndDate", couponEndDate);
			couponList = query.getResultList();
		}

		catch (Exception e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR,
					"Error in couponDao, getCouponUpToDate(); Failed");
		}
		return couponList;
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<CouponEntity> getAllExpiredCoupons() throws ApplicationException {
		List<CouponEntity> couponList = new ArrayList<CouponEntity>();

		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate localDate = LocalDate.now();
			String currentTime = dtf.format(localDate);

			Query query = entityManager
					.createQuery("SELECT coupon FROM CouponEntity As coupon WHERE couponEndDate <=:couponEndDate");
			query.setParameter("couponEndDate", currentTime);
			couponList = query.getResultList();
			System.out.println(couponList);
		}

		catch (Exception e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR,
					"Error in couponDao, getAllExpiredCoupons(); Failed");
		}
		return couponList;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteCouponsByEndDate(String couponEndDate) throws ApplicationException {
		try {
			Query query = entityManager
					.createQuery("DELETE FROM CouponEntity AS coupon WHERE couponEndDate <=:couponEndDate");
			query.setParameter("couponEndDate", couponEndDate);
			query.executeUpdate();

		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR,
					"Error in couponDao, deleteCouponsByEndDate(); Failed");
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<CouponEntity> getCouponByCustomerId(long customerId) throws ApplicationException {
		List<CouponEntity> couponByCustomerId = new ArrayList<CouponEntity>();

		try {
			Query query = entityManager
					.createQuery("SELECT coupon FROM CouponEntity As coupon WHERE customerId =:customerId ");
			query.setParameter("customerId", customerId);
			couponByCustomerId = query.getResultList();
			return couponByCustomerId;

		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR,
					"Error in CouponDao, getCouponByCustomerId(); FAILED");
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public ArrayList<CouponEntity> getCouponByCompanyId(long companyId) throws ApplicationException {
		ArrayList<CouponEntity> couponByCompanyId = new ArrayList<CouponEntity>();

		try {
			Query query = entityManager
					.createQuery("SELECT coupon FROM CouponEntity As coupon WHERE companyId =:companyId ");
			query.setParameter("companyId", companyId);
			couponByCompanyId = (ArrayList<CouponEntity>) query.getResultList();
			return couponByCompanyId;

		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR,
					"Error in CouponDao, getCouponByCompanyId(); FAILED");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean isCouponExistByTitle(String couponTitle) throws ApplicationException {
		try {
			Query query = entityManager
					.createQuery("SELECT coupon FROM CouponEntity As coupon WHERE couponTitle =:couponTitle");
			query.setParameter("couponTitle", couponTitle);
			if (query.getFirstResult() == 0) {
				return false;
			} else
				return true;

		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR,
					"Error in CouponDao, isCouponExistByTitle(); FAILED");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void purchase(Long customerId, CouponEntity coupon) throws ApplicationException {
		try {
			CustomerEntity customer = this.customerDao.getCustomerByCustomerId(customerId);
			coupon.addPurchesers(customer);
			Query query = entityManager
					.createNativeQuery("insert into couponCustomer (couponId, customerId) values (?,?)");
			query.setParameter(1, coupon.getCouponId());
			query.setParameter(2, customerId);
			query.executeUpdate();
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "Error in CouponDao, purchase(); FAILED");

		}
	}
}
