package br.com.softpethouse.api.adoption.resource;

import br.com.softpethouse.api.adoption.dto.AdoptionDtoCreate;
import lombok.extern.slf4j.Slf4j;
import br.com.softpethouse.api.Resources;
import br.com.softpethouse.api.adoption.dto.AdoptionDto;
import br.com.softpethouse.api.adoption.service.AdoptionService;
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
@Path(Resources.ADOPTION)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdoptionResource {
    
    @Inject
    AdoptionService service;

    @GET
    @Operation(summary = "Adoções", description = "Lista todas as Adoções")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = AdoptionDto[].class))),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response adoptions() {
        try {
            return service.adoptions();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(e).build();
        }
    }

    @GET
    @Path("{id}")
        @Operation(summary = "Adoção", description = "Busca Adoção por id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = AdoptionDto.class))),
            @APIResponse(responseCode = "403", description = "Adoção não encontrada"),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response adoptions(@PathParam("id") long id) {
        try {
            return service.adoptions(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(e).build();
        }
    }

    @POST
    @Operation(summary = "Criar Adoção", description = "Inicia a Adoção de um pet")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Criado"),
            @APIResponse(responseCode = "422", description = "Campos obrigatórios não informados", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ResponseError.class))),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response create(@Valid AdoptionDtoCreate dto) {
        try {
            return service.create(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(e).build();
        }
    }

    @PUT
    @Path("{id}")
    @Operation(summary = "Atualizar", description = "Atualiza uma Adoção")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso"),
            @APIResponse(responseCode = "403", description = "Adoção não encontrada"),
            @APIResponse(responseCode = "422", description = "Campos obrigatórios não informados", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ResponseError.class))),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response update(@PathParam("id") long id, @Valid AdoptionDtoCreate dto) {
        try {
            return service.update(id, dto);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(e).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Desativar", description = "Desativa/Cancela uma Adoção")
    @APIResponses(value = {
            @APIResponse(responseCode = "204", description = "Desativada"),
            @APIResponse(responseCode = "403", description = "Adoção não encontrada"),
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
