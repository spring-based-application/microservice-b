package com.trl.microserviceb.app;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AppServiceImpl implements AppService {

    private final EurekaClient eurekaClient;
    private final RestTemplate restTemplate;

    @Value("${spring.application.name}")
    private String appName;

    public AppServiceImpl(@Lazy @Qualifier("eurekaClient") EurekaClient eurekaClient, RestTemplate restTemplate) {
        this.eurekaClient = eurekaClient;
        this.restTemplate = restTemplate;
    }

    @Override
    public String checkConnectionBetweenMicroservices() {
        StringBuilder result = new StringBuilder();

        String fromMicroserviceB = getInfo();
        String fromMicroserviceC = restTemplate.getForObject("http://MICROSERVICE-C/app/getInfo", String.class);
        String fromMicroserviceD = restTemplate.getForObject("http://MICROSERVICE-D/app/getInfo", String.class);
        String fromMicroserviceE = restTemplate.getForObject("http://MICROSERVICE-E/app/getInfo", String.class);
        String fromMicroserviceA = restTemplate.getForObject("http://MICROSERVICE-A/app/getInfo", String.class);


        result.append(fromMicroserviceB);
        result.append("\n");
        result.append(fromMicroserviceC);
        result.append("\n");
        result.append(fromMicroserviceD);
        result.append("\n");
        result.append(fromMicroserviceE);
        result.append("\n");
        result.append(fromMicroserviceA);

        return result.toString();
    }

    @Override
    public String getInfo() {
        InstanceInfo instanceInfo = eurekaClient.getApplication(appName).getInstances().get(0);

        return String.format("(microservice-b)(GetInfo)(Application name: '%s')(Host: '%s')(Port: '%s')",
                instanceInfo.getAppName(), instanceInfo.getHostName(), instanceInfo.getPort());
    }
}
