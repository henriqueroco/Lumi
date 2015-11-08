
angular.module('lumi').controller('NewMovimentoEstoqueController', function ($scope, $location, locationParser, flash, MovimentoEstoqueResource , ProdutoResource, AtendimentoResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.movimentoEstoque = $scope.movimentoEstoque || {};
    
    $scope.tipoMovimentoList = [
        "ENTRADA",
        "SAIDA"
    ];
    
    $scope.unidadeMedidaMovimentacaoList = [
        "KG",
        "G",
        "MG",
        "UND",
        "L",
        "ML"
    ];
    
    $scope.produtoList = ProdutoResource.queryAll(function(items){
        $scope.produtoSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("produtoSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.movimentoEstoque.produto = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.movimentoEstoque.produto.push(collectionItem);
            });
        }
    });

    $scope.atendimentoList = AtendimentoResource.queryAll(function(items){
        $scope.atendimentoSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("atendimentoSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.movimentoEstoque.atendimento = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.movimentoEstoque.atendimento.push(collectionItem);
            });
        }
    });


    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            flash.setMessage({'type':'success','text':'The movimentoEstoque was created successfully.'});
            $location.path('/MovimentoEstoques');
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        MovimentoEstoqueResource.save($scope.movimentoEstoque, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/MovimentoEstoques");
    };
});