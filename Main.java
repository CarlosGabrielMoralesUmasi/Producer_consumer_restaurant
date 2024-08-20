import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<Order> queue = new PriorityBlockingQueue<>();
        Map<Order.OrderType, Integer> inventory = new HashMap<>();

        // Inicializar inventario
        inventory.put(Order.OrderType.APPETIZER, 10);
        inventory.put(Order.OrderType.MAIN_COURSE, 5);
        inventory.put(Order.OrderType.DESSERT, 8);
        inventory.put(Order.OrderType.DRINK, 20);

        // Crear meseros y chefs especializados
        Thread waiter1 = new Thread(new Waiter(queue, Waiter.CustomerType.REGULAR), "Waiter-Regular");
        Thread waiter2 = new Thread(new Waiter(queue, Waiter.CustomerType.VIP), "Waiter-VIP");
        Thread chef1 = new Thread(new Chef(queue, inventory, Order.OrderType.MAIN_COURSE, 2), "Chef-MainCourse");
        Thread chef2 = new Thread(new Chef(queue, inventory, Order.OrderType.DESSERT, 2), "Chef-Dessert");

        // Iniciar threads
        waiter1.start();
        waiter2.start();
        chef1.start();
        chef2.start();

        // Reabastecer inventario periódicamente con mayores cantidades
        new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(10000); // Reabastecer cada 10 segundos
                    synchronized (inventory) {
                        inventory.put(Order.OrderType.APPETIZER, inventory.get(Order.OrderType.APPETIZER) + 10);
                        inventory.put(Order.OrderType.MAIN_COURSE, inventory.get(Order.OrderType.MAIN_COURSE) + 5);
                        inventory.put(Order.OrderType.DESSERT, inventory.get(Order.OrderType.DESSERT) + 8);
                        inventory.put(Order.OrderType.DRINK, inventory.get(Order.OrderType.DRINK) + 15);
                        System.out.println("Inventory restocked!");
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
