package com.jarida.server.jaridaserver.quranlyfeAPI.model;


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
@Table(name = "namesOfAllah")
@AllArgsConstructor
@Builder
public class NamesOfAllah implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    Long id;

    @Column(name = "position")
    Long position;

    @Column(name = "arabic_name")
    String arabicName;

    @Column(name = "transliteration_name")
    String transliterationName;

    @Column(name = "meaning")
    String meaning;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        NamesOfAllah namesofAllah = (NamesOfAllah) o;
        return id != null && Objects.equals(id, namesofAllah.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
