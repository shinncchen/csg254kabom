/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server.transport;

import server.event.TransportEvent;
import server.request.Request;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
        ObjectInputStream ois = null;
        int requestId = 0;
        try {
             ois = new ObjectInputStream(bais);

             requestId = ois.readInt();
        } catch (IOException ex) {
        //} catch (ClassNotFoundException ex) {
        }
        
        // dis = new DataInputStream(bais);
        
        // Retrieve the request id from the received data as it is the first peice of info
        //int requestId = dis.readInt();
        
        // Try to create a class from the request Id
        String className = "server.request.Rid" + requestId;
        Class requestClass = Class.forName(className);
        
        // Create a new object of that request class and load the data into it
        Request request = (Request) requestClass.newInstance();
        
        // Set the Ip address of the sender
        request.setSenderIp(new String(datagramPacket.getAddress().getHostAddress()));
        
        // Set the Request Id
        request.setRequestId(requestId);
        
        // Set the received Data
        request.setRequestData(receivedData);
        
        System.out.println("d request data length: "+receivedData.length);
        System.out.println("d request Data: "+new String(receivedData));
        System.out.println("d request address: "+receivedData);
        System.out.println("");
        
        transportEvent.setRequestRecieved(request);
        transportEvent.setClientIp(request.getSenderIp());
        
        //ois.reset();
        ois.close();
        
        // Return the updated transport event
        return transportEvent;
    }
}

