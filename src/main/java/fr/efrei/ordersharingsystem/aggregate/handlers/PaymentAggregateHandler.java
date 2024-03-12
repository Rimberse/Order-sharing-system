package fr.efrei.ordersharingsystem.aggregate.handlers;

import fr.efrei.ordersharingsystem.aggregate.PaymentAggregateService;
import fr.efrei.ordersharingsystem.commands.payments.CreatePaymentCommand;
import fr.efrei.ordersharingsystem.domain.*;
import fr.efrei.ordersharingsystem.exceptions.ItemNotFoundException;
import fr.efrei.ordersharingsystem.infrastructure.interfaces.PaymentService;
import fr.efrei.ordersharingsystem.repositories.*;
import fr.efrei.ordersharingsystem.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class PaymentAggregateHandler implements PaymentAggregateService {
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final PaymentService paymentService;

    @Autowired
    public PaymentAggregateHandler(
            PaymentRepository paymentRepository,
            UserRepository userRepository,
            OrderRepository orderRepository,
            PaymentService paymentService
    ) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.paymentService = paymentService;
    }
    public void handle(CreatePaymentCommand command) {
        var order = orderRepository.findById(command.orderId())
                .orElseThrow(() -> new ItemNotFoundException("Order", command.orderId()));

        var user = userRepository.findById(command.userId())
                .orElseThrow(() -> new ItemNotFoundException("User", command.userId()));

        // Calculates total due amount for a given user order
        var totalDueAmount = Utils.calculateTotalDueAmount(orderRepository.findById(command.orderId())
                .orElseThrow(() -> new ItemNotFoundException("Order", command.orderId())));
        var totalPaymentAmount = Utils.calculateTotalPaymentAmount(orderRepository.findById(command.orderId())
                .orElseThrow(() -> new ItemNotFoundException("Order", command.orderId())));


        var payment = new Payment();
        payment.setUserId(user.getId());
        payment.setOrderId(order.getId());
        var amount = command.amount();
        if (amount + totalPaymentAmount > totalDueAmount) {
            amount = totalDueAmount - totalPaymentAmount;
        }
        payment.setAmount(amount);
        if (paymentService.pay(command.paymentAccount(), amount)) {
            payment.setStatus(Status.COMPLETED);
        } else {
            payment.setStatus(Status.FAILED);
        }
        paymentRepository.save(payment);
    }
}
