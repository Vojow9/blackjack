package blackjack;
import java.util.*;


public class BlackJackMainFunctions
{
    // instance variables - 
	ArrayList<Integer> dealtCards;
	ArrayList<Integer> pCards;
	ArrayList<Integer> dCards;
	boolean gameOver;
	int playerWins;//1 - player wins, 2 dealer wins, 3 push
	int rand;
	int pValue;
	int dValue;
	String message;
	Card c;
	int money;
	int bet;
	In in = new In();
	Out out= new Out();
        	
         public BlackJackMainFunctions(){
         dealtCards = new ArrayList<Integer>();
         pCards = new ArrayList<Integer>();
         dCards = new ArrayList<Integer>();
         gameOver=false;
         playerWins=0;//1 - player wins, 2 dealer wins, 3 push
         rand=0;
         pValue=0;
         dValue=0;
         message="";
         c = new Card();
         in.readFromFile();
      	 money=in.getMoney();
         bet=0;
             
            }
            
         public void restart(){
            	dealtCards = new ArrayList<Integer>();
            	pCards = new ArrayList<Integer>();
            	dCards = new ArrayList<Integer>();
            	gameOver=false;
            	playerWins=0;//1 - player wins, 2 dealer wins, 3 push
            	rand=0;
            	pValue=0;
            	dValue=0;
            	message="";
            	c = new Card();
            	bet=0;
            }
            
            
           public void initDeal(){
        	   int y=0;
               while(y<4){
                 dealtCards.add(dealCard());
                 y++;
                }
               
            }
            
            public int dealCard(){
                rand=getRandom();
                while(exist(rand))
                 rand=getRandom();
                 
                 return rand;
            }
            
            public int getRandom(){
                return (int) (Math.random()*(52));
            }
            
            public boolean exist(int num){
                for(int i=0; i<dealtCards.size(); i++){
                    if(dealtCards.get(i)==num)
                       return true;
                }
                   
                  return false;
            }
            
            public void setArrays(){
                pCards.add(dealtCards.get(0));
                 pCards.add(dealtCards.get(2));
                  dCards.add(dealtCards.get(1));
                   dCards.add(dealtCards.get(3));
            }
            
            
    
         
            public void calculateValue(){
                pValue=0;
                for(int i=0; i<pCards.size(); i++)
                    pValue += c.cardValue(pCards.get(i));
                    
                    if(pValue>21){
                    gameOver=true;
                    playerWins=2;
                }
                    
                     dValue=0;
                for(int i=0; i<dCards.size(); i++)
                    dValue += c.cardValue(dCards.get(i));
                    
                     if(dValue>21){
                         gameOver=true;
                         playerWins=1;
                    }
                
            }
            
            public void whoWins(){
      
                  if(gameOver==false){
                      if(dValue>pValue)
                        playerWins=2;
                        else if(dValue<pValue)
                          playerWins=1;
                          else
                          playerWins=3;
                          
                      gameOver=true;
                    }
                 
                  if(playerWins==1){
                  message="Player WINS!!! Congrats ";
                   money=bet+money;
                   out.saveToFile(money);
                } else if(playerWins==2){
                   message="Dealer WIns, Sorry ";
                    money=money-bet;
                    out.saveToFile(money);
                } else
                     message="PUSH";
            }
            
            public void playerHit(){
               dealtCards.add(dealCard());
                  pCards.add(rand);
                  calculateValue();
            }
            
            public void dealerHit(){
                 while(gameOver==false && dValue<17){
                    dealtCards.add(dealCard());
                    dCards.add(rand);
                    calculateValue();
                    if(dValue>21){
                         gameOver=true;
                         playerWins=1;
                    }
                }
                whoWins();
            }//end of method
         
            
            
            
        }
