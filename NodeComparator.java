import java.util.Comparator;

public class NodeComparator implements Comparator<Node>
{
	@Override
	public int compare(Node o1, Node o2) {
		
		int score1 = o1.evaluate();
		int score2 = o2.evaluate();
				
		return score1 - score2;
	}

}
