import static org.junit.Assert.*;

import org.junit.Test;

import blackjack.In;


public class InTest {

	@Test
	public void testReadFromFile() {
		In in = new In();
		in.readFromFile();
		assertEquals(100,in.getMoney());
		
	}

}
