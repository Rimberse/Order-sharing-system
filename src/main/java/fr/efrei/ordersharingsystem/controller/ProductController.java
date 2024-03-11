package fr.efrei.ordersharingsystem.controller;

import fr.efrei.ordersharingsystem.aggregate.ProductAggregateService;
import fr.efrei.ordersharingsystem.commands.products.CreateProductCommand;
import fr.efrei.ordersharingsystem.commands.products.DeleteProductCommand;
import fr.efrei.ordersharingsystem.commands.products.ModifyProductCommand;
import fr.efrei.ordersharingsystem.domain.Product;
import fr.efrei.ordersharingsystem.projections.ProductProjectionService;
import fr.efrei.ordersharingsystem.queries.products.GetCatalogByParkIdQuery;
import fr.efrei.ordersharingsystem.queries.products.GetProductByIdQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/parks/{parkId}/products")
public class ProductController {
    private final ProductProjectionService productProjectionService;
    private final ProductAggregateService productAggregateService;

    @Autowired
    public ProductController(ProductProjectionService productProjectionService, ProductAggregateService productAggregateService) {
        this.productProjectionService = productProjectionService;
        this.productAggregateService = productAggregateService;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getProducts(@PathVariable Long parkId) {
        GetCatalogByParkIdQuery query = new GetCatalogByParkIdQuery(parkId);
        return ResponseEntity.ok(productProjectionService.handle(query));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id, @PathVariable Long parkId) {
        GetProductByIdQuery query = new GetProductByIdQuery(parkId, id);
        return ResponseEntity.ok(productProjectionService.handle(query));
    }

    @PostMapping()
    public ResponseEntity<String> createProduct(@PathVariable Long parkId, @RequestBody CreateProductCommand command) {
        CreateProductCommand newCommand = new CreateProductCommand(command.name(), parkId, command.description(), command.price());
        var resultId = productAggregateService.handle(newCommand);
        URI location = URI.create("/api/v1/products/" + resultId);
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modifyProduct(@PathVariable Long parkId, @PathVariable Long id, @RequestBody ModifyProductCommand command) {
        ModifyProductCommand newCommand = new ModifyProductCommand(id, parkId, command.name(), command.description(), command.price());
        productAggregateService.handle(newCommand);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long parkId, @PathVariable Long id) {
        DeleteProductCommand command = new DeleteProductCommand(id);
        productAggregateService.handle(command);
        return ResponseEntity.noContent().build();
    }
}
