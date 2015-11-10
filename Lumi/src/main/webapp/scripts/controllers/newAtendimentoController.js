
angular.module('lumi').controller('NewAtendimentoController', function ($scope, $location, locationParser, flash, AtendimentoResource , ProcedimentoResource, ClienteResource, EsteticistaResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.atendimento = $scope.atendimento || {};
    
    $scope.situacaoList = [
        "MARCADO",
        "CONFIRMADO",
        "REALIZADO",
        "NAOREALIZADO",
        "CANCELADO"
    ];
    
    $scope.procedimentoList = ProcedimentoResource.queryAll(function(items){
        $scope.procedimentoSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("procedimentoSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.atendimento.procedimento = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.atendimento.procedimento.push(collectionItem);
            });
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
        if ( typeof selection != 'undefined') {
            $scope.atendimento.cliente = {};
            $scope.atendimento.cliente.id = selection.value;
        }
    });
    
    $scope.esteticistaList = EsteticistaResource.queryAll(function(items){
        $scope.esteticistaSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("esteticistaSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.atendimento.esteticista = {};
            $scope.atendimento.esteticista.id = selection.value;
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            flash.setMessage({'type':'success','text':'The atendimento was created successfully.'});
            $location.path('/Atendimentos');
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        AtendimentoResource.save($scope.atendimento, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Atendimentos");
    };
});