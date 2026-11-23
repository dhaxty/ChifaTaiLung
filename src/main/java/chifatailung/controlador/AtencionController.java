package chifatailung.controlador;

import chifatailung.estructuras.colas.cColaPrioridad;
import chifatailung.modelo.ClienteAtencion;
import chifatailung.modelo.Prioridad;
import chifatailung.modelo.Rol;

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

    // admin y empleado pueden gestionar la atencion
    public boolean tienePermiso(){
        boolean tiene = false;
        if(rolActual != null && (rolActual == Rol.ADMIN || rolActual == Rol.EMPLEADO)){
            tiene = true;
        }
        return tiene;
    }

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

    public String registrar(String nombreCliente, Prioridad prioridad){
        return encolar(nombreCliente, prioridad);
    }

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

    public String desencolar(){
        return atender();
    }

    public ClienteAtencion acceso(){
        return oCola.acceso();
    }

    public ClienteAtencion verSiguiente(){
        return oCola.acceso();
    }

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

    public String cargarDatosPrueba(){
        String mensaje = "";
        if(!tienePermiso()){
            mensaje = "Acceso denegado: no cuenta con permisos para cargar datos de prueba.";
        } else {
            vaciarCola();
            siguienteTurno = 1;
            siguienteOrdenLlegada = 1L;

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
