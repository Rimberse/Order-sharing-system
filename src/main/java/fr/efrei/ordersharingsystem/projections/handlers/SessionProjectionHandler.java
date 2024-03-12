package fr.efrei.ordersharingsystem.projections.handlers;

import fr.efrei.ordersharingsystem.domain.Session;
import fr.efrei.ordersharingsystem.projections.SessionProjectionService;
import fr.efrei.ordersharingsystem.queries.sessions.GetSessionByAlleyQuery;
import fr.efrei.ordersharingsystem.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionProjectionHandler implements SessionProjectionService {
    private final SessionRepository sessionRepository;

    @Autowired
    public SessionProjectionHandler(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public Session handle(GetSessionByAlleyQuery query) {
        var sessions = sessionRepository.findAllByParkIdAndAlleyNumberAndStatus(
                query.parkId(),
                query.alleyNumber(),
                query.status());
        var session = sessions.stream().findFirst().orElse(null);
        var sessionNotFound = session == null;
        if (sessionNotFound) {
            throw new IllegalArgumentException("Session not found: " + query.parkId() + " and " + query.alleyNumber());
        }
        return session;
    }
}
