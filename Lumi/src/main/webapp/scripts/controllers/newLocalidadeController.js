
angular.module('lumi').controller('NewLocalidadeController', function ($scope, $location, locationParser, flash, LocalidadeResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.localidade = $scope.localidade || {};
    
    $scope.ufList = [
        "AC",
        "AL",
        "AP",
        "AM",
        "BA",
        "CE",
        "DF",
        "ES",
        "GO",
        "MA",
        "MT",
        "MS",
        "MG",
        "PA",
        "PB",
        "PR",
        "PE",
        "PI",
        "RJ",
        "RN",
        "RS",
        "RO",
        "RR",
        "SC",
        "SP",
        "SE",
        "TO",
        "EX"
    ];
    
    $scope.paisList = [
        "AFEGANISTAO",
        "AFRICADOSUL",
        "AKROTIRI",
        "ALBANIA",
        "ALEMANHA",
        "ANDORRA",
        "ANGOLA",
        "ANGUILA",
        "ANTARCTIDA",
        "ANTIGUAEBARBUDA",
        "ANTILHASNEERLANDESAS",
        "ARABIASAUDITA",
        "ARCTICOCEAN",
        "ARGELIA",
        "ARGENTINA",
        "ARMENIA",
        "ARUBA",
        "ASHMOREANDCARTIERISLANDS",
        "ATLANTICOCEAN",
        "AUSTRALIA",
        "AUSTRIA",
        "AZERBAIJAO",
        "BAAMAS",
        "BANGLADECHE",
        "BARBADOS",
        "BAREM",
        "BELGICA",
        "BELIZE",
        "BENIM",
        "BERMUDAS",
        "BIELORRUSSIA",
        "BIRMANIA",
        "BOLIVIA",
        "BOSNIAEHERZEGOVINA",
        "BOTSUANA",
        "BRASIL",
        "BRUNEI",
        "BULGARIA",
        "BURQUINAFASO",
        "BURUNDI",
        "BUTAO",
        "CABOVERDE",
        "CAMAROES",
        "CAMBOJA",
        "CANADA",
        "CATAR",
        "CAZAQUISTAO",
        "CHADE",
        "CHILE",
        "CHINA",
        "CHIPRE",
        "CLIPPERTONISLAND",
        "COLOMBIA",
        "COMORES",
        "CONGOBRAZZAVILLE",
        "CONGOKINSHASA",
        "CORALSEAISLANDS",
        "COREIADONORTE",
        "COREIADOSUL",
        "COSTADOMARFIM",
        "COSTARICA",
        "CROACIA",
        "CUBA",
        "DHEKELIA",
        "DINAMARCA",
        "DOMINICA",
        "EGIPTO",
        "EMIRATOSARABESUNIDOS",
        "EQUADOR",
        "ERITREIA",
        "ESLOVAQUIA",
        "ESLOVENIA",
        "ESPANHA",
        "ESTADOSUNIDOS",
        "ESTONIA",
        "ETIOPIA",
        "FAROE",
        "FIJI",
        "FILIPINAS",
        "FINLANDIA",
        "FRANCA",
        "GABAO",
        "GAMBIA",
        "GANA",
        "GAZASTRIP",
        "GEORGIA",
        "GEORGIADOSULESANDWICHDOSUL",
        "GIBRALTAR",
        "GRANADA",
        "GRECIA",
        "GRONELANDIA",
        "GUAME",
        "GUATEMALA",
        "GUERNSEY",
        "GUIANA",
        "GUINE",
        "GUINEEQUATORIAL",
        "GUINEBISSAU",
        "HAITI",
        "HONDURAS",
        "HONGKONG",
        "HUNGRIA",
        "IEMEN",
        "ILHABOUVET",
        "ILHADONATAL",
        "ILHANORFOLK",
        "ILHASCAIMAO",
        "ILHASCOOK",
        "ILHASDOSCOCOS",
        "ILHASFALKLAND",
        "ILHASHEARDEMCDONALD",
        "ILHASMARSHALL",
        "ILHASSALOMAO",
        "ILHASTURCASECAICOS",
        "ILHASVIRGENSAMERICANAS",
        "ILHASVIRGENSBRITANICAS",
        "INDIA",
        "INDIANOCEAN",
        "INDONESIA",
        "IRA",
        "IRAQUE",
        "IRLANDA",
        "ISLANDIA",
        "ISRAEL",
        "ITALIA",
        "JAMAICA",
        "JANMAYEN",
        "JAPAO",
        "JERSEY",
        "JIBUTI",
        "JORDANIA",
        "KUWAIT",
        "LAOS",
        "LESOTO",
        "LETONIA",
        "LIBANO",
        "LIBERIA",
        "LIBIA",
        "LISTENSTAINE",
        "LITUANIA",
        "LUXEMBURGO",
        "MACAU",
        "MACEDONIA",
        "MADAGASCAR",
        "MALASIA",
        "MALAVI",
        "MALDIVAS",
        "MALI",
        "MALTA",
        "MANISLEOF",
        "MARIANASDONORTE",
        "MARROCOS",
        "MAURICIA",
        "MAURITANIA",
        "MAYOTTE",
        "MEXICO",
        "MICRONESIA",
        "MOCAMBIQUE",
        "MOLDAVIA",
        "MONACO",
        "MONGOLIA",
        "MONSERRATE",
        "MONTENEGRO",
        "MUNDO",
        "NAMIBIA",
        "NAURU",
        "NAVASSAISLAND",
        "NEPAL",
        "NICARAGUA",
        "NIGER",
        "NIGERIA",
        "NIUE",
        "NORUEGA",
        "NOVACALEDONIA",
        "NOVAZELANDIA",
        "OMA",
        "PACIFICOCEAN",
        "PAISESBAIXOS",
        "PALAU",
        "PANAMA",
        "PAPUANOVAGUINE",
        "PAQUISTAO",
        "PARACELISLANDS",
        "PARAGUAI",
        "PERU",
        "PITCAIRN",
        "POLINESIAFRANCESA",
        "POLONIA",
        "PORTORICO",
        "PORTUGAL",
        "QUENIA",
        "QUIRGUIZISTAO",
        "QUIRIBATI",
        "REINOUNIDO",
        "REPUBLICACENTROAFRICANA",
        "REPUBLICACHECA",
        "REPUBLICADOMINICANA",
        "ROMENIA",
        "RUANDA",
        "RUSSIA",
        "SALVADOR",
        "SAMOA",
        "SAMOAAMERICANA",
        "SANTAHELENA",
        "SANTALUCIA",
        "SAOCRISTOVAOENEVES",
        "SAOMARINHO",
        "SAOPEDROEMIQUELON",
        "SAOTOMEEPRINCIPE",
        "SAOVICENTEEGRANADINAS",
        "SARAOCIDENTAL",
        "SEICHELES",
        "SENEGAL",
        "SERRALEOA",
        "SERVIA",
        "SINGAPURA",
        "SIRIA",
        "SOMALIA",
        "SOUTHERNOCEAN",
        "SPRATLYISLANDS",
        "SRILANCA",
        "SUAZILANDIA",
        "SUDAO",
        "SUECIA",
        "SUICA",
        "SURINAME",
        "SVALBARDEJANMAYEN",
        "TAILANDIA",
        "TAIWAN",
        "TAJIQUISTAO",
        "TANZANIA",
        "TERRITORIOBRITANICODOOCEANOINDICO",
        "TERRITORIOSAUSTRAISFRANCESES",
        "TIMORLESTE",
        "TOGO",
        "TOKELAU",
        "TONGA",
        "TRINDADEETOBAGO",
        "TUNISIA",
        "TURQUEMENISTAO",
        "TURQUIA",
        "TUVALU",
        "UCRANIA",
        "UGANDA",
        "UNIAOEUROPEIA",
        "URUGUAI",
        "USBEQUISTAO",
        "VANUATU",
        "VATICANO",
        "VENEZUELA",
        "VIETNAME",
        "WAKEISLAND",
        "WALLISEFUTUNA",
        "WESTBANK",
        "ZAMBIA",
        "ZIMBABUE"
    ];
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            flash.setMessage({'type':'success','text':'The localidade was created successfully.'});
            $location.path('/Localidades');
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        LocalidadeResource.save($scope.localidade, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Localidades");
    };
});