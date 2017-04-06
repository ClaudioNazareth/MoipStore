/**
 * MoipStore - Responsive Shopping store
 * Separate controller for dealing with cart | e-commerce
 * The script was develop following this guide of best practices https://github.com/johnpapa/angular-styleguide/blob/master/a1/README.md
 */

(function () {
    'use strict';

    angular.module('moipstore.ecommerce.cart')
        .controller('EcommerceCartController', EcommerceCartController);

    EcommerceCartController.$inject = ['$scope','EcommerceCartService', 'EcommercePaymentService', '$location', 'SweetAlert'];

    function EcommerceCartController($scope, EcommerceCartService, EcommercePaymentService, $location, SweetAlert) {
        var ctrl = this;
        ctrl.totalItems = 0;
        ctrl.amount = 0;
        ctrl.selectedProducts = [];
        ctrl.shouldApplyCoupon= false;

        ctrl.calculateAmount = calculateAmount;
        ctrl.removeProduct = removeProduct;
        ctrl.checkout = checkout;

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

        function checkout(){
            SweetAlert.swal({
                    title: "Are you sure?",
                    text: "Do you want to log in and to proceed with the payment?",
                    type: "info",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "Yes",
                    cancelButtonText: "No, cancel",
                    closeOnConfirm: true,
                    closeOnCancel: true
                },
                function (isConfirm) {
                    if (isConfirm) {
                        $location.path('ecommerce/checkout_detail');
                    }
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

