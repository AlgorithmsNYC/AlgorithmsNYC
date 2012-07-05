/*
 * main.cpp
 *
 *  Created on: Jun 14, 2012
 *      Author: sunil
 */

//E+v*LOGV : most optimal complexity E: edges, V: vertices
#include <iostream>
#include <set>
#include <map>
#include <vector>

using namespace std;


class Edge {
public:
	Edge(std::string start,std::string end,int weight): _start(start),_end(end) {
		_weight=weight;
	}
	void print() const{
		cout << "start:" << _start << ", end:" << _end << ", weight:" << _weight << endl;
	}
	std::string getStart() const {
		return _start;
	}
	std::string getEnd() const {
		return _end;
	}
	int getWeight() const {
		return _weight;
	}
public:
 	std::string _start;
	std::string _end;
	int    _weight;
};

class EdgeWeightComparator {
public:
	bool operator() (const Edge& lhs, const Edge& rhs) {
		return lhs._weight < rhs._weight;

	}

};



typedef std::multiset<Edge,EdgeWeightComparator> EdgeWeightList;

typedef std::set<std::string> SubtreeSet;
typedef std::map<std::string,SubtreeSet*> CompleteSet;

void printCompleteSet(CompleteSet& completeSet) {
    for(CompleteSet::iterator itr = completeSet.begin(); itr != completeSet.end(); itr++) {
    	SubtreeSet* subtreeSet = itr->second;
    	cout << "printing out elements for vertex:" << itr->first << " ,subtreeset:" << subtreeSet << endl;
    	for(SubtreeSet::iterator subItr = subtreeSet->begin(); subItr != subtreeSet->end(); subItr++ ) {
    		cout <<  *subItr << ",";
    	}
    	cout << endl;

    }
}

void printMST(vector<const Edge*>& mst) {
	for(vector<const Edge*>::iterator itr = mst.begin(); itr != mst.end(); itr++) {
		(*itr)->print();
	}
}

void printEdgeList(EdgeWeightList& edgeList) {
	cout << "Printing out edge weight list:" << endl;
	for(EdgeWeightList::iterator itr = edgeList.begin();itr != edgeList.end();itr++) {
		itr->print();
	}


}

int main(int argc,char* argv[]) {
	EdgeWeightList edgeList;
	int numVertices = 4;
	edgeList.insert(Edge("A","B",1));
	edgeList.insert(Edge("A","D",3));
	edgeList.insert(Edge("A","C",2));
	edgeList.insert(Edge("C","D",4));
	edgeList.insert(Edge("B","C",1));
	printEdgeList(edgeList);
	vector<const Edge*> mst;

	typedef vector<std::string> VertexList;
	VertexList vertexList;
	vertexList.push_back("A");
	vertexList.push_back("B");
	vertexList.push_back("C");
	vertexList.push_back("D");

	CompleteSet completeSet;

    for(VertexList::iterator itr = vertexList.begin();itr != vertexList.end(); itr++) {
    	SubtreeSet *subtreeSet = new SubtreeSet();
    	subtreeSet->insert(*itr);
    	completeSet.insert(std::pair<std::string,SubtreeSet*>(*itr,subtreeSet));
    }

    cout << "printing initial complete set:" << endl;
    printCompleteSet(completeSet);


	//print out edgeList, should be sorted now
	EdgeWeightList::iterator itr = edgeList.begin();
	while((mst.size() < numVertices-1) && (itr != edgeList.end())) {
	  const Edge& edge = *itr;
	  cout << "processing for edge:" << endl;
	  edge.print();
	  CompleteSet::iterator itr1,itr2;
	  itr1 = completeSet.find(edge.getStart());
	  itr2 = completeSet.find(edge.getEnd());
	  if(itr1->second != itr2->second) {
		  mst.push_back(&edge);
		  //merge both
		  SubtreeSet* sub1 = itr1->second;
		  SubtreeSet* sub2 = itr2->second;
		  for(SubtreeSet::iterator subitr2 = sub2->begin(); subitr2 != sub2->end();subitr2++) {
			  sub1->insert(*subitr2); //set will automatically drop duplicates
		  }
		  delete sub2;
		  completeSet.erase(edge.getEnd());
		  completeSet.insert(std::pair<std::string,SubtreeSet*>(edge.getEnd(),sub1));
	  }
	  else {
		  cout << "SKipping processing of this edge..." << endl;
	  }
	  cout << "at end of processing of above edge, complete set looks like:" << endl;
	  printCompleteSet(completeSet);
	  cout << "mst contains:" << endl;
	  printMST(mst);

	  itr++;
	}
	cout << "at end of complete processing, MST is:" << endl;
	printMST(mst);
}
