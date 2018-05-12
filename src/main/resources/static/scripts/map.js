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
            console.log(fetchedMarkers);
            var latitude = parseFloat(fetchedMarkers[i].latitude);
            var longitude = parseFloat(fetchedMarkers[i].longitude);

            addMarker({
                coords:{lat: latitude, lng: longitude},
                content:'<img src=' + fetchedMarkers[i].profilbilledeSti + '/>' +
                        '<h3>' + fetchedMarkers[i].fornavn + ' ' + fetchedMarkers[i].efternavn +'</h3>' +
                        
                        '<h4>Estimeret beløb:</h4><p>' +
                        fetchedMarkers[i].estimeretBeloeb + ' kr.</p>'
            });
        }
    });
}



