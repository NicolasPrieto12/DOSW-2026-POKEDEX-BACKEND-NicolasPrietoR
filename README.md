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
