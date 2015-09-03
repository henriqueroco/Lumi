angular.module('lumi').factory('BairroResource', function($resource){
    var resource = $resource('rest/bairros/:BairroId',{BairroId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});