import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FileManager {
    private static final String FOLDER_NAME = "project_files"; //name of folder, where files are stored
    private static final String ROOT_PATH = System.getProperty("user.dir") + File.separator + FOLDER_NAME; //path to a folder above

    public static void main(String[] args) {
        Scanner menu = new Scanner(System.in);

        while (true) {
            System.out.println("File Manager Menu:");
            System.out.println("1. List files in ascending order");
            System.out.println("2. File Operations");
            System.out.println("3. Exit");
            
            	try {
            		int choice = menu.nextInt();
            		switch (choice) {
            			case 1:
            				listFilesAscending(); //list of all files in ascending order, defined later
            			break;
            			case 2:
            				fileOperations(menu); //opening new menu with file operations
            			break;
            			case 3:
            				System.out.println("Exiting the application.");
            				menu.close();
            				System.exit(0);
            			default:
            				System.out.println("Invalid choice. Please try again."); //when input isn't 1-3
            		}
            	}
            catch(InputMismatchException e) {
                System.err.println("Wrong input! Input only  numbers please. Returned to main menu.");
                menu.nextLine();
            		}
            	
            }
    }

    private static void listFilesAscending() { //defining choice number 1, files in ascending order
        File folder = new File(ROOT_PATH);
        File[] files = folder.listFiles();

        if (files == null || files.length == 0) { //when no files exist
            System.out.println("No files found in the folder.");
            return;
        }

        List<String> fileNames = new ArrayList<>();
        for (File file : files) { //adding files to list until all of them are added
            fileNames.add(file.getName());
        }

        Collections.sort(fileNames);

        System.out.println("Files in ascending order:");
        for (String fileName : fileNames) { 
            System.out.println(fileName); //output of all files in ascending order
        }
    }

    private static void fileOperations(Scanner subMenu) { //defining choice number 2, file operations menu
        while (true) {
            System.out.println("File Operations Menu:");
            System.out.println("1. Add a file");
            System.out.println("2. Delete a file");
            System.out.println("3. Search for a file");
            System.out.println("4. Back to main menu");

            int choice = subMenu.nextInt();
            subMenu.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addFile(subMenu); //add file to a folder
                    break;
                case 2:
                    deleteFile(subMenu); //delete file from a folder
                    break;
                case 3:
                    searchFile(subMenu); //search for a file in a folder
                    break;
                case 4:
                    return; //return back to previous menu
                default:
                    System.out.println("Invalid choice. Please try again."); //when input isn't 1-4
            }
        }
    }

    private static void addFile(Scanner scanner) {
        System.out.print("Enter the name of the file to add: ");
        String fileName = scanner.nextLine();
        File file = new File(ROOT_PATH, fileName); //adding a file to a defined folder

        try {
            if (file.createNewFile()) {
                System.out.println("File created successfully."); 
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file."); //exception when there is an error (e.g. folder doesn't exist)
        }
    }

    private static void deleteFile(Scanner scanner) {
        System.out.print("Enter the name of the file to delete: ");
        String fileName = scanner.nextLine();
        File file = new File(ROOT_PATH, fileName);

        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("Failed to delete the file.");
            }
        } else {
            System.out.println("File not found.");
        }
    }

    private static void searchFile(Scanner scanner) {
        System.out.print("Enter the name of the file to search for: ");
        String fileName = scanner.nextLine();
        File file = new File(ROOT_PATH, fileName);

        if (file.exists()) {
            System.out.println("File found: " + file.getAbsolutePath()); //output is a path to a searched file
        } else {
            System.out.println("File not found.");
        }
    }
}
