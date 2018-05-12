//functions for google map
var map;

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
    };

    //new map
    map = new google.maps.Map(document.getElementById('map'), options);

    getInitMarkers();
    addMarker({
        coords:{lat: 55.676098, lng: 12.568337},
        content:"s"
    });
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
        for(var i = 0; i< fetchedMarkers.length; i++){

            var latitude = parseFloat(fetchedMarkers[i].latitude);
            var longitude = parseFloat(fetchedMarkers[i].longitude);

            addMarker({
                coords:{lat: latitude, lng: longitude},
                content:'<h4>Estimeret beløb:</h4><p>' +
                        fetchedMarkers[i].pant.estimeretBeloeb + ' kr.</p>'
            });
        }
    });
}



