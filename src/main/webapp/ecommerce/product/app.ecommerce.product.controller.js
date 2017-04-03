/**
 * MoipStore - Responsive Shopping store
 * Separate controller for dealing with product | e-commerce
 */

(function () {
    'use strict';

    angular.module('moipstore.ecommerce.product')
        .controller('EcommerceProductController', EcommerceProductController);

    EcommerceProductController.$inject = ['$rootScope','$timeout','EcommerceProductService', 'EcommerceCartService'];

    function EcommerceProductController($rootScope, $timeout ,EcommerceProductService, EcommerceCartService) {
        var ctrl = this;
        ctrl.products = [];
        ctrl.loading = false

        ctrl.addProductToCart = addProductToCart;

        activate();

        function activate() {
            EcommerceProductService.findProducts().then(function (response) {
                ctrl.products = response;
            });
        }

        function addProductToCart(product) {
            ctrl.loading = true;
            //Only for aesthetic purpose
            $timeout(function() {
                EcommerceCartService.addProductToCart(product);
                $rootScope.$broadcast('addProduct');
                ctrl.loading = false;
            }, 1000);

        }
    }

})();
