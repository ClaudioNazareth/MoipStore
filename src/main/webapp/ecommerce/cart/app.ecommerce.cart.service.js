/**
 * MoipStore - Responsive Shopping store
 * Separate module for dealing with cart | e-commerce
 */

(function () {
    'use strict';

    // factory
    angular.module('moipstore.ecommerce.cart')
        .factory('EcommerceCartService', EcommerceCartService);

    EcommerceCartService.$inject = ['$http', '$window'];

    function EcommerceCartService($http, $window) {
        var service = this;
        service.selectedProducts = [];

        var service = {
            addProductToCart:addProductToCart,
            getSelectedProducts : getSelectedProducts,
            removeProductFromCart : removeProductFromCart,
            calculateAmount : calculateAmount
        };
        return service;

        ////////////

        function addProductToCart(product) {

            if(service.selectedProducts.length > 0) {
                var found = false;
                service.selectedProducts.forEach(function (selectedProduct) {
                    if (product.code === selectedProduct.code) {
                        selectedProduct.quantity++;
                        found = true;
                    }
                });
                if(!found){
                    product.quantity = 1;
                    service.selectedProducts.push(product);
                }
            }else{
                product.quantity = 1;
                service.selectedProducts.push(product);
            }
            $window.localStorage['selected-products'] = angular.toJson(service.selectedProducts);
        }
        
        function getSelectedProducts() {
            service.selectedProducts = angular.fromJson($window.localStorage['selected-products']);
            if(service.selectedProducts === undefined){
                service.selectedProducts = [];
            }
            return service.selectedProducts;
        }

        function removeProductFromCart(product) {
            var index = null;
            service.selectedProducts.forEach(function (selectedProduct) {
                if(selectedProduct.code === product.code){
                    index = service.selectedProducts.indexOf(selectedProduct)
                }
            });
            if (index != -1) {
                service.selectedProducts.splice(index, 1);
                $window.localStorage['selected-products'] = angular.toJson(service.selectedProducts);
            }
        }

        function calculateAmount(selectedProducts, shouldApplyCoupon) {
            service.selectedProducts =  selectedProducts;
            $window.localStorage['selected-products'] = angular.toJson(service.selectedProducts);
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