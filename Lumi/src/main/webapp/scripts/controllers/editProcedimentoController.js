

angular.module('lumi').controller('EditProcedimentoController', function($scope, $routeParams, $location, flash, ProcedimentoResource , ProdutoResource) {
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
            flash.setMessage({'type': 'error', 'text': 'The procedimento could not be found.'});
            $location.path("/Procedimentos");
        };
        ProcedimentoResource.get({ProcedimentoId:$routeParams.ProcedimentoId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.procedimento);
    };

    $scope.save = function() {
        var successCallback = function(){
            flash.setMessage({'type':'success','text':'Procedimento alterado consucesso.'}, true);
            $scope.get();
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        $scope.procedimento.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Procedimentos");
    };

    $scope.remove = function() {
        var successCallback = function() {
            flash.setMessage({'type': 'error', 'text': 'The procedimento was deleted.'});
            $location.path("/Procedimentos");
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
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