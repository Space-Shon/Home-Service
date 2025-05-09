package ru.headsandhands.homeservice.Model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NamedEntityGraph(
        name = "home-entity-graph-with-rooms",
        attributeNodes = {
                @NamedAttributeNode("rooms")
        }
)
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "homes")
public class HomeEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @OneToMany(
            mappedBy = "home",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            targetEntity = Room.class
    )
    private List<Room> rooms = new ArrayList<>();

    @Column(name = "ownerId")
    private String ownerId;
}
