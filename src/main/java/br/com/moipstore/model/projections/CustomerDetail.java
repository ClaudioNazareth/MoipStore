package br.com.moipstore.model.projections;

import br.com.moipstore.model.Customer;
import br.com.moipstore.model.Phone;
import br.com.moipstore.model.ShippingAddress;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

/**
 * Create a projection to customize the payload of Costumer to get all data in ONE request!
 */
@Projection(name = "customerDetail", types = {Customer.class})
public interface CustomerDetail {

    String getId();
    String getFullName();
    String getEmail();
    Date getBirthDate();
    String getTaxDocument();
    ShippingAddress getShippingAddress();
    Phone getPhone();
}
