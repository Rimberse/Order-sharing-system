package fr.efrei.ordersharingsystem.aggregate.handlers;

import fr.efrei.ordersharingsystem.aggregate.PaymentAggregateService;
import fr.efrei.ordersharingsystem.commands.payments.CreatePaymentCommand;
import fr.efrei.ordersharingsystem.domain.Order;
import fr.efrei.ordersharingsystem.domain.Payment;
import fr.efrei.ordersharingsystem.domain.Status;
import fr.efrei.ordersharingsystem.domain.User;
import fr.efrei.ordersharingsystem.exceptions.ItemNotFoundException;
import fr.efrei.ordersharingsystem.repositories.OrderRepository;
import fr.efrei.ordersharingsystem.repositories.PaymentRepository;
import fr.efrei.ordersharingsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentAggregateHandler implements PaymentAggregateService {
    private PaymentRepository paymentRepository;
    private UserRepository userRepository;
    private OrderRepository orderRepository;

    @Autowired
    public PaymentAggregateHandler(PaymentRepository paymentRepository, UserRepository userRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    public Payment handle(CreatePaymentCommand command) {
        Order order = orderRepository.findById(command.orderId())
                .orElseThrow(() -> new ItemNotFoundException("Order", command.orderId()));

        User user = userRepository.findById(command.userId())
                .orElseThrow(() -> new ItemNotFoundException("User", command.userId()));

//        if (order.g)
//
//        Payment existingPayment = paymentRepository.findAll()
//                .stream()
//                .filter(
//                        payment -> payment.getOrder() == orderRepository.findById(command.orderId())
//                        .orElseThrow(() -> new ItemNotFoundException("Order", command.orderId()))
//                )
//                .findAny()
//                .orElse(null);

        Payment payment = new Payment();
        payment.setUser(user);
        payment.setOrder(order);
        payment.setAmount(command.amount());
        payment.setStatus(Status.COMPLETED);
        return paymentRepository.save(payment);
    }
}
