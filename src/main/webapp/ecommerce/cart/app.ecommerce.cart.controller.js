/**
 * MoipStore - Responsive Shopping store
 * Separate controller for dealing with cart | e-commerce
 */

(function () {
    'use strict';

    angular.module('moipstore.ecommerce.cart')
        .controller('EcommerceCartController', EcommerceCartController);

    EcommerceCartController.$inject = ['$scope','EcommerceCartService'];

    function EcommerceCartController($scope, EcommerceCartService) {
        var ctrl = this;
        ctrl.totalItems = 0;
        ctrl.amount = 0;
        ctrl.selectedProducts = [];
        ctrl.shouldApplyCoupon = false;

        ctrl.calculateAmount = calculateAmount;
        ctrl.removeProduct = removeProduct;
        ctrl.applyCoupon = applyCoupon;

        activate();

        $scope.$on('addProduct', function(event, args) {
            calculateTotalItems();
        });

        function activate() {
            ctrl.selectedProducts = EcommerceCartService.getSelectedProducts();
            calculateTotalItems();
            calculateAmount();
        }
        
        function calculateAmount() {
            ctrl.amount = EcommerceCartService.calculateAmount(ctrl.selectedProducts, ctrl.shouldApplyCoupon);
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
        
        function applyCoupon() {
            ctrl.shouldApplyCoupon = true;
            calculateAmount();
        }
    }

})();

