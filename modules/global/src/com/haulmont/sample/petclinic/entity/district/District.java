package com.haulmont.sample.petclinic.entity.district;

import com.haulmont.addon.maps.gis.Geometry;
import com.haulmont.addon.maps.gis.converters.wkt.CubaPolygonWKTConverter;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import org.locationtech.jts.geom.Polygon;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

@NamePattern("%s|name")
@Table(name = "PETCLINIC_DISTRICT")
@Entity(name = "petclinic_District")
public class District extends StandardEntity {
    private static final long serialVersionUID = -2147909350838986117L;

    @Column(name = "NAME")
    protected String name;

    @MetaProperty(datatype = "GeoPolygon")
    @Column(name = "POLYGON")
    @Geometry
    @Convert(converter = CubaPolygonWKTConverter.class)
    protected Polygon polygon;

    public Polygon getPolygon() {
        return polygon;
    }

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}