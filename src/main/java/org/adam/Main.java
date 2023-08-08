package org.adam;

import org.adam.exceptions.ObjectNotFoundException;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

@QuarkusMain
public class Main {

    @ServerExceptionMapper
    public RestResponse<String> mapException(ObjectNotFoundException x) {
        return RestResponse.status(Response.Status.NOT_FOUND, "Key does not exist on Redis: " + x.key);
    }

    public static void main(String... args) {
        Quarkus.run(Api.class, args);
    }

    public static class Api implements QuarkusApplication {

        @Override
        public int run(String... args) throws Exception {
            Quarkus.waitForExit();
            return 0;
        }
    }
}