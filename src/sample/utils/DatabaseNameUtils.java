package sample.utils;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DatabaseNameUtils {
    public final static String DATABASE_FILE_NAME = "UserDatabase.dat";
    private String username;

    public void MakeNewDatabaseFile() throws IOException {
        File file = new File(DATABASE_FILE_NAME);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public void saveDatabaseURL(String databaseURL) throws IOException {
        FileWriter fileWriter = new FileWriter(DATABASE_FILE_NAME);
        fileWriter.write(databaseURL);
        fileWriter.close();

    }

    public String getDatabaseURL() throws IOException {
        FileReader fileReader = new FileReader(DATABASE_FILE_NAME);
        Scanner scanner = new Scanner(fileReader);
        while (scanner.hasNext()){
            username = scanner.nextLine();
        }
        fileReader.close();
        return username;
    }
}
