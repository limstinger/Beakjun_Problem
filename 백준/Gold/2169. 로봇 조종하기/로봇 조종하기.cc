#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N, M;
std::vector<std::vector<int>> map;
void input() {	// 입력 받기
	cin >> N;
	cin >> M;

	map = std::vector<std::vector<int>>(N + 1, vector<int>(M + 1));
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			cin >> map[i][j];
		}
	}
	
}


void DP(const std::vector<std::vector<int>>& _map) {
	std::vector<std::vector<int>> dp(N + 1, vector<int>(M + 1));
	std::vector<std::vector<int>> temp(2, vector<int>(M + 2));
	
	dp[1][1] = _map[1][1];
	for (int i = 2; i <= M; i++) {
		dp[1][i] = dp[1][i - 1] + _map[1][i];
	}

	for (int i = 2; i <= N; i++) {

		temp[0][0] = dp[i-1][1];
		for (int j = 1; j <= M; j++) {	// 왼쪽에서 시작
			temp[0][j] = std::max(temp[0][j - 1], dp[i - 1][j]) + _map[i][j];
		}

		temp[1][M + 1] = dp[i-1][M];
		for (int j = M; j >= 1; j--) {	// 오른쪽에서 시작
			temp[1][j] = std::max(temp[1][j + 1], dp[i - 1][j]) + _map[i][j];
		}

		for (int j = 1;j<=M;j++) {
			dp[i][j] = std::max(temp[0][j], temp[1][j]);
		}
	}
	cout << dp[N][M] << endl;
}

int main() {
	input();

	DP(map);

	return 0;
}