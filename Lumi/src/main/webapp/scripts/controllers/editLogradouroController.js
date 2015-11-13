

angular.module('lumi').controller('EditLogradouroController', function($scope, $routeParams, $location, flash, LogradouroResource , BairroResource) {
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
        };
        var errorCallback = function() {
            flash.setMessage({'type': 'error', 'text': 'The logradouro could not be found.'});
            $location.path("/Logradouros");
        };
        LogradouroResource.get({LogradouroId:$routeParams.LogradouroId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.logradouro);
    };

    $scope.save = function() {
        var successCallback = function(){
            flash.setMessage({'type':'success','text':'Logradouro alterado consucesso.'}, true);
            $scope.get();
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        $scope.logradouro.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Logradouros");
    };

    $scope.remove = function() {
        var successCallback = function() {
            flash.setMessage({'type': 'error', 'text': 'The logradouro was deleted.'});
            $location.path("/Logradouros");
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        }; 
        $scope.logradouro.$remove(successCallback, errorCallback);
    };
    
    $scope.$watch("bairroSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.logradouro.bairro = {};
            $scope.logradouro.bairro.id = selection.value;
        }
    });
    
    $scope.get();
});