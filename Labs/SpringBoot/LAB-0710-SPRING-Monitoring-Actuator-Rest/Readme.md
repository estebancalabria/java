# 🟢 LAB-0710- Monitoring de una API Rest con Actuator

**Objetivo:** Crear un **endpoint REST real**, generar logs en tiempo real y verificar métricas y health checks con Spring Boot Actuator, simulando un **caso de uso práctico** de una aplicación que procesa pedidos.

---

## **Paso 0: Preparar proyecto**

* Partimos del proyecto `LAB-0700-SPRING-Monitoring-Actuator`.
* Confirmar que **Actuator** ya está configurado en `application.properties` y que el proyecto arranca correctamente.

---

## **Paso 1: Crear clase modelo de negocio**

Archivo: `src/main/java/com/miempresa/demoactuator/model/Order.java`

```java id="lab0800-1"
package com.miempresa.demoactuator.model;

public class Order {
    private String id;
    private String product;
    private int quantity;

    public Order() {}
    
    public Order(String id, String product, int quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public String getId() { return id; }
    public String getProduct() { return product; }
    public int getQuantity() { return quantity; }

    public void setId(String id) { this.id = id; }
    public void setProduct(String product) { this.product = product; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
```

---

## **Paso 2: Crear servicio de negocio**

Archivo: `src/main/java/com/miempresa/demoactuator/service/OrderService.java`

```java id="lab0800-2"
package com.miempresa.demoactuator.service;

import com.miempresa.demoactuator.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final List<Order> orders = new ArrayList<>();

    public Order createOrder(String product, int quantity) {
        String id = UUID.randomUUID().toString();
        Order order = new Order(id, product, quantity);
        orders.add(order);
        logger.info("Order created: {} ({} units)", product, quantity);
        return order;
    }

    public List<Order> getAllOrders() {
        logger.debug("Retrieving all orders. Total: {}", orders.size());
        return orders;
    }
}
```

---

## **Paso 3: Crear endpoint REST**

Archivo: `src/main/java/com/miempresa/demoactuator/controller/OrderController.java`

```java id="lab0800-3"
package com.miempresa.demoactuator.controller;

import com.miempresa.demoactuator.model.Order;
import com.miempresa.demoactuator.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order createOrder(@RequestParam String product, @RequestParam int quantity) {
        return orderService.createOrder(product, quantity);
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderService.getAllOrders();
    }
}
```

---

## **Paso 4: Probar endpoint**

1️⃣ Ejecutar la app:

```bash id="lab0800-4"
./mvnw spring-boot:run
```

2️⃣ Crear un pedido con curl o Postman:

```bash id="lab0800-5"
curl -X POST "http://localhost:8080/api/orders?product=Laptop&quantity=2"
```

3️⃣ Listar pedidos:

```bash id="lab0800-6"
curl "http://localhost:8080/api/orders"
```

> Observar logs en la consola (`INFO` y `DEBUG`) generados por las operaciones.

---

## **Paso 5: Verificar métricas**

* Acceder a métricas de Spring Boot Actuator:

```http id="lab0800-7"
http://localhost:8080/actuator/metrics/http.server.requests
```

* Observar que se incrementa el contador de requests por cada operación POST o GET.

---

## **Paso 6: Verificar health checks**

* Acceder a:

```http id="lab0800-8"
http://localhost:8080/actuator/health
```

* Confirmar que sigue mostrando `UP` y el custom health indicator (por ejemplo, base de datos simulada).

---

## **Paso 7: Logging en tiempo real**

* Endpoint `/actuator/loggers/com.miempresa.demoactuator.service.OrderService` permite cambiar el nivel de log en caliente.
* Ejemplo para subir nivel a `DEBUG` y ver detalles en consola:

```bash id="lab0800-9"
curl -X POST -H "Content-Type: application/json" \
    -d '{"configuredLevel":"DEBUG"}' \
    http://localhost:8080/actuator/loggers/com.miempresa.demoactuator.service.OrderService
```

* Volver a crear un pedido y observar logs detallados en tiempo real.

---

## **Paso 8: Buenas prácticas**

* Exponer solo endpoints necesarios en producción (`health, info, metrics, loggers`).
* Mantener logging estructurado si se integra con dashboards externos.
* Comprobar métricas y health regularmente para detectar problemas de performance.
* Este laboratorio simula un **caso real de e-commerce**, donde los pedidos se crean, registran y monitorean con Actuator.


¿Querés que haga eso?
