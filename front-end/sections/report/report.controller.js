'use strict';

angular
    .module('app.core')
    .controller('ReportController', function($window, $http, $sce, $scope, $location, $log, CustomerService) {
        var vm = this;

    	vm.getReport = function() {
    		window.location.href = "http://localhost:8183/report/buyer?min=" + vm.min;
    	}
    });