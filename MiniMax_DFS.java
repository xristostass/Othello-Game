import java.util.ArrayList;
import java.util.List;

public  class MiniMax_DFS{

    
    public static void creatingDFS(State state,int depth, char player, String str) {
          creatingDFSrecursive(state, depth- state.current_depth, player, str);
    }

    public static void creatingDFSrecursive(State state,int depth, char player, String str){

        State st;
        char look;
        String next;
        if (str.equals("max")) {
            next="min";

        } else {
            next="max";
        }
        Evaluation eval = null;
        
        if (player == 'B') {
            look = 'W';

        } else {
            look = 'B';
        }

        if (depth >0) {
            state.checkRules(player);
            for (int i =0; i< state.children.size(); i++ ) { //as long as the node has children
                creatingDFSrecursive(state.children.get(i),depth -1, look , next); //recalling the method for his children
            }
            if (str.equals("min")) {
                state.setScore(min(state.getChildren()));
            }
            if (str.equals("max")) {
                state.setScore(max(state.getChildren()));
            }
            //here we are implementing the tree pruning
            for(int i=0; i<state.getChildren().size(); i++){
                if(state.getChildren().get(i).getScore()==state.getScore()){

                    st=state.getChildren().get(i);
                    state.getChildren().removeAll(state.getChildren());
                    state.getChildren().add(st);

                }

            }
        }
        else {
            eval = new Evaluation(state);
            state.setScore(eval.complete_evaluation()); //Evaluating the leaf nodes(depth=0)
        }
    }
    //this method finds the score of the child that has the minimum score.
    public static double min(List<State> children) {//children contains all the childrens of the current state

        double min_score =children.get(0).getScore();
        for (int i =1; i< children.size(); i++) {
            if (children.get(i).getScore()< min_score) {
                min_score = children.get(i).getScore();
            }
        }
        return min_score;
    }

    public static double max(List<State> children) {//this method finds the score of the child that has the maximum score.
        double max_score =children.get(0).getScore();
        for (int i =1; i< children.size(); i++) {
            if (children.get(i).getScore()> max_score) {
                max_score = children.get(i).getScore();
            }
        }
        return max_score;
    }
}
