//package com.anna.coupons.entities;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//
//@Entity
//@Table(name = "CouponCustomer")
//public class CouponCustomerEntity {
//	
//	public CouponCustomerEntity() {};
//
//	public CouponCustomerEntity(long couponId, long customerId) {
//		this.couponId = couponId;
//		this.customerId = customerId;
//		
//	}
//	
//	@GeneratedValue
//	@Id
//	@Column(name = "CouponId", nullable = false)
//	private long couponId;
//
//	@Column(name = "CustomerId", nullable = false)
//	private long customerId;
//
//	@ManyToOne
//	@JoinColumn(name = "CouponId")
//	private CouponEntity couponEntity;
//
//	@ManyToOne
//	@JoinColumn(name = "CustomerId")
//	private CustomerEntity customerEntity;
//
//	public long getCouponId() {
//		return couponId;
//	}
//
//	public void setCouponId(long couponId) {
//		this.couponId = couponId;
//	}
//
//	public long getCustomerId() {
//		return customerId;
//	}
//
//	public void setCustomerId(long customerId) {
//		this.customerId = customerId;
//	}
//
//	public CouponEntity getCouponEntity() {
//		return couponEntity;
//	}
//
//	public void setCouponEntity(CouponEntity couponEntity) {
//		this.couponEntity = couponEntity;
//	}
//
//	public CustomerEntity getCustomerEntity() {
//		return customerEntity;
//	}
//
//	public void setCustomerEntity(CustomerEntity customerEntity) {
//		this.customerEntity = customerEntity;
//	}
//
//	@Override
//	public String toString() {
//		return "CouponCustomerEntity [couponId=" + couponId + ", customerId=" + customerId + ", couponEntity="
//				+ couponEntity + ", customerEntity=" + customerEntity + "]";
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((couponEntity == null) ? 0 : couponEntity.hashCode());
//		result = prime * result + (int) (couponId ^ (couponId >>> 32));
//		result = prime * result + ((customerEntity == null) ? 0 : customerEntity.hashCode());
//		result = prime * result + (int) (customerId ^ (customerId >>> 32));
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		CouponCustomerEntity other = (CouponCustomerEntity) obj;
//		if (couponEntity == null) {
//			if (other.couponEntity != null)
//				return false;
//		} else if (!couponEntity.equals(other.couponEntity))
//			return false;
//		if (couponId != other.couponId)
//			return false;
//		if (customerEntity == null) {
//			if (other.customerEntity != null)
//				return false;
//		} else if (!customerEntity.equals(other.customerEntity))
//			return false;
//		if (customerId != other.customerId)
//			return false;
//		return true;
//	}
//
//
//
//}
