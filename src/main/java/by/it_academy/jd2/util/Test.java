package by.it_academy.jd2.util;

import java.io.*;

public class Test {
    public static void main(String[] args) {

        try (InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("ArtistsForTestLoad");
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
