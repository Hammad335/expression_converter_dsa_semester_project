package dsa.semesterproject;
import javax.swing.*;
import java.util.Stack;

public class ExpressionConverter {

    boolean isOperator(char x){
        switch (x){
            case '-':
            case '+':
            case '/':
            case '*':
            case '^':
                return true;
        }
        return false;
    }

    public String convertToPostfix(String expression){

        Stack<String> stack = new Stack<String>();

        for (int i = expression.length()-1; i >=0 ; i--) {

            char c = expression.charAt(i);

            if(isOperator(c)){
                String s1 = stack.pop();
                String s2 = stack.pop();
                String temp = s1 + s2 + c;
                stack.push(temp);
            }
            else{
                stack.push(c + "");
            }
        }

        String result = stack.pop();
        return result;
    }

    public String convertToPrefix(String expression){

        Stack<String> stack = new Stack<>();
        for (int i = 0; i <expression.length() ; i++) {

            char c = expression.charAt(i);

            if(isOperator(c)){
                String s1 = stack.pop();
                String s2 = stack.pop();
                String temp = c + s2 + s1;
                stack.push(temp);
            }
            else{
                stack.push(c+"");
            }
        }
        String result = stack.pop();
        return result;
    }

    public Double evaluate(double a, double b, char operator){
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return b - a;
            case '*':
                return a * b;
            case '/':
                if (a == 0)
                    throw new
                            UnsupportedOperationException("Cannot divide by zero");
                return b / a;
        }
        return 0.0;
    }

    public Double evaluatePreFixExpression(String expression) {

        Stack<Double> stack = new Stack<>();

        StringBuilder input = new StringBuilder(expression);
        input.reverse();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '*' || c == '/' || c == '^' || c == '+' || c == '-') {
                double s1 = stack.pop();
                double s2 = stack.pop();
                double temp = evaluate(s2, s1, c);
                stack.push(temp);
            } else {
                stack.push((double) (c-'0'));
            }
        }

        double result = stack.pop();
        return result;
    }

    public static void main(String[] args) {

        //        prefix= *-A/BC-/AKL
        //        postFix= ABC/-AK/L-*

        JFrame frame= new JFrame();
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setSize(400, 260);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Converter -By Hamad");

        JLabel title= new JLabel("Welcome to prefix-postfix converter.");
        JTextField editText= new JTextField(20);
        JButton convertToPostfix= new JButton("Convert to postfix");
        JButton convertToPrefix= new JButton("Convert to prefix");
        JLabel result= new JLabel("Result");
        JLabel evaluate= new JLabel("Evaluate: ");

        title.setBounds(80, 30, 240, 30);
        editText.setBounds(90, 90, 190,20);
        convertToPostfix.setBounds(50, 130, 135,20);
        convertToPrefix.setBounds(195, 130, 130,20);
        result.setBounds(140, 130, 120,100);
        evaluate.setBounds(140, 150, 120,100);

        frame.add(title);
        frame.add(editText);
        frame.add(convertToPostfix);
        frame.add(convertToPrefix);
        frame.add(result);
        frame.add(evaluate);

        convertToPostfix.addActionListener(e -> {
            String prefix= editText.getText();

            String postFixExpression= new ExpressionConverter().convertToPostfix(prefix);

            result.setText("Postfix: " + postFixExpression);
            evaluate.setText("Evaluate To: " + new ExpressionConverter().evaluatePreFixExpression(prefix));
        });

        convertToPrefix.addActionListener(e -> {
            String postfix= editText.getText();

            String preFixExpression= new ExpressionConverter().convertToPrefix(postfix);

            result.setText("Prefix: " + preFixExpression);
            evaluate.setText("Evaluate To: " + new ExpressionConverter().evaluatePreFixExpression(preFixExpression));
        });


    }
}