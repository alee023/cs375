#include <iostream>
#include <vector> 

using namespace std;

struct Node {
  int data;
  Node * left;
  Node * right;

  Node (int data) {
    this -> data = data;
    this -> left = this -> right = nullptr;
  }
};

bool isSubTree(Node * s, Node * t){

  vector <Node *> q;
  q.push_back(s);
  Node * sameT = nullptr;

  // Do bfs through s to see if the value t exists
  while (!q.empty()) {

      Node * temp = q[0];
      q.erase(q.begin());

      if (temp->data == t->data) {
        sameT = temp;
        break;
      }
      if (temp->left != nullptr) {
        q.push_back(temp->left);
      } if (temp->right != nullptr) {
        q.push_back(temp->right);
      }
  }

  if (sameT == nullptr) {
      return false;
  } 

  // Do bfs through both trees to see if they match

  vector<Node*> q1;
  vector<Node*> q2;
  q1.push_back(sameT);
  q2.push_back(t);
  
  while (!q2.empty()) {
      Node * temp2 = q1[0];
      Node * temp = q2[0];
      q2.erase(q2.begin()); 
      q1.erase(q1.begin());  

    if (temp->data != temp2->data){
          return false;
    }

      if (temp->right != nullptr && temp2->right != nullptr) {
          if (temp->right->data != temp2->right->data) {
            return false;
          }
          q2.push_back(temp->right);
          q1.push_back(temp2->right);
      } else if ((temp->right != nullptr && temp2->right == nullptr) || (temp->right == nullptr && temp2->right != nullptr)) {
        return false;
      }
      
      if (temp->left != nullptr && temp2->left != nullptr) {
          if (temp->left->data != temp2->left->data) {
            return false;
          }
          q2.push_back(temp->left);
          q1.push_back(temp2->left);
      } else if ((temp->left != nullptr && temp2->left == nullptr) || (temp->left == nullptr && temp2->left != nullptr)) {
        return false;
      }
  }

  if (!q1.empty()) {
      return false;
  }

  return true;
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
	root->right->left->left->left = new Node( 34 ) ;
	root->right->right->right = new Node( 35 ) ;


  Node* root2 = new Node( 23 ) ;
  root2->left = new Node( 32 ) ;
  root2->right = new Node( 33 ) ;
  root2->left->left = new Node( 34 ) ;

  printf("%d", isSubTree(root, root2));
}