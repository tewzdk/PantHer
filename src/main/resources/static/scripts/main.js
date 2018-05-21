//navbar
//menu toggle-button
$(document).ready(function(){
    $(".menu-icon").on("click", function(){
        $("nav ul").toggleClass("showing");
    });
});

//scrolling effect
$(window).on("scroll", function(){
    if($(window).scrollTop()){
        $('nav').addClass('solid');
    }
    else {
        $('nav').removeClass('solid');
    }
});

//navbar
if($('#login-registered').length){  // return's true if element is present
    // show or hide another div
    $('#no-login-registered').hide();
}

//modals
//declared variables
var createUserModal = document.getElementById('create-user-modal');
var createUserBtn = document.getElementById('create-user-btn');
var createUserCloseBtn = document.getElementById('create-user-close-btn');

var loginModal = document.getElementById('login-modal');
var loginBtn = document.getElementById('login-btn');
var loginCloseBtn = document.getElementById('login-close-btn');

var createMarkerModal = document.getElementById('create-marker-modal');
var createMarkerBtn = document.getElementById('create-marker-btn');
var createMarkerCloseBtn = document.getElementById('create-marker-close-btn');

//eventlisteners
createUserBtn.addEventListener('click', openCreateUserModal);
createUserCloseBtn.addEventListener('click', closeCreateUserModal);
loginBtn.addEventListener('click', openLoginModal);
loginCloseBtn.addEventListener('click', closeLoginModal);
createMarkerBtn.addEventListener('click', openCreateMarkerModal);
createMarkerCloseBtn.addEventListener('click', closeCreateMarkerModal);
window.addEventListener('click', outsideClick);

//functions for createusermodal
function openCreateUserModal(){
    createUserModal.style.display = 'block';
}
function closeCreateUserModal(){
    createUserModal.style.display = 'none';
}

//sammenligner de to password-inputs under opret bruger
function check(input) {
    if (input.value != document.getElementById('password').value) {
        input.setCustomValidity('De to kodeord skal være identiske.');
    } else {
        // input is valid -- reset the error message
        input.setCustomValidity('');
    }
}

//functions for loginmodal
function openLoginModal(){
    loginModal.style.display = 'block';
}

function closeLoginModal(){
    loginModal.style.display = 'none';
}

//functions for markermodal
function openCreateMarkerModal(){
    createMarkerModal.style.display = 'block';
}

function closeCreateMarkerModal(){
    createMarkerModal.style.display = 'none';
}

function outsideClick(e){
    if(e.target === createUserModal){
        createUserModal.style.display = 'none';
    }

    if(e.target === loginModal){
        loginModal.style.display = 'none';
    }

    if(e.target === createMarkerModal){
        createMarkerModal.style.display = 'none';
    }

}




