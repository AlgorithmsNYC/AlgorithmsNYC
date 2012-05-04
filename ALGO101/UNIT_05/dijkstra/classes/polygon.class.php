<?php

class Polygon implements ArrayAccess, Countable, Iterator {
  protected $points = array();
  
  // Interface ArrayAccess
  public function offsetSet($offset, $value) {
    if (is_null($offset)) {
      $this->points[] = $value;
    } else {
      $this->points[$offset] = $value;
    }
  }
  public function offsetExists($offset) {
    return isset($this->points[$offset]);
  }
  public function offsetUnset($offset) {
    unset($this->points[$offset]);
  }
  public function offsetGet($offset) {
    return isset($this->points[$offset]) ? $this->points[$offset] : null;
  }
  
  // Interface Countable
  public function count() {
    return count( $this->points );
  }
  
  //Interface Iterator
  private $position = 0;

  public function __construct() {
    $this->position = 0;
  }

  public function rewind() {
    $this->position = 0;
  }

  public function current() {
    return $this->points[ $this->position ];
  }

  public function key() {
    return $this->position;
  }

  public function next() {
    ++$this->position;
  }

  public function valid() {
    return isset($this->points[$this->position]);
  }
  
  
  
  public function addPoint( Point $point ) {
    $this->points[] = $point;
  }
  
  public function contains( Point $point ) {
    return in_array( $point, $points );
  }
  
  /**
   * Checks the presence of a point in the polygon
   * Returns :
   * 0 : Point not in polygon
   * 1 : Point in polygon
   * 2 : Point is a node of the polygon
   * 
   * @see http://stackoverflow.com/questions/217578/point-in-polygon-aka-hit-test
   */
  public function includes( Point $point ) {
    $return = 0;
    //Whole area check : does it include the center ?
    $includes_point = false;
    $origin = new Point(-1, -1);
    
    $airePointAKey = count( $this->points ) - 1;
    for( $airePointBKey = 0; $airePointBKey < count( $this->points ); $airePointBKey++ ) {
      if( $point->guid == $this->points[ $airePointBKey ]->guid ) {
        $return = 2;
        break;
      }

      if( Point::isCrossing( $origin, $point, $this->points[ $airePointAKey ], $this->points[ $airePointBKey ] ) )
        $includes_point = !$includes_point;

      $airePointAKey++;
      if( $airePointAKey == count( $this->points ) ) {
        $airePointAKey = 0;
      }
    }
    
    if( $return == 0 ) {
      $return = (int) $includes_point;
    }

    return $return;
  }
  
  public function getPerimeter() {
    $return = null;
    
    if( count( $this->points ) >= 2 ) {
      $perimeterPoints = $this->points;
      $perimeterPoints[] = $perimeterPoints[0];
      $perimeter = 0;
      for( $i = 0; $i < count( $this->points ); $i++ ) {
        $perimeter += Point::distance( $perimeterPoints[ $i ], $perimeterPoints[ $i + 1 ]);
      }
      
      $return = $perimeter;
    }
    
    return $return;
  }
  
  /**
   * Calculates the area of any given non-crossing polygon
   * @see http://www.mathopenref.com/coordpolygonarea2.html
   */
  public function getArea() {
    $return = null;
    if( count( $this->points ) >= 3 ) {
      $areaPoints = $this->points;
      $areaPoints[] = $areaPoints[0];
      $area = 0;
      for( $i = 0; $i < count( $this->points ); $i++ ) {
        $area += $areaPoints[ $i ]->x * $areaPoints[ $i + 1 ]->y;
        $area -= $areaPoints[ $i + 1 ]->x * $areaPoints[ $i ]->y;
      }
      
      $return = abs( $area / 2 );
    }
    
    return $return;
  }
  
  /**
   * Gets the coordinates of the centroid of the polygon
   * @see http://stackoverflow.com/questions/2792443/finding-the-centroid-of-a-polygon
   */
  public function getCentroid() {
    $return = null;
    
    if( count( $this->points ) >= 3 ) {
      $centroid = new Point(0,0);
      $signedArea = 0;
      $x0 = $y0 = $x1 = $y1 = 0;
      $a = 0;  // Partial signed area
      $vertices = $this->points;
      $vertices[] = $vertices[0];

      // For all vertices except last
      for ($i = 0; $i < count( $vertices ) - 1; $i++)
      {
          $x0 = $vertices[$i]->x;
          $y0 = $vertices[$i]->y;
          $x1 = $vertices[$i+1]->x;
          $y1 = $vertices[$i+1]->y;
          $a = $x0 * $y1 - $x1 * $y0;
          $signedArea += $a;
          $centroid->x += ($x0 + $x1) * $a;
          $centroid->y += ($y0 + $y1) * $a;
      }

      $signedArea *= 0.5;
      $centroid->x /= (6 * $signedArea);
      $centroid->y /= (6 * $signedArea);

      $return = $centroid;
    }
    return $return;
  }
  
  public static function find_polygons_in_graph( Graph $graph ) {
    $polygonList = array();

    $vertices = $graph->vertices;
    $edges = $graph->edges;
    
    foreach( $vertices as $guid => $point ) {      
      $polygonList = array_merge( $polygonList, polygon_finder_recursive( $graph, array(), $point, null, false ) );
    }
    
    return $polygonList;
  }
  
  function polygon_finder_recursive( Graph $graph, $currentPolygon, $currentVertex, $lastVertex ) {    
    // Interdiction path table
    // Struct : array[ pointGuid1 ][ pointGuid2 ]
    static $pathTable = array();
    $polygons = array();
    $return = false;
    $vertices = $graph->vertices;
    $edges = $graph->edges;
    
    if( is_null( $lastVertex) || !isset( $pathTable[ $lastVertex->guid ][ $currentVertex->guid ] ) ) {
      // The path loops = area found
      if( in_array( $currentVertex, $currentPolygon ) ) {

        // Working backward to find the closure point, exclude non-area included vertices
        $polygon = new Polygon;
        do {
          $newPoint = array_pop( $currentPolygon );
          $polygon->addPoint( $newPoint );
        }while( $currentVertex != $newPoint );
        $currentPolygon = $polygon;

        // If the polygon area doesn't include the central point
        if( $polygon->includes( reset( $vertices ) ) !== 1 ) {
        
          // Update the interdiction table
          $j = count( $currentPolygon ) - 1;
          for( $k = 0; $k < count( $currentPolygon ); $k++ ) {
            //$pathTable[ $currentPolygon[ $j ]->guid ][ $currentPolygon[ $k ]->guid ] = true;
            $pathTable[ $currentPolygon[ $k ]->guid ][ $currentPolygon[ $j ]->guid ] = true;

            $j ++;
            if( $j == count( $currentPolygon ) ) $j = 0;
          }

          $return = $currentPolygon;
        }
      }else {
        $currentPolygon[] = $currentVertex;

        if( is_null( $lastVertex ) ) {
          // First point : we search every line from the point
          foreach( array_keys( $edges[ $currentVertex->guid ] ) as $guid ) {
            $polygon = polygon_finder_recursive( $graph, $currentPolygon, $vertices[ $guid ], $currentVertex );
            
            if( $polygon !== false ) $polygonList[] = $polygon;

            $return = $polygonList;
          }
        }else{        
          // Existing line : we follow the first available path with the smallest angle      
          $angleList = array();
          foreach( array_keys( $edges[ $currentVertex->guid ] ) as $guid ) {
            // Stop condition : already passed through here in this direction
            if(
              $lastVertex->guid != $guid &&
              !isset( $pathTable[ $currentVertex->guid ][ $vertices[ $guid ]->guid ] )
            ) {
              $angleList[ $guid ] = Point::anglePolar( $lastVertex, $currentVertex, $vertices[ $guid ]);
            }
          }
          asort( $angleList );

          list( $guid, $angle ) = each( $angleList );
          if( ! is_null( $guid ) ) {
            $return = polygon_finder_recursive( $graph, $currentPolygon, $vertices[ $guid ], $currentVertex );
          }
        }
      }
    }

    return $return;
  }

}