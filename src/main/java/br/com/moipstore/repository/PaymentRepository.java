package br.com.moipstore.repository;

import br.com.moipstore.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository for payment that uses Spring Data MongoBD
 * @See http://docs.spring.io/spring-data/data-mongo/docs/current/reference/html
 */
@Repository
public interface PaymentRepository  extends MongoRepository<Payment, String> {
}
