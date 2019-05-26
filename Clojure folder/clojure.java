import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class clojure {
	
	ArrayList<Character> map = new ArrayList<>();
	int noOfCol;
	boolean vis[];
	int noOfRow;
	
	
	public static void main(String args[]) {
		
		clojure obj = new clojure();
		obj.getInput();
		
	}

	private void getInput() {
		try {
			BufferedReader br = new BufferedReader(
					new FileReader
					("/home/kbeepi/eclipse-workspace/test/PCT/src/map1.txt"));
			String s = br.readLine();
			noOfCol = s.length();
			for(char c: s.toCharArray())
				map.add(c);
			noOfRow=1;
			while((s=br.readLine())!=null)
			{
				addToArray(s);
				noOfRow++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		System.out.println(noOfCol+" "+noOfRow);
		
		for(int i=0; i<(noOfRow*noOfCol); i++)
		{
			if(i%noOfCol==0)
				System.out.println();
			System.out.print(map.get(i));
		}
		
		vis = new boolean[map.size()];
		System.out.println(findPath(0));
		//int j=noOfCol;
		for(int i=0; i<(noOfRow*noOfCol); i++)
		{
			if(i%noOfCol==0)
				System.out.println();
			System.out.print(map.get(i));
		}
	}

	private void addToArray(String s) {
		for(char c: s.toCharArray())
			map.add(c);
	}

	private boolean findPath(int i) {
		
		boolean res=false;
		if(i<0||i>=(noOfCol*noOfRow))
			return false;
		if(map.get(i)=='@')
			return true;
		if(map.get(i)=='#')
			return false;
		if(map.get(i)=='!')
			return false;
		else
		{
			map.set(i, '!');
			int up = i-noOfCol;
			int down = i+noOfCol;
			int right= -1, left =-1;
			if((i+1)%noOfCol!=0)
				right = i+1;
			if(i%noOfCol!=0)
				left = i-1;
			res = findPath(down) || findPath(right) || findPath(left) || findPath(up) ;
			
			if(res == true)
			{
				map.set(i, '+');
				return true;
			}
			else
				return false;
		}
		
	}

}
