package Graphics;

import Global.Position;

public class Graphics {
    public enum State{
        CIRCLE,
        CROSS,
        EMPTY
    }
    private State[][] currentBoard;

    public void setBoard(State[][] board){
        currentBoard = board;
    }

    public void clicked(Position position){

    }
}
