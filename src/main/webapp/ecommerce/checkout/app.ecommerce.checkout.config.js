/**
 * MoipStore - Responsive Shopping store
 * Separate config for dealing with checkout | e-commerce
 * The script was develop following this guide of best practices https://github.com/johnpapa/angular-styleguide/blob/master/a1/README.md
 */

(function () {
    'use strict';

    function config($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {

        $urlRouterProvider.otherwise("commerce/cart_detail");

        $ocLazyLoadProvider.config({
            // Set to true if you want to see what and when is dynamically loaded
            debug: false
        });
        $stateProvider
            .state('ecommerce_checkout', {
                abstract: true,
                url: "/ecommerce",
                templateUrl: "views/common/content.html",
                resolve: { // Load on demand plugins
                    loadPlugin: function ($ocLazyLoad) {
                        return $ocLazyLoad.load([
                            {
                                files: ['js/plugins/footable/footable.all.min.js', 'css/plugins/footable/footable.core.css']
                            },
                            {
                                name: 'ui.footable',
                                files: ['js/plugins/footable/angular-footable.js']
                            },
                            {
                                files: ['js/plugins/sweetalert/sweetalert.min.js', 'css/plugins/sweetalert/sweetalert.css']
                            },
                            {
                                name: 'oitozero.ngSweetAlert',
                                files: ['js/plugins/sweetalert/angular-sweetalert.min.js']
                            },
                            {
                                insertBefore: '#loadBefore',
                                name: 'localytics.directives',
                                files: ['css/plugins/chosen/bootstrap-chosen.css','js/plugins/chosen/chosen.jquery.js','js/plugins/chosen/chosen.js']
                            },

                        ]);
                    }
                }
            })
            .state('ecommerce_checkout.detail', {
                url: "/checkout_detail",
                templateUrl: "ecommerce/checkout/ecommerce_checkout.html",
                data: { pageTitle: 'Checkout' }
            })
    }

    angular.module('moipstore.ecommerce.checkout')
           .config(config)
           .run(function($rootScope, $state) {
               $rootScope.$state = $state;
           });

})();
