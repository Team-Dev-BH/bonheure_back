package com.bonheure.domain;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "groupes")
@Table(name = "groupes", indexes = {@Index(name = "index_group_reference", columnList = "reference", unique = true)})
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String reference;

    @ManyToOne
    private Company company;

    @ManyToMany
    private Set<Prestation> prestations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Set<Prestation> getPrestations() {
        return prestations;
    }

    public void setPrestations(Set<Prestation> prestations) {
        this.prestations = prestations;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
