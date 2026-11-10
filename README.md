# Chifa Tai Lung

**Sistema de gestión para un restaurante chifa**, desarrollado como proyecto final del curso *Algoritmos y Estructuras de Datos* (UTP, 2026-II) en **Java Swing**, con **Apache NetBeans**.

Es un prototipo académico de escritorio que simula la atención y el consumo dentro del restaurante. Las estructuras de datos se implementan a mano, sin colecciones de Java. No usa base de datos ni servicios externos.

> **Estado del proyecto:** en desarrollo. El repositorio se reinició para empezar el proyecto con la versión de JDK acordada por el equipo. Las funcionalidades descritas abajo forman parte del alcance previsto y no deben interpretarse como implementadas.

## Integrantes

- Steve
- Fabiano
- Alexander

## Funcionalidades previstas

| Módulo | Estructura de datos | Funcionalidad |
| --- | --- | --- |
| Carta de platos | Arreglo de objetos | Registrar, actualizar, eliminar y consultar platos (máximo 50) |
| Mesas | Matriz 4 × 4 | Consultar y cambiar el estado de las 16 mesas |
| Pedidos | Lista enlazada simple | CRUD de pedidos con hasta 10 detalles y cálculo de importes |
| Atención | Cola con prioridad | Turnos preferenciales antes que regulares, en orden de llegada |
| Comprobantes | Pila estática | Apilar, consultar y retirar comprobantes |
| Búsqueda de platos | Árbol binario de búsqueda (ABB) | Inserción sin duplicados y búsqueda secuencial e indexada por nombre |
| Acceso | Roles locales | Administrador y empleado, con permisos diferenciados |

Cada módulo funciona de forma independiente y tiene su propio botón **«Cargar datos de prueba»**.

## Arquitectura

Aplicación monolítica modular con **MVC** y separación por capas:

```text
vista → controlador → estructuras de datos → modelo
```

Paquete base: `pe.edu.utp.chifa`

```text
pe.edu.utp.chifa
├── app/            Main
├── modelo/         Entidades y enumeraciones
├── estructuras/    arreglos, matrices, listas, colas, pilas, arboles
├── controlador/    Controladores por módulo y autenticación
└── vista/          Formularios Swing (JFrame Form)
```

El detalle de clases, contratos de métodos, reglas de estilo y reparto de trabajo está en [`AGENTS.md`](AGENTS.md).

## Tecnologías

| Tecnología | Uso |
| --- | --- |
| Java | Lenguaje de programación |
| Java Swing | Interfaz gráfica de escritorio |
| Apache NetBeans | Entorno de desarrollo y diseñador de formularios |
| JDK | *Por definir por el equipo* |
| Sistema de construcción | *Por definir (Maven o Ant)* |

## Cómo ejecutar el proyecto

1. Clona el repositorio:

   ```bash
   git clone https://github.com/dhaxty/ChifaTaiLung.git
   ```

2. Abre **Apache NetBeans** y selecciona **File > Open Project**.
3. Selecciona la carpeta del proyecto.
4. Verifica que el JDK configurado en NetBeans sea el que indica este README.
5. Ejecuta el proyecto. Si no hay una clase principal configurada, selecciona `pe.edu.utp.chifa.app.Main`.

> Antes de ejecutar, comprueba tu versión de Java con `java -version`.

## Flujo de trabajo

Cada integrante trabaja en su propia rama y se une a `main` al integrar:

```bash
git checkout -b nombre-del-integrante
git add .
git commit -m "feat: descripción breve"
git push -u origin nombre-del-integrante
```

## Contexto académico

Proyecto orientado a aplicar programación orientada a objetos, diseño de interfaces con Java Swing y estructuras de datos y algoritmos (arreglos, matrices, listas enlazadas, colas con prioridad, pilas y árboles binarios de búsqueda) a un caso práctico de gestión de restaurante.
