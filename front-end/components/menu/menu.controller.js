'use strict';
angular
    .module('app.core')
    .controller('MenuController', function($scope, User, AccountService) {
        //Setup the view model object
        var vm = this;
        vm.user = User;

        if (User.username == null) {
        	AccountService.getAccount();
        }
    });