package com.haulmont.sample.petclinic.web.owner.owner;

import com.haulmont.addon.maps.web.gui.components.CanvasLayer;
import com.haulmont.addon.maps.web.gui.components.GeoMap;
import com.haulmont.addon.maps.web.gui.components.layer.VectorLayer;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.queryconditions.JpqlCondition;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.sample.petclinic.entity.district.District;
import com.haulmont.sample.petclinic.entity.owner.Owner;

import javax.inject.Inject;
import java.util.List;

@UiController("petclinic_Owner.browse")
@UiDescriptor("owner-browse.xml")
@LookupComponent("ownersTable")
@LoadDataBeforeShow
public class OwnerBrowse extends StandardLookup<Owner> {

    @Inject
    private CollectionLoader<Owner> ownersDl;
    @Inject
    private DataManager dataManager;
    @Inject
    private CollectionContainer<Owner> ownersDc;
    @Inject
    private GeoMap map;

    @Inject
    private GroupTable<Owner> ownersTable;

    private CanvasLayer.Polygon districtPolygon;

    @Subscribe("districtField")
    private void onDistrictFieldValueChange(HasValue.ValueChangeEvent<District> event) {
        District district = event.getValue();

        CanvasLayer canvas = map.getCanvas();
        if (districtPolygon != null) {
            canvas.removePolygon(districtPolygon);
        }
        if (district != null && district.getPolygon() != null) {
            districtPolygon = canvas.addPolygon(district.getPolygon());
        }

        LoadContext<Owner> loadContext = ownersDl.createLoadContext();
        loadContext.getQuery()
                .setCondition(JpqlCondition.where("e.district = :district"))
                .setParameter("district", district);
        List<Owner> owners = dataManager.loadList(loadContext);
        ownersDc.setItems(owners);
    }

    @Subscribe("map.owners")
    private void onOwnerSelected(VectorLayer.GeoObjectSelectedEvent<Owner> event) {
        ownersTable.setSelected(event.getItem());
    }

}