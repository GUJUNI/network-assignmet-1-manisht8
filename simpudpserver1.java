/*
Name - Manish Tulsiani
Roll No - 37
Course - MCA-2
Subject - Advanced Networking
Assignment - Practical Assignment-1
****************************************************************************************************************************************
Question : UDP socket program in Java where client sends number of days, and server converts it to years, months and days
*/

// ================
// Server
// ================
import java.net.*;
import java.io.*;

class Days_S {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(5000);
            System.out.println("Server is listening on port 5000");

            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                System.out.println("New client connected");

                String daysStr = new String(receivePacket.getData()).trim();
                int days = Integer.parseInt(daysStr);

                System.out.println("Client Input - Number of days = " + days);

                int years = days / 365;
                int months = (days % 365) / 30;
                int remainingDays = days - (years * 365) - (months * 30);

                String response = years + " years, " + months + " months, and " + remainingDays + " days";
                sendData = response.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(),receivePacket.getPort());
                serverSocket.send(sendPacket);
                System.out.println("Response sent");

                receiveData = new byte[1024];
                sendData = new byte[1024];
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

/*
****************************************************************************************************************************************
Output:-

Server is listening on port 5000
New client connected
Client Input - Number of days = 1128
Response sent

*/