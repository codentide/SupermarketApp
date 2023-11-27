package domain;

import java.util.List;

/**
 * Clase que representa al cliente que comprará los productos
 * @author marcodelboccio
 */
public class Cliente {
    
    // Atributos
    private final int id;
    private final String nombre;
    private final List<Producto> productos;
    
    // Constructor
    public Cliente(int id, String nombre, List<Producto> productos) {
        this.id = id;
        this.nombre = nombre;
        this.productos = productos;
    }
    
    // toString
    @Override
    public String toString(){
        
        return "Cliente n°" + this.id + ": " + this.nombre ;
    
    }
    
    // Getters 
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Producto> getProductos() {
        return productos;
    }
    
    
    
    
}
