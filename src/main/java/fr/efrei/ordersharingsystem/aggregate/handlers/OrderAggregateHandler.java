package fr.efrei.ordersharingsystem.aggregate.handlers;

import fr.efrei.ordersharingsystem.aggregate.OrderAggregateService;
import fr.efrei.ordersharingsystem.commands.orders.*;
import fr.efrei.ordersharingsystem.domain.Order;
import fr.efrei.ordersharingsystem.domain.OrderItem;
import fr.efrei.ordersharingsystem.domain.Session;
import fr.efrei.ordersharingsystem.domain.Status;
import fr.efrei.ordersharingsystem.exceptions.ItemNotFoundException;
import fr.efrei.ordersharingsystem.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class OrderAggregateHandler implements OrderAggregateService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final AlleyRepository alleyRepository;
    private final SessionRepository sessionRepository;

    @Autowired
    public OrderAggregateHandler(
            OrderRepository orderRepository,
            UserRepository userRepository,
            AlleyRepository alleyRepository,
            OrderItemRepository orderItemRepository,
            ProductRepository productRepository,
            SessionRepository sessionRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.alleyRepository = alleyRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.sessionRepository = sessionRepository;
    }

    private Session createNewSession(AddOrderCommand command) {
        var alley = alleyRepository.getAlleyByPark_IdAndNumber(command.parkId(), command.alleyNumber());
        var alleyNotFound = alley == null;
        if (alleyNotFound) {
            throw new ItemNotFoundException("Park and Alley", command.parkId() + " and " + command.alleyNumber());
        }
        var session = new Session();
        session.setParkId(command.parkId());
        session.setAlleyNumber(command.alleyNumber());
        session.setStatus(Status.PENDING);
        session = sessionRepository.save(session);
        return session;
    }

    private Order createNewOrder(AddOrderCommand command) {
        var order = new Order();
        var client = userRepository.findById(command.userId()).orElse(null);
        var clientNotFound = client == null;
        if (clientNotFound) {
            throw new ItemNotFoundException("User", command.userId());
        }
        order.setUserId(client.getId());
        order = orderRepository.save(order);
        return order;
    }

    public long handle(AddOrderCommand command) {
        var session = sessionRepository.findAllByParkIdAndAlleyNumberAndStatus(
                command.parkId(),
                command.alleyNumber(),
                Status.PENDING)
                .stream().findFirst().orElse(null);
        var sessionNotFound = session == null;
        if (sessionNotFound) {
            session = createNewSession(command);
        }
        var order = session.getOrders().stream()
                .filter(o -> Objects.equals(o.getUserId(), command.userId()))
                .findFirst().orElse(null);
        var orderNotFound = order == null;
        if (orderNotFound) {
            order = createNewOrder(command);
        }
        var orderItem = orderItemRepository.findAllByOrderIdAndProduct_Id(order.getId(), command.productId()).stream().findFirst().orElse(null);
        var orderItemExists = orderItem != null;
        if (orderItemExists) {
            throw new IllegalArgumentException("Product already exists in the order. Order: " + order + ". Command: " + command + ".");
        }
        var product = productRepository.findById(command.productId()).orElse(null);
        var productNotFound = product == null;
        if (productNotFound) {
            throw new ItemNotFoundException("Product", command.productId());
        }
        var productNotInPark = !Objects.equals(product.getParkId(), command.parkId());
        if (productNotInPark) {
            throw new IllegalArgumentException("Product does not belong to the park. Order: " + order + ". Command: " + command + ".");
        }
        orderItem = new OrderItem();
        orderItem.setOrderId(order.getId());
        orderItem.setProduct(product);
        orderItem.setQuantity(command.quantity());
        orderItemRepository.save(orderItem);
        return order.getId();
    }

    public void handle(ModifyOrderItemCommand command) {
        var order = orderRepository.findById(command.orderId()).orElse(null);
        var orderNotFound = order == null;
        if (orderNotFound) {
            throw new ItemNotFoundException("Order", command.orderId());
        }
        var orderNotBelongsToUser = !Objects.equals(order.getUserId(), command.userId());
        if (orderNotBelongsToUser) {
            throw new IllegalArgumentException("Wrong user. Order: " + order + ". Command: " + command + ".");
        }
        var session = sessionRepository.findById(order.getSessionId()).orElse(null);
        assert session != null;
        var sessionNotBelongsToAlley =
                !Objects.equals(session.getParkId(), command.parkId()) ||
                !Objects.equals(session.getAlleyNumber(), command.alleyNumber());
        if (sessionNotBelongsToAlley) {
            throw new IllegalArgumentException("Wrong alley number. Order: " + order + ". Command: " + command + ".");
        }
        var sessionNotPending = session.getStatus() != Status.PENDING;
        if (sessionNotPending) {
            throw new IllegalArgumentException("Order is not modifiable. Order: " + order + ". Command: " + command + ".");
        }
        var orderItem = orderItemRepository.findById(command.id()).orElse(null);
        var orderItemNotFound = orderItem == null;
        if (orderItemNotFound) {
            throw new ItemNotFoundException("OrderItem", command.id());
        }
        var orderItemNotBelongsToOrder = !Objects.equals(orderItem.getOrderId(), command.orderId());
        if (orderItemNotBelongsToOrder) {
            throw new IllegalArgumentException("Order item does not belong to the order. OrderItem: " + orderItem + ". Command: " + command + ".");
        }
        var orderItemNotBelongsToProduct = !Objects.equals(orderItem.getProduct().getId(), command.productId());
        if (!orderItemNotBelongsToProduct) {
            throw new IllegalArgumentException("Product is not modifiable. OrderItem: " + orderItem + ". Command: " + command + ".");
        }
        orderItem.setQuantity(command.quantity());
        orderItemRepository.save(orderItem);
    }

    public void handle(DeleteOrderItemCommand command) {
        orderItemRepository.deleteById(command.id());
    }
}
