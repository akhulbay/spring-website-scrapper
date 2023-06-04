package kz.chingiz.currencyparser.controller;

import kz.chingiz.currencyparser.service.ExchangePointService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ExchangePointController {

    private final ExchangePointService exchangePointService;

    @GetMapping("/")
    public String findAll(Model model) {
        model.addAttribute("exchangePoints", exchangePointService.findAll());
        return "index";
    }
}
