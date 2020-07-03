<section id="actions" class="py-4 mb-4 bg-light">
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <a href="index.jsp" class="btn btn-ligth btn-block">
                    <i class="fas fa-arrow-left"></i> Regresar al inicio
                </a>
            </div>
            <div class="col-md-3">
                <button type="submit"">
                    <i class="fas fa-check"></i> Guardar Personas
                </button>
            </div>
            <div class="col-md-3">
                <a href="${pageContext.request.contextPath}/test?accion=eliminar&id=${persona.id}">
                    <i class="fas fa-trash"></i> Eliminar Personas
                </a>
            </div>
        </div>
    </div>
</section>