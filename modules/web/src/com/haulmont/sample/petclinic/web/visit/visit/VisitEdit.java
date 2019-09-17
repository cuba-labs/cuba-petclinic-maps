package com.haulmont.sample.petclinic.web.visit.visit;

import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.sample.petclinic.entity.clinic.Clinic;
import com.haulmont.sample.petclinic.entity.owner.Owner;
import com.haulmont.sample.petclinic.entity.pet.Pet;
import com.haulmont.sample.petclinic.entity.visit.Visit;
import org.locationtech.jts.operation.distance.DistanceOp;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.Optional;

@UiController("petclinic_Visit.edit")
@UiDescriptor("visit-edit.xml")
@EditedEntityContainer("visitDc")
@LoadDataBeforeShow
public class VisitEdit extends StandardEditor<Visit> {
    @Inject
    private CollectionContainer<Clinic> clinicsDc;

    @Inject
    private PickerField<Clinic> clinicField;

    @Subscribe(id = "petsDc", target = Target.DATA_CONTAINER)
    private void onPetsDcItemChange(InstanceContainer.ItemChangeEvent<Pet> event) {
        Pet pet = event.getItem();
        clinicField.setValue(null);
        if (pet != null) {
            Owner owner = pet.getOwner();
            if (owner != null && owner.getLocation() != null) {
                Optional<Clinic> min = clinicsDc.getItems().stream().filter(clinic -> clinic.getLocation() != null)
                        .min(Comparator.comparingDouble(
                                o -> DistanceOp.distance(o.getLocation(), owner.getLocation())));
                min.ifPresent(clinic -> clinicField.setValue(clinic));
            }
        }
    }


}