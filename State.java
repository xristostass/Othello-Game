import java.util.ArrayList;
import java.util.List;

public class State {
	//these are the characteristics that each State object has.
	char[][] reversi_table;//this table is  representing the game's board
	State father;
	List<State> children = new ArrayList<>();
	/*the following two lists are used for knowing the possible moves each player has according to the rules.*/
	List<Integer> possible_columns = new ArrayList<>();
	List<Integer> possible_rows = new ArrayList<>();
	int current_depth;
	char player;//with this variable we know which player's turn is,either black or white.
	double score;//this variable is for keeping each node's score.
	int x, y ;//the variables x and y are the positins  on the board.

	//here we have four different constructors that we needed in our project.
	public State(State father1, int depth, char player1) {
		reversi_table = new char[8][8];
		father = father1;
		current_depth = depth;
		player = player1;
	}

	public State(State father1, int depth, char player1,int x, int y) {
		reversi_table = new char[8][8];
		father = father1;
		current_depth = depth;
		player = player1;
		this.x=x;
		this.y=y;
	}

	public State(char[][] reversi_table, State father, List<State> children, List<Integer> possible_columns, List<Integer> possible_rows, int current_depth, char player, double score, int x, int y) {
		this.reversi_table = reversi_table;
		this.father = father;
		this.children = children;
		this.possible_columns = possible_columns;
		this.possible_rows = possible_rows;
		this.current_depth = current_depth;
		this.player = player;
		this.score = score;
		this.x = x;
		this.y = y;
	}


	//getters and setters for each field of the object.
	public List<State> getChildren() {
		return children;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double Score) {
		this.score = Score;
	}

	public void setTable(char[][] table) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				this.reversi_table[i][j] = table[i][j];

			}

		}
	}
	public void setX(int X) {
		this.x = X;
	}

	public void setY(int Y) {
		this.y = Y;
	}

	public void setReversi_table(char[][] reversi_table) {
		this.reversi_table = reversi_table;
	}

	public void setFather(State father) {
		this.father = father;
	}

	public void setChildren(List<State> children) {
		this.children = children;
	}

	public void setPossible_columns(List<Integer> possible_columns) {
		this.possible_columns = possible_columns;
	}

	public void setPossible_rows(List<Integer> possible_rows) {
		this.possible_rows = possible_rows;
	}

	public void setCurrent_depth(int current_depth) {
		this.current_depth = current_depth;
	}

	public void setPlayer(char player) {
		this.player = player;
	}

	public char[][] getReversi_table() {
		return reversi_table;
	}

	public State getFather() {
		return father;
	}

	public List<Integer> getPossible_columns() {
		return possible_columns;
	}

	public List<Integer> getPossible_rows() {
		return possible_rows;
	}

	public int getCurrent_depth() {
		return current_depth;
	}

	public char getPlayer() {
		return player;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	//this method is used for adding a state's children to the corresponding list.
	public void add(State newState) {
		if (newState != null)
			children.add(newState);
	}

	/*in this method we are initializing the board setting every tile empty,symbolized with the '*' symbol, but for the for central tiles which are set black and white.*/
	public void tableInitialization() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				reversi_table[i][j] = "*".charAt(0);

			}

		}
		reversi_table[3][3] = "W".charAt(0);
		reversi_table[3][4] = "B".charAt(0);
		reversi_table[4][3] = "B".charAt(0);
		reversi_table[4][4] = "W".charAt(0);

	}

	/*in this method we print the board.*/
	public void printTable() {
		System.out.print("\n" + "   ");
		for (int i = 0; i < 8; i++) {
			System.out.print(i + " ");
		}
		System.out.print("\n" + " ");
		for (int i = 0; i < 10; i++) {
			System.out.print("_ ");
		}
		System.out.print("\n");
		char sides = 'A';// this is a variable we are using in order to count the lines of the board.
		for (int i = 0; i < 8; i++) {
			System.out.print((char) (sides + i) + "| ");
			for (int j = 0; j < 8; j++) {
				System.out.print(reversi_table[i][j] + " ");

			}
			System.out.print("|" + "\n");
		}
		System.out.print(" ");
		for (int i = 0; i < 10; i++) {
			System.out.print("_ ");
		}
		System.out.print("\n");
		int[] results = countChar();
		System.out.println("There are " + results[0] + " empty tiles.");
		System.out.println("There are " + results[1] + " black tiles.");
		System.out.println("There are " + results[2] + " white tiles.");
	}

	/*in this method we print the board again. We change the tiles in which a player can move to '+'.*/
	public void printWithPossibleMoves() {
		char[][] poss_moves = new char[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				poss_moves[i][j] = reversi_table[i][j];
			}
		}
		for (int i = 0; i < possible_rows.size(); i++) {
			poss_moves[possible_rows.get(i)][possible_columns.get(i)] = '+';
		}

		System.out.print("\n" + "   ");
		for (int i = 0; i < 8; i++) {
			System.out.print(i + " ");
		}
		System.out.print("\n" + " ");
		for (int i = 0; i < 10; i++) {
			System.out.print("_ ");
		}
		System.out.print("\n");
		char sides = 'A';// this is a variable we are using in order to count the lines of the board.
		for (int i = 0; i < 8; i++) {
			System.out.print((char) (sides + i) + "| ");
			for (int j = 0; j < 8; j++) {
				System.out.print(poss_moves[i][j] + " ");

			}
			System.out.print("|" + "\n");
		}
		System.out.print(" ");
		for (int i = 0; i < 10; i++) {
			System.out.print("_ ");
		}
		System.out.print("\n");

	}

	// here we are counting the empty tiles, the black and the white ones.
	public int[] countChar() {
		int[] values = new int[3];
		for (int i = 0; i < values.length; i++) {
			values[i] = 0;
		}

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (reversi_table[i][j] == '*') {
					values[0]++;
				} else if (reversi_table[i][j] == 'B') {
					values[1]++;
				} else {
					values[2]++;
				}
			}
		}
		return values;
	}

	// this method checks if two states are the same.
	public boolean sameStates(State states) {
		boolean samestate = true;


		for (int k = 0; k < 8; k++) {
			for (int j = 0; j < 8; j++) {
				if (states.reversi_table[k][j] != reversi_table[k][j]) {
					samestate = false;
					break;
				}
			}
		}

		if (!samestate) {
			return false;
		}
		return true;
	}

	// this method returns the children of each state.
	public void checkRules(char player) {
		char look;

		if (player == 'B') {
			look = 'W';

		} else {
			look = 'B';
		}
		State newState = null;
		State child=null;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {


				if (reversi_table[i][j] == '*') {
				    /* here we are checking the borders of the board,so that we don't get out of the board's boundaries.

                    Inside the if cases,we check if there's a neighboring tile of the opposite color,to see if we can play there.
                    This is one of the conditions.*/

					if (i == 0) {
						if (j == 0) {
							if (reversi_table[i][j + 1] == look) {
								child=changeTiles(this, newState, i, j, 5, player);
								if(child!=null){
									newState=child;
								}
							}
							if (reversi_table[i + 1][j] == look) {
								child=changeTiles(this, newState, i, j, 7, player);
								if(child!=null){
									newState=child;
								}
							}
							if (reversi_table[i + 1][j + 1] == look) {
								child=changeTiles(this, newState, i, j, 8, player);
								if(child!=null){
									newState=child;
								}
							}

						} else if (j == 7) {
							if (reversi_table[i][j - 1] == look) {
								child=changeTiles(this, newState, i, j, 4, player);
								if(child!=null){
									newState=child;
								}
							}
							if (reversi_table[i + 1][j - 1] == look) {
								child=changeTiles(this, newState, i, j, 6, player);
								if(child!=null){
									newState=child;
								}
							}
							if (reversi_table[i + 1][j] == look) {
								child=changeTiles(this, newState, i, j, 7, player);
								if(child!=null){
									newState=child;
								}
							}
						} else {
							if (reversi_table[i][j - 1] == look) {
								child=changeTiles(this, newState, i, j, 4, player);
								if(child!=null){
									newState=child;
								}
							}
							if (reversi_table[i][j + 1] == look) {
								child=changeTiles(this, newState, i, j, 5, player);
								if(child!=null){
									newState=child;
								}
							}
							if (reversi_table[i + 1][j - 1] == look) {
								child=changeTiles(this, newState, i, j, 6, player);
								if(child!=null){
									newState=child;
								}
							}
							if (reversi_table[i + 1][j] == look) {
								child=changeTiles(this, newState, i, j, 7, player);
								if(child!=null){
									newState=child;
								}
							}
							if (reversi_table[i + 1][j + 1] == look) {
								child=changeTiles(this, newState, i, j, 8, player);
								if(child!=null){
									newState=child;
								}
							}
						}
					} else if (i == 7) {
						if (j == 0) {
							if (reversi_table[i - 1][j] == look) {
								child=changeTiles(this, newState, i, j, 2, player);
								if(child!=null){
									newState=child;
								}
							}
							if (reversi_table[i - 1][j + 1] == look) {
								child=changeTiles(this, newState, i, j, 3, player);
								if(child!=null){
									newState=child;
								}
							}
							if (reversi_table[i][j + 1] == look) {
								child=changeTiles(this, newState, i, j, 5, player);
								if(child!=null){
									newState=child;
								}
							}
						} else if (j == 7) {
							if (reversi_table[i - 1][j - 1] == look) {
								child=changeTiles(this, newState, i, j, 1, player);
								if(child!=null){
									newState=child;
								}
							}
							if (reversi_table[i - 1][j] == look) {
								child=changeTiles(this, newState, i, j, 2, player);
								if(child!=null){
									newState=child;
								}
							}
							if (reversi_table[i][j - 1] == look) {
								child=changeTiles(this, newState, i, j, 4, player);
								if(child!=null){
									newState=child;
								}
							}
						} else {
							if (reversi_table[i - 1][j - 1] == look) {
								child=changeTiles(this, newState, i, j, 1, player);
								if(child!=null){
									newState=child;
								}
							}
							if (reversi_table[i - 1][j] == look) {
								child=changeTiles(this, newState, i, j, 2, player);
								if(child!=null){
									newState=child;
								}
							}
							if (reversi_table[i - 1][j + 1] == look) {
								child=changeTiles(this, newState, i, j, 3, player);
								if(child!=null){
									newState=child;
								}
							}
							if (reversi_table[i][j - 1] == look) {
								child=changeTiles(this, newState, i, j, 4, player);
								if(child!=null){
									newState=child;
								}
							}
							if (reversi_table[i][j + 1] == look) {
								child=changeTiles(this, newState, i, j, 5, player);
								if(child!=null){
									newState=child;
								}
							}
						}

					}
					// in this else case we are looking inside the board so we don't care about the boundaries.
					else {
						if(j==0) {
							if (reversi_table[i - 1][j] == look) {
								child=changeTiles(this, newState, i, j, 2, player);
								if(child!=null){
									newState=child;
								}
							}
							if (reversi_table[i - 1][j + 1] == look) {
								child=changeTiles(this, newState, i, j, 3, player);
								if(child!=null){
									newState=child;
								}
							}
							if (reversi_table[i][j + 1] == look) {
								child=changeTiles(this, newState, i, j, 5, player);
								if(child!=null){
									newState=child;
								}
							}
							if (reversi_table[i + 1][j] == look) {
								child=changeTiles(this, newState, i, j, 7, player);
								if(child!=null){
									newState=child;
								}
							}
							if (reversi_table[i + 1][j + 1] == look) {
								child=changeTiles(this, newState, i, j, 8, player);
								if(child!=null){
									newState=child;
								}
							}

						}else if(j==7) {
							if (reversi_table[i - 1][j - 1] == look) {
								child=changeTiles(this, newState, i, j, 1, player);
								if(child!=null){
									newState=child;
								}
							}
							if (reversi_table[i - 1][j] == look) {
								child=changeTiles(this, newState, i, j, 2, player);
								if(child!=null){
									newState=child;
								}
							}
							if (reversi_table[i][j - 1] == look) {
								child=changeTiles(this, newState, i, j, 4, player);
								if(child!=null){
									newState=child;
								}
							}
							if (reversi_table[i + 1][j - 1] == look) {
								child=changeTiles(this, newState, i, j, 6, player);
								if(child!=null){
									newState=child;
								}
							}
							if (reversi_table[i + 1][j] == look) {
								child=changeTiles(this, newState, i, j, 7, player);
								if(child!=null){
									newState=child;
								}
							}
						}else {
							if (reversi_table[i - 1][j - 1] == look) {
								child=changeTiles(this, newState, i, j, 1, player);
								if(child!=null){
									newState=child;
								}
							}
							if (reversi_table[i - 1][j] == look) {
								child=changeTiles(this, newState, i, j, 2, player);
								if(child!=null){
									newState=child;
								}
							}
							if (reversi_table[i - 1][j + 1] == look) {
								child=changeTiles(this, newState, i, j, 3, player);
								if(child!=null){
									newState=child;
								}
							}
							if (reversi_table[i][j - 1] == look) {
								child=changeTiles(this, newState, i, j, 4, player);
								if(child!=null){
									newState=child;
								}
							}
							if (reversi_table[i][j + 1] == look) {
								child=changeTiles(this, newState, i, j, 5, player);
								if(child!=null){
									newState=child;
								}
							}
							if (reversi_table[i + 1][j - 1] == look) {
								child=changeTiles(this, newState, i, j, 6, player);
								if(child!=null){
									newState=child;
								}
							}
							if (reversi_table[i + 1][j] == look) {
								child=changeTiles(this, newState, i, j, 7, player);
								if(child!=null){
									newState=child;
								}
							}
							if (reversi_table[i + 1][j + 1] == look) {
								child=changeTiles(this, newState, i, j, 8, player);
								if(child!=null){
									newState=child;
								}
							}

						}

					}
					if(newState!=null){
						this.add(newState);
					}
				}

				newState=null;
				child=null;
			}
		}
		/*if the state didn't have any children we add to the list the same state,because the player didn't have a possible move,but the state might not be terminal.*/
		if (children.isEmpty()) {
			char[][] reversi_table1 = new char[8][8];
			for (int i =0; i<8; i++) {
				for (int j = 0; j<8; j++) {
					reversi_table1[i][j] = reversi_table[i][j];
				}
			}

			children.add(new State(this, this.current_depth+1,look, -1,-1)); //we might need to check in the interface if the state hasn't changed so we print a message.
			children.get(0).setTable(reversi_table1);
		}

	}

	// this method will change the color of the tiles accordingly to the moves.
	public State changeTiles(State currentState, State child, int x, int y, int direction, char player) {
		char look;
		int i, j;
		boolean foundPlayer = false;
		// we have found a tile colored same as the player(no racism here).
		if (player == 'B') {
			look = 'W';

		} else {
			look = 'B';
		}
		// here we are checking if there exists a possible move for each direction.(The directions are explained with more detail in the report file.)
		if (direction == 1) {
			i = x - 1;
			j = y - 1;
			while (!foundPlayer && i >= 0 && i <= 7 && j >= 0 && j <= 7) {
				if (currentState.reversi_table[i][j] == player) {
					foundPlayer = true;
					possible_rows.add((Integer) x);
					possible_columns.add((Integer) y);
				}
				if(currentState.reversi_table[i][j]!=look ){
					break;
				}
				i--;
				j--;
			}
			i++;
			j++;
		} else if (direction == 2) {
			i = x - 1;
			j = y;
			while (!foundPlayer && i >= 0 && i <= 7 && j >= 0 && j <= 7) {
				if (currentState.reversi_table[i][j] == player) {
					foundPlayer = true;
					possible_rows.add((Integer) x);
					possible_columns.add((Integer) y);
				}
				if(currentState.reversi_table[i][j]!=look){
					break;
				}
				i--;
			}

			i++;
		} else if (direction == 3) {
			i = x - 1;
			j = y + 1;
			while (!foundPlayer && i >= 0 && i <= 7 && j >= 0 && j <= 7) {
				if (currentState.reversi_table[i][j] == player) {
					foundPlayer = true;
					possible_rows.add((Integer) x);
					possible_columns.add((Integer) y);
				}
				if(currentState.reversi_table[i][j]!=look){
					break;
				}
				i--;
				j++;
			}
			i++;
			j--;
		} else if (direction == 4) {
			i = x;
			j = y - 1;
			while (!foundPlayer && i >= 0 && i <= 7 && j >= 0 && j <= 7) {
				if (currentState.reversi_table[i][j] == player) {
					foundPlayer = true;
					possible_rows.add((Integer) x);
					possible_columns.add((Integer) y);
				}
				if(currentState.reversi_table[i][j]!=look){
					break;
				}
				j--;
			}
			j++;
		} else if (direction == 5) {
			i = x;
			j = y + 1;
			while (!foundPlayer && i >= 0 && i <= 7 && j >= 0 && j <= 7) {
				if (currentState.reversi_table[i][j] == player) {
					foundPlayer = true;
					possible_rows.add((Integer) x);
					possible_columns.add((Integer) y);
				}
				if(currentState.reversi_table[i][j]!=look){
					break;
				}
				j++;
			}
			j--;
		} else if (direction == 6) {
			i = x + 1;
			j = y - 1;
			while (!foundPlayer && i >= 0 && i <= 7 && j >= 0 && j <= 7) {
				if (currentState.reversi_table[i][j] == player) {
					foundPlayer = true;
					possible_rows.add((Integer) x);
					possible_columns.add((Integer) y);
				}
				if(currentState.reversi_table[i][j]!=look){
					break;
				}
				i++;
				j--;
			}
			i--;
			j++;
		} else if (direction == 7) {
			i = x + 1;
			j = y;
			while (!foundPlayer && i >= 0 && i <= 7 && j >= 0 && j <= 7) {
				if (currentState.reversi_table[i][j] == player) {
					foundPlayer = true;
					possible_rows.add((Integer) x);
					possible_columns.add((Integer) y);
				}
				if(currentState.reversi_table[i][j]!=look){
					break;
				}
				i++;
			}
			i--;
		} else if (direction == 8) {
			i = x + 1;
			j = y + 1;
			while (!foundPlayer && i >= 0 && i <= 7 && j >= 0 && j <= 7) {
				if (currentState.reversi_table[i][j] == player) {
					foundPlayer = true;
					possible_rows.add((Integer) x);
					possible_columns.add((Integer) y);
				}
				if(currentState.reversi_table[i][j]!=look){
					break;
				}
				i++;
				j++;
			}
			i--;
			j--;
		}
		// we know that moving is possible,so we are executing the possible moves in the following if case one at a time.
		if (foundPlayer) {
			if (child == null) {
				child = new State(currentState, currentState.current_depth+1, look,x,y);
				child.setTable(currentState.reversi_table);
				children.add(child);
			}
			// here we are making the necessary changes.
			if (direction == 1) {
				child.reversi_table[x][y] = player;
				i = x - 1;
				j = y - 1;
				// essentially here we are changing the tiles's color.
				while (i >= 0 && i <= 7 && j >= 0 && j <= 7) {
					if (child.reversi_table[i][j] == player) {
						break;
					}
					child.reversi_table[i][j] = player;
					i--;
					j--;
				}

			}else if (direction == 2) {
				child.reversi_table[x][y] = player;
				i = x - 1;
				j = y;
				while (i >= 0 && i <= 7 && j >= 0 && j <= 7) {
					if (child.reversi_table[i][j] == player) {

						break;
					}
					child.reversi_table[i][j] = player;
					i--;
				}
			} else if (direction == 3) {
				child.reversi_table[x][y] = player;
				i = x - 1;
				j = y + 1;
				while (i >= 0 && i <= 7 && j >= 0 && j <= 7) {
					if (child.reversi_table[i][j] == player) {
						break;
					}
					child.reversi_table[i][j] = player;
					i--;
					j++;
				}

			} else if (direction == 4) {
				child.reversi_table[x][y] = player;
				i = x;
				j = y - 1;
				while (i >= 0 && i <= 7 && j >= 0 && j <= 7) {
					if (child.reversi_table[i][j] == player) {
						break;
					}
					child.reversi_table[i][j] = player;
					j--;
				}
			} else if (direction == 5) {
				child.reversi_table[x][y] = player;
				i = x;
				j = y + 1;
				while (i >= 0 && i <= 7 && j >= 0 && j <= 7) {
					if (child.reversi_table[i][j] == player) {
						break;
					}
					child.reversi_table[i][j] = player;
					j++;
				}
			} else if (direction == 6) {
				child.reversi_table[x][y] = player;
				i = x + 1;
				j = y - 1;
				while (i >= 0 && i <= 7 && j >= 0 && j <= 7) {
					if (child.reversi_table[i][j] == player) {
						break;
					}
					child.reversi_table[i][j] = player;
					i++;
					j--;
				}
			} else if (direction == 7) {
				child.reversi_table[x][y] = player;
				i = x + 1;
				j = y;
				while (i >= 0 && i <= 7 && j >= 0 && j <= 7) {
					if (child.reversi_table[i][j] == player) {
						break;
					}
					child.reversi_table[i][j] = player;
					i++;
				}
			} else if (direction == 8) {
				child.reversi_table[x][y] = player;
				i = x + 1;
				j = y + 1;
				while (i >= 0 && i <= 7 && j >= 0 && j <= 7) {
					if (child.reversi_table[i][j] == player) {
						break;
					}
					child.reversi_table[i][j] = player;
					i++;
					j++;
				}
			}
		}
		return child;
	}

	/*public boolean isTerminal() { //*******may not be needed*********
		char look;
		if (player == 'B') {
			look = 'W';

		} else {
			look = 'B';
		}
		//we create a temporary state to check if it's terminal.
		State temp = new State(this.father,this.current_depth,this.player);
		State temp_child;
		temp.setTable(this.reversi_table);
		temp.checkRules(temp.player);
		if(temp.getChildren().size()==1){
			if(temp.sameStates(temp.getChildren().get(0))){
				//here we know that our initial state is the same with its child,so a player had no possible moves.
				temp_child=new State(temp.father,temp.current_depth,look);
				temp_child.checkRules(look);
				if(temp_child.getChildren().size()==1){
					//here we check if the initial state is the same with its "grandchild",so in that way we know that both players have no more possible moves.
					if(temp_child.sameStates(temp)){
						return true;
					}
				}
			}
		}

		for (int i = 0; i < 8; i++) { // if there are still empty tiles in the table the game may not have finished
			for (int j = 0; j < 8; j++) {
				if (reversi_table[i][j] == '*') {
					return false;
				}
			}
		}

		return false;

	}*/

	//skipping turns
	public boolean skip_turn(){

		char look;
		if (player == 'B') {
			look = 'W';

		} else {
			look = 'B';
		}
		//we create a temporary state to check if it's terminal.
		State temp = new State(this.father,this.current_depth,this.player);
		State temp_child;
		temp.setTable(this.reversi_table);
		temp.checkRules(temp.player);
		if(temp.getChildren().size()==1) {
			if (temp.sameStates(temp.getChildren().get(0))) {
				return true;
			}
		}
		return false;
	}
}