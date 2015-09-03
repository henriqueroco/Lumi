

angular.module('lumi').controller('EditProcedimentoController', function($scope, $routeParams, $location, ProcedimentoResource , ProdutoResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.procedimento = new ProcedimentoResource(self.original);
            ProdutoResource.queryAll(function(items) {
                $scope.produtosSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.procedimento.produtos){
                        $.each($scope.procedimento.produtos, function(idx, element) {
                            if(item.id == element.id) {
                                $scope.produtosSelection.push(labelObject);
                                $scope.procedimento.produtos.push(wrappedObject);
                            }
                        });
                        self.original.produtos = $scope.procedimento.produtos;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/Procedimentos");
        };
        ProcedimentoResource.get({ProcedimentoId:$routeParams.ProcedimentoId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.procedimento);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.procedimento.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Procedimentos");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Procedimentos");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.procedimento.$remove(successCallback, errorCallback);
    };
    
    $scope.produtosSelection = $scope.produtosSelection || [];
    $scope.$watch("produtosSelection", function(selection) {
        if (typeof selection != 'undefined' && $scope.procedimento) {
            $scope.procedimento.produtos = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.procedimento.produtos.push(collectionItem);
            });
        }
    });
    
    $scope.get();
});