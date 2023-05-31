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
// Server
// ================
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class StringOccurences_S {
    public static void main(String[] args) {
        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("Server is listening on port 5000");

            // Wait for a client connection
            Socket socket = serverSocket.accept();
            System.out.println("New client connected!");

            // Get the input stream to read data from the client
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // Read the string, character, and word from the client
            String str = reader.readLine();
            char ch = reader.readLine().charAt(0);
            String word = reader.readLine();

            System.out.println("Client Input :-");
            System.out.println("String : " + str);
            System.out.println("Word to be searched : " + word);
            System.out.println("Character to be searched : " + ch);

            // Perform the required calculations
            int charCount = countOccurrences(str, ch);
            int wordCount = countOccurrences(str, word);
            int strLength = str.length();

            // Prepare the response string
            String response = "Character count : " + charCount + "\nWord count : " + wordCount + "\nString length : " + strLength + "\n";

            // Get the output stream to send data back to the client
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(response.getBytes());

            // Close the socket connection
            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to count the occurrences of a character in a string
    private static int countOccurrences(String str, char ch) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }

    // Method to count the occurrences of a word in a string
    private static int countOccurrences(String str, String word) {
        int count = 0;
        int index = 0;
        while (index != -1) {
            index = str.indexOf(word, index);
            if (index != -1) {
                count++;
                index += word.length();
            }
        }
        return count;
    }
}

/*
****************************************************************************************************************************************
Output:-

Server is listening on port 5000
New client connected!
Client Input :-
String : Hello. I am Manish. Just want to say Hello again. Hello
Word to be searched : Hello
Character to be searched : a

*/