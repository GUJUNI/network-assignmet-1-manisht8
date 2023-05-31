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
// Client
// ================
import java.io.*;
import java.net.*;

class String_Details_C {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 12345;

        try {
            // Create a datagram socket
            DatagramSocket socket = new DatagramSocket();

            // Get user input
            BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter a string: ");
            String input = userIn.readLine();

            // Convert input to bytes and create a datagram packet
            byte[] sendBuffer = input.getBytes();
            InetAddress serverAddress = InetAddress.getByName(hostname);
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, port);

            // Send the packet to the server
            socket.send(sendPacket);

            // Receive result from the server
            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);

            // Convert received data to string and print it
            String result = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println(result);

            // Close the socket
            socket.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

/*
****************************************************************************************************************************************
Output:-

Enter a string: manish
Vowels: 2, Consonants: 4

*/