package core.basesyntax;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.io.FileReaderImpl;
import core.basesyntax.service.io.FileWriterImpl;
import core.basesyntax.strategy.OperationStrategy;
import core.basesyntax.strategy.OperationStrategyImpl;
import core.basesyntax.service.ShopService;
import core.basesyntax.service.ShopServiceImpl;
import core.basesyntax.service.ReportGenerator;
import core.basesyntax.service.ReportGeneratorImpl;
import core.basesyntax.service.FileReader;
import core.basesyntax.service.FileWriter;
import core.basesyntax.service.operation.BalanceOperation;
import core.basesyntax.service.operation.SupplyOperation;
import core.basesyntax.service.operation.PurchaseOperation;
import core.basesyntax.service.operation.ReturnOperation;
import core.basesyntax.service.operation.OperationHandler;
import core.basesyntax.service.CsvParser;

import java.util.List;
import java.util.Map;

public class Main {

    private static final String INPUT_FILE_PATH = "src/main/resources/input.csv";
    private static final String OUTPUT_FILE_PATH = "src/main/resources/result.csv";

    public static void main(String[] args) {
        Map<FruitTransaction.Operation, OperationHandler> operationHandlers = Map.of(
                FruitTransaction.Operation.BALANCE, new BalanceOperation(),
                FruitTransaction.Operation.SUPPLY, new SupplyOperation(),
                FruitTransaction.Operation.PURCHASE, new PurchaseOperation(),
                FruitTransaction.Operation.RETURN, new ReturnOperation()
        );

        // Inicjalizacja komponentów
        OperationStrategy operationStrategy = new OperationStrategyImpl(operationHandlers);
        ShopService shopService = new ShopServiceImpl(operationStrategy);
        ReportGenerator reportGenerator = new ReportGeneratorImpl();
        FileWriter fileWriter = new FileWriterImpl();
        CsvParser parser = new CsvParser();
        FileReader fileReader = new FileReaderImpl();

        // 1. Czytanie danych z pliku
        List<String> rawTransactions = fileReader.input(INPUT_FILE_PATH);

        // 2. Parsowanie CSV na obiekty
        List<FruitTransaction> transactions = parser.parse(rawTransactions);

        // 3. Przetwarzanie transakcji
        shopService.process(transactions);

        // 4. Generowanie i zapis raportu
        String report = reportGenerator.getReport();
        fileWriter.output(report, OUTPUT_FILE_PATH);

        System.out.println("Processing finished. Report written to: " + OUTPUT_FILE_PATH);
    }
}
