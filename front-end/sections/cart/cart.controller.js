'use strict';

angular
    .module('app.core')
    .controller('CartController', function($scope, $log, products) {
        var vm = this;
        vm.products = products.data.data.products;
    });