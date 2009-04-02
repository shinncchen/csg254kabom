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
    private int protocol_type;
    private String[] parameter = null;

    public String[] getValue() {
        return parameter;
    }

    public void setParameter(String[] parameter) {
        this.parameter = parameter;
    }

    public int getProtocolType() {
        return protocol_type;
    }

    public void setProtocolType(int protocol) {
        this.protocol_type = protocol;
    }

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
