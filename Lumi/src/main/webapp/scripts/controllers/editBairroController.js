

angular.module('lumi').controller('EditBairroController', function($scope, $routeParams, $location, flash, BairroResource , LocalidadeResource) {
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
        };
        var errorCallback = function() {
            flash.setMessage({'type': 'error', 'text': 'The bairro could not be found.'});
            $location.path("/Bairros");
        };
        BairroResource.get({BairroId:$routeParams.BairroId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.bairro);
    };

    $scope.save = function() {
        var successCallback = function(){
            flash.setMessage({'type':'success','text':'The bairro was updated successfully.'}, true);
            $scope.get();
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        $scope.bairro.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Bairros");
    };

    $scope.remove = function() {
        var successCallback = function() {
            flash.setMessage({'type': 'error', 'text': 'The bairro was deleted.'});
            $location.path("/Bairros");
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        }; 
        $scope.bairro.$remove(successCallback, errorCallback);
    };
    
    $scope.$watch("localidadeSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.bairro.localidade = {};
            $scope.bairro.localidade.id = selection.value;
        }
    });
    
    $scope.get();
});