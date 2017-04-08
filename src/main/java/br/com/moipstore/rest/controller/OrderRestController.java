package br.com.moipstore.rest.controller;

import br.com.moip.exception.ValidationException;
import br.com.moipstore.model.Order;
import br.com.moipstore.model.request.OrderDomain;
import br.com.moipstore.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/v1/orders")
@Api(value = "Order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, tags = {"Order API"}, description = "Handle all Order Requests", basePath = "/api/v1/orders")
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @ApiOperation(value = "Create an Order by Customer and list of items",
            notes = "Create an Order and return the link of the created order",
            response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400 , message = "Bad Request"),
            @ApiResponse(code = 500 , message = "Internal Server Error")})
    public ResponseEntity<?> createOrder(@RequestBody OrderDomain orderDomain){

        final Order order = orderService.createOrder(orderDomain);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(order.getId()).toUri();

        return created(location).build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody ErrorInfo
    handleInternalServerError(Exception ex) {
        return new ErrorInfo(ServletUriComponentsBuilder.fromCurrentRequest().toString() , ex);
    }

    /**
     * Return Moip validations to the caller
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    @ResponseBody ErrorInfo
    handleValidationException( ValidationException ex) {
        return new ErrorInfo(ServletUriComponentsBuilder.fromCurrentRequest().toString() ,ex);
    }
}
