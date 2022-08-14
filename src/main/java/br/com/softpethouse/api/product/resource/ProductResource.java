package br.com.softpethouse.api.product.resource;

import lombok.extern.slf4j.Slf4j;
import br.com.softpethouse.api.Resources;
import br.com.softpethouse.api.product.entity.ProductEntity;
import br.com.softpethouse.api.commom.validation.ResponseError;
import br.com.softpethouse.api.product.service.ProductService;
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
            @APIResponse(responseCode = "200", description = "Sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ProductEntity.class, type = SchemaType.ARRAY))),
            @APIResponse(responseCode = "404", description = "Não encontrado"),
            @APIResponse(responseCode = "500", description = "Erro interno", content = @Content(mediaType = MediaType.APPLICATION_JSON))})
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
    @Path("{id}")
    @Operation(summary = "Produto", description = "Busca Produto por id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ProductEntity.class))),
            @APIResponse(responseCode = "404", description = "Produto não encontrado"),
            @APIResponse(responseCode = "500", description = "Erro interno", content = @Content(mediaType = MediaType.APPLICATION_JSON))})
    public Response products(@PathParam("id") long id) {
        try {
            log.info("Requesting Products by id {}.", id);
            return service.products(id);
        } catch (Exception e) {
            log.error("Exception when request Products from id {}!\n{}", id, e);
            return Response.serverError().entity(e).build();
        }
    }

    @POST
    @Operation(summary = "Criar", description = "Cria Produto que pode ser uma Loja ou Clínica Veterinária")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Criado"),
            @APIResponse(responseCode = "422", description = "Objeto de entrada inválido", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ResponseError.class))),
            @APIResponse(responseCode = "500", description = "Erro interno", content = @Content(mediaType = MediaType.APPLICATION_JSON))})
    public Response create(@Valid ProductEntity product) {
        try {
            log.info("Creating Products.");
            return service.create(product);
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
            @APIResponse(responseCode = "422", description = "Objeto de entrada inválido", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ResponseError.class))),
            @APIResponse(responseCode = "500", description = "Erro interno", content = @Content(mediaType = MediaType.APPLICATION_JSON))})
    public Response update(@PathParam("id") long id, @Valid ProductEntity product) {
        try {
            log.info("Updating Products.");
            return service.update(id, product);
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
            @APIResponse(responseCode = "500", description = "Erro interno", content = @Content(mediaType = MediaType.APPLICATION_JSON))})
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
