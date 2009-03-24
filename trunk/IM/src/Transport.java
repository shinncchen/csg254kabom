import java.io.*;
import java.net.*;

public class Transport {

    public TransportData sendAndReiceve(TransportData SendTransportData) {
        TransportData receiveTransportData = null;

        try {
            byte[] dataToSend = SendTransportData.GetDataToSend();
            // new socket for tcp communication
            Socket sock = new Socket(SendTransportData.GetIP(), SendTransportData.GetPort());
            // get outputstream from the socket
            ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
            // get inputstream from the socket
            ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());

            // Send the data
            oos.writeObject(dataToSend);
            oos.flush();

            // Receive the data
            byte[] receive = (byte[]) ois.readObject();
            // TODO - set status and message based on received data
            int status = 0;
            String message = "";
            // TODO - set status and message based on received data
            receiveTransportData = new TransportData(receive, message, status);
            
            // close input stream
            ois.close();
            // close output stream
            oos.close();
            // close client socket
            sock.close();
        } catch(Exception ex) {
          System.out.println("Error: "+ex.toString());
        }

        return receiveTransportData;
    }
}