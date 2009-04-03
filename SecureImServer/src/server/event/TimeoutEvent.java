/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server.event;

/**
 *
 * @author Raghuram
 */
public class TimeoutEvent implements ImEvent {

    private int requestId ;
    private String clientIp = null;
    
    public int getEventType() {
        return ImEvent.TIMEOUT_EVENT;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }
    
    public String getClientIp() {
        return clientIp;
    }
    
}
