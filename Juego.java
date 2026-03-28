import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class JuegoGrafico extends JPanel implements KeyListener, ActionListener {

    // Jugador principal
    int x = 100, y = 100;
    int size = 40;
    Color colorJugador = Color.RED;

    // NPC
    int npcX = 300, npcY = 200;
    int npcSize = 40;

    Random rand = new Random();
    Timer timer;

    public JuegoGrafico() {
        setFocusable(true);
        addKeyListener(this);

        // Timer para mover NPC automáticamente
        timer = new Timer(150, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Fondo (cancha)
        g.setColor(new Color(0, 150, 0));
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);

        // Bordes
        g.drawRect(20, 20, getWidth() - 40, getHeight() - 40);

        // Línea central
        g.drawLine(getWidth() / 2, 20, getWidth() / 2, getHeight() - 20);

        // Círculo central
        g.drawOval(getWidth() / 2 - 50, getHeight() / 2 - 50, 100, 100);

        // Porterías
        g.drawRect(20, getHeight()/2 - 40, 20, 80);
        g.drawRect(getWidth()-40, getHeight()/2 - 40, 20, 80);

        // Jugador
        g.setColor(colorJugador);
        g.fillOval(x, y, size, size);

        // NPC
        g.setColor(Color.BLUE);
        g.fillOval(npcX, npcY, npcSize, npcSize);

        // Coordenadas
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Coordenadas: (" + x + ", " + y + ")", 10, 20);
    }

    // Movimiento del jugador
    @Override
    public void keyPressed(KeyEvent e) {

        int tecla = e.getKeyCode();

        if (tecla == KeyEvent.VK_W) y -= 10;
        if (tecla == KeyEvent.VK_S) y += 10;
        if (tecla == KeyEvent.VK_A) x -= 10;
        if (tecla == KeyEvent.VK_D) x += 10;

        repaint();
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    // Movimiento automático del NPC
    @Override
    public void actionPerformed(ActionEvent e) {

        int dir = rand.nextInt(4);

        if (dir == 0) npcY -= 10;
        if (dir == 1) npcY += 10;
        if (dir == 2) npcX -= 10;
        if (dir == 3) npcX += 10;

        repaint();
    }

    public static void main(String[] args) {

        JFrame ventana = new JFrame("Juego tipo cancha");

        JuegoGrafico juego = new JuegoGrafico();
        ventana.add(juego);

        ventana.setSize(600, 400);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
    }
}
