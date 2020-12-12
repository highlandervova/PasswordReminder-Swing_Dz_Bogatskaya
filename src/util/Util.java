package util;

import data.Record;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Util {
    public static Scanner creatScanner() {
        return new Scanner(System.in);
    }

    public static void addRecord(Record record) {
        try(FileWriter fileWriter = new FileWriter(new File("records.txt"), true)) {
            String newRec = record.getLogin() + " " + record.getPassword() + " " + record.getResource();
            fileWriter.write("\n" + newRec);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Record> showRecordsFromFile(File file) {
        List<Record> records = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                Record record = new Record(scanner.next(), scanner.next(), scanner.next());
                records.add(record);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return records;
    }

}



