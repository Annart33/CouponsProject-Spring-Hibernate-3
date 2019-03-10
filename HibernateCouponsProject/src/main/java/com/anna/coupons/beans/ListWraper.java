package com.anna.coupons.beans;

import java.util.List;

import com.anna.coupons.entities.CouponEntity;


public class ListWraper {
	
	private List<CouponEntity> coupons;

	public ListWraper() {
	}

	public List<CouponEntity> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<CouponEntity> coupons) {
		this.coupons = coupons;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coupons == null) ? 0 : coupons.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListWraper other = (ListWraper) obj;
		if (coupons == null) {
			if (other.coupons != null)
				return false;
		} else if (!coupons.equals(other.coupons))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ListWraper [coupons=" + coupons + "]";
	}
}
