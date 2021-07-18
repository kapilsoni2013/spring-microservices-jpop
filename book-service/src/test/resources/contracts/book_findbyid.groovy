import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.MediaType

Contract.make {
    description("Should return book when pass book_id")
    request {
        method(GET())
        urlPath("/books/3"){
        }
    }

    response {
        status(OK())
        headers {contentType(MediaType.APPLICATION_JSON_VALUE)}
        body(file("book_findbyid_response.json"))
    }
}