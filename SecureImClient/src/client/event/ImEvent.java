/**
 * Interface class for the different type of event expecting by
 * the event handler. An ImEvent can be:
 *    1) transport event - event created when sending/receiving a request
 *    2) timeout event   - event created when a timeout occurs in a request
 *    3) user event      - event created by an user
 *
 * @author HuskyHackers
 */

package client.event;

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