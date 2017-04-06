/**
 * MoipStore - Responsive Shopping store
 * Separate module for dealing with product | e-commerce
 * The script was develop following this guide of best practices https://github.com/johnpapa/angular-styleguide/blob/master/a1/README.md
 */

(function () {
    'use strict';

    angular.module('moipstore.ecommerce.product')
        .factory('EcommerceProductService', EcommerceProductService);

    EcommerceProductService.$inject = ['$http', '$log'];

    function EcommerceProductService($http, $log) {

        var service = {
            findProducts:findProducts
        };
        return service;

        ////////////

        /**
         * Find all products in data base and return
         * Obs: I don't use pagination for this example, but is it's already configured to do it!
         * @returns List of Product
         * @see {br.com.moipstore.model.Product}
         */
        function findProducts(){
            var req = {
                method: 'GET',
                url: '/api/products',
                headers: {
                    'Content-Type': 'application/json'
                }
            };

            return $http(req)
                .then(returnSuccess)
                .catch(returnError);

            function returnSuccess(response) {
                return response.data._embedded.products;
            }
            function returnError(error) {
                $log.error("There was an error fetching the products");
                return error;
            }
        }
    }

})();
