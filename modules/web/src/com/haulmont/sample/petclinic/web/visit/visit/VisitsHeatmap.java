package com.haulmont.sample.petclinic.web.visit.visit;

import com.haulmont.addon.maps.web.gui.components.GeoMap;
import com.haulmont.addon.maps.web.gui.components.HeatMapOptions;
import com.haulmont.addon.maps.web.gui.components.layer.style.FontPointIcon;
import com.haulmont.addon.maps.web.gui.components.layer.style.GeometryStyle;
import com.haulmont.addon.maps.web.gui.components.layer.style.PointStyle;
import com.haulmont.cuba.gui.icons.CubaIcon;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.sample.petclinic.entity.clinic.Clinic;
import com.haulmont.sample.petclinic.entity.owner.Owner;
import com.haulmont.sample.petclinic.entity.visit.Visit;
import org.locationtech.jts.geom.Point;

import javax.inject.Inject;
import java.util.Map;
import java.util.stream.Collectors;

@UiController("petclinic_VisitsHeatmap")
@UiDescriptor("visits-heatmap.xml")
public class VisitsHeatmap extends Screen {

    @Inject
    private GeoMap map;
    @Inject
    private CollectionContainer<Visit> visitsDc;

    @Install(to = "map.clinics", subject = "styleProvider")
    private GeometryStyle setClinicLayerStyleProvider(Clinic clinic) {
        return new PointStyle(new FontPointIcon(CubaIcon.HOSPITAL_O).setIconPathFillColor("red"));
    }

    @Subscribe
    private void onBeforeShow(BeforeShowEvent event) {
        getScreenData().loadAll();

        Map<Point, Double> pointIntensityMap = visitsDc.getItems().stream()
                .map(visit -> visit.getPet().getOwner())
                .filter(owner -> owner != null && owner.getLocation() != null)
                .collect(Collectors.toMap(Owner::getLocation, o -> 1D, Double::sum));

        HeatMapOptions options = new HeatMapOptions();
        options.setMaximumIntensity(5D);
        options.setMinOpacity(0.4D);
        map.addHeatMap(pointIntensityMap, options);
    }
}