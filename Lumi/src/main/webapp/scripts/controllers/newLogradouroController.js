
angular.module('lumi').controller('NewLogradouroController', function ($scope, $location, locationParser, flash, LogradouroResource , BairroResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.logradouro = $scope.logradouro || {};
    
    $scope.bairroList = BairroResource.queryAll(function(items){
        $scope.bairroSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("bairroSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.logradouro.bairro = {};
            $scope.logradouro.bairro.id = selection.value;
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            flash.setMessage({'type':'success','text':'The logradouro was created successfully.'});
            $location.path('/Logradouros');
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        LogradouroResource.save($scope.logradouro, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Logradouros");
    };
});