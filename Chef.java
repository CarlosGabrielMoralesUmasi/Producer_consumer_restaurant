import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class Chef implements Runnable {
    private final BlockingQueue<Order> queue;
    private final Map<Order.OrderType, Integer> inventory;
    private final Order.OrderType specialty;
    private final int kitchenCapacity; // Capacidad de la cocina
    private int currentOrders = 0; // Contador de pedidos en proceso

    public Chef(BlockingQueue<Order> queue, Map<Order.OrderType, Integer> inventory, Order.OrderType specialty, int kitchenCapacity) {
        this.queue = queue;
        this.inventory = inventory;
        this.specialty = specialty;
        this.kitchenCapacity = kitchenCapacity;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Order order = queue.take();
                if (order.getType() == specialty && checkAndConsumeIngredients(order.getType(), order.getSize())) {
                    synchronized (this) {
                        while (currentOrders >= kitchenCapacity) {
                            wait(); // Espera si la cocina está ocupada
                        }
                        currentOrders++;
                    }
                    order.processOrder();
                    synchronized (this) {
                        currentOrders--;
                        notifyAll(); // Notifica que hay espacio disponible en la cocina
                    }
                    notifyWaiter(order); // Notifica al mesero que el pedido está listo
                } else {
                    System.out.println("Chef " + Thread.currentThread().getName() + " cannot process order " + order.getOrderId() + " due to lack of ingredients.");
                    Thread.sleep(1000); // Espera antes de reinsertar la orden en la cola
                    queue.put(order); // Reinsertar la orden en la cola después de una pausa
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private synchronized boolean checkAndConsumeIngredients(Order.OrderType type, int size) {
        int stock = inventory.getOrDefault(type, 0);
        if (stock >= size) {
            inventory.put(type, stock - size);
            return true;
        }
        return false;
    }

    private void notifyWaiter(Order order) {
        System.out.println("Order " + order.getOrderId() + " is ready to be served!");
    }
}
