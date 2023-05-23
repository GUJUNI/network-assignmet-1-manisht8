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
import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class String_Details_S {
    public static void main(String[] args) {
        int port = 12345;

        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is listening on port 12345");

            // Create a thread pool for handling client requests
            ExecutorService threadPool = Executors.newFixedThreadPool(10);

            // Listen for incoming connections
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected");

                // Submit a new task to the thread pool
                threadPool.submit(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
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
            // Get input and output streams for the client socket
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Read input from client
            String input = in.readLine();

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
            out.println("Vowels: " + vowels + ", Consonants: " + consonants);

            // Close client socket and streams
            clientSocket.close();
            in.close();
            out.close();
        } catch (IOException e) {
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