package com.bonheure.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonheure.controller.dto.WorkingAreaDTO;
import com.bonheure.domain.WorkingArea;
import com.bonheure.repository.WorkingAreaRepository;
import com.bonheure.utils.ApiMapper;

@Service
public class WorkingAreaService {
	
	@Autowired
	WorkingAreaRepository workingAreaRepository;

    @Autowired
    private ApiMapper apiMapper;
    
    
    // save WorkingArea
    
    public WorkingAreaDTO saveWorkingArea(WorkingAreaDTO workingAreaDTO) {

    	workingAreaDTO.setReference(UUID.randomUUID().toString());
    	WorkingArea workingArea = apiMapper.fromDTOToBean(workingAreaDTO);
        workingAreaRepository.save(workingArea);

        return workingAreaDTO;

    }
    
    //get WorkingAreaByReference 
    
    public WorkingAreaDTO getWorkingAreaByReference(String reference) {
    	WorkingArea workingArea = workingAreaRepository.findOneByReference(reference).
                orElse(null);

        if (workingArea == null)
            return null;
        WorkingAreaDTO workingAreaDTO = apiMapper.fromBeanToDTO(workingArea);

        return workingAreaDTO;
    }
    
    //delete WorkingArearByReference
    
    public void deleteWorkingArearByReference(String reference) {
    	WorkingArea workingArea = workingAreaRepository.findOneByReference(reference).
                orElse(null);


        workingAreaRepository.delete(workingArea);

    }
    //update WorkingAreaByReference 
    
    public WorkingAreaDTO updateWorkingAreaByReference(String reference, WorkingAreaDTO workingAreaDTO) {

        //TODO throw exception if not found
    	WorkingArea oldWorkingArea = workingAreaRepository.findOneByReference(reference).orElse(null);

        if (oldWorkingArea != null) {
            apiMapper.updateBeanFromDto(workingAreaDTO, oldWorkingArea);
            workingAreaRepository.save(oldWorkingArea);
        }
        return workingAreaDTO;
    }
}
