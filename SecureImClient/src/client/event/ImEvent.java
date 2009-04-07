package client.event;

/**
 * @author HuskyHackers
 *
 * Interface class for the different type of event expecting by
 * the event handler. An ImEvent can be:
 *    1) transport event - sending/receiving a request
 *    2) timeout event   - a timeout occured in a request
 *    3) user event      - gui event occured
 */
public interface ImEvent {
    
    public static final int TRANSPORT_EVENT = 1;
    public static final int TIMEOUT_EVENT = 2;
    public static final int USER_EVENT = 3;

    /**
     * Get the ImEvent type
     * @return - int, event type
     */
    public int getEventType();   
}