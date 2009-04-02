/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client.transport;

import client.ChatMaster;
import client.event.TransportEvent;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author Raghuram
 */
public class Listener implements Runnable {
    
    private int port = 0;
    
    public void run() {
        try {

            // Create a socket to listen on the port.
            DatagramSocket datagramSocket = new DatagramSocket(port);

            // Create a buffer to read datagrams into. If a
            // packet is larger than this buffer, the
            // excess will simply be discarded!
            byte[] receiveBuffer = new byte[datagramSocket.getReceiveBufferSize()];

            // Now loop forever, waiting to receive packets
            while (true) {
                // Create a packet to receive data into the buffer
                DatagramPacket datagramPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                // Wait to receive a datagram
                datagramSocket.receive(datagramPacket);
                
                System.out.println("Received a datagram pkt...");
                try {
                    // Decode the reveived packet and create an event object
                    Decode decode = new Decode();
                    TransportEvent transportEvent = decode.getTransportEventFromDatagram(datagramPacket);
                    if (transportEvent!=null) {
                    	System.out.println("\tfrom: "+transportEvent.getRequestRecieved().getSenderIp());
                    	System.out.println("\tRID: "+transportEvent.getRequestRecieved().getRequestId());
                    	// Call the master event handler with the transport event
                    	ChatMaster.handle(transportEvent);
                    }
                } catch (Exception processException) {
                    processException.printStackTrace();
                }
                
                // Reset the length of the packet before reusing it.
                datagramPacket.setLength(0);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setPort(int port) {
        this.port = port;
    }
}