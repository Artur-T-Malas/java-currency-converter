public class CurrencyConverter {
    public static float convert(float amount, Currency currencyStart, Currency currencyFinal) {
        float result = amount * currencyStart.usdExchangeRate / currencyFinal.usdExchangeRate;
        return result;
    }
}
