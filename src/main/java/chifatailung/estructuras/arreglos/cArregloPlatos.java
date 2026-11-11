package chifatailung.estructuras.arreglos;

import chifatailung.modelo.Plato;

// [EXTRAPOLADO]
// Estructura lineal sobre arreglo estático para la gestión de platos.
// No utiliza colecciones de Java ni utilidades predefinidas.
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

    // [EXTRAPOLADO]
    // Algoritmo de Registro:
    // 1. Verifica si la estructura no ha alcanzado su capacidad máxima (tope < indMax).
    // 2. Incrementa el contador correlativo de sesión para generar un código único (P001, P002...).
    // 3. Incrementa el tope del arreglo y almacena la nueva instancia de Plato en esa posición.
    // 4. Retorna el código generado en caso de éxito o null si el arreglo está lleno.
    public String registrar(String nombre, String categoria, double precio, boolean disponible){
        if(tope < indMax){
            contadorCodigo++;
            String codigo = String.format("P%03d", contadorCodigo);
            tope++;
            platos[tope] = new Plato(codigo, nombre, categoria, precio, disponible);
            return codigo;
        }
        return null;
    }

    // [EXTRAPOLADO]
    // Sobrecarga de registro recibiendo objeto Plato:
    // Asigna el código correlativo de sesión si viene vacío o nulo, incrementa el tope
    // y coloca la referencia en la siguiente posición libre del arreglo.
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

    // [EXTRAPOLADO]
    // Algoritmo de Actualización:
    // 1. Recorre iterativamente las posiciones ocupadas del arreglo desde 0 hasta tope.
    // 2. Compara el código del plato actual con el código buscado ignorando mayúsculas.
    // 3. Al encontrarlo, reemplaza sus atributos (nombre, categoría, precio, disponibilidad) y retorna true.
    // 4. Si concluye el recorrido sin encontrar coincidencia, retorna false sin modificar nada.
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

    // [EXTRAPOLADO]
    // Sobrecarga de actualización recibiendo datos de un objeto Plato existente:
    // Localiza la posición por código y actualiza los campos correspondientes.
    public boolean actualizar(String codigo, Plato datos){
        if(codigo == null || datos == null) return false;
        return actualizar(codigo, datos.getNombre(), datos.getCategoria(), datos.getPrecio(), datos.isDisponible());
    }

    // [EXTRAPOLADO]
    // Algoritmo de Eliminación con Corrimiento Manual:
    // 1. Busca linealmente la posición 'pos' del plato cuyo código coincida con el solicitado.
    // 2. Si no existe, finaliza retornando false.
    // 3. Si se encuentra, desplaza hacia la izquierda cada elemento desde 'pos' hasta 'tope - 1' (platos[i] = platos[i + 1]).
    // 4. Libera la última referencia asignándole null, decrementa el tope y retorna true.
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

        for(int i = pos; i < tope; i++){
            platos[i] = platos[i + 1];
        }
        platos[tope] = null;
        tope--;
        return true;
    }

    // [EXTRAPOLADO]
    // Algoritmo de Búsqueda Secuencial:
    // 1. Itera linealmente sobre el arreglo desde la posición 0 hasta la posición tope.
    // 2. Compara el código del plato almacenado con el código objetivo sin distinguir mayúsculas.
    // 3. Retorna la referencia al objeto Plato inmediatamente al hallar la coincidencia.
    // 4. Si recorre todos los elementos válidos sin éxito, retorna null.
    public Plato buscar(String codigo){
        if(codigo == null || tope == -1) return null;
        for(int i = 0; i <= tope; i++){
            if(platos[i] != null && platos[i].getCodigo().equalsIgnoreCase(codigo)){
                return platos[i];
            }
        }
        return null;
    }

    // [EXTRAPOLADO]
    // Algoritmo de Listado para Vistas / Controladores:
    // 1. Evalúa si el arreglo está vacío (tope == -1), devolviendo un arreglo de tamaño 0.
    // 2. Si contiene elementos, instancia un nuevo arreglo de tamaño exacto (tope + 1).
    // 3. Copia secuencialmente cada referencia de plato desde el arreglo interno.
    // 4. Retorna el nuevo arreglo aislado sin exponer el arreglo estático interno de 50.
    public Plato[] listar(){
        if(tope == -1) return new Plato[0];
        Plato resultado[] = new Plato[tope + 1];
        for(int i = 0; i <= tope; i++){
            resultado[i] = platos[i];
        }
        return resultado;
    }

    // [EXTRAPOLADO]
    // Visualización en cadena al estilo del profesor:
    // Concatena secuencialmente los elementos activos con el separador " - ".
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
