package com.bonheure.service;

import com.bonheure.controller.dto.CompanyDTO;
import com.bonheure.domain.Company;
import com.bonheure.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository societyRepository;


    public CompanyDTO saveCompany(CompanyDTO companyDTO) {
        Company company = getCompanyFromDto(companyDTO);

        societyRepository.save(company);

        return companyDTO;

    }


    public CompanyDTO getCompanyByReference(String reference) {
        Company company = societyRepository.findOneByReference(reference).orElse(null);

        CompanyDTO companyDTO = getCompanyDTOFromCompany(company);

        return companyDTO;
    }

    public void deleteCompanyByReference(String reference) {
        Company company = societyRepository.findOneByReference(reference).orElse(null);
        societyRepository.delete(company);

    }

    public CompanyDTO updateCompanyByReference(String reference, CompanyDTO companyDTO) {
        Company companyOld = societyRepository.findOneByReference(reference).orElse(null);
        Company companyNew = getCompanyFromDto(companyDTO);

        if (companyOld != null) {
            Update(companyOld, companyNew);
        }
        return companyDTO;
    }


    private CompanyDTO getCompanyDTOFromCompany(Company company) {

        CompanyDTO companyDTO = new CompanyDTO();

        companyDTO.setActivityField(company.getActivityField());
        companyDTO.setCode(company.getCode());
        companyDTO.setName(company.getName());
        companyDTO.setReference(company.getReference());


        return companyDTO;
    }


    private Company getCompanyFromDto(CompanyDTO companyDTO) {
        Company company = new Company();

        company.setActivityField(companyDTO.getActivityField());
        company.setCode(companyDTO.getCode());
        company.setName(companyDTO.getName());
        company.setReference(companyDTO.getReference());
        return company;
    }

    private void Update(Company CompanyOld, Company CompanyNew) {

        CompanyOld.setActivityField(CompanyNew.getActivityField());
        CompanyOld.setCode(CompanyNew.getCode());
        CompanyOld.setName(CompanyNew.getName());
        CompanyOld.setReference(CompanyNew.getReference());


        societyRepository.save(CompanyOld);
    }

}
