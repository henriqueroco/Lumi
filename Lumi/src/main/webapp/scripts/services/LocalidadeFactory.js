angular.module('lumi').factory('LocalidadeResource', function($resource){
    var resource = $resource('rest/localidades/:LocalidadeId',{LocalidadeId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});