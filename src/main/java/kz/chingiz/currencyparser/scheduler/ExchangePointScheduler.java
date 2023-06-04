package kz.chingiz.currencyparser.scheduler;

import kz.chingiz.currencyparser.service.ScrapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExchangePointScheduler {

    private final ScrapperService scrapperService;

    @Scheduled(fixedDelay = 300000L)
    public void scheduleParser() {
        scrapperService.scrape();
    }
}
