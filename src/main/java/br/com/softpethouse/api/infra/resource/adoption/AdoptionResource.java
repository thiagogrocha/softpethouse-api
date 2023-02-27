package br.com.softpethouse.api.infra.resource.adoption;

import br.com.softpethouse.api.infra.resource.Resources;
import br.com.softpethouse.api.service.adoption.dto.AdoptionDtoCreate;
import br.com.softpethouse.api.service.adoption.dto.AdoptionDtoOut;
import br.com.softpethouse.api.service.adoption.dto.AdoptionDtoUpdate;
import br.com.softpethouse.api.service.adoption.AdoptionService;
import br.com.softpethouse.api.service.validation.EntityNotFoundException;
import br.com.softpethouse.api.service.validation.ResponseError;
import lombok.extern.slf4j.Slf4j;
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
@Path(Resources.ADOPTION)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdoptionResource {

    @Inject
    AdoptionService service;

    @GET
    @Operation(summary = "Adoções", description = "Lista todas as Adoções")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = AdoptionDtoOut.class, type = SchemaType.ARRAY))),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response adoptions() {
        try {
            return Response.ok(service.adoptions()).build();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());

            return Response.serverError().entity(e).build();
        }
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Adoção", description = "Busca Adoção por id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = AdoptionDtoOut.class))),
            @APIResponse(responseCode = "404", description = "Entidade não encontrada"),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response adoptions(@PathParam("id") long id) {
        try {
            return Response.ok(service.adoptions(id)).build();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());

            if (e instanceof EntityNotFoundException)
                return Response.status(Response.Status.NOT_FOUND.getStatusCode(), e.getMessage()).build();

            return Response.serverError().entity(e).build();
        }
    }

    @POST
    @Operation(summary = "Criar Adoção", description = "Inicia a Adoção de um pet")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Criado"),
            @APIResponse(responseCode = "404", description = "Entidade não encontrada"),
            @APIResponse(responseCode = "422", description = "Campos obrigatórios não informados",
                    content = @Content(schema = @Schema(implementation = ResponseError.class))),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response create(@Valid AdoptionDtoCreate dto) {
        try {
            return Response.created(UriBuilder.fromPath(Resources.ADOPTION)
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
    @Operation(summary = "Atualizar", description = "Atualiza uma Adoção")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso"),
            @APIResponse(responseCode = "404", description = "Entidade não encontrada"),
            @APIResponse(responseCode = "422", description = "Campos obrigatórios não informados",
                    content = @Content(schema = @Schema(implementation = ResponseError.class))),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response update(@PathParam("id") long id, @Valid AdoptionDtoUpdate dto) {
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
    @Operation(summary = "Desativar", description = "Desativa/Cancela uma Adoção")
    @APIResponses(value = {
            @APIResponse(responseCode = "204", description = "Desativada"),
            @APIResponse(responseCode = "404", description = "Entidade não encontrada"),
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
