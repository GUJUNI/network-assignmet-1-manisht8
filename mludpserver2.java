/*
Name - Manish Tulsiani
Roll No - 37
Course - MCA-2
Subject - Advanced Networking
Assignment - Practical Assignment-1
****************************************************************************************************************************************
Question : UDP socket program in Java where the client sends an array of integers to the server. The server then sorts the array in ascending and descending order and returns both sorted arrays. The server should be multi-threaded.
*/

// ================
// Server
// ================
import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ArraySort_S {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(12345);
            System.out.println("Server started and listening on port 12345...");
            ExecutorService executor = Executors.newFixedThreadPool(10);

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                System.out.println("New client connected");

                // Handle client request in a separate thread
                Runnable task = new ClientRequestHandler(serverSocket, receivePacket);
                executor.execute(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ClientRequestHandler implements Runnable {
    private DatagramSocket serverSocket;
    private DatagramPacket receivePacket;

    public ClientRequestHandler(DatagramSocket serverSocket, DatagramPacket receivePacket) {
        this.serverSocket = serverSocket;
        this.receivePacket = receivePacket;
    }

    @Override
    public void run() {
        try {
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();

            ByteArrayInputStream bais = new ByteArrayInputStream(receivePacket.getData());
            ObjectInputStream ois = new ObjectInputStream(bais);
            int[] numbers = (int[]) ois.readObject();

            // Display Client Input
            System.out.println("Client Input :-");
            System.out.println("Array elements are :-");
            for(int i = 0; i < numbers.length ; i++){
                System.out.println("Element " + (i+1) + " : " + numbers[i]);
            }

            // Sort the array in ascending order
            int[] ascending = Arrays.copyOf(numbers, numbers.length);
            Arrays.sort(ascending);

            // Sort the array in descending order
            int[] descending = Arrays.copyOf(numbers, numbers.length);
            Arrays.sort(descending);
            for (int i = 0, j = descending.length - 1; i < j; i++, j--) {
                int temp = descending[i];
                descending[i] = descending[j];
                descending[j] = temp;
            }

            // Prepare the sorted arrays to send back to the client
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(ascending);
            oos.writeObject(descending);

            // Send the sorted arrays to the client
            byte[] sendData = baos.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            serverSocket.send(sendPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
****************************************************************************************************************************************
Output:-

Server started and listening on port 12345...
New client connected
Client Input :-
Array elements are :-
Element 1 : -1
Element 2 : 0
Element 3 : -3
Element 4 : 10
Element 5 : 50
Element 6 : -30
Element 7 : 100

*/