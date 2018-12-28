/**
 * @Programmer Zeel Shah
 * @Date Oct 15 2018
 * @StudentNo 250970094
 * 
 * This is the class that Plays the board game using all the other supplementary classes.
 */
public class BoardGame {

	//declare the variables
	private int boardSize,emptyPositions, maxLevels;
	private HashDictionary dict;
	char gameBoard[][];


	/**
	 * The constructor sets the size of the board to the size given. It also sets all the empty spaces in the game board.
	 * @param boardSize
	 * @param emptyPositions
	 * @param maxLevels
	 */
	public BoardGame (int boardSize, int emptyPositions, int maxLevels)
	{
		//initialize the variables
		this.boardSize=boardSize;
		this.emptyPositions=emptyPositions;
		this.maxLevels = maxLevels;
		gameBoard= new char[boardSize][boardSize];
		
		//set each table space to an empty space
		for (int i=0;i<boardSize;i++)
		{
			for(int j=0;j<boardSize;j++)
				gameBoard[i][j]='g';
		}

	}
	
	/**
	 * This method creates the dictionary using the HashDictionary class, the size is preset to 9973
	 * @return the empty dictionary created
	 */
	public HashDictionary makeDictionary()
	{
		dict = new HashDictionary(9973);
		return dict;
	}
	
	/**
	 * This method represents the contents of the game board as a string. Then checks if the string exists
	 * in the dictionary.
	 * @param dictionary
	 * @return score or -1 if the string representation is not in the dictionary
	 */
	public int isRepeatedConfig(HashDictionary dict)
	{
		//get the string representation of the game board
		String config = new String (getStringRep());		

		//check to see if the config is in the dictionary
		return dict.getScore(config);

	}
	
	/**
	 * Represents the contents of the game board as a string and then adds it to the dictionary with the score
	 * @param dictionary
	 * @param score
	 */
	public void putConfig(HashDictionary dict, int score)
	{
		// get string rep of board game
		String config = new String (getStringRep());
		Configuration pair = new Configuration(config,score);
		//add the pair to the dictionary
		dict.put(pair);
	}
	
	/**
	 * This method stores the symbol in the game board
	 * @param row
	 * @param col
	 * @param symbol
	 */
	public void savePlay(int row, int col, char symbol)
	{
		gameBoard[row][col]=symbol;
	}
	
	/**
	 * This method checks to see if the given position is empty
	 * @param row
	 * @param col
	 * @return true if it is empty, false if it is not
	 */
	public boolean positionIsEmpty(int row, int col)
	{
		return (gameBoard[row][col]== 'g');

	}
	
	/**
	 * Checks to see if the game board position is a computer tile
	 * @param row
	 * @param col
	 * @return true if it is a computer tile, false if not
	 */
	public boolean tileOfComputer(int row, int col)
	{
		return (gameBoard[row][col]=='o');
	}
	
	/**
	 * Checks to see if the game board position is a human tile
	 * @param row
	 * @param col
	 * @return true if it is a human tile, false if it not
	 */
	public boolean tileOfHuman (int row, int col)
	{
		return (gameBoard[row][col]=='b');
	}

	/**
	 * Checks to see if there are any wins in the game. A win is when n adjacent tiles of 
	 * the type symbol are in the same row, column or diagonal. Where n is the size of the game board
	 * @param symbol
	 * @return true is there is a win false if not
	 */
	public boolean wins (char symbol)
	{
		//initialize and declare variables
		boolean win = true;
		int i =0;
		int j;

		//checks the row
		while (i<this.boardSize)
		{
			j=0;			
			// loop until there isn't an adjacent tile of the same symbol & the end of the row is reached
			while (j<this.boardSize && win)
			{
				//if the symbol is not at the position don't check the rest of the row (so set win to false)
				if (symbol!=gameBoard[i][j])
				{
					win = false; 
				}
				j++;
			}
			// if there is a win return it
			if (win)
			{
				return win;
			}
			i++;
			win=true;// reset win to true for next round
		}
		j=0;// reset j to 0 for column check
		win=true;// reset win to true to check column

		//check column
		while (j<this.boardSize)
		{
			i=0; // reset i to 0
			
			while (i<this.boardSize && win)
			{
				//if the symbol is not at the position don't check the rest of the column (so set win to false)
				if (symbol!=gameBoard[i][j])
				{
					win = false; 
				}
				i++;
			}
			if (win)
			{
				return win;
			}
			j++;
			win=true; //reset win to true for next column check
		}
		// set i and j to 0 so we can start at the first position
		i=0;
		j=0;
		win=true;// reset win to true 
		
		//check diagonals
		while (i<this.boardSize && win)
		{
			//if the symbol is not at the position don't check the rest of the diagonal (so set win to false)
			if (symbol!=gameBoard[i][j])
			{
				win = false; 
			}
			j++;
			i++;

		}
		if (win)
		{
			return win;
		}

		i=0;
		j=boardSize-1;//start at top right corner
		win = true;
		while (i<this.boardSize && win)
		{

			if (symbol!=gameBoard[i][j])
			{
				win = false; 
			}
			//this diagonal will check top right to bottom left
			j--;
			i++;

		}
		if (win)
		{
			return win;
		}
		
		// if no wins return false
		return false;

	}
	
	/**
	 * This method checks to see if there is a tie or not 
	 * @param symbol
	 * @param emptyPositions
	 * @return true if there is a tie or false if there is not a tie
	 */
	public boolean isDraw(char symbol, int emptyPositions)
	{
		int numOfEmptySpaces=0;
		// check the number of empty positions
		for (int i=0;i<boardSize;i++)
		{
			for(int j=0;j<boardSize;j++)
			{
				if (gameBoard[j][i]=='g')
				{
					numOfEmptySpaces++;
				}
			}
		}
		
		// if the number of empty positions on the game board are larger then the number of empty spaces specified then there is not a tie
		if (numOfEmptySpaces>emptyPositions)
			return false;

		// if the number of empty spaces is 0 and human doesn't win and computer doesn't win then it is a draw
		if (emptyPositions==0 &&!wins('b')&&!wins('o'))
		{
			return true;
		}
		
		//if human doesn't win and computer doesn't win then check each column, row and diagonal
		else if (!wins('b')&&!wins('o'))
		{

			//check if any adjacent tiles are available to move 
			for (int i=0; i<boardSize; i++)
			{
				for (int j=0; j<boardSize; j++)
				{
					// check if the symbol has any adjacent empty spots
					if (gameBoard[i][j]==symbol)
					{
						//check row
						if ((j>0 && j<boardSize-1 &&(gameBoard[i][j-1]=='g'||gameBoard[i][j+1] == 'g')) || (j==0 && gameBoard[i][j+1]=='g')||(j==boardSize-1 && gameBoard[i][j-1]=='g'))
						{
							return false;
						}
						//check column
						if ((i>0 && i<boardSize-1 && (gameBoard[i-1][j]=='g'||gameBoard[i+1][j] == 'g')) || (i==0 && gameBoard[i+1][j]=='g')||(i==boardSize-1 && gameBoard[i-1][j]=='g'))
						{
							return false;
						}
						// check top left to bottom right diagonal
						if (i>0 && i<boardSize-1 && j>0 && j<boardSize-1 && (gameBoard[i-1][j-1]=='g'|| gameBoard[i+1][j+1]=='g')||i==0 && j==0 && gameBoard[i+1][j+1]=='g'|| i==boardSize-1 && j==boardSize-1 && gameBoard[i-1][j-1]=='g')
						{
							return false;							
						}
						//check top right to bottom left diagonal
						if (i>0&&i<boardSize-1 && j>0 && j<boardSize-1 && (gameBoard[i-1][j+1]=='g'|| gameBoard[i+1][j-1]=='g')||i==boardSize-1 && j==0 && gameBoard[i-1][j+1]=='g'||i==0 && j==boardSize-1 &&  gameBoard[i+1][j-1]=='g' )
						{
							return false;							
						}

					}
				}
			}

		}
		// if we get here that means there is a draw
		return true;

	}
	
	/**
	 * returns the score for the computer to determine the best move
	 * @param symbol
	 * @param emptyPositions
	 * @return 3 if the computer wins, 2 if it's a draw, 1 if it's undeclared and 0 if the human wins
	 */
	public int evalBoard(char symbol, int emptyPositions)
	{
		if (wins('o'))
		{
			return 3;
		}

		else if (wins('b'))
		{
			return 0;
		}
		else if(isDraw(symbol, emptyPositions))
		{
			return 2;
		}
		else 
		{
			return 1;
		}
	}
	
	/**
	 * This method gets the string representation of the game board
	 * @return string configuration 
	 */
	private String getStringRep()
	{
		String config=new String("");
		
		//go from top left corner, down each row until the whole game board is checked
		for (int i=0;i<boardSize;i++)
		{
			for(int j=0;j<boardSize;j++)
			{
				config = config+gameBoard[j][i];
			}
		}
		return config;
	}

}
