package sample.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UsernameFileUtils {

    public final static String USER_FILE_NAME = "User.dat";
    private String username;

    public void MakeNewFile() throws IOException {
        File file = new File(USER_FILE_NAME);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public void saveUsername(String username) throws IOException {

        FileWriter fileWriter = new FileWriter(USER_FILE_NAME);
        fileWriter.write(username);
        fileWriter.close();

    }

    public String getUsername() throws IOException {

        FileReader fileReader = new FileReader(USER_FILE_NAME);
        Scanner scanner = new Scanner(fileReader);
        while (scanner.hasNext()){
            username = scanner.nextLine();
        }
        fileReader.close();
        return username;
    }
}
