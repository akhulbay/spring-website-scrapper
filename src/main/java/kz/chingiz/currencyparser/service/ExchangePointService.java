package kz.chingiz.currencyparser.service;

import kz.chingiz.currencyparser.dto.ExchangePointReadDto;
import kz.chingiz.currencyparser.model.ExchangePoint;

import java.util.List;

public interface ExchangePointService {

    List<ExchangePointReadDto> findAll();

    void save(ExchangePoint exchangePoint);
}
