/* Written by: Max Møller Hoffmeyer */

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




