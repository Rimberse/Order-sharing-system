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
import java.util.Objects;

@Service
public class PaymentAggregateHandler implements PaymentAggregateService {
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final SessionRepository sessionRepository;

    @Autowired
    public PaymentAggregateHandler(
            PaymentRepository paymentRepository,
            UserRepository userRepository,
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            ProductRepository productRepository,
            SessionRepository sessionRepository
    ) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.sessionRepository = sessionRepository;
    }

    public Payment handle(CreatePaymentCommand command) {
        var session = sessionRepository.findById(command.sessionId())
                .orElseThrow(() -> new ItemNotFoundException("Order", command.sessionId()));

        User user = userRepository.findById(command.userId())
                .orElseThrow(() -> new ItemNotFoundException("User", command.userId()));

        // Finds all order items associated with a given user order
        List<OrderItem> orderItems = orderItemRepository.findAll()
                .stream()
                .filter(orderItem -> Objects.equals(orderItem.getOrderId(), command.sessionId()))
                .toList();

        // Calculates total due amount for a given user order
        int total = orderItems
                .stream()
                .map(
                        orderItem -> {
                            Product product = productRepository.findById(orderItem.getProduct().getId())
                                    .orElseThrow(() -> new ItemNotFoundException("Product", orderItem.getProduct().getId()));
                            return orderItem.getQuantity() * product.getPrice();
                        }
                ).mapToInt(Integer::intValue).sum();

        Payment payment = new Payment();
        payment.setStatus(Status.IN_PROGRESS);

        if (total < command.amount()) {
            payment.setStatus(Status.CANCELLED);
            throw new InvalidPaymentException("Payment", command.sessionId());
        }

        payment.setUserId(user.getId());
        payment.setSession(session);
        payment.setAmount(command.amount());
        payment.setStatus(Status.COMPLETED);
        return paymentRepository.save(payment);
    }
}
