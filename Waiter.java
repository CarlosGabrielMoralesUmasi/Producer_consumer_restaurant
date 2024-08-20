import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Waiter implements Runnable {
    private final BlockingQueue<Order> queue;
    private static int orderId = 0;

    public enum CustomerType {
        REGULAR, VIP, OCCASIONAL
    }

    private final CustomerType customerType;

    public Waiter(BlockingQueue<Order> queue, CustomerType customerType) {
        this.queue = queue;
        this.customerType = customerType;
    }

    @Override
    public void run() {
        try {
            Random random = new Random();
            while (true) {
                Order.OrderType type = Order.OrderType.values()[random.nextInt(Order.OrderType.values().length)];
                int priority = customerType == CustomerType.VIP ? 5 : random.nextInt(4) + 1;
                int size = customerType == CustomerType.VIP ? 2 + random.nextInt(3) : 1; // VIPs pueden hacer pedidos grandes
                boolean isSpecial = customerType == CustomerType.VIP && random.nextBoolean(); // VIPs pueden hacer pedidos especiales

                String details = "Order details: " + (orderId + 1) + " [Priority: " + priority + ", Size: " + size + ", Special: " + isSpecial + "]";
                Order order = new Order(orderId++, details, type, priority, size, isSpecial);
                queue.put(order);
                System.out.println("Waiter " + Thread.currentThread().getName() + " placed order " + order.getOrderId() + " for " + customerType);
                Thread.sleep(random.nextInt(3000) + 1000); // Simula el tiempo de tomar una nueva orden
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
