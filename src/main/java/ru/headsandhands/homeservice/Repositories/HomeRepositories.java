package ru.headsandhands.homeservice.Repositories;

import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.headsandhands.homeservice.Model.Home;

import java.util.Optional;

@Repository
public interface  HomeRepositories extends JpaRepository<Home, Integer> {

    @EntityGraph(value = "home-entity-graph-with-rooms", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Home> findById(@NonNull Long aLong);
    @EntityGraph(value = "home-entity-graph-with-rooms", type = EntityGraph.EntityGraphType.LOAD)
    <S extends Home> S save(@NonNull S entity);
}
