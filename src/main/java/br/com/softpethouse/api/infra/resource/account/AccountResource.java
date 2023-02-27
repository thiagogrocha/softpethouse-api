package br.com.softpethouse.api.infra.resource.account;

import br.com.softpethouse.api.infra.resource.Resources;
import br.com.softpethouse.api.service.account.dto.AccountDtoCreate;
import br.com.softpethouse.api.service.account.dto.AccountDtoOut;
import br.com.softpethouse.api.service.account.dto.AccountDtoUpdate;
import br.com.softpethouse.api.service.account.AccountService;
import br.com.softpethouse.api.service.validation.EntityNotFoundException;
import br.com.softpethouse.api.service.validation.ResponseError;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Slf4j
@Path(Resources.ACCOUNT)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {

    @Inject
    AccountService service;

    @GET
    @Operation(summary = "Contas de Usuário", description = "Lista todas as Contas de Usuário")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = AccountDtoOut[].class))),
            @APIResponse(responseCode = "404", description = "Não encontrado"),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response accounts() {
        try {
            return Response.ok(service.accounts()).build();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());

            return Response.serverError().entity(e).build();
        }
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Conta de Usuário", description = "Busca Conta de Usuário por id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = AccountDtoOut.class))),
            @APIResponse(responseCode = "404", description = "Conta de Usuário não encontrada"),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response accounts(@PathParam("id") long id) {
        try {
            return Response.ok(service.accounts(id)).build();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());

            if (e instanceof EntityNotFoundException)
                return Response.status(Response.Status.NOT_FOUND.getStatusCode(), e.getMessage()).build();

            return Response.serverError().entity(e).build();
        }
    }

    @POST
    @Operation(summary = "Criar", description = "Cria Conta de Usuário")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Criado"),
            @APIResponse(responseCode = "404", description = "Conta de Usuário não encontrada"),
            @APIResponse(responseCode = "422", description = "Campos obrigatórios não informados",
                    content = @Content(schema = @Schema(implementation = ResponseError.class))),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response create(@Valid AccountDtoCreate dto) {
        try {
            return Response.created(UriBuilder.fromPath(Resources.ACCOUNT)
                    .path(String.valueOf(service.create(dto))).build()).build();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());

            if (e instanceof EntityNotFoundException)
                return Response.status(Response.Status.NOT_FOUND.getStatusCode(), e.getMessage()).build();

            return Response.serverError().entity(e).build();
        }
    }

    @PUT
    @Path("{id}")
    @Operation(summary = "Atualizar", description = "Atualiza Conta de Usuário")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso"),
            @APIResponse(responseCode = "404", description = "Conta não encontrada"),
            @APIResponse(responseCode = "422", description = "Campos obrigatórios não informados",
                    content = @Content(schema = @Schema(implementation = ResponseError.class))),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response update(@PathParam("id") long id, @Valid AccountDtoUpdate dto) {
        try {
            service.update(id, dto);

            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());

            if (e instanceof EntityNotFoundException)
                return Response.status(Response.Status.NOT_FOUND.getStatusCode(), e.getMessage()).build();

            return Response.serverError().entity(e).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Desativar", description = "Desativa Conta de Usuário")
    @APIResponses(value = {
            @APIResponse(responseCode = "204", description = "Desativado"),
            @APIResponse(responseCode = "404", description = "Conta não encontrada"),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response disable(@PathParam("id") long id) {
        try {
            service.disable(id);

            return Response.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());

            if (e instanceof EntityNotFoundException)
                return Response.status(Response.Status.NOT_FOUND.getStatusCode(), e.getMessage()).build();

            return Response.serverError().entity(e).build();
        }
    }

}
