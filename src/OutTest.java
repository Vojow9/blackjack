import static org.junit.Assert.*;

import org.junit.Test;

import blackjack.In;
import blackjack.Out;


public class OutTest {

	@Test
	public void test() {
		Out out = new Out();
		In in = new In ();
	    out.saveToFile(100);
	    in.readFromFile();
	    assertEquals(100,in.getMoney());
	}

}
