<?php

function __autoload($class_name) {
  include 'php_classes/'.strtolower( $class_name ) . '.class.php';
}

// Algorithm code

function get_key_for_minimum_value( $array, $exclusions = null ) {
  if( !is_null( $exclusions ) ) {
    $array = array_diff_key( $array, $exclusions );
  }

  return reset( array_keys( $array , min($array) ) );
}

function primsAlgorithm( $graph, $sourceVertex ) {
  $vertices = $graph->vertices;
  $edges = $graph->edges;
  $seenVertices = array();
  $distances[ $sourceVertex->guid ] = 0;
  $previousVertex = array();

  $i = 0;
  while( $i++ < count( $vertices ) ) {
    $currentVertex = $vertices[ get_key_for_minimum_value( $distances, $seenVertices ) ];
    $seenVertices[ $currentVertex->guid ] = $currentVertex;

    if( isset( $edges[ $currentVertex->guid ] ) ) {
      foreach( $edges[ $currentVertex->guid ] as $destinationVertexGuid => $weight ) {
        if(
          ! in_array( $vertices[ $destinationVertexGuid ], $seenVertices )
          && (
            !isset( $distances[ $destinationVertexGuid ] )
            || $weight < $distances[ $destinationVertexGuid ]
          )
        ) {
          $distances[ $destinationVertexGuid ] = $weight;
          $previousVertex[ $destinationVertexGuid ] = $currentVertex->guid;
        }
      }
    }
  }
  return $previousVertex;
}

// Visualization code

$graph = new Graph();
$graph->random_point_generation_disk( 500, 100, 150 );
$graph->random_noncrossing_edge_generation( 3, null, true );

$image = $graph->draw( 500, 500, true );

ob_start();
imagepng($image);
$imagevariable = ob_get_clean();

echo '<img src="data:image/png;base64,'.base64_encode( $imagevariable ).'"/>';

reset( $graph->vertices );
$sourceVertex = next( $graph->vertices );
$return = primsAlgorithm( $graph, $sourceVertex );

$graph->drop_edges();
foreach( array_keys( $return ) as $vertexGuid ) {
  $graph->add_edge_guid( $return[ $vertexGuid ], $vertexGuid, true );
}

$image = $graph->draw( 500, 500, true );

ob_start();
imagepng($image);
$imagevariable = ob_get_clean();

echo '<img src="data:image/png;base64,'.base64_encode( $imagevariable ).'"/>';

