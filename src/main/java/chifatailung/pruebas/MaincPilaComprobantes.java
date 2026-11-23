package chifatailung.pruebas;

import chifatailung.controlador.ComprobanteController;
import chifatailung.estructuras.pilas.cPilaComprobantes;
import chifatailung.modelo.Comprobante;
import chifatailung.modelo.Rol;

public class MaincPilaComprobantes {
    public static void main(String[] args){
        System.out.println("==========================================================");
        System.out.println("  PRUEBA 1: ESTRUCTURA cPilaComprobantes (Pila Estatica)  ");
        System.out.println("==========================================================");

        cPilaComprobantes oPila = new cPilaComprobantes(50);
        System.out.println("Pila creada con capacidad: " + oPila.getCapacidad());
        System.out.println("¿Esta vacia inicial?: " + oPila.estaVacia());
        System.out.println("Tope inicial: " + oPila.getTope());
        System.out.println("Accesar en vacio (espera null): " + oPila.accesar());
        System.out.println("Eliminar en vacio (espera null): " + oPila.eliminar());

        System.out.println("\n--- Apilando 3 comprobantes iniciales ---");
        oPila.insertar(new Comprobante("B001-000001", "Chaufa de Pollo + Sopa Wantan", "Boleta", 32.50));
        oPila.insertar(new Comprobante("B001-000002", "Aeropuerto Especial + Gaseosa", "Boleta", 38.00));
        oPila.insertar(new Comprobante("F001-000001", "Banquete Tai Lung Familiar", "Factura", 195.00));

        System.out.println("Cantidad apilada: " + oPila.getCantidad() + " / " + oPila.getCapacidad());
        System.out.println("Tope actual: " + oPila.getTope());
        System.out.println("Contenido actual de la pila:");
        System.out.println(oPila.muestraArreglo());

        System.out.println("\n--- Consulta de cima con accesar() ---");
        Comprobante topeObj = oPila.accesar();
        System.out.println("Tope consultado: " + (topeObj != null ? topeObj.getNumero() + " (" + topeObj.getConcepto() + ")" : "null"));
        System.out.println("Cantidad tras accesar (debe seguir en 3): " + oPila.getCantidad());

        System.out.println("\n--- Desapilando con eliminar() (comportamiento LIFO) ---");
        Comprobante desapilado1 = oPila.eliminar();
        System.out.println("1er Desapilado (espera F001-000001): " + (desapilado1 != null ? desapilado1.getNumero() : "null"));
        Comprobante desapilado2 = oPila.eliminar();
        System.out.println("2do Desapilado (espera B001-000002): " + (desapilado2 != null ? desapilado2.getNumero() : "null"));
        System.out.println("Cantidad tras 2 desapilados (debe ser 1): " + oPila.getCantidad());
        System.out.println("Pila restante: " + oPila.muestraArreglo());

        Comprobante desapilado3 = oPila.eliminar();
        System.out.println("3er Desapilado (espera B001-000001): " + (desapilado3 != null ? desapilado3.getNumero() : "null"));
        System.out.println("¿Esta vacia ahora?: " + oPila.estaVacia());
        System.out.println("Eliminar en pila vacia (espera null, sin excepcion): " + oPila.eliminar());

        System.out.println("\n--- Probando llenado hasta capacidad maxima (50/50) ---");
        for(int i = 1; i <= 50; i++){
            String num = String.format("B002-%06d", i);
            boolean ok = oPila.insertar(new Comprobante(num, "Consumo " + i, "Boleta", 20.0 + i));
            if(!ok){
                System.out.println("ERROR: Fallo al insertar elemento " + i);
            }
        }
        System.out.println("Cantidad tras 50 inserciones: " + oPila.getCantidad() + " / " + oPila.getCapacidad());
        System.out.println("¿Esta llena?: " + oPila.estaLlena());
        System.out.println("Tope actual: " + oPila.getTope());

        System.out.println("\n--- Intentando insertar elemento 51 (debe devolver false) ---");
        boolean insert51 = oPila.insertar(new Comprobante("B002-000051", "Consumo Excedente", "Boleta", 99.0));
        System.out.println("Resultado de insertar elemento 51 (espera false): " + insert51);
        System.out.println("Cantidad tras intento 51 (debe seguir en 50): " + oPila.getCantidad());

        oPila.pilaVacia();
        System.out.println("Tras pilaVacia() -> Cantidad: " + oPila.getCantidad() + ", ¿Esta vacia?: " + oPila.estaVacia());

        System.out.println("\n==========================================================");
        System.out.println("  PRUEBA 2: ComprobanteController (Reglas y Permisos)     ");
        System.out.println("==========================================================");

        ComprobanteController cAdmin = new ComprobanteController(Rol.ADMIN);
        System.out.println("Controlador instanciado con Rol: " + cAdmin.getRolActual());
        System.out.println("¿Tiene permiso?: " + cAdmin.tienePermiso());

        System.out.println("\n--- Probando validaciones de entrada ---");
        String errNumVacio = cAdmin.registrar("", "Consumo", "Boleta", 50.0);
        System.out.println("Numero vacio (espera mensaje): " + errNumVacio);

        String errConcVacio = cAdmin.registrar("B003-000001", "   ", "Boleta", 50.0);
        System.out.println("Concepto vacio (espera mensaje): " + errConcVacio);

        String errTipoVacio = cAdmin.registrar("B003-000001", "Consumo", "", 50.0);
        System.out.println("Tipo vacio (espera mensaje): " + errTipoVacio);

        String errImpNeg = cAdmin.registrar("B003-000001", "Consumo", "Boleta", -15.0);
        System.out.println("Importe negativo (espera mensaje): " + errImpNeg);

        String exito1 = cAdmin.registrar("B003-000001", "Tallarin Saltado Especial", "Boleta", 35.00);
        System.out.println("Registro exitoso 1 (espera vacio): '" + exito1 + "'");

        String errDup = cAdmin.registrar("B003-000001", "Otro Consumo", "Boleta", 40.00);
        System.out.println("Registro duplicado (espera mensaje): " + errDup);

        cAdmin.registrar("F003-000001", "Consumo Corporativo Reunion", "Factura", 120.00);
        System.out.println("Total importes acumulado: S/." + String.format(java.util.Locale.US, "%.2f", cAdmin.totalImportes()));

        System.out.println("Tope antes de desapilar: " + cAdmin.accesar().getNumero());
        String errDesapilar = cAdmin.desapilar();
        System.out.println("Desapilar exitoso (espera vacio): '" + errDesapilar + "'");
        System.out.println("Nuevo tope tras desapilar: " + cAdmin.accesar().getNumero());

        System.out.println("\n--- Probando cargarDatosPrueba() ---");
        String resPrueba = cAdmin.cargarDatosPrueba();
        System.out.println("Resultado cargarDatosPrueba (espera vacio): '" + resPrueba + "'");
        System.out.println("Cantidad de comprobantes cargados: " + cAdmin.getCantidad());
        System.out.println("Total recaudado en datos de prueba: S/." + String.format(java.util.Locale.US, "%.2f", cAdmin.totalImportes()));

        Comprobante lista[] = cAdmin.listar();
        System.out.println("Listado para JTable (" + lista.length + " elementos):");
        for(int i = 0; i < lista.length; i++){
            System.out.println("  [" + (i + 1) + "] " + lista[i].getNumero() + " | "
                    + lista[i].getTipo() + " | " + lista[i].getConcepto() + " | S/." + lista[i].getImporte());
        }

        System.out.println("\n--- Probando controlador con Rol EMPLEADO ---");
        ComprobanteController cEmpleado = new ComprobanteController(Rol.EMPLEADO);
        System.out.println("¿Empleado tiene permiso?: " + cEmpleado.tienePermiso());
        String resEmp = cEmpleado.registrar("B004-000001", "Sopa Fuchifu Familiar", "Boleta", 28.00);
        System.out.println("Registro por empleado (espera vacio): '" + resEmp + "'");

        ComprobanteController cInvalido = new ComprobanteController(null);
        String errNullRol = cInvalido.registrar("B005-000001", "Test", "Boleta", 10.0);
        System.out.println("Registro con rol nulo (espera acceso denegado): " + errNullRol);

        System.out.println("\n==========================================================");
        System.out.println("  TODAS LAS PRUEBAS DE COMPROBANTES CONCLUIDAS CON EXITO  ");
        System.out.println("==========================================================");
    }
}
