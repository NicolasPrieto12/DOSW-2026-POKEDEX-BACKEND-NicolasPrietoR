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

## Diagrama de Casos de Uso

<p align="center">
  <img src="Images/Diagrama de casos de uso.png" alt="Diagrama de Casos de Uso NikoDex" width="900"/>
</p>

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
| **Notas y comentarios** | Los filtros pueden combinarse entre sí. Los criterios incluyen: región, generación, tipo primario, tipo secundario, evolución, mega evolución, habilidades, ataques y estadísticas. |

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
| **Cómo se ejecutará** | El usuario hace clic en el ícono de favorito desde el listado o el detalle de un Pokémon para agregarlo o eliminarlo. Puede consultar su lista desde la sección "Mis Favoritos". |
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
| 3 | Sistema | Agrega o elimina el Pokémon según corresponda | Error → muestra mensaje de error |
| 4 | Sistema | Actualiza el ícono y confirma la acción en tiempo real | — |

**Flujo Alterno**

| Paso | Actor | Descripción | Excepciones |
|---|---|---|---|
| 1 | Usuario | Accede a "Mis Favoritos" desde el menú | — |
| 2 | Sistema | Muestra el listado de favoritos del usuario | Lista vacía → muestra "No tienes Pokémon favoritos aún" |

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
| ID | Identificador único |

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
| 2 | Sistema | Realiza el análisis e indica que el equipo está incompleto | — |

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
| ID | Identificador único |

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
