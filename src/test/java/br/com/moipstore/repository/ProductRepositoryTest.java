package br.com.moipstore.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void whenApplicationStartsProductDataShouldBeLoaded(){
        assertThat(productRepository.findAll())
                .as("When Application starts all data from file (data.json) must be loaded to MongoDB " +
                        "The application has been configured to automatically read the data.json file " +
                        "insert into the mongodb, which is automatically loaded in the application start, even in the integrated tests")
                .hasSize(3)
                .extracting("code", "name", "price")
                .contains(tuple("1020", "PlayStation 4 Slim Console", new BigDecimal("399.01")),
                        tuple("1030", "Xbox One S 500GB Console", new BigDecimal("350.99")));
    }
}