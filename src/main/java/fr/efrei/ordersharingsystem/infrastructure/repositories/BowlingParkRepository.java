package fr.efrei.ordersharingsystem.infrastructure.repositories;

import fr.efrei.ordersharingsystem.entity.BowlingPark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BowlingParkRepository extends JpaRepository<BowlingPark, Long> {
    List<BowlingPark> findAll();
    BowlingPark save(BowlingPark park);
    void delete(BowlingPark park);

}
