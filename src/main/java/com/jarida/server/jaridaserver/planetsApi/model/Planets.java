package com.jarida.server.jaridaserver.planetsApi.model;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "planets")
@AllArgsConstructor
@Builder
public class Planets implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    Long id;

    @Column(name = "planet_name")
    String planetName;

    @Column(name = "planet_image")
    String planetImage;

    @Column(name = "surface_temperature")
    String surfaceTemperature;

    @Column(name = "discovery")
    String discovery;

    @Column(name = "origin_of_name")
    String originOfName;

    @Column(name = "diameter")
    String diameter;

    @Column(name = "orbit")
    String orbit;

    @Column(name = "days")
    String days;

    @Column(name = "moon")
    int moon;

    @Column(name = "planet_summary")
    String planetSummary;

    @Column(name = "distance_from_sun")
    String distanceFromSun;

    @Column(name = "position")
    int position;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Planets planets = (Planets) o;
        return id != null && Objects.equals(id, planets.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
