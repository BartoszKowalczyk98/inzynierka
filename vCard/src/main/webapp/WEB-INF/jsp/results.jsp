<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<link rel="stylesheet" type="text/css" href="css/style.css"/>

<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>Wyszukane oferty</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <style type="text/css">
        body {
            font-family: "Arial", Helvetica, sans-serif;
        }

        input {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            box-sizing: border-box;
        }

    </style>
</head>
<body>

<table class="table">
    <c:forEach items="${listOfOffers}" var="offer" >
        <form:form method="POST" action="/generate-vcard" modelAttribute="vcardinfo"
                   acceptCharset="ISO 8859-16 UTF-8">
            <tr>
                <td>Nazwa:</td>
                <td>${offer.getName()}</td>
                <td><form:input type="hidden" path="name" value="${offer.getName()}"/></td>
            </tr>
            <tr>
                <td>Telefon:</td>
                <td>${offer.getTelephone()}</td>
                <td><form:input type="hidden" path="telephone" value="${offer.getTelephone()}"/></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td>${offer.getEmail()}</td>
                <td><form:input type="hidden" path="email" value="${offer.getEmail()}"/></td>
            </tr>
            <tr>
                <td>Link:</td>
                <td>${offer.getSameAs()}</td>
                <td><form:input type="hidden" path="url" value="${offer.getSameAs()}"/></td>
            </tr>
            <tr>
                <td>Adres:</td>
                <td>${offer.getAddress().toString()}</td>
                <td><form:input type="hidden" path="addressAsString" value="${offer.getAddress()}"/></td>
            </tr>
            <tr>
                <td>
                    <button type="submit">Generuj vCard</button>
                </td>
            </tr>
        </form:form>

    </c:forEach>
</table>

</body>
</html>
