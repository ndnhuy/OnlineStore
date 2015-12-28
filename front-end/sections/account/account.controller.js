'use strict';

angular
    .module('app.core')
    .controller('AccountController', function($scope, $log, user) {
        var vm = this;
        
        vm.user = user.data.data;
        $log.info("USER: " + JSON.stringify(vm.user));
    });