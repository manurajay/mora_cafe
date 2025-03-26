package com.mora.booking.utils;

public class Organizer implements Runnable{

    private TicketPool ticketPool;
    private int totalTickets;
    private int ticketReleaseRate;

    public Organizer(TicketPool ticketPool, int totalTickets, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        try {
            int releasedTickets = 0;
            while (releasedTickets < totalTickets) {
                // Release tickets in batches based on the ticketReleaseRate
                ticketPool.addTickets(ticketReleaseRate);
                releasedTickets += ticketReleaseRate;
                Thread.sleep(1000);  // Simulate the time delay between ticket releases
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
