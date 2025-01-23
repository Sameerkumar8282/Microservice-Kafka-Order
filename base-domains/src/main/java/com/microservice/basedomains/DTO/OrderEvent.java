package com.microservice.basedomains.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderEvent {
    private String message;
    private String status;
    private Order order;
}
