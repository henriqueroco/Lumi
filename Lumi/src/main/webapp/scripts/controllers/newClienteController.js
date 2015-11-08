
angular.module('lumi').controller('NewClienteController', function ($scope, $location, locationParser, flash, ClienteResource , LogradouroResource) {
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
                value : item.descricaoLogradouro+'-'+item.bairro.localidade.nomeCidade,
                text : item.descricaoLogradouro+'-'+item.bairro.localidade.nomeCidade
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
            flash.setMessage({'type':'success','text':'The cliente was created successfully.'});
            $location.path('/Clientes');
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        ClienteResource.save($scope.cliente, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Clientes");
    };
});