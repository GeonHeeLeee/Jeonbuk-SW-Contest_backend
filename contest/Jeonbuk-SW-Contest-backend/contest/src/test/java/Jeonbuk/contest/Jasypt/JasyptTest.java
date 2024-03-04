package Jeonbuk.contest.Jasypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.junit.jupiter.api.Test;

public class JasyptTest {
    @Test
    void stringEncryptor() {
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String username = "JeonbukContest";
        String password = "tkdgns123!";

        System.out.println("URL: " + jasyptEncoding(url));
        System.out.println("userName: " + jasyptEncoding(username));
        System.out.println("password: " + jasyptEncoding(password));
    }

    public String jasyptEncoding(String value) {
        String key = "ghlee00125";
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        pbeEnc.setPassword(key);
        pbeEnc.setIvGenerator(new RandomIvGenerator());
        return pbeEnc.encrypt(value);
    }
}
