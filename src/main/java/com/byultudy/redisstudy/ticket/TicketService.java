package com.byultudy.redisstudy.ticket;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketDto issueTicket(final TicketPurchaseRequestDto ticketPurchaseRequestDto, final LocalDateTime now) {

        Ticket saved = ticketRepository.save(Ticket.issue(ticketPurchaseRequestDto, now));
        return TicketDto.from(saved);
    }
}
