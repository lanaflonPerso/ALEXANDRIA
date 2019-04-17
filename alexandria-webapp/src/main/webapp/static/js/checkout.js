// Example starter JavaScript for disabling form submissions if there are invalid fields
(function() {
    'use strict';
    window.addEventListener('load', function() {
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.getElementsByClassName('needs-validation');
        // Loop over them and prevent submission
        var validation = Array.prototype.filter.call(forms, function(form) {
            form.addEventListener('submit', function(event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    }, false);
})();

function showData() {
    if(document.getElementById("deliveryAddress").style.display === "block") {
        document.getElementById("deliveryAddress").style.display="none";

    } else {
        document.getElementById("deliveryAddress").style.display = "block";
    }
}

function FillBilling() {
    if(document.getElementById("same-address").checked === true) {
        document.getElementById("addressByDeliveryAddressId.addressLine1").value = document.getElementById("addressByInvoiceAddressId.addressLine1").value;
        document.getElementById("addressByDeliveryAddressId.addressLine2").value = document.getElementById("addressByInvoiceAddressId.addressLine2").value;
        document.getElementById("countryDelivery").value = document.getElementById("countryInvoice").value;
        document.getElementById("addressByDeliveryAddressId.city").value = document.getElementById("addressByInvoiceAddressId.city").value;
        document.getElementById("addressByDeliveryAddressId.state").value = document.getElementById("addressByInvoiceAddressId.state").value;
        document.getElementById("addressByDeliveryAddressId.postalCode").value = document.getElementById("addressByInvoiceAddressId.postalCode").value;
    }
}