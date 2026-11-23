package chifatailung.estructuras.matrices;

import chifatailung.modelo.EstadoMesa;
import chifatailung.modelo.Mesa;

public class cMatrizMesas {
    private Mesa mesas[][];
    private int filas, columnas;

    public cMatrizMesas(){
        filas = 4;
        columnas = 4;
        mesas = new Mesa[filas][columnas];
        inicializarMesas();
    }

    public void inicializarMesas(){
        int numero = 1;
        for(int i = 0; i < filas; i++){
            for(int j = 0; j < columnas; j++){
                int capacidad = 4;
                if(numero <= 4) capacidad = 2;
                else if(numero <= 10) capacidad = 4;
                else if(numero <= 14) capacidad = 6;
                else capacidad = 8;

                mesas[i][j] = new Mesa(numero, capacidad, i, j, EstadoMesa.LIBRE);
                numero++;
            }
        }
    }

    public void matrizVacia(){
        for(int i = 0; i < filas; i++){
            for(int j = 0; j < columnas; j++){
                if(mesas[i][j] != null){
                    mesas[i][j].setEstado(EstadoMesa.LIBRE);
                }
            }
        }
    }

    public int getFilas(){
        return filas;
    }

    public int getColumnas(){
        return columnas;
    }

    public int getTotalMesas(){
        return filas * columnas;
    }

    public Mesa obtener(int fila, int columna){
        Mesa mesa = null;
        if(fila >= 0 && fila < filas && columna >= 0 && columna < columnas){
            mesa = mesas[fila][columna];
        }
        return mesa;
    }

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

    public Mesa[][] listar(){
        Mesa resultado[][] = new Mesa[filas][columnas];
        for(int i = 0; i < filas; i++){
            for(int j = 0; j < columnas; j++){
                resultado[i][j] = mesas[i][j];
            }
        }
        return resultado;
    }

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
