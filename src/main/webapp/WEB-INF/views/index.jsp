<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <title>Accident</title>
</head>
<body>
<div class="container pt-3">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <div class="row">
                    <div class="col-md-6">Accidents
                    </div>
                    <div class="col-md-6 text-right text-info">Login as ${user.username}</div>
                </div>
            </div>
            <div class="card-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Name</th>
                        <th scope="col">Text</th>
                        <th scope="col">Address</th>
                        <th scope="col">Rules</th>
                        <th scope="col">Type</th>
                        <th scope="col">Edit</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="accident" items="${accidents}">
                        <tr>
                            <td>
                                <c:out value="${accident.id}"/>
                            </td>
                            <td>
                                <c:out value="${accident.name}"/>
                            </td>
                            <td>
                                <c:out value="${accident.description}"/>
                            </td>
                            <td>
                                <c:out value="${accident.address}"/>
                            </td>
                            <td>
                                <c:forEach var="rule" items="${accident.ruleSet}">
                                    <c:out value="${rule.name} "/>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="type" items="${types}">
                                    <c:if test="${type.id == accident.type.id}">
                                        <c:out value="${type.name}"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <a href="<c:url value='/edit?id=${accident.id}'/>">
                                    <i class="fa fa-edit mr-3"></i>
                                </a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <a class="btn btn-primary" href="<c:url value='/create'/>" role="button">Добавить инцидент</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
