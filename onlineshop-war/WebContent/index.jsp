<%@include file="header.jspf"%>
<!-- das ist ein html kommentar -->
<%-- das ist ein jsp kommentar --%>
<%
// der kommentar kommt auch in den quellcode
%>
<p>Eingeloggt: <c:out value="${customer.email }" default="-"></c:out></p>
<%@include file="footer.jspf"%>