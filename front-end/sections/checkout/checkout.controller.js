'use strict';

angular
    .module('app.core')
    .controller('CheckoutController', function($window, $scope, $location, $log, products, CartService) {
        var vm = this;

        vm.products = products.data.data.products;
        vm.total = products.data.data.total;



        vm.checkout = function() {
             //$window.location.href = '#/cart';
            CartService.checkout(vm.order).then(function(data) {
                $log.info("Checkout data response: " + JSON.stringify(data.data));
                if (data.data.status == 201) {
                    $window.location.href = '#';
                }
                else {
                    alert("Checkout fail. Please try again");
                }
            });

        }
        vm.removeProductFromCart = function(productId) {
            CartService.removeProductFromCart(productId).then(function() {
                CartService.getProductsInCart()
                    .then(function(data) {
                        vm.products = data.data.data.products;
                        vm.total = data.data.data.total;
                    });
            });
        }
    });