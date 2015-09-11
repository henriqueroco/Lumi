
angular.module('lumi').controller('NewEstoqueController', function ($scope, $location, locationParser, EstoqueResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.estoque = $scope.estoque || {};
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Estoques/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        EstoqueResource.save($scope.estoque, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Estoques");
    };
});