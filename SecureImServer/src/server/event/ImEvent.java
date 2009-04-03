/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server.event;

/**
 *
 * @author Raghuram
 */
public interface ImEvent {
    
    public static final int TRANSPORT_EVENT = 1;
    public static final int TIMEOUT_EVENT = 2;
    
    public int getEventType();
    
    public String getClientIp(); //TODO: should thee setter and the field be abstracted
}
