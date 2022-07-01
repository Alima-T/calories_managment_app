<%@ page import="topjava.util.DateTimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="resources/css/style.css">
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<br>
<table class="table_left">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Operators</th>
    </tr>
    <c:forEach items="${allMeals}" var="meal">
        <jsp:useBean id="meal" scope="page" type="topjava.model.MealTo"/>
        <tr class="${meal.excess? 'excess':'norm'}">
            <th>
                    <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                    ${fn:replace(meal.dateTime, 'T', ' ')}
            </th>
            <th>${meal.description}</th>
            <th>${meal.calories}</th>
            <th><a href="meals?action=update&id=${meal.id}">Update</a></th>
            <th><a href="meals?action=delete&id=${meal.id}">Delete</a></th>
        </tr>
    </c:forEach>
</table>
</body>
</html>