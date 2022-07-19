package adif.jsonpathevalator;

public interface JsonPathEvaluator {

    String getDisplayName();

    String evaluate(String json, String jsonPathExpression);
}
