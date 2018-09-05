<jsp:useBean id="orderDTO" scope="request" class="com.naiden.dto.OrderDTO"/>
<jsp:useBean id="message" scope="request" class="java.lang.String"/>
<jsp:useBean id="messageColor" scope="request" class="java.lang.String"/>
<%@ page import="com.naiden.model.CurrencyEnum" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
            integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm"
            crossorigin="anonymous"></script>
    <script>
        (function () {
            'use strict';

            window.addEventListener('load', function () {
                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                var forms = document.getElementsByClassName('needs-validation');

                // Loop over them and prevent submission
                var validation = Array.prototype.filter.call(forms, function (form) {
                    form.addEventListener('submit', function (event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                            document.getElementById('message').hidden = true;
                        }
                        form.classList.add('was-validated');
                    }, false);
                });
            }, false);
        })();
    </script>
    <meta charset="utf-8">
    <title>test2pay</title>
</head>
<body class="bg-light">

<div class="container">

    <div class="py-5 text-center">
        <h1>Test2Pay</h1>
        <p class="lead">Order ID - only letters and digits</p>
        <p class="lead">Description - any symbols</p>
        <p class="lead">Amount - 5 digit number (max 5000)</p>
        <p class="lead">Currency - select the option</p>
    </div>

    <%if (message.length() > 0) {
            out.print("<div class=\"alert " + messageColor + "\" role=\"alert\" id=\"message\" name=\"message\">\n" +
                    message + "\n" +
                    "</div>");
        }%>

    <form class="bootstrap-form needs-validation" novalidate method="post">

        <div class="mb-3">
            <label for="number">Order ID</label>
            <div class="input-group">
                <input type="text" class="form-control" id="number" pattern="^[A-Za-z0-9]+$" placeholder="Order ID"
                       name="number" value="${orderDTO.number}" required>
                <div class="invalid-feedback" style="width: 100%;">
                    Order ID is required.
                </div>
            </div>
        </div>

        <div class="mb-3">
            <label for="description">Description</label>
            <input type="text" class="form-control" id="description" placeholder="Order description" name="description"
                   value="${orderDTO.description}" required>
            <div class="invalid-feedback">
                Please enter an order description.
            </div>
        </div>

        <div class="row">

            <div class="col-md-8 mb-3">
                <label for="amount">Amount</label>
                <input type="number" step="0.01" max="99999.99" min="0" class="form-control" id="amount"
                       placeholder="00.00" value="${orderDTO.amount}" name="amount" required>
                <div class="invalid-feedback">
                    Invalid amount.
                </div>
            </div>

            <div class="col-md-4 mb-3">
                <label for="currency">Currency</label>
                <select class="custom-select d-block w-100" id="currency" name="currency" required>
                    <option value="">Choose...</option>
                    <%
                        for (CurrencyEnum value : CurrencyEnum.values()) {
                            String selected = value.equals(orderDTO.getCurrency()) ? "selected=\"selected\"" : "";
                            out.print("<option " + selected + ">" + value + "</option>\n");
                        }
                    %>
                </select>
                <div class="invalid-feedback">
                    Please select a valid currency.
                </div>
            </div>

        </div>

        <button class="btn btn-primary btn-lg btn-block" type="submit">Submit</button>
    </form>

</div>

</body>
</html>