/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server.transport;

import server.ChatMaster;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author Raghuram
 */
public class Sender {
    
    public void send(byte[] message) throws SocketException, UnknownHostException, IOException {
        this.send(message, ChatMaster.SERVER_IP, ChatMaster.SERVER_PORT);
    }

    public void send(byte[] message, String remoteIp, int remotePort) throws SocketException, UnknownHostException, IOException {
        // Initialize the INET address of the remote machine
        InetAddress remoteInetAddress = InetAddress.getByName(remoteIp);
        
        // Initialize a datagram packet with data and address
        DatagramPacket datagramPacket = new DatagramPacket(message, message.length, remoteInetAddress, remotePort);
        
        // Initialize a Datagram socket
        DatagramSocket datagramSocket = new DatagramSocket();
        
        // Check if the message size is greater that the send buffer size, if yes throw an exception
        if (message.length > datagramSocket.getSendBufferSize()) {
            throw new IOException("Too much data");
        }
        
        // Send the datagram packet
        datagramSocket.send(datagramPacket);
        
        // Close the Datagram socket
        datagramSocket.close();
    }

}