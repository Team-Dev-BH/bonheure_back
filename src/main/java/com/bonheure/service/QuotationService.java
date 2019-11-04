package com.bonheure.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bonheure.controller.dto.QuotationDTO;
import com.bonheure.domain.Quotation;
import com.bonheure.repository.QuotationRepository;
import com.bonheure.utils.ApiMapper;

@Service
public class QuotationService {
	
	@Autowired
	QuotationRepository quotationRepository;
	
	@Autowired
	private ApiMapper apiMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	//postQuotation
	
	public QuotationDTO saveQuotation(QuotationDTO quotationDTO) {

		quotationDTO.setReference(UUID.randomUUID().toString());

		Quotation quotation = apiMapper.fromDTOToBean(quotationDTO);

		quotationRepository.save(quotation);

		return quotationDTO;
	}
	
	//getAllQuotations
    public List<QuotationDTO> getAllQuotation() {
		
		List<QuotationDTO> quotationDTOs=new ArrayList<QuotationDTO>();
		
		List<Quotation> quotations =quotationRepository.findAll();
		
		
		for (Quotation quotation:quotations) {
			
			if (quotation  == null)
				return null;
			
			QuotationDTO quotationDTO = apiMapper.fromBeanToDTO(quotation);
			quotationDTOs.add(quotationDTO);
		}

		return quotationDTOs;
	}

	// updateQuotationByRefrence

	public QuotationDTO updateQuotationByRefrence(String reference, QuotationDTO quotationDTO) {

		Quotation oldQuotation = quotationRepository. findOneByReference(reference).orElse(null);

		if (oldQuotation != null) {
			apiMapper.updateBeanFromDto(quotationDTO, oldQuotation);
			quotationRepository.save(oldQuotation);
		}
		return quotationDTO;
	}


}
