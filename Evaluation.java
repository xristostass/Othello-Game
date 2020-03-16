public class Evaluation {

    State state;

    public Evaluation(State state1) {
        state = state1;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int evaluatePositions(){ //first evaluation of the function
        int[][] positions =
                {
                        {100, -10, 5, 2, 2, 5, -10, 100},
                        {-10, -10, 1, 1, 1, 1, -10, -10},
                        {5, 1, 1, 1, 1, 1, 1, 5},
                        {2, 1, 1, 0, 0, 1, 1, 2},
                        {2, 1, 1, 0, 0, 1, 1, 2},
                        {5, 1, 1, 1, 1, 1, 1, 5},
                        {-10, -10, 1, 1, 1, 1, -10, -10},
                        {100, -10, 5, 2, 2, 5, -10, 100}
                };
        char look;
        if (state.player == 'B') {
            look = 'W';

        } else {
            look = 'B';
        }
        int sum =0;
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++) {
                if (state.reversi_table[i][j] == state.player) { //we add the value of each position of our pieces
                    sum += positions[i][j];
                } else if (state.reversi_table[i][j] == look){//we substract the value of each position of the opposing pieces
                    sum -= positions[i][j];
                }
            }
        }
        return sum;
    }

    public double evaluatePercentage() { //we evaluate the percentage of our pieces in comparison to our opponents

        char look;
        if (state.player == 'B') {
            look = 'W';

        } else {
            look = 'B';
        }
        int playersum = 0;
        int counttiles = 1;

        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++) {
                //we count our pieces
                if (state.reversi_table[i][j] == state.player) {
                    playersum ++;
                    counttiles++;
                //we count the opposing pieces
                } else if (state.reversi_table[i][j] == look ){
                    counttiles ++;
                }
            }

        }
        return (playersum/counttiles) - ((counttiles - playersum)/counttiles);
    }

    public double complete_evaluation(){
        int weight1=1;
        int weight2=60;
        return weight1*evaluatePositions()+ weight2*evaluatePercentage();
    }

   
}
