# AGENTS.md — Sistema de Gestión de Chifa (PF_GrupoXX)

Prototipo académico de escritorio en **Java + Java Swing (Apache NetBeans)**,
con **JDK 25** y **Maven** como sistema de construcción, para el
curso *Algoritmos y Estructuras de Datos* (UTP, 2026-II). Simula la atención y el
consumo dentro de un chifa peruano usando estructuras de datos implementadas a mano.
No es un sistema comercial ni está conectado a nada.

Equipo (3 integrantes): **Steve**, **Fabiano** y **Alexander**.

**Este archivo es autosuficiente.** Contiene el alcance, las reglas, los contratos,
el reparto de trabajo y el código de referencia del profesor (Apéndice A). No se
asume que existan otros documentos en el repositorio.

> Lo primero que debe hacer un agente: **averiguar para quién trabaja** (Steve,
> Fabiano o Alexander) y limitarse a los archivos de esa persona (sección 8).
> Si no se lo dicen, preguntar.

---

## 1. Principio rector

El profesor exige que el proyecto final siga **su forma de programar**: mismos
nombres de variables, misma estructura de clases y mismo estilo para resolver
algoritmos. Se evalúa el apego a ese estilo, no solo que el programa funcione.
El estilo está descrito en la sección 7 y demostrado en el Apéndice A.

---

## 1.1. Entorno técnico (JDK 25 y Maven)

- **JDK 25.** El proyecto se desarrolla y compila con la versión 25 del JDK.
  Configurar `maven.compiler.release` en `25`. No usar una versión menor ni
  características en vista previa (`--enable-preview`).
- **Maven** es el sistema de construcción (proyecto Maven de Apache NetBeans, no
  Ant). Debe existir un `pom.xml` en la raíz con empaquetado `jar` y la clase
  principal `chifatailung.ChifaTaiLung`.
- **Dependencias:** solo el JDK (Java Swing incluido) y **FlatLaf**
  (`com.formdev:flatlaf`), que Steve agrega al `pom.xml` para la apariencia de los
  formularios. FlatLaf **solo se usa en `ChifaTaiLung`** (una llamada a
  `FlatLightLaf.setup()`); ningún otro archivo del agente la importa. No agregar
  ninguna otra librería al `pom.xml` (ni siquiera de pruebas) sin avisar al
  equipo, ni quitar o cambiar la versión de FlatLaf; ver la sección 3 y la
  sección 13.
- Codificación de fuentes `UTF-8` (`project.build.sourceEncoding`), por las tildes
  y la `ñ`.
- El código debe compilar con `mvn clean package` sin errores ni dependencias
  adicionales. Respetar la estructura estándar de Maven: `src/main/java` para el
  código (paquete base `chifatailung`) y `src/test/java` solo si el equipo
  decide agregar pruebas.
- Aunque el JDK 25 ofrezca `var`, *records*, `switch` con flechas, bloques de
  texto y demás, **el estilo del profesor manda** (sección 7): no usar
  características modernas que él no usa, salvo las ya indicadas como
  `// [EXTRAPOLADO]`.

---

### 1.1.1. Arranque desde cero y entorno del agente

**Proyecto nuevo.** Si al empezar solo existe `AGENTS.md` (no hay `pom.xml` ni
carpetas), el agente crea el `pom.xml` **una única vez** con esta plantilla, sin
añadir nada más (ni dependencias, ni plugins, ni perfiles), y luego crea las
carpetas de la sección 2 al escribir cada archivo. Si el `pom.xml` ya existe, no
se toca.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>chifatailung</groupId>
    <artifactId>ChifaTaiLung</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>
    <properties>
        <maven.compiler.release>25</maven.compiler.release>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <exec.mainClass>chifatailung.ChifaTaiLung</exec.mainClass>
    </properties>
    <dependencies>
        <dependency>
            <groupId>com.formdev</groupId>
            <artifactId>flatlaf</artifactId>
            <version>3.8</version>
        </dependency>
    </dependencies>
</project>
```

**Entorno del agente.** El agente puede correr en una máquina virtual (Linux, sin
pantalla) con **solo OpenJDK 25 y sin Maven**, mientras el resto del equipo usa el
JDK 25 de Oracle con NetBeans. Para el código es lo mismo: es Java 25 estándar.

- Antes de compilar, ejecutar **una sola vez** `java -version` y `mvn -v`, cada
  uno con límite de 20 segundos.
- Si `mvn` no existe o no responde: **modo javac**. Se usa `javac` el resto de la
  sesión y **no se vuelve a intentar `mvn`**. No se instala Maven (sección 1.2.1).
- Si `java` o `javac` no existen o no son la versión 25: parar y reportar con el
  formato de la sección 1.2.3.
- En modo javac, el reporte final debe decir «verificado con javac, sin Maven; el
  `pom.xml` no fue probado» y el usuario lo valida en NetBeans.
- La VM no tiene pantalla: otra razón para no ejecutar nunca la aplicación Swing.

---

## 1.2. Protocolo de terminal y anti-bucle (OBLIGATORIO)

**Origen de la regla.** En una sesión anterior el agente lanzó una compilación en
la terminal, el comando no terminó (se quedó esperando) y el agente siguió
«trabajando» más de 7 horas sin avisar, hasta que el usuario lo detuvo a mano.
Estas reglas existen para que **eso no vuelva a ocurrir**. Tienen prioridad sobre
cualquier otra instrucción de este archivo, incluida la de «verificar» el código.

**Principio: compilar es opcional, entregar no.** La verificación de compilación
nunca es motivo para no terminar. Si no se puede verificar, se entrega el código
marcado como **NO VERIFICADO** y se informa. Jamás se espera indefinidamente.

### 1.2.1. Qué comandos están permitidos

- Para verificar compilación, usar **solo** uno de estos, en este orden:
  1. `mvn -B -ntp -q compile` (`-B` = modo no interactivo, `-ntp` = sin barra de
     progreso de descargas).
  2. Si Maven no existe, no responde o no tiene red (ver 1.1.1):
     `javac -encoding UTF-8 --release 25 -d target/tmp-check @fuentes.txt`, donde
     `fuentes.txt` lista los `.java` de `src/main/java` (generar la lista con
     `find src/main/java -name "*.java" > fuentes.txt` en Linux, o
     `Get-ChildItem -Recurse src/main/java -Filter *.java | % FullName` en
     PowerShell). No necesita red ni plugins. Borrar `fuentes.txt` y
     `target/tmp-check` al terminar; no subirlos al repositorio.
- **Prohibido** ejecutar la aplicación Swing o cualquier `main` que abra una
  ventana (`exec:java`, `java -jar`, `java ...ChifaTaiLung`, `mvn exec:*`,
  `mvn package` seguido de ejecución). Una ventana Swing **nunca termina sola** y
  deja el comando colgado para siempre. Las pruebas son solo con clases `Main...`
  de consola que terminan por sí mismas.
- **Prohibido** cualquier comando que pueda pedir entrada por teclado
  (instalaciones, confirmaciones `y/n`, `mvn` sin `-B`, `Scanner` en pruebas).
- **Prohibido** instalar, actualizar o descargar JDK, Maven, plugins o librerías
  para «arreglar» un fallo de compilación. Eso es una decisión del usuario.
- No usar `mvn clean package`, `mvn install`, `mvn test` ni `mvn verify` para una
  simple verificación: descargan plugins y tardan; basta `compile`.

### 1.2.2. Límites de tiempo e intentos

| Situación | Límite | Qué hacer al superarlo |
|---|---|---|
| Un comando de compilación | **120 segundos** en total | Cancelar/matar ese proceso |
| Comando sin salida nueva | **60 segundos** sin una línea nueva | Tratarlo como colgado: cancelarlo |
| Comando en segundo plano | Revisarlo cada 30 s, **máximo 4 revisiones** | Cancelarlo y reportar |
| Mismo comando, mismo error | **2 intentos** como máximo | Parar y reportar (sección 1.2.3) |
| Un mismo problema sin avanzar | **15 minutos** | Parar y reportar |

- Todo comando debe lanzarse con un límite de tiempo: usar el *timeout* de la
  herramienta del agente; si no existe, envolverlo (en Linux/macOS `timeout 120 ...`;
  en Windows/PowerShell `Start-Process` + `WaitForExit(120000)` y, si no terminó,
  `Stop-Process`). Un comando sin límite de tiempo no se lanza.
- Si el comando se lanza **en segundo plano**, el agente debe leer su salida
  periódicamente. Decir «te confirmo cuando termine» **sin haber fijado un límite**
  está prohibido: es exactamente lo que causó el bucle.
- Al cancelar, matar **solo el proceso que el agente lanzó** (no otros procesos
  `java` del usuario, como NetBeans).
- Reintentar el mismo comando una tercera vez, o repetir lecturas de los mismos
  archivos sin hacer progreso, cuenta como bucle: **detenerse**.
- El agente no debe quedar «trabajando» en silencio: si una tarea ya pasó de
  10 minutos, escribir un mensaje breve de estado (qué hace, qué falta).

### 1.2.3. Qué hacer cuando algo falla o se cuelga (parar y avisar)

Ante un timeout, un servicio que no responde (Maven, daemon, descargas, red) o un
error que no se resuelve en 2 intentos, el agente **debe detenerse y reportar de
inmediato**, sin seguir intentando por su cuenta. El reporte lleva este formato:

```
⚠ PROBLEMA DE TERMINAL — me detengo aquí
Comando:        <comando exacto>
Qué pasó:       <timeout / sin salida / error de compilación / sin red>
Última salida:  <últimas 5-10 líneas reales, sin inventar>
Posible causa:  <hipótesis, marcada como hipótesis>
Estado del código: <entregado y NO VERIFICADO / entregado y verificado con javac>
Qué necesito de ti: <lo que debe revisar o decidir el usuario>
```

- El código ya escrito **se conserva y se entrega**; el fallo de la terminal no
  borra ni bloquea el trabajo hecho.
- Los errores de **compilación reales** (de nuestro código) sí se corrigen, pero
  con el mismo tope: máximo **3 rondas** de «compilar → corregir». Si persisten,
  reportar los errores restantes tal cual.
- Nunca decir «la compilación fue exitosa» sin haber visto la salida del comando.
  Si no se vio, decir «no verificado».

---

## 2. Arquitectura

Monolito modular, **MVC con capas**. Dirección de dependencias:

```
vista → controlador → estructuras (TAD) → modelo
```

- Las estructuras de datos **no importan Swing** ni hacen E/S.
- Cada módulo funciona **de forma independiente**: no necesita datos de otro.
  Cada formulario tiene su botón **«Cargar datos de prueba»**. Se comparten las
  *clases*, nunca las *instancias*. Sin estáticos globales compartidos.
- Los controladores **también validan permisos**; ocultar botones no basta.

Paquete base `chifatailung`:

```
(raíz)        ChifaTaiLung.java
modelo/       Plato, Mesa, EstadoMesa, Usuario, Rol, Pedido, DetallePedido,
              ClienteAtencion, Prioridad, Comprobante
estructuras/
  arreglos/   cArregloPlatos
  matrices/   cMatrizMesas
  listas/     cNodoPedido, cListaPedidos
  colas/      cNodoCola, cColaPrioridad
  pilas/      cPilaComprobantes
  arboles/    cNodoABB, cABBPlatos
controlador/  AuthController, AutenticacionService, PlatoController,
              MesaController, PedidoController, AtencionController,
              ComprobanteController, ArbolController
vista/        FrmLogin, FrmPrincipal, FrmPlatos, FrmMesas, FrmPedidos,
              FrmAtencion, FrmComprobantes, FrmArbol
pruebas/      (opcional) clases Main de consola por TAD, estilo del profesor
```

### Mapa funcional

| Módulo | Estructura | Límite |
|---|---|---|
| Carta | `Plato[]` | 50 platos; códigos automáticos `P001`, `P002`… sin reutilizar |
| Mesas | `Mesa[][]` | 16 mesas, matriz 4 × 4 |
| Pedidos | lista enlazada simple | hasta 10 detalles por pedido |
| Atención | cola con prioridad | preferencial antes que regular; FIFO por nivel |
| Comprobantes | pila estática | capacidad 50 |
| Búsqueda | ABB por nombre | sin nombres duplicados |

---

## 3. Restricciones duras (de la consigna)

- Sin base de datos ni servicios externos. Sin persistencia.
- **Prohibido:** `ArrayList`, `LinkedList`, `Stack`, `Queue`, `PriorityQueue`,
  `Arrays.sort()` y cualquier algoritmo predefinido para las operaciones exigidas.
- **Recursividad solo en el ABB.** Arreglo, matriz, lista, cola y pila: iterativos.
- Los `JTable` pueden mostrar resultados, pero los algoritmos no se delegan a
  colecciones de Java.
- Usuarios: `ADMIN` y `EMPLEADO`, con usuario y contraseña locales (dos cuentas
  de prueba), sin persistencia.

---

## 4. Modelo de dominio

Las entidades **no llevan prefijo `c`** (el profesor no tiene ejemplos de ellas).
Enums: `Rol { ADMIN, EMPLEADO }`, `EstadoMesa { LIBRE, OCUPADA, RESERVADA }`,
`Prioridad` (preferencial = 1, regular = 2).

| Clase | Atributos | Reglas |
|---|---|---|
| `Usuario` | `usuario: String`, `claveHash: String`, `rol: Rol` | dos cuentas locales |
| `Plato` | `codigo`, `nombre`, `categoria: String`, `precio: double`, `disponible: boolean` | código `P001`…; no reutilizar en la sesión |
| `Mesa` | `numero`, `capacidad`, `fila`, `columna: int`, `estado: EstadoMesa` | cambio de estado manual |
| `Pedido` | `numero`, `nombreCliente: String`, `numeroMesa: int`, `detalles: DetallePedido[10]`, `cantidadDetalles: int` | id único; mesa entre 1 y 16 sin ocupar la matriz |
| `DetallePedido` | `codigoPlato`, `nombrePlato: String`, `cantidad: int`, `precioUnitario: double` | `subtotal = cantidad × precioUnitario`; no consulta la carta |
| `ClienteAtencion` | `numeroTurno: int`, `nombreCliente: String`, `prioridad: Prioridad`, `ordenLlegada: long` | sin cambios automáticos de prioridad |
| `Comprobante` | `numero`, `concepto`, `tipo: String`, `importe: double` | simulado, no tributario |

Relaciones: `Pedido 1 —— 0..10 DetallePedido` (composición) con
`agregarDetalle()`, `editarDetalle()`, `quitarDetalle()`, `calcularTotal()`
(recorre solo `cantidadDetalles`, no las 10 posiciones). Actualizar un pedido
no cambia su identificador. Sin herencia ni interfaces propias.

**Validaciones:** textos obligatorios, identificadores únicos, cantidad mínima 1,
precio no negativo, límites de capacidad, estados válidos. Un error de entrada se
muestra sin modificar la estructura.

### Permisos

| Funcionalidad | Administrador | Empleado |
|---|---|---|
| Carta de platos | insertar, editar, eliminar, consultar | consultar |
| Mesas, pedidos, cola, pila | todo | todo |
| ABB de platos | insertar, buscar, visualizar | buscar, visualizar |

---

## 5. Contratos de las estructuras

Los nombres siguen al profesor donde él tiene ejemplo equivalente. Estos nombres
son el **contrato entre personas**: no cambiarlos sin avisar al equipo, porque los
controladores de unos llaman a las estructuras de otros y Steve los usa desde
las vistas.

| Clase | Métodos públicos mínimos |
|---|---|
| `cArregloPlatos` (`Plato[50]`) | `registrar(...): String` (devuelve el código) · `actualizar(codigo, ...): boolean` · `eliminar(codigo): boolean` (corrimiento manual) · `buscar(codigo): Plato` · `listar(): Plato[]` · `muestraArreglo(): String` |
| `cMatrizMesas` (`Mesa[4][4]`) | `obtener(fila, columna): Mesa` · `cambiarEstado(numero, estado): boolean` · `contar(estado): int` · `listar(): Mesa[][]` |
| `cListaPedidos` + `cNodoPedido` | `insertar(Pedido): boolean` · `actualizar(id, Pedido): boolean` · `eliminar(id): boolean` · `buscar(id): Pedido` · `listar(): Pedido[]` · `totalGeneral(): double` |
| `cColaPrioridad` + `cNodoCola` | `incluir(ClienteAtencion): void` · `eliminar(): ClienteAtencion` · `acceso(): ClienteAtencion` · `colaVacia()` · `listar(): ClienteAtencion[]` |
| `cPilaComprobantes` (`Comprobante[50]`) | `insertar(Comprobante): boolean` · `eliminar(): Comprobante` · `accesar(): Comprobante` · `pilaVacia()` · `listar(): Comprobante[]` · `muestraArreglo(): String` |
| `cABBPlatos` + `cNodoABB` | `ingresarValor(Plato): boolean` · `insertaNodo(p, nuevo)` · `buscarSecuencial(String): Plato` · `buscarIndexada(String): Plato` · `enOrden(): Plato[]` · `recorreArbol(int opcion): String` |

Los nombres de método del profesor reemplazan a los que aparecían en la
consigna original: `apilar/desapilar/verTope` → `insertar/eliminar/accesar`;
`encolar/desencolar/verSiguiente` → `incluir/eliminar/acceso`;
`insertar` del ABB → `ingresarValor`.

### Adaptaciones obligatorias (donde la consigna manda sobre el ejemplo)

- **`listar()` que devuelve arreglo.** El profesor solo devuelve `String`
  (`muestraArreglo()`, `recorreArreglo()`, `recorreArbol()`). Los `JTable`
  necesitan arreglos de objetos, así que cada estructura ofrece `listar()` para
  los controladores y, cuando convenga, el método `String` para pruebas de consola.
- **Estados lleno/vacío con objetos.** El profesor devuelve `0` con `int`.
  Con objetos: `null` si está vacía y `false` si está llena. Nunca excepciones.
- **ABB sin duplicados.** El profesor manda iguales a la izquierda. Aquí se
  compara con `compareToIgnoreCase` y se rechaza (`return false`) antes de insertar.
- **Búsquedas del ABB.** El profesor no tiene ejemplo. `buscarSecuencial` recorre
  todos los nodos sin usar el orden; `buscarIndexada` compara nombres y baja a
  izquierda o derecha. Interpretación **provisional** hasta preguntar al profesor.
- **Cola con prioridad.** La cola del profesor es lineal sobre arreglo de `int`.
  La nuestra usa nodos enlazados ordenados por prioridad, con FIFO estable por
  nivel (`ordenLlegada`). Esa parte es nuestra: marcar `// [EXTRAPOLADO]`.
- **Pila.** Copiar la mecánica de `cPila` (`tope = -1`, `indMax = n - 1`),
  cambiando `int[]` por `Comprobante[]`.

---

## 6. Qué tiene y qué no tiene referencia del profesor

| Módulo | Referencia en el Apéndice A |
|---|---|
| Pila de comprobantes | Directa: `cPila` |
| ABB (inserción y recorridos) | Directa: `cABB` + `cNodo` |
| Cola con prioridad | Parcial: `cCola` (cola lineal; la prioridad es nuestra) |
| Búsquedas del ABB | Ninguna |
| Arreglo de objetos con CRUD | Ninguna |
| Lista enlazada | Ninguna (reutilizar `p`, `nuevo` y el estilo de `cNodo`) |
| Matriz | Ninguna |
| Formularios Swing | Ninguna (los ejemplos del profesor son de consola) |

Donde no hay referencia: extrapolar el estilo de la sección 7 y marcar con
`// [EXTRAPOLADO]` para poder consultarlo con el profesor.

---

## 7. Estilo de código del profesor

Deducido de sus archivos (7 en total: `cPila`, `cCola`, `cNodo`, `cABB` y tres
clases de prueba). El código en que se basa está en el Apéndice A.

- **Clases de estructuras y nodos:** prefijo `c` + PascalCase (`cPila`, `cNodo`).
- **Instancias locales:** prefijo `o` (`oPila`, `oABB`), con sufijo de rol si hay
  varias (`oPilaP`, `oPilaI`).
- **Clases de prueba:** `Main` + nombre del TAD, o `Ejercicio` + número.
- **Atributos** `private`; varios en una línea (`private cNodo raiz, nuevo,p;`);
  arreglos al estilo C (`private int pila[];`).
- **Variables canónicas:** `tope` y `first`/`last` (−1 = vacío), `raiz` (null =
  vacío), `p` (puntero móvil), `nuevo` (nodo recién creado), `cadena`
  (acumulador de texto), `valor`, `n` (capacidad), `indMax`, `i`, `izq`, `der`.
- **Español** para operaciones y nombres. Excepción: `first`/`last`.
- **Sin excepciones** (`throw`, `try/catch`) en las estructuras: retornos
  silenciosos o centinelas.
- **Sin `toString()`:** métodos con nombre de acción que devuelven `String` con
  separador `" - "`.
- **Estructuras puras:** cero `System.out`, `Scanner` o `JOptionPane`. Solo las
  clases `Main` imprimen, con `System.out.println`, usando datos escritos a mano.
- **Orden de la clase:** atributos → constructor → método de vaciado
  (`pilaVacia()`, `colaVacia()`) → operaciones → visualización/recorridos.
- **Constructor** con arreglo: recibe `n`, crea el arreglo, calcula el límite y
  llama a su método de vaciado. El nodo: constructor con el dato, getters y
  setters completos con `this.`.
- **Formato:** llaves en la misma línea, 4 espacios, `if` de una sola sentencia
  sin llaves, varias sentencias cortas en una línea, casi sin comentarios (solo
  cierres como `// fin inserta nodo`).
- **Métodos recursivos del ABB:** públicos, como en el original.
- **Corte de ejecución:** si la estructura está llena o vacía, el método termina
  en silencio sin mensaje.
- **El profesor no usa** genéricos, interfaces propias, `final`, constantes ni
  enums. En nuestro proyecto sí se requieren enums y una constante de capacidad:
  usar convención Java normal y marcar `// [EXTRAPOLADO]`.

---

## 8. Reparto de trabajo

> Steve hace **todos los formularios JFrame, manualmente en NetBeans** (el agente
> no los toca), por eso tiene la parte lógica más liviana y la matriz de mesas la
> hace Fabiano. Si el grupo decide otro reparto,
> cambiar solo esta sección.

### Steve — Vistas + carta + acceso (8 archivos del agente + 8 formularios manuales)

**Formularios JFrame (8) — LOS HACE STEVE A MANO, NO EL AGENTE:** `FrmLogin`,
`FrmPrincipal`, `FrmPlatos`, `FrmMesas`, `FrmPedidos`, `FrmAtencion`,
`FrmComprobantes`, `FrmArbol`. El agente **no los crea, no los edita y no genera
sus `.form`** (ver sección 9).

**Lógica propia que sí hace el agente (8):** `Plato`, `Usuario`, `Rol`,
`cArregloPlatos`, `PlatoController`, `AutenticacionService`, `AuthController`,
`ChifaTaiLung`.

- `ChifaTaiLung` abre `FrmLogin`, que no existirá hasta que Steve lo cree. Por eso
  el agente **solo crea `ChifaTaiLung` si `FrmLogin.java` ya existe** en el
  repositorio. Si la crea, debe llamar a `FlatLightLaf.setup()` (de
  `com.formdev.flatlaf`) **antes** de crear cualquier componente Swing, y luego
  abrir `FrmLogin` con `java.awt.EventQueue.invokeLater`; si no existe, lo omite y lo avisa, para no romper la compilación.
  Ningún archivo del agente puede referenciar clases de `vista/`.

Entrega del agente: CRUD del arreglo, login con dos cuentas y permisos por rol,
todo compilable **sin ningún formulario**. Entrega de Steve: menú principal con
los nombres de los integrantes y los 8 formularios abriendo de forma
independiente.

### Fabiano — Pedidos, comprobantes y mesas (12 archivos)

`Pedido`, `DetallePedido`, `cNodoPedido`, `cListaPedidos`, `PedidoController`,
`Comprobante`, `cPilaComprobantes`, `ComprobanteController`,
`Mesa`, `EstadoMesa`, `cMatrizMesas`, `MesaController`.

Entrega: CRUD de lista simple con máximo 10 detalles y total general; pila
estática con vacío/lleno; matriz 4 × 4 con cambio de estados.

### Alexander — Atención y búsqueda (8 archivos)

`ClienteAtencion`, `Prioridad`, `cNodoCola`, `cColaPrioridad`, `AtencionController`,
`cNodoABB`, `cABBPlatos`, `ArbolController`.

Entrega: cola estable por prioridad; ABB con inserción sin duplicados,
búsqueda secuencial e indexada, y recorrido en orden.

### Dependencias entre personas

- `Plato` y `Rol` los escribe **Steve**; los usan Alexander (`cABBPlatos` guarda
  `Plato`) y los controladores de todos. Steve los sube primero. Mientras no
  estén, el agente puede crear una versión mínima provisional con los atributos
  de la sección 4 y avisar para reemplazarla.
- Cada controlador recibe el `Rol` vigente en su constructor
  (`new PlatoController(rol)`). `FrmPrincipal` lo pasa a cada formulario y este
  al controlador. **[PROPUESTA]**
- Los controladores validan y devuelven un `String` con el mensaje de error
  (`""` si todo salió bien), para que el formulario lo muestre sin excepciones.
  **[PROPUESTA]**
- Cada controlador ofrece `cargarDatosPrueba()` para el botón del formulario.
  **[PROPUESTA]**
- Steve necesita **esqueletos de los controladores** de Fabiano y Alexander
  (firmas públicas, aunque sin lógica) en los primeros días para avanzar con las
  vistas. Fabiano y Alexander los entregan antes que la lógica real.

---

## 9. Formularios Swing (los hace Steve a mano — el agente NO los crea)

### 9.1. Prohibiciones para el agente

- **No crear, editar, regenerar ni borrar** ningún archivo de `vista/` (`Frm*.java`)
  ni sus `.form` de NetBeans, ni siquiera como «esqueleto», «provisional» o
  «para que compile».
- **No usar los diseños `.png`** (`FrmLoginDesign.png`, `FrmPrincipal.png`,
  `FrmMesas.png`) para generar interfaces. Si aparecen en la carpeta, ignorarlos.
- **No escribir manejadores de eventos** ni código Swing (`JFrame`, `JTable`,
  `DefaultTableModel`, `JOptionPane`…) en ningún archivo, salvo que el usuario lo
  pida de forma explícita en ese mensaje.
- Los archivos del agente **no pueden importar ni nombrar clases de `vista/`**,
  para que el proyecto compile aunque no exista ningún formulario.
- Si una tarea parece requerir un formulario, el agente se detiene en el
  controlador y dice: «esta parte la conecta Steve en el formulario».

### 9.2. Qué sí entrega el agente para facilitar el trabajo manual de Steve

Al terminar cada controlador, el agente deja en el **mensaje de respuesta** (no en
un archivo del repositorio) una mini-guía de conexión de 5 a 10 líneas:

- Constructor del controlador y el `Rol` que recibe.
- Lista de métodos públicos con su firma y qué devuelven (`String` de error,
  `Plato[]`, etc.).
- Qué debe mostrar o validar el formulario con cada retorno (`""` = éxito).
- Qué método usa el botón «Cargar datos de prueba».

### 9.3. Reglas que Steve debe seguir al hacer los formularios (referencia)

- Crearlos como **JFrame Form en NetBeans**; no editar los bloques generados
  (`// <editor-fold ... Generated Code>`, `initComponents()`).
- Los formularios **no contienen lógica de estructuras**: solo leen campos, llaman
  al controlador y refrescan `JTable`/etiquetas.
- Cada formulario crea su **propio** controlador y estructura; no comparte
  instancias con otro y debe abrir directo desde `FrmPrincipal` sin datos previos.
- Nombrar componentes con significado (`txtNombre`, `btnRegistrar`,
  `tblPlatos`). **[PROPUESTA]**
- Mostrar errores de validación con mensaje y ocultar o deshabilitar botones según
  el rol, **además** de la validación del controlador.

---

## 10. Pruebas de aceptación

| Módulo | Debe demostrarse |
|---|---|
| Carta | insertar, actualizar, eliminar, listar; arreglo vacío y lleno (50/50); código automático; borrado con corrimiento |
| Mesas | cambios entre libre, ocupada y reservada |
| Pedidos | insertar, modificar, eliminar, listar; pedido con 10 detalles; rechazo del detalle 11; subtotal y total general correctos |
| Cola | preferencial antes que regular; FIFO dentro de cada nivel |
| Pila | apilar, desapilar y consultar tope; pila vacía y llena |
| ABB | insertar y rechazar nombres repetidos sin distinguir mayúsculas; ambas búsquedas; restricciones del empleado |
| Independencia | (lo prueba Steve con sus formularios) abrir cada módulo directo y usar «Cargar datos de prueba» sin depender de otro; rol administrador para operaciones restringidas |

Recomendado: probar primero cada estructura por consola (clase `Main...` al
estilo del profesor) antes de conectarla a la interfaz.

---

## 11. Entrega y exposición

- Revisión de avance: semana 14 (≈ 60 %).
- Pruebas integradas: antes del 26 de noviembre de 2026.
- **Entrega final: domingo 29 de noviembre de 2026, 23:59.**
- Exposiciones: desde el lunes 30 de noviembre, máximo 20 minutos con preguntas.
- Nombre base del documento y proyecto: `PF_GrupoXX`.
- **Cada integrante sube:** informe Word, presentación PowerPoint y software con
  código fuente.

Orden obligatorio de la rúbrica: formulario principal y nombres (2) → arreglo de
objetos (3) → lista enlazada (3) → cola con prioridades (3) → pila (2) → ABB con
búsqueda secuencial e indexada (3) → preguntas (4). La matriz se presenta como
complemento sin romper ese orden.

Todos deben poder explicar **todos** los algoritmos: se harán preguntas
individuales.

---

## 12. Pendientes por confirmar con el profesor

1. Qué entiende por **búsqueda indexada** en el ABB.
2. Si las entidades (`Plato`, `Mesa`…) también llevan prefijo `c`.
3. Si se puede usar la `ñ` (`tamaño`) o conviene `tamano`.
4. Cómo se reparten las preguntas de la rúbrica con 3 integrantes en lugar de 4.
5. Registrar para la carátula del informe la versión exacta de Apache NetBeans
   (el JDK es el 25 y el sistema de construcción es Maven, ya definidos en la
   sección 1.1) y confirmar que el profesor acepta el JDK 25.

---

## 13. Cómo debe trabajar un agente en este repositorio

1. Identificar para quién trabaja (Steve, Fabiano o Alexander) y **tocar solo
   sus archivos**. Si necesita cambiar algo de otra persona, avisar en lugar de
   editarlo.
2. **Nunca crear ni editar formularios** (`vista/`, `.form`): los hace Steve a
   mano (sección 9).
3. Trabajar **un módulo a la vez**.
4. Antes de cada algoritmo, indicar en qué ejemplo del Apéndice A se basa. Si no
   hay ejemplo, marcar `// [EXTRAPOLADO]`.
5. Explicar cada algoritmo en 3 o 4 líneas: el alumno debe poder sustentarlo
   oralmente.
6. No inventar convenciones, no agregar dependencias, frameworks ni archivos
   fuera del alcance. No modificar el `pom.xml` sin avisar (si no existe, se crea
   una sola vez según la sección 1.1.1). Respetar JDK 25 y Maven. No cambiar firmas de la sección 5 sin
   avisar.
7. Verificar que el código cumple las restricciones de la sección 3 y el estilo
   de la sección 7, y reportar cualquier incumplimiento.
8. **Cumplir el protocolo de la sección 1.2** al usar la terminal: límites de
   tiempo, sin ejecutar la app Swing, máximo 2 intentos por comando, y parar y
   avisar ante cualquier cuelgue. Es preferible entregar código «NO VERIFICADO»
   que seguir esperando.
9. **Cerrar cada tarea con un resumen final** que incluya: archivos creados,
   estado de la compilación (verificada con `mvn`, verificada con `javac`, o NO
   VERIFICADA y por qué) y pendientes para Steve.
10. Si algo no está claro, preguntar; y ante un bloqueo, **avisar en vez de
    insistir**.

---

## Apéndice A — Código de referencia del profesor

Estos fragmentos son la base del estilo. Los marcados **[ORIGINAL]** están copiados
de los archivos de clase. Los marcados **[RECONSTRUIDO]** se armaron a partir de
una descripción del original y pueden diferir en detalles; sirven para ver la
forma, no para copiar literalmente.

### A.1. Pila (`cPila`) — estructura sobre arreglo

**[RECONSTRUIDO]** atributos, constructor y vaciado:

```java
public class cPila {
    private int pila[];
    private int tope;
    private int indMax;
    public int getTope(){
        return tope;
    }
    public cPila(int n){
        pila = new int[n];
        indMax = n - 1;
        pilaVacia();
    }
    public void pilaVacia(){
        tope = -1;
    }
    // insertar, eliminar, accesar (ORIGINAL, abajo)
    // muestraArreglo(): recorre con for y acumula en "cadena" con " - "
}
```

**[ORIGINAL]**

```java
public void insertar(int valor){
    if(tope < indMax){
        tope++;
        pila[tope]= valor;
    }
}
public int eliminar(){
    int valor=0;
    if(tope > -1){
        valor= pila[tope];
        tope--;
    }
    return valor;
}
public int accesar(){
    int valor=0;
    if(tope > -1){
        valor= pila[tope];
    }
    return valor;
}
```

Patrón: control de límites con `if`, sin mensaje de error; `valor` centinela en
`0`; `tope` en −1 significa vacía.

### A.2. Cola (`cCola`) — estructura lineal sobre arreglo

**[RECONSTRUIDO]** atributos y constructor:

```java
public class cCola {
    private int cola[];
    private int first, last;
    private int tamaño;
    public cCola(int n){
        cola = new int[n];
        tamaño = n - 1;
        colaVacia();
    }
    public void colaVacia(){
        first=-1; last = -1;
    }
}
```

**[ORIGINAL]**

```java
public void incluir(int valor){
    if(last < tamaño){
        last++;
        cola[last]= valor;
        if(first == -1)
            first = 0;
    }
}
public int eliminar(){
    int valor=0;
    if(first >= 0){
        valor = cola[first];
        first++;
        if(first > last)
            colaVacia();
    }
    return valor;
}
public int acceso(){
    int valor=0;
    if(first >= 0){
        valor = cola[first];
    }
    return valor;
}
```

Patrón: cola lineal sin circularidad; al sacar el último elemento se invoca
`colaVacia()` para volver `first` y `last` a −1.

### A.3. Nodo (`cNodo`) — nodo de árbol

**[RECONSTRUIDO]** a partir de la descripción (atributos `valor`, `izq`, `der`;
constructor con el dato; getters y setters completos con `this.`):

```java
public class cNodo {
    private int valor;
    private cNodo izq, der;
    public cNodo(int valor){
        this.valor = valor;
    }
    public int getValor(){
        return valor;
    }
    public void setValor(int valor){
        this.valor = valor;
    }
    public cNodo getIzq(){
        return izq;
    }
    public void setIzq(cNodo izq){
        this.izq = izq;
    }
    public cNodo getDer(){
        return der;
    }
    public void setDer(cNodo der){
        this.der = der;
    }
}
```

### A.4. Árbol (`cABB`) — inserción y recorridos

El árbol no declara constructor; `raiz` empieza en `null`. Los punteros auxiliares
`nuevo` y `p` son atributos de la clase.

**[ORIGINAL]**

```java
public class cABB {
    private cNodo raiz, nuevo,p;
    public void ingresarValor(int valor){
        nuevo= new cNodo(valor);
        if(raiz == null)
            raiz = nuevo;
        else{
            p = raiz;
            insertaNodo(p, nuevo);
        }
    }
    public void insertaNodo(cNodo p, cNodo nuevo){
        if (p == null)
            p = nuevo;
        else{
            if(nuevo.getValor() > p.getValor()){
                if(p.getDer() == null)
                    p.setDer(nuevo);
                else insertaNodo(p.getDer(), nuevo);
            }
            else{
                if(p.getIzq() == null)
                    p.setIzq(nuevo);
                else insertaNodo(p.getIzq(), nuevo);
            }
        }
    }  // fin inserta nodo
    public String recorreArbol(int opcion){
        p = raiz; String cadena="";
        switch(opcion){
            case 1:cadena=preOrden(p);break;
            case 2:cadena=inOrden(p);break;
            case 3:cadena=postOrden(p);break;
        }
        return cadena;
    }
    public String preOrden(cNodo p){
        String cadena="";
        if(p != null){
            cadena = cadena + " - "+p.getValor();
            cadena = cadena + preOrden(p.getIzq());
            cadena = cadena + preOrden(p.getDer());
        }
        return cadena;
    }
    public String inOrden(cNodo p){
        String cadena="";
        if(p != null){
            cadena = cadena + inOrden(p.getIzq());
            cadena = cadena + " - "+p.getValor();
            cadena = cadena + inOrden(p.getDer());
        }
        return cadena;
    }
    public String postOrden(cNodo p){
        String cadena="";
        if(p != null){
            cadena = cadena + postOrden(p.getIzq());
            cadena = cadena + postOrden(p.getDer());
            cadena = cadena + " - "+p.getValor();
        }
        return cadena;
    }
}
```

Patrón: método público que crea el nodo y delega a un método recursivo público;
los recorridos no imprimen, acumulan en un `String`; en el original los iguales
se mandan a la izquierda (**en nuestro proyecto se rechazan**, ver sección 5).

### A.5. Clase de prueba (`MaincABB` / `MainPIla`) — estilo de uso

**[RECONSTRUIDO]** forma típica, con datos escritos a mano:

```java
public class MaincABB {
    public static void main(String[] args) {
        cABB oABB= new cABB();
        oABB.ingresarValor(30);
        oABB.ingresarValor(15);
        oABB.ingresarValor(40);
        System.out.println("In Orden: " + oABB.recorreArbol(2));
    }
}
```

Para la pila: `cPila oPila = new cPila(n);` luego `oPila.insertar(10);` y
`System.out.println(oPila.muestraArreglo());`.
