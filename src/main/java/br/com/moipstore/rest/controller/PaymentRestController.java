package br.com.moipstore.rest.controller;

import br.com.moip.exception.ValidationException;
import br.com.moipstore.model.Payment;
import br.com.moipstore.model.request.ErrorInfo;
import br.com.moipstore.model.request.PaymentDomain;
import br.com.moipstore.repository.OrderRepository;
import br.com.moipstore.service.PaymentService;
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

import java.net.URI;

import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/api/v1/payments")
@Api(value = "Payment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,
     tags = {"Payment API"}, description = "Handle all Payment Requests", basePath = "/api/v1/payments")
public class PaymentRestController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping
    @ApiOperation(value = "Create an Payment by OrderId , an Hash of a credit card and CreditCard Hoelder",
            notes = "Create an Payment and return the link of the created Payment",
            response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400 , message = "Bad Request"),
            @ApiResponse(code = 500 , message = "Internal Server Error")})
    public ResponseEntity<?> createPayment(@RequestBody PaymentDomain paymentRequest){

        Payment createdPayment = paymentService.createPayment(paymentRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdPayment.getId()).toUri();

        return created(location).build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    ErrorInfo
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
