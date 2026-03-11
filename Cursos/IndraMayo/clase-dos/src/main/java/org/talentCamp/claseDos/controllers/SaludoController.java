package org.talentCamp.claseDos.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.Map;

@RestController
public class SaludoController {

    @GetMapping("/api/saludo/{nombre}")
    public String saludo(@PathVariable("nombre") String nombre) {
        //public String saludo(@PathVariable() string nombre){
        return "Hola " + nombre;
    }

    @GetMapping("/api/saludoRequestParam")
    public String saludoConQuery(@RequestParam("name") String nombre) {
        return String.format("Hola %s", nombre);
    }

    //Con Valor Por Defecto
    @GetMapping("/api/saludoDefault")
    public String saludoConQueryDefault(
            @RequestParam(value = "name", defaultValue = "Desconocido") String nombre) {
        return MessageFormat.format("Hola {0}", nombre);
    }

    //Opcional
    @GetMapping("/api/saludoOpc")
    public String saludoConQueryOpc(
            @RequestParam(value = "name", required = false) String nombre) {
        if (nombre==null){
            nombre = "[Nombre no proporcionado]";
        }
        return MessageFormat.format("Hola {0}", nombre);
    }

    @GetMapping("/api/saludoOptional")
    public String saludoOptional(@RequestParam() Optional<String> nombre ){
        return "Hola " + nombre.orElse("Desconocido (Optional)");
    }

    @GetMapping("/api/saludoMutiple")
    public String saludoMutiple(@RequestParam() List<String> nombres){
        StringBuilder sb = new StringBuilder();
        sb.append("<h1>Saludos a : </h1>");
        sb.append("<ul>");
        nombres.forEach( n -> {
            sb.append(MessageFormat.format("<li>{0}</li>", n));
        });
        sb.append("</ul>");

        return sb.toString();
    }

   @GetMapping("/api/query")
   public String queryAbierto(@RequestParam() Map<String, String> params)    {
        StringBuilder sb = new StringBuilder();
        sb.append("<h1> Los parametros que recibi por query son</h1>");
        for (String clave : params.keySet()){
            sb.append(MessageFormat.format("<div>{0}:{1}</div>", clave, params.get(clave)));
        }
        return sb.toString();
   }

}
