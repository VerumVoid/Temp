package Logic;

import Global.Position;
import Graphics.Graphics;

public class Board {

    Graphics.Graphics.State[][] state;

    private int currentPlayer = 0;

    public void setBoard(){
        for (int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                state[i][j] = Graphics.State.EMPTY;
            }
        }
    }

    Graphics graphics = new Graphics() {
        @Override
        public void clicked(Position position) {
            if (currentPlayer == 0){
                state[position.getY()][position.getX()] = State.CROSS;
                //currentPlayer = 1;
            }
            else {
                state[position.getY()][position.getX()] = State.CIRCLE;
                //currentPlayer = 0;
            }
        }
    };



}
