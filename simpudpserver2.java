/*
Name - Manish Tulsiani
Roll No - 37
Course - MCA-2
Subject - Advanced Networking
Assignment - Practical Assignment-1
****************************************************************************************************************************************
Question : UDP socket program in Java where the client sends a string, and the server returns the reverse string and the reverse of the sentence
*/

// ================
// Server
// ================
import java.io.*;
import java.net.*;

class StringReverse_S {
    public static void main(String[] args) {
        try {
            int serverPort = 12345;

            // Create socket
            DatagramSocket serverSocket = new DatagramSocket(serverPort);

            System.out.println("Server listening on port " + serverPort);

            while (true) {
                // Receive data from client
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                System.out.println("New client connected");

                // Process client's request
                String inputString = new String(receivePacket.getData(), 0, receivePacket.getLength());

                System.out.println("Client Input - Sentence : " + inputString);

                String reversedString = new StringBuilder(inputString).reverse().toString();
                String reversedSentence = new StringBuilder(inputString).toString();

                // Create response
                String response = "Reversed String: " + reversedString + "\nReversed Sentence: " + reverseSentence(inputString);
                byte[] sendData = response.getBytes();

                // Get client's address and port
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                // Send response to client
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String reverseSentence(String sentence) {
        String[] words = sentence.split("\\s+");
        StringBuilder reversedSentence = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            reversedSentence.append(words[i]).append(" ");
        }
        return reversedSentence.toString().trim();
    }
}

/*
****************************************************************************************************************************************
Output:-

Server listening on port 12345
New client connected
Client Input - Sentence : My name is Manish Tulsiani

*/