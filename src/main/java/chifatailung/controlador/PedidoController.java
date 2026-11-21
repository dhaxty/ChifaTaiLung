package chifatailung.controlador;

import chifatailung.estructuras.listas.cListaPedidos;
import chifatailung.modelo.DetallePedido;
import chifatailung.modelo.Pedido;
import chifatailung.modelo.Rol;

// controlador del modulo de pedidos con validaciones de negocio y control de permisos
public class PedidoController {
    private Rol rolActual;
    private cListaPedidos oLista;

    public PedidoController(Rol rolActual){
        this(rolActual, new cListaPedidos());
    }

    public PedidoController(Rol rolActual, cListaPedidos oLista){
        this.rolActual = rolActual;
        this.oLista = (oLista != null) ? oLista : new cListaPedidos();
    }

    public Rol getRolActual(){
        return rolActual;
    }

    public cListaPedidos getLista(){
        return oLista;
    }

    // segun matriz de permisos de la seccion 4, tanto ADMIN como EMPLEADO gestionan pedidos
    public boolean tienePermiso(){
        return rolActual != null && (rolActual == Rol.ADMIN || rolActual == Rol.EMPLEADO);
    }

    public int getCantidad(){
        return oLista.getCantidad();
    }

    public double totalGeneral(){
        return oLista.totalGeneral();
    }

    // valida permisos y campos completos antes de insertar el pedido en la lista
    public String registrar(Pedido pedido){
        if(!tienePermiso()){
            return "Acceso denegado: no cuenta con permisos para registrar pedidos.";
        }
        if(pedido == null){
            return "El pedido no puede ser nulo.";
        }
        if(pedido.getNumero() == null || pedido.getNumero().trim().isEmpty()){
            return "El número de pedido es obligatorio.";
        }
        if(oLista.buscar(pedido.getNumero().trim()) != null){
            return "Ya existe un pedido registrado con el número " + pedido.getNumero().trim() + ".";
        }
        if(pedido.getNombreCliente() == null || pedido.getNombreCliente().trim().isEmpty()){
            return "El nombre del cliente es obligatorio.";
        }
        if(pedido.getNumeroMesa() < 1 || pedido.getNumeroMesa() > 16){
            return "El número de mesa debe estar entre 1 y 16.";
        }
        if(pedido.getCantidadDetalles() < 1){
            return "El pedido debe contener al menos 1 detalle.";
        }
        if(pedido.getCantidadDetalles() > 10){
            return "El pedido no puede superar el límite máximo de 10 detalles.";
        }

        // validacion de cada detalle del pedido
        for(int i = 0; i < pedido.getCantidadDetalles(); i++){
            DetallePedido det = pedido.getDetalles()[i];
            if(det == null){
                return "El detalle #" + (i + 1) + " no puede ser nulo.";
            }
            if(det.getCodigoPlato() == null || det.getCodigoPlato().trim().isEmpty()){
                return "El código de plato en el detalle #" + (i + 1) + " es obligatorio.";
            }
            if(det.getNombrePlato() == null || det.getNombrePlato().trim().isEmpty()){
                return "El nombre de plato en el detalle #" + (i + 1) + " es obligatorio.";
            }
            if(det.getCantidad() < 1){
                return "La cantidad en el detalle #" + (i + 1) + " debe ser al menos 1.";
            }
            if(det.getPrecioUnitario() < 0){
                return "El precio unitario en el detalle #" + (i + 1) + " no puede ser negativo.";
            }
        }

        boolean insertado = oLista.insertar(pedido);
        if(!insertado){
            return "No se pudo registrar el pedido en la lista.";
        }
        return "";
    }

    // sobrecarga para registrar un pedido recibiendo un arreglo de detalles
    public String registrar(String numero, String nombreCliente, int numeroMesa, DetallePedido detalles[]){
        Pedido nuevoPedido = new Pedido(numero, nombreCliente, numeroMesa);
        if(detalles != null){
            for(int i = 0; i < detalles.length; i++){
                if(detalles[i] != null){
                    nuevoPedido.agregarDetalle(detalles[i]);
                }
            }
        }
        return registrar(nuevoPedido);
    }

    // sobrecarga para registrar un pedido inicial con su primer detalle
    public String registrar(String numero, String nombreCliente, int numeroMesa,
                            String codigoPlato, String nombrePlato, int cantidad, double precioUnitario){
        Pedido nuevoPedido = new Pedido(numero, nombreCliente, numeroMesa);
        nuevoPedido.agregarDetalle(new DetallePedido(codigoPlato, nombrePlato, cantidad, precioUnitario));
        return registrar(nuevoPedido);
    }

    // sobrecarga que valida regla de minimo 1 detalle al intentar crear un pedido sin items
    public String registrar(String numero, String nombreCliente, int numeroMesa){
        Pedido nuevoPedido = new Pedido(numero, nombreCliente, numeroMesa);
        return registrar(nuevoPedido);
    }

    // modifica cliente y mesa de un pedido existente preservando su identificador original
    public String actualizar(String numero, String nombreCliente, int numeroMesa){
        if(!tienePermiso()){
            return "Acceso denegado: no cuenta con permisos para modificar pedidos.";
        }
        if(numero == null || numero.trim().isEmpty()){
            return "El número de pedido es obligatorio.";
        }
        if(nombreCliente == null || nombreCliente.trim().isEmpty()){
            return "El nombre del cliente es obligatorio.";
        }
        if(numeroMesa < 1 || numeroMesa > 16){
            return "El número de mesa debe estar entre 1 y 16.";
        }

        Pedido existente = oLista.buscar(numero.trim());
        if(existente == null){
            return "No se encontró ningún pedido con el número: " + numero.trim();
        }

        existente.setNombreCliente(nombreCliente.trim());
        existente.setNumeroMesa(numeroMesa);
        return "";
    }

    // sobrecarga para actualizar datos y detalles de un pedido completo preservando el numero
    public String actualizar(String id, Pedido datosNuevos){
        if(!tienePermiso()){
            return "Acceso denegado: no cuenta con permisos para modificar pedidos.";
        }
        if(id == null || id.trim().isEmpty()){
            return "El identificador del pedido es obligatorio.";
        }
        if(datosNuevos == null){
            return "Los datos nuevos del pedido no pueden ser nulos.";
        }
        if(datosNuevos.getNombreCliente() == null || datosNuevos.getNombreCliente().trim().isEmpty()){
            return "El nombre del cliente es obligatorio.";
        }
        if(datosNuevos.getNumeroMesa() < 1 || datosNuevos.getNumeroMesa() > 16){
            return "El número de mesa debe estar entre 1 y 16.";
        }
        if(datosNuevos.getCantidadDetalles() < 1){
            return "El pedido debe conservar al menos 1 detalle.";
        }
        if(datosNuevos.getCantidadDetalles() > 10){
            return "El pedido no puede superar el límite máximo de 10 detalles.";
        }

        boolean actualizado = oLista.actualizar(id.trim(), datosNuevos);
        if(!actualizado){
            return "No se encontró ningún pedido con el número: " + id.trim();
        }
        return "";
    }

    // elimina un pedido de la lista por su identificador
    public String eliminar(String numero){
        if(!tienePermiso()){
            return "Acceso denegado: no cuenta con permisos para eliminar pedidos.";
        }
        if(numero == null || numero.trim().isEmpty()){
            return "El número de pedido es obligatorio.";
        }

        boolean eliminado = oLista.eliminar(numero.trim());
        if(!eliminado){
            return "No se encontró ningún pedido con el número: " + numero.trim();
        }
        return "";
    }

    // busca un pedido por su identificador unico
    public Pedido buscar(String numero){
        if(numero == null || numero.trim().isEmpty()){
            return null;
        }
        return oLista.buscar(numero.trim());
    }

    // devuelve todos los pedidos para alimentar la tabla principal
    public Pedido[] listar(){
        return oLista.listar();
    }

    // agrega un detalle a un pedido existente validando que no exceda 10 items
    public String agregarDetalleAPedido(String numeroPedido, DetallePedido detalle){
        if(!tienePermiso()){
            return "Acceso denegado: no cuenta con permisos para modificar pedidos.";
        }
        if(numeroPedido == null || numeroPedido.trim().isEmpty()){
            return "El número de pedido es obligatorio.";
        }
        Pedido pedido = oLista.buscar(numeroPedido.trim());
        if(pedido == null){
            return "No se encontró el pedido: " + numeroPedido.trim();
        }
        if(detalle == null){
            return "El detalle a agregar no puede ser nulo.";
        }
        if(detalle.getCodigoPlato() == null || detalle.getCodigoPlato().trim().isEmpty()){
            return "El código de plato es obligatorio.";
        }
        if(detalle.getNombrePlato() == null || detalle.getNombrePlato().trim().isEmpty()){
            return "El nombre de plato es obligatorio.";
        }
        if(detalle.getCantidad() < 1){
            return "La cantidad debe ser mayor o igual a 1.";
        }
        if(detalle.getPrecioUnitario() < 0){
            return "El precio unitario no puede ser negativo.";
        }
        if(pedido.getCantidadDetalles() >= 10){
            return "El pedido ya alcanzó el límite máximo de 10 detalles.";
        }

        boolean agregado = pedido.agregarDetalle(detalle);
        if(!agregado){
            return "No se pudo agregar el detalle al pedido.";
        }
        return "";
    }

    // sobrecarga para agregar detalle mediante campos individuales
    public String agregarDetalleAPedido(String numeroPedido, String codigoPlato, String nombrePlato,
                                       int cantidad, double precioUnitario){
        return agregarDetalleAPedido(numeroPedido, new DetallePedido(codigoPlato, nombrePlato, cantidad, precioUnitario));
    }

    // edita un detalle especifico dentro de un pedido existente
    public String editarDetalleDePedido(String numeroPedido, int indice, DetallePedido detalle){
        if(!tienePermiso()){
            return "Acceso denegado: no cuenta con permisos para modificar pedidos.";
        }
        if(numeroPedido == null || numeroPedido.trim().isEmpty()){
            return "El número de pedido es obligatorio.";
        }
        Pedido pedido = oLista.buscar(numeroPedido.trim());
        if(pedido == null){
            return "No se encontró el pedido: " + numeroPedido.trim();
        }
        if(indice < 0 || indice >= pedido.getCantidadDetalles()){
            return "Índice de detalle fuera de rango.";
        }
        if(detalle == null){
            return "El detalle editado no puede ser nulo.";
        }
        if(detalle.getCodigoPlato() == null || detalle.getCodigoPlato().trim().isEmpty()){
            return "El código de plato es obligatorio.";
        }
        if(detalle.getNombrePlato() == null || detalle.getNombrePlato().trim().isEmpty()){
            return "El nombre de plato es obligatorio.";
        }
        if(detalle.getCantidad() < 1){
            return "La cantidad debe ser mayor o igual a 1.";
        }
        if(detalle.getPrecioUnitario() < 0){
            return "El precio unitario no puede ser negativo.";
        }

        boolean editado = pedido.editarDetalle(indice, detalle);
        if(!editado){
            return "No se pudo editar el detalle.";
        }
        return "";
    }

    // sobrecarga para editar detalle mediante campos individuales
    public String editarDetalleDePedido(String numeroPedido, int indice, String codigoPlato,
                                       String nombrePlato, int cantidad, double precioUnitario){
        return editarDetalleDePedido(numeroPedido, indice, new DetallePedido(codigoPlato, nombrePlato, cantidad, precioUnitario));
    }

    // quita un detalle por indice garantizando que el pedido conserve al menos 1 detalle
    public String quitarDetalleDePedido(String numeroPedido, int indice){
        if(!tienePermiso()){
            return "Acceso denegado: no cuenta con permisos para modificar pedidos.";
        }
        if(numeroPedido == null || numeroPedido.trim().isEmpty()){
            return "El número de pedido es obligatorio.";
        }
        Pedido pedido = oLista.buscar(numeroPedido.trim());
        if(pedido == null){
            return "No se encontró el pedido: " + numeroPedido.trim();
        }
        if(indice < 0 || indice >= pedido.getCantidadDetalles()){
            return "Índice de detalle fuera de rango.";
        }
        if(pedido.getCantidadDetalles() <= 1){
            return "No se puede quitar el detalle: el pedido debe conservar al menos 1 detalle (o elimine el pedido completo).";
        }

        boolean quitado = pedido.quitarDetalle(indice);
        if(!quitado){
            return "No se pudo quitar el detalle.";
        }
        return "";
    }

    // limpia todos los pedidos de la lista
    public String reiniciarPedidos(){
        if(!tienePermiso()){
            return "Acceso denegado: no cuenta con permisos para reiniciar pedidos.";
        }
        oLista.listaVacia();
        return "";
    }

    // carga pedidos de prueba representativos para probar el modulo sin depender de otros
    public String cargarDatosPrueba(){
        if(!tienePermiso()){
            return "Acceso denegado: no cuenta con permisos para cargar datos.";
        }
        oLista.listaVacia();

        // Pedido 1: consumo familiar en salon
        Pedido p1 = new Pedido("PED-001", "Familia Wong", 3);
        p1.agregarDetalle(new DetallePedido("P002", "Arroz Chaufa Especial", 2, 24.00));
        p1.agregarDetalle(new DetallePedido("P006", "Sopa Wantán Especial", 1, 16.00));
        p1.agregarDetalle(new DetallePedido("P012", "Chicha Morada 1L", 1, 10.00));
        oLista.insertar(p1);

        // Pedido 2: almuerzo personal
        Pedido p2 = new Pedido("PED-002", "Carlos Mendoza", 8);
        p2.agregarDetalle(new DetallePedido("P009", "Pollo ChiJauKay", 1, 25.00));
        p2.agregarDetalle(new DetallePedido("P008", "Wantán Frito (6 unid.)", 1, 12.00));
        oLista.insertar(p2);

        // Pedido 3: mesa compartida
        Pedido p3 = new Pedido("PED-003", "Lucía Salazar", 12);
        p3.agregarDetalle(new DetallePedido("P003", "Aeropuerto de Pollo", 1, 22.00));
        p3.agregarDetalle(new DetallePedido("P005", "Tallarín Taypa", 1, 28.00));
        oLista.insertar(p3);

        // Pedido 4: pedido rapido individual
        Pedido p4 = new Pedido("PED-004", "Mesa Ejecutiva", 1);
        p4.agregarDetalle(new DetallePedido("P010", "Pollo TiPaKay", 1, 25.00));
        oLista.insertar(p4);

        return "";
    }
}
