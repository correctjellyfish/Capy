package org.example.capy.events;

/**
 * Interface for an object which watches a Reporter
 */
public interface Watcher {
    /**
     * Get and alert of an event occuring with the Reporter
     *
     * @param reporter The object reporting an event
     * @param event    The event which occured
     */
    public void receiveAlert(Reporter reporter, Event event);
}
