
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;




public class App {
    public static void main(String[] args) throws Exception {
        
        //criar frame com titulo CALCULO SIMPLES
        JFrame frame = new JFrame("Calculo Simples");
        
        frame.setSize(300,400); //define tamanho da janela/tela 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //fechar aplicação
        frame.setLayout(new GridLayout(5,1)); //criar layout com colunas e linhas / no estilo grid
        frame.revalidate();
        
        //Criar componentes
        JTextField campo1 = new JTextField();
        JTextField campo2 = new JTextField();

        JComboBox<String> operacoes = new JComboBox<>(new String[]{"+","-","*","/"});
        JButton calcular = new JButton("Calcular");
        JLabel resultado = new JLabel("Resultado:");

        //adiciopnando componentes
        frame.add(campo1);
        frame.add(operacoes);
        frame.add(campo2);
        frame.add(calcular);
        frame.add(resultado);
        
    

        //evento pra calculo
        calcular.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){

                try {
                    double valor1 = Double.parseDouble(campo1.getText());
                    double valor2 = Double.parseDouble(campo2.getText());
                    double res = 0;
                    String operacao = (String) operacoes.getSelectedItem();

                    switch (operacao) {
                        case "+":
                            res = valor1 + valor2;
                            break;
                        case "-":
                            res = valor1 - valor2;
                            break;
                        case "*":
                            res = valor1 * valor2;
                            break;
                            case "/":
                            if (valor2 == 0) {
                                throw new ArithmeticException("Divisão por zero");
                            }
                            res = valor1 / valor2;
                            break;
                        default:
                            throw new AssertionError();
                    }
                

                    resultado.setText("Resultado: " + res);
                    
                } catch (NumberFormatException ex) {
                    resultado.setText("Erro: Entrada inválida");
                } catch (ArithmeticException ex) {
                    resultado.setText("Erro: " + ex.getMessage());
                }
            }
        });

        // Exibir frame
        frame.setVisible(true);
    }
}