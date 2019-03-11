package com.anna.coupons.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.anna.coupons.enums.CouponType;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Coupons")
public class CouponEntity {

	public CouponEntity() {
	};

	public CouponEntity(long couponId, String couponTitle, String couponStartDate, String couponEndDate,
			int couponAmount, CouponType couponType, String couponMessage, int couponPrice, String couponImage,
			long companyId) {
		this.couponId = couponId;
		this.couponTitle = couponTitle;
		this.couponStartDate = couponStartDate;
		this.couponEndDate = couponEndDate;
		this.couponAmount = couponAmount;
		this.couponType = couponType;
		this.couponMessage = couponMessage;
		this.couponPrice = couponPrice;
		this.couponImage = couponImage;
		this.companyId = companyId;
	}

	public CouponEntity(String couponTitle, String couponStartDate, String couponEndDate, int couponAmount,
			CouponType couponType, String couponMessage, int couponPrice, String couponImage, long companyId) {
		this.couponTitle = couponTitle;
		this.couponStartDate = couponStartDate;
		this.couponEndDate = couponEndDate;
		this.couponAmount = couponAmount;
		this.couponType = couponType;
		this.couponMessage = couponMessage;
		this.couponPrice = couponPrice;
		this.couponImage = couponImage;
		this.companyId = companyId;
	}

	@GeneratedValue
	@Id
	private long couponId;

	@Column(name = "CouponTitle", nullable = false)
	private String couponTitle;

	@Column(name = "CouponStartDate", nullable = false)
	private String couponStartDate;

	@Column(name = "CouponEndDate", nullable = false)
	private String couponEndDate;

	@Column(name = "CouponAmount", nullable = false)
	private int couponAmount;

	@Column(name = "CouponType", nullable = false)
	private CouponType couponType;

	@Column(name = "CouponMessage", nullable = false)
	private String couponMessage;

	@Column(name = "CouponPrice", nullable = false)
	private int couponPrice;

	@Column(name = "CouponImage", nullable = false)
	private String couponImage;

	@Column(name = "CompanyId", nullable = false)
	private long companyId;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "couponcustomer", joinColumns = {
			@JoinColumn(name = "CouponId", referencedColumnName = "couponId") }, inverseJoinColumns = {
					@JoinColumn(name = "CustomerId", referencedColumnName = "customerId") })
	@JsonIgnore
	private List<CustomerEntity> purchasers;

	public void addPurchesers(CustomerEntity purcheser) {
		this.purchasers.add(purcheser);
	}

	public boolean removePurchesers(CustomerEntity purcheser) {
		return this.purchasers.remove(purcheser);
	}

	public List<CustomerEntity> getPurchasers() {
		return purchasers;
	}

	public void setPurchasers(List<CustomerEntity> purchasers) {
		this.purchasers = purchasers;
	}

	public void reduceAmountByOne() {
		couponAmount--;
	}

	public void increaseAmountByOne() {
		couponAmount++;
	}

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	public String getCouponTitle() {
		return couponTitle;
	}

	public void setCouponTitle(String couponTitle) {
		this.couponTitle = couponTitle;
	}

	public String getCouponStartDate() {
		return couponStartDate;
	}

	public void setCouponStartDate(String couponStartDate) {
		this.couponStartDate = couponStartDate;
	}

	public String getCouponEndDate() {
		return couponEndDate;
	}

	public void setCouponEndDate(String couponEndDate) {
		this.couponEndDate = couponEndDate;
	}

	public int getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(int couponAmount) {
		this.couponAmount = couponAmount;
	}

	public CouponType getCouponType() {
		return couponType;
	}

	public void setCouponType(CouponType couponType) {
		this.couponType = couponType;
	}

	public String getCouponMessage() {
		return couponMessage;
	}

	public void setCouponMessage(String couponMessage) {
		this.couponMessage = couponMessage;
	}

	public int getCouponPrice() {
		return couponPrice;
	}

	public void setCouponPrice(int couponPrice) {
		this.couponPrice = couponPrice;
	}

	public String getCouponImage() {
		return couponImage;
	}

	public void setCouponImage(String couponImage) {
		this.couponImage = couponImage;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "CouponEntity [couponId=" + couponId + ", couponTitle=" + couponTitle + ", couponStartDate="
				+ couponStartDate + ", couponEndDate=" + couponEndDate + ", couponAmount=" + couponAmount
				+ ", couponType=" + couponType + ", couponMessage=" + couponMessage + ", couponPrice=" + couponPrice
				+ ", couponImage=" + couponImage + ", companyId=" + companyId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (companyId ^ (companyId >>> 32));
		result = prime * result + couponAmount;
		result = prime * result + ((couponEndDate == null) ? 0 : couponEndDate.hashCode());
		result = prime * result + (int) (couponId ^ (couponId >>> 32));
		result = prime * result + ((couponImage == null) ? 0 : couponImage.hashCode());
		result = prime * result + ((couponMessage == null) ? 0 : couponMessage.hashCode());
		result = prime * result + couponPrice;
		result = prime * result + ((couponStartDate == null) ? 0 : couponStartDate.hashCode());
		result = prime * result + ((couponTitle == null) ? 0 : couponTitle.hashCode());
		result = prime * result + ((couponType == null) ? 0 : couponType.hashCode());
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
		CouponEntity other = (CouponEntity) obj;
		if (companyId != other.companyId)
			return false;
		if (couponAmount != other.couponAmount)
			return false;
		if (couponEndDate == null) {
			if (other.couponEndDate != null)
				return false;
		} else if (!couponEndDate.equals(other.couponEndDate))
			return false;
		if (couponId != other.couponId)
			return false;
		if (couponImage == null) {
			if (other.couponImage != null)
				return false;
		} else if (!couponImage.equals(other.couponImage))
			return false;
		if (couponMessage == null) {
			if (other.couponMessage != null)
				return false;
		} else if (!couponMessage.equals(other.couponMessage))
			return false;
		if (couponPrice != other.couponPrice)
			return false;
		if (couponStartDate == null) {
			if (other.couponStartDate != null)
				return false;
		} else if (!couponStartDate.equals(other.couponStartDate))
			return false;
		if (couponTitle == null) {
			if (other.couponTitle != null)
				return false;
		} else if (!couponTitle.equals(other.couponTitle))
			return false;
		if (couponType != other.couponType)
			return false;
		return true;
	}

}
