public class Order implements Comparable<Order> {
    private final int orderId;
    private final String details;
    private final OrderType type;
    private final int priority;
    private final int size; // Tamaño del pedido (1 = normal, >1 = grande)
    private final boolean isSpecial; // Indica si es un pedido especial

    public enum OrderType {
        APPETIZER, MAIN_COURSE, DESSERT, DRINK
    }

    public Order(int orderId, String details, OrderType type, int priority, int size, boolean isSpecial) {
        this.orderId = orderId;
        this.details = details;
        this.type = type;
        this.priority = priority;
        this.size = size;
        this.isSpecial = isSpecial;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getDetails() {
        return details;
    }

    public OrderType getType() {
        return type;
    }

    public int getPriority() {
        return priority;
    }

    public int getSize() {
        return size;
    }

    public boolean isSpecial() {
        return isSpecial;
    }

    @Override
    public int compareTo(Order other) {
        return Integer.compare(other.getPriority(), this.getPriority()); // Ordenar por prioridad
    }

    public void processOrder() {
        System.out.println("Processing order " + orderId + ": " + details + " (Type: " + type + ", Size: " + size + ", Special: " + isSpecial + ") by " + Thread.currentThread().getName());
        try {
            Thread.sleep((type == OrderType.MAIN_COURSE ? 3000 : 2000) * size); // Tiempo de procesamiento según el tamaño
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
