# DOSW-2026-POKEDEX-BACKEND-NicolasPrietoR

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

🔗 [Ver tablero en Jira](https://teamdosw.atlassian.net/jira/software/projects/NIKO/boards/2)

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

### RF-01 — Registro de usuario

| Campo | Detalle |
|---|---|
| **Código** | RF-01 |
| **Nombre** | Registro de usuario |
| **Descripción** | El sistema deberá permitir que los usuarios se registren utilizando una cuenta de Google. |
| **Cómo se ejecutará** | El usuario accede a la pantalla de registro, selecciona "Registrarse con Google", se autentica mediante OAuth 2.0 y el sistema crea su perfil automáticamente con los datos de la cuenta. |
| **Actor principal** | Usuario no registrado |
| **Precondiciones** | El usuario debe tener una cuenta de Google activa y no estar registrado previamente en el sistema. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Cuenta Google | Correo electrónico de Google del usuario | Email (OAuth) | Debe ser una cuenta Google válida y activa | Sí |
| Token OAuth | Token de autenticación generado por Google | Token | Generado automáticamente por Google OAuth 2.0 | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Perfil de usuario | Cuenta creada con nombre, foto y correo obtenidos de Google | Objeto usuario | Se almacena con rol "usuario normal" | Sí |
| Mensaje de confirmación | Notificación de registro exitoso | Texto | Mostrado en pantalla tras el registro | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede a la pantalla de inicio y selecciona "Registrarse con Google" | — |
| 2 | Sistema | Redirige al flujo de autenticación de Google OAuth 2.0 | — |
| 3 | Usuario | Selecciona su cuenta Google y otorga permisos | El usuario cancela → vuelve a la pantalla de inicio |
| 4 | Sistema | Recibe el token y valida que el correo no esté registrado | Correo ya existe → redirige al inicio de sesión |
| 5 | Sistema | Crea el perfil del usuario con los datos de Google | Error de conexión → muestra mensaje de error |
| 6 | Sistema | Redirige al usuario a la pantalla principal | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Intenta registrarse con un correo ya existente | — |
| 2 | Sistema | Detecta el correo duplicado y ofrece la opción de iniciar sesión | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | El registro es exclusivamente mediante Google. Los datos del perfil se obtienen automáticamente desde la cuenta de Google. |

**Reglas de Negocio**

| No. | Descripción |
|---|---|
| 1 | Solo se permite el registro mediante cuenta de Google. |
| 2 | No se pueden registrar dos usuarios con el mismo correo electrónico. |
| 3 | El rol asignado por defecto es "usuario normal". |

**Abreviaturas**

| Abreviatura | Significado |
|---|---|
| OAuth | Open Authorization |
| RF | Requerimiento Funcional |

**Historial de Revisión**

| Elaborado por | Aprobado por | Fecha | Descripción y Justificación de Cambios |
|---|---|---|---|
| Nicolas David Prieto Ramos | — | 25/06/2026 | Versión inicial del documento. |

---

### RF-02 — Inicio de sesión

| Campo | Detalle |
|---|---|
| **Código** | RF-02 |
| **Nombre** | Inicio de sesión |
| **Descripción** | El sistema deberá permitir que los usuarios inicien sesión mediante autenticación con Google. |
| **Cómo se ejecutará** | El usuario selecciona "Iniciar sesión con Google", se autentica con OAuth 2.0 y el sistema valida que el correo esté registrado para conceder acceso. |
| **Actor principal** | Usuario registrado |
| **Precondiciones** | El usuario debe estar registrado en el sistema y tener una cuenta de Google activa. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Cuenta Google | Correo electrónico de Google del usuario | Email (OAuth) | Debe coincidir con una cuenta registrada | Sí |
| Token OAuth | Token de autenticación generado por Google | Token | Generado automáticamente por Google OAuth 2.0 | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Sesión activa | Token de sesión del usuario en la aplicación | Token de sesión | Se almacena localmente para mantener la sesión | Sí |
| Redirección | Pantalla principal de la Pokédex | Pantalla | Se muestra tras autenticación exitosa | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Selecciona "Iniciar sesión con Google" | — |
| 2 | Sistema | Redirige al flujo de autenticación de Google OAuth 2.0 | — |
| 3 | Usuario | Selecciona su cuenta y otorga permisos | El usuario cancela → vuelve a la pantalla de inicio |
| 4 | Sistema | Valida que el correo esté registrado | Correo no registrado → muestra opción de registrarse |
| 5 | Sistema | Crea la sesión y redirige a la pantalla principal | Error de conexión → muestra mensaje de error |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Intenta iniciar sesión con cuenta bloqueada | — |
| 2 | Sistema | Detecta el bloqueo y muestra mensaje de cuenta desactivada | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | El inicio de sesión es exclusivamente mediante Google. No existe recuperación de contraseña ya que la autenticación es delegada a Google. |

**Reglas de Negocio**

| No. | Descripción |
|---|---|
| 1 | Solo se permite el inicio de sesión mediante cuenta de Google. |
| 2 | Un usuario bloqueado no puede iniciar sesión. |
| 3 | La sesión debe expirar tras un período de inactividad definido. |

**Abreviaturas**

| Abreviatura | Significado |
|---|---|
| OAuth | Open Authorization |
| RF | Requerimiento Funcional |

**Historial de Revisión**

| Elaborado por | Aprobado por | Fecha | Descripción y Justificación de Cambios |
|---|---|---|---|
| Nicolas David Prieto Ramos | — | 25/06/2026 | Versión inicial del documento. |

---

### RF-03 — Gestión de perfil

| Campo | Detalle |
|---|---|
| **Código** | RF-03 |
| **Nombre** | Gestión de perfil |
| **Descripción** | El sistema deberá permitir consultar y actualizar la información del perfil del usuario. |
| **Cómo se ejecutará** | El usuario accede a "Mi Perfil", visualiza su información actual y puede modificar los campos editables. Al guardar, el sistema actualiza la información en la base de datos. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión en el sistema. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Nombre de entrenador | Nombre personalizado dentro de la Pokédex | Texto | Máximo 30 caracteres, no puede estar vacío | Sí |
| Foto de perfil | Imagen de perfil del usuario | Imagen | Formato JPG/PNG, máximo 2MB | No |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Perfil actualizado | Información del perfil con los cambios guardados | Objeto usuario | Se refleja de inmediato en la interfaz | Sí |
| Mensaje de confirmación | Notificación de actualización exitosa | Texto | Mostrado tras guardar cambios | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede a "Mi Perfil" desde el menú | — |
| 2 | Sistema | Muestra la información actual del perfil | Error de carga → muestra mensaje de error |
| 3 | Usuario | Modifica los campos deseados y selecciona "Guardar" | — |
| 4 | Sistema | Valida los datos ingresados | Datos inválidos → muestra errores de validación |
| 5 | Sistema | Actualiza la información y confirma el cambio | Error de guardado → muestra mensaje de error |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Cancela la edición sin guardar | — |
| 2 | Sistema | Descarta los cambios y mantiene la información original | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | El correo Google no es editable ya que es el identificador único del usuario. |

**Reglas de Negocio**

| No. | Descripción |
|---|---|
| 1 | El correo electrónico no puede ser modificado por el usuario. |
| 2 | El nombre de entrenador debe ser único en el sistema. |
| 3 | La foto de perfil es opcional; por defecto se usa la foto de Google. |

**Abreviaturas**

| Abreviatura | Significado |
|---|---|
| RF | Requerimiento Funcional |
| JPG/PNG | Formatos de imagen soportados |

**Historial de Revisión**

| Elaborado por | Aprobado por | Fecha | Descripción y Justificación de Cambios |
|---|---|---|---|
| Nicolas David Prieto Ramos | — | 25/06/2026 | Versión inicial del documento. |

---

### RF-04 — Consulta de Pokédex

| Campo | Detalle |
|---|---|
| **Código** | RF-04 |
| **Nombre** | Consulta de Pokédex |
| **Descripción** | El sistema deberá permitir visualizar el listado completo de Pokémon y consultar la información detallada de cada uno. |
| **Cómo se ejecutará** | El usuario accede a la sección principal de la Pokédex y el sistema presenta el listado paginado. Al seleccionar un Pokémon, se despliega su ficha completa con estadísticas, tipos, habilidades, movimientos y evoluciones. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. Deben existir Pokémon registrados en el sistema. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Página actual | Número de página solicitada | Número | Mínimo 1, por defecto 1 | No |
| ID del Pokémon | Identificador del Pokémon a consultar en detalle | Número entero | Debe corresponder a un Pokémon existente | Condicional |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Lista de Pokémon | Pokémon con nombre, número, imagen y tipo(s) | Lista de objetos | Ordenados por número de Pokédex por defecto | Sí |
| Ficha de detalle | Información completa del Pokémon seleccionado | Objeto Pokémon | Incluye stats, habilidades, movimientos y evoluciones | Condicional |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede a la sección principal de la Pokédex | — |
| 2 | Sistema | Recupera y muestra el listado paginado de Pokémon | Error de conexión → muestra mensaje de error |
| 3 | Usuario | Selecciona un Pokémon para ver su detalle | — |
| 4 | Sistema | Carga y despliega la ficha completa del Pokémon | Pokémon no encontrado → muestra mensaje de error |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Navega entre páginas del listado | — |
| 2 | Sistema | Carga la página solicitada con los Pokémon correspondientes | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | El listado es el punto de entrada principal. Desde la ficha de detalle el usuario puede agregar el Pokémon a favoritos o a un equipo. |

**Reglas de Negocio**

| No. | Descripción |
|---|---|
| 1 | Cada consulta al detalle de un Pokémon debe registrarse para las estadísticas de uso. |
| 2 | El listado se ordena por número de Pokédex de forma ascendente por defecto. |
| 3 | Solo usuarios autenticados pueden acceder al listado y detalle. |

**Abreviaturas**

| Abreviatura | Significado |
|---|---|
| RF | Requerimiento Funcional |
| Stats | Estadísticas base del Pokémon |

**Historial de Revisión**

| Elaborado por | Aprobado por | Fecha | Descripción y Justificación de Cambios |
|---|---|---|---|
| Nicolas David Prieto Ramos | — | 25/06/2026 | Versión inicial del documento. |

---

### RF-05 — Búsqueda de Pokémon

| Campo | Detalle |
|---|---|
| **Código** | RF-05 |
| **Nombre** | Búsqueda de Pokémon |
| **Descripción** | El sistema deberá permitir buscar Pokémon por nombre o número de Pokédex. |
| **Cómo se ejecutará** | El usuario ingresa un texto o número en el campo de búsqueda y el sistema retorna los Pokémon que coincidan con el criterio ingresado. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. Deben existir Pokémon registrados en el sistema. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Término de búsqueda | Nombre completo o parcial, o número del Pokémon | Texto / Número | Mínimo 1 carácter; si es numérico se busca por número de Pokédex | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Lista de resultados | Pokémon que coinciden con el término buscado | Lista de objetos | Incluye nombre, número, imagen y tipo(s) | Sí |
| Mensaje sin resultados | Indicación de que no se encontraron coincidencias | Texto | Se muestra solo cuando no hay resultados | Condicional |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Ingresa un término en el campo de búsqueda | — |
| 2 | Sistema | Detecta si el valor es texto o número y aplica el criterio correspondiente | — |
| 3 | Sistema | Retorna los Pokémon que coincidan | Sin resultados → muestra "No se encontraron Pokémon" |
| 4 | Usuario | Selecciona un Pokémon para ver su detalle | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Borra el campo de búsqueda | — |
| 2 | Sistema | Restablece el listado completo de Pokémon | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | La búsqueda puede funcionar en tiempo real. Se recomienda un debounce de 300ms. La búsqueda por número debe aceptar solo valores positivos mayores a 0. |

**Reglas de Negocio**

| No. | Descripción |
|---|---|
| 1 | La búsqueda por nombre no distingue mayúsculas de minúsculas. |
| 2 | La búsqueda por número solo acepta enteros positivos mayores a 0. |
| 3 | El sistema detecta automáticamente si el término ingresado es texto o número. |

**Abreviaturas**

| Abreviatura | Significado |
|---|---|
| RF | Requerimiento Funcional |
| ms | Milisegundos |

**Historial de Revisión**

| Elaborado por | Aprobado por | Fecha | Descripción y Justificación de Cambios |
|---|---|---|---|
| Nicolas David Prieto Ramos | — | 25/06/2026 | Versión inicial del documento. |

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

## Diagrama de Casos de Uso

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

## Diagrama de Casos de Uso

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

## Diagrama de Casos de Uso

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

## Diagrama de Casos de Uso

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

## Diagrama de Casos de Uso

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

---

### RF-26 — Eliminar equipos Pokémon

| Campo | Detalle |
|---|---|
| **Código** | RF-26 |
| **Nombre** | Eliminar equipos Pokémon |
| **Descripción** | El sistema permite al usuario eliminar un equipo Pokémon existente de su lista de equipos. |
| **Cómo se ejecutará** | El usuario accede a "Mis Equipos", selecciona el equipo que desea eliminar, hace clic en "Eliminar" y confirma la acción. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. El usuario debe tener al menos un equipo creado. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| ID del equipo | Identificador del equipo a eliminar | Número entero | Debe corresponder a un equipo existente del usuario | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Confirmación | Indicación de que el equipo fue eliminado exitosamente | Texto | Mostrado tras confirmar la eliminación | Sí |
| Lista actualizada | Lista de equipos del usuario sin el equipo eliminado | Lista de objetos | Se actualiza en tiempo real | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede a "Mis Equipos" y selecciona un equipo | — |
| 2 | Usuario | Hace clic en "Eliminar equipo" | — |
| 3 | Sistema | Muestra un diálogo de confirmación | — |
| 4 | Usuario | Confirma la eliminación | El usuario cancela → no se elimina el equipo |
| 5 | Sistema | Elimina el equipo y actualiza la lista | Error de eliminación → muestra mensaje de error |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Cancela el diálogo de confirmación | — |
| 2 | Sistema | Cierra el diálogo y mantiene el equipo intacto | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | La eliminación es permanente e irreversible. Se debe mostrar siempre un diálogo de confirmación antes de proceder. |

---

### RF-27 — Visualizar equipos Pokémon creados

| Campo | Detalle |
|---|---|
| **Código** | RF-27 |
| **Nombre** | Visualizar equipos Pokémon creados |
| **Descripción** | El sistema permite al usuario ver todos los equipos Pokémon que ha creado, con el nombre del equipo y los Pokémon que lo conforman. |
| **Cómo se ejecutará** | El usuario accede a la sección "Mis Equipos" desde el menú y el sistema muestra la lista de todos sus equipos con información resumida de cada uno. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| ID del usuario | Identificador del usuario cuya lista de equipos se consulta | Número entero | Obtenido automáticamente desde la sesión activa | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Lista de equipos | Equipos creados por el usuario con nombre e imágenes de los Pokémon | Lista de objetos | Ordenados por fecha de creación por defecto | Sí |
| Mensaje lista vacía | Indicación de que el usuario no tiene equipos creados | Texto | Se muestra solo cuando no hay equipos | Condicional |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede a "Mis Equipos" desde el menú | — |
| 2 | Sistema | Recupera todos los equipos del usuario | Error de conexión → muestra mensaje de error |
| 3 | Sistema | Muestra la lista de equipos con nombre y Pokémon de cada uno | Sin equipos → muestra "No tienes equipos creados aún" |
| 4 | Usuario | Selecciona un equipo para ver su detalle o análisis competitivo | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Desde la lista accede a crear un nuevo equipo | — |
| 2 | Sistema | Redirige al flujo de creación de equipo (RF-24) | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | Desde esta vista el usuario debe poder acceder a editar, eliminar o ver el análisis competitivo de cada equipo. |

---

### RF-28 — Visualizar análisis competitivo del equipo

| Campo | Detalle |
|---|---|
| **Código** | RF-28 |
| **Nombre** | Visualizar análisis competitivo del equipo |
| **Descripción** | El sistema muestra un análisis competitivo del equipo Pokémon seleccionado, incluyendo coberturas de tipo, debilidades, fortalezas, estadísticas promedio del equipo y posibles sinergias. |
| **Cómo se ejecutará** | El usuario selecciona un equipo desde "Mis Equipos" y accede a la vista de análisis competitivo donde el sistema calcula y presenta la información. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. El usuario debe tener al menos un equipo creado con al menos un Pokémon. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| ID del equipo | Identificador del equipo a analizar | Número entero | Debe corresponder a un equipo existente del usuario | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Coberturas de tipo | Tipos cubiertos ofensiva y defensivamente por el equipo | Tabla / Gráfico | Basado en los tipos de los Pokémon del equipo | Sí |
| Debilidades del equipo | Tipos a los que el equipo es vulnerable | Lista | Calculado según los tipos de todos los Pokémon | Sí |
| Estadísticas promedio | Promedio de cada stat base del equipo | Gráfico de barras | Calculado con los stats de los 6 Pokémon | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Selecciona un equipo y accede a "Ver análisis competitivo" | — |
| 2 | Sistema | Recupera los datos de todos los Pokémon del equipo | Error de conexión → muestra mensaje de error |
| 3 | Sistema | Calcula coberturas, debilidades y estadísticas promedio | — |
| 4 | Sistema | Presenta el análisis en pantalla con gráficos y tablas | — |
| 5 | Usuario | Navega por las secciones del análisis | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | El equipo tiene menos de 6 Pokémon | — |
| 2 | Sistema | Realiza el análisis con los Pokémon disponibles e indica que el equipo está incompleto | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | El análisis competitivo debe actualizarse automáticamente cuando el equipo es editado. Es la funcionalidad diferenciadora del módulo de equipos. |

---

### RF-29 — Consultar estadísticas de uso de Pokémon

| Campo | Detalle |
|---|---|
| **Código** | RF-29 |
| **Nombre** | Consultar estadísticas de uso de Pokémon |
| **Descripción** | El sistema permite a usuarios y administradores consultar estadísticas generales de uso de la Pokédex, como los Pokémon más consultados, más usados en equipos y tendencias generales. |
| **Cómo se ejecutará** | El usuario accede a la sección "Estadísticas" desde el menú y el sistema presenta los datos de uso en gráficos y tablas. |
| **Actor principal** | Usuario autenticado / Administrador |
| **Precondiciones** | El usuario debe haber iniciado sesión. Deben existir datos de uso registrados en el sistema. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Período de tiempo | Rango de fechas para filtrar las estadísticas | Selector de fecha | Por defecto muestra el último mes | No |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Pokémon más consultados | Ranking de Pokémon con más visitas en su detalle | Tabla / Gráfico | Top 10 por defecto | Sí |
| Pokémon más usados en equipos | Ranking de Pokémon más agregados a equipos | Tabla / Gráfico | Top 10 por defecto | Sí |
| Total de consultas | Número total de consultas realizadas en el período | Número | Calculado sobre el período seleccionado | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede a la sección "Estadísticas" desde el menú | — |
| 2 | Sistema | Recupera los datos de uso del período por defecto | Error de conexión → muestra mensaje de error |
| 3 | Sistema | Presenta las estadísticas en gráficos y tablas | Sin datos → muestra "No hay estadísticas disponibles aún" |
| 4 | Usuario | Puede cambiar el período de tiempo para filtrar los datos | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Selecciona un período de tiempo personalizado | — |
| 2 | Sistema | Recalcula y actualiza las estadísticas para ese período | Sin datos en ese período → muestra aviso |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | Las estadísticas deben ser de solo lectura para usuarios normales. Los administradores pueden tener acceso a estadísticas más detalladas. |

---

## Diagrama de Casos de Uso

| Campo | Detalle |
|---|---|
| **Código** | RF-30 |
| **Nombre** | Consultar Pokémon más utilizados en equipos |
| **Descripción** | El sistema muestra un ranking de los Pokémon que aparecen con mayor frecuencia en los equipos creados por todos los usuarios de la plataforma. |
| **Cómo se ejecutará** | Desde la sección de estadísticas el usuario accede al ranking de uso en equipos y el sistema presenta los datos ordenados por frecuencia de aparición. |
| **Actor principal** | Usuario autenticado / Administrador |
| **Precondiciones** | El usuario debe haber iniciado sesión. Deben existir equipos creados en el sistema. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Período de tiempo | Rango de fechas para filtrar el ranking | Selector de fecha | Por defecto muestra el último mes | No |
| Cantidad de resultados | Número de Pokémon a mostrar en el ranking | Número | Por defecto Top 10, máximo Top 50 | No |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Ranking de uso | Lista de Pokémon ordenados por frecuencia de aparición en equipos | Tabla / Gráfico | Incluye nombre, imagen, número de veces usado y porcentaje | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede a la sección "Estadísticas" y selecciona "Uso en equipos" | — |
| 2 | Sistema | Recupera y calcula la frecuencia de uso de cada Pokémon en equipos | Error de conexión → muestra mensaje de error |
| 3 | Sistema | Presenta el ranking ordenado de mayor a menor uso | Sin datos → muestra "No hay datos de equipos disponibles aún" |
| 4 | Usuario | Puede seleccionar un Pokémon del ranking para ver su detalle | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Filtra el ranking por un período de tiempo específico | — |
| 2 | Sistema | Recalcula el ranking para ese período y actualiza la vista | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | Este ranking refleja las preferencias competitivas de todos los usuarios. Es útil para identificar los Pokémon más populares en la comunidad de la plataforma. |

---

### RF-31 — Consultar Pokémon más consultados

| Campo | Detalle |
|---|---|
| **Código** | RF-31 |
| **Nombre** | Consultar Pokémon más consultados |
| **Descripción** | El sistema muestra un ranking de los Pokémon cuya ficha de detalle ha sido visitada con mayor frecuencia por los usuarios de la plataforma. |
| **Cómo se ejecutará** | Desde la sección de estadísticas el usuario accede al ranking de consultas y el sistema presenta los Pokémon ordenados por número de visitas a su detalle. |
| **Actor principal** | Usuario autenticado / Administrador |
| **Precondiciones** | El usuario debe haber iniciado sesión. Deben existir registros de consultas en el sistema. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Período de tiempo | Rango de fechas para filtrar el ranking | Selector de fecha | Por defecto muestra el último mes | No |
| Cantidad de resultados | Número de Pokémon a mostrar en el ranking | Número | Por defecto Top 10, máximo Top 50 | No |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Ranking de consultas | Lista de Pokémon ordenados por número de visitas a su detalle | Tabla / Gráfico | Incluye nombre, imagen, número de consultas y porcentaje | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede a "Estadísticas" y selecciona "Pokémon más consultados" | — |
| 2 | Sistema | Recupera el conteo de visitas por Pokémon del período seleccionado | Error de conexión → muestra mensaje de error |
| 3 | Sistema | Presenta el ranking ordenado de mayor a menor consultas | Sin datos → muestra "No hay datos de consultas disponibles aún" |
| 4 | Usuario | Puede seleccionar un Pokémon del ranking para ver su detalle | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Filtra el ranking por un período de tiempo específico | — |
| 2 | Sistema | Recalcula el ranking para ese período y actualiza la vista | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | Cada vez que un usuario accede al detalle de un Pokémon el sistema debe registrar esa consulta. El conteo debe ser global, no por usuario. |

---

### RF-32 — Consultar tasa de elección de Pokémon

| Campo | Detalle |
|---|---|
| **Código** | RF-32 |
| **Nombre** | Consultar tasa de elección de Pokémon |
| **Descripción** | El sistema muestra el porcentaje de veces que cada Pokémon ha sido seleccionado para formar parte de un equipo respecto al total de Pokémon usados en equipos. |
| **Cómo se ejecutará** | Desde la sección de estadísticas el usuario accede a la tasa de elección y el sistema calcula y presenta el porcentaje de uso de cada Pokémon. |
| **Actor principal** | Usuario autenticado / Administrador |
| **Precondiciones** | El usuario debe haber iniciado sesión. Deben existir equipos creados en el sistema. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Período de tiempo | Rango de fechas para filtrar la tasa | Selector de fecha | Por defecto muestra el último mes | No |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Tasa de elección | Porcentaje de uso de cada Pokémon en equipos sobre el total | Tabla / Gráfico | Expresado en porcentaje con dos decimales | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede a "Estadísticas" y selecciona "Tasa de elección" | — |
| 2 | Sistema | Calcula la frecuencia relativa de cada Pokémon en equipos | Error de conexión → muestra mensaje de error |
| 3 | Sistema | Presenta la tasa de elección en gráfico y tabla | Sin datos → muestra "No hay datos suficientes aún" |
| 4 | Usuario | Puede filtrar por período o tipo de Pokémon | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Filtra la tasa por un período específico | — |
| 2 | Sistema | Recalcula y actualiza los porcentajes para ese período | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | La tasa de elección se calcula como: (veces que aparece el Pokémon en equipos / total de slots de equipo usados) × 100. |

---

### RF-33 — Administrar información de Pokémon (Administrador)

| Campo | Detalle |
|---|---|
| **Código** | RF-33 |
| **Nombre** | Administrar información de Pokémon |
| **Descripción** | El sistema proporciona al administrador un panel de gestión desde el cual puede crear, actualizar y eliminar Pokémon del sistema. |
| **Cómo se ejecutará** | El administrador accede al panel de administración desde el menú y puede ver el listado completo de Pokémon con opciones para crear, editar o eliminar cada uno. |
| **Actor principal** | Administrador |
| **Precondiciones** | El usuario debe haber iniciado sesión con rol de administrador. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Credenciales de administrador | Sesión activa con rol administrador | Token de sesión | Verificado automáticamente por el sistema | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Panel de administración | Vista con listado de Pokémon y opciones de gestión | Pantalla | Solo accesible para usuarios con rol administrador | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Administrador | Accede al panel de administración desde el menú | — |
| 2 | Sistema | Verifica que el usuario tenga rol de administrador | Sin permisos → redirige a pantalla de acceso denegado |
| 3 | Sistema | Muestra el panel con el listado de Pokémon y opciones de gestión | Error de carga → muestra mensaje de error |
| 4 | Administrador | Selecciona la acción deseada (crear, editar o eliminar) | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario normal | Intenta acceder al panel de administración | — |
| 2 | Sistema | Detecta que el usuario no tiene rol de administrador | — |
| 3 | Sistema | Muestra pantalla de acceso denegado | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | El panel de administración es exclusivo para usuarios con rol administrador. Cualquier intento de acceso sin permisos debe ser bloqueado y registrado. |

---

### RF-34 — Crear nuevos Pokémon (Administrador)

| Campo | Detalle |
|---|---|
| **Código** | RF-34 |
| **Nombre** | Crear nuevos Pokémon |
| **Descripción** | El sistema permite al administrador registrar un nuevo Pokémon en la Pokédex con toda su información asociada. |
| **Cómo se ejecutará** | El administrador accede al panel de administración, selecciona "Crear Pokémon", completa el formulario con la información del nuevo Pokémon y guarda los cambios. |
| **Actor principal** | Administrador |
| **Precondiciones** | El usuario debe haber iniciado sesión con rol de administrador. El número de Pokédex del nuevo Pokémon no debe existir previamente. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Número de Pokédex | Número oficial del nuevo Pokémon | Número entero | Debe ser único, mayor a 0 | Sí |
| Nombre | Nombre del Pokémon | Texto | Máximo 50 caracteres, debe ser único | Sí |
| Tipo primario | Tipo principal del Pokémon | Selector | Debe ser uno de los 18 tipos válidos | Sí |
| Tipo secundario | Segundo tipo del Pokémon | Selector | Debe ser uno de los 18 tipos válidos, diferente al primario | No |
| Estadísticas base | HP, Ataque, Defensa, Atq. Esp., Def. Esp., Velocidad | Números enteros | Cada stat entre 1 y 255 | Sí |
| Imagen | Imagen oficial del Pokémon | Imagen | Formato PNG/JPG, máximo 2MB | Sí |
| Descripción | Texto descriptivo del Pokémon | Texto | Máximo 500 caracteres | No |
| Generación | Generación a la que pertenece | Selector | Entre I y IX | Sí |
| Región | Región de origen del Pokémon | Selector | Debe ser una región válida del sistema | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Pokémon creado | Nuevo Pokémon disponible en el sistema | Objeto Pokémon | Visible de inmediato en el listado general | Sí |
| Mensaje de confirmación | Notificación de creación exitosa | Texto | Mostrado tras guardar | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Administrador | Accede al panel y selecciona "Crear Pokémon" | — |
| 2 | Administrador | Completa el formulario con los datos del nuevo Pokémon | — |
| 3 | Administrador | Selecciona "Guardar" | — |
| 4 | Sistema | Valida que el número y nombre no existan previamente | Duplicado → muestra error de validación |
| 5 | Sistema | Registra el nuevo Pokémon en la base de datos | Error de guardado → muestra mensaje de error |
| 6 | Sistema | Confirma la creación y muestra el nuevo Pokémon | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Administrador | Cancela la creación | — |
| 2 | Sistema | Descarta los datos y regresa al panel | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | El nuevo Pokémon debe quedar disponible de inmediato para todos los usuarios. Se recomienda validar los datos antes de enviar el formulario. |

---

## Diagrama de Casos de Uso

| Campo | Detalle |
|---|---|
| **Código** | RF-35 |
| **Nombre** | Actualizar información de Pokémon |
| **Descripción** | El sistema permite al administrador modificar la información de un Pokémon existente en la Pokédex. |
| **Cómo se ejecutará** | El administrador accede al panel de administración, busca el Pokémon a editar, selecciona "Editar", modifica los campos necesarios y guarda los cambios. |
| **Actor principal** | Administrador |
| **Precondiciones** | El usuario debe haber iniciado sesión con rol de administrador. El Pokémon a editar debe existir en el sistema. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| ID del Pokémon | Identificador del Pokémon a editar | Número entero | Debe corresponder a un Pokémon existente | Sí |
| Campos a modificar | Cualquier campo de la ficha del Pokémon | Varios | Mismas reglas que en la creación | No |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Pokémon actualizado | Pokémon con la información modificada | Objeto Pokémon | Los cambios se reflejan de inmediato en el sistema | Sí |
| Mensaje de confirmación | Notificación de actualización exitosa | Texto | Mostrado tras guardar los cambios | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Administrador | Busca el Pokémon en el panel y selecciona "Editar" | — |
| 2 | Sistema | Carga el formulario con la información actual del Pokémon | Error de carga → muestra mensaje de error |
| 3 | Administrador | Modifica los campos necesarios y selecciona "Guardar" | — |
| 4 | Sistema | Valida los datos modificados | Datos inválidos → muestra errores de validación |
| 5 | Sistema | Actualiza la información en la base de datos | Error de guardado → muestra mensaje de error |
| 6 | Sistema | Confirma la actualización | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Administrador | Cancela la edición sin guardar | — |
| 2 | Sistema | Descarta los cambios y mantiene la información original | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | Los cambios se reflejan de inmediato para todos los usuarios. Se recomienda registrar un historial de cambios para auditoría. |

---

### RF-36 — Eliminar Pokémon (Administrador)

| Campo | Detalle |
|---|---|
| **Código** | RF-36 |
| **Nombre** | Eliminar Pokémon |
| **Descripción** | El sistema permite al administrador eliminar un Pokémon existente de la Pokédex de forma permanente. |
| **Cómo se ejecutará** | El administrador accede al panel de administración, busca el Pokémon a eliminar, selecciona "Eliminar" y confirma la acción en el diálogo de confirmación. |
| **Actor principal** | Administrador |
| **Precondiciones** | El usuario debe haber iniciado sesión con rol de administrador. El Pokémon a eliminar debe existir en el sistema. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| ID del Pokémon | Identificador del Pokémon a eliminar | Número entero | Debe corresponder a un Pokémon existente | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Confirmación | Indicación de que el Pokémon fue eliminado exitosamente | Texto | Mostrado tras confirmar la eliminación | Sí |
| Lista actualizada | Listado de Pokémon sin el Pokémon eliminado | Lista de objetos | Se actualiza de inmediato en el sistema | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Administrador | Busca el Pokémon en el panel y selecciona "Eliminar" | — |
| 2 | Sistema | Muestra diálogo de confirmación con advertencia de acción irreversible | — |
| 3 | Administrador | Confirma la eliminación | El administrador cancela → no se elimina el Pokémon |
| 4 | Sistema | Elimina el Pokémon de la base de datos | Error de eliminación → muestra mensaje de error |
| 5 | Sistema | Confirma la eliminación y actualiza el listado | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Administrador | Cancela el diálogo de confirmación | — |
| 2 | Sistema | Cierra el diálogo y mantiene el Pokémon intacto | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | La eliminación es permanente. Si el Pokémon está en equipos o favoritos de usuarios, debe gestionarse la integridad referencial eliminándolo también de esas listas o notificando a los usuarios afectados. |

---

### RF-37 — Administrar usuarios (Administrador)

| Campo | Detalle |
|---|---|
| **Código** | RF-37 |
| **Nombre** | Administrar usuarios |
| **Descripción** | El sistema proporciona al administrador un panel de gestión de usuarios desde el cual puede consultar, modificar y desactivar cuentas de usuario. |
| **Cómo se ejecutará** | El administrador accede al panel de administración, selecciona la sección "Gestión de usuarios" y puede ver el listado completo con opciones de gestión sobre cada cuenta. |
| **Actor principal** | Administrador |
| **Precondiciones** | El usuario debe haber iniciado sesión con rol de administrador. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Credenciales de administrador | Sesión activa con rol administrador | Token de sesión | Verificado automáticamente por el sistema | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Panel de gestión de usuarios | Vista con listado de usuarios y opciones de gestión | Pantalla | Solo accesible para administradores | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Administrador | Accede al panel de administración y selecciona "Gestión de usuarios" | — |
| 2 | Sistema | Verifica que el usuario tenga rol de administrador | Sin permisos → redirige a pantalla de acceso denegado |
| 3 | Sistema | Muestra el listado de usuarios registrados con sus datos y estado | Error de carga → muestra mensaje de error |
| 4 | Administrador | Selecciona la acción deseada sobre un usuario | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario normal | Intenta acceder a la gestión de usuarios | — |
| 2 | Sistema | Detecta que el usuario no tiene permisos | — |
| 3 | Sistema | Muestra pantalla de acceso denegado | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | El administrador no puede eliminar su propia cuenta desde este panel. Las acciones sobre usuarios deben quedar registradas en un log de auditoría. |

---

### RF-38 — Consultar usuarios registrados (Administrador)

| Campo | Detalle |
|---|---|
| **Código** | RF-38 |
| **Nombre** | Consultar usuarios registrados |
| **Descripción** | El sistema permite al administrador ver el listado completo de usuarios registrados en la plataforma con su información básica y estado de cuenta. |
| **Cómo se ejecutará** | Desde el panel de gestión de usuarios el administrador visualiza la tabla con todos los usuarios, pudiendo buscarlos y filtrarlos. |
| **Actor principal** | Administrador |
| **Precondiciones** | El usuario debe haber iniciado sesión con rol de administrador. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Término de búsqueda | Nombre o correo para filtrar usuarios | Texto | Búsqueda parcial, no distingue mayúsculas | No |
| Estado del usuario | Filtro por estado de la cuenta | Selector | Valores: Todos / Activos / Bloqueados | No |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Lista de usuarios | Usuarios registrados con nombre, correo, fecha de registro y estado | Tabla | Paginada, ordenada por fecha de registro por defecto | Sí |
| Total de usuarios | Número total de usuarios registrados | Número | Mostrado como resumen en el panel | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Administrador | Accede a la sección "Gestión de usuarios" | — |
| 2 | Sistema | Recupera y muestra el listado completo de usuarios | Error de conexión → muestra mensaje de error |
| 3 | Administrador | Puede buscar o filtrar usuarios por nombre, correo o estado | — |
| 4 | Sistema | Actualiza el listado según el criterio aplicado | Sin resultados → muestra "No se encontraron usuarios" |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Administrador | Aplica filtro de usuarios bloqueados | — |
| 2 | Sistema | Muestra únicamente los usuarios con estado bloqueado | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | El listado debe mostrar claramente el estado de cada cuenta (activa o bloqueada). La información de correo es de solo lectura. |

---

### RF-39 — Modificar información de usuarios (Administrador)

| Campo | Detalle |
|---|---|
| **Código** | RF-39 |
| **Nombre** | Modificar información de usuarios |
| **Descripción** | El sistema permite al administrador editar ciertos datos del perfil de un usuario registrado, como su nombre de entrenador o su rol en el sistema. |
| **Cómo se ejecutará** | Desde el panel de gestión de usuarios el administrador selecciona un usuario, hace clic en "Editar", modifica los campos permitidos y guarda los cambios. |
| **Actor principal** | Administrador |
| **Precondiciones** | El usuario debe haber iniciado sesión con rol de administrador. El usuario a modificar debe estar registrado en el sistema. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| ID del usuario | Identificador del usuario a modificar | Número entero | Debe corresponder a un usuario existente | Sí |
| Nombre de entrenador | Nuevo nombre del entrenador | Texto | Máximo 30 caracteres | No |
| Rol | Nuevo rol asignado al usuario | Selector | Valores: Usuario normal / Administrador | No |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Usuario actualizado | Perfil del usuario con los cambios aplicados | Objeto usuario | Se refleja de inmediato en el sistema | Sí |
| Mensaje de confirmación | Notificación de actualización exitosa | Texto | Mostrado tras guardar los cambios | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Administrador | Selecciona un usuario del listado y hace clic en "Editar" | — |
| 2 | Sistema | Carga el formulario con la información actual del usuario | Error de carga → muestra mensaje de error |
| 3 | Administrador | Modifica los campos permitidos y selecciona "Guardar" | — |
| 4 | Sistema | Valida los datos ingresados | Datos inválidos → muestra errores de validación |
| 5 | Sistema | Actualiza la información del usuario | Error de guardado → muestra mensaje de error |
| 6 | Sistema | Confirma la actualización | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Administrador | Cancela la edición sin guardar | — |
| 2 | Sistema | Descarta los cambios y mantiene la información original | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | El correo Gmail no es editable por el administrador ya que es el identificador único del usuario. El cambio de rol debe aplicarse en la siguiente sesión del usuario afectado. |

---

## Diagrama de Casos de Uso

<p align="center">
  <img src="Images/Diagrama de casos de uso.png" alt="Diagrama de Casos de Uso NikoDex" width="900"/>
</p>

---

### RF-06 — Filtrado y ordenamiento de Pokémon

| Campo | Detalle |
|---|---|
| **Código** | RF-06 |
| **Nombre** | Filtrado y ordenamiento de Pokémon |
| **Descripción** | El sistema deberá permitir filtrar y ordenar los Pokémon por diferentes criterios, como región, generación, tipo, evolución, habilidades, ataques y estadísticas. |
| **Cómo se ejecutará** | El usuario accede al panel de filtros y ordenamiento, selecciona uno o más criterios y el sistema actualiza el listado mostrando únicamente los Pokémon que cumplan con los criterios seleccionados. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. Deben existir Pokémon registrados en el sistema. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Criterio de filtro | Criterio por el que se desea filtrar (región, generación, tipo, etc.) | Selector múltiple | Debe ser un criterio válido del sistema | No |
| Criterio de orden | Campo y dirección por el que se desea ordenar | Selector | Valores: nombre, número, generación, stats (asc/desc) | No |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Lista filtrada/ordenada | Pokémon que cumplen los criterios seleccionados | Lista de objetos | Incluye nombre, número, imagen y tipo(s) | Sí |
| Mensaje sin resultados | Indicación de que no hay Pokémon para los criterios aplicados | Texto | Se muestra solo cuando no hay resultados | Condicional |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede al panel de filtros y selecciona uno o más criterios | — |
| 2 | Sistema | Aplica los criterios al listado de Pokémon | — |
| 3 | Sistema | Muestra el listado filtrado y/o ordenado | Sin resultados → muestra "No se encontraron Pokémon" |
| 4 | Usuario | Puede combinar múltiples filtros y ordenamientos | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Limpia todos los filtros aplicados | — |
| 2 | Sistema | Restablece el listado completo sin filtros | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | Los filtros pueden combinarse entre sí. Los criterios de filtrado incluyen: región, generación, tipo primario, tipo secundario, evolución, mega evolución, habilidades, ataques y estadísticas. |

**Reglas de Negocio**

| No. | Descripción |
|---|---|
| 1 | Los filtros son acumulativos y pueden combinarse entre sí. |
| 2 | Solo puede aplicarse un criterio de ordenamiento a la vez. |
| 3 | El ordenamiento por estadísticas permite seleccionar el stat específico. |

**Abreviaturas**

| Abreviatura | Significado |
|---|---|
| RF | Requerimiento Funcional |
| Stats | Estadísticas base del Pokémon |
| Asc | Ascendente |
| Desc | Descendente |

**Historial de Revisión**

| Elaborado por | Aprobado por | Fecha | Descripción y Justificación de Cambios |
|---|---|---|---|
| Nicolas David Prieto Ramos | — | 25/06/2026 | Versión inicial del documento. |

---

### RF-07 — Gestión de favoritos

| Campo | Detalle |
|---|---|
| **Código** | RF-07 |
| **Nombre** | Gestión de favoritos |
| **Descripción** | El sistema deberá permitir agregar, consultar y eliminar Pokémon de la lista de favoritos del usuario. |
| **Cómo se ejecutará** | El usuario hace clic en el ícono de favorito desde el listado o el detalle de un Pokémon para agregarlo o eliminarlo. Puede consultar su lista de favoritos desde la sección "Mis Favoritos". |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. El Pokémon debe existir en el sistema. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| ID del Pokémon | Identificador del Pokémon a agregar o eliminar de favoritos | Número entero | Debe corresponder a un Pokémon existente | Sí |
| ID del usuario | Identificador del usuario que realiza la acción | Número entero | Obtenido automáticamente desde la sesión activa | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Lista de favoritos | Pokémon marcados como favoritos por el usuario | Lista de objetos | Incluye nombre, número, imagen y tipo(s) | Sí |
| Confirmación | Indicación del resultado de la acción realizada | Texto / Ícono | El ícono cambia de estado según la acción | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Hace clic en el ícono de favorito de un Pokémon | — |
| 2 | Sistema | Verifica si el Pokémon ya está en favoritos | — |
| 3 | Sistema | Agrega o elimina el Pokémon de la lista según corresponda | Error → muestra mensaje de error |
| 4 | Sistema | Actualiza el ícono y confirma la acción en tiempo real | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede a "Mis Favoritos" desde el menú | — |
| 2 | Sistema | Muestra el listado de Pokémon favoritos del usuario | Lista vacía → muestra "No tienes Pokémon favoritos aún" |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | El ícono de favorito debe ser visible desde el listado y el detalle. Los cambios deben reflejarse en tiempo real sin recargar la página. |

**Reglas de Negocio**

| No. | Descripción |
|---|---|
| 1 | Un Pokémon no puede estar duplicado en la lista de favoritos del mismo usuario. |
| 2 | La lista de favoritos es personal e independiente por usuario. |
| 3 | No hay límite máximo de Pokémon en favoritos. |

**Abreviaturas**

| Abreviatura | Significado |
|---|---|
| RF | Requerimiento Funcional |

**Historial de Revisión**

| Elaborado por | Aprobado por | Fecha | Descripción y Justificación de Cambios |
|---|---|---|---|
| Nicolas David Prieto Ramos | — | 25/06/2026 | Versión inicial del documento. |

---

### RF-08 — Gestión de equipos Pokémon

| Campo | Detalle |
|---|---|
| **Código** | RF-08 |
| **Nombre** | Gestión de equipos Pokémon |
| **Descripción** | El sistema deberá permitir crear, editar, eliminar y visualizar equipos Pokémon personalizados. |
| **Cómo se ejecutará** | El usuario accede a "Mis Equipos" y puede crear un nuevo equipo asignándole un nombre y seleccionando hasta 6 Pokémon, así como editar o eliminar equipos existentes. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. Deben existir Pokémon registrados en el sistema. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Nombre del equipo | Nombre identificador del equipo | Texto | Máximo 30 caracteres, no puede estar vacío | Sí |
| Pokémon seleccionados | Lista de Pokémon que conforman el equipo | Lista de IDs | Mínimo 1, máximo 6 Pokémon por equipo | Sí |
| ID del equipo | Identificador del equipo a editar o eliminar | Número entero | Debe corresponder a un equipo existente del usuario | Condicional |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Lista de equipos | Equipos creados por el usuario con nombre e imágenes de los Pokémon | Lista de objetos | Ordenados por fecha de creación | Sí |
| Confirmación | Notificación del resultado de la acción | Texto | Mostrado tras crear, editar o eliminar | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede a "Mis Equipos" y selecciona una acción (crear/editar/eliminar) | — |
| 2 | Usuario | Completa o modifica los datos del equipo | Nombre vacío → muestra error de validación |
| 3 | Sistema | Valida y ejecuta la acción solicitada | Error → muestra mensaje de error |
| 4 | Sistema | Confirma la acción y actualiza la lista de equipos | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Intenta agregar más de 6 Pokémon a un equipo | — |
| 2 | Sistema | Muestra aviso indicando que el límite es de 6 Pokémon | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | Un usuario puede tener múltiples equipos. La eliminación de un equipo es permanente y requiere confirmación previa. |

**Reglas de Negocio**

| No. | Descripción |
|---|---|
| 1 | Cada equipo puede tener entre 1 y 6 Pokémon. |
| 2 | Un usuario puede tener múltiples equipos sin límite definido. |
| 3 | La eliminación de equipos es permanente e irreversible. |

**Abreviaturas**

| Abreviatura | Significado |
|---|---|
| RF | Requerimiento Funcional |
| ID | Identificador único |

**Historial de Revisión**

| Elaborado por | Aprobado por | Fecha | Descripción y Justificación de Cambios |
|---|---|---|---|
| Nicolas David Prieto Ramos | — | 25/06/2026 | Versión inicial del documento. |

---

### RF-09 — Análisis competitivo

| Campo | Detalle |
|---|---|
| **Código** | RF-09 |
| **Nombre** | Análisis competitivo |
| **Descripción** | El sistema deberá mostrar un análisis básico de los equipos Pokémon, incluyendo fortalezas, debilidades y balance general. |
| **Cómo se ejecutará** | El usuario selecciona un equipo desde "Mis Equipos" y accede a la vista de análisis competitivo donde el sistema calcula y presenta coberturas de tipo, debilidades y estadísticas promedio. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión y tener al menos un equipo creado con al menos un Pokémon. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| ID del equipo | Identificador del equipo a analizar | Número entero | Debe corresponder a un equipo existente del usuario | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Coberturas de tipo | Tipos cubiertos ofensiva y defensivamente por el equipo | Tabla / Gráfico | Basado en los tipos de los Pokémon del equipo | Sí |
| Debilidades del equipo | Tipos a los que el equipo es vulnerable | Lista | Calculado según los tipos de todos los Pokémon | Sí |
| Estadísticas promedio | Promedio de cada stat base del equipo | Gráfico de barras | Calculado con los stats de los Pokémon del equipo | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Selecciona un equipo y accede a "Ver análisis competitivo" | — |
| 2 | Sistema | Recupera los datos de todos los Pokémon del equipo | Error de conexión → muestra mensaje de error |
| 3 | Sistema | Calcula coberturas, debilidades y estadísticas promedio | — |
| 4 | Sistema | Presenta el análisis con gráficos y tablas | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | El equipo tiene menos de 6 Pokémon | — |
| 2 | Sistema | Realiza el análisis con los Pokémon disponibles e indica que el equipo está incompleto | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | El análisis debe actualizarse automáticamente cuando el equipo es editado. |

**Reglas de Negocio**

| No. | Descripción |
|---|---|
| 1 | El análisis se calcula automáticamente al acceder a la vista del equipo. |
| 2 | Un equipo con menos de 6 Pokémon puede analizarse, pero se indica que está incompleto. |
| 3 | Las coberturas de tipo se calculan considerando todos los tipos del equipo. |

**Abreviaturas**

| Abreviatura | Significado |
|---|---|
| RF | Requerimiento Funcional |
| Stats | Estadísticas base del Pokémon |

**Historial de Revisión**

| Elaborado por | Aprobado por | Fecha | Descripción y Justificación de Cambios |
|---|---|---|---|
| Nicolas David Prieto Ramos | — | 25/06/2026 | Versión inicial del documento. |

---

### RF-10 — Estadísticas

| Campo | Detalle |
|---|---|
| **Código** | RF-10 |
| **Nombre** | Estadísticas |
| **Descripción** | El sistema deberá mostrar estadísticas relacionadas con el uso de la plataforma, como Pokémon más consultados, más utilizados y frecuencia de uso en equipos. |
| **Cómo se ejecutará** | El usuario accede a la sección "Estadísticas" desde el menú y el sistema presenta los datos de uso en gráficos y tablas con posibilidad de filtrar por período de tiempo. |
| **Actor principal** | Usuario autenticado / Administrador |
| **Precondiciones** | El usuario debe haber iniciado sesión. Deben existir datos de uso registrados en el sistema. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Período de tiempo | Rango de fechas para filtrar las estadísticas | Selector de fecha | Por defecto muestra el último mes | No |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Pokémon más consultados | Ranking de Pokémon con más visitas en su detalle | Tabla / Gráfico | Top 10 por defecto | Sí |
| Pokémon más usados en equipos | Ranking de Pokémon más agregados a equipos | Tabla / Gráfico | Top 10 por defecto | Sí |
| Tasa de elección | Porcentaje de uso de cada Pokémon en equipos | Tabla / Gráfico | Expresado en porcentaje con dos decimales | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede a la sección "Estadísticas" desde el menú | — |
| 2 | Sistema | Recupera los datos de uso del período por defecto | Error de conexión → muestra mensaje de error |
| 3 | Sistema | Presenta las estadísticas en gráficos y tablas | Sin datos → muestra "No hay estadísticas disponibles aún" |
| 4 | Usuario | Puede cambiar el período de tiempo para filtrar los datos | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Selecciona un período de tiempo personalizado | — |
| 2 | Sistema | Recalcula y actualiza las estadísticas para ese período | Sin datos → muestra aviso |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | Las estadísticas son de solo lectura para usuarios normales. Los administradores tienen acceso a datos más detallados. La tasa de elección se calcula como: (veces en equipos / total de slots usados) × 100. |

**Reglas de Negocio**

| No. | Descripción |
|---|---|
| 1 | Cada consulta al detalle de un Pokémon se registra automáticamente. |
| 2 | Las estadísticas son globales, no por usuario individual. |
| 3 | Los usuarios normales solo tienen acceso de lectura a las estadísticas. |

**Abreviaturas**

| Abreviatura | Significado |
|---|---|
| RF | Requerimiento Funcional |

**Historial de Revisión**

| Elaborado por | Aprobado por | Fecha | Descripción y Justificación de Cambios |
|---|---|---|---|
| Nicolas David Prieto Ramos | — | 25/06/2026 | Versión inicial del documento. |

---

### RF-11 — Administración de Pokémon

| Campo | Detalle |
|---|---|
| **Código** | RF-11 |
| **Nombre** | Administración de Pokémon |
| **Descripción** | El sistema deberá permitir al administrador crear, consultar, modificar y eliminar la información de los Pokémon. |
| **Cómo se ejecutará** | El administrador accede al panel de administración y puede ver el listado completo de Pokémon con opciones para crear nuevos, editar la información existente o eliminar registros. |
| **Actor principal** | Administrador |
| **Precondiciones** | El usuario debe haber iniciado sesión con rol de administrador. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Número de Pokédex | Número oficial del Pokémon | Número entero | Debe ser único y mayor a 0 | Sí |
| Nombre | Nombre del Pokémon | Texto | Máximo 50 caracteres, debe ser único | Sí |
| Tipo primario | Tipo principal del Pokémon | Selector | Debe ser uno de los 18 tipos válidos | Sí |
| Tipo secundario | Segundo tipo del Pokémon | Selector | Opcional, diferente al tipo primario | No |
| Estadísticas base | HP, Ataque, Defensa, Atq. Esp., Def. Esp., Velocidad | Números enteros | Cada stat entre 1 y 255 | Sí |
| Imagen | Imagen del Pokémon | Imagen | Formato PNG/JPG, máximo 2MB | Sí |
| Descripción | Texto descriptivo del Pokémon | Texto | Máximo 500 caracteres | No |
| Generación | Generación a la que pertenece | Selector | Entre I y IX | Sí |
| Región | Región de origen | Selector | Debe ser una región válida del sistema | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Pokémon gestionado | Resultado de la operación CRUD realizada | Objeto Pokémon | Cambios visibles de inmediato en el sistema | Sí |
| Mensaje de confirmación | Notificación del resultado de la acción | Texto | Mostrado tras cada operación | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Administrador | Accede al panel de administración | Sin permisos → redirige a pantalla de acceso denegado |
| 2 | Administrador | Selecciona la acción a realizar (crear/editar/eliminar) | — |
| 3 | Administrador | Completa el formulario o confirma la acción | Datos inválidos → muestra errores de validación |
| 4 | Sistema | Valida y ejecuta la operación | Error → muestra mensaje de error |
| 5 | Sistema | Confirma la operación y actualiza el listado | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario normal | Intenta acceder al panel de administración | — |
| 2 | Sistema | Detecta que no tiene permisos y muestra pantalla de acceso denegado | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | La eliminación de un Pokémon es permanente. Si el Pokémon está en equipos o favoritos de usuarios, debe gestionarse la integridad referencial. |

**Reglas de Negocio**

| No. | Descripción |
|---|---|
| 1 | Solo los administradores pueden crear, modificar o eliminar Pokémon. |
| 2 | No pueden existir dos Pokémon con el mismo número de Pokédex o nombre. |
| 3 | La eliminación de un Pokémon requiere confirmación previa. |

**Abreviaturas**

| Abreviatura | Significado |
|---|---|
| RF | Requerimiento Funcional |
| CRUD | Create, Read, Update, Delete |
| Stats | Estadísticas base del Pokémon |

**Historial de Revisión**

| Elaborado por | Aprobado por | Fecha | Descripción y Justificación de Cambios |
|---|---|---|---|
| Nicolas David Prieto Ramos | — | 25/06/2026 | Versión inicial del documento. |

---

### RF-12 — Administración de usuarios

| Campo | Detalle |
|---|---|
| **Código** | RF-12 |
| **Nombre** | Administración de usuarios |
| **Descripción** | El sistema deberá permitir al administrador consultar, modificar, bloquear y eliminar usuarios registrados. |
| **Cómo se ejecutará** | El administrador accede al panel de gestión de usuarios y puede ver el listado completo con opciones para editar información, cambiar roles, bloquear o eliminar cuentas. |
| **Actor principal** | Administrador |
| **Precondiciones** | El usuario debe haber iniciado sesión con rol de administrador. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| ID del usuario | Identificador del usuario a gestionar | Número entero | Debe corresponder a un usuario existente | Sí |
| Nombre de entrenador | Nuevo nombre del entrenador | Texto | Máximo 30 caracteres | No |
| Rol | Nuevo rol asignado al usuario | Selector | Valores: Usuario normal / Administrador | No |
| Motivo de bloqueo | Razón por la que se bloquea al usuario | Texto | Máximo 200 caracteres | No |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Lista de usuarios | Usuarios registrados con nombre, correo, estado y rol | Tabla | Paginada, ordenada por fecha de registro | Sí |
| Confirmación | Notificación del resultado de la acción | Texto | Mostrado tras cada operación | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Administrador | Accede al panel de gestión de usuarios | Sin permisos → redirige a pantalla de acceso denegado |
| 2 | Sistema | Muestra el listado completo de usuarios | Error de carga → muestra mensaje de error |
| 3 | Administrador | Selecciona un usuario y elige la acción a realizar | — |
| 4 | Sistema | Valida y ejecuta la acción | Error → muestra mensaje de error |
| 5 | Sistema | Confirma la acción y actualiza el listado | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Administrador | Reactiva un usuario previamente bloqueado | — |
| 2 | Sistema | Cambia el estado del usuario a activo | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | El administrador no puede bloquearse ni eliminarse a sí mismo. Las acciones sobre usuarios quedan registradas en un log de auditoría. |

**Reglas de Negocio**

| No. | Descripción |
|---|---|
| 1 | El administrador no puede modificar su propio rol ni bloquearse. |
| 2 | Un usuario bloqueado no puede iniciar sesión en el sistema. |
| 3 | El correo electrónico de un usuario no puede ser modificado por el administrador. |

**Abreviaturas**

| Abreviatura | Significado |
|---|---|
| RF | Requerimiento Funcional |
| ID | Identificador único |

**Historial de Revisión**

| Elaborado por | Aprobado por | Fecha | Descripción y Justificación de Cambios |
|---|---|---|---|
| Nicolas David Prieto Ramos | — | 25/06/2026 | Versión inicial del documento. |

---

### RF-13 — Comparación de Pokémon

| Campo | Detalle |
|---|---|
| **Código** | RF-13 |
| **Nombre** | Comparación de Pokémon |
| **Descripción** | El sistema deberá permitir comparar dos Pokémon mostrando sus estadísticas, tipos, habilidades y características principales. |
| **Cómo se ejecutará** | El usuario accede a la sección "Comparar Pokémon", selecciona dos Pokémon y el sistema genera una vista comparativa lado a lado resaltando las diferencias. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. Deben existir al menos dos Pokémon registrados en el sistema. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Pokémon 1 | Primer Pokémon a comparar | Selector / Buscador | Debe corresponder a un Pokémon existente | Sí |
| Pokémon 2 | Segundo Pokémon a comparar | Selector / Buscador | Debe ser diferente al primero y existir en el sistema | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Vista comparativa | Comparación lado a lado de estadísticas, tipos, habilidades y características | Tabla / Gráfico | Se resalta el valor superior en cada categoría | Sí |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede a "Comparar Pokémon" y selecciona los dos Pokémon | — |
| 2 | Sistema | Recupera los datos de ambos Pokémon | Error de conexión → muestra mensaje de error |
| 3 | Sistema | Genera y presenta la vista comparativa | — |
| 4 | Usuario | Puede cambiar uno o ambos Pokémon para una nueva comparación | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Selecciona el mismo Pokémon dos veces | — |
| 2 | Sistema | Muestra aviso indicando que deben ser Pokémon diferentes | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | La comparación debe resaltar visualmente el Pokémon superior en cada estadística. Se puede acceder desde el detalle de un Pokémon. |

**Reglas de Negocio**

| No. | Descripción |
|---|---|
| 1 | Los dos Pokémon a comparar deben ser diferentes. |
| 2 | La comparación es de solo lectura, no modifica ningún dato. |
| 3 | El valor superior en cada estadística debe resaltarse visualmente. |

**Abreviaturas**

| Abreviatura | Significado |
|---|---|
| RF | Requerimiento Funcional |
| Stats | Estadísticas base del Pokémon |

**Historial de Revisión**

| Elaborado por | Aprobado por | Fecha | Descripción y Justificación de Cambios |
|---|---|---|---|
| Nicolas David Prieto Ramos | — | 25/06/2026 | Versión inicial del documento. |

---

### RF-14 — Evoluciones

| Campo | Detalle |
|---|---|
| **Código** | RF-14 |
| **Nombre** | Evoluciones |
| **Descripción** | El sistema deberá permitir visualizar la cadena evolutiva y las evoluciones disponibles de un Pokémon. |
| **Cómo se ejecutará** | Desde la ficha de detalle de un Pokémon, el sistema muestra automáticamente su cadena evolutiva completa con las condiciones necesarias para cada evolución. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. El Pokémon debe existir en el sistema con su cadena evolutiva registrada. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| ID del Pokémon | Identificador del Pokémon cuya cadena evolutiva se consulta | Número entero | Debe corresponder a un Pokémon existente | Sí |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Cadena evolutiva | Diagrama con todas las etapas evolutivas, imágenes y condiciones | Diagrama / Lista | Desde la forma base hasta la forma final | Sí |
| Mensaje sin evoluciones | Indicación de que el Pokémon no tiene evoluciones | Texto | Se muestra solo si no tiene cadena evolutiva | Condicional |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede al detalle de un Pokémon | — |
| 2 | Sistema | Recupera la cadena evolutiva completa del Pokémon | Error de conexión → muestra mensaje de error |
| 3 | Sistema | Presenta el diagrama con todas las etapas y condiciones | Sin cadena → muestra "Este Pokémon no tiene evoluciones" |
| 4 | Usuario | Hace clic en un Pokémon de la cadena para ver su detalle | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Sistema | El Pokémon tiene evoluciones ramificadas (ej: Eevee) | — |
| 2 | Sistema | Muestra todas las ramas posibles con sus condiciones | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | La cadena evolutiva debe ser navegable. Las condiciones de evolución incluyen: nivel, amistad, objeto, intercambio, hora del día, entre otras. |

**Reglas de Negocio**

| No. | Descripción |
|---|---|
| 1 | La cadena evolutiva se muestra siempre desde la forma base. |
| 2 | Cada eslabón de la cadena debe ser navegable al detalle del Pokémon. |
| 3 | Los Pokémon sin evolución muestran un mensaje indicándolo claramente. |

**Abreviaturas**

| Abreviatura | Significado |
|---|---|
| RF | Requerimiento Funcional |
| ID | Identificador único |

**Historial de Revisión**

| Elaborado por | Aprobado por | Fecha | Descripción y Justificación de Cambios |
|---|---|---|---|
| Nicolas David Prieto Ramos | — | 25/06/2026 | Versión inicial del documento. |

---

### RF-15 — Historial y recomendaciones

| Campo | Detalle |
|---|---|
| **Código** | RF-15 |
| **Nombre** | Historial y recomendaciones |
| **Descripción** | El sistema deberá permitir consultar el historial de equipos creados y mostrar Pokémon relacionados según sus tipos y características. |
| **Cómo se ejecutará** | El usuario accede a "Historial de equipos" desde "Mis Equipos" para ver todos los equipos creados incluyendo los eliminados. Desde el detalle de un Pokémon, el sistema muestra automáticamente Pokémon relacionados por tipo. |
| **Actor principal** | Usuario autenticado |
| **Precondiciones** | El usuario debe haber iniciado sesión. Debe existir al menos un equipo creado o un Pokémon consultado. |

**Datos de Entrada**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| ID del usuario | Identificador del usuario cuyo historial se consulta | Número entero | Obtenido automáticamente desde la sesión activa | Sí |
| ID del Pokémon | Identificador del Pokémon base para buscar relacionados | Número entero | Debe corresponder a un Pokémon existente | Condicional |
| Período de tiempo | Rango de fechas para filtrar el historial | Selector de fecha | Por defecto muestra todos los registros | No |

**Datos de Salida**

| Nombre | Descripción | Tipo de campo | Reglas / Aplicación | Obligatorio |
|---|---|---|---|---|
| Historial de equipos | Lista de equipos creados con nombre, Pokémon, fecha y estado | Tabla | Ordenado por fecha de creación descendente | Sí |
| Pokémon relacionados | Pokémon que comparten tipo con el Pokémon consultado | Lista de objetos | Máximo 10 Pokémon, excluye el Pokémon actual | Condicional |

**Flujo Básico**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede a "Mis Equipos" y selecciona "Ver historial" | — |
| 2 | Sistema | Recupera el historial completo de equipos del usuario | Error de conexión → muestra mensaje de error |
| 3 | Sistema | Presenta el historial con nombre, Pokémon, fechas y estado | Sin historial → muestra "No tienes historial de equipos aún" |
| 4 | Usuario | Accede al detalle de un Pokémon para ver relacionados | — |
| 5 | Sistema | Muestra la sección de Pokémon relacionados por tipo | Sin relacionados → oculta la sección |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Selecciona un equipo eliminado del historial | — |
| 2 | Sistema | Muestra los detalles del equipo en modo de solo lectura | — |

| Campo | Detalle |
|---|---|
| **Notas y comentarios** | El historial es de solo lectura. Los equipos eliminados aparecen con estado "Eliminado" pero no pueden editarse. Los Pokémon relacionados se muestran automáticamente en la ficha de detalle. |

**Reglas de Negocio**

| No. | Descripción |
|---|---|
| 1 | El historial incluye tanto equipos activos como eliminados. |
| 2 | Los equipos eliminados son de solo lectura en el historial. |
| 3 | Los Pokémon relacionados se muestran por similitud de tipo primario o secundario, con un máximo de 10. |

**Abreviaturas**

| Abreviatura | Significado |
|---|---|
| RF | Requerimiento Funcional |
| ID | Identificador único |

**Historial de Revisión**

| Elaborado por | Aprobado por | Fecha | Descripción y Justificación de Cambios |
|---|---|---|---|
| Nicolas David Prieto Ramos | — | 25/06/2026 | Versión inicial del documento. |
