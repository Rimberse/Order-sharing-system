package fr.efrei.ordersharingsystem.aggregate;

import fr.efrei.ordersharingsystem.commands.sessions.DeleteSessionCommand;

public interface SessionAggregateService {
    void handle(DeleteSessionCommand command);
}
