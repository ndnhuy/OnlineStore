'use strict';

angular
    .module('app.core')
    .controller('OrderManageController', function($window, $scope, $location, $log, OrderService, orders, customer) {
        var vm = this;

        vm.orders = orders.data.data;
        vm.customer = customer.data.data;
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