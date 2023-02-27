package br.com.softpethouse.api.infra.resource.business;

import br.com.softpethouse.api.service.business.dto.BusinessDtoCreate;
import br.com.softpethouse.api.service.validation.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import br.com.softpethouse.api.infra.resource.Resources;
import br.com.softpethouse.api.service.business.BusinessService;
import br.com.softpethouse.api.service.business.dto.BusinessDtoOut;
import br.com.softpethouse.api.service.validation.ResponseError;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
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
@Path(Resources.BUSINESS)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BusinessResource {

    @Inject
    BusinessService service;

    @GET
    @Operation(summary = "Negócios", description = "Lista Negócios, sejam Lojas ou Clínicas Veterinárias")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = BusinessDtoOut.class, type = SchemaType.ARRAY))),
            @APIResponse(responseCode = "404", description = "Não encontrado"),
            @APIResponse(responseCode = "500", description = "Erro interno", content = @Content(mediaType = MediaType.APPLICATION_JSON))})
    public Response business() {
        try {
            log.info("Requesting all Business.");

            return Response.ok(service.business()).build();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());

            return Response.serverError().entity(e).build();
        }
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Negócio", description = "Busca Negócio por id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = BusinessDtoOut.class))),
            @APIResponse(responseCode = "404", description = "Negócio não encontrado"),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response business(@PathParam("id") long id) {
        try {
            log.info("Requesting Business by id {}.", id);

            return Response.ok(service.business(id)).build();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());

            if (e instanceof EntityNotFoundException)
                return Response.status(Response.Status.NOT_FOUND.getStatusCode(), e.getMessage()).build();

            return Response.serverError().entity(e).build();
        }
    }

    @POST
    @Operation(summary = "Criar", description = "Cria Negócio que pode ser uma Loja ou Clínica Veterinária")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Criado"),
            @APIResponse(responseCode = "404", description = "Conta de Usuário não encontrada"),
            @APIResponse(responseCode = "422", description = "Objeto de entrada inválido",
                    content = @Content(schema = @Schema(implementation = ResponseError.class))),
            @APIResponse(responseCode = "500", description = "Erro interno", content = @Content(mediaType = MediaType.APPLICATION_JSON))})
    public Response create(@Valid BusinessDtoCreate dto) {
        try {
            log.info("Creating Business.");

            return Response.created(UriBuilder.fromPath(Resources.BUSINESS)
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
    @Operation(summary = "Atualizar", description = "Atualiza Negócio")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso"),
            @APIResponse(responseCode = "404", description = "Negócio não encontrado"),
            @APIResponse(responseCode = "422", description = "Objeto de entrada inválido",
                    content = @Content(schema = @Schema(implementation = ResponseError.class))),
            @APIResponse(responseCode = "500", description = "Erro interno", content = @Content(mediaType = MediaType.APPLICATION_JSON))})
    public Response update(@PathParam("id") long id, @Valid BusinessDtoCreate dto) {
        try {
            log.info("Updating Business.");
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
    @Operation(summary = "Desativar", description = "Desativa Negócio")
    @APIResponses(value = {
            @APIResponse(responseCode = "204", description = "Desativado"),
            @APIResponse(responseCode = "404", description = "Negócio não encontrado"),
            @APIResponse(responseCode = "500", description = "Erro interno", content = @Content(mediaType = MediaType.APPLICATION_JSON))})
    public Response disable(@PathParam("id") long id) {
        try {
            log.info("Disabling Business.");
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
