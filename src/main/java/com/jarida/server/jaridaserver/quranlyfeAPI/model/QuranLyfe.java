package com.jarida.server.jaridaserver.quranlyfeAPI.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "quranlyfe")
@AllArgsConstructor
@Builder
public class QuranLyfe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    Long id;

    @Column(name = "arabic_url")
    String arabicUrl;

    @Column(name = "transliteration")
    String transliteration;

    @Column(name = "translation")
    String translation;

    @Column(name = "category")
    String category;

    @Column(name = "background_color")
    String background_color;

    @Column(name = "best_time_to_pray")
    String bestTimeToPray;

    @Column(name = "reference1")
    String reference1;

    @Column(name = "reference2")
    String reference2;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        QuranLyfe quranLyfe = (QuranLyfe) o;
        return id != null && Objects.equals(id, quranLyfe.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
