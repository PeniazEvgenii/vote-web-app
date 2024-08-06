package by.it_academy.jd2.util;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {

        try (InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("ArtistsForTestLoad");
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            List<String> collect = bufferedReader.lines()
                    .filter(str -> !str.isBlank())
                    .collect(Collectors.toList());
            System.out.println(collect);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
