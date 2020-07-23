/* globals Chart:false, feather:false */

(function () {
    'use strict'

    feather.replace();

    $("#saveAccount").click(function () {
        if ($("#newAccount")[0].reportValidity()) {
            $.ajax("addAccount", {
                type: "POST",
                data: {name: $("#name").val()},
                statusCode: {
                    200: function (response) {
                        $("#accountModal").modal("hide");
                        let $accountClone = $("#accountTemplate").clone();
                        $accountClone.attr("data", response.id);
                        $("a div div strong", $accountClone).text(response.name);
                        $(".text-success strong", $accountClone).text("0,00 BYN");
                        $(".text-danger", $accountClone).hide();
                        $("#accountsList").append($accountClone);
                        $accountClone.show();
                    },
                    400: function (response) {
                        $("#accountErrorMessage").text(response.responseText).show();
                    },
                },
            });
        }

    });
    $("#accountModal").on("show.bs.modal", function () {
        $("#accountErrorMessage").hide();
        $("#name").val("");

    });

    $("#accountsList").click(function (clickAccountEvent) {
        $(".highlight-account").removeClass("highlight-account");
        let $account = $(clickAccountEvent.target.closest("li")).addClass("highlight-account");
        let accountName = $("div > strong", $account).text();
        $("main div h1").text(accountName);
        let $transactionsClone = $("#transactionsTemplate").clone();
        $("#transactions").remove();
        $transactionsClone.attr("id", "transactions");
        $("main").append($transactionsClone);
        $.ajax("transactions", {
            type: "GET",
            data: {accountId: $account.attr("data")},
            statusCode: {
                200: function (response) {
                    $.each(response, function (index, value) {
                        let $row = $("<tr>");
                        let $dateColumn = $("<td>").text(value.date);
                        $row.append($dateColumn);
                        let $nameColumn = $("<td>").text(value.name);
                        $row.append($nameColumn);
                        let $amountColumn = $("<td>").text((value.amount).toFixed(2));
                        $row.append($amountColumn);
                        $("table tbody", $transactionsClone).append($row);
                    })
                }
            }
        })
        $transactionsClone.show();
    })


    $("#transactionTypeDropDownMenu a").click(function (clickTransactionTypeEvent) {
        let transactionType=$(clickTransactionTypeEvent.target).text();
        $("#dropdownMenuButton").text(transactionType);
    })

    $(function(){
        $('#transactionDateTimePicker input')
            .datetimepicker(
                {
                    autoclose: true,
                    format: "dd MM yyyy - hh:ii"
                }).datetimepicker("update", new Date());
    });


}())


