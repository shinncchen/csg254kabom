/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client.event;

/**
 *
 * @author Raghuram
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
