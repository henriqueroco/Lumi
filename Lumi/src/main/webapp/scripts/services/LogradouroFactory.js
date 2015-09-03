angular.module('lumi').factory('LogradouroResource', function($resource){
    var resource = $resource('rest/logradouros/:LogradouroId',{LogradouroId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});