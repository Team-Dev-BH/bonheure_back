package com.bonheure.utils;

import com.bonheure.controller.dto.AddressDTO;
import com.bonheure.controller.dto.CategoryDTO;
import com.bonheure.controller.dto.ClientDTO;
import com.bonheure.controller.dto.CompanyDTO;
import com.bonheure.controller.dto.GroupDTO;
import com.bonheure.controller.dto.PrestataireDTO;
import com.bonheure.controller.dto.PrestationDTO;
import com.bonheure.controller.dto.UserDTO;
import com.bonheure.controller.dto.WorkingAreaDTO;
import com.bonheure.domain.Address;
import com.bonheure.domain.Category;
import com.bonheure.domain.Client;
import com.bonheure.domain.Company;
import com.bonheure.domain.Group;
import com.bonheure.domain.Prestataire;
import com.bonheure.domain.Prestation;
import com.bonheure.domain.User;
import com.bonheure.domain.WorkingArea;
import com.bonheure.repository.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = { Collectors.class })
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

	@Autowired
	WorkingAreaRepository workingAreaRepository;

	// group
	@Mappings({ @Mapping(target = "reference", ignore = true),
			@Mapping(target = "company", expression = "java(companyRepository.findOneByReference(dto.getCompanyReference()).orElse(null))"),
			@Mapping(expression = "java(dto.getPrestationReferences().stream()"
					+ ".map(reference -> prestationRepository.findOneByReference(reference).orElse(null))"
					+ ".filter(prestation -> prestation != null)"
					+ ".collect(Collectors.toSet()))", target = "prestations") })
	public abstract void updateBeanFromDto(GroupDTO dto, @MappingTarget Group bean);

	@Mappings({ @Mapping(source = "company.reference", target = "companyReference"),
			@Mapping(expression = "java(bean.getPrestations().stream().map(prestation -> prestation.getReference()).collect(Collectors.toSet()))", target = "prestationReferences"), })
	public abstract GroupDTO fromBeanToDTO(Group bean);

	@Mappings({
			@Mapping(target = "company", expression = "java(companyRepository.findOneByReference(dto.getCompanyReference()).orElse(null))"),
			@Mapping(expression = "java(dto.getPrestationReferences().stream()"
					+ ".map(reference -> prestationRepository.findOneByReference(reference).orElse(null))"
					+ ".filter(prestation -> prestation != null)"
					+ ".collect(Collectors.toSet()))", target = "prestations"), })
	public abstract Group fromDTOToBean(GroupDTO dto);

	// user

	@Mappings({ @Mapping(target = "reference", ignore = true), @Mapping(target = "creationDate", ignore = true),
			@Mapping(target = "modificationDate", ignore = true), @Mapping(target = "activated", ignore = true) })
	public abstract void updateBeanFromDto(UserDTO dto, @MappingTarget User bean);

	@Mappings({ @Mapping(target = "password", ignore = true) })
	public abstract UserDTO fromBeanToDTO(User bean);

	@Mappings({ @Mapping(target = "creationDate", ignore = true),
			@Mapping(target = "modificationDate", ignore = true) })
	public abstract User fromDTOToBean(UserDTO dto);

	// Working Area

	@Mappings({ @Mapping(target = "reference", ignore = true) })
	public abstract void updateBeanFromDto(WorkingAreaDTO dto, @MappingTarget WorkingArea bean);

	@Mappings({})
	public abstract WorkingAreaDTO fromBeanToDTO(WorkingArea bean);

	@Mappings({})

	public abstract WorkingArea fromDTOToBean(WorkingAreaDTO dto);

	// Category

	@Mappings({ @Mapping(target = "reference", ignore = true),
		@Mapping(expression = "java(dto.getPrestationsNames().stream()"
				+ ".map(name -> prestationRepository.findOneByName(name).orElse(null))"
				+ ".filter(prestation -> prestation != null)" + ".collect(Collectors.toSet()))", target = "prestations")})
	public abstract void updateBeanFromDto(CategoryDTO dto, @MappingTarget Category bean);

	@Mappings({	@Mapping(expression = "java(bean.getPrestations().stream().map(prestation -> prestation.getName())."
			+ "collect(Collectors.toSet()))", target = "prestationsNames")})
	public abstract CategoryDTO fromBeanToDTO(Category bean);

	@Mappings({@Mapping(expression = "java(dto.getPrestationsNames().stream()"
			+ ".map(name -> prestationRepository.findOneByName(name).orElse(null))"
			+ ".filter(prestation -> prestation != null)" + ".collect(Collectors.toSet()))", target = "prestations"),})
	public abstract Category fromDTOToBean(CategoryDTO dto);


	// Adress

	@Mappings({ @Mapping(target = "reference", ignore = true) })
	public abstract void updateBeanFromDto(AddressDTO dto, @MappingTarget Address bean);

	@Mappings({})
	public abstract AddressDTO fromBeanToDTO(Address bean);

	@Mappings({})
	public abstract Address fromDTOToBean(AddressDTO dto);

	// client
	@Mappings({ @Mapping(source = "address.reference", target = "adressReference"),
			@Mapping(source = "company.reference", target = "companyReference"),
			@Mapping(target = "password", ignore = true),
			@Mapping(expression = "java(bean.getGroups().stream().map(group -> group.getReference()).collect(Collectors.toSet()))", target = "groupReferences"), })
	public abstract ClientDTO fromBeanToDTO(Client bean);

	@Mappings({ @Mapping(target = "creationDate", ignore = true), @Mapping(target = "modificationDate", ignore = true),
			@Mapping(target = "address", expression = "java(addressRepository.findOneByReference(dto.getAdressReference()).orElse(null))"),
			@Mapping(target = "company", expression = "java(companyRepository.findOneByReference(dto.getCompanyReference()).orElse(null))"),
			@Mapping(expression = "java(dto.getGroupReferences().stream()"
					+ ".map(reference -> groupRepository.findOneByReference(reference).orElse(null) )"
					+ ".filter(group -> group != null)" + ".collect(Collectors.toSet()))", target = "groups"), })
	public abstract Client fromDTOToBean(ClientDTO dto);

	@Mappings({

			@Mapping(target = "reference", ignore = true), @Mapping(target = "creationDate", ignore = true),
			@Mapping(target = "modificationDate", ignore = true),
			@Mapping(target = "company", expression = "java(companyRepository.findOneByReference(dto.getCompanyReference()).orElse(null))"),
			@Mapping(expression = "java(dto.getGroupReferences().stream()"
					+ ".map(reference -> groupRepository.findOneByReference(reference).orElse(null))"
					+ ".filter(group -> group != null)" + ".collect(Collectors.toSet()))", target = "groups") })
	public abstract void updateBeanFromDto(ClientDTO dto, @MappingTarget Client bean);

	// company

	@Mappings({
			@Mapping(target = "principalAddress", expression = "java(addressRepository.findOneByReference(dto.getPrincipalAddressReference())."
					+ "orElse(null))"),
			@Mapping(expression = "java(dto.getAddressesReferences().stream()"
					+ ".map(reference -> addressRepository.findOneByReference(reference).orElse(null))"
					+ ".filter(address -> address != null)" + ".collect(Collectors.toSet()))", target = "addresses") })
	public abstract Company fromDTOToBean(CompanyDTO dto);

	@Mappings({ @Mapping(source = "principalAddress.reference", target = "principalAddressReference"),
			@Mapping(expression = "java(bean.getAddresses().stream().map(Address -> Address.getReference()).collect(Collectors.toSet()))", target = "addressesReferences") })
	public abstract CompanyDTO fromBeanToDTO(Company bean);

	@Mappings({ @Mapping(target = "reference", ignore = true),
			@Mapping(target = "principalAddress", expression = "java(addressRepository.findOneByReference(dto.getPrincipalAddressReference()).orElse(null))"),
			@Mapping(expression = "java(dto.getAddressesReferences().stream()"
					+ ".map(reference -> addressRepository.findOneByReference(reference).orElse(null))"
					+ ".filter(address -> address != null)" + ".collect(Collectors.toSet()))", target = "addresses") })
	public abstract void updateBeanFromDto(CompanyDTO dto, @MappingTarget Company bean);

	// Prestataire

	@Mappings({ @Mapping(target = "creationDate", ignore = true), @Mapping(target = "modificationDate", ignore = true),
			@Mapping(expression = "java(dto.getPrestationReferences().stream()"
					+ ".map(reference -> prestationRepository.findOneByReference(reference).orElse(null))"
					+ ".filter(prestation -> prestation != null)"
					+ ".collect(Collectors.toSet()))", target = "prestations"),
			@Mapping(expression = "java(dto.getWorkingAreasReferences().stream()"
					+ ".map(reference -> workingAreaRepository.findOneByReference(reference).orElse(null))"
					+ ".filter(workingArea -> workingArea != null)"
					+ ".collect(Collectors.toSet()))", target = "workingAreas"),
			@Mapping(target = "address", expression = "java(addressRepository.findOneByReference(dto.getAddressReference()).orElse(null))") })
	public abstract Prestataire fromDTOToBean(PrestataireDTO dto);

	@Mappings({ @Mapping(source = "address.reference", target = "addressReference"),

			@Mapping(expression = "java(bean.getPrestations().stream().map(prestation -> prestation.getReference()).collect(Collectors.toSet()))", target = "prestationReferences"),
			@Mapping(expression = "java(bean.getWorkingAreas().stream().map(workingArea -> workingArea.getReference()).collect(Collectors.toSet()))", target = "workingAreasReferences") })
	public abstract PrestataireDTO fromBeanToDTO(Prestataire bean);

	@Mappings({ @Mapping(target = "reference", ignore = true), @Mapping(target = "creationDate", ignore = true),
			@Mapping(target = "modificationDate", ignore = true),
			@Mapping(expression = "java(dto.getPrestationReferences().stream()"
					+ ".map(reference -> prestationRepository.findOneByReference(reference).orElse(null))"
					+ ".filter(prestation -> prestation != null)"
					+ ".collect(Collectors.toSet()))", target = "prestations"),
			@Mapping(expression = "java(dto.getWorkingAreasReferences().stream()"
					+ ".map(reference -> workingAreaRepository.findOneByReference(reference).orElse(null))"
					+ ".filter(workingArea -> workingArea != null)"
					+ ".collect(Collectors.toSet()))", target = "workingAreas"),
			@Mapping(target = "address", expression = "java(addressRepository.findOneByReference(dto.getAddressReference()).orElse(null))") })
	public abstract void updateBeanFromDto(PrestataireDTO dto, @MappingTarget Prestataire bean);

	// Prestation

	@Mappings({
		@Mapping(expression = "java(dto.getCategoriesNames().stream()"
		+ ".map(name -> categoryRepository.findOneByName(name).orElse(null))"
		+ ".filter(category -> category != null)" + ".collect(Collectors.toSet()))", target = "categories"),
		@Mapping(target = "parent", expression = "java(prestationRepository.findOneByReference(dto.getParentReference()).orElse(null))"), })
		public abstract Prestation fromDTOToBean(PrestationDTO dto);

		@Mappings({ @Mapping(source = "parent.reference", target = "parentReference"),
		@Mapping(expression = "java(bean.getCategories().stream().map(caterory -> caterory.getName()).collect(Collectors.toSet()))", target = "categoriesNames"), })
		public abstract PrestationDTO fromBeanToDTO(Prestation bean);

		@Mappings({ @Mapping(target = "reference", ignore = true),
		@Mapping(expression = "java(dto.getCategoriesNames().stream()"
		+ ".map(name -> categoryRepository.findOneByName(name).orElse(null))"
		+ ".filter(category -> category != null)" + ".collect(Collectors.toSet()))", target = "categories"),
		@Mapping(target = "parent", expression = "java(prestationRepository.findOneByReference(dto.getParentReference()).orElse(null))"), })

		public abstract void updateBeanFromDto(PrestationDTO dto, @MappingTarget Prestation bean);

}
