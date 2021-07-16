package lessons.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BattleMap extends JPanel {
    private GameWindow gameWindow;
    private int fieldSize;
    private int winLength;

    private boolean isInit;

    private int cellWidth;
    private int cellHeight;


    public BattleMap(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setBackground(Color.ORANGE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int cellX = e.getX() / cellWidth;
                int cellY = e.getY() / cellHeight;

                if (isInit && !Logic.isGameFinished) {
                    Logic.humanTurn(cellY, cellX);

                }
                repaint();
            }
        });
    }

    void startNewGame(int fieldSize, int winLength) {
        this.fieldSize = fieldSize;
        this.winLength = winLength;

        isInit = true;  //  Данные о настройке получены
        repaint();
    }

    /**
     * Переопределяем  paintComponent для рисования
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!isInit) {
            return;
        }

        int panelWidth = getWidth();    // Размер поля по ширине
        int panelHeight = getHeight();  // Размер поля по высоте

        cellWidth = panelWidth / fieldSize;         // Размер ячейки по ширине
        cellHeight = panelHeight / fieldSize;       // Размер ячейки по высоте

        g.setColor(Color.GRAY);                                 // Цвет линии (сетки)
        ((Graphics2D) g).setStroke(new BasicStroke(2f));   // Ширина линии (сетки)

        // Рисуем сетку по вертикали
        for (int i = 1; i < fieldSize; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, panelWidth, y);
        }
        // Рисуем сетку по горизонтали
        for (int i = 1; i < fieldSize; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, panelHeight);
        }

        // Рисуем ход игрока и ход компьютера
        for (int i = 0; i < Logic.map.length; i++) {
            for (int j = 0; j < Logic.map[i].length; j++) {
                if (Logic.map[i][j] == Logic.DOT_X) {
                    drawX(g, i, j);
                }
                if (Logic.map[i][j] == Logic.DOT_O) {
                    draw0(g, i, j);
                }
            }

        }

        // Сообщение об победителе
        if (Logic.isGameFinished) {
            g.setFont(new Font("TimesRoman", Font.PLAIN, panelWidth / Logic.isWinner.length()));
            g.setColor(Color.GREEN);                                   // Цвет линии (сообщения)
            g.drawString(Logic.isWinner, 100, panelHeight / 2);
        }


    }

    /**
     * Рисуем на панели BattleMap Крестик (ход игрока)
     */
    private void drawX(Graphics g, int y, int x) {
        g.setColor(Color.BLUE);
        ((Graphics2D) g).setStroke(new BasicStroke(3f));

        g.drawLine(x * cellWidth, y * cellHeight,
                (x + 1) * cellWidth, (y + 1) * cellHeight);
        g.drawLine(x * cellWidth, (y + 1) * cellHeight,
                (x + 1) * cellWidth, y * cellHeight);
    }

    /**
     * Рисуем на панели BattleMap Нуль (ход компьютера)
     */
    private void draw0(Graphics g, int y, int x) {
        g.setColor(Color.BLUE);
        ((Graphics2D) g).setStroke(new BasicStroke(3f));

        g.drawOval(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
    }

    private void drawWinLine(Graphics g, int[] arr) {
        if (arr[2] == 0 && arr[3] == 1) {
            g.drawLine(arr[1] * cellWidth, arr[0] * cellHeight + cellHeight / 2,
                    (arr[1] + arr[4]) * cellWidth, arr[0] * cellHeight + cellHeight / 2);
        }
        if (arr[2] == 1 && arr[3] == 0) {
            g.drawLine(arr[1] * cellWidth + cellWidth / 2, arr[0] * cellHeight,
                    arr[1] * cellWidth + cellWidth / 2, (arr[0] + arr[4]) * cellHeight);
        }
        if (arr[2] == 1 && arr[3] == 1) {
            g.drawLine(arr[1] * cellWidth, arr[0] * cellHeight,
                    (arr[1] + arr[4]) * cellWidth, (arr[0] + arr[4]) * cellHeight);
        }
        if (arr[2] == -1 && arr[3] == 1) {
            g.drawLine(arr[1] * cellWidth, arr[0] * cellHeight,
                    (arr[1] - arr[4]) * cellWidth, (arr[0] + arr[4]) * cellHeight);
        }
    }

}
