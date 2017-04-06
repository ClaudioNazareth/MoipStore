/**
 * MoipStore - Responsive Shopping store
 * Separate module for dealing with product | e-commerce
 * The script was develop following this guide of best practices https://github.com/johnpapa/angular-styleguide/blob/master/a1/README.md
 */

(function () {
    'use strict';

    angular.module('moipstore.ecommerce.product', [
        'ui.router',                    // Routing
        'oc.lazyLoad',                  // ocLazyLoad
        'ui.bootstrap'                  // Ui Bootstrap
    ]);
})();