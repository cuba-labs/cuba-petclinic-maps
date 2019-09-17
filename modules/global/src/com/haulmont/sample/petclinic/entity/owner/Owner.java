package com.haulmont.sample.petclinic.entity.owner;

import com.haulmont.addon.maps.gis.Geometry;
import com.haulmont.addon.maps.gis.converters.wkt.CubaPointWKTConverter;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;
import com.haulmont.sample.petclinic.entity.Person;
import com.haulmont.sample.petclinic.entity.district.District;
import com.haulmont.sample.petclinic.entity.pet.Pet;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name = "PETCLINIC_OWNER")
@Entity(name = "petclinic_Owner")
public class Owner extends Person {
    private static final long serialVersionUID = 901690119511259222L;

    @NotNull
    @Column(name = "ADDRESS", nullable = false)
    protected String address;

    @NotNull
    @Column(name = "CITY", nullable = false)
    protected String city;

    @Email
    @Column(name = "EMAIL")
    protected String email;

    @Column(name = "TELEPHONE")
    protected String telephone;

    @OneToMany(mappedBy = "owner")
    protected List<Pet> pets;

    @MetaProperty(datatype = "GeoPoint")
    @Column(name = "LOCATION")
    @Geometry
    @Convert(converter = CubaPointWKTConverter.class)
    protected Point location;

    @OnDeleteInverse(DeletePolicy.UNLINK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DISTRICT_ID")
    protected District district;

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }


    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public List<Pet> getPets() {
        return pets;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }


}