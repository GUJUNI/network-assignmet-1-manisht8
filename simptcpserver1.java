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
// Server
// ================
import java.net.*;
import java.io.*;

class Salary_S {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server is listening on port 5000");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected");

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                int baseSalary = Integer.parseInt(in.readLine());
                int daPercentage = Integer.parseInt(in.readLine());
                int hraPercentage = Integer.parseInt(in.readLine());
                System.out.println("\nClient Input :-");
                System.out.println("Base Salary : " + baseSalary);
                System.out.println("DA Percentage : " + daPercentage);
                System.out.println("HRA Percentage : " + hraPercentage);
                System.out.println("--------------------");

                int grossSalary = calculateGrossSalary(baseSalary, daPercentage, hraPercentage);

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println("Gross salary (including DA and HRA): " + grossSalary);
                System.out.println("Response sent");

                clientSocket.close();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static int calculateGrossSalary(int baseSalary, int daPercentage, int hraPercentage) {
        int da = (int)(daPercentage / 100.0 * baseSalary);  // DA is percentage of base salary
        int hra = (int)(hraPercentage / 100.0 * baseSalary);  // HRA is percentage of base salary
        int grossSalary = baseSalary + da + hra;

        System.out.println("Total HRA : " + hra);
        System.out.println("Total DA : " + da);

        return grossSalary;
    }
}

/*
****************************************************************************************************************************************
Output:-

Server is listening on port 5000
New client connected

Client Input :-
Base Salary : 10000
DA Percentage : 20
HRA Percentage : 40
--------------------
Total HRA : 4000
Total DA : 2000
Response sent

*/