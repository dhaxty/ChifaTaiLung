package chifatailung.controlador;

import chifatailung.estructuras.matrices.cMatrizMesas;
import chifatailung.modelo.EstadoMesa;
import chifatailung.modelo.Mesa;
import chifatailung.modelo.Rol;

// controlador para la administracion de mesas con validaciones y permisos por rol
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

    // segun matriz de permisos, tanto ADMIN como EMPLEADO gestionan mesas
    public boolean puedeModificar(){
        return rolActual == Rol.ADMIN || rolActual == Rol.EMPLEADO;
    }

    // devuelve la matriz de mesas completa para renderizar el salon
    public Mesa[][] listar(){
        return oMatriz.listar();
    }

    // obtiene la mesa de una coordenada especifica de la matriz
    public Mesa obtener(int fila, int columna){
        return oMatriz.obtener(fila, columna);
    }

    // busca una mesa por su identificador numerico
    public Mesa buscar(int numero){
        if(numero < 1 || numero > oMatriz.getTotalMesas()){
            return null;
        }
        return oMatriz.buscar(numero);
    }

    // valida permisos, rango y estado antes de actualizar; retorna vacio si fue exitoso
    public String cambiarEstado(int numero, EstadoMesa nuevoEstado){
        if(!puedeModificar()){
            return "Acceso denegado: no cuenta con permisos para modificar mesas.";
        }
        if(numero < 1 || numero > oMatriz.getTotalMesas()){
            return "Número de mesa inválido (debe estar entre 1 y " + oMatriz.getTotalMesas() + ").";
        }
        if(nuevoEstado == null){
            return "El estado de la mesa es obligatorio.";
        }

        boolean actualizado = oMatriz.cambiarEstado(numero, nuevoEstado);
        if(!actualizado){
            return "No se encontró la mesa número " + numero + ".";
        }
        return "";
    }

    // conteos para indicadores de ocupacion del salon
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

    // restablece todas las mesas del chifa al estado LIBRE
    public String reiniciarMesas(){
        if(!puedeModificar()){
            return "Acceso denegado: no cuenta con permisos para reiniciar mesas.";
        }
        oMatriz.matrizVacia();
        return "";
    }

    // carga una distribucion de prueba con mesas libres, ocupadas y reservadas
    public String cargarDatosPrueba(){
        if(!puedeModificar()){
            return "Acceso denegado: no cuenta con permisos para cargar datos.";
        }
        // reinicia a libre primero
        oMatriz.matrizVacia();

        // mesas ocupadas simulando consumo en el chifa
        oMatriz.cambiarEstado(2, EstadoMesa.OCUPADA);
        oMatriz.cambiarEstado(5, EstadoMesa.OCUPADA);
        oMatriz.cambiarEstado(8, EstadoMesa.OCUPADA);
        oMatriz.cambiarEstado(11, EstadoMesa.OCUPADA);

        // mesas reservadas con anticipacion
        oMatriz.cambiarEstado(3, EstadoMesa.RESERVADA);
        oMatriz.cambiarEstado(7, EstadoMesa.RESERVADA);
        oMatriz.cambiarEstado(14, EstadoMesa.RESERVADA);

        return "";
    }
}
