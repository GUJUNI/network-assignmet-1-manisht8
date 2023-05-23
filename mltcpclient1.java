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
// Client
// ================
import java.net.*;
import java.io.*;

class Armstrong_C {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connected to server");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter lower bound: ");
            int lowerBound = Integer.parseInt(reader.readLine());
            System.out.print("Enter upper bound: ");
            int upperBound = Integer.parseInt(reader.readLine());

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(lowerBound);
            out.println(upperBound);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = in.readLine();
            System.out.println(response);

            socket.close();
        } catch (UnknownHostException e) {
            System.out.println("Error: unknown host");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

/*
****************************************************************************************************************************************
Output:-

Connected to server
Enter lower range: 2
Enter upper range: 1000
Armstrong numbers between 2 and 1000: 153 370 371 407

*/