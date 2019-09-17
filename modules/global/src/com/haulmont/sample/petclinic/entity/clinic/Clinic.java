package com.haulmont.sample.petclinic.entity.clinic;

import com.haulmont.addon.maps.gis.Geometry;
import com.haulmont.addon.maps.gis.converters.wkt.CubaPointWKTConverter;
import com.haulmont.addon.maps.gis.converters.wkt.CubaPolygonWKTConverter;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import org.locationtech.jts.geom.Point;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

@NamePattern("%s|name")
@Table(name = "PETCLINIC_CLINIC")
@Entity(name = "petclinic_Clinic")
public class Clinic extends StandardEntity {
    private static final long serialVersionUID = 5096607906996310699L;

    @Column(name = "NAME")
    protected String name;

    @MetaProperty(datatype = "GeoPoint")
    @Column(name = "LOCATION")
    @Geometry
    @Convert(converter = CubaPointWKTConverter.class)
    protected Point location;

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}