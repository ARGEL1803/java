import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class JuegoGrafico extends JPanel implements KeyListener, ActionListener {

    int x = 100, y = 100;
    int size = 40;
    Color colorJugador = Color.RED;

    int npcX = 300, npcY = 200;
    int npcSize = 40;
    int trofeoX, trofeoY;
    int trofeoSize = 20;
    int trofeos = 0;
    int TROFEOS_GANAR = 10;

    boolean juegoTerminado = false;

    Random rand = new Random();
    Timer timer;

    public JuegoGrafico() {
        setFocusable(true);
        addKeyListener(this);

        generarTrofeo();

        timer = new Timer(150, this);
        timer.start();
    }
    private void generarTrofeo() {
        trofeoX = rand.nextInt(550);
        trofeoY = rand.nextInt(330);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Fondo cancha
        g.setColor(new Color(0, 150, 0));
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);

        // Cancha
        g.drawRect(20, 20, getWidth() - 40, getHeight() - 40);
        g.drawLine(getWidth() / 2, 20, getWidth() / 2, getHeight() - 20);
        g.drawOval(getWidth() / 2 - 50, getHeight() / 2 - 50, 100, 100);

        // Jugador
        g.setColor(colorJugador);
        g.fillOval(x, y, size, size);

        // NPC
        g.setColor(Color.BLUE);
        g.fillOval(npcX, npcY, npcSize, npcSize);

        // Trofeo
        g.setColor(Color.YELLOW);
        g.fillOval(trofeoX, trofeoY, trofeoSize, trofeoSize);

        // Texto
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Coordenadas: (" + x + ", " + y + ")", 10, 20);
        g.drawString("Trofeos: " + trofeos, 10, 40);

        if (juegoTerminado) {
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("¡GANASTE!", getWidth()/2 - 80, getHeight()/2);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (juegoTerminado) return;

        int tecla = e.getKeyCode();

        if (tecla == KeyEvent.VK_W) y -= 10;
        if (tecla == KeyEvent.VK_S) y += 10;
        if (tecla == KeyEvent.VK_A) x -= 10;
        if (tecla == KeyEvent.VK_D) x += 10;

        if (Math.abs(x - trofeoX) < 30 && Math.abs(y - trofeoY) < 30) {
            trofeos++;
            generarTrofeo();
        }

        if (trofeos >= TROFEOS_GANAR) {
            juegoTerminado = true;
        }

        repaint();
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
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
