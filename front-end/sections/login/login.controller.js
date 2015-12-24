'use strict';

angular
    .module('app.core')
    .controller('LoginController', function($scope, $log, AuthService, $cookies) {
        var vm = this;

        vm.login = function() {
            AuthService.validateAccount(vm.username, vm.password).then(function(response) {

                if (response.status == 200) {
                    $log.info("Login with username " + vm.username);
                    $cookies.put('access-token', 'Basic ' + response.data.token);
                }
                else if (response.status == 401) {

                }
            });
        }
    });