package fr.efrei.ordersharingsystem.repositories;

import fr.efrei.ordersharingsystem.domain.BowlingPark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BowlingParkRepository extends JpaRepository<BowlingPark, Long> {
    List<BowlingPark> findAll();
    BowlingPark save(BowlingPark park);
    void delete(BowlingPark park);

}
