#include <iostream>

using namespace std;

int dp[1001];
int main() {
	int n;
	cin >> n;
	
	dp[1] = 1;
	dp[2] = 0;
	dp[3] = 1;
	dp[4] = 1;
	dp[5] = 1;

	for (int i = 6; i <= n; i++) {
		if (dp[i - 1] == 0) {
			dp[i] = 1;
		}
		if (dp[i - 3] == 0) {
			dp[i] = 1;
		}
		if (dp[i - 4] == 0) {
			dp[i] = 1;
		}
	}
	if (dp[n] == 1) {
		cout << "SK";
	}
	else {
		cout << "CY";
	}

	return 0;
}