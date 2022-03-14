import javax.swing.JFrame;
public class Main {
    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window.setSize(1000, 1000);
        window.setResizable(false);
        window.setTitle("2D Game");

        GamePanel gameP = new GamePanel(20, 10);
        window.add(gameP);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gameP.startGameThread();
    }
}
