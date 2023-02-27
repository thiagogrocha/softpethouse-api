package br.com.softpethouse.api.infra.resource.account;

import br.com.softpethouse.api.infra.resource.Resources;
import br.com.softpethouse.api.service.account.dto.TypeAccountDtoCreate;
import br.com.softpethouse.api.service.account.TypeAccountService;
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
@Path(Resources.TYPEACCOUNT)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TypeAccountResource {

    @Inject
    TypeAccountService service;

    @GET
    @Operation(summary = "Tipos de Conta de Usuário", description = "Lista todos os tipos de Conta de Usuário")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = TypeAccountDtoCreate[].class))),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response typesAccount() {
        try {
            return Response.ok(service.typesAccount()).build();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());

            return Response.serverError().entity(e).build();
        }
    }

    @GET
    @Path(("{id}"))
    @Operation(summary = "Tipo de Conta de Usuário", description = "Busca Tipo de Conta de Usuário por id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = TypeAccountDtoCreate.class))),
            @APIResponse(responseCode = "404", description = "Tipo de Conta de Usuário não encontrada"),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response typesAccount(@PathParam("id") long id) {
        try {
            return Response.ok(service.typesAccount(id)).build();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());

            if (e instanceof EntityNotFoundException)
                return Response.status(Response.Status.NOT_FOUND.getStatusCode(), e.getMessage()).build();

            return Response.serverError().entity(e).build();
        }
    }

    @POST
    @Operation(summary = "Criar", description = "Cria um Tipo de Conta de Usuário que pode ser Cliente, Consultor ou Administrador")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Criado"),
            @APIResponse(responseCode = "404", description = "Conta de Usuário não encontrada"),
            @APIResponse(responseCode = "422", description = "Campos obrigatórios não informados",
                    content = @Content(schema = @Schema(implementation = ResponseError.class))),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response create(@Valid TypeAccountDtoCreate dto) {
        try {
            return Response.created(UriBuilder.fromPath(Resources.TYPEACCOUNT)
                    .path(String.valueOf(service.create(dto))).build()).build();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());

            return Response.serverError().entity(e).build();
        }
    }

    @PUT
    @Path("{id}")
    @Operation(summary = "Atualizar", description = "Atualiza um Tipo de Conta de Usuário")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso"),
            @APIResponse(responseCode = "404", description = "Tipo de Conta não encontrada"),
            @APIResponse(responseCode = "422", description = "Campos obrigatórios não informados",
                    content = @Content(schema = @Schema(implementation = ResponseError.class))),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response update(@PathParam("id") long id, @Valid TypeAccountDtoCreate dto) {
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
    @Operation(summary = "Desativar", description = "Desativa um Tipo de Conta de Usuário")
    @APIResponses(value = {
            @APIResponse(responseCode = "204", description = "Desativado"),
            @APIResponse(responseCode = "404", description = "Tipo de Conta não encontrada"),
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
