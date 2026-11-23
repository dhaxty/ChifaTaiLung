package chifatailung.estructuras.colas;

import chifatailung.modelo.ClienteAtencion;

public class cColaPrioridad {
    private cNodoCola first, last, p, nuevo;
    private int cantidad;

    public cColaPrioridad(){
        colaVacia();
    }

    public void colaVacia(){
        first = null;
        last = null;
        cantidad = 0;
    }

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
        boolean vacia = false;
        if(first == null){
            vacia = true;
        }
        return vacia;
    }

    public void incluir(ClienteAtencion valor){
        if(valor != null){
            nuevo = new cNodoCola(valor);

            if(first == null){
                first = nuevo;
                last = nuevo;
            } else {
                // inserta al inicio si tiene mayor prioridad
                if(nuevo.getValor().getPrioridad().getNivel() < first.getValor().getPrioridad().getNivel()){
                    nuevo.setSig(first);
                    first = nuevo;
                } else {
                    p = first;
                    // avanza respetando la prioridad y fifo por nivel
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
        }
    }

    public ClienteAtencion eliminar(){
        ClienteAtencion valor = null;
        if(first != null){
            valor = first.getValor();
            // desvincula el primer nodo y actualiza punteros
            first = first.getSig();
            if(first == null){
                last = null;
            }
            cantidad--;
        }
        return valor;
    }

    public ClienteAtencion acceso(){
        ClienteAtencion valor = null;
        if(first != null){
            valor = first.getValor();
        }
        return valor;
    }

    public ClienteAtencion[] listar(){
        ClienteAtencion resultado[];
        if(first == null || cantidad == 0){
            resultado = new ClienteAtencion[0];
        } else {
            resultado = new ClienteAtencion[cantidad];
            p = first;
            int i = 0;
            while(p != null && i < cantidad){
                resultado[i] = p.getValor();
                p = p.getSig();
                i++;
            }
        }
        return resultado;
    }

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
    }
}
