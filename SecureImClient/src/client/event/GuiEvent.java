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
    
    public static final int EVENT_LOGIN = 10;
    public static final int EVENT_LIST = 20;
    public static final int EVENT_USERSELECT = 30;
    public static final int EVENT_USERCHAT = 40;
    public static final int EVENT_LOGOUT = 50;
    
    private Request requestRecieved = null;
    private int guiEventType;

    public int getEventType() {
        return ImEvent.USER_EVENT;
    }

    public void setRequestRecieved(Request requestRecieved) {
        this.requestRecieved = requestRecieved;
    }

    public Request getRequestRecieved() {
        return this.requestRecieved;
    }

    public int getGuiEventType() {
        return guiEventType;
    }

    public void setGuiEventType(int guiEventType) {
        this.guiEventType = guiEventType;
    }
}
