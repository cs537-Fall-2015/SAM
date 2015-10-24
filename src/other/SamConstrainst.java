package other;

import org.junit.After;
import org.junit.Before;

import SAM.SAM;


public class SamConstrainst<PadsController> {

	SamController controller;
	SAM sam;
	
	@Before
    public void setUp() {
		controller = new SamController();
		sam = new SAM();
    }
	
	@Test
	public void testLoadBits() {
		int spareBits = 0;
		for(int i=2; i>=-1; i--){
			sam = controller.action("SAM_LOAD_BITS"); 
		}
		assertEquals(sam.getSpare_bits(), spareBits);
	}

	@Test
	public void testReplaceBits() {
		int spareBits = 2;
		sam = ((SamController) controller).action("SAM_REPLACE_BITS"); 
		SAM sam = null;
		System.out.println("sam.getSpare_bits()"+sam.getSpare_bits()); 
		assertEquals(sam.getSpare_bits(), spareBits);
	}

	private void assertEquals(int spare_bits, int spareBits) {
		// TODO Auto-generated method stub
		
	}

	@After
	public void tearDown() {
	    controller = null;
	    sam = null;		
	}
}