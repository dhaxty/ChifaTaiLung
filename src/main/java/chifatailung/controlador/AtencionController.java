package chifatailung.controlador;

import chifatailung.estructuras.colas.cColaPrioridad;
import chifatailung.modelo.ClienteAtencion;
import chifatailung.modelo.Prioridad;
import chifatailung.modelo.Rol;

// controlador del modulo de atencion al cliente con cola por prioridad y gestion de turnos
public class AtencionController {
    private Rol rolActual;
    private cColaPrioridad oCola;
    private int siguienteTurno;
    private long siguienteOrdenLlegada;
    private ClienteAtencion ultimoAtendido;

    public AtencionController(Rol rolActual){
        this(rolActual, new cColaPrioridad());
    }

    public AtencionController(Rol rolActual, cColaPrioridad oCola){
        this.rolActual = rolActual;
        this.oCola = (oCola != null) ? oCola : new cColaPrioridad();
        this.siguienteTurno = 1;
        this.siguienteOrdenLlegada = 1L;
    }

    public Rol getRolActual(){
        return rolActual;
    }

    public cColaPrioridad getCola(){
        return oCola;
    }

    public ClienteAtencion getUltimoAtendido(){
        return ultimoAtendido;
    }

    // segun matriz de permisos de la seccion 4, tanto ADMIN como EMPLEADO gestionan la atencion
    public boolean tienePermiso(){
        return rolActual != null && (rolActual == Rol.ADMIN || rolActual == Rol.EMPLEADO);
    }

    // encola un cliente asignando automaticamente turno y orden de llegada secuencial
    public String encolar(String nombreCliente, Prioridad prioridad){
        if(!tienePermiso()){
            return "Acceso denegado: no cuenta con permisos para registrar en la cola de atención.";
        }
        if(nombreCliente == null || nombreCliente.trim().isEmpty()){
            return "El nombre del cliente es obligatorio.";
        }
        if(prioridad == null){
            return "La prioridad del cliente es obligatoria.";
        }

        ClienteAtencion nuevo = new ClienteAtencion(siguienteTurno, nombreCliente.trim(), prioridad, siguienteOrdenLlegada);
        siguienteTurno++;
        siguienteOrdenLlegada++;

        oCola.incluir(nuevo);
        return "";
    }

    // sobrecarga para registrar cliente existente respetando o normalizando su turno u orden
    public String encolar(ClienteAtencion cliente){
        if(!tienePermiso()){
            return "Acceso denegado: no cuenta con permisos para registrar en la cola de atención.";
        }
        if(cliente == null){
            return "El cliente no puede ser nulo.";
        }
        if(cliente.getNombreCliente() == null || cliente.getNombreCliente().trim().isEmpty()){
            return "El nombre del cliente es obligatorio.";
        }
        if(cliente.getPrioridad() == null){
            return "La prioridad del cliente es obligatoria.";
        }

        if(cliente.getNumeroTurno() <= 0){
            cliente.setNumeroTurno(siguienteTurno++);
        } else if(cliente.getNumeroTurno() >= siguienteTurno){
            siguienteTurno = cliente.getNumeroTurno() + 1;
        }

        if(cliente.getOrdenLlegada() <= 0){
            cliente.setOrdenLlegada(siguienteOrdenLlegada++);
        } else if(cliente.getOrdenLlegada() >= siguienteOrdenLlegada){
            siguienteOrdenLlegada = cliente.getOrdenLlegada() + 1;
        }

        oCola.incluir(cliente);
        return "";
    }

    // sinonimo para registrar cliente en la cola
    public String registrar(String nombreCliente, Prioridad prioridad){
        return encolar(nombreCliente, prioridad);
    }

    // atiende y desencola al cliente prioritario al frente de la cola
    public String atender(){
        if(!tienePermiso()){
            return "Acceso denegado: no cuenta con permisos para atender clientes.";
        }
        if(oCola.estaVacia()){
            return "La cola de atención está vacía. No hay clientes por atender.";
        }

        ultimoAtendido = oCola.eliminar();
        if(ultimoAtendido == null){
            return "No se pudo atender al cliente.";
        }
        return "";
    }

    // sinonimo descriptivo para desencolar
    public String desencolar(){
        return atender();
    }

    // consulta el cliente prioritario al frente sin desencolarlo
    public ClienteAtencion acceso(){
        return oCola.acceso();
    }

    // sinonimo para consultar el proximo cliente a ser atendido
    public ClienteAtencion verSiguiente(){
        return oCola.acceso();
    }

    // devuelve arreglo con los clientes en espera para renderizar en JTable
    public ClienteAtencion[] listar(){
        return oCola.listar();
    }

    public int getCantidad(){
        return oCola.getCantidad();
    }

    public boolean estaVacia(){
        return oCola.estaVacia();
    }

    // restablece la cola a vacia
    public String vaciarCola(){
        if(!tienePermiso()){
            return "Acceso denegado: no cuenta con permisos para vaciar la cola.";
        }
        oCola.colaVacia();
        ultimoAtendido = null;
        return "";
    }

    // carga clientes de prueba con llegadas intercaladas para demostrar la prioridad y FIFO
    public String cargarDatosPrueba(){
        if(!tienePermiso()){
            return "Acceso denegado: no cuenta con permisos para cargar datos de prueba.";
        }
        vaciarCola();
        siguienteTurno = 1;
        siguienteOrdenLlegada = 1L;

        // llegada intercalada de clientes representativos en un chifa
        encolar("Juan Carlos Mendoza", Prioridad.REGULAR);
        encolar("Rosa Elvira Huamán (Adulto mayor)", Prioridad.PREFERENCIAL);
        encolar("Carlos Alberto Wong", Prioridad.REGULAR);
        encolar("María Elena Torres (Madre gestante)", Prioridad.PREFERENCIAL);
        encolar("Pedro Chang Salazar", Prioridad.REGULAR);
        encolar("José Antonio Vega (Persona con discapacidad)", Prioridad.PREFERENCIAL);
        encolar("Sofía Luna Quispe", Prioridad.REGULAR);

        return "";
    }
}
