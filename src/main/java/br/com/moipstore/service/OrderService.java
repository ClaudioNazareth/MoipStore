package br.com.moipstore.service;

import br.com.moipstore.model.Order;
import br.com.moipstore.model.request.OrderDomain;



public interface OrderService {

    Order createOrder(OrderDomain orderDomain);
}
