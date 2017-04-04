/**
 * MoipStore - Responsive Shopping store
 * Separate controller for dealing with Checkout | e-commerce
 */

(function () {
    'use strict';

    angular.module('moipstore.ecommerce.checkout')
        .controller('EcommerceCheckoutController', EcommerceCheckoutController);

    EcommerceCheckoutController.$inject = ['$scope','EcommerceCartService'];

    function EcommerceCheckoutController($scope, EcommerceCartService) {
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

        ctrl.makePayment = makePayment;

        activate();

        function activate() {
            ctrl.selectedProducts = EcommerceCartService.getSelectedProducts();
            calculateAmount();
        }

        function calculateAmount() {
            ctrl.amount = EcommerceCartService.calculateAmount(ctrl.selectedProducts, ctrl.shouldApplyCoupon);
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
                alert(cc.hash())
            } else {
                alert('Invalid credit card. Verify parameters: number, cvc, expiration Month, expiration Year');
                return false; // Don't submit the form
            }
            
        }
    }
})();

