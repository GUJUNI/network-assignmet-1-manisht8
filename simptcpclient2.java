/*
Name - Manish Tulsiani
Roll No - 37
Course - MCA-2
Subject - Advanced Networking
Assignment - Practical Assignment-1
****************************************************************************************************************************************
Question : TCP socket program in Java where the client sends a string, a character, and a word to the server. The server then returns the number of occurrences of the character and the word, along with the length of the string.
*/

// ================
// Client
// ================
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

class StringOccurences_C {
    public static void main(String[] args) {
        try {
            // Create a socket connection to the server
            Socket socket = new Socket("localhost", 8888);

            Scanner sc = new Scanner(System.in);

            // Get the output stream to send data to the server
            OutputStream outputStream = socket.getOutputStream();

            // Get user input for string, character, and word
            BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter a string : ");
            String str = sc.nextLine();

            System.out.print("Enter a character to search : ");
            char ch = sc.nextLine().charAt(0);

            System.out.print("Enter a word to search : ");
            String word = sc.nextLine();

            // Send the string, character, and word to the server
            String data = str + "\n" + ch + "\n" + word + "\n";

            outputStream.write(data.getBytes());

            // Read the response from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Close the socket connection
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
****************************************************************************************************************************************
Output:-

Enter a string : Hello. I am Manish. Just want to say Hello again. Hello
Enter a character to search : a
Enter a word to search : Hello
Character count : 6
Word count : 3
String length : 55

*/