'use strict';

angular
    .module('app.core')
    .controller('CartController', function($scope, $log, products, CartService) {
        var vm = this;
        vm.products = products.data.data.products;
        vm.total = products.data.data.total;

        vm.removeProductFromCart = function(productId) {
        	CartService.removeProductFromCart(productId).then(function() {
                CartService.getProductsInCart()
                    .then(function(data) {
                        vm.products = data.data.data.products;
                        vm.total = data.data.data.total;
                    });
        	});
        }

        vm.updateQuantityOfProduct = function(quantity, productId) {
            CartService.updateQuantityOfProduct(quantity, productId).then(function(data) {
                if (data.data.status === 200) {
                    CartService.getProductsInCart()
                        .then(function(data) {
                            vm.products = data.data.data.products;
                            vm.total = data.data.data.total;
                        });
                }
            });
        }
    });
