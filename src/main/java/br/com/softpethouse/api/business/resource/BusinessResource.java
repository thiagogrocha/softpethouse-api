package br.com.softpethouse.api.business.resource;

import br.com.softpethouse.api.Resources;
import br.com.softpethouse.api.business.dto.BusinessDto;
import br.com.softpethouse.api.account.service.BusinessService;
import br.com.softpethouse.api.business.dto.BusinessDtoOut;
import br.com.softpethouse.api.commom.validation.ResponseError;
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
            @APIResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = BusinessDtoOut.class, type = SchemaType.ARRAY))),
            @APIResponse(responseCode = "500", description = "Erro interno", content =
            @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RuntimeException.class)))})
    public Response business() {
        try {
            return service.business();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).entity(e).build();
        }
    }

    @GET
    @Path(("{id}"))
    @Operation(summary = "Negócio", description = "Busca Negócio por id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = BusinessDtoOut.class))),
            @APIResponse(responseCode = "403", description = "Negócio não encontrada"),
            @APIResponse(responseCode = "500", description = "Erro interno", content =
            @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RuntimeException.class)))})
    public Response business(@PathParam("id") long id) {
        try {
            return service.business(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).entity(e).build();
        }
    }

    @POST
    @Operation(summary = "Criar", description = "Cria Negócio que pode ser uma Loja ou Clínica Veterinária")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Criado"),
            @APIResponse(responseCode = "422", description = "Campos obrigatórios não informados",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ResponseError.class))),
            @APIResponse(responseCode = "500", description = "Erro interno", content =
            @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RuntimeException.class)))})
    public Response create(BusinessDto dto) {
        try {
            return service.create(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).entity(e).build();
        }
    }

    @PUT
    @Path("{id}")
    @Operation(summary = "Atualizar", description = "Atualiza Negócio")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class))),
            @APIResponse(responseCode = "403", description = "Negócio não encontrada"),
            @APIResponse(responseCode = "422", description = "Campos obrigatórios não informados",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ResponseError.class))),
            @APIResponse(responseCode = "500", description = "Erro interno", content =
            @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RuntimeException.class)))})
    public Response update(@PathParam("id") long id, BusinessDto dto) {
        try {
            return service.update(id, dto);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).entity(e).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Desativar", description = "Desativa Negócio")
    @APIResponses(value = {
            @APIResponse(responseCode = "204", description = "Desativado"),
            @APIResponse(responseCode = "403", description = "Negócio não encontrado"),
            @APIResponse(responseCode = "500", description = "Erro interno", content =
            @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RuntimeException.class)))})
    public Response disable(@PathParam("id") long id) {
        try {
            return service.disable(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).entity(e).build();
        }
    }

}
