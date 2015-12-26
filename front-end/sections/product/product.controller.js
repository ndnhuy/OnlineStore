'use strict';

angular
    .module('app.core')
    .controller('ProductController', function($scope, $log, products, CartService, AuthService, $cookies) {
        var vm = this;

        vm.products = products.data.data;

        vm.addProductIntoCart = function(productId) {
        	CartService.addProductIntoCart(productId);
        };
    });

