package chifatailung.controlador;

import chifatailung.estructuras.arboles.cABBPlatos;
import chifatailung.modelo.Plato;
import chifatailung.modelo.Rol;

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

    // solo admin puede registrar platos en el arbol
    public boolean puedeModificar(){
        boolean puede = false;
        if(rolActual == Rol.ADMIN){
            puede = true;
        }
        return puede;
    }

    public boolean tienePermisoConsulta(){
        boolean tiene = false;
        if(rolActual != null && (rolActual == Rol.ADMIN || rolActual == Rol.EMPLEADO)){
            tiene = true;
        }
        return tiene;
    }

    public int getCantidad(){
        return oABB.contar();
    }

    public boolean estaVacio(){
        boolean vacio = false;
        if(oABB.estaVacio()){
            vacio = true;
        }
        return vacio;
    }

    public String insertar(String codigo, String nombre, String categoria, double precio, boolean disponible){
        String mensaje = "";
        if(!puedeModificar()){
            mensaje = "Acceso denegado: solo el Administrador puede registrar platos en el árbol.";
        } else if(codigo == null || codigo.trim().isEmpty()){
            mensaje = "El código del plato es obligatorio.";
        } else if(nombre == null || nombre.trim().isEmpty()){
            mensaje = "El nombre del plato es obligatorio.";
        } else if(categoria == null || categoria.trim().isEmpty()){
            mensaje = "La categoría es obligatoria.";
        } else if(precio < 0){
            mensaje = "El precio no puede ser negativo.";
        } else if(oABB.buscarIndexada(nombre.trim()) != null){
            mensaje = "Ya existe un plato registrado con el nombre: " + nombre.trim();
        } else {
            Plato plato = new Plato(codigo.trim(), nombre.trim(), categoria.trim(), precio, disponible);
            boolean insertado = oABB.ingresarValor(plato);
            if(!insertado){
                mensaje = "No se pudo registrar el plato en el árbol.";
            }
        }
        return mensaje;
    }

    public String insertar(Plato plato){
        String mensaje = "";
        if(plato == null){
            mensaje = "El plato no puede ser nulo.";
        } else {
            mensaje = insertar(plato.getCodigo(), plato.getNombre(), plato.getCategoria(), plato.getPrecio(), plato.isDisponible());
        }
        return mensaje;
    }

    public Plato buscarSecuencial(String nombre){
        Plato encontrado = null;
        if(nombre != null && !nombre.trim().isEmpty()){
            encontrado = oABB.buscarSecuencial(nombre.trim());
        }
        return encontrado;
    }

    public Plato buscarIndexada(String nombre){
        Plato encontrado = null;
        if(nombre != null && !nombre.trim().isEmpty()){
            encontrado = oABB.buscarIndexada(nombre.trim());
        }
        return encontrado;
    }

    public Plato[] enOrden(){
        return oABB.enOrden();
    }

    public Plato[] listar(){
        return oABB.enOrden();
    }

    public String recorreArbol(int opcion){
        return oABB.recorreArbol(opcion);
    }

    // vaciado restringido a rol admin
    public String vaciarArbol(){
        String mensaje = "";
        if(!puedeModificar()){
            mensaje = "Acceso denegado: solo el Administrador puede vaciar el árbol.";
        } else {
            oABB.arbolVacio();
        }
        return mensaje;
    }

    public String cargarDatosPrueba(){
        oABB.arbolVacio();

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
