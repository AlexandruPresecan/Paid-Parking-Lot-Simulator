package com.company;

import java.util.LinkedList;
import java.util.List;

public class Event {

    public interface  EventListener {
        void onEvent();
    }

    private List<EventListener> listeners = new LinkedList();

    /** Adds a new EventListener to the current event
     * @param listener EventListener to be added
     */
    public void addListener(EventListener listener) {
        listeners.add(listener);
    }

    /** Calls all the listeners which were assigned to the current event <br>
     * Call this when the event happens
     */
    public void callListeners() {
        for (EventListener listener : listeners)
            listener.onEvent();
    }
}
