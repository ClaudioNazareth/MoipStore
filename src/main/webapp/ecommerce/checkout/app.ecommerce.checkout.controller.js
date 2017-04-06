/**
 * MoipStore - Responsive Shopping store
 * Separate controller for dealing with Checkout | e-commerce
 * The script was develop following this guide of best practices https://github.com/johnpapa/angular-styleguide/blob/master/a1/README.md
 */

(function () {
    'use strict';

    angular.module('moipstore.ecommerce.checkout')
        .controller('EcommerceCheckoutController', EcommerceCheckoutController);

    EcommerceCheckoutController.$inject = ['$scope','EcommerceCartService', 'EcommerceCheckoutService', 'EcommercePaymentService', 'SweetAlert'];

    function EcommerceCheckoutController($scope, EcommerceCartService, EcommerceCheckoutService, EcommercePaymentService, SweetAlert) {
        var ctrl = this;

        ctrl.amount = 0;
        ctrl.selectedProducts = [];
        ctrl.shouldApplyCoupon = false;
        ctrl.cardNumber= null;
        ctrl.cardCVC= null;
        ctrl.cardExpirationMonth = null;
        ctrl.cardExpirationYear = null;
        ctrl.cardName = null;
        ctrl.loading = false;
        ctrl.coupon = null;


        ctrl.makePayment = makePayment;
        ctrl.applyCoupon = applyCoupon;

        activate();

        function activate() {
            ctrl.selectedProducts = EcommerceCartService.getSelectedProducts();
            calculateAmount();
        }

        function calculateAmount() {
            ctrl.amount = EcommercePaymentService.calculateAmount(ctrl.selectedProducts, ctrl.shouldApplyCoupon);
        }
        
        function applyCoupon() {
            ctrl.shouldApplyCoupon = true;
            SweetAlert.swal({
                title: "Success",
                text: "Coupon applied with success "
            });
            ctrl.coupon = null;
            calculateAmount();
        }
        function makePayment() {
            var cc = new Moip.CreditCard({
                number  : ctrl.cardNumber,
                cvc     : ctrl.cardCVC,
                expMonth: ctrl.cardExpirationMonth,
                expYear : ctrl.cardExpirationYear,
                pubKey  : 'MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoBttaXwRoI1Fbcond5mS7QOb7X2lykY5hvvDeLJelvFhpeLnS4YDwkrnziM3W00UNH1yiSDU+3JhfHu5G387O6uN9rIHXvL+TRzkVfa5iIjG+ap2N0/toPzy5ekpgxBicjtyPHEgoU6dRzdszEF4'
                    +'ItimGk5ACx/lMOvctncS5j3uWBaTPwyn0hshmtDwClf6dEZgQvm/dNaIkxHKV+9jMn3ZfK/liT8A3xwaVvRzzuxf09xJTXrAd9v5VQbeWGxwFcW05oJulSFjmJA9HcmbDYHJT+sG2mlZDEruCGAzCVubJwGY1aRlcs9AQc1jIm/l8JwH7le2kpk3QoX+gz0wWwIDAQAB'
            });

            if( cc.isValid()){
                ctrl.loading = true;
                var itens =[];
                ctrl.selectedProducts.forEach(function (selectedProduct) {
                    var item = {productCode : selectedProduct.code , quantity : selectedProduct.quantity};
                    itens.push(item);
                });
                var orderRequest = {
                        customerId : "cust",
                        ShippingAddressId: "order",
                        items: itens
                };
                EcommerceCheckoutService.createOrder(orderRequest).then(function (response) {
                    var paymentRequest = {orderId : response.headers('Location').split("order/")[1]};
                    EcommercePaymentService.createPayment(paymentRequest).then(function (response) {
                        ctrl.loading = false;
                        alert(response.headers('Location').split("payment/")[1])
                    });
                });

            } else {
                alert('Invalid credit card. Verify parameters: number, cvc, expiration Month, expiration Year');
                return false; // Don't submit the form
            }
            
        }
    }
})();

