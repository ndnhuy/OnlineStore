'use strict';

angular
    .module('app.core')
    .controller('AccountController', function($window, $scope, $log, user, AccountService) {
        var vm = this;
        
        if (user != null)
       		 vm.user = user.data.data;
       		
        vm.message = "";
        $log.info("USER: " + JSON.stringify(vm.user));

        vm.askToResetPassword = function() {
        	AccountService.askToResetPassword(vm.passwordResetEmail).then(function(data) {
        		if (data.data.status === 200) {
        			alert(data.data.message);
                     $window.location.href = '#/changePassword';
        		}
        	});
        }

        vm.resetPassword = function() {
            AccountService.resetPassword(vm.resetPasswordToken, vm.newPassword).then(function(data) {
                if (data.data.status === 200) {
                    vm.passwordChangedMessage = data.data.message;
                }
            })
        }

    });