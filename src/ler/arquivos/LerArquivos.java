package ler.arquivos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class LerArquivos {

    private JFrame frame;
    private JTextArea textArea;

    public static void main(String[] args) {
    	
        LerArquivos gui = new LerArquivos();
        gui.createAndShow();
        
    }

    private void createAndShow() {
    	
        frame = new JFrame("Ler Arquivo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(800, 60));

        JButton button = new JButton("Buscar");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int result = chooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String path = chooser.getSelectedFile().getAbsolutePath();
                    readFile(path);
                }
            }
        });

        panel.add(button);

        textArea = new JTextArea();
        textArea.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(textArea);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(new JLabel("Clique em buscar para selecionar o arquivo."), BorderLayout.SOUTH);

        frame.setPreferredSize(new Dimension(800, 600));

        frame.pack();
        frame.setVisible(true);
        
    }

    private void readFile(String path) {
    	
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
        	
            textArea.setText("");
            String line = br.readLine();
            
            while (line != null) {
                textArea.append(line + "\n");
                line = br.readLine();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




