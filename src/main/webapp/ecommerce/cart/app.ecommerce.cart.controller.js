/**
 * MoipStore - Responsive Shopping store
 * Separate controller for dealing with cart | e-commerce
 * The script was develop following this guide of best practices https://github.com/johnpapa/angular-styleguide/blob/master/a1/README.md
 */

(function () {
    'use strict';

    angular.module('moipstore.ecommerce.cart')
        .controller('EcommerceCartController', EcommerceCartController);

    EcommerceCartController.$inject = ['$scope','EcommerceCartService', 'EcommercePaymentService'];

    function EcommerceCartController($scope, EcommerceCartService, EcommercePaymentService) {
        var ctrl = this;
        ctrl.totalItems = 0;
        ctrl.amount = 0;
        ctrl.selectedProducts = [];
        ctrl.shouldApplyCoupon= false;

        ctrl.calculateAmount = calculateAmount;
        ctrl.removeProduct = removeProduct;

        $scope.$on('addProduct', function(event, args) {
            calculateTotalItems();
        });

        activate();

        function activate() {
            ctrl.selectedProducts = EcommerceCartService.getSelectedProducts();
            calculateTotalItems();
            calculateAmount();
        }
        
        function calculateAmount() {
            ctrl.amount = EcommercePaymentService.calculateAmount(ctrl.selectedProducts, ctrl.shouldApplyCoupon);
            calculateTotalItems();
        }

        function calculateTotalItems() {
            ctrl.totalItems = 0;
            ctrl.selectedProducts.forEach(function (selectedProduct) {
                ctrl.totalItems += selectedProduct.quantity;
            });
        }
        
        function removeProduct(product) {
            EcommerceCartService.removeProductFromCart(product);
            ctrl.selectedProducts = EcommerceCartService.getSelectedProducts();
            calculateTotalItems();
            calculateAmount();
        }
    }

})();

