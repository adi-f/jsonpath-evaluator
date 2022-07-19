package adif.jsonpathevalator;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.List;

public class Gui {

    private final List<JsonPathEvaluator> jsonPathEvaluators;

    private JsonPathEvaluator currentJsonPathEvaluator;
    private JTextComponent jsonPathExpressionTextComponent;
    private JTextComponent jsonTextComponent;
    private JTextComponent outputTextComponent;


    public Gui(List<JsonPathEvaluator> jsonPathEvaluators) {
        this.jsonPathEvaluators = jsonPathEvaluators;
        currentJsonPathEvaluator = jsonPathEvaluators.get(0);
    }

    public void show() {
        // Font monospaced = new Font(Font.MONOSPACED, Font.PLAIN, 12);
        JFrame window = new JFrame();
        window.setTitle("JSON Path Evaluator");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.PAGE_AXIS));
        window.setSize(730, 420);

        window.add(createEvaluatorDropdown());
        window.add(createExpressionInput());
        window.add(createJsonInput());
        window.add(createEvaluateButton());
        window.add(createOutput());

        window.setVisible(true);

    }

    private void evaluate() {
        try {
            String json = jsonTextComponent.getText();
            String expression = jsonPathExpressionTextComponent.getText();
            String output = currentJsonPathEvaluator.evaluate(json, expression);
            outputTextComponent.setText(output);
        } catch (RuntimeException e) {
            e.printStackTrace();
            outputTextComponent.setText(e.toString());
        }
    }

    private JPanel createEvaluatorDropdown() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JComboBox<JsonPathEvaluatorWrapper> evaluators = new JComboBox<>();
        jsonPathEvaluators.stream().map(JsonPathEvaluatorWrapper::new).forEach(evaluators::addItem);

        evaluators.addActionListener(event -> currentJsonPathEvaluator = ((JsonPathEvaluatorWrapper) evaluators.getSelectedItem()).getJsonPathEvaluator());

        panel.add(new JLabel("Evaluator: "));
        panel.add(evaluators);

        return panel;
    }

    private JPanel createExpressionInput() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField expression = new JTextField("", 50);

        panel.add(new JLabel("JSON Path Expression: "));
        panel.add(expression);
        jsonPathExpressionTextComponent = expression;

        return panel;
    }

    private JPanel createJsonInput() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        // panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        JTextArea json = new JTextArea(10, 59);
        JScrollPane scrollPane = new JScrollPane(json);

        panel.add(new JLabel("JSON: "));
        panel.add(scrollPane);
        jsonTextComponent = json;

        return panel;
    }

    private JPanel createEvaluateButton() {
        JPanel panel = new JPanel();
        JButton evaluateButton = new JButton("Evaluate");

        evaluateButton.addActionListener(event -> evaluate());

        panel.add(evaluateButton);

        return panel;
    }

    private JPanel createOutput() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextArea output = new JTextArea(5, 59);
        output.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(output);

        panel.add(new JLabel("Result: "));
        panel.add(scrollPane);
        outputTextComponent = output;

        return panel;
    }


    private static class JsonPathEvaluatorWrapper {
        private final JsonPathEvaluator jsonPathEvaluator;

        public JsonPathEvaluatorWrapper(JsonPathEvaluator jsonPathEvaluator) {
            this.jsonPathEvaluator = jsonPathEvaluator;
        }

        public JsonPathEvaluator getJsonPathEvaluator() {
            return jsonPathEvaluator;
        }

        @Override
        public String toString() {
            return jsonPathEvaluator.getDisplayName();
        }
    }
}
