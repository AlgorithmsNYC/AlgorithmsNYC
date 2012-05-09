#include <stdio.h>
#include <stdbool.h>

/* Implementation of Prim's Algorithm
    -- for definition of algorithm, see *Introduction to Algorithms*, 
       Cormen, et al. third edition
    -- Daniel Cordes, 2012-05-08
*/

/* global definitions */
/*    definition of an edge, which is what Prim will return */
struct edge
{
  int u;  /* incident TO this vertex */
  int v;  /* incident TO this vertex */
};
/*    struct to store vertex key & parent information */
struct vertex_key_parent
{
  int key;    /* an edge weight */
  int parent; /* parent of the vertex */
};

/* checks if queue is populated */
bool queue_populated ( int size, bool queue[size] )
{
  bool populated = false;

  for ( int i = 0; i < size; i++ )
  {
    if ( queue[i] )
    {
      populated = true;
      break;
    }
  }

  return populated;
}

/* implementation of extract-min:
      -- that is, return the vertex number whose key value is not smaller than
         that of any other key
 */
int extract_min ( int size, bool queue[size], \
  struct vertex_key_parent vertex_info[size] )
{
  /* start by setting the first key equal to the minimum key */
  int min_key = -1;

  /* check each key for minimum size, updating min_key as we go */
  for ( int i = 0; i < size; i++ )
  {
    /* if the min_key has not yet been initialized, and this vertex is in the
     * queue, set min_key to this vertex */
    if ( min_key == -1 && queue[i] )
    {
      min_key = i;
    }
    /* otherwise, if min_key is already set, perform the comparison check: i.e.,
     * if this vertex is in the queue, and its value is less than our current
     * minimum value, then update the minimum value key */
    else if ( queue[i] && vertex_info[i].key < vertex_info[min_key].key )
    {
      min_key = i;
    }
  }

  /* return the key number that we have identified */
  return min_key;
}

/* Prim's algorithm */
struct edge *mst_prim ( int size, int ugraph[size][size], int r, \
  struct edge ugraph_mst[size*size] )
{
  /* array of key/parent structs for this matrix, where:
      key = 999999999, arbitrarily high value to simulate infinity
      parent = -1, meaning NIL, i.e. no parent
  */
  struct vertex_key_parent vertex_info[size];
  for ( int i = 0; i < size; i++ )
  {
    vertex_info[i].key = 999999999;
    vertex_info[i].parent = -1;
  }

  /* initialize starting vertex */
  vertex_info[r].key = 0;

  /* lame implementation of a queue */
  bool ugraph_queue[size];
  for ( int i = 0; i < size; i++ )
  {
    ugraph_queue[i] = true;
  }

  /* loop until queue is empty */
  while ( queue_populated ( size, ugraph_queue ) )
  {
    /* find a vertex in the queue whose key value is not less than the key value
     * of any other vertex in the queue */
    int u = extract_min ( size, ugraph_queue, vertex_info );

    /* simulate a queue pop by marking the element just popped as no longer
     * present */
    ugraph_queue[u] = false;

    /* loop through all vertices adjacent to vertex u, the one just found */
    for ( int v = 0; v < size; v++ )
    {
      if ( 
        v != u &&             /* if we are not self-referential */
        ugraph[u][v] != 0 &&  /* if this is an adjacency */
        ugraph_queue[v] &&    /* if this is in the queue */
        ugraph[u][v] < vertex_info[v].key /* the weight of this edge is less
                                             the weight of the current key */
        )
      {
        vertex_info[v].parent = u;
        vertex_info[v].key = ugraph[u][v];
      }
    }
  }

  /* populate the set of edges in the minimum spanning tree */
  int mst_edge = 0;
  for ( int i = 0; i < size; i++ )
  {
    if ( vertex_info[i].parent != -1 )
    {
      ugraph_mst[mst_edge].u = vertex_info[i].parent;
      ugraph_mst[mst_edge].v = i;
      mst_edge++;
    }
  }

  /* return the mst */
  return ugraph_mst;
}

/* print a square 2-dim matrix whose rows/cols = size/size */
void ugraph_print ( int size, int ugraph[size][size] )
{
  for ( int row = 0; row < size; row++ )
  {
    for ( int column = 0; column < size; column++ )
    {
      printf ("\t%i", ugraph[row][column] );
    }
    printf ("\n");
  }
}

/* print an edge set for a 2-dim matrix whose rows/cols = size/size */
void ugraph_mst_print ( int size, struct edge ugraph_mst[size*size] )
{
  for ( int i = 0; ugraph_mst[i].u != -1; i++ )
  {
    printf ("%i <=====> %i\n", ugraph_mst[i].u, ugraph_mst[i].v );
  }
}

/* main function begins */
int main (void)
{
  /* note on graph representation 
      -- each test input graph is treated as a unigraph, i.e. undirected
      -- adjacency-matrix representation is used; thus a matrix of {x,y}
          signifies a graph with x vertices, where x == y, with the presence and
          weight of each connecting edge between vertices x and y denoted by the
          value of cell {x,y}
      -- the value of each cell is to be interpreted as follows:
        -- 0: no edge/adjacency
        -- i: edge/adjacency present with weight of integer i
  */

  /* test unigraph 1 */
  int ugraph1_size = 5; /* length/width of matrix */
  int ugraph1[5][5] =   /* matrix itself */
  {
    { 0, 0, 1, 2, 0 },
    { 0, 0, 3, 0, 5 },
    { 2, 3, 0, 6, 4 },
    { 1, 0, 6, 0, 0 },
    { 0, 5, 4, 0, 0 }
  };

  /* edge for matrix, returned by prim; -1 means empty element */
  struct edge ugraph1_mst[ugraph1_size*ugraph1_size]; 
  for ( int i = 0; i < ( ugraph1_size * ugraph1_size ); i++ )
  {
    ugraph1_mst[i].u = -1;
    ugraph1_mst[i].v = -1;
  }

  /* test unigraph 2 */
  int ugraph2_size = 7; /* length/width of matrix */
  int ugraph2[7][7] =   /* matrix itself */
  {
    { 0, 3, 0, 0, 0, 0, 6 },
    { 3, 0, 0, 4, 0, 0, 8 },
    { 0, 1, 0, 2, 10,0, 0 },
    { 0, 4, 2, 0, 0, 0, 5 },
    { 0, 0, 10,0, 0, 8, 3 },
    { 0, 0, 0, 0, 8, 0, 0 },
    { 6, 8, 0, 5, 3, 0, 0 }
  };
  struct edge ugraph2_mst[ugraph2_size*ugraph2_size]; 
  for ( int i = 0; i < ( ugraph2_size * ugraph2_size ); i++ )
  {
    ugraph2_mst[i].u = -1;
    ugraph2_mst[i].v = -1;
  }

  /* arbitrary starting vertex */
  int r = 0;

  /* get MST for unigraph1, and print */
  printf ("Matrix:\n");
  ugraph_print ( ugraph1_size, ugraph1 );
  printf ("Minimum spanning tree for matrix, from Prim's algorithm:\n");
  ugraph_mst_print ( ugraph1_size, mst_prim ( ugraph1_size, ugraph1, r, \
    ugraph1_mst  )  );
  printf ("~~~~~~~~~~~~~~~~~~~~~~~~\n");

  /* get MST for unigraph2, and print */
  printf ("Matrix:\n");
  ugraph_print ( ugraph2_size, ugraph2 );
  printf ("Minimum spanning tree for matrix, from Prim's algorithm:\n");
  ugraph_mst_print ( ugraph2_size, mst_prim ( ugraph2_size, ugraph2, r, \
    ugraph2_mst  )  );
  printf ("~~~~~~~~~~~~~~~~~~~~~~~~\n");

  /* normal return value */
  return 0;
}
