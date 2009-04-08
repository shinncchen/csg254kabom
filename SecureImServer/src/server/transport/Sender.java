/**
 * Class contains method to send a datagram packet
 *
 * @author HuskyHackers
 */

package server.transport;

import server.ChatMaster;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Sender {

    /**
     * Send a datagram packet given a message
     * @param message - string, message to send
     * @throws java.net.SocketException
     * @throws java.net.UnknownHostException
     * @throws java.io.IOException
     */
    public void send(byte[] message) throws SocketException, UnknownHostException, IOException {
        this.send(message, ChatMaster.SERVER_IP, ChatMaster.SERVER_PORT);
    }

    /**
     * Send a datagram packet given a message, IP and port
     * @param message    - byte[], message to send
     * @param remoteIp   - string, ip address
     * @param remotePort - int, port number
     * @throws java.net.SocketException
     * @throws java.net.UnknownHostException
     * @throws java.io.IOException
     */
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