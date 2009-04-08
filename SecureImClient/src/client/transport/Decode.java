/**
 * Decode the received packet to an instance of Request
 *
 * @author HuskyHackers
 */

package client.transport;

import client.event.TransportEvent;
import client.request.Request;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.net.DatagramPacket;

public class Decode {

    /**
     * Decode a datagram packet to an instance of Request
     * and encapsulate in a TransportEvent
     * @param datagramPacket - DatagramPacket, the datagram packet received
     * @return               - TransportEvent, transport event
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     */
    public TransportEvent getTransportEventFromDatagram(DatagramPacket datagramPacket) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        
        // Initialize a Transport Event that has to be returned
        TransportEvent transportEvent = new TransportEvent();
        
        // Initialize the received data from the datagram
        byte[] receivedData = datagramPacket.getData();
        
        // Create Byte and Data streams from the received data to retrieve data from
        ByteArrayInputStream bais = new ByteArrayInputStream(receivedData);
        ObjectInputStream dis = new ObjectInputStream(bais);
        
        // Retrieve the request id from the received data as it is the first peice of info
        int requestId = dis.readInt();
        
        // Try to create a class from the request Id
        String className = "client.request.Rid" + requestId;
        Class requestClass = null;
        try {
        	requestClass = Class.forName(className);
        } catch (Exception e) { System.err.println("Invalid RID"); return null; }
        // Create a new object of that request class and load the data into it
        Request request = (Request) requestClass.newInstance();
        
        // Set the Ip address of the sender
        request.setSenderIp(new String(datagramPacket.getAddress().getHostAddress()));
        
        // Set the Request Id
        request.setRequestId(requestId);
        
        // Set the received Data
        request.setRequestData(receivedData);
        
        transportEvent.setRequestRecieved(request);
        
        dis.close();
        
        // Return the updated transport event
        return transportEvent;
    }
}

