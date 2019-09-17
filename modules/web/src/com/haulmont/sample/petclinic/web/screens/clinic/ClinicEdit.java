package com.haulmont.sample.petclinic.web.screens.clinic;

import com.haulmont.cuba.gui.screen.*;
import com.haulmont.sample.petclinic.entity.clinic.Clinic;

@UiController("petclinic_Clinic.edit")
@UiDescriptor("clinic-edit.xml")
@EditedEntityContainer("clinicDc")
@LoadDataBeforeShow
public class ClinicEdit extends StandardEditor<Clinic> {
}