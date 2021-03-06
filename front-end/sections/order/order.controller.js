'use strict';

angular
    .module('app.core')
    .controller('OrderController', function($window, $scope, $location, $log, OrderService, orders, user) {
        var vm = this;

        vm.orders = orders.data.data;
        vm.user = user.data.data;
        vm.deleteOrder = function(id) {
            OrderService.deleteOrder(id).then(function(data) {
                if (data.data.status === 200) {
                    OrderService.getOrders().then(function(data) {
                        vm.orders = data.data.data;
                    });
                }
            });
        }
    });