package com.core.transaction.controller;

import com.core.transaction.dto.NotificaticionDTO;
import com.core.transaction.dto.RequestKafkaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificaticionController {

    @Autowired
    private KafkaTemplate<String, RequestKafkaDTO> kafkaTemplate;

    @PostMapping("/send")
    public String sendMessage(@RequestBody NotificaticionDTO messageDTO) {
        try {
            RequestKafkaDTO requestKafkaDTO = RequestKafkaDTO.builder()
                    .serialId("12312") // Este es un ID de transaccion, puede ser cualquiera lo puedes cambiar
                    .opcion("CustomerNotificationGmailBO") // Este nombre no lo cambies, es el nombre del bean que envia el mensaje
                    .email("hola@gmail.com") // Correo destinatarios, lo puedes cambiar
                    .mensaje("Probando envio de correo electronico por kafka: "+messageDTO.getMessage()) // Mensaje lo cambios por el que quieras enviar
                    .build();
            kafkaTemplate.send("jsburgos", requestKafkaDTO); // Aqui cambias el nombre del topic
            return "Synchronous message sent successfully: " + messageDTO.getMessage();
        }catch (Exception e){
            return "Error sending synchronous message: " + e.getMessage();
        }
    }
}
