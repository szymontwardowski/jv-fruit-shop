package core.basesyntax;

import java.util.Map;

public class OperationStrategyImpl implements OperationStrategy {

    private final Map<FruitTransaction.Operation, OperationHandler> operationHandlers;

    public OperationStrategyImpl(Map<FruitTransaction.Operation, OperationHandler> operationHandlers) {
        this.operationHandlers = operationHandlers;

    }
        public OperationHandler getHandler(FruitTransaction.Operation operation) {
            if(!operationHandlers.containsKey(operation)) {
            throw new RuntimeException("Unknown operation type: " + operation);
        }
            return operationHandlers.get(operation);
    }
}
