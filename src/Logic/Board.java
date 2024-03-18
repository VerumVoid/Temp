package Logic;

import Global.Position;
import Graphics.Graphics;

public class Board {

    Graphics.Graphics.State[][] board;

    private int currentPlayer = 0;
    private int sideLength;

    private int winingPieces;

    private int winner;
    public void setBoard(){
        for (int i = 0; i < sideLength; i++){
            for(int j = 0; j < sideLength; j++){
                board[i][j] = Graphics.State.EMPTY;
            }
        }
    }

    public boolean fullBoard() {
        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                if (board[i][j] == Graphics.State.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    Graphics graphics = new Graphics() {
        @Override
        public void clicked(Position position) {
            if (currentPlayer == 0){
                board[position.getY()][position.getX()] = State.CROSS;
                //currentPlayer = 1;
            }
            else {
                board[position.getY()][position.getX()] = State.CIRCLE;
                //currentPlayer = 0;
            }
        }
    };

    public boolean isFinished(Position position){
        int row = position.getY();
        int column = position.getX();
        Graphics.Graphics.State currentState = board[column][row];
        int matching = 1;
        boolean helper = false;

        if (currentState != Graphics.State.EMPTY) {

            for (int i = 1; i < winingPieces; i++) {
                //up
                if (row - i >= 0) {
                    if (currentState == board[row - i][column] && !fullBoard()) {
                        matching++;
                    } else {
                        matching--;
                    }
                }
                if (matching == winingPieces) {
                    helper = true;
                    winner = currentPlayer;
                }
            }

            matching = 1;
            for (int i = 1; i < sideLength; i++) {
                //down
                if (row + i < winingPieces) {
                    if (currentState == board[row + i][column] && !fullBoard()) {
                        matching++;
                    } else {
                        matching--;
                    }
                }
                if (matching == winingPieces) {
                    helper = true;
                    winner = currentPlayer;
                }
            }

            matching = 1;
            for (int i = 1; i < sideLength; i++) {
                //right
                if (column + i < winingPieces) {
                    if (currentState == board[row][column + i] && !fullBoard()) {
                        matching++;
                    } else {
                        matching--;
                    }
                }
                if (matching == winingPieces) {
                    helper = true;
                    winner = currentPlayer;
                }
            }

            matching = 1;
            for (int i = 1; i < winingPieces; i++) {
                //left
                if (column - i >= 0) {
                    if (currentState == board[row][column - i] && !fullBoard()) {
                        matching++;
                    } else {
                        matching--;
                    }
                }
                if (matching == winingPieces) {
                    helper = true;
                    winner = currentPlayer;
                }
            }

            matching = 1;
            for (int i = 1; i < winingPieces; i++) {
                //up right
                if (row - i >= 0 && column + i < sideLength) {
                    if (currentState == board[row - i][column + i] && !fullBoard()) {
                        matching++;
                    } else {
                        matching--;
                    }
                }
                if (matching == winingPieces) {
                    helper = true;
                    winner = currentPlayer;
                }
            }

            matching = 1;
            for (int i = 1; i < winingPieces; i++) {
                //up left
                if (row - i >= 0 && column - i >= 0) {
                    if (currentState == board[row - i][column - i] && !fullBoard()) {
                        matching++;
                    } else {
                        matching--;
                    }
                }
                if (matching == winingPieces) {
                    helper = true;
                    winner = currentPlayer;
                }
            }

            matching = 1;
            for (int i = 1; i < winingPieces; i++) {
                //down right
                if (row + i < sideLength && column + i < sideLength) {
                    if (currentState == board[row + i][column + i] && !fullBoard()) {
                        matching++;
                    } else {
                        matching--;
                    }
                    if (matching == winingPieces) {
                        helper = true;
                        winner = currentPlayer;
                    }
                }
            }

            matching = 1;
            for (int i = 1; i < winingPieces; i++) {
                //down left
                if (column - i >= 0 && row + i < sideLength) {
                    if (currentState == board[row + i][column - i] && !fullBoard()) {
                        matching++;
                    } else {
                        matching--;
                    }
                }
                if (matching == winingPieces) {
                    helper = true;
                    winner = currentPlayer;
                }
            }
        }
        return helper;
    }


    public int getSideLength() {
        return sideLength;
    }

    public void setSideLength(int sideLength) {
        this.sideLength = sideLength;
    }

    public int getWiningPieces() {
        return winingPieces;
    }

    public void setWiningPieces(int winingPieces) {
        this.winingPieces = winingPieces;
    }
}
