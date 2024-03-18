package Graphics;

import Global.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;

public class Graphics {
    public enum State{
        CIRCLE,
        CROSS,
        EMPTY
    }
    private State[][] currentBoard;
    private JPanel panel;

    private Position mousePosition = new Position(0,0);

    public Graphics() {
        open();
    }

    private void open(){
        JFrame frame = new JFrame();
        panel = new JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;

                g2.setBackground(Color.WHITE);
                g2.clearRect(0,0,getWidth(),getHeight());

                g2.setStroke(new BasicStroke(3));

                //Set  anti-alias!
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                // Set anti-alias for text
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

                State currentState;
                Position mousePos = getRealToBoardPosition(mousePosition.x,mousePosition.y);
                Color hover = new Color(200,200,245);

                int block_size = getBlockSize();
                int margin = 30;
                int cx,cy,w,h;
                for(int y = 0;y < currentBoard.length;y++){
                    for(int x = 0;x < currentBoard[y].length;x++){
                        currentState = currentBoard[y][x];

                        cx = block_size*x;
                        cy = block_size*y;
                        w = block_size;
                        h = block_size;
                        switch (currentState){
                            case EMPTY -> {
                                if(x == mousePos.getX() && y == mousePos.getY()){
                                    g2.setColor(hover);
                                    g2.fillRect(cx,cy,w,h);
                                }
                            }
                            case CIRCLE -> {
                                g2.setColor(Color.BLUE);
                                g2.drawOval(cx+margin,cy+margin,w-margin*2,h-margin*2);
                            }
                            case CROSS -> {
                                g2.setColor(Color.RED);
                                g2.drawLine(cx+margin,cy+margin,w-margin,h-margin);
                                g2.drawLine(cx+w-margin,cy+margin,margin,h-margin);
                            }
                        }


                        g2.setColor(Color.BLACK);
                        g2.drawRect(cx,cy,w,h);
                    }
                }
            }
        };
        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Position pos = getRealToBoardPosition(e.getX(),e.getY());

                if(isOnBoard(pos.getX(),pos.getY())) {
                    clicked(new Position(pos.getX(), pos.getY()));
                }
                panel.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        panel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mousePosition.setX(e.getX());
                mousePosition.setY(e.getY());
                panel.repaint();
            }
        });
        frame.add(panel);
        frame.setSize(500,500);

        frame.setVisible(true);
    }

    public void close(){
        panel.getParent().dispatchEvent(new WindowEvent((Window) panel.getParent(), WindowEvent.WINDOW_CLOSING));
    }

    public Position getRealToBoardPosition(int x, int y){
        x = (int)x/getBlockSize();
        y = (int) y/getBlockSize();

        return new Position(x,y);
    }

    public boolean isOnBoard(int x, int y){
        return x >= 0 && x < currentBoard[0].length && y >= 0 && y < currentBoard.length;
    }

    public int getBlockSize(){
        return panel.getWidth()/Math.min(currentBoard.length, currentBoard[0].length);
    }

    public void setBoard(State[][] board){
        currentBoard = board;
    }

    public void clicked(Position position){
        System.out.println("Clicked!" + position);
    }
}
