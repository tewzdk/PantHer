/* Written by: Max Møller Hoffmeyer */

//map control modals
var createMarkerModal = document.getElementById('create-marker-modal');
var createMarkerBtn = document.getElementById('create-marker-btn');
var createMarkerCloseBtn = document.getElementById('create-marker-close-btn');

//eventlisteners
createMarkerBtn.addEventListener('click', openCreateMarkerModal);
createMarkerCloseBtn.addEventListener('click', closeCreateMarkerModal);
window.addEventListener('click', outsideClick);

//functions for markermodal
function openCreateMarkerModal(){
    createMarkerModal.style.display = 'block';
}

function closeCreateMarkerModal(){
    createMarkerModal.style.display = 'none';
}

function outsideClick(e){
    if(e.target === createMarkerModal){
        createMarkerModal.style.display = 'none';
    }
}