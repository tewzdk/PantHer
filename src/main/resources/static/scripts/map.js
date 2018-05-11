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
    };

    //new map
    var map = new google.maps.Map(document.getElementById('map'), options);

    getInitMarkers();

}

//functions for markers
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

function getInitMarkers(){
    //TODO kæmpe arraysammenkobling med database.
    $.getJSON("/fetch-markers", function(fetchedMarkers){
        console.log(fetchedMarkers);
    });
}