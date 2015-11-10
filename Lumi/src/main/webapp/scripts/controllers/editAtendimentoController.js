

angular.module('lumi').controller('EditAtendimentoController', function($scope, $routeParams, $location, flash, AtendimentoResource , ProcedimentoResource, ClienteResource, EsteticistaResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.atendimento = new AtendimentoResource(self.original);
            ProcedimentoResource.queryAll(function(items) {
                $scope.procedimentoSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.atendimento.procedimento){
                        $.each($scope.atendimento.procedimento, function(idx, element) {
                            if(item.id == element.id) {
                                $scope.procedimentoSelection.push(labelObject);
                                $scope.atendimento.procedimento.push(wrappedObject);
                            }
                        });
                        self.original.procedimento = $scope.atendimento.procedimento;
                    }
                    return labelObject;
                });
            });
            ClienteResource.queryAll(function(items) {
                $scope.clienteSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.atendimento.cliente && item.id == $scope.atendimento.cliente.id) {
                        $scope.clienteSelection = labelObject;
                        $scope.atendimento.cliente = wrappedObject;
                        self.original.cliente = $scope.atendimento.cliente;
                    }
                    return labelObject;
                });
            });
            EsteticistaResource.queryAll(function(items) {
                $scope.esteticistaSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.atendimento.esteticista && item.id == $scope.atendimento.esteticista.id) {
                        $scope.esteticistaSelection = labelObject;
                        $scope.atendimento.esteticista = wrappedObject;
                        self.original.esteticista = $scope.atendimento.esteticista;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            flash.setMessage({'type': 'error', 'text': 'The atendimento could not be found.'});
            $location.path("/Atendimentos");
        };
        AtendimentoResource.get({AtendimentoId:$routeParams.AtendimentoId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.atendimento);
    };

    $scope.save = function() {
        var successCallback = function(){
            flash.setMessage({'type':'success','text':'The atendimento was updated successfully.'}, true);
            $scope.get();
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        $scope.atendimento.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Atendimentos");
    };

    $scope.remove = function() {
        var successCallback = function() {
            flash.setMessage({'type': 'error', 'text': 'The atendimento was deleted.'});
            $location.path("/Atendimentos");
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        }; 
        $scope.atendimento.$remove(successCallback, errorCallback);
    };
    
    $scope.situacaoList = [
        "MARCADO",  
        "CONFIRMADO",  
        "REALIZADO",  
        "NAOREALIZADO",  
        "CANCELADO"  
    ];
    $scope.procedimentoSelection = $scope.procedimentoSelection || [];
    $scope.$watch("procedimentoSelection", function(selection) {
        if (typeof selection != 'undefined' && $scope.atendimento) {
            $scope.atendimento.procedimento = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.atendimento.procedimento.push(collectionItem);
            });
        }
    });
    $scope.$watch("clienteSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.atendimento.cliente = {};
            $scope.atendimento.cliente.id = selection.value;
        }
    });
    $scope.$watch("esteticistaSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.atendimento.esteticista = {};
            $scope.atendimento.esteticista.id = selection.value;
        }
    });
    
    $scope.get();
});