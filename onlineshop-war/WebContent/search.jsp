<%@include file="header.jspf"%>
<article>
	<section>
		<form action="search" method="post">
			<fieldset>
				<legend>Suchen</legend>
				<table>
					<tbody>
						<tr>
							<th><label for="search">Suche:</label></th>
							<td><input type="text" name="search" size="40"
								maxlength="40" title="Suchtext"
								placeholder="Suchentext eingeben"></td>
							<td><input type="reset"> <input type="submit">
							</td>
						</tr>
					</tbody>
				</table>
			</fieldset>
		</form>
	</section>
</article>
<c:forEach var="item" items="${items}">
	<article>
		<section>
			<form action="buy" method="post">
				<fieldset>
					<legend>ID: ${item.id}</legend>
					<h2>${item.title }</h2>
					<p>${item.description }</p>
					<p>Preis: ${item.price } Euro</p>
					<c:if test="${not empty customer }">
						<c:choose>
							<c:when test="${empty item.traded }">
								<input type="hidden" name="item_id" value="${item.id }">
								<input type="submit" value="Kaufen">
							</c:when>
							<c:otherwise>
								<b>Verkauft am ${item.traded } an ${item.buyerId }</b>
							</c:otherwise>
						</c:choose> 
					</c:if> 
					<aside><p><img src="foto?id=${item.id }" /></p></aside>
				</fieldset>
			</form>
		</section>
	</article>
</c:forEach>
<%@include file="footer.jspf"%>