package kz.chingiz.currencyparser.repository;

import kz.chingiz.currencyparser.model.ExchangePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExchangePointRepository extends JpaRepository<ExchangePoint, Long> {
    Optional<ExchangePoint> findByName(String name);
}
