/**
 * MoipStore - Responsive Shopping store
 * Separate controller for dealing with product | e-commerce
 */

(function () {
    'use strict';

    angular.module('moipstore.ecommerce')
        .controller('EcommerceProductController', EcommerceProductController);

    EcommerceProductController.$inject = ['EcommerceProductService'];

    function EcommerceProductController(EcommerceProductService) {
        var ctrl = this;
        ctrl.products = [];

        activate();

        function activate() {
            EcommerceProductService.findProducts().then(function (response) {
                ctrl.products = response;
            });
        }
    }

})();
