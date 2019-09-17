package com.haulmont.sample.petclinic.web.screens.district;

import com.haulmont.cuba.gui.screen.*;
import com.haulmont.sample.petclinic.entity.district.District;

@UiController("petclinic_District.edit")
@UiDescriptor("district-edit.xml")
@EditedEntityContainer("districtDc")
@LoadDataBeforeShow
public class DistrictEdit extends StandardEditor<District> {
}