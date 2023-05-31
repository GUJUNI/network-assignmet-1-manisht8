/*
Name - Manish Tulsiani
Roll No - 37
Course - MCA-2
Subject - Advanced Networking
Assignment - Practical Assignment-1
****************************************************************************************************************************************
Question : TCP socket program in Java where client enters base salary and DA percentage and HRA percentage, and server returns the gross salary including DA and HRA
*/

// ================
// Client
// ================
import java.net.*;
import java.io.*;

class Salary_C {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connected to server");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter base salary: ");
            int baseSalary = Integer.parseInt(reader.readLine());
            System.out.print("Enter DA percentage: ");
            int daPercentage = Integer.parseInt(reader.readLine());
            System.out.print("Enter HRA percentage: ");
            int hraPercentage = Integer.parseInt(reader.readLine());

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(baseSalary);
            out.println(daPercentage);
            out.println(hraPercentage);

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
Enter base salary: 10000
Enter DA percentage: 20
Enter HRA percentage: 40
Gross salary (including DA and HRA): 16000

*/