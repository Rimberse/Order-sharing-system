package fr.efrei.ordersharingsystem.repositories;

import fr.efrei.ordersharingsystem.domain.Alley;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlleyRepository extends JpaRepository<Alley, Long> {
    List<Alley> findAll();
    Alley getAlleyByPark_IdAndNumber (Long parkId, Integer number);
    Alley save(Alley alley);
    void delete(Alley alley);
}
