package ru.headsandhands.homeservice.Model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NamedEntityGraph(
        name = "home-entity-graph-with-rooms",
        attributeNodes = {@NamedAttributeNode("rooms")}
        )
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "homes")
public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "home", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Room> rooms = new ArrayList<>();

}
