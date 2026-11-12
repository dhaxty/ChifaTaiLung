package chifatailung.controlador;

import chifatailung.estructuras.arreglos.cArregloPlatos;
import chifatailung.modelo.Plato;
import chifatailung.modelo.Rol;

// controlador para la carta de platos con control de permisos por rol
public class PlatoController {
    private Rol rolActual;
    private cArregloPlatos oArreglo;

    public PlatoController(Rol rolActual){
        this.rolActual = rolActual;
        this.oArreglo = new cArregloPlatos(50);
    }

    public PlatoController(Rol rolActual, cArregloPlatos oArreglo){
        this.rolActual = rolActual;
        this.oArreglo = (oArreglo != null) ? oArreglo : new cArregloPlatos(50);
    }

    public Rol getRolActual(){
        return rolActual;
    }

    // solo el admin puede agregar, modificar o eliminar platos de la carta
    public boolean puedeModificar(){
        return rolActual == Rol.ADMIN;
    }

    public int getCantidad(){
        return oArreglo.getCantidad();
    }

    public int getCapacidad(){
        return oArreglo.getCapacidad();
    }

    public cArregloPlatos getArreglo(){
        return oArreglo;
    }

    // valida permisos y campos antes de registrar; retorna vacio si fue exitoso
    public String registrar(String nombre, String categoria, double precio, boolean disponible){
        if(!puedeModificar()){
            return "Acceso denegado: solo el Administrador puede registrar platos.";
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
        if(oArreglo.estaLleno()){
            return "Capacidad máxima alcanzada (límite: 50 platos).";
        }

        String codigo = oArreglo.registrar(nombre.trim(), categoria.trim(), precio, disponible);
        if(codigo == null){
            return "No se pudo registrar el plato: estructura llena.";
        }
        return "";
    }

    // valida campos y modifica el plato por su codigo
    public String actualizar(String codigo, String nombre, String categoria, double precio, boolean disponible){
        if(!puedeModificar()){
            return "Acceso denegado: solo el Administrador puede editar platos.";
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

        boolean actualizado = oArreglo.actualizar(codigo.trim(), nombre.trim(), categoria.trim(), precio, disponible);
        if(!actualizado){
            return "No se encontró ningún plato con el código: " + codigo;
        }
        return "";
    }

    // elimina el plato por codigo con corrimiento manual
    public String eliminar(String codigo){
        if(!puedeModificar()){
            return "Acceso denegado: solo el Administrador puede eliminar platos.";
        }
        if(codigo == null || codigo.trim().isEmpty()){
            return "El código del plato es obligatorio.";
        }

        boolean eliminado = oArreglo.eliminar(codigo.trim());
        if(!eliminado){
            return "No se encontró ningún plato con el código: " + codigo;
        }
        return "";
    }

    // busqueda por codigo permitida para admin y empleado
    public Plato buscar(String codigo){
        if(codigo == null || codigo.trim().isEmpty()){
            return null;
        }
        return oArreglo.buscar(codigo.trim());
    }

    // lista los platos registrados para cargarlos en la tabla
    public Plato[] listar(){
        return oArreglo.listar();
    }

    // carga platos tradicionales para probar la carta sin depender de otros modulos
    public String cargarDatosPrueba(){
        String platosDemo[][] = {
            {"Arroz Chaufa de Pollo", "Chaufas", "18.00"},
            {"Arroz Chaufa Especial", "Chaufas", "24.00"},
            {"Aeropuerto de Pollo", "Combinados", "22.00"},
            {"Tallarín Saltado de Carne", "Tallarines", "22.00"},
            {"Tallarín Taypa", "Tallarines", "28.00"},
            {"Sopa Wantán Especial", "Sopas", "16.00"},
            {"Sopa Fuchifú", "Sopas", "15.00"},
            {"Wantán Frito (6 unid.)", "Entradas", "12.00"},
            {"Pollo ChiJauKay", "Especialidades", "25.00"},
            {"Pollo TiPaKay", "Especialidades", "25.00"},
            {"Kam Lu Wantán", "Especialidades", "28.00"},
            {"Chicha Morada 1L", "Bebidas", "10.00"}
        };

        for(int i = 0; i < platosDemo.length; i++){
            if(oArreglo.estaLleno()) break;
            double precio = Double.parseDouble(platosDemo[i][2]);
            oArreglo.registrar(platosDemo[i][0], platosDemo[i][1], precio, true);
        }
        return "";
    }
}
