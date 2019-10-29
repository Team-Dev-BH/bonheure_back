package com.bonheure.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(indexes = {@Index(name = "index_prestation_reference", columnList = "reference", unique = true)})
public class Prestation  implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String reference;

    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> tags;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Category> categories;

    @ManyToOne
    private Prestation parent;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Prestation getParent() {
        return parent;
    }

    public void setParent(Prestation parent) {
        this.parent = parent;
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }

    
}
