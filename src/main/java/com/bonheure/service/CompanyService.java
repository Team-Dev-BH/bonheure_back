package com.bonheure.service;

import com.bonheure.controller.dto.CompanyDTO;
import com.bonheure.domain.Company;
import com.bonheure.repository.CompanyRepository;
import com.bonheure.utils.ApiMapper;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;
    
    @Autowired
    private ApiMapper apiMapper;

//savecompany
    
    public CompanyDTO saveCompany(CompanyDTO companyDTO) {
    	
    	companyDTO.setReference(UUID.randomUUID().toString());
        Company company = apiMapper.fromDTOToBean(companyDTO);

        companyRepository.save(company);
        

        return companyDTO;

    }
    //getcompanybyreference
    public CompanyDTO getCompanyByReference(String reference) {
        Company company = companyRepository.findOneByReference(reference).
        		orElse(null);
        
        if (company == null)
            return null;

        CompanyDTO companyDTO = apiMapper.fromBeanToDTO(company);

        return companyDTO;
    } 

    
    //deletecompanyByReference
      public void deleteCompanyByReference(String reference) {
        Company company = companyRepository.findOneByReference(reference).orElse(null);
        companyRepository.delete(company);

    } 

      //updateCompanyReference
     public CompanyDTO updateCompanyByReference(String reference, CompanyDTO companyDTO) {
        Company companyOld = companyRepository.findOneByReference(reference).orElse(null);
         

        if (companyOld != null) {
        	apiMapper.updateBeanFromDto(companyDTO, companyOld);
            companyRepository.save(companyOld);
             
        }
        return companyDTO;
    } 


     


     
}