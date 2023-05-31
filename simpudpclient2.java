/*
Name - Manish Tulsiani
Roll No - 37
Course - MCA-2
Subject - Advanced Networking
Assignment - Practical Assignment-1
****************************************************************************************************************************************
Question : UDP socket program in Java where the client sends a string, and the server returns the reverse string and the reverse of the sentence.
*/

// ================
// Client
// ================
import java.io.*;
import java.net.*;

class StringReverse_C {
    public static void main(String[] args) {
        try {
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 12345;

            // Create socket
            DatagramSocket clientSocket = new DatagramSocket();

            // Get user input
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter a string: ");
            String inputString = reader.readLine();

            // Convert string to bytes and send to server
            byte[] sendData = inputString.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);

            // Receive data from server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            // Process and print server's response
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println(response);

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
****************************************************************************************************************************************
Output:-

Enter a string: My name is Manish Tulsiani
Reversed String: inaisluT hsinaM si eman yM
Reversed Sentence: Tulsiani Manish is name My

*/