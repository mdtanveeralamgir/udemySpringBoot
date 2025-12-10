package com.in28minutes.spring.learn_spring_framework.enterprise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class MyWebController {

    //Field based dependency injection
    @Autowired
    private BusinessService businessService;

    public long returnValueFromBusinessService() {
        return businessService.CalculateSum();
    }
}

@Component
class BusinessService {

    private DataService dataService;

    @Autowired
    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }

    public long CalculateSum() {
        List<Integer> data = dataService.getData();
        return data.stream().reduce(Integer::sum).get();
    }
}

@Component
class DataService {
    public List<Integer> getData() {
        return Arrays.asList(1, 2, 3);
    }
}
