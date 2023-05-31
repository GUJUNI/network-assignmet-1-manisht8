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
// Client
// ================
import java.io.*;
import java.net.*;
import java.util.Scanner;

class ArraySort_C {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 12345;

            Scanner sc = new Scanner(System.in);

            // Input array size from user
            System.out.print("Enter array size : ");
            int size = sc.nextInt();

            // Create an array of integers to send to the server
            int[] numbers = new int[size];
            for(int i = 0; i < size ; i++){
                System.out.print("Enter element " + (i+1) + " : ");
                numbers[i] = sc.nextInt();
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(numbers);

            // Send the array to the server
            byte[] sendData = baos.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);

            // Receive the sorted arrays from the server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            ByteArrayInputStream bais = new ByteArrayInputStream(receiveData);
            ObjectInputStream ois = new ObjectInputStream(bais);
            int[] ascending = (int[]) ois.readObject();
            int[] descending = (int[]) ois.readObject();

            // Print the sorted arrays
            System.out.println("Array sorted in Ascending order:- ");
            for (int num : ascending) {
                System.out.print(num + " ");
            }
            System.out.println();

            System.out.println("Array sorted in Descending order:- ");
            for (int num : descending) {
                System.out.print(num + " ");
            }
            System.out.println();

            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
****************************************************************************************************************************************
Output:-

Enter array size : 7
Enter element 1 : -1
Enter element 2 : 0
Enter element 3 : -3
Enter element 4 : 10
Enter element 5 : 50
Enter element 6 : -30
Enter element 7 : 100
Array sorted in Ascending order:-
-30 -3 -1 0 10 50 100
Array sorted in Descending order:-
100 50 10 0 -1 -3 -30

*/