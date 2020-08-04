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
                        location.reload();
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

    let transactionType = null;
    $("#transactionTypeDropDownMenu a").click(function (clickTransactionTypeEvent) {
        let transactionTypeName = $(clickTransactionTypeEvent.target).text();
        $("#dropdownMenuButton").text(transactionTypeName);
        transactionType = $(clickTransactionTypeEvent.target).attr("data-transaction-type");
    })


    let transactionDate = new Date();
    $(function () {
        $('#transactionDateTimePicker input')
            .datetimepicker(
                {
                    autoclose: true,
                    format: "dd MM yyyy - hh:ii"
                }).datetimepicker("update", new Date()).on("changeDate", function (ev) {
            transactionDate = ev.date;
        })

    });


    $("#saveTransaction").click(function () {
        if ((transactionType != null) && ($("#newTransaction")[0].reportValidity())) {
            $.ajax("addTransaction", {
                type: "POST",
                data: {
                    date: transactionDate,
                    name: $("#transactionName").val(),
                    amount: $("#amount").val(),
                    account_id: $(".highlight-account").attr("data"),
                    transactionType: transactionType
                },
                statusCode: {
                    200: function (response) {
                        $("#transactionModal").modal("hide");
                        updateTransactions($(".highlight-account").attr("data"), $("#transactions"));
                        updateAccount($(".highlight-account").attr("data"));
                    }
                }
            })
        } else if (!transactionType) {
            $("#typeTransactionError").text("Select type of transaction").show();
        }
    })

    $("#transactionModal").on("show.bs.modal", function () {
        $("#typeTransactionError").hide();
        transactionType = null;
        $("#dropdownMenuButton").text("Type of transaction");
        $("#transactionName").val(null);
        $("#amount").val(null);
    })

    let $transactionId = 0;
    function updateTransactions(accountId, $transactionsElement) {
        $.ajax("transactions", {
            type: "GET",
            data: {accountId: accountId},
            statusCode: {
                200: function (response) {
                    $("table tbody tr", $transactionsElement).remove();
                    $.each(response, function (index, value) {
                        let $row = $("<tr>");
                        $row.attr("id", value.id);
                        let $dateColumn = $("<td>").text(value.date);
                        $row.append($dateColumn);
                        let $nameColumn = $("<td>").text(value.name);
                        $row.append($nameColumn);
                        let $amountColumn = $("<td>").text((value.amount).toFixed(2));
                        if (value.amount < 0) {
                            $amountColumn.addClass("negative-balance");
                        } else {
                            $amountColumn.addClass("positive-balance");
                        }
                        $row.append($amountColumn);
                        let $deleteTransaction = $("<td>").append($("<a type='button' " +
                            "class='btn d-flex align-items-center text-muted ' delete-transaction" +
                            " href='#'>")
                            .append($("<span data-feather='minus-circle'>")));
                        $row.append($deleteTransaction);

                        $("table tbody", $transactionsElement).append($row);
                    })
                    feather.replace();
                    $("[delete-transaction]").click(function (deleteTransactionEvent) {
                        $transactionId = $(deleteTransactionEvent.target.closest("tr")).attr("id");
                        $("#deleteTransaction").modal("show");
                    })

                }
            }
        })
    }


    function updateAccount(accountId) {
        $.ajax("account", {
            type: "GET",
            data: {id: accountId},
            statusCode: {
                200: function (response) {
                    let accountBalance = $(".account-balance", ".highlight-account");
                    accountBalance.removeClass("text-danger", "text-seccess");
                    if (response.balance < 0) {

                        accountBalance.addClass("text-danger");
                        accountBalance.text((response.balance).toFixed(2) + " BYN");
                    } else {
                        accountBalance.addClass("text-success");
                        accountBalance.text((response.balance).toFixed(2) + " BYN");
                    }


                }
            }
        })
    }



    $("#deleteThisTransaction").click(function () {
        $("#deleteTransaction").modal("hide");
        $.ajax("/deleteTransaction", {
            type: "DELETE",
            data: {id: $transactionId},
            statusCode: {
                200: function () {
                    updateTransactions($(".highlight-account").attr("data"));
                    updateAccount($(".highlight-account").attr("data"));
                }
            }
        })
    })

}())


