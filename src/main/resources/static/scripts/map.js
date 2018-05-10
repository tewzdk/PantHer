//functions for google map
function initMap(){
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
    var options = {
        zoom:17,
        disableDefaultUI: true,
        center: {lat: 55.676098, lng: 12.568337},
        styles: myStyles
    }

    //new map
    var map = new google.maps.Map(document.getElementById('map'), options);

    getInitMarkers(map);
    placeInitMarkers(initMarkers, map)

}

//functions for markers
var initMarkers = [];

function addMarker(props){
    var marker = new google.maps.Marker({
        position: props.coords,
        content: props.content,
        map: props.map
    });

    if(props.content){
        var infoWindow = new google.maps.InfoWindow({
            content: props.content
        })
    }

    marker.addListener('click', function(){
        infoWindow.open(props.map, marker);
    });
}

function getInitMarkers(map){
    //TODO kæmpe arraysammenkobling med database.
    initMarkers[0] = {
        coords:{lat: 55.676098, lng: 12.568337},
        content:'<h4>Estimeret beløb:</h4>' +
        '<p>30 kr.</p>' +
        '<h4>Adresse:</h4> ' +
        '<p>Rådhuspladsen 2</p>',
        map:map
    }
}
function placeInitMarkers(initMarkers, map){
    for(var i = 0;i < initMarkers.length; i++){
        addMarker(initMarkers[i]);
    }
}