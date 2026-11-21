package chifatailung.estructuras.matrices;

import chifatailung.modelo.EstadoMesa;
import chifatailung.modelo.Mesa;

// estructura matricial 4x4 para el control de las 16 mesas del chifa
public class cMatrizMesas {
    private Mesa mesas[][];
    private int filas, columnas;

    public cMatrizMesas(){
        filas = 4;
        columnas = 4;
        mesas = new Mesa[filas][columnas];
        inicializarMesas();
    }

    // crea e inicializa las 16 mesas con sus capacidades y estado LIBRE
    public void inicializarMesas(){
        int numero = 1;
        for(int i = 0; i < filas; i++){
            for(int j = 0; j < columnas; j++){
                // capacidades realistas de chifa: 2 para parejas, 4 estandar, 6 y 8 familiares
                int capacidad = 4;
                if(numero <= 4) capacidad = 2;
                else if(numero <= 10) capacidad = 4;
                else if(numero <= 14) capacidad = 6;
                else capacidad = 8;

                mesas[i][j] = new Mesa(numero, capacidad, i, j, EstadoMesa.LIBRE);
                numero++;
            }
        }
    } // fin inicializarMesas

    // reinicia todas las mesas de la matriz al estado LIBRE
    public void matrizVacia(){
        for(int i = 0; i < filas; i++){
            for(int j = 0; j < columnas; j++){
                if(mesas[i][j] != null){
                    mesas[i][j].setEstado(EstadoMesa.LIBRE);
                }
            }
        }
    } // fin matrizVacia

    public int getFilas(){
        return filas;
    }

    public int getColumnas(){
        return columnas;
    }

    public int getTotalMesas(){
        return filas * columnas;
    }

    // devuelve la mesa en la posicion [fila][columna] o null si excede los limites
    public Mesa obtener(int fila, int columna){
        Mesa mesa = null;
        if(fila >= 0 && fila < filas && columna >= 0 && columna < columnas){
            mesa = mesas[fila][columna];
        }
        return mesa;
    }

    // busca una mesa por su numero del 1 al 16; devuelve null si no existe
    public Mesa buscar(int numero){
        Mesa encontrado = null;
        int i = 0;
        while(i < filas && encontrado == null){
            int j = 0;
            while(j < columnas && encontrado == null){
                if(mesas[i][j] != null && mesas[i][j].getNumero() == numero){
                    encontrado = mesas[i][j];
                }
                j++;
            }
            i++;
        }
        return encontrado;
    }

    // cambia el estado de una mesa por su numero; retorna true si la encontro y actualizo
    public boolean cambiarEstado(int numero, EstadoMesa estado){
        boolean cambiado = false;
        if(estado != null){
            Mesa mesaBuscada = buscar(numero);
            if(mesaBuscada != null){
                mesaBuscada.setEstado(estado);
                cambiado = true;
            }
        }
        return cambiado;
    }

    // cuenta cuantas mesas se encuentran en el estado consultado
    public int contar(EstadoMesa estado){
        int contador = 0;
        if(estado != null){
            for(int i = 0; i < filas; i++){
                for(int j = 0; j < columnas; j++){
                    if(mesas[i][j] != null && mesas[i][j].getEstado() == estado){
                        contador++;
                    }
                }
            }
        }
        return contador;
    }

    // devuelve una copia de la matriz 4x4 para actualizar vistas y tablas
    public Mesa[][] listar(){
        Mesa resultado[][] = new Mesa[filas][columnas];
        for(int i = 0; i < filas; i++){
            for(int j = 0; j < columnas; j++){
                resultado[i][j] = mesas[i][j];
            }
        }
        return resultado;
    }

    // arma una cadena con las mesas separadas por guiones para verificar en consola
    public String muestraMatriz(){
        String cadena = "";
        for(int i = 0; i < filas; i++){
            for(int j = 0; j < columnas; j++){
                if(mesas[i][j] != null){
                    cadena = cadena + " - Mesa " + mesas[i][j].getNumero()
                            + " [F" + i + ",C" + j + "] (Cap:" + mesas[i][j].getCapacidad()
                            + ", " + mesas[i][j].getEstado() + ")";
                }
            }
        }
        return cadena;
    }
}
