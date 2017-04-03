/**
 * MoipStore - Responsive Shopping store
 * Separate config for dealing with product| e-commerce
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
            .state('ecommerce_cart', {
                abstract: true,
                url: "/ecommerce",
                templateUrl: "views/common/content.html",
                resolve: {
                    loadPlugin: function ($ocLazyLoad) {
                        return $ocLazyLoad.load([
                            {
                                files: ['js/plugins/footable/footable.all.min.js', 'css/plugins/footable/footable.core.css']
                            },
                            {
                                name: 'ui.footable',
                                files: ['js/plugins/footable/angular-footable.js']
                            }
                        ]);
                    }
                }
            })
            .state('ecommerce_cart.detail', {
                url: "/cart_detail",
                templateUrl: "ecommerce/cart/ecommerce_cart.html",
                data: { pageTitle: 'Shopping cart' }
            })
    }

    angular.module('moipstore.ecommerce.cart')
           .config(config)
           .run(function($rootScope, $state) {
               $rootScope.$state = $state;
           });

})();
