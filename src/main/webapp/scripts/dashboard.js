/* globals Chart:false, feather:false */

(function () {
    'use strict'

    feather.replace();

    $("#saveAccount").click(function () {
        if ( $("#newAccount")[0].reportValidity()){
            $.post("addAccount", {name: $("#name").val()}, function (response) {
                if (response.result === "OK") {
                    $("#accountModal").modal("hide");
                    let $accountClone = $("#accountTemplate").clone();
                    $("a div div strong", $accountClone).text(response.accountModel.name);
                    $(".text-success strong", $accountClone).text("0,00 BYN");
                    $(".text-danger", $accountClone).hide();
                    $("#accountsList").append($accountClone);
                    $accountClone.show();
                } else {
                    $("#accountErrorMessage").text(response.message).show();
                }
            });
        }

    });
    $("#accountModal").on("show.bs.modal", function () {
        $("#accountErrorMessage").hide();
        $("#name").val("");

    })


}())


