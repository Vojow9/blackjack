package blackjack;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Out {
	public void saveToFile(int money){
		try
		{
			PrintWriter writer = new PrintWriter("money.txt", "UTF-8");

			writer.println(String.valueOf(money));
			writer.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Critical error: " + e);
		}
		catch(UnsupportedEncodingException e)
		{
			System.out.println("Critical error: " + e);
		}
		}
	
}
