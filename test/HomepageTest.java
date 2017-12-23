import org.junit.Test;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;


import static org.junit.Assert.assertEquals;
import static play.test.Helpers.*;

public class HomepageTest extends FunctionalTest {



    @Test
    public void testRegistration()
    {
        Http.RequestBuilder request = Helpers.fakeRequest()
                .method(GET)
                .uri("/");

        Result result = route(app, request);
        assertEquals(OK, result.status());

    }
}
