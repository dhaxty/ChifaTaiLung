package chifatailung.controlador;

import chifatailung.estructuras.arboles.cABBPlatos;
import chifatailung.modelo.Plato;
import chifatailung.modelo.Rol;

// controlador del modulo de busqueda en arbol binario con permisos por rol
public class ArbolController {
    private Rol rolActual;
    private cABBPlatos oABB;

    public ArbolController(Rol rolActual){
        this(rolActual, new cABBPlatos());
    }

    public ArbolController(Rol rolActual, cABBPlatos oABB){
        this.rolActual = rolActual;
        this.oABB = (oABB != null) ? oABB : new cABBPlatos();
    }

    public Rol getRolActual(){
        return rolActual;
    }

    public cABBPlatos getArbol(){
        return oABB;
    }

    // segun matriz de permisos de la seccion 4, solo ADMIN puede registrar platos en el arbol
    public boolean puedeModificar(){
        return rolActual == Rol.ADMIN;
    }

    public boolean tienePermisoConsulta(){
        return rolActual != null && (rolActual == Rol.ADMIN || rolActual == Rol.EMPLEADO);
    }

    public int getCantidad(){
        return oABB.contar();
    }

    public boolean estaVacio(){
        return oABB.estaVacio();
    }

    // valida permisos y campos antes de ingresar un plato al arbol binario
    public String insertar(String codigo, String nombre, String categoria, double precio, boolean disponible){
        if(!puedeModificar()){
            return "Acceso denegado: solo el Administrador puede registrar platos en el árbol.";
        }
        if(codigo == null || codigo.trim().isEmpty()){
            return "El código del plato es obligatorio.";
        }
        if(nombre == null || nombre.trim().isEmpty()){
            return "El nombre del plato es obligatorio.";
        }
        if(categoria == null || categoria.trim().isEmpty()){
            return "La categoría es obligatoria.";
        }
        if(precio < 0){
            return "El precio no puede ser negativo.";
        }

        // verificacion previa para mensaje explicativo al usuario
        if(oABB.buscarIndexada(nombre.trim()) != null){
            return "Ya existe un plato registrado con el nombre: " + nombre.trim();
        }

        Plato plato = new Plato(codigo.trim(), nombre.trim(), categoria.trim(), precio, disponible);
        boolean insertado = oABB.ingresarValor(plato);
        if(!insertado){
            return "No se pudo registrar el plato en el árbol.";
        }
        return "";
    }

    // sobrecarga para insertar directamente un objeto Plato
    public String insertar(Plato plato){
        if(plato == null){
            return "El plato no puede ser nulo.";
        }
        return insertar(plato.getCodigo(), plato.getNombre(), plato.getCategoria(), plato.getPrecio(), plato.isDisponible());
    }

    // busqueda secuencial permitida para administrador y empleado
    public Plato buscarSecuencial(String nombre){
        if(nombre == null || nombre.trim().isEmpty()){
            return null;
        }
        return oABB.buscarSecuencial(nombre.trim());
    }

    // busqueda indexada permitida para administrador y empleado
    public Plato buscarIndexada(String nombre){
        if(nombre == null || nombre.trim().isEmpty()){
            return null;
        }
        return oABB.buscarIndexada(nombre.trim());
    }

    // devuelve arreglo de platos en orden alfabetico para JTable
    public Plato[] enOrden(){
        return oABB.enOrden();
    }

    // sinonimo para compatibilidad con vistas y otros controladores
    public Plato[] listar(){
        return oABB.enOrden();
    }

    // recorridos en cadena (1: pre-orden, 2: in-orden, 3: post-orden)
    public String recorreArbol(int opcion){
        return oABB.recorreArbol(opcion);
    }

    // vaciado del arbol restringido a rol administrador
    public String vaciarArbol(){
        if(!puedeModificar()){
            return "Acceso denegado: solo el Administrador puede vaciar el árbol.";
        }
        oABB.arbolVacio();
        return "";
    }

    // carga platos de prueba representativos para probar busquedas y recorridos
    public String cargarDatosPrueba(){
        oABB.arbolVacio();

        // platos seleccionados para un arbol balanceado con variedad de letras iniciales
        Plato demo[] = {
            new Plato("P001", "Kam Lu Wantán", "Especialidades", 28.00, true),
            new Plato("P002", "Chicha Morada 1L", "Bebidas", 10.00, true),
            new Plato("P003", "Sopa Wantán Especial", "Sopas", 16.00, true),
            new Plato("P004", "Aeropuerto de Pollo", "Combinados", 22.00, true),
            new Plato("P005", "Chaufa Especial", "Chaufas", 24.00, true),
            new Plato("P006", "Pollo ChiJauKay", "Especialidades", 25.00, true),
            new Plato("P007", "Tallarín Taypa", "Tallarines", 28.00, true),
            new Plato("P008", "Wantán Frito (6 unid.)", "Entradas", 12.00, true),
            new Plato("P009", "Pollo TiPaKay", "Especialidades", 25.00, true),
            new Plato("P010", "Sopa Fuchifú", "Sopas", 15.00, true)
        };

        for(int i = 0; i < demo.length; i++){
            oABB.ingresarValor(demo[i]);
        }
        return "";
    }
}
