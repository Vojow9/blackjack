package blackjack;

import java.applet.*;
import java.awt.*;
import java.net.*;
import java.util.*;

public class BJackGraphical extends Applet implements Runnable
{
// variables
	BlackJackMainFunctions bjb = new BlackJackMainFunctions();//connection to main class
	private Thread th;//our thread that will create our run method
	Font font;
	int font_size = 20;//fond size for text on screen
	Image[] card=new Image[52];//image of each card
	Image redChip;//image of red chip
	Image greenChip;//image of green chip
	MediaTracker mt;//for creating image
	URL base;//used for creating image
	private Image dbImage;//used for drawing image to screen
	private Graphics dbg;//used for drawing all graphics to screen
	int z=0;
	String msg="";
	int stage=0;//keeps track of which stage in the game you are in...
  

public void init()
{
setBackground (Color.black);//color of the background

  try { 
        base = getDocumentBase(); // getDocumentbase gets the applet path. 
       } catch (Exception e) {}
  
        mt = new MediaTracker(this); // initialize the MediaTracker 
         


  for (int i=2; i<15; i++){
      for (int y=1; y<5; y++){
      //counter++;
          card[z] = getImage(base, "cards/b"+i+"-"+y+".gif");
          z++;
        }}
        //set images for the chips below
           greenChip = getImage(base, "cards/greenchip.png");
           redChip = getImage(base, "cards/redchip.png");
            mt.addImage(greenChip,1);
             mt.addImage(redChip,1);
  // tell the MediaTracker to keep an eye on this image, and give it ID 1; 
         
           for (int i=0; i<52; i++){
              mt.addImage(card[i],1);//add each card to media tracker
           }


}
 
  public boolean mouseDown(Event e, int x, int y)//added(Event e, int x, int y)
       {
          msg=" " + x + " " + y;
       if(stage==0){//betting stage
              if((x>352 && x<415)&&(y>52 && y<108))
                     {
                         bjb.bet+=10;
                   
                       }
                       
               if((x>352 && x<415)&&(y>152 && y<216))
                     {
                         bjb.bet-=10;
                   
                       }
                        
                       
                 if((x>310 && x<450)&&(y>250 && y<298))//if you click on DEAL
                     {
                      
                          openDeal();
                        stage++;// Stage increments
                       }//opening deal
                       
               }      
              if(stage==1){                   
                 if((x>310 && x<450)&&(y>67 && y<110))
                     {
                       
                  
                          bjb.playerHit();//player hits
                          
                        
                         // bet++;
                       
                    }
                    
                     if((x>310 && x<450)&&(y>173 && y<225))
                     {
                       
                       
                         stage++;
                        // if(stage>1)
                          bjb.dealerHit();//dealer hits
                          msg=bjb.message;//message set - whether wins, lose or push
                        
                    }
                }
                
              if(stage==2){                   
                 if((x>310 && x<450)&&(y>67 && y<110))
                     {   
               
                          bjb.restart();//player hits
                          stage=0;
                        }
                    }
                        

        return true;
        }

public void start ()
{
	th = new Thread(this);
	th.start ();
}

public void stop()
{
	th.stop();
}

public void destroy()
{
	th.stop();
}

public void run () // If you recall, the run method sets everything in motion
{
	Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
	while (true)
	{
		//openDeal();
		// repaint applet
		repaint();

		try
		{
			Thread.sleep(15);
		}
		catch (InterruptedException ex)
		{
			// do nothing
		}

		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
	}
}



public void update (Graphics g)
{
	if (dbImage == null)
	{
		dbImage = createImage (this.getSize().width, this.getSize().height);
		dbg = dbImage.getGraphics ();
	}

	dbg.setColor (getBackground ());
	dbg.fillRect (0, 0, this.getSize().width, this.getSize().height);

	dbg.setColor (getForeground());
	paint (dbg);

	g.drawImage (dbImage, 0, 0, this);
}


   public void openDeal(){//deal out cards and set arrays and calculate views
       
       bjb.initDeal();
       bjb.setArrays();
       bjb.calculateValue();
      // open=true;
       //stage++;
    }



public void paint (Graphics g)//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
{
// draw player
	font = new Font("SansSerif", Font.BOLD, font_size);
	g.setFont(font);
	g.setColor (Color.blue) ;

	if(stage==0)
		g.fillRect( 310,250,150,50);//x,y w, h - deal button

	if(stage==1){
		g.fillRect( 310,60,150,50);//x,y w, h - HIT
		g.fillRect( 310,170,150,50);//x,y w, h - STAY
	}
	if(stage==2)
		g.fillRect( 310,60,150,50);//x,y w, h - HIT

		g.setColor (Color.red) ;
    if(stage==0){
    	g.drawString("Place Bet " , 320,270);
    	g.drawImage(greenChip,350,50,this);
    	g.drawImage(redChip,350,150,this);
    }
            
    if(stage==1){
    	if(bjb.pValue>21){
    		stage=2;
    		bjb.whoWins();
    		msg=bjb.message;
    	}
    	g.drawString("Player Hit " , 320,80); 
    	g.drawString("Player Stay " , 320,194); 
    } 
    if(stage==2)
    	g.drawString("Play Again " , 320,80); 

    if(stage>0){

    	g.drawString("Player: " + bjb.pValue, 10,140);
    	g.drawString("Dealer: " + bjb.dValue, 10,330);
    }

   
    if(stage>0){
    	int x=0;

    	for(int i=0; i<bjb.pCards.size(); i++){  

    		g.drawImage(card[bjb.pCards.get(i)],x,10,this); //image,x,y,width,height - draws players cards
    		x+=40;
    	}
  
    	x=0;

    	for(int i=0; i<bjb.dCards.size(); i++){  
    
    		g.drawImage(card[bjb.dCards.get(i)],x,200,this); //image,x,y,width,height
    		x+=40;
    	}
    }
    g.drawString( bjb.message, 100,380);//displays message at end of game

    g.drawString(" BET: $" + bjb.bet + " - MONEY: $"+ bjb.money, 200,20);
}
}
