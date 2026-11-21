package chifatailung.pruebas;

import chifatailung.controlador.MesaController;
import chifatailung.estructuras.matrices.cMatrizMesas;
import chifatailung.modelo.EstadoMesa;
import chifatailung.modelo.Mesa;
import chifatailung.modelo.Rol;

// prueba de consola para la matriz de mesas y su controlador
public class MaincMatrizMesas {
    public static void main(String[] args){
        System.out.println("=== PRUEBA DE cMatrizMesas (16 MESAS, 4x4) ===");
        cMatrizMesas oMatriz = new cMatrizMesas();

        System.out.println("Dimensiones: " + oMatriz.getFilas() + "x" + oMatriz.getColumnas()
                + " (Total: " + oMatriz.getTotalMesas() + " mesas)");
        System.out.println("Estado inicial de la matriz:");
        System.out.println(oMatriz.muestraMatriz());

        // verificar conteo inicial
        System.out.println("\nConteos iniciales:");
        System.out.println("Libres: " + oMatriz.contar(EstadoMesa.LIBRE));
        System.out.println("Ocupadas: " + oMatriz.contar(EstadoMesa.OCUPADA));
        System.out.println("Reservadas: " + oMatriz.contar(EstadoMesa.RESERVADA));

        // probar transiciones de estado
        System.out.println("\n--- Probando cambios de estado directo en estructura ---");
        oMatriz.cambiarEstado(1, EstadoMesa.OCUPADA);
        oMatriz.cambiarEstado(2, EstadoMesa.RESERVADA);
        oMatriz.cambiarEstado(3, EstadoMesa.OCUPADA);
        oMatriz.cambiarEstado(16, EstadoMesa.RESERVADA);

        Mesa m1 = oMatriz.buscar(1);
        Mesa m2 = oMatriz.buscar(2);
        Mesa m16 = oMatriz.buscar(16);
        System.out.println("Mesa 1 estado: " + (m1 != null ? m1.getEstado() : "null"));
        System.out.println("Mesa 2 estado: " + (m2 != null ? m2.getEstado() : "null"));
        System.out.println("Mesa 16 estado: " + (m16 != null ? m16.getEstado() : "null"));

        // transicion de reservada a ocupada, y de ocupada a libre
        oMatriz.cambiarEstado(2, EstadoMesa.OCUPADA);
        oMatriz.cambiarEstado(1, EstadoMesa.LIBRE);
        System.out.println("Mesa 2 paso a: " + m2.getEstado());
        System.out.println("Mesa 1 paso a: " + m1.getEstado());

        System.out.println("\nConteos tras cambios:");
        System.out.println("Libres: " + oMatriz.contar(EstadoMesa.LIBRE));
        System.out.println("Ocupadas: " + oMatriz.contar(EstadoMesa.OCUPADA));
        System.out.println("Reservadas: " + oMatriz.contar(EstadoMesa.RESERVADA));

        // prueba del controlador con roles y datos de prueba
        System.out.println("\n=== PRUEBA DE MesaController (ADMIN y EMPLEADO) ===");
        MesaController cAdmin = new MesaController(Rol.ADMIN);
        cAdmin.cargarDatosPrueba();
        System.out.println("Datos de prueba cargados en Admin:");
        System.out.println("Libres: " + cAdmin.contarLibres());
        System.out.println("Ocupadas: " + cAdmin.contarOcupadas());
        System.out.println("Reservadas: " + cAdmin.contarReservadas());

        // prueba cambio de estado mediante controlador
        String errCambio = cAdmin.cambiarEstado(4, EstadoMesa.OCUPADA);
        System.out.println("Cambio mesa 4 por Admin (espera vacio): '" + errCambio + "'");

        // prueba de validacion de limites
        String errInvalido = cAdmin.cambiarEstado(99, EstadoMesa.OCUPADA);
        System.out.println("Cambio mesa 99 invalida (espera mensaje): '" + errInvalido + "'");

        // prueba con rol EMPLEADO
        MesaController cEmpleado = new MesaController(Rol.EMPLEADO);
        String errEmp = cEmpleado.cambiarEstado(6, EstadoMesa.RESERVADA);
        System.out.println("Cambio mesa 6 por Empleado (espera vacio): '" + errEmp + "'");

        System.out.println("\n=== TODAS LAS PRUEBAS DE MESAS COMPLETADAS EXITOSAMENTE ===");
    }
}
