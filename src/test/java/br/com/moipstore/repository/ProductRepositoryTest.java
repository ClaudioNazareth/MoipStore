package br.com.moipstore.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void whenApplicationStartsProductDataShouldBeLoaded(){
        assertThat(productRepository.findAll())
                .as("When Application starts all data from file (data.json) must be loaded to MongoDB " +
                        "The application has been configured to automatically read the data.json file " +
                        "insert into the mongodb, which is automatically loaded in the application start, even in the integrated tests")
                .hasSize(3)
                .extracting("code", "name", "price")
                .contains(tuple("PROD-1020", "PlayStation 4 Slim Console",39901),
                        tuple("PROD-1030", "Xbox One S 500GB Console", 35099),
                        tuple("PROD-1040", "Nintendo Switch Console", 29999));
    }
}