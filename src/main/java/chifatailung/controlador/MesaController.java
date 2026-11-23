package chifatailung.controlador;

import chifatailung.estructuras.matrices.cMatrizMesas;
import chifatailung.modelo.EstadoMesa;
import chifatailung.modelo.Mesa;
import chifatailung.modelo.Rol;

public class MesaController {
    private Rol rolActual;
    private cMatrizMesas oMatriz;

    public MesaController(Rol rolActual){
        this.rolActual = rolActual;
        this.oMatriz = new cMatrizMesas();
    }

    public MesaController(Rol rolActual, cMatrizMesas oMatriz){
        this.rolActual = rolActual;
        this.oMatriz = (oMatriz != null) ? oMatriz : new cMatrizMesas();
    }

    public Rol getRolActual(){
        return rolActual;
    }

    public cMatrizMesas getMatriz(){
        return oMatriz;
    }

    // admin y empleado pueden gestionar mesas
    public boolean puedeModificar(){
        boolean puede = false;
        if(rolActual == Rol.ADMIN || rolActual == Rol.EMPLEADO){
            puede = true;
        }
        return puede;
    }

    public Mesa[][] listar(){
        return oMatriz.listar();
    }

    public Mesa obtener(int fila, int columna){
        return oMatriz.obtener(fila, columna);
    }

    public Mesa buscar(int numero){
        Mesa mesa = null;
        if(numero >= 1 && numero <= oMatriz.getTotalMesas()){
            mesa = oMatriz.buscar(numero);
        }
        return mesa;
    }

    public String cambiarEstado(int numero, EstadoMesa nuevoEstado){
        String mensaje = "";
        if(!puedeModificar()){
            mensaje = "Acceso denegado: no cuenta con permisos para modificar mesas.";
        } else if(numero < 1 || numero > oMatriz.getTotalMesas()){
            mensaje = "Número de mesa inválido (debe estar entre 1 y " + oMatriz.getTotalMesas() + ").";
        } else if(nuevoEstado == null){
            mensaje = "El estado de la mesa es obligatorio.";
        } else {
            boolean actualizado = oMatriz.cambiarEstado(numero, nuevoEstado);
            if(!actualizado){
                mensaje = "No se encontró la mesa número " + numero + ".";
            }
        }
        return mensaje;
    }

    public int contar(EstadoMesa estado){
        return oMatriz.contar(estado);
    }

    public int contarLibres(){
        return oMatriz.contar(EstadoMesa.LIBRE);
    }

    public int contarOcupadas(){
        return oMatriz.contar(EstadoMesa.OCUPADA);
    }

    public int contarReservadas(){
        return oMatriz.contar(EstadoMesa.RESERVADA);
    }

    public String reiniciarMesas(){
        String mensaje = "";
        if(!puedeModificar()){
            mensaje = "Acceso denegado: no cuenta con permisos para reiniciar mesas.";
        } else {
            oMatriz.matrizVacia();
        }
        return mensaje;
    }

    public String cargarDatosPrueba(){
        String mensaje = "";
        if(!puedeModificar()){
            mensaje = "Acceso denegado: no cuenta con permisos para cargar datos.";
        } else {
            oMatriz.matrizVacia();

            oMatriz.cambiarEstado(2, EstadoMesa.OCUPADA);
            oMatriz.cambiarEstado(5, EstadoMesa.OCUPADA);
            oMatriz.cambiarEstado(8, EstadoMesa.OCUPADA);
            oMatriz.cambiarEstado(11, EstadoMesa.OCUPADA);

            oMatriz.cambiarEstado(3, EstadoMesa.RESERVADA);
            oMatriz.cambiarEstado(7, EstadoMesa.RESERVADA);
            oMatriz.cambiarEstado(14, EstadoMesa.RESERVADA);
        }
        return mensaje;
    }
}
