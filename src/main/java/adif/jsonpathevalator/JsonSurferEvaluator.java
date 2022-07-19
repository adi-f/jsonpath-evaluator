package adif.jsonpathevalator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.jsfr.json.Collector;
import org.jsfr.json.JsonSurferJackson;
import org.jsfr.json.ValueBox;

import java.util.Collection;

public class JsonSurferEvaluator implements JsonPathEvaluator {

    private final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();

    @Override
    public String getDisplayName() {
        return "JsonSurfer";
    }

    @Override
    public String evaluate(String json, String jsonPathExpression) {
        Collector collector = JsonSurferJackson.INSTANCE.collector(json);
        ValueBox<Collection<Object>> result = collector.collectAll(jsonPathExpression);
        collector.exec();
        try {
            return objectWriter.writeValueAsString(result.get());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
