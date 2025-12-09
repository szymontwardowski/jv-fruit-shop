package core.basesyntax.strategy;

import core.basesyntax.service.operation.OperationHandler;
import core.basesyntax.model.FruitTransaction;

public interface OperationStrategy {
    OperationHandler getHandler(FruitTransaction.Operation operation);
}
