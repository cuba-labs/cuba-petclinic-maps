package com.haulmont.sample.petclinic.web.screens.clinic;

import com.haulmont.cuba.gui.screen.*;
import com.haulmont.sample.petclinic.entity.clinic.Clinic;

@UiController("petclinic_Clinic.browse")
@UiDescriptor("clinic-browse.xml")
@LookupComponent("clinicsTable")
@LoadDataBeforeShow
public class ClinicBrowse extends StandardLookup<Clinic> {
}