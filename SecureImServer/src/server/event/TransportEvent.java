/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server.event;

import server.request.Request;

/**
 *
 * @author Raghuram
 */
public class TransportEvent implements ImEvent {

    private Request requestRecieved = null;
    private String clientIp = null;
    
    public int getEventType() {
        return ImEvent.TRANSPORT_EVENT;
    }
    
    public void setRequestRecieved(Request requestRecieved) {
        this.requestRecieved = requestRecieved;
    }
    
    public Request getRequestRecieved() {
        return this.requestRecieved;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }
    
    public String getClientIp() {
        return clientIp;
    }
    
}
