package com.anna.coupons.controller;

import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.anna.coupons.dao.CouponDao;
import com.anna.coupons.entities.CouponEntity;
import com.anna.coupons.enums.CouponType;
import com.anna.coupons.enums.ErrorType;
import com.anna.coupons.exceptions.ApplicationException;
import com.anna.coupons.utils.DateUtils;

@Controller
public class CouponController {

	@Autowired
	private CouponDao couponDao;

	public long createCoupon(CouponEntity coupon) throws ApplicationException {
		validateCreateCoupon(coupon);

		return this.couponDao.createCoupon(coupon);
	}

	private void validateCreateCoupon(CouponEntity coupon) throws ApplicationException {
		if (this.couponDao.isCouponExistByTitle(coupon.getCouponTitle())) {
			throw new ApplicationException(ErrorType.ALREADY_EXISTS, "This coupon already exists");
		}

	}

	public CouponEntity getCouponByCouponId(long couponId) throws ApplicationException {
		return this.couponDao.getCouponByCouponId(couponId);
	}

	public void deleteCoupon(long couponId) throws ApplicationException {
		couponDao.deleteCoupon(couponId);
	}

	public void deleteCouponsByCompany(long companyId) throws ApplicationException {
		couponDao.deleteCouponsByCompany(companyId);
	}

	public void updateCoupon(CouponEntity coupon) throws ApplicationException {
		couponDao.updateCoupon(coupon);
	}

	public List<CouponEntity> getAllCoupons() throws ApplicationException {
		return this.couponDao.getAllCoupons();
	}

	public List<CouponEntity> getCouponByCouponType(CouponType couponType) throws ApplicationException {
		return this.couponDao.getCouponByCouponType(couponType);
	}

	public List<CouponEntity> getCouponsUpToPrice(int couponPrice) throws ApplicationException {
		return this.couponDao.getCouponsUpToPrice(couponPrice);
	}

	public List<CouponEntity> getCouponsUpToDate(String couponEndDate) throws ApplicationException {
		return this.couponDao.getCouponUpToDate(couponEndDate);
	}

	public List<CouponEntity> getAllExpiredCoupons() throws ApplicationException {
		return this.couponDao.getAllExpiredCoupons();
	}

	public List<CouponEntity> getCouponByCustomerId(long customerId) throws ApplicationException {
		return this.couponDao.getCouponByCustomerId(customerId);
	}

	public List<CouponEntity> getCouponByCompanyId(long companyId) throws ApplicationException {
		return this.couponDao.getCouponByCompanyId(companyId);
	}

	public void isCouponExistByTitle(String couponTitle) throws ApplicationException {
		if (this.couponDao.isCouponExistByTitle(couponTitle) == false) {
			throw new ApplicationException(ErrorType.INVALID_PARMETER, "Coupon doesn't exist by this title");
		}
		couponDao.isCouponExistByTitle(couponTitle);

	}

	public void deleteExpiredCoupons() throws ApplicationException {

		GregorianCalendar today = new GregorianCalendar();

		String todayStr = DateUtils.dateToStr(today);
		System.out.println(todayStr);

		this.couponDao.deleteCouponsByEndDate(todayStr);

	}

//	public void getCouponCustomerByCouponId(long couponId) throws ApplicationException {
//		couponDao.getCouponCustomerByCouponId(couponId);
//	}

//	public void deleteCouponsByEndDate(String couponEndDate) throws ApplicationException {
//		couponDao.deleteCouponsByEndDate(couponEndDate);
//
//	}

	public void purchase(Long customerId, Long couponId) throws ApplicationException {
		if(couponId==null) {
			throw new ApplicationException(ErrorType.SYSTEM_ERROR, "Error in purchase");
		}
		
		if(customerId==null) {
			throw new ApplicationException(ErrorType.SYSTEM_ERROR, "Error in purchase");
		}
		CouponEntity couponToBuy = this.couponDao.getCouponByCouponId(couponId);
		
		if(couponToBuy.getCouponAmount() <= 0) {
			
			throw new ApplicationException(ErrorType.SYSTEM_ERROR, "Error in purchase, out of stock");
		}
		
		this.couponDao.purchase(customerId, couponToBuy);
		
		couponToBuy.reduceAmountByOne();
	}

}
