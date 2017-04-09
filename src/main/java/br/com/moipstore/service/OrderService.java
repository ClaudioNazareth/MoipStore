package br.com.moipstore.service;

import br.com.moipstore.model.Order;
import br.com.moipstore.model.request.OrderDomain;

public interface OrderService {
    /**
     *  Create a order in moip api and also in a moipstore and store in the database
     * @param orderDomain
     * @return Oreder
     */
    Order createOrder(OrderDomain orderDomain);
}
