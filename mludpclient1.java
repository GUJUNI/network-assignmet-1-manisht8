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
            // Open a socket to the server
            Socket socket = new Socket(hostname, port);

            // Get input and output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Get user input
            BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter a string: ");
            String input = userIn.readLine();

            // Send input to server
            out.println(input);

            // Receive result from server and print it
            String result = in.readLine();
            System.out.println(result);

            // Close socket and streams
            socket.close();
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

Enter a string: manish
Vowels: 2, Consonants: 4

*/