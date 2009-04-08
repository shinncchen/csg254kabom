/**
 * Task performed when a timeout occurs
 *
 * @author HuskyHackers
 */

package client.request;

import client.ChatMaster;
import client.event.TimeoutEvent;
import java.util.TimerTask;

public class TimerTimeoutTask extends TimerTask {
        
        private int requestId;

        /**
         * TimerTimeoutTask constructor
         * @param requestId
         */
        public TimerTimeoutTask(int requestId) {
            this.requestId = requestId;
        }

        /**
         * Tasks when time out occurs
         */
        public void run() {
            System.out.println("Timeout occoured");
            TimeoutEvent timeoutEvent = new TimeoutEvent();
            timeoutEvent.setRequestId(requestId);
            ChatMaster.handle(timeoutEvent);
        }
    }
