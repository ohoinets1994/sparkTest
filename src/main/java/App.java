import com.google.gson.Gson;
import org.eclipse.jetty.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.post;


// localhost:4567
public class App {
    public static void main(String[] args) {
        List<Test> tests = new ArrayList<>();

        post("/test", (request, response) -> {
            tests.add(new Gson().fromJson(request.body(), Test.class));
            response.status(HttpStatus.CREATED_201);
            return "";
        });

        get("/test", (request, response) -> new Gson().toJson(tests));

        get("/test/:id", (request, response) -> {
            int id = Integer.valueOf(request.params("L:id"));
            return new Gson().toJson(tests.stream().filter(it -> it.id == id).findAny().orElse(null));
        });
    }
}

class Test {
    int id;
    String name;
}
