'use strict';

angular
    .module('app.core')
    .controller('CustomerController', function($window, $scope, $location, $log, customers) {
        var vm = this;

        vm.customers = customers.data.data;
    });