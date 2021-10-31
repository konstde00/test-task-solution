package service;

import config.AppConfig;
import dto.response.ProductResponseDto;
import java.util.ArrayList;
import java.util.List;
import model.Product;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.impl.ConsoleReader;
import service.impl.FileReader;
import service.impl.WebSourceReader;
import service.mapper.ProductDtoMapper;
import util.DataSourceGetterUtil;

public class App {
    private static final AnnotationConfigApplicationContext context
            = new AnnotationConfigApplicationContext(AppConfig.class);
    private static ProductService productService = context.getBean(ProductService.class);
    private static DataReader fileReader = context.getBean(FileReader.class);
    private static DataReader webSourceReader = context.getBean(WebSourceReader.class);
    private static DataReader consoleReader = context.getBean(ConsoleReader.class);
    private static ProductParser parser = context.getBean(ProductParser.class);
    private static ReportProduct reportProduct = context.getBean(ReportProduct.class);
    private static Validator validator = context.getBean(Validator.class);
    private static ProductDtoMapper mapper = new ProductDtoMapper();

    private static List<String> dataSources = new ArrayList<>();

    public App() {
    }

    public static void build() {
        consoleReader.readFromDataSource("console");

        dataSources = DataSourceGetterUtil.getFilePaths();
        dataSources.addAll(DataSourceGetterUtil.getUrls());

        System.out.println(reportProduct.getReport().trim());
    }

    public static void workOutDatasourceInput(String data) {
        List<String> dataFromSource;
        if (data.contains("http")) {
            dataFromSource = webSourceReader.readFromDataSource(data);
        } else {
            dataFromSource = fileReader.readFromDataSource(data);
        }
        for (String line : dataFromSource) {
            validator.checkInputData(line);
            ProductResponseDto productResponseDto = parser.parseData(line);
            Product newProduct = mapper.mapToModel(productResponseDto);
            if (productService.exists(newProduct)) {
                productService.update(newProduct);
            } else {
                productService.save(newProduct);
            }
        }
    }
}
