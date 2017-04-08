/**
 * MoipStore - Responsive Shopping store
 * Separate controller for dealing with Checkout | e-commerce
 * The script was develop following this guide of best practices https://github.com/johnpapa/angular-styleguide/blob/master/a1/README.md
 */

(function () {
    'use strict';

    angular.module('moipstore.ecommerce.checkout')
        .controller('EcommerceCheckoutController', EcommerceCheckoutController);

    EcommerceCheckoutController.$inject = ['$scope', '$location' ,'EcommerceCartService', 'EcommerceCheckoutService', 'EcommercePaymentService', 'SweetAlert', 'CreditCardService', 'CustomerService'];

    function EcommerceCheckoutController($scope, $location , EcommerceCartService, EcommerceCheckoutService, EcommercePaymentService, SweetAlert, CreditCardService, CustomerService) {
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

        /**
         * Calculates the value of the parcels in the JS to avoid requests to the server
         */
        function calculateInstallments() {
            var amount = ctrl.amount;
            var amountWithInterest =  ctrl.amount * 1.025;
            ctrl.installments = [];
            ctrl.amount
            for (var i = 1; i <= 13; i++) {
                if(i> 1){
                    amount = amountWithInterest;
                }
                var installment = {number: i, text: i + " x " + ((amount/100) / i).toFixed(2)};
                ctrl.installments.push(installment);
                if(i ===1){
                    ctrl.installment = installment;
                }
            }
        }

        /**
         * Apply coupon discount, this value is then validated on the server
         */
        function applyCoupon() {
            if(ctrl.coupon != null) {
                ctrl.shouldApplyCoupon = true;
                SweetAlert.swal({
                    title: "Success",
                    text: "Coupon applied with success "
                });
                ctrl.coupon = null;
                ctrl.installment = null;
                calculateAmount();
            }else{
                SweetAlert.swal({
                    title: "Error",
                    text: "Coupon must not be null "
                });
            }
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

            /**
             * If Order create with success this method will be call and proceed to payment
             * @param response
             */
            function onCreateOrderSuccess(response) {
                var paymentDomain = {
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

                EcommercePaymentService.createPayment(paymentDomain).then(onPaymentSuccess, onPaymentError);

                /**
                 * Show a message error when the Payment was successfully created
                 */
                function onPaymentSuccess(response) {
                    ctrl.loading = false;

                    // Time out here
                    SweetAlert.swal({
                            title: "Success",
                            text: "Your was successfully performed! Payment code : " + response.headers('Location').split("payments/")[1],
                            type: "success",
                            showCancelButton: false,
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "ok",
                            closeOnConfirm: true,
                            closeOnCancel: true
                        },
                        function (isConfirm) {
                            if (isConfirm) {
                                $location.path('ecommerce/product_details');
                            }
                        });
                }

                /**
                 * Show a message error when there is a problem creating an Payment
                 */
                function onPaymentError() {
                    ctrl.loading = false;
                    SweetAlert.swal({
                        title: "Error",
                        text: "Error trying to create a Payment"
                    });
                }
            }

            /**
             * Show a message error when there is a problem creating an order
             */
            function onCreateOrderError(response) {
                ctrl.loading = false;
                SweetAlert.swal({
                    title: "Error",
                    text: "Error trying to create an Order"
                });
            }
        }

        function createOrderDomain() {
            var items = [];
            ctrl.selectedProducts.forEach(function (selectedProduct) {
                var item = {productCode: selectedProduct.code, quantity: selectedProduct.quantity};
                items.push(item);
            });
            var orderDomain = {
                customerId: ctrl.customer.code,
                items: items,
                numberOfInstallments : ctrl.installment.number,
                coupon : ctrl.coupon

            };
            return orderDomain;
        }
    }
})();

