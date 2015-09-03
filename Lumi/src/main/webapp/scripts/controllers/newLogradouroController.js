
angular.module('lumi').controller('NewLogradouroController', function ($scope, $location, locationParser, LogradouroResource , BairroResource, ClienteResource) {
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
    
    $scope.clienteList = ClienteResource.queryAll(function(items){
        $scope.clienteSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("clienteSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.logradouro.cliente = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.logradouro.cliente.push(collectionItem);
            });
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Logradouros/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        LogradouroResource.save($scope.logradouro, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Logradouros");
    };
});