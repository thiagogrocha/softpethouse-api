package br.com.softpethouse.api.account.resource;

import br.com.softpethouse.api.Resources;
import br.com.softpethouse.api.account.dto.TypeAccountDto;
import br.com.softpethouse.api.account.entity.AccountEntity;
import br.com.softpethouse.api.account.service.TypeAccountService;
import br.com.softpethouse.api.commom.validation.ResponseError;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(Resources.TYPEACCOUNT)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TypeAccountResource {

    private TypeAccountService service;

    @Inject
    public TypeAccountResource(TypeAccountService service) {
        this.service = service;
    }

    @POST
    @Operation(summary = "Cria Tipo de Conta", description = "Um Conta pode ser do tipo Cliente, Consultor ou Administrador")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Criado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = AccountEntity.class))),
            @APIResponse(responseCode = "422", description = "Campos obrigatórios não informados",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ResponseError.class))),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response post(TypeAccountDto dto) {
        try {
            return service.save(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).entity(e).build();
        }
    }

}
