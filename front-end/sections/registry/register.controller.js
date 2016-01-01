'use strict';

angular
    .module('app.core')
    .controller('RegisterController', function($window, $scope, $log, $cookies, AccountService) {
        var vm = this;

        vm.user = {
        	'username': null,
        	'password': null,
        	'email': null,
        	'street': null,
        	'city': null
        };

        vm.confirmSucceed = false;

    	vm.register = function() {
    		AccountService.register(vm.user).then(function(data) {
    			if (data.data.status == 201) {
    				$window.location.href = '#/registerConfirm';
    			}
    		});
    	};

        vm.confirmRegister = function() {
            AccountService.confirmRegistration(vm.confirmToken).then(function(data) {
                if (data.data.status === 200) {
                    vm.confirmSucceed = true;
                }
            });
        }
    });

