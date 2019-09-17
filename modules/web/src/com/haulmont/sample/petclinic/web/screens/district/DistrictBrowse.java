package com.haulmont.sample.petclinic.web.screens.district;

import com.haulmont.cuba.gui.screen.*;
import com.haulmont.sample.petclinic.entity.district.District;

@UiController("petclinic_District.browse")
@UiDescriptor("district-browse.xml")
@LookupComponent("districtsTable")
@LoadDataBeforeShow
public class DistrictBrowse extends StandardLookup<District> {
}