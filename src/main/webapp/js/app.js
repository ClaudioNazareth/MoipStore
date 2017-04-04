/**
 * MoipStore - Responsive Shopping store
 */
(function () {
    'use strict';

    angular.module('moipstore', [
        'ui.router',                    // Routing
        'oc.lazyLoad',                  // ocLazyLoad
        'ui.bootstrap',                 // Ui Bootstrap
        'moipstore.ecommerce.product',  // Product e-commerce module
        'moipstore.ecommerce.cart',     // Cart e-commerce module
        'moipstore.ecommerce.checkout'  // Checkout e-commerce module
    ])

})();

