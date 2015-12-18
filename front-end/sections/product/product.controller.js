'use strict';

angular
    .module('app.core')
    .controller('ProductController', function($scope, $log, products) {
        var vm = this;

        vm.products = products.data.data;
    });

