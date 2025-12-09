package core.basesyntax.strategy;

import core.basesyntax.model.FruitTransaction; // Zmieniona kolejność
import core.basesyntax.service.operation.OperationHandler;
import java.util.Map;

public interface OperationStrategy {
    OperationHandler getHandler(FruitTransaction.Operation operation);
}
