/**
 * MoipStore - Responsive Shopping store
 * Separate module for dealing with product | e-commerce
 */

(function () {
    'use strict';

    // factory
    angular.module('moipstore.ecommerce.product')
        .factory('EcommerceProductService', EcommerceProductService);

    EcommerceProductService.$inject = ['$http'];

    function EcommerceProductService($http) {

        var service = {
            findProducts:findProducts,
        };
        return service;

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
                //TODO - Implement looger
            }
        }
    }

})();
