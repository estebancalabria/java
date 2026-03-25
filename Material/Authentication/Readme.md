
### **1️⃣ HTTP Basic Authentication**

* Las credenciales (`usuario:contraseña`) se envían **en cada request** en el header `Authorization: Basic base64(usuario:contraseña)`.
* Es muy simple, pero **no es recomendable para producción** sin HTTPS, porque las credenciales viajan codificadas en base64, no cifradas.
* Ventaja: muy fácil de implementar y probar (como hicimos con `curl -u`).
* Limitación: el servidor debe validar la contraseña cada vez, no hay token temporal ni expiración.

---

### **2️⃣ JWT (JSON Web Token)**

* El usuario se autentica una vez y recibe un **token firmado**.
* El token se envía en el header `Authorization: Bearer <token>` en cada request.
* El servidor **no necesita almacenar sesión**, porque el token lleva la información de usuario y roles dentro de sí mismo.
* Ventaja: escalable, ideal para APIs y SPAs (Single Page Applications).
* Limitación: si se filtra el token, alguien más puede usarlo hasta que expire.

---

### **3️⃣ OAuth / OAuth2**

* Es un **framework de autorización y autenticación** más completo.
* Permite que una aplicación acceda a recursos de otra en nombre de un usuario (por ejemplo, “Iniciar sesión con Google”).
* Se usan **access tokens** y a veces **refresh tokens**.
* Ventaja: muy seguro, permite delegar la autenticación a terceros confiables.
* Limitación: más complejo de implementar que HTTP Basic o JWT.

---

En resumen, **sí**, esas son tres formas comunes de autenticación:

| Tipo       | Cómo funciona                                     | Cuándo usar                              |
| ---------- | ------------------------------------------------- | ---------------------------------------- |
| HTTP Basic | Credenciales enviadas en cada request             | Pruebas, APIs simples, laboratorio       |
| JWT        | Token firmado que contiene información de usuario | APIs modernas, SPAs, apps móviles        |
| OAuth2     | Token de acceso delegado a otra aplicación        | Integraciones con terceros, login social |

---

Si querés, puedo hacer un **mini-diagrama comparativo de HTTP Basic vs JWT vs OAuth2** para que quede muy claro visualmente cómo fluye cada uno. ¿Querés que lo haga?
