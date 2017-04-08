package br.com.moipstore.repository.rest;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import net.minidev.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductRepositoryRestTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void whenApplicationStartsRestServicesShouldBeStarted(){
        String body = this.restTemplate.getForObject("/api/v1/products", String.class);
        ReadContext ctx = JsonPath.parse(body);
        List<JSONArray> products = ctx.read("$..products");
        assertThat(products.get(0)).as("When Application starts all data from file (data.json) must be loaded to MongoDB" +
                " and be exposed as a Rest Resource at /api/products " +
                "The application has been configured to automatically read the data.json file " +
                "insert into the mongodb and expose the data as Rest Sources which is automatically loaded in the application start, even in the integrated tests")
                .hasSize(3);
        final LinkedHashMap linkedHashMap = (LinkedHashMap) products.get(0).get(0);
        assertThat(linkedHashMap.get("code")).isEqualTo("PROD-1020");
        assertThat(linkedHashMap.get("name")).isEqualTo("PlayStation 4 Slim Console");
        assertThat(linkedHashMap.get("price")).isEqualTo( 39901);
    }

}
