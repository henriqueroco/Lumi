

angular.module('lumi').controller('EditBairroController', function($scope, $routeParams, $location, BairroResource , LocalidadeResource, LogradouroResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.bairro = new BairroResource(self.original);
            LocalidadeResource.queryAll(function(items) {
                $scope.localidadeSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.bairro.localidade && item.id == $scope.bairro.localidade.id) {
                        $scope.localidadeSelection = labelObject;
                        $scope.bairro.localidade = wrappedObject;
                        self.original.localidade = $scope.bairro.localidade;
                    }
                    return labelObject;
                });
            });
            LogradouroResource.queryAll(function(items) {
                $scope.logradouroSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.bairro.logradouro){
                        $.each($scope.bairro.logradouro, function(idx, element) {
                            if(item.id == element.id) {
                                $scope.logradouroSelection.push(labelObject);
                                $scope.bairro.logradouro.push(wrappedObject);
                            }
                        });
                        self.original.logradouro = $scope.bairro.logradouro;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/Bairros");
        };
        BairroResource.get({BairroId:$routeParams.BairroId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.bairro);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.bairro.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Bairros");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Bairros");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.bairro.$remove(successCallback, errorCallback);
    };
    
    $scope.$watch("localidadeSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.bairro.localidade = {};
            $scope.bairro.localidade.id = selection.value;
        }
    });
    $scope.logradouroSelection = $scope.logradouroSelection || [];
    $scope.$watch("logradouroSelection", function(selection) {
        if (typeof selection != 'undefined' && $scope.bairro) {
            $scope.bairro.logradouro = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.bairro.logradouro.push(collectionItem);
            });
        }
    });
    
    $scope.get();
});