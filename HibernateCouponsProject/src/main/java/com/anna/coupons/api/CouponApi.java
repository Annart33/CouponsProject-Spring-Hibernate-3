package com.anna.coupons.api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anna.coupons.controller.CouponController;
import com.anna.coupons.entities.CouponEntity;
import com.anna.coupons.enums.CouponType;
import com.anna.coupons.exceptions.ApplicationException;
import com.anna.coupons.utils.CookieUtils;

@RestController
@RequestMapping("/coupons")
public class CouponApi {

	@Autowired
	private CouponController couponController;
	
	@GetMapping
	public ArrayList<CouponEntity> getAllCoupons() throws ApplicationException {
		return (ArrayList<CouponEntity>) couponController.getAllCoupons();
	}

	@GetMapping
	@RequestMapping("/{couponId}")
	public CouponEntity getCoupon(@PathVariable("couponId") long couponId) throws ApplicationException {
		return couponController.getCouponByCouponId(couponId);
	}

	@DeleteMapping
	@RequestMapping("/byCouponId/{couponId}")
	public void deleteCoupon(@PathVariable("couponId") long couponId) throws ApplicationException {
		couponController.deleteCoupon(couponId);
	}

	@DeleteMapping
	@RequestMapping("/byCompany/{companyId}")
	public void deleteCouponsByCompany(@PathVariable("companyId") long companyId) throws ApplicationException {
		couponController.deleteCouponsByCompany(companyId);
	}
	
	//deletes all coupons, not just expired
	@DeleteMapping
	public void deleteExpiredCoupons() throws ApplicationException {
		couponController.deleteExpiredCoupons();
	}

	@PostMapping
	public void createCoupon(@RequestBody CouponEntity coupon) throws ApplicationException, SQLException {
		couponController.createCoupon(coupon);
	}

	@PutMapping
	public void updateCoupon(@RequestBody CouponEntity coupon) throws ApplicationException {
		couponController.updateCoupon(coupon);
	}

	@GetMapping
	@RequestMapping("/byCouponType/{couponType}")
	public ArrayList<CouponEntity> getCouponByCouponType(@PathVariable("couponType") CouponType couponType)
			throws ApplicationException {
		return (ArrayList<CouponEntity>) couponController.getCouponByCouponType(couponType);
	}

	@GetMapping
	@RequestMapping("/upToPrice/{couponPrice}")
	public List<CouponEntity> getCouponsUpToPrice(@PathVariable("couponPrice") int couponPrice) throws ApplicationException {
		return couponController.getCouponsUpToPrice(couponPrice);
	}

	//won't get slashes in date 
	@GetMapping
	@RequestMapping("/upToDate/{couponEndDate}")
	public List<CouponEntity> getCouponsUpToDate(@PathVariable("couponEndDate") String couponEndDate)
			throws ApplicationException {
		return couponController.getCouponsUpToDate(couponEndDate);
	}

	//gives back all coupons, even unexpired ones
	@GetMapping
	@RequestMapping("/byExpiration")
	public List<CouponEntity> getAllExpiredCoupons() throws ApplicationException {
		return couponController.getAllExpiredCoupons();
	}

	@GetMapping
	@RequestMapping("/byCustomerId/{customerId}/")
	public List<CouponEntity> getCouponByCustomerId(@PathVariable("customerId") long customerId) throws ApplicationException {
		return couponController.getCouponByCustomerId(customerId);
	}

	@GetMapping
	@RequestMapping("/byCompanyId/{companyId}")
	public List<CouponEntity> getCouponByCompanyId(@PathVariable("companyId") long companyId) throws ApplicationException {
		return couponController.getCouponByCompanyId(companyId);
	}

	
	//not showing in db
	@PostMapping
	@RequestMapping("/purchase/{couponId}")
	public void purchase(HttpServletRequest request, @PathVariable("couponId") Long couponId)
			throws ApplicationException {
		long customerId = 0;
		String StrCustomerId = CookieUtils.getCookieValue(request, "login");
		customerId = Long.parseLong(StrCustomerId);
		System.out.println("customer" + customerId+ "couponid" + couponId);
		couponController.purchase(customerId, couponId);
	}

}
