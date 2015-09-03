

angular.module('lumi').controller('EditLogradouroController', function($scope, $routeParams, $location, LogradouroResource , BairroResource, ClienteResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.logradouro = new LogradouroResource(self.original);
            BairroResource.queryAll(function(items) {
                $scope.bairroSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.logradouro.bairro && item.id == $scope.logradouro.bairro.id) {
                        $scope.bairroSelection = labelObject;
                        $scope.logradouro.bairro = wrappedObject;
                        self.original.bairro = $scope.logradouro.bairro;
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
                    if($scope.logradouro.cliente){
                        $.each($scope.logradouro.cliente, function(idx, element) {
                            if(item.id == element.id) {
                                $scope.clienteSelection.push(labelObject);
                                $scope.logradouro.cliente.push(wrappedObject);
                            }
                        });
                        self.original.cliente = $scope.logradouro.cliente;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/Logradouros");
        };
        LogradouroResource.get({LogradouroId:$routeParams.LogradouroId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.logradouro);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.logradouro.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Logradouros");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Logradouros");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.logradouro.$remove(successCallback, errorCallback);
    };
    
    $scope.$watch("bairroSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.logradouro.bairro = {};
            $scope.logradouro.bairro.id = selection.value;
        }
    });
    $scope.clienteSelection = $scope.clienteSelection || [];
    $scope.$watch("clienteSelection", function(selection) {
        if (typeof selection != 'undefined' && $scope.logradouro) {
            $scope.logradouro.cliente = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.logradouro.cliente.push(collectionItem);
            });
        }
    });
    
    $scope.get();
});