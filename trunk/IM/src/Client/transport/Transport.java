/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Client.transport;

/**
 *
 * @author Raghuram
 */
public class Transport {

    public TransportData sendAndReceive(TransportData transportData) {
        transportData.setMDataReceive("server response".getBytes());
        return transportData;
    }

}