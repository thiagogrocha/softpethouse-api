package br.com.softpethouse.api.account.resource;

import br.com.softpethouse.api.Resources;
import br.com.softpethouse.api.account.dto.AccountDtoCreate;
import br.com.softpethouse.api.account.dto.AccountDtoOut;
import br.com.softpethouse.api.account.dto.AccountDtoUpdate;
import br.com.softpethouse.api.account.service.AccountService;
import br.com.softpethouse.api.commom.validation.ResponseError;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(Resources.ACCOUNT)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {

    private AccountService service;

    @Inject
    public AccountResource(AccountService service) {
        this.service = service;
    }

    @POST
    @Operation(summary = "Criar", description = "Cria Conta de Usuário")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Criado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = AccountDtoOut.class))),
            @APIResponse(responseCode = "422", description = "Campos obrigatórios não informados",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ResponseError.class))),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response create(AccountDtoCreate dto) {
        try {
            return service.create(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).entity(e).build();
        }
    }

    @PUT
    @Path("{accountId}")
    @Operation(summary = "Atualizar", description = "Atualiza Conta de Usuário")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class))),
            @APIResponse(responseCode = "422", description = "Campos obrigatórios não informados",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ResponseError.class))),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response update(@PathParam("accountId") long id, AccountDtoUpdate dto) {
        try {
            return service.update(id, dto);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).entity(e).build();
        }
    }


    @DELETE
    @Path("{accountId}")
    @Operation(summary = "Desativar", description = "Desativa Conta de Usuário")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class))),
            @APIResponse(responseCode = "422", description = "Campos obrigatórios não informados",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ResponseError.class))),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response disable(@PathParam("id") long id) {
        try {
            return service.disable(id);
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).entity(e).build();
        }
    }
}
