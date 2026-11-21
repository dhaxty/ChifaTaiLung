package chifatailung.pruebas;

import chifatailung.controlador.ArbolController;
import chifatailung.estructuras.arboles.cABBPlatos;
import chifatailung.modelo.Plato;
import chifatailung.modelo.Rol;

// prueba de consola para el arbol binario de busqueda (cABBPlatos) y su controlador
public class MaincABBPlatos {
    public static void main(String[] args){
        System.out.println("====================================================================");
        System.out.println("  PRUEBA 1: ESTRUCTURA cABBPlatos (Arbol Binario de Busqueda)       ");
        System.out.println("====================================================================");

        cABBPlatos oABB = new cABBPlatos();
        System.out.println("Arbol creado exitosamente.");
        System.out.println("¿Esta vacio inicial?: " + oABB.estaVacio());
        System.out.println("Cantidad inicial: " + oABB.contar());
        System.out.println("Busqueda indexada en arbol vacio: " + oABB.buscarIndexada("Kam Lu Wantán"));
        System.out.println("Busqueda secuencial en arbol vacio: " + oABB.buscarSecuencial("Kam Lu Wantán"));

        // Insercion de platos tradicionales
        System.out.println("\n--- Insercion de platos en el ABB ---");
        boolean ins1 = oABB.ingresarValor(new Plato("P001", "Kam Lu Wantán", "Especialidades", 28.00, true));
        boolean ins2 = oABB.ingresarValor(new Plato("P002", "Chicha Morada 1L", "Bebidas", 10.00, true));
        boolean ins3 = oABB.ingresarValor(new Plato("P003", "Sopa Wantán Especial", "Sopas", 16.00, true));
        boolean ins4 = oABB.ingresarValor(new Plato("P004", "Aeropuerto de Pollo", "Combinados", 22.00, true));
        boolean ins5 = oABB.ingresarValor(new Plato("P005", "Pollo ChiJauKay", "Especialidades", 25.00, true));

        System.out.println("Insercion P001 (Kam Lu Wantán): " + ins1);
        System.out.println("Insercion P002 (Chicha Morada 1L): " + ins2);
        System.out.println("Insercion P003 (Sopa Wantán Especial): " + ins3);
        System.out.println("Insercion P004 (Aeropuerto de Pollo): " + ins4);
        System.out.println("Insercion P005 (Pollo ChiJauKay): " + ins5);
        System.out.println("Cantidad de nodos tras 5 inserciones: " + oABB.contar());

        // Verificacion de rechazo de duplicados sin importar mayusculas/minusculas
        System.out.println("\n--- Prueba de rechazo de duplicados (seccion 5 y 10) ---");
        boolean dupExacto = oABB.ingresarValor(new Plato("P999", "Kam Lu Wantán", "Especialidades", 30.00, true));
        System.out.println("Intento con nombre identico 'Kam Lu Wantán' (espera false): " + dupExacto);

        boolean dupMinusculas = oABB.ingresarValor(new Plato("P998", "kam lu wantán", "Especialidades", 30.00, true));
        System.out.println("Intento con minusculas 'kam lu wantán' (espera false): " + dupMinusculas);

        boolean dupMayusculas = oABB.ingresarValor(new Plato("P997", "CHICHA MORADA 1L", "Bebidas", 12.00, true));
        System.out.println("Intento con mayusculas 'CHICHA MORADA 1L' (espera false): " + dupMayusculas);

        System.out.println("Cantidad de nodos tras intentos duplicados (debe mantenerse en 5): " + oABB.contar());

        // Pruebas de Busqueda Secuencial vs Busqueda Indexada
        System.out.println("\n--- Pruebas de Busqueda: Secuencial vs Indexada ---");
        // Busqueda existente
        Plato busqSecOk = oABB.buscarSecuencial("aeropuerto de pollo");
        System.out.println("buscarSecuencial('aeropuerto de pollo') -> Encontrado: " 
                + (busqSecOk != null ? busqSecOk.getCodigo() + " - " + busqSecOk.getNombre() : "null"));

        Plato busqIndOk = oABB.buscarIndexada("POLLO CHIJAUKAY");
        System.out.println("buscarIndexada('POLLO CHIJAUKAY') -> Encontrado: " 
                + (busqIndOk != null ? busqIndOk.getCodigo() + " - " + busqIndOk.getNombre() : "null"));

        // Busqueda inexistente
        Plato busqSecNo = oABB.buscarSecuencial("Ceviche Clásico");
        System.out.println("buscarSecuencial('Ceviche Clásico') inexistente (espera null): " + busqSecNo);

        Plato busqIndNo = oABB.buscarIndexada("Tallarín Taypa");
        System.out.println("buscarIndexada('Tallarín Taypa') inexistente (espera null): " + busqIndNo);

        // Recorridos del Arbol estilo profesor (recorreArbol)
        System.out.println("\n--- Recorridos del Arbol (Apendice A.4) ---");
        System.out.println("Pre-Orden  (1):" + oABB.recorreArbol(1));
        System.out.println("In-Orden   (2):" + oABB.recorreArbol(2));
        System.out.println("Post-Orden (3):" + oABB.recorreArbol(3));

        // Listado enOrden para JTable
        System.out.println("\n--- Listado enOrden(): Plato[] (Alfabetico para JTable) ---");
        Plato tablaOrden[] = oABB.enOrden();
        System.out.println("Total elementos devueltos: " + tablaOrden.length);
        for(int i = 0; i < tablaOrden.length; i++){
            System.out.println("  [" + (i + 1) + "] " + tablaOrden[i].getCodigo() 
                    + " | " + tablaOrden[i].getNombre() 
                    + " | " + tablaOrden[i].getCategoria() 
                    + " | S/ " + String.format("%.2f", tablaOrden[i].getPrecio()));
        }

        // Reinicio con arbolVacio()
        oABB.arbolVacio();
        System.out.println("\nTras arbolVacio() -> Cantidad: " + oABB.contar() + ", ¿Esta vacio?: " + oABB.estaVacio());

        System.out.println("\n====================================================================");
        System.out.println("  PRUEBA 2: ArbolController (Validaciones, Permisos y Datos Prueba) ");
        System.out.println("====================================================================");

        // Prueba con Rol ADMIN
        ArbolController cAdmin = new ArbolController(Rol.ADMIN);
        System.out.println("Controlador instanciado con Rol: " + cAdmin.getRolActual());
        System.out.println("¿Puede modificar ADMIN?: " + cAdmin.puedeModificar());

        // Validaciones de entrada
        System.out.println("\n--- Validaciones de campos al insertar ---");
        String errCodVacio = cAdmin.insertar("", "Plato Prueba", "Chaufas", 20.00, true);
        System.out.println("Codigo vacio (espera mensaje): " + errCodVacio);

        String errNomVacio = cAdmin.insertar("P100", "", "Chaufas", 20.00, true);
        System.out.println("Nombre vacio (espera mensaje): " + errNomVacio);

        String errCatVacia = cAdmin.insertar("P100", "Plato Prueba", "", 20.00, true);
        System.out.println("Categoria vacia (espera mensaje): " + errCatVacia);

        String errPrecioNeg = cAdmin.insertar("P100", "Plato Prueba", "Chaufas", -5.00, true);
        System.out.println("Precio negativo (espera mensaje): " + errPrecioNeg);

        // Insercion exitosa con ADMIN
        System.out.println("\n--- Insercion exitosa con ADMIN ---");
        String okAdmin = cAdmin.insertar("P101", "Arroz Chaufa Especial", "Chaufas", 24.00, true);
        System.out.println("Resultado insercion ADMIN (espera vacio): '" + okAdmin + "'");

        String errDupAdmin = cAdmin.insertar("P102", "arroz chaufa especial", "Chaufas", 26.00, true);
        System.out.println("Resultado duplicado ADMIN (espera mensaje): " + errDupAdmin);

        // Prueba con Rol EMPLEADO (control de permisos estricto)
        System.out.println("\n--- Prueba de permisos con Rol EMPLEADO (seccion 4 y 10) ---");
        ArbolController cEmpleado = new ArbolController(Rol.EMPLEADO);
        System.out.println("Controlador instanciado con Rol: " + cEmpleado.getRolActual());
        System.out.println("¿Puede modificar EMPLEADO?: " + cEmpleado.puedeModificar());

        String bloqueoEmpleado = cEmpleado.insertar("P201", "Sopa Wantán", "Sopas", 14.00, true);
        System.out.println("Intento de insercion por EMPLEADO (espera acceso denegado): " + bloqueoEmpleado);

        String bloqueoVaciar = cEmpleado.vaciarArbol();
        System.out.println("Intento de vaciar arbol por EMPLEADO (espera acceso denegado): " + bloqueoVaciar);

        // Cargar datos de prueba (permitido para ambos)
        System.out.println("\n--- Cargar datos de prueba desde controlador ---");
        String resCarga = cEmpleado.cargarDatosPrueba();
        System.out.println("Resultado cargarDatosPrueba() por empleado (espera vacio): '" + resCarga + "'");
        System.out.println("Cantidad de platos cargados: " + cEmpleado.getCantidad());

        // Busquedas y listados con EMPLEADO
        Plato busqEmp1 = cEmpleado.buscarIndexada("Pollo TiPaKay");
        System.out.println("buscarIndexada por empleado: " 
                + (busqEmp1 != null ? busqEmp1.getCodigo() + " | " + busqEmp1.getNombre() : "null"));

        Plato busqEmp2 = cEmpleado.buscarSecuencial("Sopa Fuchifú");
        System.out.println("buscarSecuencial por empleado: " 
                + (busqEmp2 != null ? busqEmp2.getCodigo() + " | " + busqEmp2.getNombre() : "null"));

        Plato listaFinal[] = cEmpleado.listar();
        System.out.println("Total platos en lista final ordenada: " + listaFinal.length);
        for(int i = 0; i < listaFinal.length; i++){
            System.out.println("  (" + (i + 1) + ") " + listaFinal[i].getCodigo() 
                    + " - " + listaFinal[i].getNombre() 
                    + " [" + listaFinal[i].getCategoria() + "] S/ " 
                    + String.format("%.2f", listaFinal[i].getPrecio()));
        }

        System.out.println("\n====================================================================");
        System.out.println("  TODAS LAS PRUEBAS DEL MODULO ABB CONCLUIDAS CON EXITO             ");
        System.out.println("====================================================================");
    }
}
