package fr.efrei.ordersharingsystem.repositories;

import fr.efrei.ordersharingsystem.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAll();
    List<Product> findAllByPark_Id(Long parkId);
    Product save(Product product);
    void delete(Product product);
}
