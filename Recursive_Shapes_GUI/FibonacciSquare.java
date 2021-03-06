package homework04;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Creates arcs in different quadrants to make a fibonacci square
 */
public class FibonacciSquare extends AbstractShape {

	// the quadrant that the arc will drawn in
	private int quad;
	
	// the fibonacci series number
	private int fibonacciSeries;
	
	/**
	 * Creates the fibonacci arc & square
	 * 
	 * @param int x: XCoordinate
	 * @param int y: YCoordinate
	 * @param color c: color
	 * @param int quadrant: quadrant
	 * @param int n: fibonacci series number
	 */
	public FibonacciSquare(int x, int y, Color c, int quadrant, int n) {
		super(x,y,c,10*fibonacciNum(n));
		
		this.fibonacciSeries = n;
		this.quad = quadrant;
		children = new Shape[1];
	}
	
	/** When given a fibonacci series number returns its fibonacci number */
	public static int fibonacciNum(int n) {
		
		// return value if the series number is 0 or 1
		if(n == 0 || n == 1) {
			return n;
		}
		
		
		// ints that are used in the calculation for the fibonacci number
		int a = 0; 
		int b = 1;
		int sum = 0;
		
		// calculates the fibonacci number
		for(int i = 2; i <= n; i++) {
			sum = a + b;
			a = b;
			b = sum;
		}
		
		// returns the fibonacci number
	    return sum;
	}

	/** draws the fibonacci arc and square 
	 * 
	 * @param g: Graphics for the FibonacciSquare
	 */
	@Override
	public void draw(Graphics g) {		
		
		// sets the color for the arc and square
		g.setColor(color);
		
		// first quadrant (use width = 100, height = 0)
		if(quad == 1) {
		
			// draws the fibonacci square
			g.drawRect(xCoord, yCoord, size, size);
			// draws the arc in the fibonacci square
			g.drawArc(xCoord - size, yCoord, 2*size, 2*size, 0, 90);
		}
		
		// second quadrant(use width = 0, height = 0)
		if(quad == 2) {
		
			// draws the fibonacci square
			g.drawRect(xCoord, yCoord, size, size);
			// draws the arc in the fibonacci square
			g.drawArc(xCoord, yCoord, 2*size, 2*size, 90, 90);
		}
		
		// third quadrant (use width = 0, height = 100)
		if(quad == 3) {
			
			// draws the fibonacci square
			g.drawRect(xCoord, yCoord, size, size);
			// draws the arc in the fibonacci square
			g.drawArc(xCoord, yCoord - size, 2*size, 2*size, 180, 90);
		}
		
		// fourth quadrant(width = 100, height = 100)
		if(quad == 4) {
		
			// draws the fibonacci square
			g.drawRect(xCoord, yCoord, size, size);
			// draws the arc in the fibonacci square
			g.drawArc(xCoord - size, yCoord - size, 2*size, 2*size, 270, 90);
		}

		// draws the most recent level in the fibonacci shape array
		try {
				children[0].draw(g);					
		
		// if there are no more levels then it stops drawing
		} catch(NullPointerException e) {}
	}
	
	/** Deletes the newest level of the FibonacciSquare so it returns to its previous level*/
	@Override
	public void deleteChildren() {
		
		// sets the child in the most recent level to null
		// this returns the shape to its previous level
		children[0] = null;
	}
	
	/** Creates a new level for the FibonacciSQuare
	 * 
	 * @return bool: whether or not children were created
	 */
	@Override
	public boolean createChildren() {
		
		// intial boolean set to false
		boolean bool = false;
			
				// depending on which quadrant was last, next shape/child
				// has certain characteristics, starting points (x,y coords)
		
				// path if the shape is in the 1st quadrant
				if (quad == 1 && 0 < xCoord - (10*fibonacciNum(fibonacciSeries+1))) {
					
					// creates a new FibonacciSquare child
					// then adds it to the children array
					children[0] = new  FibonacciSquare(xCoord - (10*fibonacciNum(fibonacciSeries+1)), 
							yCoord, color, quad + 1, fibonacciSeries + 1);
					
					// boolean set to true
					bool = true;
				}
			
				// path if the shape is in the 2nd quadrant
				if (quad == 2 && yCoord + (10*fibonacciNum(fibonacciSeries)) < 600 ) {
					
					// creates a new FibonacciSquare child
					// then adds it to the children array
					children[0] = new  FibonacciSquare(xCoord, 
							yCoord + (10*fibonacciNum(fibonacciSeries)), color, quad + 1, fibonacciSeries + 1);
					
					// boolean set to true
					bool = true;
				}
				
				// path if the shape is in the 3rd quadrant
				if (quad == 3 && xCoord +  (10*fibonacciNum(fibonacciSeries)) < 560 && 0 < yCoord - (10*fibonacciNum(fibonacciSeries - 1))) {
					
					// creates a new FibonacciSquare child
					// then adds it to the children array
					children[0] = new  FibonacciSquare(xCoord +  (10*fibonacciNum(fibonacciSeries)), 
							yCoord - (10*fibonacciNum(fibonacciSeries - 1)), color, quad + 1, fibonacciSeries + 1);
					
					// boolean set to true
					bool = true;
				}
				
				// path if the shape is in the 4th quadrant
				if (quad == 4 && 0 < xCoord - (10*fibonacciNum(fibonacciSeries - 1)) && 0 < yCoord - (10*fibonacciNum(fibonacciSeries + 1))) {
					
					// creates a new FibonacciSquare child
					// then adds it to the children array
					children[0] = new  FibonacciSquare(xCoord - (10*fibonacciNum(fibonacciSeries - 1)), 
							yCoord - (10*fibonacciNum(fibonacciSeries + 1)), color, 1, fibonacciSeries + 1);
					
					// boolean set to true
					bool = true;
				}
				
		// returns the boolean
		// true if a new FibonacciSquare child was created, false if not
		return bool;
	}
	
	/** 
	 * determines if the given location contains a FibonacciSquare
	 * 
	 * @param int x: x coordinate of given location
	 * @param int y: y coordinate of given location
	 * 
	 * @return boolean: whether or not the shape area contains the given coordinates 
	 */
	@Override
	public boolean contains(int x, int y) {
	
		// returns true if the given location is within the left half of the window
		if ((x > 0 && x < 500) && (y > 0 && y < 600)) {
			return true;
		} else {
			return false;
		}
	}
}