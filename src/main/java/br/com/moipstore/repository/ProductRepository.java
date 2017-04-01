package br.com.moipstore.repository;

import br.com.moipstore.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * Repository for product that uses Spring Data Rest MongoBD
 * @See http://docs.spring.io/spring-data/data-mongo/docs/current/reference/html
 */
@Repository
@RepositoryRestResource
public interface ProductRepository extends MongoRepository<Product, String> {
}
