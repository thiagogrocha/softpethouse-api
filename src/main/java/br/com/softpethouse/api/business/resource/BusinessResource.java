package br.com.softpethouse.api.business.resource;

import br.com.softpethouse.api.Resources;
import br.com.softpethouse.api.business.dto.BusinessDto;
import br.com.softpethouse.api.business.service.BusinessService;
import br.com.softpethouse.api.business.dto.BusinessDtoOut;
import br.com.softpethouse.api.commom.validation.ResponseError;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@Path(Resources.BUSINESS)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BusinessResource {

    private BusinessService service;

    @Inject
    public BusinessResource(BusinessService service) {
        this.service = service;
    }

    @GET
    @Operation(summary = "Negócios", description = "Lista Negócios, sejam Lojas ou Clínicas Veterinárias")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = BusinessDtoOut.class, type = SchemaType.ARRAY))),
            @APIResponse(responseCode = "404", description = "Não encontrado"),
            @APIResponse(responseCode = "500", description = "Erro interno", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RuntimeException.class)))})
    public Response business() {
        try {
            log.info("Requesting all Business.");
            return service.business();
        } catch (Exception e) {
            log.error("Exception when requesting all Business!\n{}", e);
            return Response.serverError().entity(e).build();
        }
    }

    @GET
    @Path(("{id}"))
    @Operation(summary = "Negócio", description = "Busca Negócio por id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = BusinessDtoOut.class))),
            @APIResponse(responseCode = "404", description = "Negócio não encontrado"),
            @APIResponse(responseCode = "500", description = "Erro interno", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RuntimeException.class)))})
    public Response business(@PathParam("id") long id) {
        try {
            log.info("Requesting Business by id {}.", id);
            return service.business(id);
        } catch (Exception e) {
            log.error("Exception when request Business from id {}!\n{}", id, e);
            return Response.serverError().entity(e).build();
        }
    }

    @POST
    @Operation(summary = "Criar", description = "Cria Negócio que pode ser uma Loja ou Clínica Veterinária")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Criado"),
            @APIResponse(responseCode = "422", description = "Objeto de entrada inválido",content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ResponseError.class))),
            @APIResponse(responseCode = "500", description = "Erro interno", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RuntimeException.class)))})
    public Response create(BusinessDto dto) {
        try {
            log.info("Creating Business.");
            return service.create(dto);
        } catch (Exception e) {
            log.error("Exception when creating Business!\n{}", e);
            return Response.serverError().entity(e).build();
        }
    }

    @PUT
    @Path("{id}")
    @Operation(summary = "Atualizar", description = "Atualiza Negócio")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso"),
            @APIResponse(responseCode = "404", description = "Negócio não encontrado"),
            @APIResponse(responseCode = "422", description = "Objeto de entrada inválido", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ResponseError.class))),
            @APIResponse(responseCode = "500", description = "Erro interno", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RuntimeException.class)))})
    public Response update(@PathParam("id") long id, BusinessDto dto) {
        try {
            log.info("Updating Business.");
            return service.update(id, dto);
        } catch (Exception e) {
            log.error("Exception when Updating Business!\n{}", e);
            return Response.serverError().entity(e).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Desativar", description = "Desativa Negócio")
    @APIResponses(value = {
            @APIResponse(responseCode = "204", description = "Desativado"),
            @APIResponse(responseCode = "404", description = "Negócio não encontrado"),
            @APIResponse(responseCode = "500", description = "Erro interno", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RuntimeException.class)))})
    public Response disable(@PathParam("id") long id) {
        try {
            log.info("Disabling Business.");
            return service.disable(id);
        } catch (Exception e) {
            log.error("Exception when disabling Business!\n{}", e);
            return Response.serverError().entity(e).build();
        }
    }

}
