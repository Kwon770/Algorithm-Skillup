#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <vector>
#include <queue>
using namespace std;

struct Node
{
	int cur, via;
	long long sum;						// ���� ���� ���, ���� ���Ŀ� ��� ��, ����ġ ��
};
int N, T, A, B, C, farthest;			// farthest : 1�� ��忡�� ����Ͽ� ���� ���� ��带 ������ �� �����ϴ� ���
vector<pair<int, int>> tree[50001];		// (����� ����, ����ġ) �÷� ����

long long BFS(int start_v)				// Ʈ���� ���� : (���⿡����) �� ��忡�� �ٸ� ������ ���ľ� �ϴ� ��� ���� �ִ�
{
	int max = 0;							// start_v�������� ����Ͽ� ��ģ ��� ���� �ִ�
	long long weight_sum = 50000000000;		// �� ���� ����ġ ���� �ּ�
	bool visited[50001] = { false, };
	queue<Node> q;
	
	q.push({ start_v, 0, 0 });
	visited[start_v] = true;

	while (!q.empty())
	{
		Node node = q.front();
		q.pop();

		if (max < node.via || (max == node.via && weight_sum > node.sum))		// ��ģ ��� ���� �� �������ų� ��ģ ��� ���� �����鼭 ����ġ �� �ּڰ��� ���� ��
		{
			max = node.via;
			weight_sum = node.sum;
			farthest = node.cur;
		}
		for (auto edge : tree[node.cur])
			if (!visited[edge.first])
			{
				q.push({ edge.first, node.via + 1, node.sum + edge.second });
				visited[edge.first] = true;
			}
	}
	return weight_sum;
}

int main()
{
	scanf("%d %d", &N, &T);
	for (int i = 0; i < N - 1; i++)
	{
		scanf("%d %d %d", &A, &B, &C);
		tree[A].push_back({ B, C });
		tree[B].push_back({ A, C });
	}
	BFS(1);									// ���� 1���������� BFS�� �����Ͽ� farthest�� ����
	long long time = BFS(farthest);			// farthest���� ����Ͽ� ���� �ָ� ������ �������� �ð��� �ּڰ� ����
	if (time % T == 0)
		printf("%lld", time / T);
	else
		printf("%lld", time / T + 1);
	return 0;
}