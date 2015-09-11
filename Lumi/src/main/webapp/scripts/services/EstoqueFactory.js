angular.module('lumi').factory('EstoqueResource', function($resource){
    var resource = $resource('rest/estoques/:EstoqueId',{EstoqueId:'@produto'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});