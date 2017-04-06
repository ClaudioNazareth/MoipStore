/**
 * MoipStore - Responsive Shopping store
 * Separate module for dealing with cart | e-commerce
 * The script was develop following this guide of best practices https://github.com/johnpapa/angular-styleguide/blob/master/a1/README.md
 */

(function () {
    'use strict';

    // factory
    angular.module('moipstore.ecommerce.cart')
        .factory('EcommerceCartService', EcommerceCartService);

    EcommerceCartService.$inject = ['LocalStorageService'];

    function EcommerceCartService( LocalStorageService) {
        var service = this;
        service.selectedProducts = [];

        var service = {
            addProductToCart:addProductToCart,
            getSelectedProducts : getSelectedProducts,
            removeProductFromCart : removeProductFromCart,
        };
        return service;

        ////////////

        /**
         * Add product to cart, if it is already there increment the quantity
         * @param product
         */
        function addProductToCart(product) {
            var isProductInCart = false;
            if(service.selectedProducts.length > 0) {
                service.selectedProducts.forEach(function (selectedProduct) {
                    if (product.code === selectedProduct.code) {
                        selectedProduct.quantity++;
                        isProductInCart = true;
                    }
                });
            }
            if(!isProductInCart){
                product.quantity = 1;
                service.selectedProducts.push(product);
            }
            LocalStorageService.storeData('selected-products', service.selectedProducts);
        }

        /**
         * Return all products in cart
         * @returns {Array|Product}
         */
        function getSelectedProducts() {
            service.selectedProducts =LocalStorageService.getStoredData('selected-products');
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
                LocalStorageService.storeData('selected-products', service.selectedProducts);
            }
        }
    }

})();