package ru.headsandhands.homeservice.Model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@MappedSuperclass
@RequiredArgsConstructor
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id = -1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 17 + Objects.hashCode(id) * 31;
    }

    @Override
    public String toString() {
        return getClass().getCanonicalName() + id;
    }
}
