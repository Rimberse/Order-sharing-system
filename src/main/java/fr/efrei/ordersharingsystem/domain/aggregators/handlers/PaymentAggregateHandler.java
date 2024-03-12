package fr.efrei.ordersharingsystem.domain.aggregators.handlers;

import fr.efrei.ordersharingsystem.domain.aggregators.PaymentAggregateService;
import fr.efrei.ordersharingsystem.domain.commands.payments.CreatePaymentCommand;
import fr.efrei.ordersharingsystem.entity.Payment;
import fr.efrei.ordersharingsystem.entity.Status;
import fr.efrei.ordersharingsystem.exceptions.ItemNotFoundException;
import fr.efrei.ordersharingsystem.infrastructure.services.NotificationService;
import fr.efrei.ordersharingsystem.infrastructure.services.PaymentService;
import fr.efrei.ordersharingsystem.infrastructure.repositories.OrderRepository;
import fr.efrei.ordersharingsystem.infrastructure.repositories.PaymentRepository;
import fr.efrei.ordersharingsystem.infrastructure.repositories.UserRepository;
import fr.efrei.ordersharingsystem.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentAggregateHandler implements PaymentAggregateService {
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final PaymentService paymentService;
    private final NotificationService notificationService;

    @Autowired
    public PaymentAggregateHandler(
            PaymentRepository paymentRepository,
            UserRepository userRepository,
            OrderRepository orderRepository,
            PaymentService paymentService,
            NotificationService notificationService
    ) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.paymentService = paymentService;
        this.notificationService = notificationService;
    }
    public void handle(CreatePaymentCommand command) {
        var order = orderRepository.findById(command.orderId())
                .orElseThrow(() -> new ItemNotFoundException("Order", command.orderId()));

        var user = userRepository.findById(command.userId())
                .orElseThrow(() -> new ItemNotFoundException("User", command.userId()));

        // Calculates total due amount for a given user order
        var totalDueAmount = Utils.calculateTotalDueAmount(order);
        var totalPaymentAmount = Utils.calculateTotalPaymentAmount(order);

        var payment = new Payment();
        payment.setUserId(user.getId());
        payment.setOrderId(order.getId());
        var amount = command.amount();
        if (amount + totalPaymentAmount > totalDueAmount) {
            amount = totalDueAmount - totalPaymentAmount;
        }
        payment.setAmount(amount);
        if (command.paymentAccount().equals("cash")) {
            payment.setStatus(Status.COMPLETED);
            System.out.println("Payment with cash: " + amount + " for order " + order.getId() + " has been made.");
        } else if (paymentService.pay(command.paymentAccount(), amount)) {
            payment.setStatus(Status.COMPLETED);
        } else {
            payment.setStatus(Status.FAILED);
        }
        paymentRepository.save(payment);
        if (payment.getStatus() == Status.FAILED) {
            var users = List.of(user);
            var title = "Payment for order " + order.getId() + " has failed";
            var message = "Please try again";
            notificationService.sendNotification(users, title, message);
            return;
        }

        var users = userRepository.findAllUsersInOrderId(order.getId());
        var newOrder = orderRepository.findById(command.orderId())
                .orElseThrow(() -> new ItemNotFoundException("Order", command.orderId()));
        var remainingAmountToBePaid = totalDueAmount - totalPaymentAmount - amount;
        var title = "A payment for order " + newOrder.getId() + " has been made";
        var message = "The remaining amount to be paid is " + remainingAmountToBePaid;
        if (remainingAmountToBePaid == 0) {
            newOrder.setStatus(Status.COMPLETED);
            orderRepository.save(newOrder);
            title = "Order " + newOrder.getId() + " is Complete";
            message = "All payments for order " + newOrder.getId() + " is completed";
        }
        notificationService.sendNotification(users, title, message);
    }
}
