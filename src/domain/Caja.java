package domain;

/**
 * Clase que procesa las compras, implementa de Runnable
 * @author marcodelboccio
 */
public class Caja implements Runnable {
    
    // Atributos
    private int id;
    private Cliente cliente;
    private long initialTime;

    // Constructor
    public Caja(int id, Cliente cliente, long initialTime) {
        this.id = id;
        this.cliente = cliente;
        this.initialTime = initialTime;
    }
    
    @Override
    public void run(){
        float facturacion = 0;
        
        System.out.println("→ La caja n°" + this.id + " ha empezado a procesar "
                + "la compra del cliente " + this.cliente.getNombre() 
                + " → " + (System.currentTimeMillis() - this.initialTime) / 1000 
                + " segundos");
        
        for (Producto p : this.cliente.getProductos()) {
            
            esperarSegundos();
            
            facturacion += p.getPrecio();
            
            System.out.println("     ↳ "
            + p.getNombre() + ", valor: " + p.getPrecio()
            + " → " + (System.currentTimeMillis() - this.initialTime) / 1000 + " segundos");  
            
        }
        
        System.out.println("     ↳ proceso de facturación terminada, el total fue de " + facturacion + "$"
        + " → " + (System.currentTimeMillis() - this.initialTime) / 1000 
        + " segundos");            
        
    }
    
    private void esperarSegundos() {
        try {
            Thread.sleep(1000);                
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }    
    }
    
    @Override
    public String toString(){
        
        return "→ Caja n°: " + this.id 
             + "\n     ↳ " + this.cliente ;
        
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public long getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(long initialTime) {
        this.initialTime = initialTime;
    }


    
    
    
    
    
    
}
