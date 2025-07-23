package supermarketKata;

import java.util.*;
import java.util.stream.Collectors;

public class Supermarket {
    public static List<Integer> calculateSummary(List<ClientSale> clientSales) {
            return clientSales.stream()
                    .mapToInt(ClientSale::getPriceProductsSummary).boxed().collect(Collectors.toList())
                    .stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }
}