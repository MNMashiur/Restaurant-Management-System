import java.io.*;
import java.util.Scanner;
import java.net.*;

public class Admin {
    public static void main(String[] args) throws IOException {
        Scanner in= new Scanner (System.in);
        File folder = new File("E:/Java/Restaurant Management System/ad");
        folder.mkdir();

        File employee = new File(folder.getAbsolutePath() + "/Employee.txt");
        File client = new File(folder.getAbsolutePath() + "/Client.txt");
        File maintenance = new File(folder.getAbsolutePath() + "/Maintenance Details.txt");
        File stock = new File(folder.getAbsolutePath() + "/Stock Details.txt");

        try {
            if (!employee.exists()) {
                employee.createNewFile();

            }
            if (!client.exists()) {
                client.createNewFile();
            }
            if (!maintenance.exists()) {
                maintenance.createNewFile();
            }
            PrintWriter writer = new PrintWriter(employee); // Use FileWriter here
            writer.println("Name\t\t\t\tSalary\t\t\tDesignation\t\tJoined Date"); // Added tabs for formatting
            writer.println("1. Maksudul Alam\t85,500\t\tManaging Director\tJuly 18, 2023");
            writer.println("2. Zaber Ali\t\t75,500\t\t\tManager\t\t\tJuly 18, 2023");
            writer.println("3. Titu Hossain\t\t65,500\t\t\tChief Chef\t\tJuly 18, 2023");

            writer.close();

            PrintWriter writer1 = new PrintWriter(client); // Use FileWriter here
            writer1.println("Name\t\t\t\tVisiting Date\t\tAmount of Order"); // Added tabs for formatting
            writer1.println("1. Maksudul Alam\tJuly 18, 2023\t\t3500/=");
            writer1.println("2. Zaber Ali\t\tJuly 18, 2023\t\t4500/=");
            writer1.println("3. Titu Hossain\t\tJuly 18, 2023\t\t5500/=");

            writer1.close();

            PrintWriter writer2 = new PrintWriter(maintenance); // Use FileWriter here
            writer2.println("Fields\t\t\t\tBilling Date\t\tBill Amount"); // Added tabs for formatting
            writer2.println("1. Water\t\t\tJuly 18, 2023\t\t3500/=");
            writer2.println("2. Electricity\t\tJuly 18, 2023\t\t4500/=");
            writer2.println("3. Gas\t\t\t\tJuly 18, 2023\t\t5500/=");

            writer2.close();

            PrintWriter writer3 = new PrintWriter(stock); // Use FileWriter here
            writer3.println("Ingredients\t\t\t\tAmount"); // Added tabs for formatting
            writer3.println("1. Oil\t\t\t\t\t35 Litre");
            writer3.println("2. Rice\t\t\t\t\t4500 kg");
            writer3.println("3. Vegetables\t\t\t2 ton");

            writer3.close();

        }catch(IOException e) {
            e.printStackTrace();
        }

        String correctUsername = "admin";
        String correctPassword = "password";

        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\tWelcome to the Restaurant Admin Panel\t\t\t\t\t\t\t\t\t\t\t\t\t");

        // User authentication
        System.out.print("Enter username: ");
        String enteredUsername = in.nextLine();

        System.out.print("Enter password: ");
        String enteredPassword = in.nextLine();

        if (enteredUsername.equals(correctUsername) && enteredPassword.equals(correctPassword)) {
            System.out.println("Authentication successful. You have access to admin fields.");
            boolean continueProgram = true;
            while (continueProgram) {
                System.out.println("Options: ");
                System.out.println("1. Access employee details \n" +
                        "2. Client details\n" +
                        "3. Maintenance details \n" +
                        "4. Stock Details \n" +
                        "5. Chat \n" +
                        "6. Exit \n");
                System.out.print("Enter your choice: ");
                int n = in.nextInt();

                if (n == 1) {
                    Scanner e = new Scanner(employee);

                    while (e.hasNextLine()) {
                        String line = e.nextLine();
                        System.out.println(line);
                    }
                    e.close();
                }
                else if (n == 2) {
                    Scanner c = new Scanner(client);

                    while (c.hasNextLine()) {
                        String na = c.nextLine();// Added tab separation
                        System.out.println(na);
                    }
                    c.close();
                }
                else if (n == 3) {
                    Scanner m = new Scanner(maintenance);

                    while (m.hasNextLine()) {
                        String ma = m.nextLine();// Added tab separation
                        System.out.println(ma);
                    }
                    m.close();
                }
                else if (n == 4) {
                    Scanner s = new Scanner(stock);

                    while (s.hasNextLine()) {
                        String na = s.nextLine();// Added tab separation
                        System.out.println(na);
                    }
                    s.close();
                }
                else if (n == 5) {
                    ServerSocket server = new ServerSocket(65306);
                    Socket socket = server.accept();
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    try {
                        for (int i = 0; ; i++) {
                            String kotha = (String) ois.readObject();
                            if(kotha!=" ") {
                                System.out.println("Client: " + kotha);
                            }
                            System.out.print("Server: ");
                            oos.writeObject(in.nextLine());
                        }
                    }catch(ClassNotFoundException e){
                        e.printStackTrace();
                    }
                }



                else if (n == 6) {
                    System.out.println("Exiting the program.");
                    continueProgram = false;
                    break;
                }
                System.out.println("\n");
            }
        }
        else {
            System.out.println("Access Denied");
        }
    }
}
