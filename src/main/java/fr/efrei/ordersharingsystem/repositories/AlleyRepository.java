package fr.efrei.ordersharingsystem.repositories;

import fr.efrei.ordersharingsystem.domain.Alley;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlleyRepository extends JpaRepository<Alley, Long> {
    List<Alley> findAll();
    List<Alley> findAllByNumber(int number);
    List<Alley> findAlleyByParkId(Long parkId);
    Optional<Alley> findByNumberAndParkId(int number, Long parkId);
    List<Alley> save(Iterable<Alley> alleys);
    Alley save(Alley alley);
    void delete(Alley alley);
    boolean existsByNumber(int number);
    boolean existsByParkId(Long parkId);
}
