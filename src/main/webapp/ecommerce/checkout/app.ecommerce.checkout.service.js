/**
 * MoipStore - Responsive Shopping store
 * Separate module for dealing with checkout | e-commerce
 * The script was develop following this guide of best practices https://github.com/johnpapa/angular-styleguide/blob/master/a1/README.md
 */

(function () {
    'use strict';

    // factory
    angular.module('moipstore.ecommerce.checkout')
        .factory('EcommerceCheckoutService', EcommerceCheckoutService);

    EcommerceCheckoutService.$inject = ['$http','$log'];

    function EcommerceCheckoutService($http, $log) {

        var service = {
            createOrder:createOrder
        };
        return service;

        function createOrder(orderRequest) {
            var req = {
                method: 'POST',
                url: '/api/v1/orders',
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
    }

})();