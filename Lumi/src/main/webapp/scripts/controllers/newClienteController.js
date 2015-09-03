
angular.module('lumi').controller('NewClienteController', function ($scope, $location, locationParser, ClienteResource , LogradouroResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.cliente = $scope.cliente || {};
    
    $scope.sexoList = [
        "MASCULINO",
        "FEMININO"
    ];
    
    $scope.situacaoList = [
        "ATIVO",
        "INATIVO"
    ];
    
    $scope.logradouroList = LogradouroResource.queryAll(function(items){
        $scope.logradouroSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("logradouroSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.cliente.logradouro = {};
            $scope.cliente.logradouro.id = selection.value;
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Clientes/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        ClienteResource.save($scope.cliente, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Clientes");
    };
});