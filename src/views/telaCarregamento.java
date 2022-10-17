package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

public class telaCarregamento extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaCarregamento frame = new telaCarregamento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public telaCarregamento() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"C:\\Users\\andre\\OneDrive\\Área de Trabalho\\Estudos\\Oracle\\Hotel\\src\\imagenes\\aH-40px.png"));
		setResizable(false);
		setTitle("Aguarde");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 376, 205);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		progressBar.setBounds(41, 79, 279, 40);
		contentPane.add(progressBar);

		JLabel lblNewLabel = new JLabel("Carregando...");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(41, 20, 279, 63);
		contentPane.add(lblNewLabel);
		setLocationRelativeTo(null);
	}

}
