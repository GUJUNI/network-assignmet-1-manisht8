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
// Client
// ================
import java.io.*;
import java.net.*;
import java.util.Scanner;

class ArrayOperations_C {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Connected to server...");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Scanner sc = new Scanner(System.in);

            // Input array size from user
            System.out.print("Enter array size : ");
            int size = sc.nextInt();

            // Create an array of integers to send to the server
            int[] array = new int[size];
            for(int i = 0; i < size ; i++){
                System.out.print("Enter element " + (i+1) + " : ");
                array[i] = sc.nextInt();
            }

            // Send the array to the server
            StringBuilder sb = new StringBuilder();
            for (int num : array) {
                sb.append(num).append(",");
            }
            out.println(sb.toString());

            // Receive and print the result from the server
            String result;
            while ((result = in.readLine()) != null) {
                System.out.println(result);
            }

            socket.close();
            System.out.println("Disconnected from server...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
****************************************************************************************************************************************
Output:-

Connected to server...
Enter array size : 5
Enter element 1 : 10
Enter element 2 : 20
Enter element 3 : 30
Enter element 4 : 40
Enter element 5 : 50
Sum: 150
Average: 30.0
Maximum: 50
Minimum: 10
Disconnected from server...

*/