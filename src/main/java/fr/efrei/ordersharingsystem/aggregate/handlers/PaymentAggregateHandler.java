package fr.efrei.ordersharingsystem.aggregate.handlers;

import fr.efrei.ordersharingsystem.aggregate.PaymentAggregateService;
import fr.efrei.ordersharingsystem.commands.payments.CreatePaymentCommand;
import fr.efrei.ordersharingsystem.domain.*;
import fr.efrei.ordersharingsystem.exceptions.InvalidPaymentException;
import fr.efrei.ordersharingsystem.exceptions.ItemNotFoundException;
import fr.efrei.ordersharingsystem.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentAggregateHandler implements PaymentAggregateService {
    private PaymentRepository paymentRepository;
    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private ProductRepository productRepository;

    @Autowired
    public PaymentAggregateHandler(PaymentRepository paymentRepository, UserRepository userRepository,
                                   OrderRepository orderRepository, OrderItemRepository orderItemRepository, ProductRepository productRepository) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }

    public Payment handle(CreatePaymentCommand command) {
        Order order = orderRepository.findById(command.orderId())
                .orElseThrow(() -> new ItemNotFoundException("Order", command.orderId()));

        User user = userRepository.findById(command.userId())
                .orElseThrow(() -> new ItemNotFoundException("User", command.userId()));

        // Finds all order items associated with a given user order
        List<OrderItem> orderItems = orderItemRepository.findAll()
                .stream()
                .filter(orderItem -> orderItem.getOrder().getId() == command.orderId())
                .collect(Collectors.toList());

        // Calculates total due amount for a given user order
        int total = orderItems
                .stream()
                .map(
                        orderItem -> {
                            Product product = productRepository.findById(orderItem.getProduct().getId())
                                    .orElseThrow(() -> new ItemNotFoundException("Product", orderItem.getProduct().getId()));
                            return orderItem.getQuantity() * product.getPrice();
                        }
                )
                .collect(Collectors.summingInt(Integer::intValue));

        Payment payment = new Payment();
        payment.setStatus(Status.IN_PROGRESS);

        if (total < command.amount()) {
            payment.setStatus(Status.CANCELLED);
            throw new InvalidPaymentException("Payment", command.orderId());
        }

        payment.setUser(user);
        payment.setOrder(order);
        payment.setAmount(command.amount());
        payment.setStatus(Status.COMPLETED);
        return paymentRepository.save(payment);
    }
}
