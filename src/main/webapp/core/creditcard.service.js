/**
 * MoipStore - Responsive Shopping store
 * Separate module for dealing with CredCard Validation
 * The script was develop following this guide of best practices https://github.com/johnpapa/angular-styleguide/blob/master/a1/README.md
 */

(function () {
    'use strict';

    // factory
    angular.module('moipstore')
        .factory('CreditCardService', CreditCardService);

    function CreditCardService() {

        var cc = null;

        var PLUBLIC_KEY = 'MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoBttaXwRoI1Fbcond5mS7QOb7X2lykY5hvvDeLJelvFhpeLnS4YDwkrnziM3W00UNH1yiSDU+3JhfHu5G387O6uN9rIHXvL+TRzkVfa5iIjG+ap2N0/toPzy5ekpgxBicjtyPHEgoU6dRzdszEF4'
            +'ItimGk5ACx/lMOvctncS5j3uWBaTPwyn0hshmtDwClf6dEZgQvm/dNaIkxHKV+9jMn3ZfK/liT8A3xwaVvRzzuxf09xJTXrAd9v5VQbeWGxwFcW05oJulSFjmJA9HcmbDYHJT+sG2mlZDEruCGAzCVubJwGY1aRlcs9AQc1jIm/l8JwH7le2kpk3QoX+gz0wWwIDAQAB'

        var service = {
            isCreditCardValid:isCreditCardValid,
            getCreditCardHash: getCreditCardHash
        };
        return service;


        function isCreditCardValid(card) {

            cc = new Moip.CreditCard({
                number  : card.number,
                cvc     : card.CVC,
                expMonth: card.expirationMonth,
                expYear : card.expirationYear,
                pubKey  : PLUBLIC_KEY
            });
            return cc.isValid();
        }

        function getCreditCardHash(){
            if(cc != null){
                return cc.hash();
            }else{
                alert("The CreditCard must be validated first");
            }
        }
    }

})();
