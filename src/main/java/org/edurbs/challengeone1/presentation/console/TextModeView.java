package org.edurbs.challengeone1.presentation.console;

import org.edurbs.challengeone1.application.domain.Currency;
import org.edurbs.challengeone1.application.gateways.CurrencyGateway;
import org.edurbs.challengeone1.application.usecases.ConvertCurrency;
import org.edurbs.challengeone1.application.usecases.ValidateCurrency;

import java.util.Scanner;

public class TextModeView {

    private final Scanner scanner = new Scanner(System.in);
    private final CurrencyGateway currencyGateway;

    public  TextModeView(CurrencyGateway currencyGateway){
        this.currencyGateway = currencyGateway;
    }

    public void start(){

            showInstructions();
            System.out.print("Informe a moeda de origem: ");
            String originCurrency = scanner.nextLine();
            if (isInvalidCurrencyOption(originCurrency)) {
                return;
            }
            System.out.print("Informe a moeda de destino: ");
            String destinationCurrency = scanner.nextLine();
            if (isInvalidCurrencyOption(originCurrency)) {
                return;
            }
            System.out.print("Informe o valor a ser convertido: ");
            double amount = scanner.nextDouble();
            if (isInvalidAmount(amount)) {
                return;
            }

            showResult(convert(originCurrency, destinationCurrency, amount));

    }

    private void showInstructions(){
        for (Currency currency : Currency.values()) {
            System.out.print(currency.getCode()+", ");
        }
        System.out.println("Informe as moedas de origem, destino e o valor que deseja converter.");
    }

    private void showResult(double result){
        System.out.println("O resultado da conversão é: " + result);
    }

    private double convert(String originCurrency, String destinationCurrency, double amount) {
        ConvertCurrency convertCurrency = new ConvertCurrency(currencyGateway);
        Currency originCurrencyEnum = Currency.valueOf(originCurrency.toUpperCase());
        Currency destinationCurrencyEnum = Currency.valueOf(destinationCurrency.toUpperCase());
        return convertCurrency.convert(originCurrencyEnum, destinationCurrencyEnum, amount);
    }

    private boolean isInvalidAmount(double amount) {
        if (amount <= 0) {
            System.out.println("O valor deve ser maior que zero");
            return true;
        }
        return false;
    }

    private boolean isInvalidCurrencyOption(String option){
        if(option.equals("sair")){
            System.exit(0);
            return true;
        }else if(!ValidateCurrency.isValid(option)){
            System.out.println("Moeda inválida");
            return true;
        }
        return false;
    }

}
