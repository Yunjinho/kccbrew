document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("address_kakao").addEventListener("click", function () {
        new daum.Postcode({
            oncomplete: function (data) {
                document.getElementById("address_kakao").value = data.address;
                document.querySelector("input[name=storeAddrDtl]").focus();
                updateLatLng();
            }
        }).open();
    });

    var mallsEnabled = false;

    function update_selected() {
        var selectedValue = $("#location").val();
        mallsEnabled = (selectedValue === "02");
        if (mallsEnabled) {
            $("#locationSeoul").prop('disabled', false);
        } else {
            $("#locationSeoul").prop('disabled', true);
        }
    }

    $(function () {
        update_selected();

        $("#location").change(function () {
            update_selected();
        });
    });

    function updateLatLng() {
        var address = document.getElementById("address_kakao").value;
        var geocoder = new google.maps.Geocoder();
        geocoder.geocode({
            'address': address
        }, function (results, status) {
            if (status === google.maps.GeocoderStatus.OK) {
                var lat = results[0].geometry.location.lat();
                var lng = results[0].geometry.location.lng();
                document.getElementById("lat").value = lat;
                document.getElementById("lng").value = lng;
            } else {
                console.error('Geocode was not successful for the following reason: ' + status);
            }
        });
    }



});