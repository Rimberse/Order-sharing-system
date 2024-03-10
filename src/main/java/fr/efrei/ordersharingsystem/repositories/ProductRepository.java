package fr.efrei.ordersharingsystem.repositories;

import fr.efrei.ordersharingsystem.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAll();
    List<Product> findAllByDescription(String description);
    List<Product> findAllByDescriptionIgnoreCase(String description);
    List<Product> findAllByPriceLessThan(int price);
    List<Product> findAllByPriceLessThanEqual(int price);
    List<Product> findAllByPriceGreaterThan(int price);
    List<Product> findAllByPriceGreaterThanEqual(int price);
    List<Product> findAllByPriceBetween(int start, int end);
    Optional<Product> findByName(String name);
    Optional<Product> findByNameIgnoreCase(String name);
    Optional<Product> findByDescription(String description);
    Optional<Product> findByDescriptionIgnoreCase(String description);
    Optional<Product> findByPrice(int price);
    List<Product> save(Iterable<Product> products);
    Product save(Product product);
    void delete(Product product);
    boolean existsBy(Long id);
    boolean existsByName(String name);
    boolean existsByPriceBetween(int start, int end);
}
