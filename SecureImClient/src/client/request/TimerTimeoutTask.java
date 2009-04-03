/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client.request;

import client.ChatMaster;
import client.event.TimeoutEvent;
import java.util.TimerTask;

/**
 *
 * @author Raghuram
 */
public class TimerTimeoutTask extends TimerTask {
        
        private int requestId;
        
        public TimerTimeoutTask(int requestId) {
            this.requestId = requestId;
        }
        
        public void run() {
            System.out.println("Timeout occoured");
            TimeoutEvent timeoutEvent = new TimeoutEvent();
            timeoutEvent.setRequestId(requestId);
            ChatMaster.handle(timeoutEvent);
        }
    }
