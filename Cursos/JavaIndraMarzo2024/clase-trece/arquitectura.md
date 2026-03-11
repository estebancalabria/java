```mermaid
graph LR
    client[Cliente] --> controller[Controlador REST]
    controller --> service[Servicio]
    service --> repository[Repositorio]
    repository --> database[Base de Datos]

    client[Cliente] --> saludoController[Controlador de Saludos]
    saludoController --> service

    controller --> modelValidator[Validador de Modelos]
    modelValidator --> model[Modelo de Negocio]

    service --> serviceError[Excepción de Servicio]

    subgraph Spring Boot
        controller
        service
        repository
        modelValidator
    end
    ```