package com.haulmont.sample.petclinic.entity.visit;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.sample.petclinic.entity.clinic.Clinic;
import com.haulmont.sample.petclinic.entity.pet.Pet;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NamePattern("%s (%s)|pet,visitDate")
@Table(name = "PETCLINIC_VISIT")
@Entity(name = "petclinic_Visit")
public class Visit extends StandardEntity {
    private static final long serialVersionUID = 6351202390461847589L;

    @Temporal(TemporalType.DATE)
    @NotNull
    @Column(name = "VISIT_DATE", nullable = false)
    protected Date visitDate;

    @Column(name = "DESCRIPTION", length = 4000)
    protected String description;

    @Lookup(type = LookupType.SCREEN, actions = {"lookup", "open", "clear"})
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PET_ID")
    protected Pet pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLINIC_ID")
    protected Clinic clinic;

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Pet getPet() {
        return pet;
    }
}