package com.bonheure.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "clients")
@DiscriminatorValue("CLIENT")
public class Client extends User {

    private String position;

    private LocalDateTime birthDate;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Company company;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Group> groups;

    @OneToOne
    private Address address;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


}
