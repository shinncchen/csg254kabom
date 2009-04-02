/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client.event;

import client.request.Request;

/**
 *
 * @author Shinn Chyang Chen
 */
public class GuiEvent implements ImEvent {
    
    private Request requestRecieved = null;

    public int getEventType() {
        return ImEvent.USER_EVENT;
    }

    public void setRequestRecieved(Request requestRecieved) {
        this.requestRecieved = requestRecieved;
    }

    public Request getRequestRecieved() {
        return this.requestRecieved;
    }
}
