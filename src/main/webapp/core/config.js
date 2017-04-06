/**
 * MoipStore - Responsive Shopping store
 *
 * MoipStore theme use AngularUI Router to manage routing and views
 * Each view are defined as state.
 *
 */
function config($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {
    $urlRouterProvider.otherwise("ecommerce/product_details");

    $ocLazyLoadProvider.config({
        // Set to true if you want to see what and when is dynamically loaded
        debug: false
    });
}
angular
    .module('moipstore')
    .config(config)
    .run(function($rootScope, $state) {
        $rootScope.$state = $state;
    });
