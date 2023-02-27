package br.com.softpethouse.api.infra.resource.product;

import br.com.softpethouse.api.service.product.dto.ProductDtoCreate;
import br.com.softpethouse.api.service.product.dto.ProductDtoOut;
import br.com.softpethouse.api.service.product.dto.ProductDtoUpdate;
import lombok.extern.slf4j.Slf4j;
import br.com.softpethouse.api.infra.resource.Resources;
import br.com.softpethouse.api.service.validation.ResponseError;
import br.com.softpethouse.api.service.product.ProductService;
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

@Slf4j
@Path(Resources.PRODUCT)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductService service;

    @GET
    @Operation(summary = "Produtos", description = "Lista Produtos, sejam Lojas ou Clínicas Veterinárias")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = ProductDtoOut.class, type = SchemaType.ARRAY))),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response products() {
        try {
            log.info("Requesting all Products.");
            return service.products();
        } catch (Exception e) {
            log.error("Exception when requesting all Products!\n{}", e.getMessage());
            return Response.serverError().entity(e).build();
        }
    }

    @GET
    @Path("{idBusiness}")
    @Operation(summary = "Produto", description = "Busca Produto por Negócio")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = ProductDtoOut.class, type = SchemaType.ARRAY))),
            @APIResponse(responseCode = "404", description = "Negócio não encontrado"),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response products(@PathParam("idBusiness") long idBusiness) {
        try {
            log.info("Requesting Products by id {}.", idBusiness);
            return service.productsByBusiness(idBusiness);
        } catch (Exception e) {
            log.error("Exception when request Products from id {}!\n{}", idBusiness, e);
            return Response.serverError().entity(e).build();
        }
    }

    @POST
    @Operation(summary = "Criar", description = "Cria Produto que pode ser de uma Loja ou Clínica Veterinária")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Criado"),
            @APIResponse(responseCode = "422", description = "Objeto de entrada inválido", content = @Content(schema = @Schema(implementation = ResponseError.class))),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response create(@Valid ProductDtoCreate dto) {
        try {
            log.info("Creating Products.");
            return service.create(dto);
        } catch (Exception e) {
            log.error("Exception when creating Product!\n{}", e.getMessage());
            return Response.serverError().entity(e).build();
        }
    }

    @PUT
    @Path("{id}")
    @Operation(summary = "Atualizar", description = "Atualiza Produto")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso"),
            @APIResponse(responseCode = "404", description = "Produto não encontrado"),
            @APIResponse(responseCode = "422", description = "Objeto de entrada inválido", content = @Content(schema = @Schema(implementation = ResponseError.class))),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response update(@PathParam("id") long id, @Valid ProductDtoUpdate dto) {
        try {
            log.info("Updating Products.");
            return service.update(id, dto);
        } catch (Exception e) {
            log.error("Exception when Updating Product!\n{}", e.getMessage());
            return Response.serverError().entity(e).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Desativar", description = "Desativa Produto")
    @APIResponses(value = {
            @APIResponse(responseCode = "204", description = "Desativado"),
            @APIResponse(responseCode = "404", description = "Produto não encontrado"),
            @APIResponse(responseCode = "500", description = "Erro interno")})
    public Response disable(@PathParam("id") long id) {
        try {
            log.info("Disabling Products.");
            return service.disable(id);
        } catch (Exception e) {
            log.error("Exception when disabling Product!\n{}", e.getMessage());
            return Response.serverError().entity(e).build();
        }
    }

}
