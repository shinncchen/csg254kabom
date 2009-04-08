/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server.event;

import server.request.Request;

/**
 * @author HuskyHackers
 *
 * TransportEvent class which create an event when sending/receiving a request
 */
public class TransportEvent implements ImEvent {

    private Request requestRecieved = null;
    private String clientIp = null;

    /**
     * Get the event type
     * @return - int, ImEvent.TRANSPORT_EVENT
     */
    public int getEventType() {
        return ImEvent.TRANSPORT_EVENT;
    }

    /**
     * Set the request received
     * @param requestRecieved - Request, request received
     */
    public void setRequestRecieved(Request requestRecieved) {
        this.requestRecieved = requestRecieved;
    }

    /**
     * Get the request received
     * @return - Request, request received
     */
    public Request getRequestRecieved() {
        return this.requestRecieved;
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
