
angular.module('lumi').controller('NewBairroController', function ($scope, $location, locationParser, BairroResource , LocalidadeResource, LogradouroResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.bairro = $scope.bairro || {};
    
    $scope.localidadeList = LocalidadeResource.queryAll(function(items){
        $scope.localidadeSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("localidadeSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.bairro.localidade = {};
            $scope.bairro.localidade.id = selection.value;
        }
    });
    
    $scope.logradouroList = LogradouroResource.queryAll(function(items){
        $scope.logradouroSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("logradouroSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.bairro.logradouro = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.bairro.logradouro.push(collectionItem);
            });
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Bairros/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        BairroResource.save($scope.bairro, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Bairros");
    };
});