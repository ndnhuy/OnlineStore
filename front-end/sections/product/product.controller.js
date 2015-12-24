'use strict';

angular
    .module('app.core')
    .controller('ProductController', function($scope, $log, products, ProductService, AuthService, $cookies) {
        var vm = this;

        vm.products = products.data.data;

        vm.addProductIntoCart = function(productId) {
        	ProductService.addProductIntoCart(productId);
        };
    });

