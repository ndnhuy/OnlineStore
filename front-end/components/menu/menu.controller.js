'use strict';
angular
    .module('app.core')
    .controller('MenuController', function($scope, $window, $log, $cookies, User, AccountService) {
        //Setup the view model object
        var vm = this;
       

        $log.info("MenuController User.username is " + User.username);

        if ($cookies.get('access-token') != null && User.username == null) {
        	AccountService.getAccount();
        }

        vm.user = User;

        vm.logout = function() {
        	AccountService.logout();
        	vm.user = null;
        	$window.location.href = '#/';
        }
    });