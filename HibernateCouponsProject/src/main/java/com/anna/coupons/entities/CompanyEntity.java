package com.anna.coupons.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Companies")
public class CompanyEntity {

	public CompanyEntity() {
	};

	public CompanyEntity(String companyName, String companyPassword, String companyEmail) {
		this.companyName = companyName;
		this.companyPassword = companyPassword;
		this.companyEmail = companyEmail;
	}
	
	public CompanyEntity(long companyId ,String companyName, String companyPassword, String companyEmail) {
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyPassword = companyPassword;
		this.companyEmail = companyEmail;
	}

	@GeneratedValue
	@Id
	@Column(name="CompanyId", nullable=false)
	private long companyId;

	@Column(name = "CompanyName", nullable = false)
	private String companyName;

	@Column(name = "CompanyPassword", nullable = false)
	private String companyPassword;

	@Column(name = "CompanyEmail", nullable = false)
	private String companyEmail;

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyPassword() {
		return companyPassword;
	}

	public void setCompanyPassword(String companyPassword) {
		this.companyPassword = companyPassword;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	@Override
	public String toString() {
		return "CompaniesEntity [companyId=" + companyId + ", companyName=" + companyName + ", companyPassword="
				+ companyPassword + ", companyEmail=" + companyEmail + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyEmail == null) ? 0 : companyEmail.hashCode());
		result = prime * result + (int) (companyId ^ (companyId >>> 32));
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((companyPassword == null) ? 0 : companyPassword.hashCode());
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
		CompanyEntity other = (CompanyEntity) obj;
		if (companyEmail == null) {
			if (other.companyEmail != null)
				return false;
		} else if (!companyEmail.equals(other.companyEmail))
			return false;
		if (companyId != other.companyId)
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (companyPassword == null) {
			if (other.companyPassword != null)
				return false;
		} else if (!companyPassword.equals(other.companyPassword))
			return false;
		return true;
	}

}
