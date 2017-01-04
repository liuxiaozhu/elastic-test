package me.islim;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ESTest {
    public static void main(String[] args){

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

        TransportClient client = (TransportClient) context.getBean("client");

        GetResponse response = client.prepareGet("megacorp", "employee", "1").get();

        System.out.println(response.toString());

    }
}
