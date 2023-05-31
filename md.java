/*
Name - Manish Tulsiani
Roll No - 37
Course - MCA-2
Subject - Advanced Networking
Assignment - Practical Assignment-1
****************************************************************************************************************************************
Question : Write a program to compute a message digest for a file of any type and any size.
*/

import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class md {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file path: ");
        String filePath = scanner.nextLine();

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            FileInputStream fis = new FileInputStream(filePath);
            DigestInputStream dis = new DigestInputStream(fis, md);

            byte[] buffer = new byte[4096];
            int bytesRead;
            StringBuilder fileContent = new StringBuilder();
            while ((bytesRead = dis.read(buffer)) != -1) {
                // Reading the file while updating the digest
                md.update(buffer, 0, bytesRead);
                fileContent.append(new String(buffer, 0, bytesRead));
            }

            dis.close();
            byte[] digest = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }

            System.out.println("File content:\n" + fileContent.toString());
            System.out.println("\nMD5 : " + sb.toString());
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }
}

/*
OUTPUT :-

Enter the file path: C:\MCA\Sem2\AN\networking\message_digest_demo.txt
File content:
Hello. My name is Manish Tulsiani.

MD5 : 11826d0d76fbe8be979dd042702b0613

*/
