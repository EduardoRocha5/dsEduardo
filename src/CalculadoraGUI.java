import java.awt.*; // Importa componentes gráficos
import java.awt.event.*; // Importa eventos (ex: clique)
import javax.swing.*; // Importa Swing (JFrame, JButton, etc.)
import javax.swing.border.EmptyBorder; // Permite adicionar "espaço" (margem interna)

public class CalculadoraGUI extends JFrame implements ActionListener {

    private final JTextField display; // Campo de exibição
    private String operacao = ""; // Operação atual: +, -, *, /
    private double num1, num2; // Números usados na operação
    private boolean novoNumero = true; // Flag para controlar quando o usuário começa a digitar um novo número

    public CalculadoraGUI() {
        // Configurações da janela
        setTitle("Calculadora");
        setSize(350, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o app ao fechar janela
        setLocationRelativeTo(null); // Centraliza na tela
        setLayout(new BorderLayout(10, 10)); // Layout com regiões (NORTH, CENTER etc)
        getContentPane().setBackground(new Color(30, 30, 30)); // Fundo da janela

        // Campo de exibição (display)
        display = new JTextField();
        display.setFont(new Font("Consolas", Font.BOLD, 36)); // Fonte do texto
        display.setHorizontalAlignment(JTextField.RIGHT); // Alinha o texto à direita
        display.setEditable(false); // Usuário não pode digitar diretamente
        display.setBackground(new Color(20, 20, 20)); // Fundo escuro
        display.setForeground(Color.WHITE); // Texto branco
        display.setBorder(new EmptyBorder(10, 10, 10, 10)); // Espaçamento interno
        add(display, BorderLayout.NORTH); // Coloca o display no topo

        // Painel que vai conter os botões
        JPanel painel = new JPanel(new GridLayout(4, 4, 10, 10)); // 4x4 grade com espaçamento
        painel.setBackground(new Color(30, 30, 30)); // Cor de fundo do painel
        painel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Margem interna

        // Texto dos botões em ordem de layout
        String[] botoes = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "+",
            "C", "0", "=", "-"
        };

        // Criação dos botões
        for (String texto : botoes) {
            JButton botao = new JButton(texto); // Cria botão com texto
            botao.setFont(new Font("Arial", Font.BOLD, 24)); // Define fonte do botão
            botao.setFocusPainted(false); // Remove destaque de foco (visual)
            botao.setForeground(Color.WHITE); // Texto branco

            // Define cores diferentes para cada tipo de botão
            if ("0123456789".contains(texto)) {
                botao.setBackground(new Color(60, 63, 65)); // Cor cinza para números
            } else if ("+-*/".contains(texto)) {
                botao.setBackground(new Color(70, 130, 180)); // Azul para operações
            } else if ("=".equals(texto)) {
                botao.setBackground(new Color(34, 139, 34)); // Verde para "="
            } else if ("C".equals(texto)) {
                botao.setBackground(new Color(178, 34, 34)); // Vermelho para "C"
            }

            botao.addActionListener(this); // Conecta o botão ao método de ação
            painel.add(botao); // Adiciona o botão ao painel
        }

        add(painel, BorderLayout.CENTER); // Adiciona o painel no centro da tela
        setVisible(true); // Torna a janela visível
    }

    // Método chamado quando um botão é clicado
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand(); // Pega o texto do botão clicado

        // Se for número
        if ("0123456789".contains(comando)) {
            if (novoNumero) {
                display.setText(comando); // Começa um novo número
                novoNumero = false;
            } else {
                display.setText(display.getText() + comando); // Continua digitando o número
            }

        // Se for operação
        } else if ("+-*/".contains(comando)) {
            num1 = Double.parseDouble(display.getText()); // Guarda o primeiro número
            operacao = comando; // Armazena a operação
            novoNumero = true; // Prepara para receber o segundo número

        // Se for igual
        } else if ("=".equals(comando)) {
            num2 = Double.parseDouble(display.getText()); // Guarda o segundo número
            double resultado = calcular(num1, num2, operacao); // Realiza o cálculo
            display.setText(String.valueOf(resultado)); // Mostra o resultado
            operacao = ""; // Limpa a operação
            novoNumero = true; // Prepara para novo número

        // Se for limpar (C)
        } else if ("C".equals(comando)) {
            display.setText(""); // Limpa o display
            operacao = ""; // Limpa a operação
            novoNumero = true; // Prepara para novo número
        }
    }

    // Realiza o cálculo com base na operação
    private double calcular(double valor1, double valor2, String ope) {
        return switch (ope) {
            case "+" -> valor1 + valor2;
            case "-" -> valor1 - valor2;
            case "*" -> valor1 * valor2;
            case "/" -> (valor2 != 0) ? valor1 / valor2 : 0;
            default -> 0;
        };
    }

    // Método principal que inicia a calculadora
    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculadoraGUI::new); // Garante que rode na thread certa
    }
}
