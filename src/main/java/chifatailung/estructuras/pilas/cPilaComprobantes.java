package chifatailung.estructuras.pilas;

import chifatailung.modelo.Comprobante;

// pila estatica sobre arreglo de comprobantes siguiendo el estilo cPila del profesor
public class cPilaComprobantes {
    private Comprobante pila[];
    private int tope;
    private int indMax;

    public cPilaComprobantes(){
        this(50);
    }

    public cPilaComprobantes(int n){
        pila = new Comprobante[n];
        indMax = n - 1;
        pilaVacia();
    }

    // restablece el tope en -1 y limpia las referencias a objetos
    public void pilaVacia(){
        tope = -1;
        for(int i = 0; i <= indMax; i++){
            pila[i] = null;
        }
    } // fin pilaVacia

    public int getTope(){
        return tope;
    }

    public int getCapacidad(){
        return indMax + 1;
    }

    public int getCantidad(){
        return tope + 1;
    }

    public boolean estaVacia(){
        boolean vacia = false;
        if(tope == -1){
            vacia = true;
        }
        return vacia;
    }

    public boolean estaLlena(){
        boolean llena = false;
        if(tope >= indMax){
            llena = true;
        }
        return llena;
    }

    // apila si no excede indMax; devuelve true si pudo apilar y false si esta llena
    public boolean insertar(Comprobante valor){
        boolean insertado = false;
        if(valor != null && tope < indMax){
            tope++;
            pila[tope] = valor;
            insertado = true;
        }
        return insertado;
    } // fin insertar

    // desapila el comprobante del tope y lo devuelve; retorna null si la pila esta vacia
    public Comprobante eliminar(){
        Comprobante valor = null;
        if(tope > -1){
            valor = pila[tope];
            pila[tope] = null;
            tope--;
        }
        return valor;
    } // fin eliminar

    // consulta el comprobante en la cima sin desapilar; retorna null si esta vacia
    public Comprobante accesar(){
        Comprobante valor = null;
        if(tope > -1){
            valor = pila[tope];
        }
        return valor;
    } // fin accesar

    // busca secuencialmente un comprobante por su codigo o numero identificador
    public Comprobante buscar(String numero){
        Comprobante encontrado = null;
        if(numero != null && tope > -1){
            int i = 0;
            while(i <= tope && encontrado == null){
                if(pila[i] != null && pila[i].getNumero() != null && pila[i].getNumero().equalsIgnoreCase(numero.trim())){
                    encontrado = pila[i];
                }
                i++;
            }
        }
        return encontrado;
    } // fin buscar

    // devuelve un arreglo exacto con los comprobantes activos para alimentar JTable
    public Comprobante[] listar(){
        Comprobante resultado[];
        if(tope == -1){
            resultado = new Comprobante[0];
        } else {
            resultado = new Comprobante[tope + 1];
            for(int i = 0; i <= tope; i++){
                resultado[i] = pila[i];
            }
        }
        return resultado;
    } // fin listar

    // acumula en cadena los comprobantes con separador " - " para visualizacion en consola
    public String muestraArreglo(){
        String cadena = "";
        for(int i = 0; i <= tope; i++){
            if(pila[i] != null){
                cadena = cadena + " - [" + (i + 1) + "] " + pila[i].getNumero()
                        + " (" + pila[i].getTipo() + ", " + pila[i].getConcepto()
                        + ", S/." + String.format(java.util.Locale.US, "%.2f", pila[i].getImporte()) + ")";
            }
        }
        return cadena;
    } // fin muestraArreglo
}
