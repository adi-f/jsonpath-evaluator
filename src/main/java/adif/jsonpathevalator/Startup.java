package adif.jsonpathevalator;

import java.util.List;

public class Startup {
    public static void main(String[] args) {
        List<JsonPathEvaluator> jsonPathEvaluators = List.of(
                new JaywayEvaluator(),
                new JsonSurferEvaluator()
        );

        new Gui(jsonPathEvaluators).show();
    }
}
