package br.com.softpethouse.api.infra.resource.historic;

import br.com.softpethouse.api.infra.resource.Resources;
import br.com.softpethouse.api.service.validation.ResponseError;
import br.com.softpethouse.api.service.historic.dto.HistoricDtoCreate;
import br.com.softpethouse.api.service.historic.dto.HistoricDtoOut;
import br.com.softpethouse.api.service.historic.HistoricService;
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

@Slf4j
@Path(Resources.HISTORIC)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HistoricResource {

    @Inject
    HistoricService service;

    @GET
    @Path("{id}")
    @Operation(summary = "Históricos", description = "Busca Históricos por id do Pet")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = HistoricDtoOut.class))),
            @APIResponse(responseCode = "404", description = "Pet não encontrado"),
            @APIResponse(responseCode = "500", description = "Erro interno", content = @Content(mediaType = MediaType.APPLICATION_JSON))})
    public Response historic(@PathParam("id") long id) {
        try {
            log.info("Requesting Historic from Pet id {}.", id);
            return service.historic(id);
        } catch (Exception e) {
            log.error("Exception when request Historic from id {}!\n{}", id, e);
            return Response.serverError().entity(e).build();
        }
    }

    @POST
    @Operation(summary = "Criar", description = "Cria Histórico de um Pet")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Criado"),
            @APIResponse(responseCode = "422", description = "Objeto de entrada inválido", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ResponseError.class))),
            @APIResponse(responseCode = "500", description = "Erro interno", content = @Content(mediaType = MediaType.APPLICATION_JSON))})
    public Response create(@Valid HistoricDtoCreate dto) {
        try {
            log.info("Creating Business.");
            return service.create(dto);
        } catch (Exception e) {
            log.error("Exception when creating Business!\n{}", e.getMessage());
            return Response.serverError().entity(e).build();
        }
    }


}
