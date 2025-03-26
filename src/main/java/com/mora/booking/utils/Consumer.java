package com.mora.booking.utils;

public class Consumer implements Runnable{
    private final TicketPool ticketPool;
    private final int customerRetrievalRate;

    public Consumer(TicketPool ticketPool, int customerRetrievalRate) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    @Override
    public void run() {
        try {
            // Attempt to purchase tickets based on customerRetrievalRate
            ticketPool.removeTickets(customerRetrievalRate);
            Thread.sleep(1000);  // Simulate the time delay between customer ticket purchases
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
