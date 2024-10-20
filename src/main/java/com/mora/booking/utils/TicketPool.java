package com.mora.booking.utils;

import com.mora.booking.pojo.Ticket;

import java.util.*;

public class TicketPool{

    private int availableTickets;
    private final int maxTicketCapacity;
    private final List<Ticket> tickets = Collections.synchronizedList(new ArrayList<>());

    public TicketPool(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    // add a ticket to the pool
    public synchronized void addTickets(int numberOfTickets) {
        if (tickets.size() + numberOfTickets <= maxTicketCapacity) {
            for (int i = 0; i < numberOfTickets; i++) {
                tickets.add(new Ticket());  // Add a new ticket
            }
            System.out.println("Added " + numberOfTickets + " tickets. Current pool size: " + tickets.size());
        } else {
            System.out.println("Ticket pool at maximum capacity.");
        }
    }

    // remove ticket from the pool
    public synchronized void removeTickets(int numberOfTickets) {
        if (tickets.size() >= numberOfTickets) {
            for (int i = 0; i < numberOfTickets; i++) {
                tickets.remove(0);  // Remove a ticket from the pool
            }
            System.out.println("Removed " + numberOfTickets + " tickets. Remaining pool size: " + tickets.size());
        } else {
            System.out.println("Not enough tickets available for removal.");
        }
    }

    // get ticket count
    public int getAvailableTickets() {
        return tickets.size();
    }

    // get max capacity
    public int getMaxCapacity() {
        return maxTicketCapacity;
    }
}
