package com.jarida.server.jaridaserver.organization_one_to_one.model;

//Every organization will be associated with an address. So you can call it a one-to-one relation.
/*
* You can find a reference to address entity in the organization entity but no reference to the
* organization entity in the address entity, so we call it a unidirectional relation.
* */


import javax.persistence.*;

@Entity
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String orgId;

    //are how you define a one-to-one mapping.
    @OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL)
    private Address address;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgId() {
        return this.orgId;
    }


    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}

