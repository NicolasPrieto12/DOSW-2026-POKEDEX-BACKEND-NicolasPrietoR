# DOSW-2026-POKEDEX-NicolasPrietoR

**Proyecto:** Pokédex  
**Curso:** Desarrollo y Operaciones de Software (DOSW) · 2026 Intersemestral  
**Estudiante:** Nicolas David Prieto Ramos  
**Fecha:** 25/06/2026

---

## Descripción del Proyecto

Aplicación web tipo Pokédex inspirada en la franquicia Pokémon. Permite a los usuarios explorar, buscar, filtrar y consultar información detallada de Pokémon, crear equipos competitivos y gestionar sus favoritos, con roles diferenciados entre usuarios normales y administradores.

---

## Funcionalidades

- Registro e inicio de sesión con Gmail
- Listado, búsqueda y filtrado de Pokémon
- Consulta de detalle, estadísticas y cadenas evolutivas
- Gestión de favoritos y equipos Pokémon
- Análisis competitivo de equipos
- Panel de estadísticas de uso
- Administración de Pokémon y usuarios (rol administrador)
- Comparación de Pokémon
- Funcionalidades adicionales de exploración

---

## Enlace al tablero de Jira

> _Próximamente_

## Enlace al prototipo (Figma)

> _Próximamente_

---

## Documento de Análisis de Requerimientos

| Campo | Detalle |
|---|---|
| Proyecto | DOSW - Pokédex |
| Estudiante | Nicolas David Prieto Ramos |
| Fecha | 25/06/2026 |
| Versión | 1.0 |

---

## Requerimientos No Funcionales

| Código | Descripción |
|---|---|
| RNF-01 | La interfaz debe ser intuitiva y fácil de usar. |
| RNF-02 | La aplicación debe responder búsquedas en menos de 2 segundos. |
| RNF-03 | La aplicación debe estar disponible al menos el 99% del tiempo. |
| RNF-04 | Solo usuarios autenticados podrán acceder a funcionalidades personalizadas. |
| RNF-05 | La aplicación debe funcionar correctamente en Google Chrome. |
| RNF-06 | La aplicación debe funcionar correctamente en Mozilla Firefox. |
| RNF-07 | La aplicación debe funcionar correctamente en Microsoft Edge. |
| RNF-08 | La aplicación debe adaptarse a dispositivos móviles, tablets y computadores. |
| RNF-09 | Las contraseñas y credenciales deben gestionarse de forma segura mediante autenticación Gmail. |
| RNF-10 | La información almacenada debe mantener integridad y consistencia. |
| RNF-11 | La arquitectura debe permitir agregar nuevas generaciones Pokémon sin afectar funcionalidades existentes. |
| RNF-12 | El código fuente debe ser mantenible y documentado. |
| RNF-13 | La aplicación debe seguir estándares de accesibilidad web. |
| RNF-14 | La interfaz debe presentar contraste adecuado para facilitar la lectura. |
| RNF-15 | El sistema debe registrar eventos y errores para facilitar mantenimiento. |
| RNF-16 | La información de usuarios debe protegerse conforme a buenas prácticas de privacidad. |
| RNF-17 | La aplicación debe soportar múltiples usuarios concurrentes. |
| RNF-18 | La base de datos debe evitar registros duplicados de Pokémon. |
| RNF-19 | La aplicación debe ser escalable para incorporar nuevas funcionalidades. |
| RNF-20 | La recuperación de información debe realizarse de forma eficiente incluso con grandes volúmenes de datos. |

---

## Requerimientos Funcionales

---

### RF-01 — Registro de usuario mediante cuenta Gmail

| Campo | Detalle |
|---|---|
| **Código** | RF-01 |
| **Nombre** | Registro de usuario mediante cuenta Gmail |
| **Descripción** | El sistema permite que un nuevo usuario cree su cuenta en la Pokédex utilizando su cuenta de Gmail como método de autenticación y registro. |
| **Cómo se ejecutará** | El usuario accede a la pantalla de registro, selecciona la opción "Registrarse con Gmail", se redirige al flujo de OAuth de Google y, al autenticarse exitosamente, se crea su perfil en el sistema. |
| **Actor principal** | Usuario no registrado |
| **Precondiciones** | El usuario debe tener una cuenta de Gmail activa. El usuario no debe estar registrado previamente en el sistema. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Cuenta Gmail | Correo electrónico de Google del usuario | Email (OAuth) | Debe ser una cuenta Gmail válida y activa | Sí |
| Token OAuth | Token de autenticación generado por Google | Token | Generado automáticamente por Google OAuth 2.0 | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Perfil de usuario | Cuenta creada con nombre, foto y correo tomados desde Gmail | Objeto usuario | Se almacena en la base de datos con rol "usuario normal" | Sí |
| Mensaje de confirmación | Notificación de registro exitoso | Texto | Mostrado en pantalla tras el registro | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede a la pantalla de inicio y selecciona "Registrarse con Gmail" | — |
| 2 | Sistema | Redirige al flujo de autenticación de Google OAuth 2.0 | — |
| 3 | Usuario | Selecciona o ingresa su cuenta Gmail y otorga permisos | El usuario cancela el proceso → vuelve a la pantalla de inicio |
| 4 | Sistema | Recibe el token OAuth y valida que el correo no esté registrado | Si el correo ya existe → redirige al inicio de sesión |
| 5 | Sistema | Crea el perfil del usuario con los datos obtenidos de Gmail | Error de conexión → muestra mensaje de error |
| 6 | Sistema | Redirige al usuario a la pantalla principal de la Pokédex | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Intenta registrarse con un correo que ya existe en el sistema | — |
| 2 | Sistema | Detecta que el correo ya está registrado | — |
| 3 | Sistema | Muestra mensaje indicando que ya existe una cuenta y ofrece la opción de iniciar sesión | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | El registro solo se permite mediante Gmail. No se contempla registro manual con usuario y contraseña. Los datos del perfil (nombre, foto) se obtienen automáticamente desde la cuenta de Google. |

---

### RF-02 — Inicio de sesión mediante cuenta Gmail

| Campo | Detalle |
|---|---|
| **Código** | RF-02 |
| **Nombre** | Inicio de sesión mediante cuenta Gmail |
| **Descripción** | El sistema permite que un usuario ya registrado inicie sesión en la Pokédex utilizando su cuenta de Gmail a través del protocolo OAuth 2.0. |
| **Cómo se ejecutará** | El usuario accede a la pantalla de inicio, selecciona "Iniciar sesión con Gmail", se autentica con Google y el sistema valida que el correo esté registrado para conceder acceso. |
| **Actor principal** | Usuario registrado |
| **Precondiciones** | El usuario debe estar previamente registrado en el sistema. El usuario debe tener una sesión activa de Gmail o credenciales disponibles. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Cuenta Gmail | Correo electrónico de Google del usuario | Email (OAuth) | Debe coincidir con una cuenta registrada en el sistema | Sí |
| Token OAuth | Token de autenticación generado por Google | Token | Generado automáticamente por Google OAuth 2.0 | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Sesión activa | Token de sesión del usuario en la aplicación | Token de sesión | Se almacena localmente para mantener la sesión | Sí |
| Redirección | Pantalla principal de la Pokédex | Pantalla | Se muestra tras autenticación exitosa | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede a la pantalla de inicio y selecciona "Iniciar sesión con Gmail" | — |
| 2 | Sistema | Redirige al flujo de autenticación de Google OAuth 2.0 | — |
| 3 | Usuario | Selecciona su cuenta Gmail y otorga permisos | El usuario cancela → vuelve a la pantalla de inicio |
| 4 | Sistema | Recibe el token OAuth y valida que el correo esté registrado | Correo no registrado → muestra opción de registrarse |
| 5 | Sistema | Crea la sesión del usuario y redirige a la pantalla principal | Error de conexión → muestra mensaje de error |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Intenta iniciar sesión con un correo no registrado | — |
| 2 | Sistema | No encuentra el correo en la base de datos | — |
| 3 | Sistema | Muestra mensaje de cuenta no encontrada y ofrece la opción de registrarse | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | El inicio de sesión es exclusivamente mediante Gmail. No existe recuperación de contraseña ya que la autenticación es delegada a Google. |

---

### RF-03 — Gestión de perfil de usuario

| Campo | Detalle |
|---|---|
| **Código** | RF-03 |
| **Nombre** | Gestión de perfil de usuario |
| **Descripción** | El sistema permite que el usuario autenticado consulte y edite la información de su perfil personal dentro de la Pokédex, como nombre de entrenador y foto de perfil. |
| **Cómo se ejecutará** | El usuario accede a la sección "Mi Perfil" desde el menú principal, visualiza su información actual y puede modificar los campos editables. Al guardar, el sistema actualiza la información en la base de datos. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión en el sistema. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Nombre de entrenador | Nombre personalizado dentro de la Pokédex | Texto | Máximo 30 caracteres, no puede estar vacío | Sí |
| Foto de perfil | Imagen de perfil del usuario | Imagen | Formato JPG/PNG, máximo 2MB. Por defecto usa la foto de Gmail | No |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Perfil actualizado | Información del perfil con los cambios guardados | Objeto usuario | Se refleja de inmediato en la interfaz | Sí |
| Mensaje de confirmación | Notificación de actualización exitosa | Texto | Mostrado tras guardar cambios | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede a la sección "Mi Perfil" desde el menú | — |
| 2 | Sistema | Muestra la información actual del perfil del usuario | Error de carga → muestra mensaje de error |
| 3 | Usuario | Modifica los campos deseados y selecciona "Guardar" | — |
| 4 | Sistema | Valida los datos ingresados | Datos inválidos → muestra errores de validación |
| 5 | Sistema | Actualiza la información en la base de datos y confirma el cambio | Error de guardado → muestra mensaje de error |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Cancela la edición sin guardar cambios | — |
| 2 | Sistema | Descarta los cambios y mantiene la información original | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | El correo Gmail no es editable ya que es el identificador único del usuario. Los cambios de foto de perfil son opcionales. |

---

### RF-04 — Visualización del listado de Pokémon

| Campo | Detalle |
|---|---|
| **Código** | RF-04 |
| **Nombre** | Visualización del listado de Pokémon |
| **Descripción** | El sistema muestra al usuario un listado paginado de todos los Pokémon disponibles en la Pokédex, con información básica de cada uno como nombre, número, imagen y tipo(s). |
| **Cómo se ejecutará** | Al acceder a la sección principal de la Pokédex, el sistema carga y presenta el listado de Pokémon en formato de cuadrícula o lista, con paginación o scroll infinito. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. Deben existir Pokémon registrados en el sistema. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Página actual | Número de página solicitada | Número | Valor mínimo 1, por defecto 1 | No |
| Cantidad por página | Número de Pokémon a mostrar por página | Número | Por defecto 20, máximo 100 | No |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Lista de Pokémon | Colección de Pokémon con nombre, número, imagen y tipo(s) | Lista de objetos | Ordenados por número de Pokédex por defecto | Sí |
| Total de Pokémon | Número total de Pokémon disponibles | Número | Usado para calcular la paginación | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede a la sección principal de la Pokédex | — |
| 2 | Sistema | Consulta la base de datos y recupera el listado paginado | Error de conexión → muestra mensaje de error |
| 3 | Sistema | Presenta el listado en pantalla con imagen, nombre, número y tipo de cada Pokémon | Sin datos → muestra mensaje "No hay Pokémon disponibles" |
| 4 | Usuario | Navega entre páginas o hace scroll para ver más Pokémon | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Aplica un filtro u ordenamiento desde el listado | — |
| 2 | Sistema | Recarga el listado aplicando el criterio seleccionado | Sin resultados → muestra mensaje "No se encontraron Pokémon" |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | El listado es el punto de entrada principal de la aplicación. Desde cada tarjeta del listado el usuario puede acceder al detalle del Pokémon. |

---

### RF-05 — Búsqueda de Pokémon por nombre

| Campo | Detalle |
|---|---|
| **Código** | RF-05 |
| **Nombre** | Búsqueda de Pokémon por nombre |
| **Descripción** | El sistema permite al usuario buscar un Pokémon específico ingresando su nombre o parte de él en un campo de búsqueda, retornando los resultados que coincidan. |
| **Cómo se ejecutará** | El usuario escribe en el campo de búsqueda el nombre del Pokémon. El sistema filtra en tiempo real o al confirmar la búsqueda y muestra los resultados coincidentes. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. Deben existir Pokémon registrados en el sistema. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Nombre del Pokémon | Nombre completo o parcial del Pokémon a buscar | Texto | Mínimo 1 carácter, no distingue mayúsculas/minúsculas | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Lista de resultados | Pokémon cuyo nombre coincide con el término buscado | Lista de objetos | Incluye nombre, número, imagen y tipo(s) | Sí |
| Mensaje sin resultados | Indicación de que no se encontraron coincidencias | Texto | Se muestra solo cuando no hay resultados | Condicional |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Escribe el nombre o parte del nombre en el campo de búsqueda | — |
| 2 | Sistema | Filtra el listado de Pokémon según el texto ingresado | — |
| 3 | Sistema | Muestra los Pokémon cuyo nombre contiene el texto buscado | Sin coincidencias → muestra "No se encontraron Pokémon con ese nombre" |
| 4 | Usuario | Selecciona un Pokémon de los resultados para ver su detalle | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Borra el texto del campo de búsqueda | — |
| 2 | Sistema | Restablece el listado completo de Pokémon | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | La búsqueda puede funcionar en tiempo real (mientras el usuario escribe). Se recomienda aplicar un debounce de al menos 300ms para no sobrecargar el sistema. |

---

### RF-06 — Búsqueda de Pokémon por número de Pokédex

| Campo | Detalle |
|---|---|
| **Código** | RF-06 |
| **Nombre** | Búsqueda de Pokémon por número de Pokédex |
| **Descripción** | El sistema permite al usuario buscar un Pokémon ingresando su número oficial de Pokédex en el campo de búsqueda. |
| **Cómo se ejecutará** | El usuario ingresa el número en el campo de búsqueda, el sistema identifica que es un valor numérico y retorna el Pokémon correspondiente. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. Deben existir Pokémon registrados en el sistema. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Número de Pokédex | Número oficial del Pokémon en la Pokédex nacional | Número entero | Debe ser un número positivo mayor a 0 | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Pokémon encontrado | Pokémon cuyo número coincide con el ingresado | Objeto Pokémon | Incluye nombre, número, imagen y tipo(s) | Sí |
| Mensaje sin resultados | Indicación de que no existe un Pokémon con ese número | Texto | Se muestra solo cuando no hay coincidencia | Condicional |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Ingresa un número en el campo de búsqueda | — |
| 2 | Sistema | Detecta que el valor ingresado es numérico | — |
| 3 | Sistema | Busca el Pokémon con ese número en la base de datos | Número no existe → muestra "No se encontró un Pokémon con ese número" |
| 4 | Sistema | Muestra el Pokémon correspondiente | — |
| 5 | Usuario | Selecciona el Pokémon para ver su detalle | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Ingresa un número fuera del rango válido (ej: 0 o negativo) | — |
| 2 | Sistema | Valida el rango e informa que el número no es válido | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | El campo de búsqueda puede detectar automáticamente si el valor ingresado es texto o número para aplicar el criterio correcto. |

---

### RF-07 — Consulta del detalle completo de un Pokémon

| Campo | Detalle |
|---|---|
| **Código** | RF-07 |
| **Nombre** | Consulta del detalle completo de un Pokémon |
| **Descripción** | El sistema muestra la información completa de un Pokémon seleccionado, incluyendo estadísticas base, tipos, habilidades, movimientos, cadena evolutiva, descripción y datos generales. |
| **Cómo se ejecutará** | El usuario selecciona un Pokémon desde el listado o desde los resultados de búsqueda y el sistema despliega su ficha de detalle completa. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. El Pokémon debe existir en el sistema. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| ID del Pokémon | Identificador único del Pokémon seleccionado | Número entero | Debe corresponder a un Pokémon existente | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Ficha de detalle | Información completa del Pokémon (nombre, número, tipos, stats, habilidades, movimientos, evoluciones, descripción, altura, peso) | Objeto Pokémon | Todos los campos disponibles en la base de datos | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Selecciona un Pokémon desde el listado o resultados de búsqueda | — |
| 2 | Sistema | Recupera la información completa del Pokémon desde la base de datos | Error de conexión → muestra mensaje de error |
| 3 | Sistema | Despliega la ficha de detalle con toda la información disponible | Pokémon no encontrado → muestra mensaje de error |
| 4 | Usuario | Navega por las secciones del detalle (stats, movimientos, evoluciones, etc.) | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Desde el detalle navega a un Pokémon de su cadena evolutiva | — |
| 2 | Sistema | Carga el detalle del Pokémon seleccionado de la evolución | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | La ficha de detalle es la pantalla más completa de la aplicación. Debe incluir acceso rápido a favoritos y a agregar al equipo. |

---

### RF-08 — Filtrar Pokémon por región

| Campo | Detalle |
|---|---|
| **Código** | RF-08 |
| **Nombre** | Filtrar Pokémon por región |
| **Descripción** | El sistema permite al usuario filtrar el listado de Pokémon según la región de origen (Kanto, Johto, Hoenn, Sinnoh, Unova, Kalos, Alola, Galar, Paldea, etc.). |
| **Cómo se ejecutará** | El usuario selecciona una o más regiones desde el panel de filtros y el sistema actualiza el listado mostrando únicamente los Pokémon de esa región. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. Deben existir Pokémon registrados con información de región. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Región seleccionada | Nombre de la región por la que se desea filtrar | Selector (dropdown/checkbox) | Debe ser una región válida y existente en el sistema | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Lista filtrada | Pokémon pertenecientes a la región seleccionada | Lista de objetos | Incluye nombre, número, imagen y tipo(s) | Sí |
| Mensaje sin resultados | Indicación de que no hay Pokémon para esa región | Texto | Se muestra solo cuando no hay resultados | Condicional |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede al panel de filtros y selecciona una región | — |
| 2 | Sistema | Aplica el filtro de región al listado de Pokémon | — |
| 3 | Sistema | Muestra únicamente los Pokémon de la región seleccionada | Sin resultados → muestra "No se encontraron Pokémon para esta región" |
| 4 | Usuario | Puede combinar este filtro con otros criterios | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Deselecciona el filtro de región | — |
| 2 | Sistema | Restablece el listado completo sin el filtro de región | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | Los filtros deben poder combinarse entre sí. La región se determina según la generación y Pokédex regional de cada Pokémon. |

---

### RF-09 — Filtrar Pokémon por tipo primario

| Campo | Detalle |
|---|---|
| **Código** | RF-09 |
| **Nombre** | Filtrar Pokémon por tipo primario |
| **Descripción** | El sistema permite al usuario filtrar el listado de Pokémon según su tipo primario (Fuego, Agua, Planta, Eléctrico, etc.). |
| **Cómo se ejecutará** | El usuario selecciona un tipo primario desde el panel de filtros y el sistema actualiza el listado mostrando únicamente los Pokémon cuyo tipo principal coincide. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. Deben existir Pokémon registrados con información de tipo primario. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Tipo primario | Tipo principal del Pokémon por el que se desea filtrar | Selector (dropdown/checkbox) | Debe ser uno de los 18 tipos Pokémon válidos | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Lista filtrada | Pokémon cuyo tipo primario coincide con el seleccionado | Lista de objetos | Incluye nombre, número, imagen y tipo(s) | Sí |
| Mensaje sin resultados | Indicación de que no hay Pokémon para ese tipo | Texto | Se muestra solo cuando no hay resultados | Condicional |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede al panel de filtros y selecciona un tipo primario | — |
| 2 | Sistema | Aplica el filtro al listado buscando Pokémon cuyo tipo 1 coincida | — |
| 3 | Sistema | Muestra los Pokémon filtrados | Sin resultados → muestra "No se encontraron Pokémon de ese tipo" |
| 4 | Usuario | Puede combinar con otros filtros activos | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Deselecciona el filtro de tipo primario | — |
| 2 | Sistema | Restablece el listado sin ese filtro | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | Existen 18 tipos Pokémon en total. El tipo primario es el primer tipo asignado al Pokémon. Este filtro puede combinarse con el filtro de tipo secundario (RF-10). |

---

### RF-10 — Filtrar Pokémon por tipo secundario

| Campo | Detalle |
|---|---|
| **Código** | RF-10 |
| **Nombre** | Filtrar Pokémon por tipo secundario |
| **Descripción** | El sistema permite al usuario filtrar el listado de Pokémon según su tipo secundario, mostrando solo aquellos que tengan ese tipo como segundo tipo asignado. |
| **Cómo se ejecutará** | El usuario selecciona un tipo secundario desde el panel de filtros y el sistema actualiza el listado mostrando únicamente los Pokémon que posean ese tipo como tipo 2. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. Deben existir Pokémon registrados con tipo secundario definido. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Tipo secundario | Segundo tipo del Pokémon por el que se desea filtrar | Selector (dropdown/checkbox) | Debe ser uno de los 18 tipos Pokémon válidos | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Lista filtrada | Pokémon cuyo tipo secundario coincide con el seleccionado | Lista de objetos | Incluye nombre, número, imagen y tipo(s) | Sí |
| Mensaje sin resultados | Indicación de que no hay Pokémon con ese tipo secundario | Texto | Se muestra solo cuando no hay resultados | Condicional |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede al panel de filtros y selecciona un tipo secundario | — |
| 2 | Sistema | Aplica el filtro al listado buscando Pokémon cuyo tipo 2 coincida | — |
| 3 | Sistema | Muestra los Pokémon filtrados | Sin resultados → muestra "No se encontraron Pokémon con ese tipo secundario" |
| 4 | Usuario | Puede combinar con otros filtros activos | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Deselecciona el filtro de tipo secundario | — |
| 2 | Sistema | Restablece el listado sin ese filtro | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | No todos los Pokémon tienen tipo secundario. Los Pokémon de un solo tipo no aparecerán en este filtro. Puede combinarse con RF-09 para filtrar por combinación de tipos. |

---

### RF-11 — Filtrar Pokémon por generación

| Campo | Detalle |
|---|---|
| **Código** | RF-11 |
| **Nombre** | Filtrar Pokémon por generación |
| **Descripción** | El sistema permite al usuario filtrar el listado de Pokémon según la generación a la que pertenecen (Generación I al IX). |
| **Cómo se ejecutará** | El usuario selecciona una generación desde el panel de filtros y el sistema actualiza el listado mostrando únicamente los Pokémon de esa generación. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. Deben existir Pokémon registrados con información de generación. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Generación seleccionada | Número de generación por la que se desea filtrar | Selector (dropdown/checkbox) | Debe ser una generación válida entre I y IX | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Lista filtrada | Pokémon pertenecientes a la generación seleccionada | Lista de objetos | Incluye nombre, número, imagen y tipo(s) | Sí |
| Mensaje sin resultados | Indicación de que no hay Pokémon para esa generación | Texto | Se muestra solo cuando no hay resultados | Condicional |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede al panel de filtros y selecciona una generación | — |
| 2 | Sistema | Aplica el filtro de generación al listado | — |
| 3 | Sistema | Muestra únicamente los Pokémon de esa generación | Sin resultados → muestra "No se encontraron Pokémon para esta generación" |
| 4 | Usuario | Puede combinar este filtro con otros criterios | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Deselecciona el filtro de generación | — |
| 2 | Sistema | Restablece el listado completo sin ese filtro | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | Actualmente existen 9 generaciones Pokémon. Este filtro puede combinarse con el de región ya que cada generación corresponde a una región principal. |

---

### RF-12 — Filtrar Pokémon por evolución

| Campo | Detalle |
|---|---|
| **Código** | RF-12 |
| **Nombre** | Filtrar Pokémon por evolución |
| **Descripción** | El sistema permite al usuario filtrar el listado de Pokémon según su etapa evolutiva (básico, primera evolución, segunda evolución, sin evolución). |
| **Cómo se ejecutará** | El usuario selecciona una etapa evolutiva desde el panel de filtros y el sistema actualiza el listado con los Pokémon que correspondan a esa etapa. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. Deben existir Pokémon registrados con información de cadena evolutiva. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Etapa evolutiva | Etapa de evolución por la que se desea filtrar | Selector (dropdown/checkbox) | Valores: Básico, Primera evolución, Segunda evolución, Sin evolución | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Lista filtrada | Pokémon que corresponden a la etapa evolutiva seleccionada | Lista de objetos | Incluye nombre, número, imagen y tipo(s) | Sí |
| Mensaje sin resultados | Indicación de que no hay Pokémon para esa etapa | Texto | Se muestra solo cuando no hay resultados | Condicional |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede al panel de filtros y selecciona una etapa evolutiva | — |
| 2 | Sistema | Aplica el filtro al listado según la etapa seleccionada | — |
| 3 | Sistema | Muestra los Pokémon que corresponden a esa etapa | Sin resultados → muestra "No se encontraron Pokémon para esta etapa evolutiva" |
| 4 | Usuario | Puede combinar este filtro con otros criterios | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Deselecciona el filtro de evolución | — |
| 2 | Sistema | Restablece el listado completo sin ese filtro | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | Los Pokémon legendarios y míticos generalmente no tienen evolución. Este filtro es útil para encontrar Pokémon en su forma final de evolución. |

---

### RF-13 — Filtrar Pokémon por estadísticas (stats)

| Campo | Detalle |
|---|---|
| **Código** | RF-13 |
| **Nombre** | Filtrar Pokémon por estadísticas (stats) |
| **Descripción** | El sistema permite al usuario filtrar el listado de Pokémon estableciendo rangos mínimos y máximos para una o más estadísticas base (HP, Ataque, Defensa, Ataque Especial, Defensa Especial, Velocidad). |
| **Cómo se ejecutará** | El usuario define rangos de valores para las estadísticas deseadas desde el panel de filtros y el sistema muestra los Pokémon que cumplan con esos rangos. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. Deben existir Pokémon registrados con estadísticas base definidas. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Estadística seleccionada | Nombre del stat a filtrar (HP, Ataque, Defensa, etc.) | Selector | Debe ser una estadística válida | Sí |
| Valor mínimo | Valor mínimo del rango para la estadística | Número entero | Mínimo 0, no puede ser mayor al valor máximo | No |
| Valor máximo | Valor máximo del rango para la estadística | Número entero | Máximo 255, no puede ser menor al valor mínimo | No |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Lista filtrada | Pokémon cuyas estadísticas se encuentran dentro del rango definido | Lista de objetos | Incluye nombre, número, imagen y tipo(s) | Sí |
| Mensaje sin resultados | Indicación de que no hay Pokémon para ese rango | Texto | Se muestra solo cuando no hay resultados | Condicional |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede al panel de filtros y selecciona una estadística | — |
| 2 | Usuario | Define el rango mínimo y/o máximo para esa estadística | — |
| 3 | Sistema | Valida que el rango sea coherente | Rango inválido → muestra mensaje de validación |
| 4 | Sistema | Filtra y muestra los Pokémon que cumplen el rango | Sin resultados → muestra "No se encontraron Pokémon con esas estadísticas" |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Limpia el filtro de estadísticas | — |
| 2 | Sistema | Restablece el listado completo sin ese filtro | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | Se recomienda usar sliders para definir los rangos de estadísticas. El usuario puede filtrar por múltiples stats simultáneamente. |

---

### RF-14 — Filtrar Pokémon por ataques aprendidos

| Campo | Detalle |
|---|---|
| **Código** | RF-14 |
| **Nombre** | Filtrar Pokémon por ataques aprendidos |
| **Descripción** | El sistema permite al usuario filtrar el listado de Pokémon según un ataque o movimiento específico que puedan aprender. |
| **Cómo se ejecutará** | El usuario ingresa o selecciona un ataque desde el panel de filtros y el sistema muestra todos los Pokémon que pueden aprender dicho ataque. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. Deben existir Pokémon registrados con sus movimientos asociados. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Nombre del ataque | Nombre del movimiento por el que se desea filtrar | Texto / Selector | Debe corresponder a un ataque existente en el sistema | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Lista filtrada | Pokémon que pueden aprender el ataque seleccionado | Lista de objetos | Incluye nombre, número, imagen y tipo(s) | Sí |
| Mensaje sin resultados | Indicación de que ningún Pokémon aprende ese ataque | Texto | Se muestra solo cuando no hay resultados | Condicional |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede al panel de filtros e ingresa o selecciona un ataque | — |
| 2 | Sistema | Busca todos los Pokémon asociados a ese movimiento | Ataque no encontrado → muestra "El ataque ingresado no existe" |
| 3 | Sistema | Muestra el listado de Pokémon que pueden aprender ese ataque | Sin resultados → muestra "Ningún Pokémon aprende ese ataque" |
| 4 | Usuario | Puede combinar con otros filtros activos | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Limpia el filtro de ataque | — |
| 2 | Sistema | Restablece el listado completo sin ese filtro | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | Un Pokémon puede aprender ataques por nivel, MT/MO, tutor o huevo. El filtro debe considerar todas las formas de aprendizaje. |

---

### RF-15 — Filtrar Pokémon por habilidades

| Campo | Detalle |
|---|---|
| **Código** | RF-15 |
| **Nombre** | Filtrar Pokémon por habilidades |
| **Descripción** | El sistema permite al usuario filtrar el listado de Pokémon según una habilidad específica que posean, incluyendo habilidades ocultas. |
| **Cómo se ejecutará** | El usuario ingresa o selecciona una habilidad desde el panel de filtros y el sistema muestra todos los Pokémon que la posean. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. Deben existir Pokémon registrados con sus habilidades asociadas. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Nombre de la habilidad | Nombre de la habilidad por la que se desea filtrar | Texto / Selector | Debe corresponder a una habilidad existente en el sistema | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Lista filtrada | Pokémon que poseen la habilidad seleccionada | Lista de objetos | Incluye nombre, número, imagen, tipo(s) e indicador si es habilidad oculta | Sí |
| Mensaje sin resultados | Indicación de que ningún Pokémon tiene esa habilidad | Texto | Se muestra solo cuando no hay resultados | Condicional |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede al panel de filtros e ingresa o selecciona una habilidad | — |
| 2 | Sistema | Busca todos los Pokémon que posean esa habilidad | Habilidad no encontrada → muestra "La habilidad ingresada no existe" |
| 3 | Sistema | Muestra el listado de Pokémon con esa habilidad | Sin resultados → muestra "Ningún Pokémon posee esa habilidad" |
| 4 | Usuario | Puede combinar con otros filtros activos | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Limpia el filtro de habilidad | — |
| 2 | Sistema | Restablece el listado completo sin ese filtro | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | Cada Pokémon puede tener hasta 3 habilidades (2 normales y 1 oculta). El filtro debe indicar si la habilidad es normal u oculta para los resultados mostrados. |

---

### RF-16 — Filtrar Pokémon por mega evolución

| Campo | Detalle |
|---|---|
| **Código** | RF-16 |
| **Nombre** | Filtrar Pokémon por mega evolución |
| **Descripción** | El sistema permite al usuario filtrar el listado de Pokémon según si tienen o no mega evolución disponible. |
| **Cómo se ejecutará** | El usuario activa la opción "Con mega evolución" o "Sin mega evolución" desde el panel de filtros y el sistema actualiza el listado correspondiente. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. Deben existir Pokémon registrados con el campo de mega evolución definido. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Tiene mega evolución | Indica si se desea filtrar Pokémon con o sin mega evolución | Toggle / Radio button | Valores: Con mega evolución / Sin mega evolución / Todos | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Lista filtrada | Pokémon que cumplen el criterio de mega evolución seleccionado | Lista de objetos | Incluye nombre, número, imagen y tipo(s) | Sí |
| Mensaje sin resultados | Indicación de que no hay Pokémon para ese criterio | Texto | Se muestra solo cuando no hay resultados | Condicional |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede al panel de filtros y selecciona la opción de mega evolución | — |
| 2 | Sistema | Aplica el filtro al listado según el criterio seleccionado | — |
| 3 | Sistema | Muestra los Pokémon que cumplen el criterio | Sin resultados → muestra "No se encontraron Pokémon con ese criterio" |
| 4 | Usuario | Puede combinar con otros filtros activos | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Deselecciona el filtro de mega evolución | — |
| 2 | Sistema | Restablece el listado completo sin ese filtro | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | Las mega evoluciones fueron introducidas en la Generación VI. Solo ciertos Pokémon tienen esta capacidad. El filtro debe mostrar también las formas mega en el detalle del Pokémon. |

---

### RF-17 — Ordenar Pokémon por nombre

| Campo | Detalle |
|---|---|
| **Código** | RF-17 |
| **Nombre** | Ordenar Pokémon por nombre |
| **Descripción** | El sistema permite al usuario ordenar el listado de Pokémon alfabéticamente por nombre, de forma ascendente o descendente. |
| **Cómo se ejecutará** | El usuario selecciona la opción de ordenamiento por nombre desde el panel de ordenamiento y el sistema reordena el listado inmediatamente. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. Deben existir Pokémon registrados en el sistema. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Criterio de orden | Dirección del ordenamiento por nombre | Selector | Valores: A-Z / Z-A | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Lista ordenada | Listado de Pokémon ordenado alfabéticamente según el criterio | Lista de objetos | Incluye nombre, número, imagen y tipo(s) | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Selecciona "Ordenar por nombre" y elige A-Z o Z-A | — |
| 2 | Sistema | Reordena el listado alfabéticamente según el criterio elegido | — |
| 3 | Sistema | Muestra el listado reordenado | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Cambia el criterio de ordenamiento a otro campo | — |
| 2 | Sistema | Aplica el nuevo criterio y actualiza el listado | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | El ordenamiento puede combinarse con los filtros activos. Por defecto el listado se muestra ordenado por número de Pokédex. |

---

### RF-18 — Ordenar Pokémon por número de Pokédex

| Campo | Detalle |
|---|---|
| **Código** | RF-18 |
| **Nombre** | Ordenar Pokémon por número de Pokédex |
| **Descripción** | El sistema permite al usuario ordenar el listado de Pokémon por su número oficial de Pokédex, de forma ascendente o descendente. |
| **Cómo se ejecutará** | El usuario selecciona la opción de ordenamiento por número desde el panel de ordenamiento y el sistema reordena el listado. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. Deben existir Pokémon registrados en el sistema. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Criterio de orden | Dirección del ordenamiento por número | Selector | Valores: Ascendente (001→) / Descendente (→001) | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Lista ordenada | Listado de Pokémon ordenado por número de Pokédex | Lista de objetos | Incluye nombre, número, imagen y tipo(s) | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Selecciona "Ordenar por número" y elige ascendente o descendente | — |
| 2 | Sistema | Reordena el listado numéricamente según el criterio elegido | — |
| 3 | Sistema | Muestra el listado reordenado | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Cambia el criterio de ordenamiento a otro campo | — |
| 2 | Sistema | Aplica el nuevo criterio y actualiza el listado | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | Este es el ordenamiento por defecto de la aplicación (ascendente). El número de Pokédex es el identificador más reconocido por los fans de la franquicia. |

---

### RF-19 — Ordenar Pokémon por generación

| Campo | Detalle |
|---|---|
| **Código** | RF-19 |
| **Nombre** | Ordenar Pokémon por generación |
| **Descripción** | El sistema permite al usuario ordenar el listado de Pokémon según la generación a la que pertenecen, de forma ascendente o descendente. |
| **Cómo se ejecutará** | El usuario selecciona la opción de ordenamiento por generación desde el panel de ordenamiento y el sistema reordena el listado agrupando por generación. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. Deben existir Pokémon registrados con información de generación. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Criterio de orden | Dirección del ordenamiento por generación | Selector | Valores: Gen I→IX / Gen IX→I | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Lista ordenada | Listado de Pokémon ordenado por generación | Lista de objetos | Incluye nombre, número, imagen y tipo(s) | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Selecciona "Ordenar por generación" y elige ascendente o descendente | — |
| 2 | Sistema | Reordena el listado por generación según el criterio elegido | — |
| 3 | Sistema | Muestra el listado reordenado | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Cambia el criterio de ordenamiento a otro campo | — |
| 2 | Sistema | Aplica el nuevo criterio y actualiza el listado | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | Dentro de cada generación los Pokémon se ordenan por número de Pokédex. Este ordenamiento puede combinarse con el filtro de generación (RF-11). |

---

### RF-20 — Ordenar Pokémon por estadísticas

| Campo | Detalle |
|---|---|
| **Código** | RF-20 |
| **Nombre** | Ordenar Pokémon por estadísticas |
| **Descripción** | El sistema permite al usuario ordenar el listado de Pokémon según una estadística base específica (HP, Ataque, Defensa, Ataque Especial, Defensa Especial, Velocidad o Total), de forma ascendente o descendente. |
| **Cómo se ejecutará** | El usuario selecciona la estadística por la que desea ordenar y la dirección del orden desde el panel de ordenamiento, y el sistema reordena el listado. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. Deben existir Pokémon registrados con estadísticas base definidas. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Estadística seleccionada | Nombre del stat por el que se desea ordenar | Selector | Valores: HP, Ataque, Defensa, Atq. Esp., Def. Esp., Velocidad, Total | Sí |
| Criterio de orden | Dirección del ordenamiento | Selector | Valores: Mayor a menor / Menor a mayor | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Lista ordenada | Listado de Pokémon ordenado por la estadística seleccionada | Lista de objetos | Incluye nombre, número, imagen, tipo(s) y valor del stat seleccionado | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Selecciona "Ordenar por estadística", elige el stat y la dirección | — |
| 2 | Sistema | Reordena el listado según la estadística y dirección elegidas | — |
| 3 | Sistema | Muestra el listado reordenado resaltando el valor del stat seleccionado | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Cambia la estadística de ordenamiento | — |
| 2 | Sistema | Reordena el listado con el nuevo stat seleccionado | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | El stat "Total" corresponde a la suma de todas las estadísticas base del Pokémon. Este ordenamiento es útil para encontrar los Pokémon más fuertes o más débiles en una categoría específica. |

---

### RF-21 — Guardar Pokémon como favorito

| Campo | Detalle |
|---|---|
| **Código** | RF-21 |
| **Nombre** | Guardar Pokémon como favorito |
| **Descripción** | El sistema permite al usuario marcar un Pokémon como favorito para guardarlo en su lista personal de favoritos y acceder a él fácilmente. |
| **Cómo se ejecutará** | El usuario hace clic en el ícono de favorito (corazón o estrella) desde el listado o desde el detalle del Pokémon y el sistema lo agrega a su lista de favoritos. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. El Pokémon debe existir en el sistema. El Pokémon no debe estar ya en la lista de favoritos del usuario. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| ID del Pokémon | Identificador del Pokémon a marcar como favorito | Número entero | Debe corresponder a un Pokémon existente | Sí |
| ID del usuario | Identificador del usuario que realiza la acción | Número entero | Obtenido automáticamente desde la sesión activa | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Confirmación | Indicación de que el Pokémon fue agregado a favoritos | Texto / Ícono | El ícono de favorito cambia a estado activo | Sí |
| Lista actualizada | Lista de favoritos del usuario con el nuevo Pokémon incluido | Lista de objetos | Se actualiza en tiempo real | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Hace clic en el ícono de favorito de un Pokémon | — |
| 2 | Sistema | Verifica que el Pokémon no esté ya en favoritos del usuario | Ya está en favoritos → no hace nada o muestra aviso |
| 3 | Sistema | Agrega el Pokémon a la lista de favoritos del usuario | Error de guardado → muestra mensaje de error |
| 4 | Sistema | Actualiza el ícono a estado activo y confirma la acción | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Intenta guardar como favorito sin haber iniciado sesión | — |
| 2 | Sistema | Redirige al usuario a la pantalla de inicio de sesión | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | El ícono de favorito debe ser visible desde el listado y desde el detalle del Pokémon. El cambio de estado debe reflejarse en tiempo real sin recargar la página. |

---

### RF-22 — Eliminar Pokémon de favoritos

| Campo | Detalle |
|---|---|
| **Código** | RF-22 |
| **Nombre** | Eliminar Pokémon de favoritos |
| **Descripción** | El sistema permite al usuario quitar un Pokémon de su lista de favoritos. |
| **Cómo se ejecutará** | El usuario hace clic en el ícono de favorito activo desde el listado, el detalle del Pokémon o desde su lista de favoritos, y el sistema lo elimina de dicha lista. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. El Pokémon debe estar en la lista de favoritos del usuario. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| ID del Pokémon | Identificador del Pokémon a quitar de favoritos | Número entero | Debe estar en la lista de favoritos del usuario | Sí |
| ID del usuario | Identificador del usuario que realiza la acción | Número entero | Obtenido automáticamente desde la sesión activa | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Confirmación | Indicación de que el Pokémon fue eliminado de favoritos | Texto / Ícono | El ícono de favorito cambia a estado inactivo | Sí |
| Lista actualizada | Lista de favoritos del usuario sin el Pokémon eliminado | Lista de objetos | Se actualiza en tiempo real | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Hace clic en el ícono de favorito activo de un Pokémon | — |
| 2 | Sistema | Verifica que el Pokémon esté en la lista de favoritos del usuario | — |
| 3 | Sistema | Elimina el Pokémon de la lista de favoritos | Error de eliminación → muestra mensaje de error |
| 4 | Sistema | Actualiza el ícono a estado inactivo y confirma la acción | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Elimina un favorito desde la sección "Mis Favoritos" | — |
| 2 | Sistema | Elimina el Pokémon y actualiza la lista visible | Si la lista queda vacía → muestra mensaje "No tienes Pokémon favoritos aún" |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | La eliminación debe ser inmediata y reflejarse en todos los puntos donde se muestre el estado de favorito del Pokémon. |

---

### RF-23 — Consultar lista de Pokémon favoritos

| Campo | Detalle |
|---|---|
| **Código** | RF-23 |
| **Nombre** | Consultar lista de Pokémon favoritos |
| **Descripción** | El sistema permite al usuario ver todos los Pokémon que ha marcado como favoritos en una sección dedicada. |
| **Cómo se ejecutará** | El usuario accede a la sección "Mis Favoritos" desde el menú y el sistema muestra el listado de todos sus Pokémon favoritos. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| ID del usuario | Identificador del usuario cuya lista se consulta | Número entero | Obtenido automáticamente desde la sesión activa | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Lista de favoritos | Pokémon marcados como favoritos por el usuario | Lista de objetos | Incluye nombre, número, imagen y tipo(s) | Sí |
| Mensaje lista vacía | Indicación de que el usuario no tiene favoritos guardados | Texto | Se muestra solo cuando la lista está vacía | Condicional |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede a la sección "Mis Favoritos" desde el menú | — |
| 2 | Sistema | Recupera la lista de Pokémon favoritos del usuario | Error de conexión → muestra mensaje de error |
| 3 | Sistema | Muestra el listado de favoritos | Lista vacía → muestra "No tienes Pokémon favoritos aún" |
| 4 | Usuario | Puede seleccionar un Pokémon para ver su detalle o eliminarlo de favoritos | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Elimina un Pokémon desde la lista de favoritos | — |
| 2 | Sistema | Actualiza la lista en tiempo real removiendo el Pokémon | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | La sección de favoritos debe permitir acceder al detalle de cada Pokémon y también eliminarlo directamente desde ahí. |

---

### RF-24 — Crear equipos Pokémon

| Campo | Detalle |
|---|---|
| **Código** | RF-24 |
| **Nombre** | Crear equipos Pokémon |
| **Descripción** | El sistema permite al usuario crear un equipo Pokémon seleccionando hasta 6 Pokémon, asignándole un nombre al equipo para su posterior consulta y análisis competitivo. |
| **Cómo se ejecutará** | El usuario accede a la sección "Mis Equipos", selecciona "Crear nuevo equipo", le asigna un nombre, agrega hasta 6 Pokémon buscándolos o seleccionándolos del listado, y guarda el equipo. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. Deben existir Pokémon registrados en el sistema. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Nombre del equipo | Nombre identificador del equipo | Texto | Máximo 30 caracteres, no puede estar vacío | Sí |
| Pokémon seleccionados | Lista de Pokémon que conforman el equipo | Lista de IDs | Mínimo 1, máximo 6 Pokémon por equipo | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Equipo creado | Equipo guardado con nombre y Pokémon seleccionados | Objeto equipo | Se almacena vinculado al usuario | Sí |
| Mensaje de confirmación | Notificación de que el equipo fue creado exitosamente | Texto | Mostrado tras guardar el equipo | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede a "Mis Equipos" y selecciona "Crear nuevo equipo" | — |
| 2 | Usuario | Ingresa un nombre para el equipo | Nombre vacío → muestra error de validación |
| 3 | Usuario | Busca y agrega Pokémon al equipo (hasta 6) | Intento de agregar más de 6 → muestra aviso de límite |
| 4 | Usuario | Selecciona "Guardar equipo" | — |
| 5 | Sistema | Valida los datos y guarda el equipo | Error de guardado → muestra mensaje de error |
| 6 | Sistema | Redirige a la vista del equipo creado con confirmación | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Cancela la creación del equipo | — |
| 2 | Sistema | Descarta los datos y regresa a la lista de equipos | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | Un usuario puede tener múltiples equipos. El equipo debe permitir Pokémon repetidos según preferencia del usuario. Cada equipo puede tener entre 1 y 6 Pokémon. |

---

### RF-25 — Editar equipos Pokémon

| Campo | Detalle |
|---|---|
| **Código** | RF-25 |
| **Nombre** | Editar equipos Pokémon |
| **Descripción** | El sistema permite al usuario modificar un equipo Pokémon existente, cambiando su nombre o los Pokémon que lo conforman. |
| **Cómo se ejecutará** | El usuario accede a la sección "Mis Equipos", selecciona un equipo existente, hace clic en "Editar" y realiza los cambios deseados antes de guardar. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. El usuario debe tener al menos un equipo creado. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| ID del equipo | Identificador del equipo a editar | Número entero | Debe corresponder a un equipo existente del usuario | Sí |
| Nombre del equipo | Nuevo nombre del equipo | Texto | Máximo 30 caracteres, no puede estar vacío | No |
| Pokémon seleccionados | Nueva lista de Pokémon del equipo | Lista de IDs | Mínimo 1, máximo 6 Pokémon | No |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Equipo actualizado | Equipo con los cambios aplicados | Objeto equipo | Se refleja de inmediato en la interfaz | Sí |
| Mensaje de confirmación | Notificación de que los cambios fueron guardados | Texto | Mostrado tras guardar los cambios | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede a "Mis Equipos" y selecciona un equipo | — |
| 2 | Usuario | Hace clic en "Editar" | — |
| 3 | Usuario | Modifica el nombre y/o los Pokémon del equipo | — |
| 4 | Usuario | Selecciona "Guardar cambios" | — |
| 5 | Sistema | Valida los datos y actualiza el equipo | Error de guardado → muestra mensaje de error |
| 6 | Sistema | Confirma los cambios y muestra el equipo actualizado | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Cancela la edición sin guardar | — |
| 2 | Sistema | Descarta los cambios y mantiene el equipo original | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | El usuario solo puede editar sus propios equipos. Los cambios deben reflejarse inmediatamente en el análisis competitivo del equipo. |
