
angular.module('lumi').controller('NewBairroController', function ($scope, $location, locationParser, flash, BairroResource , LocalidadeResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.bairro = $scope.bairro || {};
    
    $scope.localidadeList = LocalidadeResource.queryAll(function(items){
        $scope.localidadeSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("localidadeSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.bairro.localidade = {};
            $scope.bairro.localidade.id = selection.value;
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            flash.setMessage({'type':'success','text':'The bairro was created successfully.'});
            $location.path('/Bairros');
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        BairroResource.save($scope.bairro, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Bairros");
    };
});