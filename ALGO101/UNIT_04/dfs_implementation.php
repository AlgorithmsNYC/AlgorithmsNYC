<?php

// Graph initialization

$graph = array();
$values = array();

for( $i = 0; $i < 10; $i ++ ) {
  $graph[ $i ] = array_fill( 0, 9, 0 );
  $values[ $i ] = rand( 10, 50 );
}

for( $i = 0; $i < 20; $i ++ ) {
  $start = rand( 0, 9 );
  $end = rand( 0, 9 );
  
  $graph[ $start ][ $end ] = 1;
  $graph[ $end ][ $start ] = 1;
}

function dfs( $graph, $current_key ) {
  static $explore = array();
  
  echo "Explored node $current_key<br/>";
  
  $explore[ $current_key ] = 1;
  
  foreach( $graph[ $current_key ] as $node_key => $is_vertex ) {

    if( $is_vertex && !isset( $explore[ $node_key ] ) ) {

      dfs( $graph, $node_key );
    }

  }

}

dfs( $graph, 0 );