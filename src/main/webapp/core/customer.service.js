/**
 * MoipStore - Responsive Shopping store
 * Separate module for dealing with Customer | e-commerce
 * The script was develop following this guide of best practices https://github.com/johnpapa/angular-styleguide/blob/master/a1/README.md
 */

(function () {
    'use strict';

    // factory
    angular.module('moipstore')
        .factory('CustomerService', CustomerService);

    CustomerService.$inject = ['$http', '$log'];

    function CustomerService($http, $log) {

        var customer = null;

        var service = {
            getCustomer: getCustomer,
            findCostumerById: findCostumerById
        };
        return service;

        ////////////////

        /**
         * Find a Customer bu it's Id
         * @returns Customer
         * @see {br.com.moipstore.model.Customer}
         */
        function findCostumerById(custumerId){
           var req = {
                method: 'GET',
                url: '/api/v1/customers/'+custumerId,
                headers: {
                    'Content-Type': 'application/json'
                }
            };

            return $http(req)
                .then(returnSuccess)
                .catch(returnError);

            function returnSuccess(response) {
                return response.data;
            }
            function returnError(error) {
                $log.error("There was an error fetching the Customer");
                return error;
            }
        }

        function getCustomer() {
            if(customer == null){
                var costumerId = 'CUST-1000';    //<-- ONLY FOR DEMO PURPOSE
                customer = findCostumerById(costumerId);
            }
            return customer;
        }
    }

})();
