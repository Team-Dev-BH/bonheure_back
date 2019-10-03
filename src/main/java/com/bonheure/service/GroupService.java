package com.bonheure.service;

import com.bonheure.controller.dto.GroupDTO;
import com.bonheure.domain.Group;
import com.bonheure.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;


    public GroupDTO saveGroup(GroupDTO groupDTO) {
        Group group = getGroupFromDto(groupDTO);

        groupRepository.save(group);

        return groupDTO;

    }


    public GroupDTO getGroupByReference(String reference) {
        Group group = groupRepository.findByReference(reference);

        GroupDTO groupDTO = getGroupDTOFromGroup(group);

        return groupDTO;
    }

    public void deleteGroupByReference(String reference) {
        Group group = groupRepository.findByReference(reference);
        groupRepository.delete(group);

    }

    public GroupDTO updateGroupByReference(String reference, GroupDTO groupDTO) {
        Group groupOld = groupRepository.findByReference(reference);
        Group groupNew = getGroupFromDto(groupDTO);

        if (groupOld != null) {
            Update(groupOld, groupNew);
        }
        return groupDTO;
    }


    private GroupDTO getGroupDTOFromGroup(Group group) {

        GroupDTO groupDTO = new GroupDTO();

        groupDTO.setName(group.getName());
        groupDTO.setReference(group.getReference());


        return groupDTO;
    }


    private Group getGroupFromDto(GroupDTO groupDTO) {
        Group group = new Group();

        group.setName(groupDTO.getName());
        group.setReference(groupDTO.getReference());

        return group;
    }

    private void Update(Group GroupOld, Group GroupNew) {

        GroupOld.setName(GroupNew.getName());
        GroupOld.setReference(GroupNew.getReference());

        groupRepository.save(GroupOld);
    }
}
