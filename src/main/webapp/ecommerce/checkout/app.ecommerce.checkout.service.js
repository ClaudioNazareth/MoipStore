/**
 * MoipStore - Responsive Shopping store
 * Separate module for dealing with checkout | e-commerce
 */

(function () {
    'use strict';

    // factory
    angular.module('moipstore.ecommerce.checkout')
        .factory('EcommerceCheckoutService', EcommerceCheckoutService);

    EcommerceCheckoutService.$inject = ['$http'];

    function EcommerceCheckoutService($http) {

        var service = {
            createOrder:createOrder,
            createPayment : createPayment
        };
        return service;

        function createOrder(orderRequest) {
            var req = {
                method: 'POST',
                url: '/api/order',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: orderRequest
            };
            return $http(req)
                .then(returnSuccess)
                .catch(getError);

            function returnSuccess(response) {
                return response;
            }
            function getError(error) {
                //Implementar looger
            }
        }

        function createPayment(paymentRequest) {
            var req = {
                method: 'POST',
                url: '/api/payment',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: paymentRequest
            };
            return $http(req)
                .then(returnSuccess)
                .catch(getError);

            function returnSuccess(response) {
                return response;
            }

            function getError(error) {
                //Implementar looger
            }
        }
    }

})();