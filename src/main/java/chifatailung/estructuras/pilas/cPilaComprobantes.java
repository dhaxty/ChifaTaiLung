package chifatailung.estructuras.pilas;

import chifatailung.modelo.Comprobante;

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

    public void pilaVacia(){
        tope = -1;
        for(int i = 0; i <= indMax; i++){
            pila[i] = null;
        }
    }

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

    public boolean insertar(Comprobante valor){
        boolean insertado = false;
        if(valor != null && tope < indMax){
            // incrementa el tope y almacena el elemento
            tope++;
            pila[tope] = valor;
            insertado = true;
        }
        return insertado;
    }

    public Comprobante eliminar(){
        Comprobante valor = null;
        if(tope > -1){
            // obtiene el elemento del tope y decrementa
            valor = pila[tope];
            pila[tope] = null;
            tope--;
        }
        return valor;
    }

    public Comprobante accesar(){
        Comprobante valor = null;
        if(tope > -1){
            valor = pila[tope];
        }
        return valor;
    }

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
    }

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
    }

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
    }
}
