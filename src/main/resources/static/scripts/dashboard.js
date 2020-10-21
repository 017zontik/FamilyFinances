/* globals Chart:false, feather:false */


(function () {
    'use strict'

    feather.replace();

    $(function () {
        $("#accountsList li a").eq(0).click();
    })

    function addAccount() {
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
    }


    $("#saveAccount").click(function () {
        if(!$newAccount){
            editAccount();
            $newAccount = true;
        }else{
            addAccount();
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


    function addTransaction() {
        if ((transactionType != null) && ($("#newTransaction")[0].reportValidity())) {
            $.ajax("addTransaction", {
                type: "POST",
                data: {
                    date: transactionDate,
                    name: $("#transactionName").val(),
                    amount: $("#amount").val(),
                    accountId: $(".highlight-account").attr("data"),
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
    }

    function updateTransaction() {
        $.ajax("/transactions/" + $transactionId, {
            type: "PUT",
            data: {
                id: $transactionId,
                date: transactionDate,
                name: $("#transactionName").val(),
                amount: $("#amount").val(),
                accountId: $(".highlight-account").attr("data"),
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
    }

    $("#saveTransaction").click(function () {
        if(!$newTransaction){
            updateTransaction();
            $newTransaction = true;
        }else{
            addTransaction();
        }
    })

    $("#transactionModal").on("show.bs.modal", function (ev) {
        if($(ev.target).is("#transactionModal")) {
            $("#typeTransactionError").hide();
            transactionType = null;
            $("#dropdownMenuButton").text("Type of transaction");
            $("#transactionName").val(null);
            $("#amount").val(null);
        }
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
                        $row.attr("type", value.transactionType);
                        let $dateColumn = $("<td>").text(value.date);
                        $row.append($dateColumn);
                        let $nameColumn = $("<td>").text(value.name);
                        $row.append($nameColumn);
                        let $amountColumn = $("<td>").text((value.amount).toFixed(2));
                        if (value.amount < 0 || value.transactionType === "EXPENSE") {
                            $amountColumn.addClass("negative-balance");
                        } else {
                            $amountColumn.addClass("positive-balance");
                        }
                        $row.append($amountColumn);
                        let $editTransaction = $("<td>").append($("<a type='button' " +
                            "class='btn d-flex align-items-center text-muted ' edit-transaction" +
                            " href='#'>")
                            .append($("<span data-feather='edit'>")));
                        $row.append($editTransaction);
                        let $deleteTransaction = $("<td>").append($("<a type='button' " +
                            "class='btn d-flex align-items-center text-muted ' delete-transaction" +
                            " href='#'>")
                            .append($("<span data-feather='trash-2'>")));
                        $row.append($deleteTransaction);
                        $("table tbody", $transactionsElement).append($row);
                    })
                    feather.replace();
                    $("[delete-transaction]").click(function (deleteTransactionEvent) {
                        $transactionId = $(deleteTransactionEvent.target.closest("tr")).attr("id");
                        $("#deleteTransaction").modal("show");

                    })
                    $("[edit-transaction]").click(editTransaction);
                }

            }
        })
    }

   let $newTransaction = true;

    function editTransaction(editTransactionEvent) {
        $transactionId = $(editTransactionEvent.target.closest("tr")).attr("id");
        $.ajax("/transactions/" + $transactionId, {
            type: "GET",
            data: {id: $transactionId},
            statusCode: {
                200: function (response) {
                    $("#transactionModal").modal("show");
                    $("#typeTransactionError").hide();
                    $("#dropdownMenuButton").text($("[data-transaction-type='" + response.transactionType +"']").text());
                    transactionType = response.transactionType;
                    $("#dateTransaction").val(response.date);
                    $("#transactionName").val(response.name);
                    if(response.transactionType=== "EXPENSE"){
                        $("#amount").val(response.amount*(-1));
                    }else{
                        $("#amount").val(response.amount);
                    }
                    $newTransaction = false;
                },
                404: function (response) {
                    $("#transactionError").modal("show");
                    $("#transactionErrorMessage").text(response.responseText);
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
                    $("a div div strong",".highlight-account" ).text(response.name);
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
        $.ajax("/transactions/" + $transactionId, {
            type: "DELETE",
            data: {id: $transactionId},
            statusCode: {
                200: function () {
                    updateTransactions($(".highlight-account").attr("data"));
                    updateAccount($(".highlight-account").attr("data"));
                },
                404: function (response) {
                    $("#transactionError").modal("show");
                    $("#transactionErrorMessage").text(response.responseText);
                }
            }
        })
    })

    $("svg.feather.feather-file-text").click(function () {
        $("#accountModal").modal("show");
    })

    function editAccount() {
        $.ajax("account", {
            type: "GET",
            data: {id: $(".highlight-account").attr("data")},
            statusCode: {
               200: function (response) {
                   $("#name").val(response.name);
                   $newAccount = false;
                   $.ajax("account/" + response.id, {
                       type: "PUT",
                       data: {
                           id: response.id,
                           name: $("#name").val(),
                           balance: response.balance
                       },
                       statusCode: {
                           200: function (response) {
                               $("#accountModal").modal("hide");
                               updateAccount($(".highlight-account").attr("data"));
                           }
                       }
                   })
               }
            }
        })
    }
    let $newAccount = true;

}())


