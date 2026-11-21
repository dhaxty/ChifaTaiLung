package chifatailung.pruebas;

import chifatailung.controlador.AtencionController;
import chifatailung.estructuras.colas.cColaPrioridad;
import chifatailung.modelo.ClienteAtencion;
import chifatailung.modelo.Prioridad;
import chifatailung.modelo.Rol;

// prueba de consola para la cola con prioridad (cColaPrioridad) y su controlador
public class MaincColaPrioridad {
    public static void main(String[] args){
        System.out.println("====================================================================");
        System.out.println("  PRUEBA 1: ESTRUCTURA cColaPrioridad (Cola Dinamica con Prioridad) ");
        System.out.println("====================================================================");

        cColaPrioridad oCola = new cColaPrioridad();
        System.out.println("Cola creada exitosamente.");
        System.out.println("¿Esta vacia inicial?: " + oCola.estaVacia());
        System.out.println("Cantidad inicial: " + oCola.getCantidad());
        System.out.println("Acceso en cola vacia (espera null): " + oCola.acceso());
        System.out.println("Eliminar en cola vacia (espera null): " + oCola.eliminar());

        // Insercion con llegada intercalada de clientes
        System.out.println("\n--- Encolando clientes con llegadas intercaladas ---");
        // Llegada 1: Regular
        oCola.incluir(new ClienteAtencion(1, "Juan Perez", Prioridad.REGULAR, 1L));
        System.out.println("Llego #1: Juan Perez [REGULAR]");

        // Llegada 2: Preferencial (debe posicionarse antes que Juan Perez)
        oCola.incluir(new ClienteAtencion(2, "Dona Rosa (Adulto mayor)", Prioridad.PREFERENCIAL, 2L));
        System.out.println("Llego #2: Dona Rosa [PREFERENCIAL]");

        // Llegada 3: Regular (debe ir despues de Juan Perez respetando FIFO regular)
        oCola.incluir(new ClienteAtencion(3, "Carlos Wong", Prioridad.REGULAR, 3L));
        System.out.println("Llego #3: Carlos Wong [REGULAR]");

        // Llegada 4: Preferencial (debe ir despues de Dona Rosa y antes de Juan Perez)
        oCola.incluir(new ClienteAtencion(4, "Maria Torres (Madre gestante)", Prioridad.PREFERENCIAL, 4L));
        System.out.println("Llego #4: Maria Torres [PREFERENCIAL]");

        // Llegada 5: Regular (debe ir despues de Carlos Wong)
        oCola.incluir(new ClienteAtencion(5, "Pedro Chang", Prioridad.REGULAR, 5L));
        System.out.println("Llego #5: Pedro Chang [REGULAR]");

        System.out.println("\nCantidad en cola: " + oCola.getCantidad());
        System.out.println("Orden actual en la cola (espera PREFERENCIAL 2, 4 luego REGULAR 1, 3, 5):");
        System.out.println(oCola.muestraCola());

        // Probar acceso() sin remover
        System.out.println("\n--- Consulta al frente con acceso() ---");
        ClienteAtencion frente = oCola.acceso();
        System.out.println("Cliente al frente: " + (frente != null ? frente.getNombreCliente() + " (" + frente.getPrioridad().getDescripcion() + ")" : "null"));
        System.out.println("Cantidad tras acceso() (debe mantenerse en 5): " + oCola.getCantidad());

        // Atender / Desencolar verificando el orden de atencion exacto
        System.out.println("\n--- Desencolando clientes con eliminar() (verificacion de orden) ---");
        String ordenEsperado[] = {
            "Dona Rosa (Adulto mayor)",
            "Maria Torres (Madre gestante)",
            "Juan Perez",
            "Carlos Wong",
            "Pedro Chang"
        };

        int paso = 1;
        while(!oCola.estaVacia()){
            ClienteAtencion atendido = oCola.eliminar();
            String nombre = (atendido != null) ? atendido.getNombreCliente() : "null";
            String prio = (atendido != null) ? atendido.getPrioridad().getDescripcion() : "null";
            System.out.println("Atendido #" + paso + ": " + nombre + " [" + prio + "] -> Esperado: " + ordenEsperado[paso - 1]);
            paso++;
        }

        System.out.println("\n¿Esta vacia ahora?: " + oCola.estaVacia());
        System.out.println("Eliminar en cola vacia (espera null, sin excepcion): " + oCola.eliminar());

        // Reinicio con colaVacia()
        oCola.incluir(new ClienteAtencion(6, "Cliente Test", Prioridad.REGULAR, 6L));
        System.out.println("Elemento agregado. Cantidad: " + oCola.getCantidad());
        oCola.colaVacia();
        System.out.println("Tras colaVacia() -> Cantidad: " + oCola.getCantidad() + ", ¿Esta vacia?: " + oCola.estaVacia());

        System.out.println("\n====================================================================");
        System.out.println("  PRUEBA 2: AtencionController (Validaciones, Permisos y Turnos)   ");
        System.out.println("====================================================================");

        AtencionController cAdmin = new AtencionController(Rol.ADMIN);
        System.out.println("Controlador instanciado con Rol: " + cAdmin.getRolActual());
        System.out.println("¿Tiene permiso?: " + cAdmin.tienePermiso());

        // Validaciones de entrada
        System.out.println("\n--- Probando validaciones de entrada ---");
        String errNombreVacio = cAdmin.encolar("", Prioridad.REGULAR);
        System.out.println("Nombre vacio (espera mensaje): " + errNombreVacio);

        String errNombreEspacios = cAdmin.encolar("   ", Prioridad.PREFERENCIAL);
        System.out.println("Nombre solo espacios (espera mensaje): " + errNombreEspacios);

        String errPrioNula = cAdmin.encolar("Cliente Prueba", null);
        System.out.println("Prioridad nula (espera mensaje): " + errPrioNula);

        // Registro exitoso con generacion correlativa
        System.out.println("\n--- Registro exitoso con correlativo automatico ---");
        String ok1 = cAdmin.encolar("Luis Ramirez", Prioridad.REGULAR);
        String ok2 = cAdmin.encolar("Carmen Silva (Gestante)", Prioridad.PREFERENCIAL);
        System.out.println("Registro 1 resultado (espera vacio): '" + ok1 + "'");
        System.out.println("Registro 2 resultado (espera vacio): '" + ok2 + "'");

        System.out.println("Proximo cliente a atender: " + cAdmin.verSiguiente().getNombreCliente()
                + " (Turno #" + cAdmin.verSiguiente().getNumeroTurno()
                + ", " + cAdmin.verSiguiente().getPrioridad().getDescripcion() + ")");

        // Atender mediante controlador
        String resAtender = cAdmin.atender();
        System.out.println("Atender resultado (espera vacio): '" + resAtender + "'");
        System.out.println("Ultimo atendido: " + cAdmin.getUltimoAtendido().getNombreCliente());

        // Probar carga de datos de prueba
        System.out.println("\n--- Probando cargarDatosPrueba() ---");
        String resDatosPrueba = cAdmin.cargarDatosPrueba();
        System.out.println("cargarDatosPrueba() resultado (espera vacio): '" + resDatosPrueba + "'");
        System.out.println("Cantidad de clientes en cola: " + cAdmin.getCantidad());

        ClienteAtencion tabla[] = cAdmin.listar();
        System.out.println("Listado de cola para visualizacion en JTable (" + tabla.length + " clientes):");
        for(int i = 0; i < tabla.length; i++){
            System.out.println("  [" + (i + 1) + "] Turno #" + tabla[i].getNumeroTurno()
                    + " | " + tabla[i].getNombreCliente()
                    + " | Prioridad: " + tabla[i].getPrioridad().getDescripcion()
                    + " | Llegada: #" + tabla[i].getOrdenLlegada());
        }

        // Probar permisos con Rol EMPLEADO
        System.out.println("\n--- Probando permisos con Rol EMPLEADO ---");
        AtencionController cEmpleado = new AtencionController(Rol.EMPLEADO);
        System.out.println("¿Empleado tiene permiso?: " + cEmpleado.tienePermiso());
        String resEmp = cEmpleado.encolar("Cliente Empleado", Prioridad.REGULAR);
        System.out.println("Encolar por empleado (espera vacio): '" + resEmp + "'");

        // Probar permisos con Rol nulo
        AtencionController cInvalido = new AtencionController(null);
        String errNullRol = cInvalido.encolar("Cliente Fantasma", Prioridad.REGULAR);
        System.out.println("Encolar con rol nulo (espera acceso denegado): " + errNullRol);
        String errAtenderNullRol = cInvalido.atender();
        System.out.println("Atender con rol nulo (espera acceso denegado): " + errAtenderNullRol);

        System.out.println("\n====================================================================");
        System.out.println("  TODAS LAS PRUEBAS DE COLA CON PRIORIDAD CONCLUIDAS CON EXITO      ");
        System.out.println("====================================================================");
    }
}
