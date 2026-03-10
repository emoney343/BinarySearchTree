import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BST tree = new BST();

        tree.insert(new FileEntry("projects", "folder", 0));
        tree.insert(new FileEntry("music", "folder", 0));
        tree.insert(new FileEntry("videos", "folder", 0));
        tree.insert(new FileEntry("beats", "file", 24));

        Scanner scanner = new Scanner(System.in);
        int option = 0;
        
        while (option != 8 ) {
        System.out.println("Select Option: ");
        System.out.println("1: Add file");
        System.out.println("2: Add folder");
        System.out.println("3: Search for file or folder");
        System.out.println("4: Delete file or folder");
        System.out.println("5: Get file or folder at index");
        System.out.println("6: Update file or folder");
        System.out.println("7: List all");
        System.out.println("8: Exit");

        option = scanner.nextInt();
        scanner.nextLine();

        String name;
        String newName;
        String newType;
        long size;
        long newSize;
        int index;

        try {

        switch(option) {

            // Add File
            case 1:
            System.out.print("Enter name of file: ");
            name = scanner.nextLine(); 

            System.out.print("Enter size: ");
            size = scanner.nextLong();
            scanner.nextLine();

            tree.insert(new FileEntry(name, "file", size));
            break;

            // Add Folder
            case 2:
            System.out.print("Enter name of folder: ");
            name = scanner.nextLine(); 

            tree.insert(new FileEntry(name, "folder", 0));
            break;

            // Search for file or folder
            case 3: {
            System.out.print("Enter name of file or folder: ");
            name = scanner.nextLine();

            FileEntry result = tree.search(name);
            if (result != null) {
                System.out.println("Found: " + result.name + " | " + result.type + " | " + result.size + "kb");
            } else {
                System.out.println("File not found.");
            }
            break;
        }


            // Delete file or folder
            case 4:
            System.out.print("Enter name of file or folder: ");
            name = scanner.nextLine();

            tree.delete(name);
            break;
            

            // Get file at index 
            case 5: {
            System.out.print("Enter index: ");
            index = scanner.nextInt();

            FileEntry result = tree.getIndex(index);
            if (result != null) {
                System.out.println("Found: " + result.name + " | " + result.type + " | " + result.size + "kb");
            } else {
                System.out.println("Invalid index.");
            }
            break;
        }


            // Update file or folder
            case 6:
            System.out.print("Enter name of file or folder: ");
            name = scanner.nextLine();

            System.out.print("Enter new name: ");
            newName = scanner.nextLine();
            
            System.out.print("Enter new type: ");
            newType = scanner.nextLine();

            System.out.print("Enter new size: ");
            newSize = scanner.nextLong();
            scanner.nextLine();

            tree.update(name, newName, newType, newSize);
            break;

            // List all files
            case 7:
            tree.inOrder(tree.root);
            break;

            // exit
            case 8:
            System.out.println("Goodbye!");
            break;
            
            

            default:
            System.out.println("Invalid option. Please choose 1-9");

        }
        
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
        System.out.println("Error: " + e.getMessage());
        }



        


        }
        scanner.close();
    }
}
