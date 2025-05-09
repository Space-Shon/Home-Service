package ru.headsandhands.homeservice.Repositories;

import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.headsandhands.homeservice.Model.HomeEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface HomeRepositories extends JpaRepository<HomeEntity, Integer> {

    @EntityGraph(value = "home-entity-graph-with-rooms", type = EntityGraph.EntityGraphType.LOAD)
    Optional<HomeEntity> findById(@NonNull Long aLong);

    @Query("SELECT h FROM HomeEntity h WHERE h.ownerId = :ownerId")
    List<HomeEntity> findByAllHouse(@Param("ownerId") String ownerId);

    @Query("SELECT h from HomeEntity h where (h.id = :id AND h.ownerId = :ownerId)")
    Optional<HomeEntity> findByHouseId(@Param("id") Integer id, @Param("ownerId") String ownerId);

    @Modifying
    @Transactional
    @Query("UPDATE HomeEntity SET address = :#{#home.address}, name = :#{#home.name} WHERE id = :id AND ownerId = :ownerId")
    void saveHome(@Param("home") HomeEntity home, @Param("id") Integer id, @Param("ownerId") String ownerId);

    @Modifying
    @Transactional
    @Query("DELETE FROM HomeEntity where id = :id and ownerId = :ownerId")
    void deleteHomeById(@Param("id") Integer id, @Param("ownerId") String ownerId);

    Optional<HomeEntity> findByOwnerId(String ownerId);
}
