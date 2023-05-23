/*
Name - Manish Tulsiani
Roll No - 37
Course - MCA-2
Subject - Advanced Networking
Assignment - Practical Assignment-1
****************************************************************************************************************************************
Question : TCP socket program in Java where client sends 2 numbers : lower bound and upper bound, and server returns all the armstrong numbers between those numbers. The server should be multi-threaded.
*/

// ================
// Server
// ================
import java.net.*;
import java.io.*;

class Armstrong_S {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server is listening on port 5000");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected");

                // Start a new thread to handle the client's request
                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            int lowerBound = Integer.parseInt(in.readLine());
            int upperBound = Integer.parseInt(in.readLine());

            String armstrongNumbers = findArmstrongNumbers(lowerBound, upperBound);

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("Armstrong numbers between " + lowerBound + " and " + upperBound + ": " + armstrongNumbers);
            System.out.println("Response sent");

            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public String findArmstrongNumbers(int lowerBound, int upperBound) {
        String result = "";

        System.out.println("Client Input - Lower Bound : " + lowerBound);
        System.out.println("Client Input - Upper Bound : " + upperBound);
        for (int i = lowerBound; i <= upperBound; i++) {
            if (isArmstrong(i)) {
                result += i + " ";
            }
        }

        return result.trim();
    }

    public boolean isArmstrong(int number) {
        int originalNumber = number;
        int result = 0;

        while (originalNumber != 0) {
            int remainder = originalNumber % 10;
            result += Math.pow(remainder, 3);
            originalNumber /= 10;
        }

        return result == number;
    }
}

/*
****************************************************************************************************************************************
Output:-

Server is listening on port 5000
New client connected
Client Input - Lower Bound : 2
Client Input - Upper Bound : 1000
Response sent

*/