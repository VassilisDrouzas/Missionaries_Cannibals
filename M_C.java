import java.util.*;

public class M_C {

	static int boatSize;
	static int N;
	
	static HashSet<Node> done;
	
	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
		
        System.out.println("White the Number of each group: ");
        N = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("White the Capasity of the boat: ");
        boatSize = scanner.nextInt();
        scanner.nextLine();
		
        System.out.println("White max number of moves: ");
        int depth = scanner.nextInt();
        scanner.nextLine();
        
        long start = System.currentTimeMillis();
        Node winner = Astar(new Node(N), depth);
        long end = System.currentTimeMillis();
        System.out.println("Search time:" + (double)(end - start) / 1000 + " sec.");  // total time of searching in seconds.
		
        if(winner == null)
        {
        	System.out.println("No solutions with depth " + depth);
        }
        else
        {
        	System.out.println("A solution was found with depth " + depth);
        	
            System.out.println("\n <<< The Path >>> \n");
            
            ArrayList<Node> path = new ArrayList<Node>();
            
            Node curr = winner;
            while(curr != null)
            {
            	path.add(curr);
            	curr = curr.parent;
            }
            
            for(int i = path.size() - 1; i >= 0; i--)
            {
            	System.out.println(path.get(i).toString());
            }
        	
        }
               
        scanner.close();
	}
	
	
	static Node Astar(Node root, int maxdepth)
	{
		if(root.isTerminal())
		{
			return root;
		}
		
		done = new HashSet<Node>(); // closed set		
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparator());
		
		frontier.add(root);
	
		while(frontier.size() > 0)
		{
			// find the best node in nodes	
			Node n = frontier.poll();
						
			if(n.isTerminal())
			{
				return n;
			}
			
			if(!M_C.done.contains(n))
			{
				M_C.done.add(n);
				
				if(n.depth < maxdepth)
				{
					ArrayList<Node> childen = n.GetChildren();
					frontier.addAll(childen);
				}
			}
								
		}				
		return null;
	}
	
	
	

}
