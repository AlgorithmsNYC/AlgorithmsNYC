<?php

function __autoload($class_name) {
  include 'classes/'.strtolower( $class_name ) . '.class.php';
}

$sizeX = 500;
$sizeY = 500;
$pointNumber = 1000;
$relationNumber = 2;
$font = 'arial.ttf';

putenv('GDFONTPATH=' . realpath('.'));

function get_key_for_minimum_value( $array, $exclusions = null ) {
  if( !is_null( $exclusions ) ) {
    $array = array_diff_key( $array, $exclusions );
  }

  return reset( array_keys( $array , min($array) ) );
}

function shortestPath( $vertices, $edges, $sourceVertex ) {
  $leftVertices = $vertices;
  $seenVertices = array();
  $distances[ $sourceVertex->guid ] = 0;
  $previousVertex = array();

  $i = 0;
  while( count( $seenVertices ) != count( $vertices ) && $i++ < count( $vertices ) ) {
    $currentVertex = $vertices[ get_key_for_minimum_value( $distances, $seenVertices ) ];
    $seenVertices[ $currentVertex->guid ] = $currentVertex;

    if( isset( $edges[ $currentVertex->guid ] ) ) {
      foreach( $edges[ $currentVertex->guid ] as $destinationVertexGuid => $weight ) {
        if(
          !isset( $distances[ $destinationVertexGuid ] )
          || $distances[ $currentVertex->guid ] + $weight < $distances[ $destinationVertexGuid ]
        ) {
          $distances[ $destinationVertexGuid ] = $distances[ $currentVertex->guid ] + $weight;
          $previousVertex[ $destinationVertexGuid ] = $currentVertex->guid;
        }
      }
    }
  }
  return $previousVertex;
}

$graph = new Graph();
$graph->random_point_generation_disk( 500, 150, 200 );
$graph->random_noncrossing_edge_generation( 3, null, true );

$image = $graph->draw( 500, 500, true );

ob_start();
imagepng($image);
$imagevariable = ob_get_clean();

echo '<img src="data:image/png;base64,'.base64_encode( $imagevariable ).'"/><pre>';

reset( $graph->vertices );
$sourceVertex = next( $graph->vertices );
$return = shortestPath( $graph->vertices, $graph->edges, $sourceVertex );

echo "</pre>";
foreach( array_keys( $return ) as $vertexGuid ) {
  $currentVertexGuid = $vertexGuid;
  $string = $vertexGuid;
  do {
    $previousVertexGuid = $return[ $currentVertexGuid ];
    $string = $previousVertexGuid . ' -> ' . $string;
    $currentVertexGuid = $previousVertexGuid;
  }while( $currentVertexGuid != $sourceVertex->guid );
  echo "<p>$string</p>";
}

