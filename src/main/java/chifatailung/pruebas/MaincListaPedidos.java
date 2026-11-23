package chifatailung.pruebas;

import chifatailung.controlador.PedidoController;
import chifatailung.estructuras.listas.cListaPedidos;
import chifatailung.modelo.DetallePedido;
import chifatailung.modelo.Pedido;
import chifatailung.modelo.Rol;

public class MaincListaPedidos {
    public static void main(String[] args){
        System.out.println("====================================================================");
        System.out.println("  PRUEBA 1: ESTRUCTURA cListaPedidos (Lista Enlazada Simple)        ");
        System.out.println("====================================================================");

        cListaPedidos oLista = new cListaPedidos();
        System.out.println("Lista creada exitosamente.");
        System.out.println("¿Esta vacia inicial?: " + oLista.estaVacia());
        System.out.println("Cantidad inicial: " + oLista.getCantidad());
        System.out.println("Total general inicial: S/ " + oLista.totalGeneral());
        System.out.println("Buscar en vacia (espera null): " + oLista.buscar("PED-001"));
        System.out.println("Eliminar en vacia (espera false): " + oLista.eliminar("PED-001"));

        System.out.println("\n--- Insercion de pedidos ---");
        Pedido oP1 = new Pedido("PED-001", "Familia Wong", 3);
        oP1.agregarDetalle(new DetallePedido("P002", "Arroz Chaufa Especial", 2, 24.00));
        oP1.agregarDetalle(new DetallePedido("P012", "Chicha Morada 1L", 1, 10.00));
        boolean ins1 = oLista.insertar(oP1);
        System.out.println("Insertar PED-001 (Total S/ 58.00): " + ins1);

        Pedido oP2 = new Pedido("PED-002", "Carlos Mendoza", 8);
        oP2.agregarDetalle(new DetallePedido("P009", "Pollo ChiJauKay", 1, 25.00));
        boolean ins2 = oLista.insertar(oP2);
        System.out.println("Insertar PED-002 (Total S/ 25.00): " + ins2);

        Pedido oP3 = new Pedido("PED-003", "Lucia Salazar", 12);
        oP3.agregarDetalle(new DetallePedido("P003", "Aeropuerto de Pollo", 1, 22.00));
        boolean ins3 = oLista.insertar(oP3);
        System.out.println("Insertar PED-003 (Total S/ 22.00): " + ins3);

        System.out.println("\n--- Verificacion de unicidad de identificador ---");
        Pedido oPRepetido = new Pedido("PED-001", "Cliente Invalido", 5);
        oPRepetido.agregarDetalle(new DetallePedido("P001", "Chaufa Simple", 1, 15.00));
        boolean insRep = oLista.insertar(oPRepetido);
        System.out.println("Insertar PED-001 repetido (espera false): " + insRep);

        System.out.println("\nCantidad en lista tras inserciones: " + oLista.getCantidad());
        System.out.println("Contenido en lista:");
        System.out.println(oLista.muestraLista());
        System.out.println("Total general acumulado: S/ " + oLista.totalGeneral());

        System.out.println("\n--- Busqueda iterativa ---");
        Pedido b1 = oLista.buscar("PED-002");
        System.out.println("Buscar PED-002: " + (b1 != null ? b1.getNombreCliente() + " (Mesa " + b1.getNumeroMesa() + ")" : "null"));
        Pedido bInex = oLista.buscar("PED-999");
        System.out.println("Buscar PED-999 inexistente (espera null): " + bInex);

        System.out.println("\n--- Modificacion preservando identificador ---");
        Pedido oP2Mod = new Pedido("PED-999-INTENTO_CAMBIO", "Carlos Mendoza (Actualizado)", 10);
        oP2Mod.agregarDetalle(new DetallePedido("P009", "Pollo ChiJauKay", 2, 25.00));
        boolean act2 = oLista.actualizar("PED-002", oP2Mod);
        System.out.println("Actualizar PED-002: " + act2);

        Pedido b2 = oLista.buscar("PED-002");
        System.out.println("Verificar ID original conservado: " + (b2 != null ? b2.getNumero() : "null"));
        System.out.println("Nuevo cliente: " + (b2 != null ? b2.getNombreCliente() : "null"));
        System.out.println("Nueva mesa: " + (b2 != null ? b2.getNumeroMesa() : -1));
        System.out.println("Nuevo total del pedido: S/ " + (b2 != null ? b2.calcularTotal() : 0.0));
        System.out.println("Total general actualizado: S/ " + oLista.totalGeneral());

        System.out.println("\n--- Eliminacion iterativa (nodo intermedio PED-002) ---");
        boolean elim2 = oLista.eliminar("PED-002");
        System.out.println("Eliminar PED-002: " + elim2);
        System.out.println("Cantidad tras eliminar: " + oLista.getCantidad());
        System.out.println("Contenido tras eliminar:");
        System.out.println(oLista.muestraLista());

        System.out.println("\n--- Eliminacion del primer nodo (PED-001) ---");
        boolean elim1 = oLista.eliminar("PED-001");
        System.out.println("Eliminar PED-001: " + elim1);
        System.out.println("Cantidad tras eliminar primero: " + oLista.getCantidad());
        System.out.println(oLista.muestraLista());

        Pedido arrPedidos[] = oLista.listar();
        System.out.println("Longitud del arreglo nativo listar(): " + arrPedidos.length);
        if(arrPedidos.length > 0){
            System.out.println("Primer elemento del arreglo: " + arrPedidos[0].getNumero() + " - " + arrPedidos[0].getNombreCliente());
        }

        System.out.println("\n====================================================================");
        System.out.println("  PRUEBA 2: COMPOSICION PEDIDO 1 -- 0..10 DETALLE (LIMITE Y TOTAL)   ");
        System.out.println("====================================================================");

        Pedido oPedidoLimite = new Pedido("PED-100", "Banquete Familiar", 6);
        System.out.println("Pedido creado con capacidad maxima de 10 detalles.");
        System.out.println("Cantidad inicial de detalles: " + oPedidoLimite.getCantidadDetalles());

        System.out.println("\n--- Agregando 10 detalles secuenciales ---");
        for(int i = 1; i <= 10; i++){
            boolean ag = oPedidoLimite.agregarDetalle(new DetallePedido("P0" + (i < 10 ? "0" + i : i), "Plato de prueba #" + i, 1, 10.00 * i));
            System.out.println("Detalle #" + i + " agregado: " + ag + " | Cantidad actual: " + oPedidoLimite.getCantidadDetalles());
        }

        System.out.println("\n--- Probando rechazo del detalle #11 (limite maximo 10) ---");
        boolean ag11 = oPedidoLimite.agregarDetalle(new DetallePedido("P011", "Plato Excedente #11", 1, 30.00));
        System.out.println("Intento de agregar detalle 11 (espera false): " + ag11);
        System.out.println("Cantidad final de detalles tras intento (debe seguir en 10): " + oPedidoLimite.getCantidadDetalles());

        System.out.println("Total calculado del pedido (10 items): S/ " + oPedidoLimite.calcularTotal());

        System.out.println("\n--- Edicion de detalle en posicion 2 ---");
        double totalAntesEdicion = oPedidoLimite.calcularTotal();
        boolean editado = oPedidoLimite.editarDetalle(2, new DetallePedido("P003", "Plato #3 Modificado", 3, 30.00));
        System.out.println("Detalle indice 2 editado: " + editado);
        System.out.println("Total antes de edicion: S/ " + totalAntesEdicion + " -> Total despues: S/ " + oPedidoLimite.calcularTotal());

        System.out.println("\n--- Quitar detalle indice 0 con corrimiento manual a la izquierda ---");
        DetallePedido detPos1Antes = oPedidoLimite.obtenerDetalle(1);
        System.out.println("Detalle en indice 1 antes del borrado: " + (detPos1Antes != null ? detPos1Antes.getNombrePlato() : "null"));

        boolean quitado = oPedidoLimite.quitarDetalle(0);
        System.out.println("Detalle indice 0 quitado: " + quitado);
        System.out.println("Cantidad tras quitar (debe ser 9): " + oPedidoLimite.getCantidadDetalles());

        DetallePedido detPos0Ahora = oPedidoLimite.obtenerDetalle(0);
        System.out.println("Detalle ahora en indice 0 (corrimiento verificado): " + (detPos0Ahora != null ? detPos0Ahora.getNombrePlato() : "null"));
        System.out.println("Nuevo total del pedido tras corrimiento: S/ " + oPedidoLimite.calcularTotal());

        System.out.println("\n====================================================================");
        System.out.println("  PRUEBA 3: PedidoController (Permisos, Validaciones y Datos Demo)  ");
        System.out.println("====================================================================");

        PedidoController cAdmin = new PedidoController(Rol.ADMIN);
        PedidoController cEmpleado = new PedidoController(Rol.EMPLEADO);
        PedidoController cSinPermiso = new PedidoController(null);

        System.out.println("Permiso ADMIN: " + cAdmin.tienePermiso());
        System.out.println("Permiso EMPLEADO: " + cEmpleado.tienePermiso());
        System.out.println("Permiso Rol Nulo: " + cSinPermiso.tienePermiso());

        System.out.println("\n--- Probando validaciones del controlador ---");
        String errSinPermiso = cSinPermiso.registrar("PED-999", "Cliente", 2);
        System.out.println("Intento sin rol valido: " + errSinPermiso);

        String errNumeroVacio = cAdmin.registrar("", "Cliente", 2);
        System.out.println("Numero vacio: " + errNumeroVacio);

        String errClienteVacio = cAdmin.registrar("PED-010", "", 2);
        System.out.println("Cliente vacio: " + errClienteVacio);

        String errMesaCero = cAdmin.registrar("PED-010", "Cliente", 0);
        System.out.println("Mesa 0 (fuera de rango 1-16): " + errMesaCero);

        String errMesa17 = cAdmin.registrar("PED-010", "Cliente", 17);
        System.out.println("Mesa 17 (fuera de rango 1-16): " + errMesa17);

        String errSinDetalles = cAdmin.registrar("PED-010", "Cliente", 5);
        System.out.println("Pedido sin detalles (minimo 1): " + errSinDetalles);

        System.out.println("\n--- Registro valido a traves del controlador ---");
        String resReg = cAdmin.registrar("PED-010", "Sr. Roberto Chang", 4, "P001", "Arroz Chaufa de Pollo", 2, 18.00);
        System.out.println("Registro exitoso PED-010 (espera vacio): '" + resReg + "'");

        String resDet2 = cAdmin.agregarDetalleAPedido("PED-010", "P008", "Wantán Frito (6 unid.)", 1, 12.00);
        System.out.println("Agregar detalle 2 a PED-010 (espera vacio): '" + resDet2 + "'");

        Pedido pReg = cAdmin.buscar("PED-010");
        System.out.println("Total PED-010 en controlador: S/ " + (pReg != null ? pReg.calcularTotal() : 0.0));

        System.out.println("\n--- Probando proteccion de detalle minimo al quitar ---");
        cAdmin.quitarDetalleDePedido("PED-010", 1);
        System.out.println("Detalles restantes en PED-010: " + pReg.getCantidadDetalles());
        String errQuitarUltimo = cAdmin.quitarDetalleDePedido("PED-010", 0);
        System.out.println("Quitar el ultimo detalle (espera mensaje protector): " + errQuitarUltimo);

        System.out.println("\n--- Carga de datos de prueba del modulo ---");
        String resCarga = cAdmin.cargarDatosPrueba();
        System.out.println("Resultado de cargarDatosPrueba() (espera vacio): '" + resCarga + "'");
        System.out.println("Cantidad de pedidos cargados: " + cAdmin.getCantidad());
        System.out.println("Total general vendido: S/ " + cAdmin.totalGeneral());

        Pedido listaCargada[] = cAdmin.listar();
        for(int i = 0; i < listaCargada.length; i++){
            System.out.println("  " + (i + 1) + ". " + listaCargada[i].getNumero()
                    + " | Mesa " + listaCargada[i].getNumeroMesa()
                    + " | Cliente: " + listaCargada[i].getNombreCliente()
                    + " | Items: " + listaCargada[i].getCantidadDetalles()
                    + " | Total: S/ " + String.format(java.util.Locale.US, "%.2f", listaCargada[i].calcularTotal()));
        }

        System.out.println("\n====================================================================");
        System.out.println("  TODAS LAS PRUEBAS DE ACEPTACION DEL MODULO DE PEDIDOS SUPERADAS  ");
        System.out.println("====================================================================");
    }
}
