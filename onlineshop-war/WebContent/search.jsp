<%@include file="header.jspf"%>
<article>
	<section>
		<form action="buy" method="post">
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
<%@include file="footer.jspf"%>