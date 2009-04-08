package client.event;

import client.request.Request;

/**
 * @author HuskyHackers
 *
 * GuiEvent class which create an gui event handled by the state machine
 */
public class GuiEvent implements ImEvent {
    
    public static final int EVENT_LOGIN = 10;
    public static final int EVENT_LIST = 20;
    public static final int EVENT_USERSELECT = 30;
    public static final int EVENT_USERCHAT = 40;
    public static final int EVENT_LOGOUT = 50;
    
    private Request requestRecieved = null;
    private int guiEventType;

    /**
     * Get gui event type
     * @return - int, ImEvent.USER_EVENT
     */
    public int getEventType() {
        return ImEvent.USER_EVENT;
    }

    /**
     * Set the request
     * @param requestRecieved - Request, request to set
     */
    public void setRequestRecieved(Request requestRecieved) {
        this.requestRecieved = requestRecieved;
    }

    /**
     * Get the request
     * @return - Request
     */
    public Request getRequestRecieved() {
        return this.requestRecieved;
    }

    /**
     * Get the gui event type which is one of the static member in this class
     * @return - int, gui event type
     */
    public int getGuiEventType() {
        return guiEventType;
    }

    /**
     * Set the gui event type
     * @param guiEventType - int, gui event type
     */
    public void setGuiEventType(int guiEventType) {
        this.guiEventType = guiEventType;
    }
}
