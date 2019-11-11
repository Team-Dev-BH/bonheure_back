package com.bonheure.service;

import com.bonheure.controller.dto.GroupDTO;
 import com.bonheure.domain.Group;
 
import com.bonheure.repository.GroupRepository;
import com.bonheure.utils.ApiMapper;

import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;


    @Autowired
    private ApiMapper apiMapper;

//savegroup
    
    public GroupDTO saveGroup(GroupDTO groupDTO) {

    	groupDTO.setReference(UUID.randomUUID().toString());
        Group group = apiMapper.fromDTOToBean(groupDTO);
        groupRepository.save(group);

        return groupDTO;

    }
//getgroupByReference
    
    public GroupDTO getGroupByReference(String reference) {
    	Group group = groupRepository.findOneByReference(reference).
                orElse(null);

        if (group == null)
            return null;
        GroupDTO groupDTO = apiMapper.fromBeanToDTO(group);

        return groupDTO;
    }

//deleteUGroupByReference
    
    public void deleteUGroupByReference(String reference) {
    	Group group = groupRepository.findOneByReference(reference).
                orElse(null);


    	groupRepository.delete(group);

    }
//updateUGroupByReference
    public GroupDTO updateUGroupByReference(String reference, GroupDTO groupDTO) {

        //TODO throw exception if not found
    	Group oldGroup = groupRepository.findOneByReference(reference).orElse(null);
       

        if (oldGroup  != null) {
            apiMapper.updateBeanFromDto(groupDTO, oldGroup);
            groupRepository.save(oldGroup);
        }
        return groupDTO;
    }
    
}
   

