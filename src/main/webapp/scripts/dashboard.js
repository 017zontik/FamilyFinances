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
        updateTransactions($account.attr("data"), $transactionsClone);
        $transactionsClone.show();
        $("#buttonAddTransaction").show();
    })


    $("#transactionTypeDropDownMenu a").click(function (clickTransactionTypeEvent) {
        console.log("dropdownMenu");
        let transactionType = $(clickTransactionTypeEvent.target).text();
        $("#dropdownMenuButton").text(transactionType);
    })

    let transactionDate=new Date();
    $(function () {
        $('#transactionDateTimePicker input')
            .datetimepicker(
                {
                    autoclose: true,
                    format: "dd MM yyyy - hh:ii"
                }).datetimepicker("update", new Date()).on("changeDate", function (ev) {
                    transactionDate=ev.date;
        })

    });



    $("#saveTransaction").click(function () {
        if ($("#newTransaction")[0].reportValidity()) {
        $.ajax("addTransaction", {
            type: "POST",
            data: {
                date: transactionDate,
                name: $("#nameTransaction").val(),
                amount: $("#amount").val(),
                account_id: $(".highlight-account").attr("data")
            },
            statusCode: {
                200: function (response) {
                    $("#transactionModal").modal("hide");
                    updateTransactions($(".highlight-account").attr("data"), $("#transactions"));


                }
            }
        })
        }
    })
    function updateTransactions(accountId, $transactionsElement) {
        $.ajax("transactions", {
            type: "GET",
            data: {accountId: accountId},
            statusCode: {
                200: function (response) {
                    $("table tbody tr", $transactionsElement).remove();
                    $.each(response, function (index, value) {
                        let $row = $("<tr>");
                        let $dateColumn = $("<td>").text(value.date);
                        $row.append($dateColumn);
                        let $nameColumn = $("<td>").text(value.name);
                        $row.append($nameColumn);
                        let $amountColumn = $("<td>").text((value.amount).toFixed(2));
                        $row.append($amountColumn);
                        $("table tbody", $transactionsElement).append($row);
                    })
                }
            }
        })
    }

}())


