'use strict';

angular
    .module('app.core')
    .controller('RegisterController', function($scope, $log, $cookies, AccountService) {
        var vm = this;

        vm.user = {
        	'username': null,
        	'password': null,
        	'email': null,
        	'street': null,
        	'city': null
        };
    	vm.register = function() {
    		AccountService.register(vm.user);
    	};
    });

