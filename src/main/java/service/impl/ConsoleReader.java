package service.impl;

import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Service;
import service.App;
import service.DataReader;

@Service
public class ConsoleReader implements DataReader {
    private static Scanner scanner = new Scanner(System.in);

    @Override
    public List<String> readFromDataSource(String string) {
        System.out.println("If you want to use console input"
                + " - just enter the path to file or url.");
        String input = scanner.nextLine();
        if (!input.equals("Stop")) {
            App.workOutDatasourceInput(input);
        }
        scanner.close();
        return List.of(input);
    }
}
