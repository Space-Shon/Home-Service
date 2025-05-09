package ru.headsandhands.homeservice.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "home_id", insertable = false, updatable = false)
    private long homeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_id", referencedColumnName = "id")
    @JsonBackReference
    private HomeEntity home;
}
