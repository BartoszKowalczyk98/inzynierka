<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<link rel="stylesheet" type="text/css" href="css/style.css"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>vCard</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <style type="text/css">
        body{
            font-family: "Arial", Helvetica, sans-serif;
        }
        input{
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            box-sizing: border-box;
        }

    </style>
</head>
<body>
<h2>Podaj dane do wyszukania </h2>

    <form:form method="POST" action="/search" modelAttribute="index" acceptCharset="ISO 8859-16 UTF-8">
    <table>
        <tr>
            <td><form:label path="k">Posada: </form:label></td>
            <td><form:input path="k"/></td>
        </tr>

        <tr>
            <td><form:label path="l">Lokalizacja: </form:label></td>
            <td><form:input path="l"/></td>
        </tr>

        <tr>
            <td><button class="btn btn-success" type="submit">Szukaj</button></td>
        </tr>

    </table>
</form:form>
</body>
</html>
