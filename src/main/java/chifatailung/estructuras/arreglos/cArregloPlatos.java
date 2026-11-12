package chifatailung.estructuras.arreglos;

import chifatailung.modelo.Plato;

// arreglo lineal para la carta de platos implementado a mano sin colecciones
public class cArregloPlatos {
    private Plato platos[];
    private int tope;
    private int indMax;
    private int contadorCodigo;

    public cArregloPlatos(){
        this(50);
    }

    public cArregloPlatos(int n){
        platos = new Plato[n];
        indMax = n - 1;
        contadorCodigo = 0;
        arregloVacio();
    }

    // deja el tope en -1 y limpia las posiciones del arreglo
    public void arregloVacio(){
        tope = -1;
        for(int i = 0; i <= indMax; i++){
            platos[i] = null;
        }
    }

    public int getTope(){
        return tope;
    }

    public int getCantidad(){
        return tope + 1;
    }

    public int getCapacidad(){
        return indMax + 1;
    }

    public boolean estaLleno(){
        return tope >= indMax;
    }

    public boolean estaVacio(){
        return tope == -1;
    }

    // genera codigo automatico P001, P002... y guarda al final si hay espacio
    public String registrar(String nombre, String categoria, double precio, boolean disponible){
        if(tope < indMax){
            // el contador no baja al borrar para no reutilizar codigos en la sesion
            contadorCodigo++;
            String codigo = String.format("P%03d", contadorCodigo);
            tope++;
            platos[tope] = new Plato(codigo, nombre, categoria, precio, disponible);
            return codigo;
        }
        return null;
    }

    // sobrecarga para registrar cuando ya se tiene el objeto plato
    public String registrar(Plato nuevoPlato){
        if(nuevoPlato == null) return null;
        if(tope < indMax){
            if(nuevoPlato.getCodigo() == null || nuevoPlato.getCodigo().trim().isEmpty()){
                contadorCodigo++;
                nuevoPlato.setCodigo(String.format("P%03d", contadorCodigo));
            }
            tope++;
            platos[tope] = nuevoPlato;
            return nuevoPlato.getCodigo();
        }
        return null;
    }

    // busca por codigo y actualiza sus datos; devuelve false si no existe
    public boolean actualizar(String codigo, String nombre, String categoria, double precio, boolean disponible){
        if(codigo == null) return false;
        for(int i = 0; i <= tope; i++){
            if(platos[i] != null && platos[i].getCodigo().equalsIgnoreCase(codigo)){
                platos[i].setNombre(nombre);
                platos[i].setCategoria(categoria);
                platos[i].setPrecio(precio);
                platos[i].setDisponible(disponible);
                return true;
            }
        }
        return false;
    }

    // actualiza los datos tomando los valores desde otro objeto plato
    public boolean actualizar(String codigo, Plato datos){
        if(codigo == null || datos == null) return false;
        return actualizar(codigo, datos.getNombre(), datos.getCategoria(), datos.getPrecio(), datos.isDisponible());
    }

    // busca el codigo y corre los platos a la izquierda para tapar el vacio
    public boolean eliminar(String codigo){
        if(codigo == null || tope == -1) return false;
        int pos = -1;
        for(int i = 0; i <= tope; i++){
            if(platos[i] != null && platos[i].getCodigo().equalsIgnoreCase(codigo)){
                pos = i;
                break;
            }
        }
        if(pos == -1) return false;

        // corre los platos una posicion a la izquierda
        for(int i = pos; i < tope; i++){
            platos[i] = platos[i + 1];
        }
        // limpia la ultima referencia y decrementa el tope
        platos[tope] = null;
        tope--;
        return true;
    }

    // busqueda secuencial por codigo del plato; devuelve null si no existe
    public Plato buscar(String codigo){
        if(codigo == null || tope == -1) return null;
        for(int i = 0; i <= tope; i++){
            if(platos[i] != null && platos[i].getCodigo().equalsIgnoreCase(codigo)){
                return platos[i];
            }
        }
        return null;
    }

    // copia los platos activos a un nuevo arreglo para cargarlos en la tabla
    public Plato[] listar(){
        if(tope == -1) return new Plato[0];
        Plato resultado[] = new Plato[tope + 1];
        for(int i = 0; i <= tope; i++){
            resultado[i] = platos[i];
        }
        return resultado;
    }

    // arma una cadena con los platos separados por guiones para probar por consola
    public String muestraArreglo(){
        String cadena = "";
        for(int i = 0; i <= tope; i++){
            if(platos[i] != null){
                cadena = cadena + " - " + platos[i].getCodigo() + ": " + platos[i].getNombre();
            }
        }
        return cadena;
    }
}
