#include <iostream>

using namespace std ;

struct Node {
	int data ;
	Node *left, *right ;
	
	// constructor
	Node( int data ) {
		this->data = data ;
		this->left = this->right = nullptr ;
	}
} ;

Node* FCA( Node* curr, Node* node1, Node* node2 ) {
	if( curr == nullptr ) return nullptr ;
	if( curr == node1 || curr == node2 ) { 
		return curr ;
	}
	
	Node* leftSubtree = FCA( curr->left, node1, node2 ) ;
	Node* rightSubtree = FCA( curr->right, node1, node2 ) ;
	// if &&->true, then one of each node is present in each tree...
	if( leftSubtree != nullptr && rightSubtree != nullptr ) {
		// ... then the current node holding the leftSubtree and rightSubtree has to be the FCA
		return curr ;
	}
	
	// otherwise return the non-null subtree
	else if( leftSubtree != nullptr ) return leftSubtree ;
	else {
		return rightSubtree ;
	}
}

int main() {
	Node* root = new Node( 1 ) ;
	root->left = new Node( 11 ) ;
	root->left->left = new Node( 21 ) ;
	root->left->left->left = new Node( 28 ) ;
	root->left->left->right = new Node( 29 ) ;
	root->left->right = new Node( 22 ) ;
	root->left->right->left = new Node( 30 ) ;
	root->left->right->right = new Node( 31 ) ;
	root->right = new Node( 12 ) ;
	root->right->left = new Node( 23 ) ;
	root->right->left->left = new Node( 32 ) ;
	root->right->left->right = new Node( 33 ) ;
	root->right->right = new Node( 24 ) ;
	root->right->right->left = new Node( 34 ) ;
	root->right->right->right = new Node( 35 ) ;
	
	Node* first = FCA( root, root->left->left->right, root->left->right->right ) ;
	cout << "FCA( 29, 31 ) is " << first->data << endl ; // should be 11
	
	Node* second = FCA( root, root->right->left->right, root->left->left ) ;
	cout << "FCA( 33, 21 ) is " << second->data << endl ; // should be 1
	
	Node* third = FCA( root, root->left, root->right->left ) ;
	cout << "FCA( 11, 23 ) is " << third->data << endl ; // should be 24
}