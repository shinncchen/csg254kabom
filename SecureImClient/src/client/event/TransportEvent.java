/**
 * TransportEvent class which create an event when sending/receiving a request
 *
 * @author HuskyHackers
 */

package client.event;

import client.request.Request;

public class TransportEvent implements ImEvent {

    private Request requestRecieved = null;

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
}
