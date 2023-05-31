/*
Name - Manish Tulsiani
Roll No - 37
Course - MCA-2
Subject - Advanced Networking
Assignment - Practical Assignment-1
****************************************************************************************************************************************
Question : Write a Program That Performs Encryption/Decryption.
*/

import java.security.*;
import javax.crypto.*;
import java.util.Base64;
import java.util.Scanner;

public class encryption {
    public static void main(String[] args) throws Exception {
        // Generate RSA key pair
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom();
        keyPairGenerator.initialize(2048, secureRandom);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // Get public and private keys
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Display encoded private and public keys
        String encodedPrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        String encodedPublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        System.out.println("\nPrivate Key: " + encodedPrivateKey);
        System.out.println("\nPublic Key: " + encodedPublicKey);

        // Get input string from the user
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter a string to encrypt : ");
        String inputString = scanner.nextLine();
        scanner.close();

        // Encryption
        byte[] encryptedBytes = encrypt(inputString, publicKey);

        // Print input string, encrypted message, and decrypted message
        System.out.println("Input String: " + inputString);
        System.out.println("\nEncrypted message: " + Base64.getEncoder().encodeToString(encryptedBytes));

        // Decryption
        String decryptedMessage = decrypt(encryptedBytes, privateKey);

        // Print decrypted message
        System.out.println("\nDecrypted message: " + decryptedMessage);
    }

    public static byte[] encrypt(String message, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(message.getBytes());
    }

    public static String decrypt(byte[] encryptedBytes, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
}

/* 
OUTPUT :-

Private Key: MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCfSkCOmj39ZGocLpg8hEsvVwl6DOzMYFsh0XT/sUGS0XFAH5tKidzcZxUWBuBAoDHF1PQF/Rkkm++HYR/IXJmhUbkzXGfiKmamkMKYoCvoTb7SEOy81Wa8/ISjro+9hJ0Eyc4P/5RV121+ss0WLXw/I05S+N1UJX8ePGt1gadEFnyI9esUC3PR26t/vH2nb3p2+FX0weqYv3b++GkNSlR+1tiM9Bh15+hM6JA0xxyVEJV9rtEbEN9tPb055Y99+Kbw9UnHqSF9/SPGiqr1NMuzdk1khVPPGGKQhyILz2VrO/utZ8oNwEw36mlMW3x/2gcsbD+1KlV6M3yfmfaQ5pWTAgMBAAECggEATZa69FswN8WaJAkUMWIVR0VjgdZa38BNk571mPiL4spvGhdrKncVpCv36udFg2UkaWMty975SMpF9dD6zLtCQABRMDHiDhmuL5MSnopiaMC54pPI5MAjz4lGyH3mWR+H5kWwQD2zUM7i8FSCrjtQqmZ1p9e941dyYFV42F9jjLeLba3xpVHFAxfHCa7aDqu9PlC+MWsgGK/YY+FEEzFLqTGFgzV4tCRnXXvY1Da2iOxmreH7QbBKbOd6Lvyr1uLnsk/uAzmkfzs0o0HGAuO29XB/w1OveN7AaBGbKIwtKyTkRiNBRxnOKybRxiO83BYgVWP64lAielCGGvk+oX8zsQKBgQC1bOfMoNypjDwOjuOWBFZMvisE3CQNvu0qorWVwAlQ9F5MuGU60ycbkcYtBZftps5fRqJabB2lNuNGeDoliLidm7AOGEhwj7O9zSDWFGkXx9P4q16NUHbLCMpj9RkG7d05JmMdZsHwqfM1Wbg2HSRjsF7K4PBGnEOIkJq4oAcP8QKBgQDgxBRATkpEGaFwwAajPkehvQlQY5+u2E5n/13nQSI2YEiDIaMKEQLkSPGu19ML0xfmhhUzHEIqaJJf5b8BuaC2xHNg2Ig5oHsMMXEaX4RNmGvAznB+2kqaX+J42xbcbqNTm0ZsHf9EA4305Tquf8ew44+Mmq/x/ENDheb3YU2BwwKBgBXWFdA7TFPb3R6jdCEKRJjYCrV7CZvd7w3Cp7hudLCZuP/i8cwyvfJDiWbB1Zd1DEHLbp8ThD3ubUx+DAGYgx/1LSuFILLrmOavWdRqgHusQYccmTTJCoNFe5asWWbMGmRWindvdshQrEUvl0xsFYUqJT6l9EWLaVV2kijGmhMBAoGANk+rBQ7l6ya2V+ROtktqS669/WrVPw6Ng5u1ORgICFOjsewC1X/ezwLBQ5prKMRNViq4HK2LX2v9UqbVTLlqGD/IEXLBGAurL/sSdWj3MzjNcL1xeXWN6VeaBdIK6CDj3mP61E07TVf+iVqKn3fI7PlPyo3mTfbC2iIU+VnNvLkCgYAkqcWU9I1wipTWrkU2EllpOmi/ecsMIdGoEf7t/jT1MUbDZfrLi/jorECgzDWMZ2N4DzENu6MWVIeeC+qzKjKO2Hmc3AUYM2cA1atGyqc8Ptgski/b7/yl6dAeZPoi5KIdhQEgWQhwm6ScweoqSpb7vIRE5rqniukS9xzl4o1CoQ==

Public Key: MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAn0pAjpo9/WRqHC6YPIRLL1cJegzszGBbIdF0/7FBktFxQB+bSonc3GcVFgbgQKAxxdT0Bf0ZJJvvh2EfyFyZoVG5M1xn4ipmppDCmKAr6E2+0hDsvNVmvPyEo66PvYSdBMnOD/+UVddtfrLNFi18PyNOUvjdVCV/HjxrdYGnRBZ8iPXrFAtz0durf7x9p296dvhV9MHqmL92/vhpDUpUftbYjPQYdefoTOiQNMcclRCVfa7RGxDfbT29OeWPffim8PVJx6khff0jxoqq9TTLs3ZNZIVTzxhikIciC89lazv7rWfKDcBMN+ppTFt8f9oHLGw/tSpVejN8n5n2kOaVkwIDAQAB

Enter a string to encrypt : manish tulsiani
Input String: manish tulsiani

Encrypted message: F9+jTceEMaqZjJB1IPy/Xk2NvuJWtsYPlArTXJTHaZd3hU6tHEvkFWBusaZo3EIIeZaIB7/q2kOYBQiKzzSJucpXuiKsuxKIEQqek3IUr/kV7+pXDNo9pj1BzpDZCAzWf5AIwW896yYNX4eSjcndRnNdPDf95Xa3zl91kErEcmpAxm5jm4JcnfncM058Vd3WjJo7wQFBexZQGINC3j+uTql161WmVVXAnozlM5FxoqvG6SjXlGseFzX+o3vtSs8pap0fzQDOf5c+Yaw2GEBXb0WE91PfdczO+nB9kCgN/G7kN8jFJa+X5AECViniIG0mpjMa0sClHg1P9xHyY9E8oQ==

Decrypted message: manish tulsiani

*/
