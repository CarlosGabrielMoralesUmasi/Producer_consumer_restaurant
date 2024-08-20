# Simulación de Restaurante con la Técnica Producer-Consumer

Este proyecto es una simulación de un sistema de gestión de órdenes en un restaurante utilizando la técnica Producer-Consumer en Java. La aplicación maneja la creación y procesamiento de órdenes por parte de meseros y chefs especializados, gestionando prioridades, inventario, y capacidad de la cocina.

## Características

- **Productores (Meseros)**: Generan órdenes de clientes con diferentes prioridades (VIP, Regular) y tamaños (órdenes grandes o especiales).
- **Consumidores (Chefs)**: Procesan las órdenes según la especialidad del chef (entrantes, platos principales, postres, bebidas), gestionando el inventario de ingredientes y la capacidad de la cocina.
- **Inventario Dinámico**: Los ingredientes se consumen a medida que se procesan las órdenes, y se reabastecen automáticamente a intervalos regulares.
- **Manejo de Capacidad**: La cocina tiene una capacidad limitada, lo que puede causar que algunas órdenes tengan que esperar hasta que se liberen recursos.

## Requisitos

- **Java 8** o superior debe estar instalado en tu máquina.

## Instalación y Ejecución

1. **Clonar el repositorio (si es necesario):**

    ```bash
    git clone https://github.com/tu-usuario/tu-repositorio.git
    cd tu-repositorio
    ```

2. **Crear los Archivos Java**:

   Asegúrate de que los siguientes archivos `.java` estén presentes en el directorio de trabajo:
   - `Order.java`
   - `Waiter.java`
   - `Chef.java`
   - `Main.java`

3. **Compilar los archivos Java**:

    Abre PowerShell o una terminal, navega al directorio donde están tus archivos `.java`, y compílalos con el siguiente comando:

    ```bash
    javac *.java
    ```

4. **Ejecutar el programa:**

    Después de compilar, ejecuta el programa con el siguiente comando:

    ```bash
    java Main
    ```

## Ejemplo de Salida

Al ejecutar el programa, verás una salida similar a la siguiente en la consola:

Chef Chef-MainCourse cannot process order 0 due to lack of ingredients.
Chef Chef-Dessert cannot process order 1 due to lack of ingredients.
Waiter Waiter-VIP placed order 1 for VIP
Waiter Waiter-Regular placed order 0 for REGULAR
Processing order 3: Order details: 4 [Priority: 5, Size: 4, Special: true] (Type: MAIN_COURSE, Size: 4, Special: true) by Chef-MainCourse
Inventory restocked!
Order 10 is ready to be served!
Chef Chef-MainCourse cannot process order 9 due to lack of ingredients.


### Descripción de la Salida

- **Chef Chef-MainCourse cannot process order...**: Indica que un chef no pudo procesar una orden debido a la falta de ingredientes. La orden se reintentará después de un pequeño retraso.
- **Waiter Waiter-VIP placed order...**: Un mesero ha generado y colocado una nueva orden en la cola de procesamiento.
- **Processing order...**: Un chef ha comenzado a procesar una orden específica. Se muestra el tipo de orden, el tamaño, y si es una orden especial.
- **Inventory restocked!**: El inventario de ingredientes se ha reabastecido automáticamente.
- **Order...is ready to be served!**: Una orden ha sido completada y está lista para ser servida por un mesero.

## Contribuciones

Las contribuciones son bienvenidas. Si tienes ideas para mejorar el sistema o añadir nuevas características, sigue estos pasos:

1. Haz un fork del proyecto.
2. Crea una rama para tu funcionalidad (`git checkout -b feature/nueva-funcionalidad`).
3. Haz commit de tus cambios (`git commit -m 'Añadir nueva funcionalidad'`).
4. Haz push a la rama (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request.

