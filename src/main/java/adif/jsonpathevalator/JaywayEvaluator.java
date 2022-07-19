package adif.jsonpathevalator;

import com.jayway.jsonpath.JsonPath;

import java.util.Objects;

public class JaywayEvaluator implements JsonPathEvaluator {


    @Override
    public String getDisplayName() {
        return "Jayway";
    }

    @Override
    public String evaluate(String json, String jsonPathExpression) {
        return Objects.toString(JsonPath.read(json, jsonPathExpression));
    }
}
