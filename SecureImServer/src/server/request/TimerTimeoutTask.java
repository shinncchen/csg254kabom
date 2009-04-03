/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server.request;

import java.util.TimerTask;
import server.ChatMaster;
import server.event.TimeoutEvent;

/**
 *
 * @author Raghuram
 */
public class TimerTimeoutTask extends TimerTask {
        
        private int requestId;
        private String ipAddress;
        
        public TimerTimeoutTask(int requestId, String ipAddress) {
            this.requestId = requestId;
            this.ipAddress = ipAddress;
        }
        
        public void run() {
            System.out.println("Timeout occoured");
            TimeoutEvent timeoutEvent = new TimeoutEvent();
            timeoutEvent.setRequestId(requestId);
            timeoutEvent.setClientIp(ipAddress);
            ChatMaster.handle(timeoutEvent);
        }
    }
