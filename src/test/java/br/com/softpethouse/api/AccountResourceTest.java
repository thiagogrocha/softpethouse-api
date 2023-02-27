package br.com.softpethouse.api;

import br.com.softpethouse.api.infra.resource.Resources;

import static io.restassured.RestAssured.given;

//@QuarkusTest
class AccountResourceTest {

//    @Test
    void testAccountsByIdEndpoint() {
        given()
                .when().get(Resources.ACCOUNT)
                .then()
                .statusCode(200);
//                .body(is(new ObjectMapper().writeValueAsString(AccountDtoOut.builder()
//                        .id(12).active("S").username("")
//                        .user(UserDtoCreate.builder().name("Thiago").age(32).build())
//                        .typeAccount(TypeAccountDtoCreate.builder().name("ADM Negócio").description("description").build())
//                        .business(BusinessDtoCreate.builder().name("Pet e Cia PetShop").description("PetShop").build()).build())));
    }

}