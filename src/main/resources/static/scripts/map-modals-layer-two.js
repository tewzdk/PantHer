/* Written by: Max Møller Hoffmeyer */

//map control modals layer two
if($('#user-markers').length){  // return's true if element is present
    // show or hide another div
    //hide opret pant if user has marker already (this can probably be abused by evil hackers)
    $('#no-user-markers').hide();

    var editMarkerModal = document.getElementById('edit-marker-modal');
    var editMarkerBtn = document.getElementById('edit-marker-btn');
    var editMarkerCloseBtn = document.getElementById('edit-marker-close-btn');

    var deleteMarkerModal = document.getElementById('delete-marker-modal');
    var deleteMarkerBtn = document.getElementById('delete-marker-btn');
    var deleteMarkerCloseBtn = document.getElementById('delete-marker-close-btn');

    //eventlisteners
    editMarkerBtn.addEventListener('click', openEditMarkerModal);
    editMarkerCloseBtn.addEventListener('click', closeEditMarkerModal);
    deleteMarkerBtn.addEventListener('click', openDeleteMarkerModal);
    deleteMarkerCloseBtn.addEventListener('click', closeDeleteMarkerModal);
    window.addEventListener('click', outsideClick);

    //functions for markermodal
    function openEditMarkerModal(){
        editMarkerModal.style.display = 'block';
        console.debug("hertil open open")
    }

    function closeEditMarkerModal(){
        editMarkerModal.style.display = 'none';
    }

    function openDeleteMarkerModal(){
        deleteMarkerModal.style.display = 'block';
        console.debug("hertil open open")
    }

    function closeDeleteMarkerModal(){
        deleteMarkerModal.style.display = 'none';
    }

    function outsideClick(e) {
        if (e.target === deleteMarkerModal) {
            deleteMarkerModal.style.display = 'none';
        }

        if (e.target === editMarkerModal){
            editMarkerModal.style.display = 'none';
        }
    }
}