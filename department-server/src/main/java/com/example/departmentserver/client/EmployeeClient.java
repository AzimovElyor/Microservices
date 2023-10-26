package com.example.departmentserver.client;

import com.example.departmentserver.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


@Component
public class EmployeeClient {
    @Autowired
    private WebClient.Builder webClient;
    @Value("${client.api.employee-base-url}")
    String EMPLOYEE_BASE_URL;
    public List<Employee> getEmployeesByDepartmentId(Integer departmentId){
     /*   ResponseEntity<List<Employee>> getDepartmentEmployees = restTemplate.exchange(
                EMPLOYEE_BASE_URL + "/department-employees/" + departmentId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Employee>>() {
                }
        );
        return getDepartmentEmployees.getBody();*/
        WebClient build = webClient.build();
      return   build.get()
                .uri(EMPLOYEE_BASE_URL + "/department-employees/" + departmentId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Employee>>() {
                }).block();
    }
}
