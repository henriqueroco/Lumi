

angular.module('lumi').controller('EditAtendimentoController', function($scope, $routeParams, $location, flash, AtendimentoResource , ClienteResource, ProcedimentoResource, EsteticistaResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.atendimento = new AtendimentoResource(self.original);
            ClienteResource.queryAll(function(items) {
                $scope.clienteSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.nome
                    };
                    var labelObject = {
                        value : item.nome,
                        text : item.nome
                    };
                    if($scope.atendimento.cliente){
                        $.each($scope.atendimento.cliente, function(idx, element) {
                            if(item.id == element.id) {
                                $scope.clienteSelection.push(labelObject);
                                $scope.atendimento.cliente.push(wrappedObject);
                            }
                        });
                        self.original.cliente = $scope.atendimento.cliente;
                    }
                    return labelObject;
                });
            });
            ProcedimentoResource.queryAll(function(items) {
                $scope.procedimentoSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.nome
                    };
                    var labelObject = {
                        value : item.nome,
                        text : item.nome
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
            EsteticistaResource.queryAll(function(items) {
                $scope.esteticistaSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.nome
                    };
                    var labelObject = {
                        value : item.nome,
                        text : item.nome
                    };
                    if($scope.atendimento.esteticista){
                        $.each($scope.atendimento.esteticista, function(idx, element) {
                            if(item.id == element.id) {
                                $scope.esteticistaSelection.push(labelObject);
                                $scope.atendimento.esteticista.push(wrappedObject);
                            }
                        });
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
    $scope.clienteSelection = $scope.clienteSelection || [];
    $scope.$watch("clienteSelection", function(selection) {
        if (typeof selection != 'undefined' && $scope.atendimento) {
            $scope.atendimento.cliente = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.atendimento.cliente.push(collectionItem);
            });
        }
    });
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
    $scope.esteticistaSelection = $scope.esteticistaSelection || [];
    $scope.$watch("esteticistaSelection", function(selection) {
        if (typeof selection != 'undefined' && $scope.atendimento) {
            $scope.atendimento.esteticista = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.atendimento.esteticista.push(collectionItem);
            });
        }
    });
    
    $scope.get();
});