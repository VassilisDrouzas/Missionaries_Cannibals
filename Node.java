import java.util.*;

public class Node 
{
    public Node parent;
    public int depth;
    
    public int missionariesAtLeft;
    public int cannibalsAtLeft;
      
    public boolean boatIsAtCoastLeft;
    
    public Node(Node root, int m, int c)
    {
    	this.parent = root; //for parent - kid relationship
    	this.depth = root.depth + 1; // to make evaluate return larger values
    	
    	this.missionariesAtLeft = root.missionariesAtLeft;
    	this.cannibalsAtLeft = root.cannibalsAtLeft;
        
        if(root.boatIsAtCoastLeft)
        {
        	this.missionariesAtLeft -= m;
        	this.cannibalsAtLeft -= c;
        }
        else
        {
        	this.missionariesAtLeft += m;
        	this.cannibalsAtLeft += c;
        }
        
        this.boatIsAtCoastLeft = !root.boatIsAtCoastLeft;  
    }
    
    public Node(int N)
    {
    	this.parent = null;
    	this.depth = 0;
        this.missionariesAtLeft = N;    
        this.cannibalsAtLeft = N;
        this.boatIsAtCoastLeft = true;
        
    }
    
    public int evaluate()
    {
    	int score = 0;
    	
    	if(this.isTerminal()) 
    	{
    		return depth;
    	}
    	
    	if(this.boatIsAtCoastLeft)
    	{
    		score = 1;
    	}
    	else
    	{
    		score = 2;
    	}
    	   	
    	return score + depth;
    }
    
    boolean isTerminal()
    {
    	return this.missionariesAtLeft == 0 && this.cannibalsAtLeft == 0;
    }
    
    ArrayList<Node> GetChildren()
    {
    	ArrayList<Node> childs = new ArrayList<Node>();
    	
    	for(int m = 0; m <= M_C.boatSize; m++)
    	{
    		for(int c = 0; c <= M_C.boatSize; c++)
    		{   				
    	    	if(m + c == 0) continue;
    	    	
    	    	if(m + c > M_C.boatSize) continue;
    	    	
    	    	if(m > c && c != 0) continue;
    	    	
    	    	Node child = new Node(this, m, c);    	    	
    			if(child.isValid())
    			{
    				childs.add(child);
    			}			
    		}
    	}
    	
    	return childs;
    }
    
    boolean isValid()
    {           
    	if(this.missionariesAtLeft < 0) return false;
    	if(this.cannibalsAtLeft < 0) return false;
    	if(this.missionariesAtRight() < 0) return false;
    	if(this.cannibalsAtRight() < 0) return false;   	
    		        
        if(this.missionariesAtLeft > 0)
        {
        	if(this.cannibalsAtLeft > this.missionariesAtLeft) return false;
        }
        
        if(this.missionariesAtRight() > 0)
        {
        	if(this.cannibalsAtRight() > this.missionariesAtRight()) return false;
        }
           	
    	return true;
    }
    
    public int missionariesAtRight()
    {
    	return M_C.N - this.missionariesAtLeft;
    }
    
    public int cannibalsAtRight()
    {
    	return M_C.N - this.cannibalsAtLeft;
    }
    
    public String toString()
    {
    	String str;
    	
    	str = String.format("(I = %d , C = %d) %s (I = %d , C = %d) (%d)", 
    			this.missionariesAtLeft, this.cannibalsAtLeft,
    			boatIsAtCoastLeft ? "{}   " : "   {}",
    			this.missionariesAtRight(), this.cannibalsAtRight(), this.evaluate());
    	  	
    	return str;
    }
    
    @Override
    public int hashCode() {
    	int hash = 17;
    	hash = hash * 31 + this.missionariesAtLeft;
    	hash = hash * 31 + this.cannibalsAtLeft;
    	hash = hash * 31 + (this.boatIsAtCoastLeft ? 1 : 2);

    	return hash;
    }
    
    @Override
    public boolean equals (Object o) 
    {
    	Node a = (Node)o;
    	  	
        return this.missionariesAtLeft == a.cannibalsAtLeft && 
        		this.cannibalsAtLeft == a.cannibalsAtLeft &&
        		this.boatIsAtCoastLeft == a.boatIsAtCoastLeft;
    }

}








