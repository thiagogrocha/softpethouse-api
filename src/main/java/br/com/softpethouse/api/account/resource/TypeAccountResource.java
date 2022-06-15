package br.com.softpethouse.api.account.resource;

import lombok.extern.slf4j.Slf4j;
import br.com.softpethouse.api.Resources;
import br.com.softpethouse.api.account.dto.TypeAccountDto;
import br.com.softpethouse.api.account.service.TypeAccountService;
import br.com.softpethouse.api.commom.validation.ResponseError;
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
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = TypeAccountDto[].class))),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response typesAccount() {
        try {
            return service.typesAccount();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(e).build();
        }
    }

    @GET
    @Path(("{id}"))
    @Operation(summary = "Tipo de Conta de Usuário", description = "Busca Tipos de Conta de Usuário por id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = TypeAccountDto.class))),
            @APIResponse(responseCode = "403", description = "Tipo de Conta de Usuário não encontrada"),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response typesAccount(@PathParam("id") long id) {
        try {
            return service.typesAccount(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(e).build();
        }
    }

    @POST
    @Operation(summary = "Criar", description = "Cria um Tipo de Conta de Usuário que pode ser Cliente, Consultor ou Administrador")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Criado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = TypeAccountDto.class))),
            @APIResponse(responseCode = "422", description = "Campos obrigatórios não informados",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ResponseError.class))),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response create(@Valid TypeAccountDto dto) {
        try {
            return service.create(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(e).build();
        }
    }

    @PUT
    @Path("{id}")
    @Operation(summary = "Atualizar", description = "Atualiza um Tipo de Conta de Usuário")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class))),
            @APIResponse(responseCode = "403", description = "Tipo de Conta não encontrada"),
            @APIResponse(responseCode = "422", description = "Campos obrigatórios não informados",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ResponseError.class))),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response update(@PathParam("id") long id, @Valid TypeAccountDto dto) {
        try {
            return service.update(id, dto);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(e).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Desativar", description = "Desativa um Tipo de Conta de Usuário")
    @APIResponses(value = {
            @APIResponse(responseCode = "204", description = "Desativado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class))),
            @APIResponse(responseCode = "403", description = "Conta não encontrada"),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response disable(@PathParam("id") long id) {
        try {
            return service.disable(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(e).build();
        }
    }

}
