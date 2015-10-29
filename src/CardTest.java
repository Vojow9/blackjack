import static org.junit.Assert.*;

import org.junit.Test;

import blackjack.Card;


public class CardTest {

	@Test
	public void testcardValue() {
		Card c = new Card() ;
		assertEquals(2, c.cardValue(0));
	}

}
