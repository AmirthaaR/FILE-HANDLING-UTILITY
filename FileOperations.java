package Task1;

import java.io.*;
import java.util.*;

public class FileOperations {

    static final String FILE_NAME = "data.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- File Handling Menu ---");
            System.out.println("1. Write to File");
            System.out.println("2. Read from File");
            System.out.println("3. Modify File");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    writeToFile(scanner);
                    break;
                case 2:
                    readFromFile();
                    break;
                case 3:
                    modifyFile(scanner);
                    break;
                case 4:
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1-4.");
            }
        } while (choice != 4);

        scanner.close();
    }

    // Write to File (Overwrites existing content)
    public static void writeToFile(Scanner scanner) {
        try {
            FileWriter writer = new FileWriter(FILE_NAME);
            System.out.println("Enter text to write (type 'exit' to finish):");
            while (true) {
                String line = scanner.nextLine();
                if (line.equalsIgnoreCase("exit"))
                    break;
                writer.write(line + "\n");
            }
            writer.close();
            System.out.println("Content written to file.");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }

    // Read from File
    public static void readFromFile() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                System.out.println("File not found!");
                return;
            }
            Scanner reader = new Scanner(file);
            System.out.println("\n--- File Content ---");
            while (reader.hasNextLine()) {
                System.out.println(reader.nextLine());
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    // Modify File (Replace a word with another)
    public static void modifyFile(Scanner scanner) {
        System.out.print("Enter word to replace: ");
        String oldWord = scanner.nextLine();
        System.out.print("Enter new word: ");
        String newWord = scanner.nextLine();

        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                System.out.println("File not found!");
                return;
            }

            // Read original content
            Scanner reader = new Scanner(file);
            StringBuilder content = new StringBuilder();
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                content.append(line.replaceAll(oldWord, newWord)).append("\n");
            }
            reader.close();

            // Write modified content
            FileWriter writer = new FileWriter(FILE_NAME);
            writer.write(content.toString());
            writer.close();

            System.out.println("File modified successfully.");
        } catch (IOException e) {
            System.out.println("Error modifying file.");
        }
    }
}
