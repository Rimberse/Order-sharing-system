package fr.efrei.ordersharingsystem.projections;

import fr.efrei.ordersharingsystem.domain.Session;
import fr.efrei.ordersharingsystem.queries.sessions.GetSessionByAlleyQuery;

import java.util.List;

public interface SessionProjectionService {
    Session handle(GetSessionByAlleyQuery query);
}
