package chifataitlung.controlador;

import chifataitlung.estructuras.arreglos.cArregloPlatos;
import chifataitlung.modelo.Plato;
import chifataitlung.modelo.Rol;

// [EXTRAPOLADO]
// Controlador del módulo Carta de Platos.
// Gestiona la lógica de negocio, validaciones de entrada y control de acceso basado en roles.
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

    // [EXTRAPOLADO]
    // Validación y registro de plato:
    // 1. Verifica permisos: solo ADMIN puede registrar nuevos platos en la carta.
    // 2. Valida obligatoriedad de campos de texto (nombre, categoría) y precio no negativo.
    // 3. Comprueba que el arreglo no haya excedido el límite de 50 elementos.
    // 4. Invoca la inserción en la estructura cArregloPlatos; retorna "" si es exitoso o el mensaje de error.
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

    // [EXTRAPOLADO]
    // Validación y actualización de plato:
    // 1. Verifica permisos de edición exclusivos para ADMIN.
    // 2. Valida código, nombre, categoría y precio no negativo.
    // 3. Solicita a la estructura actualizar los datos del código indicado.
    // 4. Retorna "" en caso de éxito o mensaje descriptivo de fallo si el código no existe.
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

    // [EXTRAPOLADO]
    // Validación y eliminación de plato:
    // 1. Verifica permisos de eliminación exclusivos para ADMIN.
    // 2. Valida que el código a eliminar haya sido proporcionado.
    // 3. Ejecuta la eliminación con corrimiento manual en la estructura cArregloPlatos.
    // 4. Retorna "" si fue eliminado o el mensaje de error correspondiente.
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

    // [EXTRAPOLADO]
    // Búsqueda de plato por código:
    // Permite la consulta a cualquier rol autenticado (ADMIN o EMPLEADO).
    // Delega la búsqueda secuencial a la estructura de datos.
    public Plato buscar(String codigo){
        if(codigo == null || codigo.trim().isEmpty()){
            return null;
        }
        return oArreglo.buscar(codigo.trim());
    }

    // [EXTRAPOLADO]
    // Obtención de todos los platos para la tabla:
    // Retorna el arreglo de platos activos sin exponer el límite interno de 50.
    public Plato[] listar(){
        return oArreglo.listar();
    }

    // [EXTRAPOLADO]
    // Carga de datos de prueba del chifa:
    // Registra platos tradicionales para demostración rápida e independiente del módulo.
    // Permite poblar la estructura sin requerir servicios externos ni dependencias.
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
