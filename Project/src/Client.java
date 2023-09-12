import java.util.*;
import java.io.*;
import java.net.*;


class MenuItem {
    private String name;
    private String description;
    private double price;

    public MenuItem(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}

class OrderItem {
    private MenuItem menuItem;
    private int quantity;

    public OrderItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public double calculateTotal() {
        return menuItem.getPrice() * quantity;
    }
}

public class Client {
    public static void main(String[] args) throws IOException{

        Scanner scanner = new Scanner(System.in);


        List<MenuItem> menu = new ArrayList<>();
        List<OrderItem> order = new ArrayList<>();
        initializeMenu(menu);


        System.out.println("Welcome to the restaurant!");
        int choice;
        do {
            System.out.println("Select an option:");
            System.out.println("1. Show Menu");
            System.out.println("2. Place Order");
            System.out.println("3. Show Bill");
            System.out.println("4. Chat");
            System.out.println("0. Exit");
            choice = scanner.nextInt();

            if (choice == 1) {
                displayMenu(menu);
            } else if (choice == 2) {
                takeOrder(menu, order, scanner);
            } else if (choice == 3) {
                printBill(order);
            } else if (choice == 4) {


                    Socket socket = new Socket("127.0.0.1",65306);

                    System.out.println("You are connected to the server");
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                try {
                    for (int i = 0; ; i++) {
                        System.out.print("Client: ");
                        oos.writeObject(scanner.nextLine());
                        String kotha=(String)ois.readObject();
                        if(kotha!=" ")
                            System.out.println("Server: " + kotha);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (choice == 0) {
                System.out.println("Thank you for dining with us!");
            } else {
                System.out.println("Invalid choice. Please select a valid option.");
            }
        } while (choice != 0);

        scanner.close();
    }

    public static void initializeMenu(List<MenuItem> menu) {
        menu.add(new MenuItem("Burger", "Juicy beef burger", 8.99));
        menu.add(new MenuItem("Pizza", "Delicious cheese pizza", 10.99));
        menu.add(new MenuItem("Salad", "Fresh garden salad", 6.99));
    }

    public static void displayMenu(List<MenuItem> menu) {
        System.out.println("Menu:");
        int itemNumber = 1;
        for (MenuItem item : menu) {
            System.out.println(itemNumber + ". " + item.getName() + " - " + item.getDescription() + " ($" + item.getPrice() + ")");
            itemNumber++;
        }
    }

    public static void takeOrder(List<MenuItem> menu, List<OrderItem> order, Scanner scanner) {
        displayMenu(menu);

        System.out.println("Enter the number of the item you want to order (0 to finish):");
        int choice;
        do {
            choice = scanner.nextInt();

            if (choice == 0) {
                break;
            } else if (choice >= 1 && choice <= menu.size()) {
                MenuItem selectedMenuItem = menu.get(choice - 1);
                System.out.println("Enter the quantity:");
                int quantity = scanner.nextInt();

                if (quantity > 0) {
                    order.add(new OrderItem(selectedMenuItem, quantity));
                    System.out.println("Item added to your order.");
                } else {
                    System.out.println("Invalid quantity. Please enter a positive number.");
                }
            } else {
                System.out.println("Invalid choice. Please select a valid item number.");
            }
        } while (choice != 0);
    }

    public static void printBill(List<OrderItem> order) {
        System.out.println("Your Order:");
        double total = 0.0;
        for (OrderItem item : order) {
            MenuItem menuItem = item.getMenuItem();
            int quantity = item.getQuantity();
            double itemTotal = item.calculateTotal();
            System.out.println(menuItem.getName() + " x" + quantity + " - $" + itemTotal);
            total += itemTotal;
        }
        System.out.println("Total: $" + total);
    }
}
