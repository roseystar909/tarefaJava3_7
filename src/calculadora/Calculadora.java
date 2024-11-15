package calculadora;

import java.awt.*;
import java.math.*;
import javax.swing.*;

public class Calculadora extends JFrame {
    private JTextField areaDeTexto;
    private BigDecimal num1 = BigDecimal.ZERO;
    private BigDecimal num2 = BigDecimal.ZERO;
    private String operador = "";
    private boolean operadorSelecionado = false;
    
    public Calculadora() {
        // Criar Janela
        this.setDefaultCloseOperation(3);
        this.setLayout(new BorderLayout());
        this.setSize(250, 300);
        this.setResizable(false);
        this.setFocusable(false);
        this.setTitle("Calculadora");
        
        // Area de Texto
        JPanel painelTexto = new JPanel();
        painelTexto.setLayout(new BoxLayout(painelTexto, BoxLayout.Y_AXIS));
        painelTexto.setPreferredSize(new Dimension(1, 80));
        this.add(painelTexto, BorderLayout.NORTH);

        areaDeTexto = new JTextField();
        areaDeTexto.setFont(new Font("Arial", Font.PLAIN, 24));
        areaDeTexto.setEditable(false);
        painelTexto.add(areaDeTexto);
        
        // Painel dos Botões
        JPanel painelBotao = new JPanel();
        painelBotao.setLayout(new GridLayout(4, 4, 5, 5));
        painelBotao.setPreferredSize(new Dimension(1, 150));
        this.add(painelBotao, BorderLayout.SOUTH);

        // Painel Superior de Botões
        JPanel painelTopBotao = new JPanel();
        painelTopBotao.setLayout(new GridLayout(1, 2, 5, 5));
        this.add(painelTopBotao, BorderLayout.CENTER);
        
        // Botões
        JButton buttonClear = new JButton("C");
        JButton buttonBackspace = new JButton("⌫");

        painelTopBotao.add(buttonClear);
        painelTopBotao.add(buttonBackspace);

        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");
        JButton button4 = new JButton("4");
        JButton button5 = new JButton("5");
        JButton button6 = new JButton("6");
        JButton button7 = new JButton("7");
        JButton button8 = new JButton("8");
        JButton button9 = new JButton("9");
        JButton button0 = new JButton("0");
        JButton buttonCom = new JButton(",");
        JButton buttonAdd = new JButton("+");
        JButton buttonSub = new JButton("-");
        JButton buttonMul = new JButton("*");
        JButton buttonDiv = new JButton("/");
        JButton buttonEqu = new JButton("=");

        painelBotao.add(button7);
        painelBotao.add(button8);
        painelBotao.add(button9);
        painelBotao.add(buttonDiv);
        painelBotao.add(button4);
        painelBotao.add(button5);
        painelBotao.add(button6);
        painelBotao.add(buttonMul);
        painelBotao.add(button1);
        painelBotao.add(button2);
        painelBotao.add(button3);
        painelBotao.add(buttonSub);
        painelBotao.add(button0);
        painelBotao.add(buttonCom);
        painelBotao.add(buttonEqu);
        painelBotao.add(buttonAdd);
        
        // Numerais
        button0.addActionListener(e -> areaDeTexto.setText(areaDeTexto.getText()+ "0"));
        button1.addActionListener(e -> areaDeTexto.setText(areaDeTexto.getText()+ "1"));
        button2.addActionListener(e -> areaDeTexto.setText(areaDeTexto.getText()+ "2"));
        button3.addActionListener(e -> areaDeTexto.setText(areaDeTexto.getText()+ "3"));
        button4.addActionListener(e -> areaDeTexto.setText(areaDeTexto.getText()+ "4"));
        button5.addActionListener(e -> areaDeTexto.setText(areaDeTexto.getText()+ "5"));
        button6.addActionListener(e -> areaDeTexto.setText(areaDeTexto.getText()+ "6"));
        button7.addActionListener(e -> areaDeTexto.setText(areaDeTexto.getText()+ "7"));
        button8.addActionListener(e -> areaDeTexto.setText(areaDeTexto.getText()+ "8"));
        button9.addActionListener(e -> areaDeTexto.setText(areaDeTexto.getText()+ "9"));
        
        // Virgula
        buttonCom.addActionListener(e -> areaDeTexto.setText(areaDeTexto.getText()+ "."));
        
        // Operadores
        buttonAdd.addActionListener(e -> setOperador("+"));
        buttonSub.addActionListener(e -> setOperador("-"));
        buttonMul.addActionListener(e -> setOperador("*"));
        buttonDiv.addActionListener(e -> setOperador("/"));
        
        // Igual
        buttonEqu.addActionListener(e -> calcular());

        // Limpar
        buttonClear.addActionListener(e -> areaDeTexto.setText(""));

        // Apagar
        buttonBackspace.addActionListener(e -> {
            String textoAtual = areaDeTexto.getText();
            if (!textoAtual.isEmpty()) {
                areaDeTexto.setText(textoAtual.substring(0, textoAtual.length() - 1));
            }
        });

        // Visibilidade da Janela
        this.setVisible(true);
    }
    
    private void setOperador(String op) {
        try {
            if (!operadorSelecionado) {
                num1 = new BigDecimal(areaDeTexto.getText());
                operador = op;
                areaDeTexto.setText(areaDeTexto.getText() + " " + operador + " ");
                operadorSelecionado = true;
            }
        } catch (NumberFormatException e) {
            areaDeTexto.setText("Erro");
        }
    }
    
    private void calcular() {
        try {
            String[] partes = areaDeTexto.getText().split(" ");
            if (partes.length < 3) return; 
            
            num1 = new BigDecimal(partes[0]);
            num2 = new BigDecimal(partes[2]);
            
            BigDecimal resultado = BigDecimal.ZERO;
        
            switch(operador) {
                case "+":
                    resultado = num1.add(num2);
                    break;
                case "-":
                    resultado = num1.subtract(num2);
                    break;
                case "*":
                    resultado = num1.multiply(num2);
                    break;
                case "/":
                    resultado = num2.compareTo(BigDecimal.ZERO) != 0 ? num1.divide(num2, 2, RoundingMode.HALF_UP) : null;
                    break;
        }
        
        areaDeTexto.setText(String.valueOf(resultado));
        operador = "";
        operadorSelecionado = false;
        } catch (NumberFormatException e) {
            areaDeTexto.setText("Erro");
        }
    }

    public static void main(String[] args) {
        new Calculadora();
    }
}
