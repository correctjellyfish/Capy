package org.example.capy.events;

/**
 * Interface for an object which reports events
 */
public interface Reporter {
    /**
     * Add a watcher which will be notified when the repoter changes
     */
    public void addWatcher(Watcher watcher);

    /**
     * Send an alert to all associated watchers
     *
     * @param event The event that watchers should be notified of
     */
    public void sendAlert(Event event);
}
