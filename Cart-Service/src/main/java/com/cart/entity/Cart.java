package com.cart.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long cartId;
    private Long productId;
    private Long userId;
    private LocalDate dateAdded = LocalDate.now();
    private Long quantity;
    private Long productTotalPrice;
}
