
/**@author Guilherme Wenger
 * 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class gameOfAnimals {
	public static JFrame jfbegin;
	public static JPanel jpBegin;
	public static JButton jbBegin;
	public static JLabel jlBegin;
	public static tree newTree = new tree();

	public static void main(String[] args) {
		try {
			String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (Exception e) {
			Logger.getLogger(gameOfAnimals.class.getName()).log(Level.SEVERE, null, e);
		}

		newTree = createTree(newTree);
		createScreen();

	}

	// criar tela principal
	public static void createScreen() {
		jfbegin = new JFrame("Animais");
		jfbegin.setSize(200, 100);
		jfbegin.setLocationRelativeTo(null);
		jfbegin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfbegin.setResizable(false);

		startingComponents();
	}

	// criar e inicializar componentes da tela principal
	public static void startingComponents() {

		jpBegin = new JPanel();
		jpBegin.setSize(200, 100);
		jpBegin.setLayout(null);
		jpBegin.setVisible(true);

		jlBegin = new JLabel("Pense em um animal.");
		jlBegin.setSize(150, 20);
		jlBegin.setLocation(48, 10);
		jlBegin.setVisible(true);

		jbBegin = new JButton("Ok");
		jbBegin.setSize(50, 20);
		jbBegin.setLocation(75, 40);
		jbBegin.setVisible(true);

		displaySreen();
	}

	// exibir tela
	public static void displaySreen() {
		jfbegin.setVisible(true);
		jfbegin.add(jpBegin);
		jpBegin.add(jlBegin);
		jpBegin.add(jbBegin);

		actions();

	}

	// ações da tela
	public static void actions() {
		jbBegin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				travel(newTree.root, "");
			}
		});

	}

	// inicializar a arvore
	public static tree createTree(tree newTree) {
		node initialNode = new node();
		newTree.root = initialNode;
		initialNode.question = "O animal que você pensou vive na água?";

		return newTree;
	}

	// percorrer a arvore
	public static void travel(node node, String animal) {
		String question = node.question;
		if (JOptionPane.showConfirmDialog(null, question, "Jogo dos animais", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
			if (!animal.equals("")) {
				animal = node.animalName;
			} else {
				animal = "Tubarão";
			}

			if (node.left != null) {
				travel(node.left, animal);
			} else {

				if (JOptionPane.showConfirmDialog(null, "O animal que você pensou é " + animal + "?",
						"Jogo dos animais", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

					JOptionPane.showMessageDialog(null, "Acertei novamente!");

				} else {

					node newNode = new node();
					newNode.animalName = JOptionPane.showInputDialog(null, "Qual animal você pensou?", "Desisto",
							JOptionPane.QUESTION_MESSAGE);
					newNode.tip = JOptionPane.showInputDialog(null,
							"Um(a) " + newNode.animalName + " _________ mas um " + animal + " não.", "Complete", 1);
					newNode.question = "O animal que você pensou " + newNode.tip + "?";
					node.left = newNode;
				}
			}

		} else {

			if (animal.equals("")) {
				animal = "Macaco";
			}

			if (node.right != null) {
				travel(node.right, animal);
			} else {

				if (JOptionPane.showConfirmDialog(null, "O animal que você pensou é " + animal + "?",
						"Jogo dos animais", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

					JOptionPane.showMessageDialog(null, "Acertei novamente!");

				} else {

					node newNode = new node();
					newNode.animalName = JOptionPane.showInputDialog(null, "Qual animal você pensou?", "Desisto",
							JOptionPane.QUESTION_MESSAGE);
					newNode.tip = JOptionPane.showInputDialog(null,
							"Um(a) " + newNode.animalName + " _________ mas um " + animal + " não.", "Complete", 1);
					newNode.question = "O animal que você pensou " + newNode.tip + "?";
					node.right = newNode;
				}
			}
		}
	}
}