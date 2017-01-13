package me.islim;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ESTest {

    public static void main(String[] args){

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

        TransportClient client = (TransportClient) context.getBean("esClient");

//        GetResponse getResponse = client.prepareGet("megacorp", "employee", "1").get();
        SearchResponse searchResponse = client.prepareSearch("megacorp")
                                     .setTypes("employee")
                                     .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                                     .setQuery(QueryBuilders.matchQuery("about", "to go"))                 // Query
                                     .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(28))     // Filter
                                     .setFrom(0).setSize(60).setExplain(true)
                                     .get();

//        System.out.println(getResponse.toString());
        System.out.println(searchResponse.toString());

    }
}
