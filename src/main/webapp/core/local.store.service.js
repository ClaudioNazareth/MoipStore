/**
 * MoipStore - Responsive Shopping store
 * Separate module for dealing with localStorage | e-commerce
 * Store data on localStore fo browser
 * The script was develop following this guide of best practices https://github.com/johnpapa/angular-styleguide/blob/master/a1/README.md
 */

(function () {
    'use strict';

    // factory
    angular.module('moipstore')
        .factory('LocalStorageService', LocalStorageService);

    LocalStorageService.$inject = ['$window'];

    function LocalStorageService($window) {

        var service = {
            storeData:storeData,
            getStoredData : getStoredData,
            cleanData : cleanData
        };
        return service;

        ////////////////

        function storeData(key, data) {
            $window.localStorage[key] = angular.toJson(data);
        }

        function getStoredData(key) {
            return angular.fromJson($window.localStorage[key]);
        }

        function cleanData(key) {
            $window.localStorage[key] = angular.toJson([]);
        }
    }

})();