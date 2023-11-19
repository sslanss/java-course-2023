package edu.hw6.task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

public class PortRegistrationChecker {
    private final Map<Integer, String> tcpPorts;
    private final Map<Integer, String> udpPorts;

    public PortRegistrationChecker() {
        tcpPorts = new HashMap<>() {{
            put(22, "SSH");
            put(80, "HTTP");
            put(135, "EPMAP");
            put(5413, "Wonderware SuiteLink Protocol");
            put(3306, "MySQL");
            put(5421, "NetSupport 2");
            put(5432, "PostgreSQL");
            put(5445, "Cisco Unified Video Advantage");
            put(5495, "Applix TM1 Admin server");
            put(5498, "Hotline tracker server connection");
            put(5500, "VNC remote desktop protocol");
            put(5501, "Hotline file transfer connection");
            put(5517, "Setiqueue Proxy server client for SETI@Home project");
            put(5555, "Freeciv versions up to 2.0, Hewlett Packard Data Protector, SAP");
        }};
        udpPorts = new HashMap<>() {{
            put(22, "SSH");
            put(80, "HTTP");
            put(137, "Служба имен NetBIOS");
            put(138, "Служба датаграмм NetBIOS");
            put(3306, "MySQL");
            put(5432, "PostgreSQL");
            put(5445, "Cisco Unified Video Advantage");
            put(5499, "Hotline tracker server discovery");
            put(5555, "Freeciv");
            put(5632, "pcANYWHEREstat, Symantec pcAnywhere (version 7.52 and later) status");
        }};
    }

    public void checkPorts() {
        System.out.println("Протокол\tПорт\tСервис");
        connectTCPPorts(tcpPorts);
        connectUDPPorts(udpPorts);
    }

    private void connectTCPPorts(Map<Integer, String> ports) {
        for (Map.Entry<Integer, String> entry : ports.entrySet()) {
            int port = entry.getKey();
            String serviceName = entry.getValue();
            try {
                ServerSocket socket = new ServerSocket(port);
                socket.close();
                System.out.println("TCP" + "\t\t\t" + port + "\t\t");
            } catch (IOException e) {
                System.out.println("TCP" + "\t\t\t" + port + "\t\t" + serviceName);
            }
        }
    }

    private void connectUDPPorts(Map<Integer, String> ports) {
        for (Map.Entry<Integer, String> entry : ports.entrySet()) {
            int port = entry.getKey();
            String serviceName = entry.getValue();
            try {
                DatagramSocket datagramSocket = new DatagramSocket(port);
                datagramSocket.close();
                System.out.println("UDP" + "\t\t\t" + port + "\t\t");
            } catch (IOException e) {
                System.out.println("UDP" + "\t\t\t" + port + "\t\t" + serviceName);
            }
        }
    }
}
