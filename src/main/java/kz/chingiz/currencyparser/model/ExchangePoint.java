package kz.chingiz.currencyparser.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exchange_point")
public class ExchangePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private LocalTime time;

    private Double sellDollar;

    private Double purchaseDollar;

    private Double sellEuro;

    private Double purchaseEuro;

    private Double sellRuble;

    private Double purchaseRuble;

    private String contacts;
}
