/**
 * MoipStore - Responsive Shopping store
 * Separate module for dealing with checkout | e-commerce
 */

(function () {
    'use strict';

    angular.module('moipstore.ecommerce.checkout', [
        'ui.router',                    // Routing
        'oc.lazyLoad',                  // ocLazyLoad
        'ui.bootstrap'                  // Ui Bootstrap
    ]);
})();