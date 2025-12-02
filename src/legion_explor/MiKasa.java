package legion_explor;
import java.util.Stack;
import javax.swing.JOptionPane;
import java.util.EmptyStackException;

public class MiKasa extends javax.swing.JFrame {

    // Variables globales
    double num1 = 0, num2 = 0, resultado = 0;
    String operacion = "";
    String displayCompleto = "";
    double memoria = 0;

    public MiKasa() {
        initComponents();
        setVisible(true);
    }

    private boolean esNumero(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String obtenerUltimoNumero(String texto) {
        if (texto.isEmpty()) return "";
        String[] partes = texto.split(" ");
        for (int i = partes.length - 1; i >= 0; i--) {
            if (esNumero(partes[i])) {
                return partes[i];
            }
        }
        return esNumero(texto) ? texto : "";
    }

    private void agregarNumeroConMultiplicacionImplicita(String numero) {
        if (!displayCompleto.isEmpty()) {
            char ultimoCaracter = displayCompleto.charAt(displayCompleto.length() - 1);
            // Si el último carácter es un paréntesis derecho, agregar multiplicación
            if (ultimoCaracter == ')') {
                displayCompleto += " × " + numero;
            } else {
                displayCompleto += numero;
            }
        } else {
            displayCompleto += numero;
        }
        Ventana.setText(displayCompleto);
    }

    // Método principal para evaluar expresiones
    private double evaluarExpresion(String expresion) {
        try {
            // Limpiar y preparar la expresión
            expresion = expresion.replace(" ", "")
                                .replace("×", "*")
                                .replace("÷", "/")
                                .replace("^", "^")  // Cambiado de ** a ^
                                .replace("ⁿ√", "root");
            
            return evaluar(expresion);
        } catch (Exception e) {
            throw new RuntimeException("Error evaluando expresión: " + expresion);
        }
    }

    // Método recursivo para evaluar la expresión
    private double evaluar(String expresion) {
        Stack<Double> numeros = new Stack<>();
        Stack<Character> operadores = new Stack<>();
        
        try {
            for (int i = 0; i < expresion.length(); i++) {
                char c = expresion.charAt(i);
                
                if (Character.isDigit(c) || c == '.') {
                    // Leer número completo
                    StringBuilder sb = new StringBuilder();
                    while (i < expresion.length() && 
                          (Character.isDigit(expresion.charAt(i)) || expresion.charAt(i) == '.')) {
                        sb.append(expresion.charAt(i++));
                    }
                    i--;
                    numeros.push(Double.parseDouble(sb.toString()));
                } 
                else if (c == '(') {
                    operadores.push(c);
                } 
                else if (c == ')') {
                    while (!operadores.isEmpty() && operadores.peek() != '(') {
                        numeros.push(aplicarOperador(operadores.pop(), numeros.pop(), numeros.pop()));
                    }
                    operadores.pop(); // Remover '('
                } 
                else if (esOperador(c)) {
                    while (!operadores.isEmpty() && precedencia(c) <= precedencia(operadores.peek())) {
                        numeros.push(aplicarOperador(operadores.pop(), numeros.pop(), numeros.pop()));
                    }
                    operadores.push(c);
                }
            }
            
            while (!operadores.isEmpty()) {
                numeros.push(aplicarOperador(operadores.pop(), numeros.pop(), numeros.pop()));
            }
            
            return numeros.pop();
        } catch (EmptyStackException e) {
            throw new RuntimeException("Expresión inválida");
        }
    }

    // Verificar si es operador - CORREGIDO
    private boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    // Precedencia de operadores - CORREGIDO
    private int precedencia(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^': // Potencia (cambiamos ** por ^)
                return 3;
            default:
                return 0;
        }
    }

    // Aplicar operador - CORREGIDO
    private double aplicarOperador(char op, double b, double a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': 
                if (b == 0) throw new RuntimeException("División por cero");
                return a / b;
            case '^': return Math.pow(a, b); // Cambiamos ** por ^
            default: throw new RuntimeException("Operador desconocido: " + op);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnArcsin = new javax.swing.JButton();
        btnCsc = new javax.swing.JButton();
        btnParentesisIzq = new javax.swing.JButton();
        btnPunto = new javax.swing.JButton();
        btnParantesisDer = new javax.swing.JButton();
        btn0 = new javax.swing.JButton();
        btnPorcentaje = new javax.swing.JButton();
        btnIgual = new javax.swing.JButton();
        btnDividir = new javax.swing.JButton();
        btnSumar = new javax.swing.JButton();
        brnTan = new javax.swing.JButton();
        btnArccos = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btnRaizn = new javax.swing.JButton();
        btnMultiplicar = new javax.swing.JButton();
        btnRaiz = new javax.swing.JButton();
        btnCot = new javax.swing.JButton();
        btnElevada = new javax.swing.JButton();
        btnArctan = new javax.swing.JButton();
        btnPi = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btnCE = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btnSin = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btncici = new javax.swing.JButton();
        btnRestar = new javax.swing.JButton();
        btnex = new javax.swing.JButton();
        btnSec = new javax.swing.JButton();
        btnLog = new javax.swing.JButton();
        btnIn = new javax.swing.JButton();
        Ventana = new javax.swing.JLabel();
        btnMS = new javax.swing.JButton();
        btnMR = new javax.swing.JButton();
        btnMC = new javax.swing.JButton();
        btnMmas = new javax.swing.JButton();
        btnMmenos = new javax.swing.JButton();
        btnX = new javax.swing.JButton();
        btnNegativo_Positivo = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btnC = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btnCos = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));

        btnArcsin.setBackground(new java.awt.Color(255, 244, 102));
        btnArcsin.setText("arcsin");
        btnArcsin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnArcsin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArcsinActionPerformed(evt);
            }
        });

        btnCsc.setBackground(new java.awt.Color(255, 163, 102));
        btnCsc.setText("csc");
        btnCsc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnCsc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCscActionPerformed(evt);
            }
        });

        btnParentesisIzq.setBackground(new java.awt.Color(255, 102, 102));
        btnParentesisIzq.setText("(");
        btnParentesisIzq.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnParentesisIzq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParentesisIzqActionPerformed(evt);
            }
        });

        btnPunto.setBackground(new java.awt.Color(178, 178, 178));
        btnPunto.setText(".");
        btnPunto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnPunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPuntoActionPerformed(evt);
            }
        });

        btnParantesisDer.setBackground(new java.awt.Color(255, 102, 102));
        btnParantesisDer.setText(")");
        btnParantesisDer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnParantesisDer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParantesisDerActionPerformed(evt);
            }
        });

        btn0.setBackground(new java.awt.Color(178, 178, 178));
        btn0.setText("0");
        btn0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn0ActionPerformed(evt);
            }
        });

        btnPorcentaje.setBackground(new java.awt.Color(255, 102, 102));
        btnPorcentaje.setText("%");
        btnPorcentaje.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnPorcentaje.setIconTextGap(10);
        btnPorcentaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPorcentajeActionPerformed(evt);
            }
        });

        btnIgual.setBackground(new java.awt.Color(255, 102, 102));
        btnIgual.setText("=");
        btnIgual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnIgual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIgualActionPerformed(evt);
            }
        });

        btnDividir.setBackground(new java.awt.Color(255, 102, 102));
        btnDividir.setText("÷  ");
        btnDividir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnDividir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDividirActionPerformed(evt);
            }
        });

        btnSumar.setBackground(new java.awt.Color(255, 102, 102));
        btnSumar.setText("+");
        btnSumar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSumar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSumarActionPerformed(evt);
            }
        });

        brnTan.setBackground(new java.awt.Color(255, 163, 102));
        brnTan.setText("tan");
        brnTan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        brnTan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brnTanActionPerformed(evt);
            }
        });

        btnArccos.setBackground(new java.awt.Color(255, 244, 102));
        btnArccos.setText("arccos");
        btnArccos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnArccos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArccosActionPerformed(evt);
            }
        });

        btn7.setBackground(new java.awt.Color(178, 178, 178));
        btn7.setText("7");
        btn7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });

        btn8.setBackground(new java.awt.Color(178, 178, 178));
        btn8.setText("8");
        btn8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });

        btn9.setBackground(new java.awt.Color(178, 178, 178));
        btn9.setText("9");
        btn9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });

        btnRaizn.setBackground(new java.awt.Color(102, 148, 255));
        btnRaizn.setText("ⁿ√");
        btnRaizn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnRaizn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRaiznActionPerformed(evt);
            }
        });

        btnMultiplicar.setBackground(new java.awt.Color(255, 102, 102));
        btnMultiplicar.setText("x");
        btnMultiplicar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnMultiplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMultiplicarActionPerformed(evt);
            }
        });

        btnRaiz.setBackground(new java.awt.Color(102, 148, 255));
        btnRaiz.setText("√");
        btnRaiz.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnRaiz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRaizActionPerformed(evt);
            }
        });

        btnCot.setBackground(new java.awt.Color(255, 163, 102));
        btnCot.setText("cot");
        btnCot.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnCot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCotActionPerformed(evt);
            }
        });

        btnElevada.setBackground(new java.awt.Color(102, 148, 255));
        btnElevada.setText("ʌ");
        btnElevada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnElevada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnElevadaActionPerformed(evt);
            }
        });

        btnArctan.setBackground(new java.awt.Color(255, 244, 102));
        btnArctan.setText("arctan");
        btnArctan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnArctan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArctanActionPerformed(evt);
            }
        });

        btnPi.setBackground(new java.awt.Color(102, 148, 255));
        btnPi.setText("π");
        btnPi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnPi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPiActionPerformed(evt);
            }
        });

        btn4.setBackground(new java.awt.Color(178, 178, 178));
        btn4.setText("4");
        btn4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });

        btnCE.setBackground(new java.awt.Color(112, 255, 102));
        btnCE.setText("CE");
        btnCE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnCE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCEActionPerformed(evt);
            }
        });

        btn5.setBackground(new java.awt.Color(178, 178, 178));
        btn5.setText("5");
        btn5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });

        btnSin.setBackground(new java.awt.Color(255, 163, 102));
        btnSin.setText("sin");
        btnSin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSinActionPerformed(evt);
            }
        });

        btn6.setBackground(new java.awt.Color(178, 178, 178));
        btn6.setText("6");
        btn6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });

        btncici.setBackground(new java.awt.Color(229, 102, 255));
        btncici.setText("xⁿ");
        btncici.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btncici.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnciciActionPerformed(evt);
            }
        });

        btnRestar.setBackground(new java.awt.Color(255, 102, 102));
        btnRestar.setText("-");
        btnRestar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnRestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestarActionPerformed(evt);
            }
        });

        btnex.setBackground(new java.awt.Color(229, 102, 255));
        btnex.setText("eˣ ");
        btnex.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexActionPerformed(evt);
            }
        });

        btnSec.setBackground(new java.awt.Color(255, 163, 102));
        btnSec.setText("sec");
        btnSec.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSecActionPerformed(evt);
            }
        });

        btnLog.setBackground(new java.awt.Color(229, 102, 255));
        btnLog.setText("log");
        btnLog.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogActionPerformed(evt);
            }
        });

        btnIn.setBackground(new java.awt.Color(229, 102, 255));
        btnIn.setText("In");
        btnIn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInActionPerformed(evt);
            }
        });

        Ventana.setBackground(new java.awt.Color(255, 255, 255));
        Ventana.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        Ventana.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Ventana.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        Ventana.setEnabled(false);
        Ventana.setOpaque(true);

        btnMS.setBackground(new java.awt.Color(102, 255, 234));
        btnMS.setText("MS");
        btnMS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMSActionPerformed(evt);
            }
        });

        btnMR.setBackground(new java.awt.Color(102, 255, 234));
        btnMR.setText("MR");
        btnMR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnMR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMRActionPerformed(evt);
            }
        });

        btnMC.setBackground(new java.awt.Color(102, 255, 234));
        btnMC.setText("MC");
        btnMC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnMC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMCActionPerformed(evt);
            }
        });

        btnMmas.setBackground(new java.awt.Color(102, 255, 234));
        btnMmas.setText("M+");
        btnMmas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnMmas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMmasActionPerformed(evt);
            }
        });

        btnMmenos.setBackground(new java.awt.Color(102, 255, 234));
        btnMmenos.setText("M-");
        btnMmenos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnMmenos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMmenosActionPerformed(evt);
            }
        });

        btnX.setBackground(new java.awt.Color(102, 148, 255));
        btnX.setText("|x|");
        btnX.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXActionPerformed(evt);
            }
        });

        btnNegativo_Positivo.setBackground(new java.awt.Color(178, 178, 178));
        btnNegativo_Positivo.setText("+/-");
        btnNegativo_Positivo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnNegativo_Positivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNegativo_PositivoActionPerformed(evt);
            }
        });

        btn1.setBackground(new java.awt.Color(178, 178, 178));
        btn1.setText("1");
        btn1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        btnC.setBackground(new java.awt.Color(112, 255, 102));
        btnC.setText("C");
        btnC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCActionPerformed(evt);
            }
        });

        btn2.setBackground(new java.awt.Color(178, 178, 178));
        btn2.setText("2");
        btn2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        btnCos.setBackground(new java.awt.Color(255, 163, 102));
        btnCos.setText("cos");
        btnCos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnCos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCosActionPerformed(evt);
            }
        });

        btn3.setBackground(new java.awt.Color(178, 178, 178));
        btn3.setText("3");
        btn3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Ventana, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnX, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRaizn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnRaiz, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnElevada, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPi, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCE, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnMS, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnMR, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnMC, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnMmas, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnMmenos, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSin, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btncici, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnex, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLog, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnIn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnC, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCos, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnArcsin, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnParentesisIzq, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnParantesisDer, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDividir, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(brnTan, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnArccos, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnMultiplicar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCot, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnArctan, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRestar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnCsc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSec, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnNegativo_Positivo, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnPunto, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(btn0, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnIgual, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSumar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(Ventana, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMR, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMmas, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMmenos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMS, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnX, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRaizn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRaiz, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnElevada, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCE, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncici, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnex, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLog, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnArcsin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnParentesisIzq, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnParantesisDer, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDividir, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(brnTan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnArccos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMultiplicar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCot, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnArctan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRestar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSec, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNegativo_Positivo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPunto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn0, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnIgual, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCsc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnSumar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCscActionPerformed
        try {
        String ultimoNumero = obtenerUltimoNumero(displayCompleto);
        if (!ultimoNumero.isEmpty()) {
            double valor = Double.parseDouble(ultimoNumero);
            double resultado = 1.0 / Math.sin(Math.toRadians(valor));
            Ventana.setText(String.valueOf(resultado));
            displayCompleto = String.valueOf(resultado);
        }
    } catch (Exception e) {
        Ventana.setText("Error csc");
    }
    }//GEN-LAST:event_btnCscActionPerformed

    private void btnParentesisIzqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParentesisIzqActionPerformed
        // Agrega funcion parentesis izquierda
             if (!displayCompleto.isEmpty()) {
        char ultimo = displayCompleto.charAt(displayCompleto.length() - 1);
        // Si el último carácter es número o ), agregar × antes de (
        if (Character.isDigit(ultimo) || ultimo == ')') {
            displayCompleto += " × (";
        } else {
            displayCompleto += "(";
        }
    } else {
        displayCompleto += "(";
    }
    Ventana.setText(displayCompleto);
    }//GEN-LAST:event_btnParentesisIzqActionPerformed

    private void btnParantesisDerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParantesisDerActionPerformed
        // Agrega funcion parentesisi derecha
        displayCompleto += ")";
    Ventana.setText(displayCompleto);
    }//GEN-LAST:event_btnParantesisDerActionPerformed

    private void btn0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn0ActionPerformed
        // Agregar funcionamiento de boton 0
       displayCompleto += "0";
Ventana.setText(displayCompleto);
    }//GEN-LAST:event_btn0ActionPerformed

    private void btnPorcentajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPorcentajeActionPerformed
        // Agrega funcion porcentaje
       try {
        if (!displayCompleto.isEmpty()) {
            String[] partes = displayCompleto.split(" ");
            if (partes.length >= 3) {
                // Si hay una operación en curso: "numero operador porcentaje"
                double base = Double.parseDouble(partes[partes.length - 3]);
                String operador = partes[partes.length - 2];
                double porcentaje = Double.parseDouble(partes[partes.length - 1]);
                
                double resultadoParcial = (base * porcentaje) / 100.0;
                
                // Reemplazar la última parte con el cálculo
                displayCompleto = displayCompleto.substring(0, displayCompleto.lastIndexOf(partes[partes.length - 1])) + resultadoParcial;
                Ventana.setText(displayCompleto);
            } else {
                // Si solo hay un número, calcular su porcentaje
                String ultimoNumero = obtenerUltimoNumero(displayCompleto);
                if (!ultimoNumero.isEmpty()) {
                    double valor = Double.parseDouble(ultimoNumero);
                    double porcentaje = valor / 100.0;
                    
                    int inicio = displayCompleto.lastIndexOf(ultimoNumero);
                    displayCompleto = displayCompleto.substring(0, inicio) + porcentaje;
                    Ventana.setText(displayCompleto);
                }
            }
        }
    } catch (Exception e) {
        Ventana.setText("Error %");
    }
    }//GEN-LAST:event_btnPorcentajeActionPerformed

    private void btnIgualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIgualActionPerformed
        // Agrega funcion igual
      try {
        // Evaluar la expresión completa
        double resultado = evaluarExpresion(displayCompleto);
        
        // Mostrar resultado
        String resultadoFormateado = String.format("%.10f", resultado).replaceAll("\\.?0*$", "");
        Ventana.setText(resultadoFormateado);
        displayCompleto = resultadoFormateado;
        num1 = resultado;
        
    } catch (Exception e) {
        Ventana.setText("Error: " + e.getMessage());
        e.printStackTrace();
    }
    }//GEN-LAST:event_btnIgualActionPerformed

    private void btnDividirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDividirActionPerformed
        // Agrega funcion dividir
         try {
            String ultimoNumero = obtenerUltimoNumero(displayCompleto);
            if (!ultimoNumero.isEmpty()) {
                num1 = Double.parseDouble(ultimoNumero);
            }
            displayCompleto += " ÷ ";
            Ventana.setText(displayCompleto);
            operacion = "/";
        } catch (Exception e) {
            Ventana.setText("Error");
        }
    
        
    }//GEN-LAST:event_btnDividirActionPerformed

    private void btnSumarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSumarActionPerformed
        //Agrega funcion de suma
        try {
            String ultimoNumero = obtenerUltimoNumero(displayCompleto);
            if (!ultimoNumero.isEmpty()) {
                num1 = Double.parseDouble(ultimoNumero);
            }
            displayCompleto += " + ";
            Ventana.setText(displayCompleto);
            operacion = "+";
        } catch (Exception e) {
            Ventana.setText("Error");
        }
    
    }//GEN-LAST:event_btnSumarActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        // Agregar fuuncionamiento de boton 7
        displayCompleto += "7";
Ventana.setText(displayCompleto);
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        // Agregar fuuncionamiento de boton 8
   displayCompleto += "8";
Ventana.setText(displayCompleto);
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        // Agregar fuuncionamiento de boton 9
        displayCompleto += "9";
Ventana.setText(displayCompleto);
    }//GEN-LAST:event_btn9ActionPerformed

    private void btnRaiznActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRaiznActionPerformed
    try {
        double valorX = Double.parseDouble(Ventana.getText());

        String inputN = JOptionPane.showInputDialog(this, "Ingresa el índice de la raíz (n):");
        double indiceN = Double.parseDouble(inputN);

        double resultado = Math.pow(valorX, 1.0 / indiceN);

        Ventana.setText(String.valueOf(resultado));

    } catch (Exception e) {
        Ventana.setText("Error");
    }
    }//GEN-LAST:event_btnRaiznActionPerformed

    private void btnMultiplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMultiplicarActionPerformed
        // Agrega funcion multiplicar
       try {
            String ultimoNumero = obtenerUltimoNumero(displayCompleto);
            if (!ultimoNumero.isEmpty()) {
                num1 = Double.parseDouble(ultimoNumero);
            }
            displayCompleto += " × ";
            Ventana.setText(displayCompleto);
            operacion = "*";
        } catch (Exception e) {
            Ventana.setText("Error");
        }

    }//GEN-LAST:event_btnMultiplicarActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        // Agregar fuuncionamiento de boton 4
         displayCompleto += "4";
        Ventana.setText(displayCompleto);

    }//GEN-LAST:event_btn4ActionPerformed

    private void btnCEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCEActionPerformed
        // Limpia ultimo digito
   if (!displayCompleto.isEmpty()) {
        // Encontrar el último número en el display
        String ultimoNumero = obtenerUltimoNumero(displayCompleto);
        
        if (!ultimoNumero.isEmpty() && ultimoNumero.length() > 1) {
            // Si el último número tiene más de 1 dígito, borrar el último carácter
            int inicioUltimoNumero = displayCompleto.lastIndexOf(ultimoNumero);
            String nuevoUltimoNumero = ultimoNumero.substring(0, ultimoNumero.length() - 1);
            
            displayCompleto = displayCompleto.substring(0, inicioUltimoNumero) + nuevoUltimoNumero;
            Ventana.setText(displayCompleto);
        } else if (!ultimoNumero.isEmpty() && ultimoNumero.length() == 1) {
            // Si el último número tiene solo 1 dígito, borrar todo el número
            int inicioUltimoNumero = displayCompleto.lastIndexOf(ultimoNumero);
            displayCompleto = displayCompleto.substring(0, inicioUltimoNumero);
            Ventana.setText(displayCompleto);
        }
   }
    }//GEN-LAST:event_btnCEActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        // Agregar fuuncionamiento de boton 5
     displayCompleto += "5";
Ventana.setText(displayCompleto);
    }//GEN-LAST:event_btn5ActionPerformed

    private void btnSinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSinActionPerformed
       try {
        String ultimoNumero = obtenerUltimoNumero(displayCompleto);
        if (!ultimoNumero.isEmpty()) {
            double valor = Double.parseDouble(ultimoNumero);
            double resultado = Math.sin(Math.toRadians(valor));
            Ventana.setText(String.valueOf(resultado));
            displayCompleto = String.valueOf(resultado);
        }
    } catch (Exception e) {
        Ventana.setText("Error sin");
    }
    }//GEN-LAST:event_btnSinActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        // Agregar fuuncionamiento de boton 6
            displayCompleto += "6";
        Ventana.setText(displayCompleto);
    }//GEN-LAST:event_btn6ActionPerformed

    private void btnciciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnciciActionPerformed
       try {
        double base = Double.parseDouble(Ventana.getText());

        String inputN = JOptionPane.showInputDialog(this, "Ingresa el exponente n:");
        double exponente = Double.parseDouble(inputN);

        double resultado = Math.pow(base, exponente);

        Ventana.setText(String.valueOf(resultado));

    } catch (Exception e) {
        Ventana.setText("Error");
    }
    }//GEN-LAST:event_btnciciActionPerformed

    private void btnRestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestarActionPerformed
        //  Agrega funcion suma
        try {
            String ultimoNumero = obtenerUltimoNumero(displayCompleto);
            if (!ultimoNumero.isEmpty()) {
                num1 = Double.parseDouble(ultimoNumero);
            }
            displayCompleto += " - ";
            Ventana.setText(displayCompleto);
            operacion = "-";
        } catch (Exception e) {
            Ventana.setText("Error");
        }
    }//GEN-LAST:event_btnRestarActionPerformed

    private void btnMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMSActionPerformed
        // MS - Memory Store: Guarda el valor actual en memoria
    try {
        String ultimoNumero = obtenerUltimoNumero(displayCompleto);
        if (!ultimoNumero.isEmpty()) {
            memoria = Double.parseDouble(ultimoNumero);
            Ventana.setText("M = " + memoria); // Mostrar confirmación
            // Restaurar el display después de un breve tiempo
            new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        Ventana.setText(displayCompleto);
                    }
                },
                1000 // 1 segundo
            );
        }
    } catch (Exception e) {
        Ventana.setText("Error MS");
    }
    }//GEN-LAST:event_btnMSActionPerformed

    private void btnMRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMRActionPerformed
        // MR - Memory Recall: Recupera el valor de memoria
    if (memoria != 0) {
        // Si hay una operación en curso, agregar a la operación
        if (displayCompleto.endsWith(" + ") || displayCompleto.endsWith(" - ") || 
            displayCompleto.endsWith(" × ") || displayCompleto.endsWith(" ÷ ")) {
            displayCompleto += memoria;
        } else {
            // Si no hay operación, reemplazar el display
            displayCompleto = String.valueOf(memoria);
        }
        Ventana.setText(displayCompleto);
    }
    }//GEN-LAST:event_btnMRActionPerformed

    private void btnXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXActionPerformed
         try {
        String ultimoNumero = obtenerUltimoNumero(displayCompleto);
        if (!ultimoNumero.isEmpty()) {
            double valor = Double.parseDouble(ultimoNumero);
            double resultado = Math.abs(valor);
            Ventana.setText(String.valueOf(resultado));
            displayCompleto = String.valueOf(resultado);
        }
    } catch (Exception e) {
        Ventana.setText("Error |x|");
    }
    }//GEN-LAST:event_btnXActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        // Agregar fuuncionamiento de boton 1
         displayCompleto += "1";
Ventana.setText(displayCompleto);
    }//GEN-LAST:event_btn1ActionPerformed

    private void btnCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCActionPerformed
        // Agregar funcion de limpiar todo
                                      
    Ventana.setText("");
    num1 = 0;
    num2 = 0;
    resultado = 0;
    operacion = "";
    displayCompleto = "";
    }//GEN-LAST:event_btnCActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        // Agregar fuuncionamiento de boton 2
        displayCompleto += "2";
Ventana.setText(displayCompleto);
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        // Agregar fuuncionamiento de boton 3
       displayCompleto += "3";
Ventana.setText(displayCompleto);
    }//GEN-LAST:event_btn3ActionPerformed

    private void btnPuntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPuntoActionPerformed
        // Agrega funcion .
         if (!displayCompleto.contains(".")) {
        displayCompleto += ".";
        Ventana.setText(displayCompleto);
         }
    }//GEN-LAST:event_btnPuntoActionPerformed

    private void btnMCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMCActionPerformed
         // MC - Memory Clear: Limpia la memoria
    memoria = 0;
    Ventana.setText("MC"); // Mostrar confirmación
    // Restaurar el display después de un breve tiempo
    new java.util.Timer().schedule(
        new java.util.TimerTask() {
            @Override
            public void run() {
                Ventana.setText(displayCompleto);
            }
        },
        1000 // 1 segundo
    );
    }//GEN-LAST:event_btnMCActionPerformed

    private void btnMmasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMmasActionPerformed
         // M+ - Memory Add: Suma el valor actual a la memoria
    try {
        String ultimoNumero = obtenerUltimoNumero(displayCompleto);
        if (!ultimoNumero.isEmpty()) {
            memoria += Double.parseDouble(ultimoNumero);
            Ventana.setText("M+ = " + memoria); // Mostrar confirmación
            // Restaurar el display después de un breve tiempo
            new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        Ventana.setText(displayCompleto);
                    }
                },
                1000 // 1 segundo
            );
        }
    } catch (Exception e) {
        Ventana.setText("Error M+");
    }
    }//GEN-LAST:event_btnMmasActionPerformed

    private void btnMmenosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMmenosActionPerformed
         // M- - Memory Subtract: Resta el valor actual de la memoria
    try {
        String ultimoNumero = obtenerUltimoNumero(displayCompleto);
        if (!ultimoNumero.isEmpty()) {
            memoria -= Double.parseDouble(ultimoNumero);
            Ventana.setText("M- = " + memoria); // Mostrar confirmación
            // Restaurar el display después de un breve tiempo
            new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        Ventana.setText(displayCompleto);
                    }
                },
                1000 // 1 segundo
            );
        }
    } catch (Exception e) {
        Ventana.setText("Error M-");
    }
    }//GEN-LAST:event_btnMmenosActionPerformed

    private void btnNegativo_PositivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNegativo_PositivoActionPerformed
       try {
        String ultimoNumero = obtenerUltimoNumero(displayCompleto);
        if (!ultimoNumero.isEmpty()) {
            double valor = Double.parseDouble(ultimoNumero);
            valor = -valor;
            
            // Reemplazar el último número con el valor cambiado
            int inicio = displayCompleto.lastIndexOf(ultimoNumero);
            displayCompleto = displayCompleto.substring(0, inicio) + valor;
            Ventana.setText(displayCompleto);
        }
    } catch (Exception e) {
        Ventana.setText("Error");
    }
    }//GEN-LAST:event_btnNegativo_PositivoActionPerformed

    private void btnCosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCosActionPerformed
        try {
        String ultimoNumero = obtenerUltimoNumero(displayCompleto);
        if (!ultimoNumero.isEmpty()) {
            double valor = Double.parseDouble(ultimoNumero);
            double resultado = Math.cos(Math.toRadians(valor));
            Ventana.setText(String.valueOf(resultado));
            displayCompleto = String.valueOf(resultado);
        }
    } catch (Exception e) {
        Ventana.setText("Error cos");
    }
    }//GEN-LAST:event_btnCosActionPerformed

    private void brnTanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brnTanActionPerformed
         try {
        String ultimoNumero = obtenerUltimoNumero(displayCompleto);
        if (!ultimoNumero.isEmpty()) {
            double valor = Double.parseDouble(ultimoNumero);
            double resultado = Math.tan(Math.toRadians(valor));
            Ventana.setText(String.valueOf(resultado));
            displayCompleto = String.valueOf(resultado);
        }
    } catch (Exception e) {
        Ventana.setText("Error tan");
    }
    }//GEN-LAST:event_brnTanActionPerformed

    private void btnCotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCotActionPerformed
        try {
        String ultimoNumero = obtenerUltimoNumero(displayCompleto);
        if (!ultimoNumero.isEmpty()) {
            double valor = Double.parseDouble(ultimoNumero);
            double resultado = 1.0 / Math.tan(Math.toRadians(valor));
            Ventana.setText(String.valueOf(resultado));
            displayCompleto = String.valueOf(resultado);
        }
    } catch (Exception e) {
        Ventana.setText("Error cot");
    }
    }//GEN-LAST:event_btnCotActionPerformed

    private void btnSecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSecActionPerformed
        try {
        String ultimoNumero = obtenerUltimoNumero(displayCompleto);
        if (!ultimoNumero.isEmpty()) {
            double valor = Double.parseDouble(ultimoNumero);
            double resultado = 1.0 / Math.cos(Math.toRadians(valor));
            Ventana.setText(String.valueOf(resultado));
            displayCompleto = String.valueOf(resultado);
        }
    } catch (Exception e) {
        Ventana.setText("Error sec");
    }
    }//GEN-LAST:event_btnSecActionPerformed

    private void btnArcsinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArcsinActionPerformed
        try {
        String ultimoNumero = obtenerUltimoNumero(displayCompleto);
        if (!ultimoNumero.isEmpty()) {
            double valor = Double.parseDouble(ultimoNumero);
            // arcsin solo funciona entre -1 y 1
            if (valor >= -1 && valor <= 1) {
                double resultado = Math.asin(valor);
                Ventana.setText(String.valueOf(resultado));
                displayCompleto = String.valueOf(resultado);
            } else {
                Ventana.setText("Error: valor debe estar entre -1 y 1");
            }
        }
    } catch (Exception e) {
        Ventana.setText("Error arcsin");
    }
    }//GEN-LAST:event_btnArcsinActionPerformed

    private void btnArccosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArccosActionPerformed
         try {
        String ultimoNumero = obtenerUltimoNumero(displayCompleto);
        if (!ultimoNumero.isEmpty()) {
            double valor = Double.parseDouble(ultimoNumero);
            // arccos solo funciona entre -1 y 1
            if (valor >= -1 && valor <= 1) {
                double resultado = Math.acos(valor);
                Ventana.setText(String.valueOf(resultado));
                displayCompleto = String.valueOf(resultado);
            } else {
                Ventana.setText("Error: valor debe estar entre -1 y 1");
            }
        }
    } catch (Exception e) {
        Ventana.setText("Error arccos");
    }
    }//GEN-LAST:event_btnArccosActionPerformed

    private void btnArctanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArctanActionPerformed
         try {
        String ultimoNumero = obtenerUltimoNumero(displayCompleto);
        if (!ultimoNumero.isEmpty()) {
            double valor = Double.parseDouble(ultimoNumero);
            double resultado = Math.atan(valor);
            Ventana.setText(String.valueOf(resultado));
            displayCompleto = String.valueOf(resultado);
        }
    } catch (Exception e) {
        Ventana.setText("Error arctan");
    }
    }//GEN-LAST:event_btnArctanActionPerformed

    private void btnexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexActionPerformed
          try {
        double valor = Double.parseDouble(Ventana.getText());

        double resultado = Math.exp(valor);  // e^x

        Ventana.setText(String.valueOf(resultado));

    } catch (Exception e) {
        Ventana.setText("Error");
    }
    }//GEN-LAST:event_btnexActionPerformed

    private void btnLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogActionPerformed
        try {
        String ultimoNumero = obtenerUltimoNumero(displayCompleto);
        if (!ultimoNumero.isEmpty()) {
            double valor = Double.parseDouble(ultimoNumero);
            if (valor > 0) {
                double resultado = Math.log10(valor);
                Ventana.setText(String.valueOf(resultado));
                displayCompleto = String.valueOf(resultado);
            } else {
                Ventana.setText("Error: número debe ser > 0");
            }
        }
    } catch (Exception e) {
        Ventana.setText("Error log");
    }
    }//GEN-LAST:event_btnLogActionPerformed

    private void btnInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInActionPerformed
         try {
        String ultimoNumero = obtenerUltimoNumero(displayCompleto);
        if (!ultimoNumero.isEmpty()) {
            double valor = Double.parseDouble(ultimoNumero);
            if (valor > 0) {
                double resultado = Math.log(valor);
                Ventana.setText(String.valueOf(resultado));
                displayCompleto = String.valueOf(resultado);
            } else {
                Ventana.setText("Error: número debe ser > 0");
            }
        }
    } catch (Exception e) {
        Ventana.setText("Error ln");
    }
    }//GEN-LAST:event_btnInActionPerformed

    private void btnPiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPiActionPerformed
         displayCompleto += "3.141592653589793";
    Ventana.setText(displayCompleto);
    }//GEN-LAST:event_btnPiActionPerformed

    private void btnElevadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnElevadaActionPerformed
         try {
        String ultimoNumero = obtenerUltimoNumero(displayCompleto);
        if (!ultimoNumero.isEmpty()) {
            num1 = Double.parseDouble(ultimoNumero);
            displayCompleto += " ^ ";
            Ventana.setText(displayCompleto);
            operacion = "^";
        }
    } catch (Exception e) {
        Ventana.setText("Error ʌ");
    }
    }//GEN-LAST:event_btnElevadaActionPerformed

    private void btnRaizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRaizActionPerformed
          try {
        // Obtener el texto actual del display
        String textoActual = Ventana.getText();
        
        // Si hay texto en el display, usarlo para calcular la raíz
        if (!textoActual.isEmpty() && esNumero(textoActual)) {
            double valor = Double.parseDouble(textoActual);
            if (valor >= 0) {
                double resultado = Math.sqrt(valor);
                Ventana.setText(String.valueOf(resultado));
                displayCompleto = String.valueOf(resultado);
            } else {
                Ventana.setText("Error: número negativo");
            }
        } else {
            // Si no hay número válido, mostrar error
            Ventana.setText("Error: ingrese un número");
        }
    } catch (Exception e) {
        Ventana.setText("Error √");
    }
    }//GEN-LAST:event_btnRaizActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MiKasa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MiKasa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MiKasa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MiKasa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MiKasa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Ventana;
    private javax.swing.JButton brnTan;
    private javax.swing.JButton btn0;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnArccos;
    private javax.swing.JButton btnArcsin;
    private javax.swing.JButton btnArctan;
    private javax.swing.JButton btnC;
    private javax.swing.JButton btnCE;
    private javax.swing.JButton btnCos;
    private javax.swing.JButton btnCot;
    private javax.swing.JButton btnCsc;
    private javax.swing.JButton btnDividir;
    private javax.swing.JButton btnElevada;
    private javax.swing.JButton btnIgual;
    private javax.swing.JButton btnIn;
    private javax.swing.JButton btnLog;
    private javax.swing.JButton btnMC;
    private javax.swing.JButton btnMR;
    private javax.swing.JButton btnMS;
    private javax.swing.JButton btnMmas;
    private javax.swing.JButton btnMmenos;
    private javax.swing.JButton btnMultiplicar;
    private javax.swing.JButton btnNegativo_Positivo;
    private javax.swing.JButton btnParantesisDer;
    private javax.swing.JButton btnParentesisIzq;
    private javax.swing.JButton btnPi;
    private javax.swing.JButton btnPorcentaje;
    private javax.swing.JButton btnPunto;
    private javax.swing.JButton btnRaiz;
    private javax.swing.JButton btnRaizn;
    private javax.swing.JButton btnRestar;
    private javax.swing.JButton btnSec;
    private javax.swing.JButton btnSin;
    private javax.swing.JButton btnSumar;
    private javax.swing.JButton btnX;
    private javax.swing.JButton btncici;
    private javax.swing.JButton btnex;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
