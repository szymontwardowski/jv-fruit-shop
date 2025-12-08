package core.basesyntax;

import java.util.Map;

public class SupplyOperation implements OperationHandler {

    @Override
    public void apply(FruitTransaction transaction) {
        Map<String, Integer> inventory = Storage.getFruitInventory();

        String fruit = transaction.getFruit();
        int quantity = transaction.getQuantity();

        int currentQuantity = inventory.getOrDefault(fruit, 0);

        int newQuantity = currentQuantity + quantity;

        inventory.put(fruit, newQuantity);
    }
}
