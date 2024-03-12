package fr.efrei.ordersharingsystem.aggregate.handlers;

import fr.efrei.ordersharingsystem.aggregate.SessionAggregateService;
import fr.efrei.ordersharingsystem.commands.sessions.DeleteSessionCommand;
import fr.efrei.ordersharingsystem.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SessionAggregateHandler implements SessionAggregateService {
    private final SessionRepository sessionRepository;

    @Autowired
    public SessionAggregateHandler(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void handle(DeleteSessionCommand command) {
        var session = sessionRepository.findById(command.id()).orElse(null);
        if (session == null) {
            return;
        }
        var sessionNotInAlley =
                !Objects.equals(session.getParkId(), command.parkId()) ||
                !Objects.equals(session.getAlleyNumber(), command.alleyNumber());
        if (sessionNotInAlley) {
            return;
        }
        sessionRepository.deleteById(command.id());
    }
}
