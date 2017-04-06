/**
 * MoipStore - Responsive Shopping store
 * Separate module for dealing with payment | e-commerce
 * The script was develop following this guide of best practices https://github.com/johnpapa/angular-styleguide/blob/master/a1/README.md
 */

(function () {
    'use strict';

    // factory
    angular.module('moipstore.ecommerce.checkout')
        .factory('EcommercePaymentService', EcommercePaymentService);

    EcommercePaymentService.$inject = ['$http', 'LocalStorageService'];

    function EcommercePaymentService($http, LocalStorageService) {

        var service = {
            createPayment : createPayment,
            calculateAmount : calculateAmount
        };
        return service;

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
                return response;
            }
        }

        function calculateAmount(selectedProducts, shouldApplyCoupon) {
            LocalStorageService.storeData('selected-products', selectedProducts);
            service.selectedProducts =  selectedProducts;
            var amount = 0;
            service.selectedProducts.forEach(function (selectedProduct) {
                amount += (selectedProduct.quantity * selectedProduct.price);
            });
            if(shouldApplyCoupon){
                amount *= 0.95;
            }
            return amount;
        }
    }

})();
