package com.bonheure.controller.dto;


import java.util.Set;

public class GroupDTO {


    private String name;
    private String reference;
    private String companyReference;

    private Set<String> prestationReferences;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * @param reference the reference to set
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    public Set<String> getPrestationReferences() {
        return prestationReferences;
    }

    public void setPrestationReferences(Set<String> prestationReferences) {
        this.prestationReferences = prestationReferences;
    }

    public String getCompanyReference() {
        return companyReference;
    }

    public void setCompanyReference(String companyReference) {
        this.companyReference = companyReference;
    }
}
