

angular.module('lumi').controller('EditClienteController', function($scope, $routeParams, $location, flash, ClienteResource , LogradouroResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.cliente = new ClienteResource(self.original);
            LogradouroResource.queryAll(function(items) {
                $scope.logradouroSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.cliente.logradouro && item.id == $scope.cliente.logradouro.id) {
                        $scope.logradouroSelection = labelObject;
                        $scope.cliente.logradouro = wrappedObject;
                        self.original.logradouro = $scope.cliente.logradouro;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            flash.setMessage({'type': 'error', 'text': 'The cliente could not be found.'});
            $location.path("/Clientes");
        };
        ClienteResource.get({ClienteId:$routeParams.ClienteId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.cliente);
    };

    $scope.save = function() {
        var successCallback = function(){
            flash.setMessage({'type':'success','text':'Cliente alterado consucesso.'}, true);
            $scope.get();
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        $scope.cliente.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Clientes");
    };

    $scope.remove = function() {
        var successCallback = function() {
            flash.setMessage({'type': 'error', 'text': 'The cliente was deleted.'});
            $location.path("/Clientes");
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        }; 
        $scope.cliente.$remove(successCallback, errorCallback);
    };
    
    $scope.sexoList = [
        "MASCULINO",  
        "FEMININO"  
    ];
    $scope.situacaoList = [
        "ATIVO",  
        "INATIVO"  
    ];
    $scope.$watch("logradouroSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.cliente.logradouro = {};
            $scope.cliente.logradouro.id = selection.value;
        }
    });
    
    $scope.get();
});