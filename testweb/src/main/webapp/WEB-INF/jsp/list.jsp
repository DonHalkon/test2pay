<%@ page contentType="text/html;charset=UTF-8" import="java.util.List" %>
<%@ page import="com.naiden.model.EOrder" %>
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

        <script type="text/javascript" language="javascript" src="//code.jquery.com/jquery-1.12.4.js"></script>
        <script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>

    <meta charset="utf-8">

    <title>test2pay</title>

    <script>
        $(document).ready(function() {
            $('#table').DataTable();
        });
    </script>

</head>
<body class="bg-light">


<div class="container">

    <div class="py-5 text-center">
        <h1>Test2Pay</h1>
        <p class="lead">Table with search</p>
    </div>

    <div class="content">
        <table class="table table-striped table-bordered" cellspacing="0" width="100%" id="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Number</th>
                <th>Description</th>
                <th>Amount</th>
                <th>Currency</th>
            </tr>
            </thead>
            <tbody>

            <%
                List<EOrder> orders = (List<EOrder>) request.getAttribute("orders");
                for (EOrder order : orders) {
                    out.println("<tr>" +
                    		"<td>"+order.getId()+"</td>" +
                    		"<td>"+order.getNumber()+"</td>" +
                    		"<td>"+order.getDescription()+"</td>" +
                    		"<td>"+order.getAmount()+"</td>" +
                    		"<td>"+order.getCurrency()+"</td>" +
                            "</tr>"
                    );
                }
            %>

            </tbody>
        </table>
    </div>





</div>
</body>
</html>