package presentation;

import domain.Caja;
import domain.Cliente;
import domain.Producto;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal donde se ejecuta el programa
 * @author marcodelboccio
 */
public class Main {
    
    static Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // variable de opcion para switch
        int option = -1;
        long initialTime = System.currentTimeMillis();        
        
        // Listas para guardar datos temporalmente        
        List<Cliente> clientes = new ArrayList<>();
        List<Caja> cajas = new ArrayList<>();
        List <Producto> productos = new ArrayList<>();        
                
        Producto producto1 = new Producto("Manzana", (float) 2.3);
        Producto producto2 = new Producto("Aguacate",(float) 4.6);
        Producto producto3 = new Producto("Cafe",(float) 12.8);
        productos.add(producto1);
        productos.add(producto2);
        productos.add(producto3);        
        
        Cliente cl1 = new Cliente(1, "Marco", productos);
        Cliente cl2 = new Cliente(2, "Anderson", productos); 
        Cliente cl3 = new Cliente(3, "Jennifer", productos);         
        clientes.add(cl1);
        clientes.add(cl2);     
        clientes.add(cl3);
        
        Caja cj1 = new Caja(1, cl1, initialTime);
        Caja cj2 = new Caja(2, cl2, initialTime);  
        Caja cj3 = new Caja(3, cl3, initialTime);         
        cajas.add(cj1);
        cajas.add(cj2);
        cajas.add(cj3);        

        
        while (option != 0) {
            
            // Menú de opciones
            System.out.println("""
                                → Menú
                                     ↳ 1. Iniciar procesos de compra
                                     ↳ 2. Crear cliente
                                     ↳ 3. Leer clientes
                                     ↳ 4. Leer cliente por id
                                     ↳ 5. Crear caja
                                     ↳ 6. Leer cajas activas                          
                                     ↳ 0. Salir
                                """);          
            
            System.out.print("→ Respuesta: ");
            option = sc.nextInt();
            
            // Captura el enter
            sc.nextLine();
            
            // Opciones a escoger
            switch(option) {
                
                // Cierre de la aplicación
                case 0 -> System.out.println("\nCerrando app...\n");
                // Itera sobre los hilos en la lista y los ejecuta
                case 1 -> iniciarHilos(cajas, initialTime);
                // Crear cliente retorna un nuevo cliente, el cual es añadido a la lista
                case 2 -> clientes.add(crearCliente());
                // Admite como parámetro la lista de clientes que contendrá los clientes creados
                case 3 -> leerClientes(clientes);
                // Admite como parámetro la lista de clientes para mostrar uno en específico
                case 4 -> leerCliente(obtenerClienteById(clientes));
                // Crear caja
                case 5 -> crearCaja(cajas, obtenerClienteById(clientes), initialTime);
                case 6 -> leerCajas(cajas);
                default -> System.out.println("- Digite un numero valido...");
    
            }        
                
        }        
        
    }

    // Clientes
    
    /**
     * Muestra todos los clientes que haya en la lista por pantalla
     * @param listaClientes Es la lista en la cual se leen los clientes
     */
    private static void leerClientes(List<Cliente> listaClientes){
        
        System.out.println("\nLista de clientes: \n");
        
        // Validar si la lista está vacía
        if(!listaClientes.isEmpty()){
            
            for (Cliente cliente : listaClientes){            
                System.out.println("→ " + cliente);            
            }
            
        // Si está vacía mostrar un mensaje descriptivo    
        } else {
            System.out.println("→ No hay clientes en la lista...");
        }    
        
        System.out.println("");
      
    }
    
    /**
     * Método para mostrar un cliente por un id
     * @param clientes 
     */
    private static void leerCliente(Cliente cliente) {
        
        // Validar si la lista está vacía
        if(cliente != null){
            System.out.println("");
            System.out.println("→ " + cliente);
            System.out.println("     ↳ Productos: ");
            for (Producto producto : cliente.getProductos()){
                
                System.out.println("          ↳ " + producto);
                
            }
            
        // Si es nulo mostrar un mensaje descriptivo    
        }  
        
        System.out.println("");        
        
    }  
    
    /**
     * 
     * @param listaClientes
     * @return 
     */
    private static Cliente obtenerClienteById(List<Cliente> listaClientes) {
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("\n→ Digite el id del cliente: ");
        int idCliente = sc.nextInt();
        sc.nextLine();
        
        // Validar si la lista está vacía
        if(!listaClientes.isEmpty()){
            
            for (Cliente cliente : listaClientes){ 
                
                if (idCliente == cliente.getId()){
                    
                    return cliente;
                    
                }                
          
            }            

        } 
        
        // Si está vacía mostrar un mensaje descriptivo            
        System.out.println("→ No se encontro el cliente...");
        System.out.println("");
        
        // Retornar nulo en caso de no encontrar el cliente
        return null;        
    }  
        
    /**
     * Crea un nuevo cliente solicitando información al usuario.
     *
     * @return Un objeto Cliente recién creado con la información proporcionada por el usuario.
     */
    private static Cliente crearCliente() {
        System.out.println("");
        
        // Capturar id
        System.out.print("→ Digite id: ");
        int idCliente = sc.nextInt();
        sc.nextLine(); // Capturar enter
        
        // Capturar nombre
        System.out.print("→ Digite nombre: ");
        String nombreCliente = sc.nextLine();
        
        System.out.println("\n→ Que productos comprará el cliente?");
        // setProductos devuelve la lista con los productos seleccionados
        List<Producto> productosCliente = crearProductos();
        
        // Instanciar nuevo cliente
        Cliente nuevoCliente = new Cliente(idCliente, nombreCliente, productosCliente);
        
        //Retornar dicho cliente nuevo
        return nuevoCliente;        
    }
    
    /**
     * Crea productos solicitando informacion al usuario y los añade a una lista
     * @return Una lista de Objeto Producto con los productos creados
     */
    private static List<Producto> crearProductos() {
        
        List<Producto> productosNuevos = new ArrayList<>();
        int option;       
                
        // Se necesita que se cree almenos un producto, por eso doWhile
        do{
            // Capturar nombre
            System.out.print("\n→ Digite nombre del producto: ");
            String nombreProducto = sc.nextLine();

            // Capturar precio
            System.out.print("→ Digite precio del producto: ");
            float precioProducto = sc.nextFloat();

            // Crear nuevo producto y añadirlo a la lista
            Producto productoNuevo = new Producto(nombreProducto, precioProducto);
            productosNuevos.add(productoNuevo);
            
            // Si se presiona 1, el bucle se repite para añadir otro producto
            System.out.println("\n→ Desea añadir otro producto? (1 = si / 0 = no)");            
            System.out.print("\n→ Respuesta: ");
            option = sc.nextInt();            
            sc.nextLine(); // Atrapar el enter para evitar errores
                        
        } while (option != 0);
        
        // Espacion en blanco
        System.out.println("");        
        
        // Se retorna la lista con los nuevos productos del cliente
        return productosNuevos;
        
    }

    /**
     * Método para crear una nueva caja
     * @param listaCajas Donde se añadirá la nueva caja
     * @param cliente El cual se anexará en el nuevo objeto caja
     * @param time El cual se anexará en el nuevo objeto caja
     */
    private static void crearCaja(List<Caja> listaCajas, Cliente cliente, long time) {
        
        // Para que autoincremente
        int idCaja = listaCajas.size() + 1;        
        
        // Instanciar la nueva caja
        Caja cajaNueva = new Caja(idCaja, cliente, time);
        
        // Añadir caja a la lista
        listaCajas.add(cajaNueva);
        
        System.out.println("\n→ Caja creada con éxito...\n");
        
    }
    
    /**
     * Muestra todos los clientes que haya en la lista por pantalla
     * @param listaClientes Es la lista en la cual se leen los clientes
     */
    private static void leerCajas(List<Caja> listaCajas){
        
        System.out.println("\n→ Lista de cajas: \n");
        
        // Validar si la lista está vacía
        if(!listaCajas.isEmpty()){
            
            for (Caja caja : listaCajas){            
                System.out.println(caja);            
            }
            
        // Si está vacía mostrar un mensaje descriptivo    
        } else {
            System.out.println("→ No hay cajas en la lista...");
        }    
        
        System.out.println("");
      
    }    

    private static void iniciarHilos(List<Caja> listaCajas, long time) {
        time = System.currentTimeMillis(); // Reiniciar el tiempo inicial

        System.out.println("");
        
        for (Caja caja : listaCajas) {
            caja.setInitialTime(time); // Actualizar el tiempo inicial para cada caja
            caja.run();
            System.out.println("");
        }
    }
    
}
