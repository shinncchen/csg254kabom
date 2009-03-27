/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client.event;

import client.request.Request;

/**
 *
 * @author Raghuram
 */
public class TransportEvent implements ImEvent {

    private Request requestRecieved = null;
    
    public int getEventType() {
        return ImEvent.TRANSPORT_EVENT;
    }
    
    public void setRequestRecieved(Request requestRecieved) {
        this.requestRecieved = requestRecieved;
    }
    
    public Request getRequestRecieved() {
        return this.requestRecieved;
    }
}
