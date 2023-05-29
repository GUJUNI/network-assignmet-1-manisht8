/*
Name - Manish Tulsiani
Roll No - 37
Course - MCA-2
Subject - Advanced Networking
Assignment - Practical Assignment-1
****************************************************************************************************************************************
Question : TCP socket program in Java where the client sends an array of integers to the server, and the server calculates and returns the sum, average, maximum, and minimum elements of that array. The server should be multi-threaded.
*/

// ================
// Server
// ================
import java.io.*;
import java.net.*;
import java.util.Arrays;

class ArrayOperations_S {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server started and listening on port 12345...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected");

                ClientHandler clientHandler = new ClientHandler(clientSocket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Read the array of integers from the client
            String input = in.readLine();
            String[] numbers = input.split(",");
            int[] array = new int[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                array[i] = Integer.parseInt(numbers[i]);
            }

            // Display Client Input
            System.out.println("Client Input :-");
            System.out.println("Array elements are :-");
            for(int i = 0; i < array.length ; i++){
                System.out.println("Element " + (i+1) + " : " + array[i]);
            }

            // Calculate the sum, average, maximum, and minimum
            int sum = 0;
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for (int num : array) {
                sum += num;
                if (num > max) {
                    max = num;
                }
                if (num < min) {
                    min = num;
                }
            }
            double average = (double) sum / array.length;

            // Send the result back to the client
            out.println("Sum: " + sum);
            out.println("Average: " + average);
            out.println("Maximum: " + max);
            out.println("Minimum: " + min);

            clientSocket.close();
        } catch (IOException e) {
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
Element 1 : 10
Element 2 : 20
Element 3 : 30
Element 4 : 40
Element 5 : 50

*/