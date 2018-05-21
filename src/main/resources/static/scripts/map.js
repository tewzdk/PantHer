//init map variable
var map;

//init infoWindow variable - in order to only have one window at a time.
var infowindow;

//map styles
var myStyles = [{
        featureType: "poi",
        elementType: "labels",
        stylers: [{ visibility: "off"}]
}];

//map options
var mapOptions = {
    zoom:17,
    disableDefaultUI: true,
    center: {lat: 55.676098, lng: 12.568337},
    styles: myStyles
};
var geoLocationOptions = {
    enableHighAccuracy: true,
    timeout: 5000,
    maximumAge: 0
};

function initMap(){

    //new map
    map = new google.maps.Map(document.getElementById('map'), mapOptions);
    infowindow = new google.maps.InfoWindow();

    function success(pos){
        var currentPosition = {
            lat: pos.coords.latitude,
            lng: pos.coords.longitude
        };
        map.setCenter(currentPosition);
        addDeviceMarker(currentPosition)

        //set hidden inputs for create-marker-form
        document.getElementById('lat-hidden').value = pos.coords.latitude;
        document.getElementById('lng-hidden').value = pos.coords.longitude;

    }
    function error(){
        console.log('unable to find current geolocation');
    }

    navigator.geolocation.getCurrentPosition(success, error, geoLocationOptions);

    getInitMarkers();
}

//functions for markers
function addMarker(props){

    var marker = new google.maps.Marker({
        position: props.coords,
        content: props.content,
        map:map
    });

    google.maps.event.addListener(marker,'click', function(){
        infowindow.setContent(marker.content);
        infowindow.open(map, this);
    });

}
function addDeviceMarker(pos){
    var marker = new google.maps.Marker({
        position: pos,
        map: map,
        icon: 'pictures/userMarker.png'
    });
}

function getInitMarkers(){
    $.getJSON("/fetch-markers", function(fetchedMarkers){
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
                content:
                    '<div class="infoWindow">' +
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
