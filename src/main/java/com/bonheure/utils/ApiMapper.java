package com.bonheure.utils;


import com.bonheure.controller.dto.GroupDTO;
import com.bonheure.controller.dto.UserDTO;
import com.bonheure.domain.Group;
import com.bonheure.domain.User;
import com.bonheure.repository.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;


@Mapper(componentModel = "spring", imports = {Collectors.class})
public abstract class ApiMapper {


    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    PrestationRepository prestationRepository;

    //group
    @Mappings({@Mapping(target = "reference", ignore = true),
            @Mapping(target = "company",
                    expression = "java(companyRepository.findOneByReference(dto.getCompanyReference()).orElse(null))"),
            @Mapping(expression = "java(dto.getPrestationReferences().stream()" +
                    ".map(reference -> prestationRepository.findOneByReference(reference).orElse(null))" +
                    ".filter(prestation -> prestation != null)" +
                    ".collect(Collectors.toSet()))",
                    target = "prestations")})
    public abstract void updateBeanFromDto(GroupDTO dto, @MappingTarget Group bean);

    @Mappings({
            @Mapping(source = "company.reference", target = "companyReference"),
            @Mapping(expression = "java(bean.getPrestations().stream().map(prestation -> prestation.getReference()).collect(Collectors.toSet()))",
                    target = "prestationReferences"),
    })
    public abstract GroupDTO fromBeanToDTO(Group bean);

    @Mappings({
            @Mapping(target = "company",
                    expression = "java(companyRepository.findOneByReference(dto.getCompanyReference()).orElse(null))"),
            @Mapping(expression = "java(dto.getPrestationReferences().stream()" +
                    ".map(reference -> prestationRepository.findOneByReference(reference).orElse(null))" +
                    ".filter(prestation -> prestation != null)" +
                    ".collect(Collectors.toSet()))",
                    target = "prestations"),})
    public abstract Group fromDTOToBean(GroupDTO dto);


    //user

    @Mappings({@Mapping(target = "reference", ignore = true),
            @Mapping(target = "creationDate", ignore = true),
            @Mapping(target = "modificationDate", ignore = true),})
    public abstract void updateBeanFromDto(UserDTO dto, @MappingTarget User bean);

    @Mappings({})
    public abstract UserDTO fromBeanToDTO(User bean);

    @Mappings({
            @Mapping(target = "creationDate", ignore = true),
            @Mapping(target = "modificationDate", ignore = true)})
    public abstract User fromDTOToBean(UserDTO dto);


}
