package blackjack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class In {

	int money;
	public int getMoney() {
		return money;
	}

	public void readFromFile(){
		try
	    {
	    	File file = new File("money.txt");
			String line;
			
	    	BufferedReader reader = new BufferedReader(new FileReader(file));
			line = reader.readLine();
			reader.close();
			money=Integer.valueOf(line);
		}
	    catch(FileNotFoundException e1)
		{
			e1.printStackTrace();
		}
	    catch(NumberFormatException e)
	    {
	    	System.out.println("Critical error: " + e);
		}
	    catch(IOException e)
	    {
	    	System.out.println("Critical error: " + e);
		}
		
	}
}
