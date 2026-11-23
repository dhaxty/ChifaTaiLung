package chifatailung.controlador;

import chifatailung.estructuras.pilas.cPilaComprobantes;
import chifatailung.modelo.Comprobante;
import chifatailung.modelo.Rol;

public class ComprobanteController {
    private Rol rolActual;
    private cPilaComprobantes oPila;

    public ComprobanteController(Rol rolActual){
        this(rolActual, new cPilaComprobantes(50));
    }

    public ComprobanteController(Rol rolActual, cPilaComprobantes oPila){
        this.rolActual = rolActual;
        this.oPila = (oPila != null) ? oPila : new cPilaComprobantes(50);
    }

    public Rol getRolActual(){
        return rolActual;
    }

    public cPilaComprobantes getPila(){
        return oPila;
    }

    // admin y empleado pueden gestionar comprobantes
    public boolean tienePermiso(){
        boolean tiene = false;
        if(rolActual != null && (rolActual == Rol.ADMIN || rolActual == Rol.EMPLEADO)){
            tiene = true;
        }
        return tiene;
    }

    public String registrar(String numero, String concepto, String tipo, double importe){
        String mensaje = "";
        if(!tienePermiso()){
            mensaje = "Acceso denegado: no cuenta con permisos para registrar comprobantes.";
        } else if(oPila.estaLlena()){
            mensaje = "La pila de comprobantes está llena (capacidad máxima: " + oPila.getCapacidad() + ").";
        } else if(numero == null || numero.trim().isEmpty()){
            mensaje = "El número de comprobante es obligatorio.";
        } else if(concepto == null || concepto.trim().isEmpty()){
            mensaje = "El concepto del comprobante es obligatorio.";
        } else if(tipo == null || tipo.trim().isEmpty()){
            mensaje = "El tipo de comprobante es obligatorio.";
        } else if(importe < 0){
            mensaje = "El importe del comprobante no puede ser negativo.";
        } else if(oPila.buscar(numero) != null){
            mensaje = "Ya existe un comprobante registrado con el número " + numero.trim() + ".";
        } else {
            boolean insertado = oPila.insertar(new Comprobante(numero.trim(), concepto.trim(), tipo.trim(), importe));
            if(!insertado){
                mensaje = "No se pudo apilar el comprobante (la pila está llena).";
            }
        }
        return mensaje;
    }

    public String registrar(Comprobante comprobante){
        String mensaje = "";
        if(comprobante == null){
            mensaje = "El comprobante no puede ser nulo.";
        } else {
            mensaje = registrar(comprobante.getNumero(), comprobante.getConcepto(), comprobante.getTipo(), comprobante.getImporte());
        }
        return mensaje;
    }

    public String desapilar(){
        String mensaje = "";
        if(!tienePermiso()){
            mensaje = "Acceso denegado: no cuenta con permisos para desapilar comprobantes.";
        } else if(oPila.estaVacia()){
            mensaje = "La pila está vacía. No hay comprobantes para desapilar.";
        } else {
            Comprobante eliminado = oPila.eliminar();
            if(eliminado == null){
                mensaje = "No se pudo desapilar el comprobante.";
            }
        }
        return mensaje;
    }

    public String eliminarTope(){
        String mensaje = desapilar();
        return mensaje;
    }

    public Comprobante accesar(){
        return oPila.accesar();
    }

    public Comprobante verTope(){
        return oPila.accesar();
    }

    public Comprobante buscar(String numero){
        return oPila.buscar(numero);
    }

    public Comprobante[] listar(){
        return oPila.listar();
    }

    public double totalImportes(){
        Comprobante comprobantes[] = oPila.listar();
        double total = 0.0;
        for(int i = 0; i < comprobantes.length; i++){
            if(comprobantes[i] != null){
                total += comprobantes[i].getImporte();
            }
        }
        return total;
    }

    public int getCantidad(){
        return oPila.getCantidad();
    }

    public int getCapacidad(){
        return oPila.getCapacidad();
    }

    public boolean estaVacia(){
        boolean vacia = false;
        if(oPila.estaVacia()){
            vacia = true;
        }
        return vacia;
    }

    public boolean estaLlena(){
        boolean llena = false;
        if(oPila.estaLlena()){
            llena = true;
        }
        return llena;
    }

    public String vaciarPila(){
        String mensaje = "";
        if(!tienePermiso()){
            mensaje = "Acceso denegado: no cuenta con permisos para vaciar la pila.";
        } else {
            oPila.pilaVacia();
        }
        return mensaje;
    }

    public String cargarDatosPrueba(){
        String mensaje = "";
        if(!tienePermiso()){
            mensaje = "Acceso denegado: no cuenta con permisos para cargar datos de prueba.";
        } else {
            oPila.pilaVacia();

            registrar("B001-000101", "Consumo Salón - Mesa 2 (Chaulafán y Wantán)", "Boleta", 58.50);
            registrar("B001-000102", "Consumo Salón - Mesa 5 (Arroz Chaufa Especial)", "Boleta", 42.00);
            registrar("F001-000030", "Almuerzo Ejecutivo Corporativo (Mesa 8)", "Factura", 185.00);
            registrar("B001-000103", "Banquete Familiar Tai Lung (Mesa 11)", "Boleta", 260.00);
            registrar("B001-000104", "Consumo Pareja - Mesa 1 (Kam Lu Wantán)", "Boleta", 74.50);
        }
        return mensaje;
    }
}
