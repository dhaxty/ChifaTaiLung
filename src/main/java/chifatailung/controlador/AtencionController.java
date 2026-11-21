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
        boolean tiene = false;
        if(rolActual != null && (rolActual == Rol.ADMIN || rolActual == Rol.EMPLEADO)){
            tiene = true;
        }
        return tiene;
    }

    // encola un cliente asignando automaticamente turno y orden de llegada secuencial
    public String encolar(String nombreCliente, Prioridad prioridad){
        String mensaje = "";
        if(!tienePermiso()){
            mensaje = "Acceso denegado: no cuenta con permisos para registrar en la cola de atención.";
        } else if(nombreCliente == null || nombreCliente.trim().isEmpty()){
            mensaje = "El nombre del cliente es obligatorio.";
        } else if(prioridad == null){
            mensaje = "La prioridad del cliente es obligatoria.";
        } else {
            ClienteAtencion nuevo = new ClienteAtencion(siguienteTurno, nombreCliente.trim(), prioridad, siguienteOrdenLlegada);
            siguienteTurno++;
            siguienteOrdenLlegada++;
            oCola.incluir(nuevo);
        }
        return mensaje;
    }

    // sobrecarga para registrar cliente existente respetando o normalizando su turno u orden
    public String encolar(ClienteAtencion cliente){
        String mensaje = "";
        if(!tienePermiso()){
            mensaje = "Acceso denegado: no cuenta con permisos para registrar en la cola de atención.";
        } else if(cliente == null){
            mensaje = "El cliente no puede ser nulo.";
        } else if(cliente.getNombreCliente() == null || cliente.getNombreCliente().trim().isEmpty()){
            mensaje = "El nombre del cliente es obligatorio.";
        } else if(cliente.getPrioridad() == null){
            mensaje = "La prioridad del cliente es obligatoria.";
        } else {
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
        }
        return mensaje;
    }

    // sinonimo para registrar cliente en la cola
    public String registrar(String nombreCliente, Prioridad prioridad){
        return encolar(nombreCliente, prioridad);
    }

    // atiende y desencola al cliente prioritario al frente de la cola
    public String atender(){
        String mensaje = "";
        if(!tienePermiso()){
            mensaje = "Acceso denegado: no cuenta con permisos para atender clientes.";
        } else if(oCola.estaVacia()){
            mensaje = "La cola de atención está vacía. No hay clientes por atender.";
        } else {
            ultimoAtendido = oCola.eliminar();
            if(ultimoAtendido == null){
                mensaje = "No se pudo atender al cliente.";
            }
        }
        return mensaje;
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
        boolean vacia = false;
        if(oCola.estaVacia()){
            vacia = true;
        }
        return vacia;
    }

    // restablece la cola a vacia
    public String vaciarCola(){
        String mensaje = "";
        if(!tienePermiso()){
            mensaje = "Acceso denegado: no cuenta con permisos para vaciar la cola.";
        } else {
            oCola.colaVacia();
            ultimoAtendido = null;
        }
        return mensaje;
    }

    // carga clientes de prueba con llegadas intercaladas para demostrar la prioridad y FIFO
    public String cargarDatosPrueba(){
        String mensaje = "";
        if(!tienePermiso()){
            mensaje = "Acceso denegado: no cuenta con permisos para cargar datos de prueba.";
        } else {
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
        }
        return mensaje;
    }
}
