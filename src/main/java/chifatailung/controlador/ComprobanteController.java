package chifatailung.controlador;

import chifatailung.estructuras.pilas.cPilaComprobantes;
import chifatailung.modelo.Comprobante;
import chifatailung.modelo.Rol;

// controlador del modulo de comprobantes de pago con validaciones y control de permisos
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

    // segun matriz de permisos de la seccion 4, tanto ADMIN como EMPLEADO gestionan comprobantes
    public boolean tienePermiso(){
        return rolActual != null && (rolActual == Rol.ADMIN || rolActual == Rol.EMPLEADO);
    }

    // valida campos obligatorios, identificador unico, importe no negativo y capacidad de pila
    public String registrar(String numero, String concepto, String tipo, double importe){
        if(!tienePermiso()){
            return "Acceso denegado: no cuenta con permisos para registrar comprobantes.";
        }
        if(oPila.estaLlena()){
            return "La pila de comprobantes está llena (capacidad máxima: " + oPila.getCapacidad() + ").";
        }
        if(numero == null || numero.trim().isEmpty()){
            return "El número de comprobante es obligatorio.";
        }
        if(concepto == null || concepto.trim().isEmpty()){
            return "El concepto del comprobante es obligatorio.";
        }
        if(tipo == null || tipo.trim().isEmpty()){
            return "El tipo de comprobante es obligatorio.";
        }
        if(importe < 0){
            return "El importe del comprobante no puede ser negativo.";
        }
        if(oPila.buscar(numero) != null){
            return "Ya existe un comprobante registrado con el número " + numero.trim() + ".";
        }

        boolean insertado = oPila.insertar(new Comprobante(numero.trim(), concepto.trim(), tipo.trim(), importe));
        if(!insertado){
            return "No se pudo apilar el comprobante (la pila está llena).";
        }
        return "";
    }

    // sobrecarga para registrar recibiendo un objeto Comprobante
    public String registrar(Comprobante comprobante){
        if(comprobante == null){
            return "El comprobante no puede ser nulo.";
        }
        return registrar(comprobante.getNumero(), comprobante.getConcepto(), comprobante.getTipo(), comprobante.getImporte());
    }

    // desapila el comprobante de la cima; devuelve mensaje de error o vacio si fue exitoso
    public String desapilar(){
        if(!tienePermiso()){
            return "Acceso denegado: no cuenta con permisos para desapilar comprobantes.";
        }
        if(oPila.estaVacia()){
            return "La pila está vacía. No hay comprobantes para desapilar.";
        }
        Comprobante eliminado = oPila.eliminar();
        if(eliminado == null){
            return "No se pudo desapilar el comprobante.";
        }
        return "";
    }

    // sinonimo para eliminarTope requerido por la consigna
    public String eliminarTope(){
        return desapilar();
    }

    // consulta el comprobante en el tope de la pila sin removerlo
    public Comprobante accesar(){
        return oPila.accesar();
    }

    // sinonimo descriptivo para accesar
    public Comprobante verTope(){
        return oPila.accesar();
    }

    // busca un comprobante por su numero
    public Comprobante buscar(String numero){
        return oPila.buscar(numero);
    }

    // devuelve arreglo con los comprobantes activos para visualizacion en JTable
    public Comprobante[] listar(){
        return oPila.listar();
    }

    // calcula el total acumulado en soles de todos los comprobantes apilados
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
        return oPila.estaVacia();
    }

    public boolean estaLlena(){
        return oPila.estaLlena();
    }

    // restablece la pila a vacia
    public String vaciarPila(){
        if(!tienePermiso()){
            return "Acceso denegado: no cuenta con permisos para vaciar la pila.";
        }
        oPila.pilaVacia();
        return "";
    }

    // carga comprobantes tipicos de prueba para el boton del formulario
    public String cargarDatosPrueba(){
        if(!tienePermiso()){
            return "Acceso denegado: no cuenta con permisos para cargar datos de prueba.";
        }
        // reinicia la pila
        oPila.pilaVacia();

        // apila consumos simulados representativos de un chifa
        registrar("B001-000101", "Consumo Salón - Mesa 2 (Chaulafán y Wantán)", "Boleta", 58.50);
        registrar("B001-000102", "Consumo Salón - Mesa 5 (Arroz Chaufa Especial)", "Boleta", 42.00);
        registrar("F001-000030", "Almuerzo Ejecutivo Corporativo (Mesa 8)", "Factura", 185.00);
        registrar("B001-000103", "Banquete Familiar Tai Lung (Mesa 11)", "Boleta", 260.00);
        registrar("B001-000104", "Consumo Pareja - Mesa 1 (Kam Lu Wantán)", "Boleta", 74.50);

        return "";
    }
}
