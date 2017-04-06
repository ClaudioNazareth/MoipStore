/**
 * MoipStore - Responsive Shopping store
 * Separate controller for dealing with product | e-commerce
 * The script was develop following this guide of best practices https://github.com/johnpapa/angular-styleguide/blob/master/a1/README.md
 */

(function () {
    'use strict';

    angular.module('moipstore.ecommerce.product')
        .controller('EcommerceProductController', EcommerceProductController);

    EcommerceProductController.$inject = ['$rootScope','$timeout', '$log', 'SweetAlert' ,'EcommerceProductService', 'EcommerceCartService'];

    function EcommerceProductController($rootScope, $timeout, $log, SweetAlert, EcommerceProductService, EcommerceCartService) {

        var ctrl = this;
        ctrl.products = [];
        ctrl.loading = false;
        ctrl.date = new Date();
        ctrl.addProductToCart = addProductToCart;

        activate();

        function activate() {
            EcommerceProductService.findProducts().then(returnSuccess, returnError);
        }
        function returnSuccess(response) {
            ctrl.products = response;
        }
        function returnError(error) {
            SweetAlert.swal({
                title: "Error",
                text: "There was an error fetching the products"
            });
        }

        function addProductToCart(product) {
            ctrl.loading = true;

            //Only for aesthetic purpose
            $timeout(function() {
                EcommerceCartService.addProductToCart(product);
                $rootScope.$broadcast('addProduct'); // Broadcast to cart controller
                ctrl.loading = false;
            }, 1000);

        }
    }

})();
