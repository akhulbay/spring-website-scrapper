package kz.chingiz.currencyparser.mapper;

import kz.chingiz.currencyparser.dto.ExchangePointReadDto;
import kz.chingiz.currencyparser.model.ExchangePoint;
import org.springframework.stereotype.Component;

@Component
public class ExchangePointReadMapper implements Mapper<ExchangePoint, ExchangePointReadDto>{

    @Override
    public ExchangePointReadDto map(ExchangePoint object) {
        return new ExchangePointReadDto(
                object.getId(),
                object.getName(),
                object.getAddress(),
                object.getTime(),
                object.getSellDollar(),
                object.getPurchaseDollar(),
                object.getSellEuro(),
                object.getPurchaseEuro(),
                object.getSellRuble(),
                object.getPurchaseRuble(),
                object.getContacts()
        );
    }
}
