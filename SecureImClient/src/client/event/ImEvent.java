/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client.event;

/**
 *
 * @author Raghuram
 */
public interface ImEvent {
    
    public static final int TRANSPORT_EVENT = 1;
    public static final int TIMEOUT_EVENT = 2;
    public static final int USER_EVENT = 3;
    
    public int getEventType();
    
}
