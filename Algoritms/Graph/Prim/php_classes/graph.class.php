<?php
// ver 3

class Graph {
  // List of every vertex in the graph, indexed by point guid
  public $vertices = array();
  // Directed two-dimensional array with weight : $edges[ Start point guid ][ End point guid ] = weight
  public $edges = array();
  // Two-dimensional array of every distance between points, computed during point generation
  public $distances = array();
  
  public function add_point( Point $point, $minDist = null, $maxDist = null ) {
    $pointCreationFlag = count( $this->vertices ) == 0;
    $distanceTemp = array();

    foreach( $this->vertices as $pointItem ) {
      $distance = Point::distance( $point, $pointItem );
      
      $distanceTemp[ $pointItem->guid ] = $distance;
      
      if( !is_null( $minDist ) && $distance < $minDist ) {
        $pointCreationFlag = false;

        break;
      }

      if( !is_null( $maxDist ) ) {
        $pointCreationFlag = $pointCreationFlag || ($distance < $maxDist);
      }else {
        $pointCreationFlag = true;
      }
    }

    if( $pointCreationFlag ) {
      foreach( $distanceTemp as $guid => $distance ) {
        $this->distances[ $point->guid ][ $guid ] = $distance;
        $this->distances[ $guid ][ $point->guid ] = $distance;
      }

      $this->vertices[ $point->guid ] = $point;
    }
    
    return $pointCreationFlag;
  }
  
  public function add_edge_guid( $vertexStartGuid, $vertexEndGuid, $isDirected = false ) {
    $vertexStart = $this->vertices[ $vertexStartGuid ];
    $vertexEnd = $this->vertices[ $vertexEndGuid ];

    return $this->add_edge( $vertexStart, $vertexEnd, $isDirected );
  }
  
  public function add_edge( Point $vertexStart, Point $vertexEnd, $isDirected = false ) {
    $return = true;
    // TODO : Check existence
    $distance = $this->distances[ $vertexStart->guid ][ $vertexEnd->guid ];
  
    $this->edges[ $vertexStart->guid ][ $vertexEnd->guid ] = $distance;
    if( ! $isDirected ) {
      $this->edges[ $vertexEnd->guid ][ $vertexStart->guid ] = $distance;
    }
    
    return $return;
  }
  
  public function drop_edges() {
    $this->edges = array();
  }
  
  public function random_point_generation_simple( $sizeX = 500, $sizeY = 500, $numPoints = 10 ) {
    for( ;$numPoints != 0; $numPoints--) {
      $this->add_point( new Point( rand(0, $sizeX - 1), rand(0, $sizeY - 1) ) );
    }
  }
  
  public function random_point_generation_disk( $sizeX, $minDist, $maxDist ) {
    $sizeY = $sizeX;
    // Initial point generation
    $this->add_point( new Point( round( $sizeX / 2 ), round( $sizeY / 2 ), '0' ) );
    
    $initialPoint = reset( $this->vertices );

    for( $i = 65; $i < 91; $i++ ) $letters[] = chr( $i );
    for( $i = 97; $i < 123; $i++ ) $letters[] = chr( $i );
    $i = 0;
    
    // Point creation procedure
    $maxMissedPoints = 1000;
    $missedPointCount = 0;
    while( $missedPointCount < $maxMissedPoints ) {
      $x = rand(0, $sizeX - 1);
      $y = rand(0, $sizeY - 1);
      
      $point = new Point( $x, $y, $letters[ $i ] );
      
      $pointCreationFlag = false;
      
      if( Point::distance( $point, $initialPoint ) <= ($sizeX / 2) ) {
        $pointCreationFlag = $this->add_point( $point, $minDist, $maxDist );
      }

      if( $pointCreationFlag ) {
        $missedPointCount = 0;
        $i++;
      }else {
        $missedPointCount++;
      }
    }
  }
  
  public function random_noncrossing_edge_generation( $relationNumber = 2, $maxDist = null, $isDirected = false ) {
    $edgeNumber = 0;
    foreach( $this->distances as $guid => $distanceList ) {
      asort( $distanceList );
      $edgeByVertexNumber = 0;
      while( (list( $pointGuid, $distance ) = each( $distanceList ) ) && $edgeByVertexNumber < $relationNumber ) { 
        $edgeDrawingFlag = true;
        
        // Existence check
        if( isset( $this->edges[$guid][$pointGuid] ) )
          $edgeDrawingFlag = false;

        // Distance check
        if( $edgeDrawingFlag && !is_null( $maxDist ) && $distance > $maxDist * 1.9 )
          $edgeDrawingFlag = false;

        // Same vertex, different orientation check
        if( $edgeDrawingFlag && !isset( $this->edges[$pointGuid][$guid] ) {
          // Crossing check
          foreach( $this->edges as $guidA => $lines ) {
            foreach( $lines as $guidB => $dummy ) {
              if( Point::isCrossing( $this->vertices[ $guid ], $this->vertices[ $pointGuid ], $this->vertices[ $guidA ], $this->vertices[ $guidB ] ) ) {
                continue 3; // continue while loop
              }
            }
          }
        }

        // Adding new edge
        if( $edgeDrawingFlag )
        ) {
          $this->add_edge_guid( $guid, $pointGuid, $isDirected );
          $edgeByVertexNumber++;
          $edgeNumber++;
        }
        
      }
    }
    return $edgeNumber;
  }

  public function draw($sizeX = 500, $sizeY = 500, $isDirected = false) {
    
    $font = __DIR__.'/arial.ttf';

    $img = imagecreatetruecolor($sizeX,$sizeY); 
    imagesavealpha($img, true);

    // Fill the image with transparent color 
    $color = imagecolorallocatealpha($img,0x00,0x00,0x00,127); 

    $white = imagecolorallocate($img, 255, 255, 255);
    $red = imagecolorallocate($img, 255, 0, 0);
    $green = imagecolorallocate($img, 0, 255, 0);
    $blue = imagecolorallocate($img, 0, 0, 255);
    $black = imagecolorallocatealpha($img, 0, 0, 0, 0);
    $grey = imagecolorallocate($img, 211, 211, 211);

    $sand = imagecolorallocate($img, 255, 200, 0);
    $earth = imagecolorallocate($img, 50, 21, 12);
    $earth = imagecolorallocate($img, 101, 159, 0);

    $marine = imagecolorallocate($img, 0, 100, 255); 
    $sea = imagecolorallocate($img, 0, 161, 185);

    imagefill($img, 0, 0, $grey);

    $divisor = 100;

    for($iw=1; $iw < $sizeX / $divisor; $iw++){imageline($img, $iw * $divisor, 0, $iw * $divisor, $sizeX, $white);}
    for($ih=1; $ih < $sizeY / $divisor; $ih++){imageline($img, 0, $ih * $divisor, $sizeX, $ih * $divisor, $white);}
    
    foreach( $this->vertices as $vertex ) {
      imagerectangle($img, $vertex->x + 1, $sizeY - ($vertex->y + 1), $vertex->x - 1, $sizeY - ($vertex->y - 1), $black);
      // Ajout d'ombres au texte
      imagettftext($img, 15, 0, $vertex->x + 3, $sizeY - ($vertex->y + 3), $grey, $font, $vertex->guid);

      // Ajout du texte
      imagettftext($img, 15, 0, $vertex->x + 2, $sizeY - ($vertex->y + 2), $black, $font, $vertex->guid);
    }
    $alength = 15;
    $awidth = 3;
    foreach( $this->edges as $vertexStartGuid => $edges ) {
      foreach( $edges as $vertexEndGuid => $distance ) {
        if( $isDirected ) {
          $dx = $this->vertices[$vertexEndGuid]->x + ($this->vertices[$vertexStartGuid]->x - $this->vertices[$vertexEndGuid]->x) * $alength / $distance;
          $dy = $this->vertices[$vertexEndGuid]->y + ($this->vertices[$vertexStartGuid]->y - $this->vertices[$vertexEndGuid]->y) * $alength / $distance;

          $k = $awidth / $alength;

          $x2o = $this->vertices[$vertexEndGuid]->x - $dx;
          $y2o = $dy - $this->vertices[$vertexEndGuid]->y;

          $x3 = $y2o * $k + $dx;
          $y3 = $x2o * $k + $dy;

          $x4 = $dx - $y2o * $k;
          $y4 = $dy - $x2o * $k;

          imageline($img, $this->vertices[$vertexStartGuid]->x, $sizeY - $this->vertices[$vertexStartGuid]->y, $dx, $sizeY - $dy, $marine);
          imagefilledpolygon($img, array($this->vertices[$vertexEndGuid]->x, $sizeY - $this->vertices[$vertexEndGuid]->y, $x3, $sizeY - $y3, $x4, $sizeY - $y4), 3, $marine);
        }else {
          imageline(
            $img,
            $this->vertices[$vertexStartGuid]->x,
            $sizeY - $this->vertices[$vertexStartGuid]->y,
            $this->vertices[$vertexEndGuid]->x,
            $sizeY - $this->vertices[$vertexEndGuid]->y,
            $sand
          );
        }
      }
    }
    
    foreach( $this->vertices as $vertex ) {
      // Ajout d'ombres au texte
      imagettftext($img, 15, 0, $vertex->x + 3, $sizeY - ($vertex->y + 3), $grey, $font, $vertex->guid);
      // Ajout du texte
      imagettftext($img, 15, 0, $vertex->x + 2, $sizeY - ($vertex->y + 2), $black, $font, $vertex->guid);
    }
    
    return $img;
  }
}