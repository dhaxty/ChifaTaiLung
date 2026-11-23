package chifatailung.estructuras.arreglos;

import chifatailung.modelo.Plato;

public class cArregloPlatos {
    private Plato platos[];
    private int cantidad;
    private int indMax;
    private int contadorCodigo;

    public cArregloPlatos(){
        this(50);
    }

    public cArregloPlatos(int n){
        platos = new Plato[n];
        indMax = n - 1;
        contadorCodigo = 0;
        cantidad = 0;
        arregloVacio();
    }

    public void arregloVacio(){
        cantidad = 0;
        for(int i = 0; i <= indMax; i++){
            platos[i] = null;
        }
    }

    public int getCantidad(){
        return cantidad;
    }

    public int getCapacidad(){
        return indMax + 1;
    }

    public boolean estaLleno(){
        boolean lleno = false;
        if(cantidad >= platos.length){
            lleno = true;
        }
        return lleno;
    }

    public boolean estaVacio(){
        boolean vacio = false;
        if(cantidad == 0){
            vacio = true;
        }
        return vacio;
    }

    public String registrar(String nombre, String categoria, double precio, boolean disponible){
        String codigo = null;
        if(cantidad < platos.length){
            // no reutiliza codigos en la sesion
            contadorCodigo++;
            codigo = String.format("P%03d", contadorCodigo);
            platos[cantidad] = new Plato(codigo, nombre, categoria, precio, disponible);
            cantidad++;
        }
        return codigo;
    }

    public String registrar(Plato nuevoPlato){
        String codigo = null;
        if(nuevoPlato != null && cantidad < platos.length){
            if(nuevoPlato.getCodigo() == null || nuevoPlato.getCodigo().trim().isEmpty()){
                contadorCodigo++;
                nuevoPlato.setCodigo(String.format("P%03d", contadorCodigo));
            }
            platos[cantidad] = nuevoPlato;
            codigo = nuevoPlato.getCodigo();
            cantidad++;
        }
        return codigo;
    }

    public boolean actualizar(String codigo, String nombre, String categoria, double precio, boolean disponible){
        boolean actualizado = false;
        if(codigo != null){
            int i = 0;
            while(i < cantidad && !actualizado){
                if(platos[i] != null && platos[i].getCodigo().equalsIgnoreCase(codigo)){
                    platos[i].setNombre(nombre);
                    platos[i].setCategoria(categoria);
                    platos[i].setPrecio(precio);
                    platos[i].setDisponible(disponible);
                    actualizado = true;
                }
                i++;
            }
        }
        return actualizado;
    }

    public boolean actualizar(String codigo, Plato datos){
        boolean actualizado = false;
        if(codigo != null && datos != null){
            actualizado = actualizar(codigo, datos.getNombre(), datos.getCategoria(), datos.getPrecio(), datos.isDisponible());
        }
        return actualizado;
    }

    public boolean eliminar(String codigo){
        boolean eliminado = false;
        if(codigo != null && cantidad > 0){
            int pos = -1;
            int i = 0;
            while(i < cantidad && pos == -1){
                if(platos[i] != null && platos[i].getCodigo().equalsIgnoreCase(codigo)){
                    pos = i;
                }
                i++;
            }
            if(pos != -1){
                // desplaza los elementos a la izquierda tras eliminar
                for(int j = pos; j < cantidad - 1; j++){
                    platos[j] = platos[j + 1];
                }
                platos[cantidad - 1] = null;
                cantidad--;
                eliminado = true;
            }
        }
        return eliminado;
    }

    public Plato buscar(String codigo){
        Plato encontrado = null;
        if(codigo != null && cantidad > 0){
            int i = 0;
            while(i < cantidad && encontrado == null){
                if(platos[i] != null && platos[i].getCodigo().equalsIgnoreCase(codigo)){
                    encontrado = platos[i];
                }
                i++;
            }
        }
        return encontrado;
    }

    public Plato[] listar(){
        Plato resultado[] = new Plato[cantidad];
        for(int i = 0; i < cantidad; i++){
            resultado[i] = platos[i];
        }
        return resultado;
    }

    public String muestraArreglo(){
        String cadena = "";
        for(int i = 0; i < cantidad; i++){
            if(platos[i] != null){
                cadena = cadena + " - " + platos[i].getCodigo() + ": " + platos[i].getNombre();
            }
        }
        return cadena;
    }
}
