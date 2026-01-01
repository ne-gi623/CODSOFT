import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter base currency (e.g. USD, INR, EUR): ");
        String baseCurrency = scanner.next().toUpperCase();

        System.out.print("Enter target currency (e.g. USD, INR, EUR): ");
        String targetCurrency = scanner.next().toUpperCase();

        System.out.print("Enter amount to convert: ");
        double amount = scanner.nextDouble();

        try {
            double rate = getExchangeRate(baseCurrency, targetCurrency);
            double convertedAmount = amount * rate;

            System.out.println("\n Conversion Result ");
            System.out.printf("%.2f %s = %.2f %s\n",
                    amount, baseCurrency, convertedAmount, targetCurrency);

        } catch (Exception e) {
            System.out.println(" Error fetching exchange rate.");
        }

        scanner.close();
    }

    private static double getExchangeRate(String base, String target) throws Exception {

        String urlStr = "https://api.exchangerate-api.com/v4/latest/" + base;
        URL url = java.net.URI.create(urlStr).toURL();

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();

        String json = response.toString();

        // Extract target currency rate manually
        String search = "\"" + target + "\":";
        int index = json.indexOf(search);

        if (index == -1) {
            throw new RuntimeException("Currency not found");
        }

        int start = index + search.length();
        int end = json.indexOf(",", start);
        if (end == -1) {
            end = json.indexOf("}", start);
        }

        return Double.parseDouble(json.substring(start, end));
    }
}
