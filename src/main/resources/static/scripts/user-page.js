//edit user
var editUserButton = document.getElementById('edit-user');
var editUserText = document.getElementById('edit-user-text');
var editArrow = document.getElementById('edit-arrow');
var editUserContent = document.getElementById('edit-user-form-content');



editUserButton.onclick = function(){
        //lukker content der ikke er i brug
        if(createAddressContent.className == "show"){
            createAddressContent.className = "";
            addressArrow.style.visibility = "hidden";
            createAddressText.style.visibility = "visible";
        }
        if(editUserContent.className == "show"){
            editUserContent.className = "";
            editArrow.style.visibility = "hidden";
            editUserText.style.visibility = "visible";

        } else {
            editUserContent.className = "show";
            editArrow.style.visibility = "visible";
            editUserText.style.visibility = "hidden";
        }
};

//create address
var createAddressButton = document.getElementById('create-address');
var createAddressText = document.getElementById('create-address-text');
var addressArrow = document.getElementById('address-arrow');
var createAddressContent = document.getElementById('create-address-form-content');

createAddressButton.onclick = function(){
    //lukker content der ikke er i brug
    if(editUserContent.className == "show"){
        editUserContent.className = "";
        editArrow.style.visibility = "hidden";
        editUserText.style.visibility = "visible";
        }
    if(createAddressContent.className == "show"){
        createAddressContent.className = "";
        addressArrow.style.visibility = "hidden";
        createAddressText.style.visibility = "visible";
    } else {
        createAddressContent.className = "show";
        addressArrow.style.visibility = "visible";
        createAddressText.style.visibility = "hidden";
    }

};




