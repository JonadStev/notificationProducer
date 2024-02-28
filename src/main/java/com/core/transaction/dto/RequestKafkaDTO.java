package com.core.transaction.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestKafkaDTO {
    private String serialId;
    private String opcion;
    private String email;
    private String mensaje;
}