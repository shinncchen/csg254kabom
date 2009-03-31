/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client.transport;

import client.event.TransportEvent;
import client.request.Request;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;

/**
 *
 * @author Raghuram
 */
public class Decode {

    public TransportEvent getTransportEventFromDatagram(DatagramPacket datagramPacket) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        
        // Initialize a Transport Event that has to be returned
        TransportEvent transportEvent = new TransportEvent();
        
        // Initialize the received data from the datagram
        byte[] receivedData = datagramPacket.getData();
        
        // Create Byte and Data streams from the received data to retrieve data from
        ByteArrayInputStream bais = new ByteArrayInputStream(receivedData);
        DataInputStream dis = new DataInputStream(bais);
        
        // Retrieve the request id from the received data as it is the first peice of info
        int requestId = dis.readInt();
        
        // Try to create a class from the request Id
        String className = "client.request.Rid" + requestId;
        Class requestClass = Class.forName(className);
        
        // Create a new object of that request class and load the data into it
        Request request = (Request) requestClass.newInstance();
        
        // Set the Ip address of the sender
        request.setSenderIp(new String(datagramPacket.getAddress().getHostAddress()));
        
        // Set the Request Id
        request.setRequestId(requestId);
        
        // Set the received Data
        request.setRequestData(receivedData);
        
        transportEvent.setRequestRecieved(request);
        
        dis.reset();
        dis.close();
        
        // Return the updated transport event
        return transportEvent;
    }
}

