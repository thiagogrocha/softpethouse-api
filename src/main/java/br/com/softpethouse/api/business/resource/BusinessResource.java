package br.com.softpethouse.api.business.resource;

import br.com.softpethouse.api.Resources;
import br.com.softpethouse.api.business.dto.BusinessDto;
import br.com.softpethouse.api.account.service.BusinessService;
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
import java.util.stream.Collectors;

@Path(Resources.BUSINESS)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BusinessResource {

    private BusinessService service;

    @Inject
    public BusinessResource(BusinessService service) {
        this.service = service;
    }

    @POST
    @Operation(summary = "Cria Negócio", description = "Um Negócio pode ser um Petshop ou uma Clínica Veterinária")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Criado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = BusinessDto.class))),
            @APIResponse(responseCode = "422", description = "Campos obrigatórios não informados",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ResponseError.class))),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response post(BusinessDto dto) {
        try {
            return service.save(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).entity(e).build();
        }
    }

    @GET
    @Operation(summary = "Lista Negócios", description = "Lista Lojas ou Clínicas Veterinárias")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = BusinessDto.class))),
            @APIResponse(responseCode = "403", description = "Usuário não encontrado"),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response get() {
        try {
            return Response.ok(service.listAll()
                    .stream()
                    .map(BusinessDto::fromEntity)
                    .collect(Collectors.toList())).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).entity(e).build();
        }
    }
}