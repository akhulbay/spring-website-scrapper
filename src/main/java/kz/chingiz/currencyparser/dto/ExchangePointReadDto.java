package kz.chingiz.currencyparser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangePointReadDto {

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
