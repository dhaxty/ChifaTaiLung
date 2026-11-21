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
        boolean tiene = false;
        if(rolActual != null && (rolActual == Rol.ADMIN || rolActual == Rol.EMPLEADO)){
            tiene = true;
        }
        return tiene;
    }

    public int getCantidad(){
        return oLista.getCantidad();
    }

    public double totalGeneral(){
        return oLista.totalGeneral();
    }

    // valida permisos y campos completos antes de insertar el pedido en la lista
    public String registrar(Pedido pedido){
        String mensaje = "";
        if(!tienePermiso()){
            mensaje = "Acceso denegado: no cuenta con permisos para registrar pedidos.";
        } else if(pedido == null){
            mensaje = "El pedido no puede ser nulo.";
        } else if(pedido.getNumero() == null || pedido.getNumero().trim().isEmpty()){
            mensaje = "El número de pedido es obligatorio.";
        } else if(oLista.buscar(pedido.getNumero().trim()) != null){
            mensaje = "Ya existe un pedido registrado con el número " + pedido.getNumero().trim() + ".";
        } else if(pedido.getNombreCliente() == null || pedido.getNombreCliente().trim().isEmpty()){
            mensaje = "El nombre del cliente es obligatorio.";
        } else if(pedido.getNumeroMesa() < 1 || pedido.getNumeroMesa() > 16){
            mensaje = "El número de mesa debe estar entre 1 y 16.";
        } else if(pedido.getCantidadDetalles() < 1){
            mensaje = "El pedido debe contener al menos 1 detalle.";
        } else if(pedido.getCantidadDetalles() > 10){
            mensaje = "El pedido no puede superar el límite máximo de 10 detalles.";
        } else {
            // validacion de cada detalle del pedido
            int i = 0;
            while(i < pedido.getCantidadDetalles() && mensaje.isEmpty()){
                DetallePedido det = pedido.getDetalles()[i];
                if(det == null){
                    mensaje = "El detalle #" + (i + 1) + " no puede ser nulo.";
                } else if(det.getCodigoPlato() == null || det.getCodigoPlato().trim().isEmpty()){
                    mensaje = "El código de plato en el detalle #" + (i + 1) + " es obligatorio.";
                } else if(det.getNombrePlato() == null || det.getNombrePlato().trim().isEmpty()){
                    mensaje = "El nombre de plato en el detalle #" + (i + 1) + " es obligatorio.";
                } else if(det.getCantidad() < 1){
                    mensaje = "La cantidad en el detalle #" + (i + 1) + " debe ser al menos 1.";
                } else if(det.getPrecioUnitario() < 0){
                    mensaje = "El precio unitario en el detalle #" + (i + 1) + " no puede ser negativo.";
                }
                i++;
            }
            if(mensaje.isEmpty()){
                boolean insertado = oLista.insertar(pedido);
                if(!insertado){
                    mensaje = "No se pudo registrar el pedido en la lista.";
                }
            }
        }
        return mensaje;
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
        String mensaje = registrar(nuevoPedido);
        return mensaje;
    }

    // sobrecarga para registrar un pedido inicial con su primer detalle
    public String registrar(String numero, String nombreCliente, int numeroMesa,
                            String codigoPlato, String nombrePlato, int cantidad, double precioUnitario){
        Pedido nuevoPedido = new Pedido(numero, nombreCliente, numeroMesa);
        nuevoPedido.agregarDetalle(new DetallePedido(codigoPlato, nombrePlato, cantidad, precioUnitario));
        String mensaje = registrar(nuevoPedido);
        return mensaje;
    }

    // sobrecarga que valida regla de minimo 1 detalle al intentar crear un pedido sin items
    public String registrar(String numero, String nombreCliente, int numeroMesa){
        Pedido nuevoPedido = new Pedido(numero, nombreCliente, numeroMesa);
        String mensaje = registrar(nuevoPedido);
        return mensaje;
    }

    // modifica cliente y mesa de un pedido existente preservando su identificador original
    public String actualizar(String numero, String nombreCliente, int numeroMesa){
        String mensaje = "";
        if(!tienePermiso()){
            mensaje = "Acceso denegado: no cuenta con permisos para modificar pedidos.";
        } else if(numero == null || numero.trim().isEmpty()){
            mensaje = "El número de pedido es obligatorio.";
        } else if(nombreCliente == null || nombreCliente.trim().isEmpty()){
            mensaje = "El nombre del cliente es obligatorio.";
        } else if(numeroMesa < 1 || numeroMesa > 16){
            mensaje = "El número de mesa debe estar entre 1 y 16.";
        } else {
            Pedido existente = oLista.buscar(numero.trim());
            if(existente == null){
                mensaje = "No se encontró ningún pedido con el número: " + numero.trim();
            } else {
                existente.setNombreCliente(nombreCliente.trim());
                existente.setNumeroMesa(numeroMesa);
            }
        }
        return mensaje;
    }

    // sobrecarga para actualizar datos y detalles de un pedido completo preservando el numero
    public String actualizar(String id, Pedido datosNuevos){
        String mensaje = "";
        if(!tienePermiso()){
            mensaje = "Acceso denegado: no cuenta con permisos para modificar pedidos.";
        } else if(id == null || id.trim().isEmpty()){
            mensaje = "El identificador del pedido es obligatorio.";
        } else if(datosNuevos == null){
            mensaje = "Los datos nuevos del pedido no pueden ser nulos.";
        } else if(datosNuevos.getNombreCliente() == null || datosNuevos.getNombreCliente().trim().isEmpty()){
            mensaje = "El nombre del cliente es obligatorio.";
        } else if(datosNuevos.getNumeroMesa() < 1 || datosNuevos.getNumeroMesa() > 16){
            mensaje = "El número de mesa debe estar entre 1 y 16.";
        } else if(datosNuevos.getCantidadDetalles() < 1){
            mensaje = "El pedido debe conservar al menos 1 detalle.";
        } else if(datosNuevos.getCantidadDetalles() > 10){
            mensaje = "El pedido no puede superar el límite máximo de 10 detalles.";
        } else {
            boolean actualizado = oLista.actualizar(id.trim(), datosNuevos);
            if(!actualizado){
                mensaje = "No se encontró ningún pedido con el número: " + id.trim();
            }
        }
        return mensaje;
    }

    // elimina un pedido de la lista por su identificador
    public String eliminar(String numero){
        String mensaje = "";
        if(!tienePermiso()){
            mensaje = "Acceso denegado: no cuenta con permisos para eliminar pedidos.";
        } else if(numero == null || numero.trim().isEmpty()){
            mensaje = "El número de pedido es obligatorio.";
        } else {
            boolean eliminado = oLista.eliminar(numero.trim());
            if(!eliminado){
                mensaje = "No se encontró ningún pedido con el número: " + numero.trim();
            }
        }
        return mensaje;
    }

    // busca un pedido por su identificador unico
    public Pedido buscar(String numero){
        Pedido encontrado = null;
        if(numero != null && !numero.trim().isEmpty()){
            encontrado = oLista.buscar(numero.trim());
        }
        return encontrado;
    }

    // devuelve todos los pedidos para alimentar la tabla principal
    public Pedido[] listar(){
        return oLista.listar();
    }

    // agrega un detalle a un pedido existente validando que no exceda 10 items
    public String agregarDetalleAPedido(String numeroPedido, DetallePedido detalle){
        String mensaje = "";
        if(!tienePermiso()){
            mensaje = "Acceso denegado: no cuenta con permisos para modificar pedidos.";
        } else if(numeroPedido == null || numeroPedido.trim().isEmpty()){
            mensaje = "El número de pedido es obligatorio.";
        } else {
            Pedido pedido = oLista.buscar(numeroPedido.trim());
            if(pedido == null){
                mensaje = "No se encontró el pedido: " + numeroPedido.trim();
            } else if(detalle == null){
                mensaje = "El detalle a agregar no puede ser nulo.";
            } else if(detalle.getCodigoPlato() == null || detalle.getCodigoPlato().trim().isEmpty()){
                mensaje = "El código de plato es obligatorio.";
            } else if(detalle.getNombrePlato() == null || detalle.getNombrePlato().trim().isEmpty()){
                mensaje = "El nombre de plato es obligatorio.";
            } else if(detalle.getCantidad() < 1){
                mensaje = "La cantidad debe ser mayor o igual a 1.";
            } else if(detalle.getPrecioUnitario() < 0){
                mensaje = "El precio unitario no puede ser negativo.";
            } else if(pedido.getCantidadDetalles() >= 10){
                mensaje = "El pedido ya alcanzó el límite máximo de 10 detalles.";
            } else {
                boolean agregado = pedido.agregarDetalle(detalle);
                if(!agregado){
                    mensaje = "No se pudo agregar el detalle al pedido.";
                }
            }
        }
        return mensaje;
    }

    // sobrecarga para agregar detalle mediante campos individuales
    public String agregarDetalleAPedido(String numeroPedido, String codigoPlato, String nombrePlato,
                                       int cantidad, double precioUnitario){
        String mensaje = agregarDetalleAPedido(numeroPedido, new DetallePedido(codigoPlato, nombrePlato, cantidad, precioUnitario));
        return mensaje;
    }

    // edita un detalle especifico dentro de un pedido existente
    public String editarDetalleDePedido(String numeroPedido, int indice, DetallePedido detalle){
        String mensaje = "";
        if(!tienePermiso()){
            mensaje = "Acceso denegado: no cuenta con permisos para modificar pedidos.";
        } else if(numeroPedido == null || numeroPedido.trim().isEmpty()){
            mensaje = "El número de pedido es obligatorio.";
        } else {
            Pedido pedido = oLista.buscar(numeroPedido.trim());
            if(pedido == null){
                mensaje = "No se encontró el pedido: " + numeroPedido.trim();
            } else if(indice < 0 || indice >= pedido.getCantidadDetalles()){
                mensaje = "Índice de detalle fuera de rango.";
            } else if(detalle == null){
                mensaje = "El detalle editado no puede ser nulo.";
            } else if(detalle.getCodigoPlato() == null || detalle.getCodigoPlato().trim().isEmpty()){
                mensaje = "El código de plato es obligatorio.";
            } else if(detalle.getNombrePlato() == null || detalle.getNombrePlato().trim().isEmpty()){
                mensaje = "El nombre de plato es obligatorio.";
            } else if(detalle.getCantidad() < 1){
                mensaje = "La cantidad debe ser mayor o igual a 1.";
            } else if(detalle.getPrecioUnitario() < 0){
                mensaje = "El precio unitario no puede ser negativo.";
            } else {
                boolean editado = pedido.editarDetalle(indice, detalle);
                if(!editado){
                    mensaje = "No se pudo editar el detalle.";
                }
            }
        }
        return mensaje;
    }

    // sobrecarga para editar detalle mediante campos individuales
    public String editarDetalleDePedido(String numeroPedido, int indice, String codigoPlato,
                                       String nombrePlato, int cantidad, double precioUnitario){
        String mensaje = editarDetalleDePedido(numeroPedido, indice, new DetallePedido(codigoPlato, nombrePlato, cantidad, precioUnitario));
        return mensaje;
    }

    // quita un detalle por indice garantizando que el pedido conserve al menos 1 detalle
    public String quitarDetalleDePedido(String numeroPedido, int indice){
        String mensaje = "";
        if(!tienePermiso()){
            mensaje = "Acceso denegado: no cuenta con permisos para modificar pedidos.";
        } else if(numeroPedido == null || numeroPedido.trim().isEmpty()){
            mensaje = "El número de pedido es obligatorio.";
        } else {
            Pedido pedido = oLista.buscar(numeroPedido.trim());
            if(pedido == null){
                mensaje = "No se encontró el pedido: " + numeroPedido.trim();
            } else if(indice < 0 || indice >= pedido.getCantidadDetalles()){
                mensaje = "Índice de detalle fuera de rango.";
            } else if(pedido.getCantidadDetalles() <= 1){
                mensaje = "No se puede quitar el detalle: el pedido debe conservar al menos 1 detalle (o elimine el pedido completo).";
            } else {
                boolean quitado = pedido.quitarDetalle(indice);
                if(!quitado){
                    mensaje = "No se pudo quitar el detalle.";
                }
            }
        }
        return mensaje;
    }

    // limpia todos los pedidos de la lista
    public String reiniciarPedidos(){
        String mensaje = "";
        if(!tienePermiso()){
            mensaje = "Acceso denegado: no cuenta con permisos para reiniciar pedidos.";
        } else {
            oLista.listaVacia();
        }
        return mensaje;
    }

    // carga pedidos de prueba representativos para probar el modulo sin depender de otros
    public String cargarDatosPrueba(){
        String mensaje = "";
        if(!tienePermiso()){
            mensaje = "Acceso denegado: no cuenta con permisos para cargar datos.";
        } else {
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
        }
        return mensaje;
    }
}
