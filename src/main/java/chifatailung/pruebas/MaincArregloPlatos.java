package chifatailung.pruebas;

import chifatailung.controlador.AuthController;
import chifatailung.controlador.PlatoController;
import chifatailung.estructuras.arreglos.cArregloPlatos;
import chifatailung.modelo.Plato;
import chifatailung.modelo.Rol;

// [EXTRAPOLADO]
// Clase de prueba por consola al estilo del profesor (Apéndice A.5).
public class MaincArregloPlatos {
    public static void main(String[] args){
        System.out.println("=== PRUEBA DE cArregloPlatos ===");
        cArregloPlatos oArreglo = new cArregloPlatos(50);
        String c1 = oArreglo.registrar("Chaufa Pollo", "Chaufas", 18.0, true);
        String c2 = oArreglo.registrar("Tallarín Saltado", "Tallarines", 22.0, true);
        String c3 = oArreglo.registrar("Wantán Frito", "Entradas", 12.0, true);
        System.out.println("Platos registrados: " + oArreglo.muestraArreglo());

        System.out.println("Eliminando " + c2 + " (corrimiento manual)...");
        oArreglo.eliminar(c2);
        System.out.println("Luego de eliminar: " + oArreglo.muestraArreglo());

        System.out.println("\n=== PRUEBA DE PlatoController (ADMIN vs EMPLEADO) ===");
        PlatoController cAdmin = new PlatoController(Rol.ADMIN);
        cAdmin.cargarDatosPrueba();
        System.out.println("Platos cargados (Admin): " + cAdmin.listar().length);
        String errAdmin = cAdmin.registrar("Kam Lu Wantán", "Especiales", 28.0, true);
        System.out.println("Registro Admin resultado (vacio es exito): '" + errAdmin + "'");

        PlatoController cEmp = new PlatoController(Rol.EMPLEADO);
        String errEmp = cEmp.registrar("Plato Prohibido", "Especiales", 20.0, true);
        System.out.println("Registro Empleado resultado (espera denegado): '" + errEmp + "'");

        System.out.println("\n=== PRUEBA DE AuthController ===");
        AuthController auth = new AuthController();
        String errLogin1 = auth.iniciarSesion("admin", "admin123");
        System.out.println("Login admin: '" + errLogin1 + "' -> Rol: " + auth.getRolAutenticado());
        String errLogin2 = auth.iniciarSesion("empleado", "empleado123");
        System.out.println("Login empleado: '" + errLogin2 + "' -> Rol: " + auth.getRolAutenticado());
        String errLogin3 = auth.iniciarSesion("admin", "claveErronea");
        System.out.println("Login invalido: '" + errLogin3 + "'");
        System.out.println("=== TODAS LAS PRUEBAS COMPLETADAS ===");
    }
}
