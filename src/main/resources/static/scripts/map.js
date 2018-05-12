//functions for google map
var map;

//map styles
var myStyles =[
    {
        featureType: "poi",
        elementType: "labels",
        stylers: [
            { visibility: "off"}
        ]
    }

];

//map options
var mapOptions = {
    zoom:17,
    disableDefaultUI: true,
    center: {lat: 55.676098, lng: 12.568337},
    styles: myStyles
};

function initMap(){
    //new map
    map = new google.maps.Map(document.getElementById('map'), mapOptions);

    getInitMarkers();
}

//functions for markers
function addMarker(props){

    var marker = new google.maps.Marker({
        position: props.coords,
        content: props.content,
        map:map
    });

    if(props.content){
        var infoWindow = new google.maps.InfoWindow({
            content: props.content
        })
    }

    marker.addListener('click', function(){
        infoWindow.open(map, marker);
    });

}
function getInitMarkers(){
    $.getJSON("/fetch-markers", function(fetchedMarkers){
        console.log(fetchedMarkers);
        for(var i = 0; i< fetchedMarkers.length; i++){

            //defining attributes
            var latitude = parseFloat(fetchedMarkers[i].latitude);
            var longitude = parseFloat(fetchedMarkers[i].longitude);
            var profilbilledeSti = fetchedMarkers[i].profilbilledeSti;
            var fornavn = fetchedMarkers[i].fornavn;
            var efternavn = fetchedMarkers[i].efternavn;
            var telefonnummer = fetchedMarkers[i].telefonnummer;
            var pantbilledeSti = fetchedMarkers[i].pantbilledeSti;
            var estimeretBeloeb = fetchedMarkers[i].estimeretBeloeb;

            addMarker({
                coords:{lat: latitude, lng: longitude},
                content:'<div class="infoWindow">' +
                            '<h3>' + fornavn + ' ' + efternavn +'</h3>' +
                            '<img class="profil-billede" src=' + profilbilledeSti + '/>' +
                            '<p>&emsp;' + telefonnummer + '&ensp;<i class="fas fa-phone"></i></p>' +
                            '<hr>' + '<br>' +
                            '<div class="pant-container">' +
                                '<img class="pant-billede" src=' + pantbilledeSti + '/>' +
                                '<h4>Estimeret beløb: ' + estimeretBeloeb + ' kr.</h4>' +
                            '</div>' +
                        '</div>'
            });
        }
    });
}



