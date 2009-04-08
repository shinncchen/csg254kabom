/**
 * Task performed when a timeout occurs
 *
 * @author HuskyHackers
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

        /**
         * TimerTimeoutTask constructor
         * @param requestId - int, request id
         * @param ipAddress - string, ip address
         */
        public TimerTimeoutTask(int requestId, String ipAddress) {
            this.requestId = requestId;
            this.ipAddress = ipAddress;
        }

        /**
         * Tasks to perform when time out occurs
         */
        public void run() {
            System.out.println("Timeout occoured");
            TimeoutEvent timeoutEvent = new TimeoutEvent();
            timeoutEvent.setRequestId(requestId);
            timeoutEvent.setClientIp(ipAddress);
            ChatMaster.handle(timeoutEvent);
        }
    }
