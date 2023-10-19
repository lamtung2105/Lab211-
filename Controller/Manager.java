package Controller;

import Model.Fruit;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * The Manager class controls the business logic of the Fruit Shop application.
 */
public class Manager {

    List<Fruit> fruits = new ArrayList<>();
    Hashtable<String, ArrayList<Fruit>> orders = new Hashtable<>();
    Validate validation = new Validate();

    /**
     * Retrieves a Fruit object by its ID.
     *
     * @param id The ID of the fruit to retrieve.
     * @return The Fruit object if found, or null if not found.
     */
    public Fruit getFruitById(String id) {
        for (Fruit fruit : fruits) {
            if (id.equals(fruit.getFruitId())) {
                return fruit;
            }
        }
        return null;
    }

    /**
     * Creates a new fruit and adds it to the inventory.
     */
    public void createFruit() {
        while (true) {
            String fruitId = validation.inputString("Enter id:", ".+");

            if (getFruitById(fruitId) != null) {
                System.out.println("ID is existed");
                continue;
            }
            String fruitName = validation.inputString("Enter name:", "[A-Za-z\\s]+");
            double price = validation.inputDouble("Enter price:", 1, Double.MAX_VALUE);
            int quantity = validation.inputInt("Enter quantity:", 1, Integer.MAX_VALUE);
            String origin = validation.inputString("Enter origin:", "[A-Za-z\\s]+");
            fruits.add(new Fruit(fruitId, fruitName, price, quantity, origin));
            if (!validation.checkInputYN("Do you want to continue?")) {
                break;
            }
        }
    }

    /**
     * Displays the list of available fruits for shopping.
     *
     * @return The selected item number.
     */
    public int displayListFruit() {
        int countItem = 0;
        if (fruits.isEmpty()) {
            return -1;
        }
        for (Fruit fruit : fruits) {
            if (fruit.getQuantity() != 0) {
                countItem++;
                if (countItem == 1) {
                    System.out.printf("%-10s%-20s%-20s%-15s\n", "Item", "Fruit name", "Origin", "Price");
                }
                System.out.printf("%-10d%-20s%-20s%-15.0f$\n", countItem,
                        fruit.getFruitName(), fruit.getOrigin(),
                        fruit.getPrice());
            }
        }
        if (countItem == 0) {
            return -1;
        }
        int item = validation.inputInt("Enter item:", 1, countItem);
        return item;

    }

    /**
     * Gets the selected fruit from the list.
     *
     * @param item The selected item number.
     * @return The selected Fruit object.
     */
    public Fruit getFruit(int item) {
        int count = 0;
        for (Fruit fruit : fruits) {
            if (fruit.getQuantity() != 0) {
                count++;
            }
            if (item == count) {
                return fruit;
            }
        }
        return null;
    }

    /**
     * Checks if a specific fruit is in the list of ordered items.
     *
     * @param listOrder The list of ordered items.
     * @param id The ID of the fruit to check.
     * @return The Fruit object if found, or null if not found.
     */
    public Fruit checkFruitInOrder(ArrayList<Fruit> listOrder, String id) {
        for (Fruit fruit : listOrder) {
            if (fruit.getFruitId().equalsIgnoreCase(id)) {
                return fruit;
            }
        }
        return null;
    }

    /**
     * Allows customers to shop for fruits, select quantities, and place orders.
     */
    public void shopping() {
        ArrayList<Fruit> listOrder = new ArrayList<>();
        while (true) {
//             Fruit fruit= displayListFruit();
            int item = displayListFruit();
            if (item == -1) {
                System.out.println("Out of stock.");
                return;
            }
            Fruit fruit = getFruit(item);
            System.out.println("You selected:" + fruit.getFruitName());
            int quantity = validation.inputInt("Enter quantity:", 0, fruit.getQuantity());
            fruit.setQuantity(fruit.getQuantity() - quantity);
            Fruit fruitInOrder = checkFruitInOrder(listOrder, fruit.getFruitId());
            if (fruitInOrder != null) {
                fruitInOrder.setQuantity(fruitInOrder.getQuantity() + quantity);
            } else {
                if (quantity != 0) {
                    listOrder.add(new Fruit(fruit.getFruitId(), fruit.getFruitName(), fruit.getPrice(), quantity, fruit.getOrigin()));
                }

            }
            if (!validation.checkInputYN("Do you want to continue?")) {
                break;
            }
        }
        if (listOrder.isEmpty()) {
            System.out.println("No orders");
        } else {
            displayListOrder(listOrder);
            String name = setName();
            orders.put(name, listOrder);
        }

    }

    /**
     * Generates a unique customer name by appending a count to the input name.
     *
     * @return The generated customer name.
     */
    public String setName() {
        String name = validation.inputString("Enter name:", "[A-Za-z\\s]+");
        int count = 0;
        for (String name_key : orders.keySet()) {
            String real_name = name_key.split("#")[0];
            if (name.equals(real_name)) {
                count++;
            }
        }
        return name + "#" + count;
    }

    /**
     * Displays the list of ordered items, their quantities, and the total
     * amount.
     *
     * @param listOrder The list of ordered items.
     */
    public void displayListOrder(ArrayList<Fruit> listOrder) {
        double total = 0;

        System.out.printf("%15s%15s%15s%15s\n", "Product", "Quantity", "Price", "Amount");
        for (Fruit fruit : listOrder) {
            System.out.printf("%15s%15d%15.0f$%15.0f$\n", fruit.getFruitName(),
                    fruit.getQuantity(), fruit.getPrice(),
                    fruit.getPrice() * fruit.getQuantity());
            total += fruit.getPrice() * fruit.getQuantity();
        }
        System.out.println("Total: " + total);
    }

    /**
     * Displays the list of orders made by customers.
     */
    public void viewOrder() {
        if (orders.isEmpty()) {
            System.out.println("No orders");
            return;
        }
        for (String name : orders.keySet()) {
            System.out.println("Customer: " + name.split("#")[0]);
            ArrayList<Fruit> listOrder = orders.get(name);
            displayListOrder(listOrder);
        }

    }

//Just adding some fruit for testing output/debugging
    public void generateFruit() {
        fruits.add(new Fruit("F1", "Chuoi", 2000, 3, "Hanoi"));
        fruits.add(new Fruit("F2", "Buoi", 4000, 6, "Hanoi"));
        fruits.add(new Fruit("F3", "Dua", 5000, 5, "Hanoi"));
    }
}
