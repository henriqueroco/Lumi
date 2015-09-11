angular.module('lumi').factory('MovimentoEstoqueResource', function($resource){
    var resource = $resource('rest/movimentoestoques/:MovimentoEstoqueId',{MovimentoEstoqueId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});