'use strict';

angular
    .module('app.core')
    .controller('CartController', function($scope, $log, products, CartService) {
        var vm = this;
        vm.products = products.data.data.products;
        vm.total = products.data.data.total;

        vm.removeProductFromCart = function(productId) {
        	CartService.removeProductFromCart(productId).then(function() {
        		$log.info("products: " + JSON.stringify(vm.products));
        		for (var key in vm.products) {
        			if (vm.products.hasOwnProperty(key)) {
        				$log.info(key + " -> " + JSON.stringify(vm.products[key]) + " index: ");
        				if (vm.products[key].id === productId) {
        					delete vm.products[key];
        					vm.products.splice(vm.products.indexOf(vm.products[key]), 1);
        				}
        			}
        		}
        	});
        }
    });