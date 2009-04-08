package client.event;

/**
 * @author HuskyHackers
 *
 * TimeoutEvent class which create an gui event
 * to be handle by the state machine
 */
public class TimeoutEvent implements ImEvent {

    private int requestId ;
    
    public int getEventType() {
        return ImEvent.TIMEOUT_EVENT;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }
    
}
