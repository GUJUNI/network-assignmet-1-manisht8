/*
Name - Manish Tulsiani
Roll No - 37
Course - MCA-2
Subject - Advanced Networking
Assignment - Practical Assignment-1
****************************************************************************************************************************************
Question : UDP socket program in Java program where client inputs a string, and server returns the number of vowels and consonants in it. The server should be multi-threaded.
*/

// ================
// Server
// ================
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class String_Details_S {
    public static void main(String[] args) {
        int port = 12345;

        try {
            // Create a datagram socket
            DatagramSocket serverSocket = new DatagramSocket(port);
            System.out.println("Server is listening on port 12345");

            // Create a thread pool for handling client requests
            ExecutorService threadPool = Executors.newFixedThreadPool(10);

            // Listen for incoming datagrams
            while (true) {
                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

                serverSocket.receive(receivePacket);
                System.out.println("Client connected");

                // Submit a new task to the thread pool
                threadPool.submit(new ClientHandler(serverSocket, receivePacket));
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

class ClientHandler implements Runnable {
    private DatagramSocket serverSocket;
    private DatagramPacket receivePacket;

    public ClientHandler(DatagramSocket serverSocket, DatagramPacket receivePacket) {
        this.serverSocket = serverSocket;
        this.receivePacket = receivePacket;
    }

    @Override
    public void run() {
        try {
            // Get input from client
            String input = new String(receivePacket.getData(), 0, receivePacket.getLength());

            System.out.println("Client Input - String is : " + input);

            // Count vowels and consonants
            int vowels = 0, consonants = 0;
            for (char c : input.toCharArray()) {
                if (Character.isLetter(c)) {
                    if ("AEIOUaeiou".indexOf(c) != -1) {
                        vowels++;
                    } else {
                        consonants++;
                    }
                }
            }

            // Send result back to client
            String result = "Vowels: " + vowels + ", Consonants: " + consonants;
            byte[] sendBuffer = result.getBytes();
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);

            serverSocket.send(sendPacket);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

/*
****************************************************************************************************************************************
Output:-

Server is listening on port 12345
Client connected
Client Input - String is : manish

*/