'use strict';

angular
    .module('app.core')
    .controller('ProductDetailController', function($scope, $log, CartService, product) {
        var vm = this;

        vm.product = product.data.data;
        vm.addToCart = function() {
        	CartService.addProductIntoCart(vm.product.id).then(function(data) {
        		if (data.data.status === 201) {
        			alert("The " + vm.product.name + " added to cart");
        		}
        	});
        }
    });

