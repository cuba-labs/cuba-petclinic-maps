package com.haulmont.sample.petclinic.web.owner.owner;

import com.haulmont.addon.maps.web.gui.components.layer.style.FontPointIcon;
import com.haulmont.addon.maps.web.gui.components.layer.style.GeometryStyle;
import com.haulmont.addon.maps.web.gui.components.layer.style.PointStyle;
import com.haulmont.addon.maps.web.gui.components.layer.style.PolygonStyle;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.icons.CubaIcon;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.sample.petclinic.entity.district.District;
import com.haulmont.sample.petclinic.entity.owner.Owner;

import javax.inject.Inject;

@UiController("petclinic_Owner.edit")
@UiDescriptor("owner-edit.xml")
@EditedEntityContainer("ownerDc")
@LoadDataBeforeShow
public class OwnerEdit extends StandardEditor<Owner> {

    @Inject
    private Notifications notifications;

    @Install(to = "map.ownerLayer", subject = "styleProvider")
    private GeometryStyle setOwnerLayerStyleProvider(Owner owner) {
        return new PointStyle(new FontPointIcon(CubaIcon.HOME));
    }

    @Install(to = "map.districtLayer", subject = "styleProvider")
    private GeometryStyle setDistrictLayerStyleProvider(District owner) {
        return new PolygonStyle()
                .setFillColor("green")
                .setStrokeColor("darkgreen")
                .setStrokeWeight(2);
    }

    @Subscribe(target = Target.DATA_CONTEXT)
    private void onPreCommit(DataContext.PreCommitEvent event) {
        District district = getEditedEntity().getDistrict();
        if (district != null
                && district.getPolygon() != null
                && getEditedEntity().getLocation() != null
                && !getEditedEntity().getLocation().within(district.getPolygon())) {
            event.preventCommit();
            notifications.create(Notifications.NotificationType.TRAY)
                    .withCaption("Location of the owner should be inside the specified district")
                    .show();
        }
    }


}