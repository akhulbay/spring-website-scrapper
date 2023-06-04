package kz.chingiz.currencyparser.service.impl;

import kz.chingiz.currencyparser.model.ExchangePoint;
import kz.chingiz.currencyparser.service.ExchangePointService;
import kz.chingiz.currencyparser.service.ScrapperService;
import kz.chingiz.currencyparser.utils.TimeConverter;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScrapperServiceImpl implements ScrapperService {

    private static final String USD_CURRENCY = "USD";
    private static final String EUR_CURRENCY = "EUR";
    private static final String RUB_CURRENCY = "RUB";

    @Value("${website.url}")
    private String websiteUrl;

    private final ExchangePointService exchangePointService;

    @Override
    public void scrape() {
        ChromeDriver driver = new ChromeDriver();
        try {
            driver.get(websiteUrl);
            Thread.sleep(5000L);
            WebElement element = driver.findElement(By.className("scroll-container"));
            List<WebElement> elements = element.findElements(By.className("punkt-open"));
            for (WebElement webElement : elements) {
                ExchangePoint exchangePoint = new ExchangePoint();

                WebElement exchangePointName = webElement.findElement(By.className("tab"));
                if (exchangePointName.getText() != null) {
                    exchangePoint.setName(exchangePointName.getText());
                }

                WebElement address = webElement.findElement(By.tagName("address"));
                if (address.getText() != null) {
                    exchangePoint.setAddress(address.getText());
                }

                WebElement timeElement = webElement.findElement(By.tagName("time"));
                String time = timeElement.getAttribute("title");
                if (time != null) {
                    exchangePoint.setTime(TimeConverter.convertStringToLocalTime(time).orElse(null));
                }

                List<WebElement> allCurrencies = webElement.findElements(By.className("currency"));

                List<WebElement> usdCurrencySpanElements = allCurrencies.get(0).findElements(By.tagName("span"));
                processCurrency(usdCurrencySpanElements, exchangePoint, USD_CURRENCY);

                List<WebElement> eurCurrencySpanElements = allCurrencies.get(1).findElements(By.tagName("span"));
                processCurrency(eurCurrencySpanElements, exchangePoint, EUR_CURRENCY);

                List<WebElement> rubCurrencyElements = allCurrencies.get(2).findElements(By.tagName("span"));
                processCurrency(rubCurrencyElements, exchangePoint, RUB_CURRENCY);

                List<WebElement> contacts = webElement.findElements(By.className("phone"));
                StringBuilder allContacts = new StringBuilder();
                for (int i = 0; i < contacts.size(); i++) {
                    String contact = contacts.get(i).getText();
                    if (!contact.isEmpty()) {
                        allContacts.append(contact);
                        if (i < contacts.size() - 1) {
                            allContacts.append(",");
                        }
                    }
                }
                exchangePoint.setContacts(allContacts.toString());
                exchangePointService.save(exchangePoint);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }
    }

    private void processCurrency(List<WebElement> currencyElements, ExchangePoint exchangePoint, String currencyType) {
        double purchase = Double.parseDouble(currencyElements.get(0).getText());
        double sell = Double.parseDouble(currencyElements.get(2).getText());

        switch (currencyType) {
            case "USD" -> {
                exchangePoint.setPurchaseDollar(purchase);
                exchangePoint.setSellDollar(sell);
            }
            case "EUR" -> {
                exchangePoint.setPurchaseEuro(purchase);
                exchangePoint.setSellEuro(sell);
            }
            case "RUB" -> {
                exchangePoint.setPurchaseRuble(purchase);
                exchangePoint.setSellRuble(sell);
            }
        }
    }
}
