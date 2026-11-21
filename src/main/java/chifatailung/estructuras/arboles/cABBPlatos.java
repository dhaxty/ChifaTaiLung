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
        boolean vacio = false;
        if(raiz == null){
            vacio = true;
        }
        return vacio;
    }

    // inserta un plato verificando que no exista duplicado por nombre ignorando mayusculas y minusculas
    public boolean ingresarValor(Plato valor){
        boolean ingresado = false;
        if(valor != null && valor.getNombre() != null && !valor.getNombre().trim().isEmpty()){
            // rechazo silencioso de duplicados segun seccion 5
            if(buscarIndexada(valor.getNombre()) == null){
                nuevo = new cNodoABB(valor);
                if(raiz == null){
                    raiz = nuevo;
                } else {
                    p = raiz;
                    insertaNodo(p, nuevo);
                }
                ingresado = true;
            }
        }
        return ingresado;
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
        Plato encontrado = null;
        if(nombre != null && raiz != null){
            encontrado = buscarSecuencialRecursivo(raiz, nombre.trim());
        }
        return encontrado;
    }

    private Plato buscarSecuencialRecursivo(cNodoABB p, String nombre){
        Plato encontrado = null;
        if(p != null){
            if(p.getValor() != null && p.getValor().getNombre() != null 
                    && p.getValor().getNombre().equalsIgnoreCase(nombre)){
                encontrado = p.getValor();
            } else {
                encontrado = buscarSecuencialRecursivo(p.getIzq(), nombre);
                if(encontrado == null){
                    encontrado = buscarSecuencialRecursivo(p.getDer(), nombre);
                }
            }
        }
        return encontrado;
    }

    // busqueda indexada (binaria) recursiva que desciende por izquierda (<0) o derecha (>0)
    public Plato buscarIndexada(String nombre){
        Plato encontrado = null;
        if(nombre != null && raiz != null){
            encontrado = buscarIndexadaRecursivo(raiz, nombre.trim());
        }
        return encontrado;
    }

    private Plato buscarIndexadaRecursivo(cNodoABB p, String nombre){
        Plato encontrado = null;
        if(p != null){
            int cmp = nombre.compareToIgnoreCase(p.getValor().getNombre());
            if(cmp == 0){
                encontrado = p.getValor();
            } else if(cmp < 0){
                encontrado = buscarIndexadaRecursivo(p.getIzq(), nombre);
            } else {
                encontrado = buscarIndexadaRecursivo(p.getDer(), nombre);
            }
        }
        return encontrado;
    }

    // cuenta de nodos de forma recursiva
    public int contar(){
        int total = contarNodos(raiz);
        return total;
    }

    public int contarNodos(cNodoABB p){
        int total = 0;
        if(p != null){
            total = 1 + contarNodos(p.getIzq()) + contarNodos(p.getDer());
        }
        return total;
    }

    // devuelve arreglo nativo con los platos ordenados alfabeticamente para alimentar JTable
    public Plato[] enOrden(){
        int total = contar();
        Plato arreglo[] = new Plato[total];
        llenarEnOrden(raiz, arreglo, 0);
        return arreglo;
    }

    private int llenarEnOrden(cNodoABB p, Plato arreglo[], int indice){
        int nuevoIndice = indice;
        if(p != null){
            nuevoIndice = llenarEnOrden(p.getIzq(), arreglo, nuevoIndice);
            arreglo[nuevoIndice++] = p.getValor();
            nuevoIndice = llenarEnOrden(p.getDer(), arreglo, nuevoIndice);
        }
        return nuevoIndice;
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
