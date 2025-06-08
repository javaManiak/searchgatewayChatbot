<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@  taglib uri="http://www.springframework.org/tags/form" prefix="f" %>

<%--<meta charset="ISO-8859-1">--%>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"
        integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>--%>
<%--<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css">--%>

<!DOCTYPE html>

<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Role Form</title>

    <style type="text/css">
        .myCss {
            color: red;
            font-family: courier;
            font-size: 120%
        }
    </style>

</head>
<body>

<%--<div align="center">--%>
<%--    <jsp:include page="common.jsp"></jsp:include>--%>
<%--</div>--%>

<br/> <br/>
<div align="center">
    <h3>Amenity Entry Form</h3>
    <f:form action="saveAmenity" method="POST" modelAttribute="amenity">
        <table align="center" width="50%" cellspacing="1px" style="border-spacing: 2px">

            <!-- the following code snippet will put the input errors on top of the input form -->
                <%--<c:if test="${errors}">--%>
                <%--<tr>--%>
                <%--<td>Errors:</td>--%>
                <%--<td><f:errors path="*" cssClass="myCss"/></td>--%>
                <%--</tr>--%>
                <%--</c:if>--%>

            <tr align = "center">
                <td>Amenity Id: <f:input path="amenityId" value="${a.getAmenityId()}" size="50%"/></td>
                <td><f:errors path="amenityId" cssClass="myCss"/></td>
            </tr>

            <tr align="center">
                <td>Name:
                    <f:input path="amenityName" value="${a.getAmenityName()}" size="50%"/></td>
                <td><f:errors path="amenityName" cssClass="myCss"/></td>
            </tr>

            <tr align="center">
                <td colspan="2" align="center"><input type="submit" value="Submit" class="btn btn-primary"/></td>
            </tr>

        </table>
    </f:form>
</div>
<br/><br/>
<div align="center">
    <h4>Amenities</h4>
    <table border="1" align="center" class="table-active table-striped table-hover">
        <tr>
            <th>Amenity Id</th>
            <th>Amenity Name</th>
            <th colspan="2">Actions</th>
        </tr>

        <c:forEach items="${amenities}" var="a">
            <tr>
                <td>${r.getAmenityId()}</td>
                <td>${r.getAmenityName()}</td>

                <td>
<%--                    <sec:authorize access="hasAnyAuthority('Admin')">--%>
                        <a href="${pageContext.request.contextPath}/updateAmenity?amenityId=${a.getAmenityId()}" class="btn btn-warning">update</a>
<%--                    </sec:authorize>--%>
                </td>
                <td>
<%--                    <sec:authorize access="hasAnyAuthority('Admin')">--%>
                        <a href="${pageContext.request.contextPath}/deleteAmenity?amenityId=${a.getAmenityId()}" class="btn btn-danger">delete</a>
<%--                    </sec:authorize>--%>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>