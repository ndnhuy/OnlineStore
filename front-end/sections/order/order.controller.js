'use strict';

angular
    .module('app.core')
    .controller('OrderController', function($window, $scope, $location, $log, orders, user) {
        var vm = this;

        vm.orders = orders.data.data;
        vm.user = user.data.data;
        $log.info("OrderController - user: " + JSON.stringify(vm.user));
    });