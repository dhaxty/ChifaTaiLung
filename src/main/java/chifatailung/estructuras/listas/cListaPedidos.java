package chifatailung.estructuras.listas;

import chifatailung.modelo.DetallePedido;
import chifatailung.modelo.Pedido;

// lista enlazada simple 100% iterativa para pedidos segun estilo del profesor
public class cListaPedidos {
    private cNodoPedido primero, p, nuevo;
    private int cantidad;

    public cListaPedidos(){
        listaVacia();
    }

    // inicializa o reinicia la lista a vacia
    public void listaVacia(){
        primero = null;
        cantidad = 0;
    } // fin listaVacia

    public cNodoPedido getPrimero(){
        return primero;
    }

    public int getCantidad(){
        return cantidad;
    }

    public boolean estaVacia(){
        return primero == null;
    }

    // inserta un nuevo pedido al final de la lista de forma iterativa validando unicidad de numero
    public boolean insertar(Pedido valor){
        if(valor == null) return false;
        if(valor.getNumero() == null || valor.getNumero().trim().isEmpty()) return false;
        if(buscar(valor.getNumero().trim()) != null) return false;

        nuevo = new cNodoPedido(valor);
        if(primero == null){
            primero = nuevo;
        } else {
            p = primero;
            while(p.getSig() != null){
                p = p.getSig();
            }
            p.setSig(nuevo);
        }
        cantidad++;
        return true;
    } // fin insertar

    // busca por identificador y actualiza cliente, mesa y detalles sin alterar el numero original
    public boolean actualizar(String id, Pedido datosNuevos){
        if(id == null || datosNuevos == null) return false;
        p = primero;
        while(p != null){
            if(p.getValor() != null && p.getValor().getNumero() != null
                    && p.getValor().getNumero().equalsIgnoreCase(id.trim())){
                p.getValor().setNombreCliente(datosNuevos.getNombreCliente());
                p.getValor().setNumeroMesa(datosNuevos.getNumeroMesa());

                DetallePedido nuevosDetalles[] = datosNuevos.getDetalles();
                int totalNuevos = datosNuevos.getCantidadDetalles();
                for(int i = 0; i < 10; i++){
                    if(i < totalNuevos){
                        p.getValor().getDetalles()[i] = nuevosDetalles[i];
                    } else {
                        p.getValor().getDetalles()[i] = null;
                    }
                }
                p.getValor().setCantidadDetalles(totalNuevos);
                return true;
            }
            p = p.getSig();
        }
        return false;
    } // fin actualizar

    // elimina el pedido coincidente reacomodando enlaces de forma iterativa
    public boolean eliminar(String id){
        if(primero == null || id == null) return false;

        if(primero.getValor() != null && primero.getValor().getNumero() != null
                && primero.getValor().getNumero().equalsIgnoreCase(id.trim())){
            primero = primero.getSig();
            cantidad--;
            return true;
        }

        cNodoPedido ant = primero;
        p = primero.getSig();
        while(p != null){
            if(p.getValor() != null && p.getValor().getNumero() != null
                    && p.getValor().getNumero().equalsIgnoreCase(id.trim())){
                ant.setSig(p.getSig());
                cantidad--;
                return true;
            }
            ant = p;
            p = p.getSig();
        }
        return false;
    } // fin eliminar

    // busqueda iterativa por identificador que retorna el pedido o null
    public Pedido buscar(String id){
        if(id == null || primero == null) return null;
        p = primero;
        while(p != null){
            if(p.getValor() != null && p.getValor().getNumero() != null
                    && p.getValor().getNumero().equalsIgnoreCase(id.trim())){
                return p.getValor();
            }
            p = p.getSig();
        }
        return null;
    } // fin buscar

    // devuelve un arreglo exacto Pedido[] con los pedidos registrados para JTable
    public Pedido[] listar(){
        if(primero == null || cantidad == 0){
            return new Pedido[0];
        }
        Pedido resultado[] = new Pedido[cantidad];
        p = primero;
        int i = 0;
        while(p != null && i < cantidad){
            resultado[i] = p.getValor();
            p = p.getSig();
            i++;
        }
        return resultado;
    } // fin listar

    // calcula el total general acumulando iterativamente el total de cada pedido
    public double totalGeneral(){
        double total = 0.0;
        p = primero;
        while(p != null){
            if(p.getValor() != null){
                total += p.getValor().calcularTotal();
            }
            p = p.getSig();
        }
        return total;
    } // fin totalGeneral

    // acumula en cadena los pedidos con separador " - " para visualizacion en consola
    public String muestraLista(){
        p = primero;
        String cadena = "";
        while(p != null){
            if(p.getValor() != null){
                cadena = cadena + " - [Pedido " + p.getValor().getNumero() + "] "
                        + p.getValor().getNombreCliente() + " (Mesa "
                        + p.getValor().getNumeroMesa() + ", "
                        + p.getValor().getCantidadDetalles() + " items, Total: S/ "
                        + String.format(java.util.Locale.US, "%.2f", p.getValor().calcularTotal()) + ")";
            }
            p = p.getSig();
        }
        return cadena;
    } // fin muestraLista
}
