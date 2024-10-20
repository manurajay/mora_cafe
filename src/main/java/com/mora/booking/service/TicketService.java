package com.mora.booking.service;

import com.mora.booking.utils.Consumer;
import com.mora.booking.utils.Organizer;
import com.mora.booking.utils.TicketPool;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class TicketService {

    private final TicketPool ticketPool;
    private final ExecutorService consumerThreadPool;

    public TicketService() {
        int maxTicketCapacity = 100;
        this.ticketPool = new TicketPool(maxTicketCapacity);
        this.consumerThreadPool = Executors.newFixedThreadPool(10);
    }

    // release tickets by event organizers
    public void startEventOrganizer(int totalTickets, int ticketReleaseRate) {
        Organizer organizer = new Organizer(ticketPool, ticketReleaseRate, totalTickets);
        new Thread(organizer).start();
    }

    // purchase tickets by consumers
    public void startConsumer(int consumerRetrievalRate) {
        Consumer consumer = new Consumer(ticketPool, consumerRetrievalRate);
        consumerThreadPool.submit(consumer);
    }

    // get available tickets
    public int getAvailableTickets() {
        return ticketPool.getAvailableTickets();
    }

    // shutdown the thread pool
    public void shutDown() {
        consumerThreadPool.shutdown();
        try {
            if (!consumerThreadPool.awaitTermination(5, TimeUnit.SECONDS)) {
                consumerThreadPool.shutdownNow();
            }
        } catch (InterruptedException ex) {
            consumerThreadPool.shutdownNow();
        }
    }
}
