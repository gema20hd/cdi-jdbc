<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="es_MX" />

<section id="clientes">
	<div class="container">
		<div class="row">
			<div class="col-md-9">
				<div class="card">
					<div class="card-header">
						<h4>Listado de Personas</h4>
					</div>
					<table border="1" width="100%">
						<tr>
							<td>ID</td>
							<td>NOMBRE</td>
							<td>DIRECCION</td>
							<td>EMAIL</td>
							<td colspan=2>ACCIONES</td>
						</tr>
						<c:forEach var="persona" items="${personas}">
							<tr>
								<td><c:out value="${persona.cedula}" /></td>
								<td><c:out value="${persona.nombre}" /></td>
								<td><c:out value="${persona.direccion}" /></td>
								<td><c:out value="${persona.correoElectronico}" /></td>

								<td><a
									href="${pageContext.request.contextPath}/test?accion=editar&id=${persona.id}"> 
									<iclass="fas fa-angle-double-right"></i> Editar
								</a></td>
								<td><a
									href="${pageContext.request.contextPath}/test?accion=eliminar&id=${persona.id}"> 
									<iclass="fas fa-angle-double-right"></i> Eliminar
								</a></td>


							</tr>
						</c:forEach>
					</table>
				</div>
			</div>


		</div>
	</div>
</section>

<!-- Agregar MODAL -->
<jsp:include page="/WEB-INF/paginas/cliente/agregarPersona.jsp" />
