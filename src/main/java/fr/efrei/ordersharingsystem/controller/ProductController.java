package fr.efrei.ordersharingsystem.controller;

import fr.efrei.ordersharingsystem.aggregate.ProductAggregateService;
import fr.efrei.ordersharingsystem.commands.products.CreateProductCommand;
import fr.efrei.ordersharingsystem.commands.products.DeleteProductCommand;
import fr.efrei.ordersharingsystem.commands.products.ModifyProductCommand;
import fr.efrei.ordersharingsystem.domain.Product;
import fr.efrei.ordersharingsystem.projections.ProductProjectionService;
import fr.efrei.ordersharingsystem.queries.products.GetProductByIdQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    private ProductProjectionService productProjectionService;
    @Autowired
    private ProductAggregateService productAggregateService;

    @GetMapping()
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productProjectionService.handle());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        GetProductByIdQuery query = new GetProductByIdQuery(id);
        return ResponseEntity.ok(productProjectionService.handle(query));
    }

    @PostMapping()
    public ResponseEntity<String> createProduct(@RequestBody CreateProductCommand command) {
        var resultId = productAggregateService.handle(command);
        URI location = URI.create("/api/v1/products/" + resultId);
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modifyProduct(@PathVariable long id, @RequestBody ModifyProductCommand command) {
        if (id != command.id()) {
            return ResponseEntity.badRequest().body("The id in the path and in the body must be the same");
        }
        productAggregateService.handle(command);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id) {
        DeleteProductCommand command = new DeleteProductCommand(id);
        productAggregateService.handle(command);
        return ResponseEntity.noContent().build();
    }
}
