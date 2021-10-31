package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.App;

@Controller
public class InputController {

    @GetMapping(value = "/giveInput")
    public String addDataSource(String dataSource) {
        return "add data source";
    }

    @PostMapping(value = "/giveInput")
    public String dataSourcePost(@RequestParam("dataSource") String dataSource) {
        App.workOutDatasourceInput(dataSource);
        return "add data source";
    }
}
