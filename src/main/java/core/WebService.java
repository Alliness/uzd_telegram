package core;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import static spark.Spark.*;

public class WebService {


    private static final Logger log = Logger.getLogger(WebService.class);

    public WebService() {
        port(9300);
        initRoutes();
    }

    private void initRoutes() {

        before("/*", (request, response) -> System.out.println(String.format("[%s]%s", request.requestMethod(), request.uri())));
        path("/telegram", () -> get("/message", this::handle));

    }

    private Object handle(Request request, Response response) {
        log.info(request.requestMethod());
        log.info(request.body());
        response.header("Content-type", "application/json");
        response.body(new JSONObject().put("status", 200).toString());
        return response.body();
    }

}
