/**
 * MoipStore - Responsive Shopping store
 * Separate controller for dealing with Checkout | e-commerce
 * The script was develop following this guide of best practices https://github.com/johnpapa/angular-styleguide/blob/master/a1/README.md
 */

(function () {
    'use strict';

    angular.module('moipstore.ecommerce.checkout')
        .controller('EcommerceCheckoutController', EcommerceCheckoutController);

    EcommerceCheckoutController.$inject = ['$scope','EcommerceCartService', 'EcommerceCheckoutService', 'EcommercePaymentService', 'SweetAlert', 'CreditCardService', 'CustomerService'];

    function EcommerceCheckoutController($scope, EcommerceCartService, EcommerceCheckoutService, EcommercePaymentService, SweetAlert, CreditCardService, CustomerService) {
        var ctrl = this;

        ctrl.amount = 0;
        ctrl.selectedProducts = [];
        ctrl.shouldApplyCoupon = false;
        ctrl.card ={
            number:null,
            CVC: null,
            expirationMonth:null,
            expirationYear:null,
            name:null
        };

        ctrl.loading = false;
        ctrl.coupon = null;
        ctrl.installment = null;
        ctrl.installments = [];
        ctrl.customer = null;

        ctrl.makePayment = makePayment;
        ctrl.applyCoupon = applyCoupon;

        activate();

        function activate() {
            ctrl.selectedProducts = EcommerceCartService.getSelectedProducts();
            calculateAmount();
            CustomerService.getCustomer().then(function (data) {
                ctrl.customer = data;
            })
        }

        function calculateAmount() {
            ctrl.amount = EcommercePaymentService.calculateAmount(ctrl.selectedProducts, ctrl.shouldApplyCoupon);
            calculateInstallments();
        }

        function calculateInstallments() {
            ctrl.installments = [];
            for (var i = 1; i <= 13; i++) {
                var installment = {number: i, text: i + " x " + ((ctrl.amount/100) / i).toFixed(2)};
                ctrl.installments.push(installment);
            }
        }
        
        function applyCoupon() {
            ctrl.shouldApplyCoupon = true;
            SweetAlert.swal({
                title: "Success",
                text: "Coupon applied with success "
            });
            ctrl.coupon = null;
            ctrl.installment = null;
            calculateAmount();
        }

        function makePayment() {

            if( CreditCardService.isCreditCardValid(ctrl.card)){
                ctrl.loading = true;
                var orderDomain = createOrderDomain();

                EcommerceCheckoutService.createOrder(orderDomain).then(onCreateOrderSuccess, onCreateOrderError);
            } else {
                SweetAlert.swal({
                    title: "CreditCard Invalid",
                    text: "Invalid credit card. Verify parameters: number, cvc, expiration Month, expiration Year"
                });
            }

            function createOrderDomain() {
                var items = [];
                ctrl.selectedProducts.forEach(function (selectedProduct) {
                    var item = {productCode: selectedProduct.code, quantity: selectedProduct.quantity};
                    items.push(item);
                });
                var orderDomain = {
                    customerId: ctrl.customer.code,
                    items: items
                };
                return orderDomain;
            }

            function onCreateOrderSuccess(response) {
                var paymentRequest = {
                    orderId : response.headers('Location').split("orders/")[1],
                    creditCardHash : CreditCardService.getCreditCardHash(),
                    holderDomain :{
                        fullName : ctrl.customer.fullName,
                        birthDate : ctrl.customer.birthDate.split("T")[0],
                        phoneAreaCode : ctrl.customer.phone.areaCode,
                        phoneNumber : ctrl.customer.phone.number,
                        taxDocument : ctrl.customer.taxDocument
                    }
                };

                EcommercePaymentService.createPayment(paymentRequest).then(onPaymentSuccess, onPaymentError);

                function onPaymentSuccess(response) {
                    ctrl.loading = false;
                    alert(response.headers('Location').split("payments/")[1])
                }

                function onPaymentError() {
                    SweetAlert.swal({
                        title: "Error",
                        text: "Error trying to create a Payment"
                    });
                }
            }

            function onCreateOrderError(response) {
                SweetAlert.swal({
                    title: "Error",
                    text: "Error trying to create an Order"
                });
            }
        }
    }
})();

