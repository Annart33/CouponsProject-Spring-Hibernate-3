package com.anna.coupons.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.anna.coupons.dao.CompanyDao;
import com.anna.coupons.entities.CompanyEntity;
import com.anna.coupons.enums.ErrorType;
import com.anna.coupons.exceptions.ApplicationException;
import com.anna.coupons.utils.validationUtils;

@Controller
public class CompanyController {
	
	@Autowired
	private CompanyDao companyDao;

	public void createCompany(CompanyEntity company) throws ApplicationException {
		validateCreateCompany(company);

		this.companyDao.createCompany(company);
	}

	private void validateCreateCompany(CompanyEntity company) throws ApplicationException {

		if (!validationUtils.isEmailAddressValid(company.getCompanyEmail())) {
			throw new ApplicationException(ErrorType.INVALID_EMAIL, "Not an Email format");
		} else if (!validationUtils.ispasswordValid(company.getCompanyPassword())) {
			throw new ApplicationException(ErrorType.INVALID_PASSWROD,
					"Password doesn't contain Upper-Case letters or 8 digits");
		}

	}

	public void deleteCompany(long companyId) throws ApplicationException {
		this.companyDao.deleteCompany(companyId);
	}

	public void updateCompany(CompanyEntity company) throws ApplicationException {
		this.companyDao.updateCompany(company);
	}

	public List<CompanyEntity> getAllCompanies() throws ApplicationException {
		return this.companyDao.getAllComapnies();
	}

	public CompanyEntity getCompanyByCompanyId(long companyId) throws ApplicationException {
		return this.companyDao.getCompanyByCompanyId(companyId);
	}

	public Long login(String companyEmail, String companyPassword) throws ApplicationException {
		return this.companyDao.login(companyEmail, companyPassword);
	}
}
