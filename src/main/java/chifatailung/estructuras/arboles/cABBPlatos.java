package chifatailung.estructuras.arboles;

import chifatailung.modelo.Plato;

// arbol binario de busqueda para platos ordenados alfabeticamente por nombre (adaptado de cABB del profesor)
public class cABBPlatos {
    private cNodoABB raiz, nuevo, p;

    public cABBPlatos(){
        raiz = null;
    }

    public cNodoABB getRaiz(){
        return raiz;
    }

    public void arbolVacio(){
        raiz = null;
    }

    public boolean estaVacio(){
        return raiz == null;
    }

    // inserta un plato verificando que no exista duplicado por nombre ignorando mayusculas y minusculas
    public boolean ingresarValor(Plato valor){
        if(valor == null || valor.getNombre() == null || valor.getNombre().trim().isEmpty()){
            return false;
        }

        // rechazo silencioso de duplicados segun seccion 5
        if(buscarIndexada(valor.getNombre()) != null){
            return false;
        }

        nuevo = new cNodoABB(valor);
        if(raiz == null){
            raiz = nuevo;
        } else {
            p = raiz;
            insertaNodo(p, nuevo);
        }
        return true;
    }

    // insercion recursiva segun estilo del profesor con compareToIgnoreCase
    public void insertaNodo(cNodoABB p, cNodoABB nuevo){
        if (p == null)
            p = nuevo;
        else{
            if(nuevo.getValor().getNombre().compareToIgnoreCase(p.getValor().getNombre()) > 0){
                if(p.getDer() == null)
                    p.setDer(nuevo);
                else insertaNodo(p.getDer(), nuevo);
            }
            else{
                if(p.getIzq() == null)
                    p.setIzq(nuevo);
                else insertaNodo(p.getIzq(), nuevo);
            }
        }
    }  // fin inserta nodo

    // busqueda secuencial recursiva que recorre todos los nodos sin aprovechar el orden del arbol
    public Plato buscarSecuencial(String nombre){
        if(nombre == null || raiz == null){
            return null;
        }
        return buscarSecuencialRecursivo(raiz, nombre.trim());
    }

    private Plato buscarSecuencialRecursivo(cNodoABB p, String nombre){
        if(p == null){
            return null;
        }
        if(p.getValor() != null && p.getValor().getNombre() != null 
                && p.getValor().getNombre().equalsIgnoreCase(nombre)){
            return p.getValor();
        }
        Plato encontrado = buscarSecuencialRecursivo(p.getIzq(), nombre);
        if(encontrado != null){
            return encontrado;
        }
        return buscarSecuencialRecursivo(p.getDer(), nombre);
    }

    // busqueda indexada (binaria) recursiva que desciende por izquierda (<0) o derecha (>0)
    public Plato buscarIndexada(String nombre){
        if(nombre == null || raiz == null){
            return null;
        }
        return buscarIndexadaRecursivo(raiz, nombre.trim());
    }

    private Plato buscarIndexadaRecursivo(cNodoABB p, String nombre){
        if(p == null){
            return null;
        }
        int cmp = nombre.compareToIgnoreCase(p.getValor().getNombre());
        if(cmp == 0){
            return p.getValor();
        } else if(cmp < 0){
            return buscarIndexadaRecursivo(p.getIzq(), nombre);
        } else {
            return buscarIndexadaRecursivo(p.getDer(), nombre);
        }
    }

    // cuenta de nodos de forma recursiva
    public int contar(){
        return contarNodos(raiz);
    }

    public int contarNodos(cNodoABB p){
        if(p == null){
            return 0;
        }
        return 1 + contarNodos(p.getIzq()) + contarNodos(p.getDer());
    }

    // devuelve arreglo nativo con los platos ordenados alfabeticamente para alimentar JTable
    public Plato[] enOrden(){
        int total = contar();
        Plato arreglo[] = new Plato[total];
        llenarEnOrden(raiz, arreglo, 0);
        return arreglo;
    }

    private int llenarEnOrden(cNodoABB p, Plato arreglo[], int indice){
        if(p != null){
            indice = llenarEnOrden(p.getIzq(), arreglo, indice);
            arreglo[indice++] = p.getValor();
            indice = llenarEnOrden(p.getDer(), arreglo, indice);
        }
        return indice;
    }

    // recorridos en String exactamente al estilo del Apendice A.4
    public String recorreArbol(int opcion){
        p = raiz; String cadena="";
        switch(opcion){
            case 1:cadena=preOrden(p);break;
            case 2:cadena=inOrden(p);break;
            case 3:cadena=postOrden(p);break;
        }
        return cadena;
    }

    public String preOrden(cNodoABB p){
        String cadena="";
        if(p != null){
            cadena = cadena + " - " + p.getValor().getNombre();
            cadena = cadena + preOrden(p.getIzq());
            cadena = cadena + preOrden(p.getDer());
        }
        return cadena;
    }

    public String inOrden(cNodoABB p){
        String cadena="";
        if(p != null){
            cadena = cadena + inOrden(p.getIzq());
            cadena = cadena + " - " + p.getValor().getNombre();
            cadena = cadena + inOrden(p.getDer());
        }
        return cadena;
    }

    public String postOrden(cNodoABB p){
        String cadena="";
        if(p != null){
            cadena = cadena + postOrden(p.getIzq());
            cadena = cadena + postOrden(p.getDer());
            cadena = cadena + " - " + p.getValor().getNombre();
        }
        return cadena;
    }
}
