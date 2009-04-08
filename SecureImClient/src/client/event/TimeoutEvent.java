package client.event;

/**
 * @author HuskyHackers
 *
 * TimeoutEvent class which create an timeout event handled by the state machine
 */
public class TimeoutEvent implements ImEvent {

    private int requestId ;

    /**
     * Get the event type
     * @return - int, ImEvent.TIMEOUT_EVENT
     */
    public int getEventType() {
        return ImEvent.TIMEOUT_EVENT;
    }

    /**
     * Get the request id when timeout occurs
     * @return - int, request id
     */
    public int getRequestId() {
        return requestId;
    }

    /**
     * Set the request for a request id
     * @param requestId - int, request id
     */
    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }
    
}
