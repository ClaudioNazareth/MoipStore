/**
 * MoipStore - Responsive Shopping store
 * Separate config for dealing with product| e-commerce
 */

(function () {
    'use strict';

    function config($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {

        $urlRouterProvider.otherwise("commerce/product_details");

        $ocLazyLoadProvider.config({
            // Set to true if you want to see what and when is dynamically loaded
            debug: false
        });
        $stateProvider
            .state('ecommerce', {
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
            .state('ecommerce.product_details', {
                url: "/product_details",
                templateUrl: "ecommerce/product/ecommerce_product_details.html",
                data: { pageTitle: 'E-commerce Product detail' },
                resolve: {
                    loadPlugin: function ($ocLazyLoad) {
                        return $ocLazyLoad.load([
                            {
                                files: ['css/plugins/slick/slick.css','css/plugins/slick/slick-theme.css','js/plugins/slick/slick.min.js']
                            },
                            {
                                name: 'slick',
                                files: ['js/plugins/slick/angular-slick.min.js']
                            }
                        ]);
                    }
                }
            })

    }

    angular.module('moipstore.ecommerce')
        .config(config)
        .run(function($rootScope, $state) {
            $rootScope.$state = $state;
        });

})();
