/**
 * TimeoutEvent class which create an timeout event handled by the state machine
 *
 * @author HuskyHackers
 */

package server.event;

public class TimeoutEvent implements ImEvent {

    private int requestId ;
    private String clientIp = null;

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

    /**
     * Set the client IP address
     * @param clientIp - string, IP address
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    /**
     * Get the client IP address
     * @return - string, IP address
     */
    public String getClientIp() {
        return clientIp;
    }
    
}
