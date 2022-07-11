import java.util.Scanner;

public class Game extends State{
	
	
	public static final int MAX_GAMES=7;
	public static final int PAPER=1;
	public static final int SCISSORS=2;
	public static final int ROCK=3;
	
	private StateGame gameState;
	private Player gameWinner;
	
	int numberOfGames;
	int playerWins;
	int compWins;
	int currGame;
	int compChoice;
	
	Game()
	{
		gameState=StateGame.INTRO;
	}
	
	public void play(){
		
		do {
			
			switch(gameState)
			{
			
				case INTRO:
									intro();
									currGame=0;
									gameState=StateGame.NUMBER_OF_GAME;
									break;
					
				case NUMBER_OF_GAME:
									numberOfGames=getNum("Enter the number of games to play- ");
									if(numberOfGames<=MAX_GAMES)
										gameState=StateGame.START_ROUND;
									else
										System.out.println("exceeded the max number of games which is 7");
									break;
					
					
				case START_ROUND:
										currGame++;
										if(currGame>numberOfGames) {
											gameState=StateGame.RESULT;
											break;
										}
										System.out.println("\n Game Number: "+ currGame);
										compChoice=(int)(Math.random()*3+1);
										gameState=StateGame.PLAY_ROUND;
										break;	
					
				case PLAY_ROUND:		
										System.out.println("1. Paper 2. Scissors 3. Rock");
										int playerChoice=getNum("Enter your choice- ");	
										if(playerChoice>=PAPER && playerChoice<=ROCK) {
											switch(playerChoice)
											{
												case PAPER:		System.out.println("you chose PAPER");
																	break;
												case SCISSORS:	System.out.println("you chose SCISSORS");
																	break;
												case ROCK:		System.out.println("you chose ROCK");
												 					break;
											}	
										}
										if(playerChoice == compChoice)
											System.out.println("GAME TIED!!!");
										else
										{
											switch(playerChoice)
											{
												case PAPER:
													if(compChoice==SCISSORS)
														gameWinner=Player.COMPUTER;
													else
														gameWinner=Player.USER;
													break;
												case SCISSORS:
													if(compChoice==PAPER)
														gameWinner=Player.USER;
													else
														gameWinner=Player.COMPUTER;
													break;
												case ROCK:
													if(compChoice==PAPER)
														gameWinner=Player.COMPUTER;
													else 
														gameWinner=Player.USER;
													break;
											}
										
										if(gameWinner==Player.USER)
										{
											System.out.println("Congrats, you won the game !!! ");
											playerWins++;
										}
										else
										{
											System.out.println("Hahah, I won and you lost !!!");
											compWins++;
										}
									}
										gameState=StateGame.START_ROUND;
										break;
					
				case RESULT:
								System.out.println();
								System.out.println("I Have won the game- "+compWins+ (compWins > 1?" times":" time."));
								System.out.println("You Have won the game- "+playerWins+ (playerWins > 1?" times":" time."));
								System.out.println((numberOfGames-playerWins-compWins)+" number of times game Tied");
								System.out.println("\n Game Over");
								gameState=StateGame.END_GAME;
					
					
			}
			
		}
		while(gameState!=StateGame.END_GAME);
	}
	
	private int getNum(String str) {
		System.out.print(str);
		Scanner sc=new Scanner(System.in);
		return sc.nextInt();
	}
	private void intro()
	{
		System.out.println("This is the Game of Rock Paper And Scissors");
		System.out.println();
		
	}
	
	
	public static void main(String[] args)
	{
		Game game=new Game();
		game.play();
	}
}
