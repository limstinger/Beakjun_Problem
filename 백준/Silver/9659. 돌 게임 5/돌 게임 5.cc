#include <iostream>

using namespace std;

int main() {
	long N;
	cin >> N;
	
	if (N % 2 == 1) {
		cout << "SK";
	}
	else {
		cout << "CY";
	}

	return 0;
}