package chifatailung.estructuras.arboles;

import chifatailung.modelo.Plato;

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

    public boolean ingresarValor(Plato valor){
        boolean ingresado = false;
        if(valor != null && valor.getNombre() != null && !valor.getNombre().trim().isEmpty()){
            // rechaza nombres duplicados
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

    public void insertaNodo(cNodoABB p, cNodoABB nuevo){
        if (p == null)
            p = nuevo;
        else{
            if(nuevo.getValor().getNombre().compareToIgnoreCase(p.getValor().getNombre()) > 0){
                // caso recursivo a la derecha si es mayor
                if(p.getDer() == null)
                    p.setDer(nuevo);
                else insertaNodo(p.getDer(), nuevo);
            }
            else{
                // caso recursivo a la izquierda si es menor
                if(p.getIzq() == null)
                    p.setIzq(nuevo);
                else insertaNodo(p.getIzq(), nuevo);
            }
        }
    }

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
                // caso base coincidencia exacta
                encontrado = p.getValor();
            } else if(cmp < 0){
                // caso recursivo subarbol izquierdo
                encontrado = buscarIndexadaRecursivo(p.getIzq(), nombre);
            } else {
                // caso recursivo subarbol derecho
                encontrado = buscarIndexadaRecursivo(p.getDer(), nombre);
            }
        }
        return encontrado;
    }

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
