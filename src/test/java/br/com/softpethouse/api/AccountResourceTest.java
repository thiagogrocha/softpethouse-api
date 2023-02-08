package br.com.softpethouse.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

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
//                        .user(UserDto.builder().name("Thiago").age(32).build())
//                        .typeAccount(TypeAccountDto.builder().name("ADM Negócio").description("description").build())
//                        .business(BusinessDto.builder().name("Pet e Cia PetShop").description("PetShop").build()).build())));
    }

}