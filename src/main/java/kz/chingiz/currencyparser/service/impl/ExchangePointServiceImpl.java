package kz.chingiz.currencyparser.service.impl;

import kz.chingiz.currencyparser.dto.ExchangePointReadDto;
import kz.chingiz.currencyparser.mapper.ExchangePointReadMapper;
import kz.chingiz.currencyparser.model.ExchangePoint;
import kz.chingiz.currencyparser.repository.ExchangePointRepository;
import kz.chingiz.currencyparser.service.ExchangePointService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExchangePointServiceImpl implements ExchangePointService {

    private final ExchangePointRepository exchangePointRepository;
    private final ExchangePointReadMapper exchangePointReadMapper;

    @Override
    public List<ExchangePointReadDto> findAll() {
        return exchangePointRepository.findAll().stream()
                .map(exchangePointReadMapper::map)
                .toList();
    }

    @Transactional
    @Override
    public void save(ExchangePoint exchangePoint) {
        exchangePointRepository.findByName(exchangePoint.getName())
                .ifPresent(point -> exchangePoint.setId(point.getId()));
        exchangePointRepository.save(exchangePoint);
    }
}
