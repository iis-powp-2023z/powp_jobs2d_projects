package edu.kis.powp.jobs2d.observer;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private List<Subscriber> subscribers = new ArrayList<>();

    /**
     * Add a subscriber to the list.
     *
     * @param subscriber The subscriber to be added.
     */
    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    /**
     * Remove a subscriber from the list.
     *
     * @param subscriber The subscriber to be removed.
     */
    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    /**
     * Notify all subscribers of an event.
     */
    public void notifyObservers() {
        for (Subscriber subscriber : subscribers) {
            subscriber.update();
        }
    }
}