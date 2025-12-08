package core.basesyntax;

import java.util.Map;

public class BalanceOperation implements OperationHandler {

    @Override
    public void apply(FruitTransaction transaction) {

        Map<String, Integer> inventory = Storage.getFruitInventory();

        String fruit = transaction.getFruit();
        int quantity = transaction.getQuantity();

        inventory.put(fruit, quantity);
    }
}
