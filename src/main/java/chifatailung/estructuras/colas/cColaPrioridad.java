package chifatailung.estructuras.colas;

import chifatailung.modelo.ClienteAtencion;

// cola dinamica con prioridad y orden FIFO estable por nivel segun estilo del profesor
public class cColaPrioridad {
    private cNodoCola first, last, p, nuevo;
    private int cantidad;

    public cColaPrioridad(){
        colaVacia();
    }

    // inicializa o reinicia la cola a estado vacio
    public void colaVacia(){
        first = null;
        last = null;
        cantidad = 0;
    } // fin colaVacia

    public cNodoCola getFirst(){
        return first;
    }

    public cNodoCola getLast(){
        return last;
    }

    public int getCantidad(){
        return cantidad;
    }

    public boolean estaVacia(){
        return first == null;
    }

    // incluye un cliente insertandolo segun prioridad (menor nivel antes) y FIFO estable por ordenLlegada
    public void incluir(ClienteAtencion valor){
        if(valor == null) return;
        nuevo = new cNodoCola(valor);

        if(first == null){
            first = nuevo;
            last = nuevo;
        } else {
            // si el nuevo tiene mayor prioridad que el primero (menor numero de nivel)
            if(nuevo.getValor().getPrioridad().getNivel() < first.getValor().getPrioridad().getNivel()){
                nuevo.setSig(first);
                first = nuevo;
            } else {
                p = first;
                // avanza mientras el siguiente tenga prioridad estrictamente mayor o igual (para respetar FIFO)
                while(p.getSig() != null && p.getSig().getValor().getPrioridad().getNivel() <= nuevo.getValor().getPrioridad().getNivel()){
                    p = p.getSig();
                }
                nuevo.setSig(p.getSig());
                p.setSig(nuevo);
                if(nuevo.getSig() == null){
                    last = nuevo;
                }
            }
        }
        cantidad++;
    } // fin incluir

    // desencola y devuelve el cliente al frente; devuelve null si la cola esta vacia
    public ClienteAtencion eliminar(){
        ClienteAtencion valor = null;
        if(first != null){
            valor = first.getValor();
            first = first.getSig();
            if(first == null){
                last = null;
            }
            cantidad--;
        }
        return valor;
    } // fin eliminar

    // consulta el cliente al frente sin desencolar; retorna null si esta vacia
    public ClienteAtencion acceso(){
        ClienteAtencion valor = null;
        if(first != null){
            valor = first.getValor();
        }
        return valor;
    } // fin acceso

    // devuelve un arreglo exacto con los clientes en orden de atencion para alimentar JTable
    public ClienteAtencion[] listar(){
        if(first == null || cantidad == 0){
            return new ClienteAtencion[0];
        }
        ClienteAtencion resultado[] = new ClienteAtencion[cantidad];
        p = first;
        int i = 0;
        while(p != null && i < cantidad){
            resultado[i] = p.getValor();
            p = p.getSig();
            i++;
        }
        return resultado;
    } // fin listar

    // acumula en cadena los clientes con separador " - " para visualizacion en consola
    public String muestraCola(){
        p = first;
        String cadena = "";
        while(p != null){
            if(p.getValor() != null){
                cadena = cadena + " - [Turno " + p.getValor().getNumeroTurno() + "] "
                        + p.getValor().getNombreCliente() + " ("
                        + p.getValor().getPrioridad().getDescripcion()
                        + ", Llegada #" + p.getValor().getOrdenLlegada() + ")";
            }
            p = p.getSig();
        }
        return cadena;
    } // fin muestraCola
}
