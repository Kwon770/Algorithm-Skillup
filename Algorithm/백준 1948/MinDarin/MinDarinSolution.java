import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception
	{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int n = Integer.parseInt(line);
		line = br.readLine();
		int m = Integer.parseInt(line);
		LinkedList<Integer[]>[] map = new LinkedList[n+1];
		
		HashSet<Integer> Route = new HashSet();
		node[] info = new node[n+1];
		LinkedList<Integer[]>[] reverse = new LinkedList[n+1];	//�������� ���� ���� ���� ã�� ���� �ڿ������� ���� �����ϴ°� ã�Ƴ���
		for(int i = 1; i <=n; i++)
		{
			reverse[i] = new LinkedList<Integer[]>();
			map[i] = new LinkedList<Integer[]>();
			info[i] = new node();
		}
		int[] start_count = new int[n+1];
		int road_idx = 0;
		for(int i = 0; i < m; i++)
		{			
			line = br.readLine();
			StringTokenizer st = new StringTokenizer(line);
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			Integer[] to = new Integer[2];
			to[0] = e;
			to[1] = w;
			map[s].add(to);
			start_count[e]++;
			
			Integer[] r_to = new Integer[3];
			r_to[0] = s;
			r_to[1] = w;
			r_to[2] = road_idx++;
			reverse[e].add(r_to);
		}
		line = br.readLine();
		StringTokenizer st = new StringTokenizer(line);
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		LinkedList<Integer>queue = new LinkedList();
		queue.add(start);
		long answer_w = 0;
		while(!queue.isEmpty())
		{
			int cur = queue.pop();
			if(cur == end)
			{
				answer_w = info[cur].w;
				break;
			}
			while(!map[cur].isEmpty())
			{
				Integer[] next = map[cur].pop(); // [0]�� ���� ��ġ [1]�� �ױ��� ���µ� �ɸ� �ð�
				if(info[next[0]].w < info[cur].w + next[1])
				{
					info[next[0]].w = info[cur].w+next[1];
//					info[next[0]].c = info[cur].c+1;	//�̷��� �ϸ� �ݷʰ� ���� (���� �ݷ� ã�Ƴ���)
				}
//				else if(info[next[0]].w == info[cur].w + next[1])
//				{
//					info[next[0]].c += info[cur].c+1;
//				}
				//���� ���� ��
				start_count[next[0]]--;
				if(start_count[next[0]] == 0)
				{
					queue.add(next[0]);
				}
			}
		}
		System.out.println(answer_w);

		queue = new LinkedList();
		queue.add(end);
		while(!queue.isEmpty())
		{
			int cur = queue.pop();
			Iterator it = map[cur].iterator();
			while(!reverse[cur].isEmpty())
			{
				Integer[] next = reverse[cur].pop();
				if(info[cur].w- info[next[0]].w == next[1])
				{
					queue.add(next[0]);
					Route.add(next[2]);
				}
			}
		}
		System.out.println(Route.size());
	}
}
class node
{
	long w = 0;
//	int c = 0;
}
