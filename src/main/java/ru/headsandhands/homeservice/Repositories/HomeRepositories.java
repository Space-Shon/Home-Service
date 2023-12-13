package ru.headsandhands.homeservice.Repositories;

import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.headsandhands.homeservice.Model.Home;

import java.util.List;
import java.util.Optional;

@Repository
public interface  HomeRepositories extends JpaRepository<Home, Integer> {

    @EntityGraph(value = "home-entity-graph-with-rooms", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Home> findById(@NonNull Long aLong);

    @Query("SELECT h FROM Home h WHERE h.ownerId = :ownerId")
    List<Home> findByAllHouse(@Param("ownerId") String ownerId);

    @Query("SELECT h from Home h where (h.id = :id AND h.ownerId = :ownerId)")
    Optional<Home> findByHouseId(@Param("id") Integer id, @Param("ownerId") String ownerId);

}
