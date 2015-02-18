<%@include file="header.jspf"%>
<form action="sell" method="post" enctype="multipart/form-data">
	<fieldset>
		<legend>Verkaufen</legend>
		<table>
			<tbody>
				<tr>
					<th><label for="title">Title:</label></th>
					<td><input type="text" name="title" size="40" maxlength="40"
						title="Ein Titel für den Artikel" placeholder="Titel eingeben"
						pattern=".{6,40}" required="required"></td>
				</tr>
				<tr>
					<th><label for="description">Beschreibung:</label></th>
					<td><textarea name="description" rows="10" cols="100"
							maxlength="1000"></textarea></td>
				</tr>
				<tr>
					<th><label for="price">Preis:</label></th>
					<td><input type="number" name="price" size="40" maxlength="40"
						title="Ein Preis für den Artikel" placeholder="Preis eingeben"
						pattern=".{1,40}" required="required"></td>
				</tr>
				<tr>
					<th><label for="foto">Foto:</label></th>
					<td><input type="file" name="foto"></td>
				</tr>
				<tr>
					<td />
					<td><input type="reset"> <input type="submit">
					</td>
				</tr>
			</tbody>
		</table>
	</fieldset>
</form>
<%@include file="footer.jspf"%>