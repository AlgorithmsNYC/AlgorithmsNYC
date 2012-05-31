<?php

class Point {
  public $guid;
  public $x;
  public $y;
  
  static $distanceTable = array();
  static $angleTable = array();
  static $anglePolarTable = array();
  
  function __construct( $coordX, $coordY, $guid = null ) {
    $this->x = $coordX;
    $this->y = $coordY;
    $this->guid = $guid;

    if( is_null( $this->guid ) ) {
      $this->guid = str_pad( $coordX, 0, '0', STR_PAD_LEFT ).','.str_pad( $coordY, 0, '0', STR_PAD_LEFT );
    }
  }
  
  public function __toString() {
    return $this->guid;
  }
  
  static function distance( $point1, $point2 ) {
    
    //if( !isset( self::$distanceTable[ $point1->guid ][ $point2->guid ] ) ) {
    
      $a = $point2->x - $point1->x;
      $b = $point2->y - $point1->y;
      
      $distance = round( sqrt( pow( $a, 2 ) + pow( $b, 2 ) ), 1 );
      
      self::$distanceTable[ $point1->guid ][ $point2->guid ] = $distance;
      self::$distanceTable[ $point2->guid ][ $point1->guid ] = $distance;
    //}

    return self::$distanceTable[ $point1->guid ][ $point2->guid ];
  }
  
  static function getDistanceTable() {
    return self::$distanceTable;
  }
  
  static function angle( $pointA, $pointB, $pointC ) {
    if( ! isset( self::$angleTable[ $pointA->guid . ';' . $pointB->guid . ';' . $pointC->guid ] ) ) {
      $AB = Point::distance( $pointA, $pointB );
      $BC = Point::distance( $pointB, $pointC );
      $AC = Point::distance( $pointC, $pointA );
      
      // Al-Khashi theorem
      self::$angleTable[ $pointA->guid.';'.$pointB->guid.';'.$pointC->guid ] =
        rad2deg( acos( ( pow( $AB, 2 ) + pow( $BC, 2 ) - pow( $AC, 2 ) ) / ( 2 * $AB * $BC ) ) );
    }
    return self::$angleTable[ $pointA->guid.';'.$pointB->guid.';'.$pointC->guid ];
  }
  
  /* Polar angle difference between $pointA and $pointC, $pointB is the origin, $pointA is 0 angle */
  static function anglePolar( $pointA, $pointB, $pointC ) {
    if( ! isset( self::$anglePolarTable[ $pointA->guid . ';' . $pointB->guid . ';' . $pointC->guid ] ) ) {
      $Ax = $pointA->x - $pointB->x;
      $Ay = $pointA->y - $pointB->y;
      $Cx = $pointC->x - $pointB->x;
      $Cy = $pointC->y - $pointB->y;
      
      if( $Ax <= 0 && pow( $Ay, 2 ) == 0 ) {
        $Apolar = 180;
      }else {
        $Apolar = rad2deg( 2 * atan( $Ay / ( $Ax + sqrt( pow( $Ax, 2 ) + pow( $Ay, 2 ) ) ) ) );
      }
      if( $Cx + sqrt( pow( $Cx, 2 ) + pow( $Cy, 2 ) ) == 0 ) {
        $Cpolar = 180;
      }else {
        $Cpolar = rad2deg( 2 * atan( $Cy / ( $Cx + sqrt( pow( $Cx, 2 ) + pow( $Cy, 2 ) ) ) ) );
      }

      $result = $Cpolar - $Apolar;
      if( $result < 0 ) $result += 360;

      self::$anglePolarTable[ $pointA->guid.';'.$pointB->guid.';'.$pointC->guid ] = $result;
    }
    return self::$anglePolarTable[ $pointA->guid.';'.$pointB->guid.';'.$pointC->guid ];
  }
  
  static function aireTriangle( $pointA, $pointB, $pointC ) {
    $AB = Point::distance( $pointA, $pointB );
    $BC = Point::distance( $pointB, $pointC );
    $AC = Point::distance( $pointC, $pointA );
    
    return sqrt( ( $AB + $BC + $AC) * ( - $AB + $BC + $AC ) * ( $AB - $BC + $AC ) * ( $AB + $BC - $AC ) ) / 4;
  }
  
  static function isCrossing( $pointA, $pointB, $pointC, $pointD ) {
    //var_dump( "isCrossing", $pointA->guid, $pointB->guid, $pointC->guid, $pointD->guid );
    $isCrossing = false;
    
    if(
      $pointA == $pointC && $pointB == $pointD ||
      $pointA == $pointD && $pointB == $pointC ) {
      // All identical points
      $isCrossing = true;
    }elseif(
      $pointA != $pointC && $pointB != $pointD &&
      $pointA != $pointD && $pointB != $pointC
    ) {
      // If only one point id identical => not crossing
      
      
      
      // Division by zero protection
      $v1 = ($pointA->x == $pointB->x);
      $v2 = ($pointC->x == $pointD->x);
      
      if( ! ($v1 && $v2) ) {
        if( $v1 ) {
          // AB is vertical
          $aCD = ($pointD->y - $pointC->y) / ($pointD->x - $pointC->x);
          $bCD = $pointC->y - $aCD * $pointC->x;
          
          $commonY= $aCD * $pointA->x + $bCD;
          
          $isCrossing =
            $commonY > min( $pointA->y, $pointB->y ) &&
            $commonY < max( $pointA->y, $pointB->y ) &&
            $commonY > min( $pointC->y, $pointD->y ) &&
            $commonY < max( $pointC->y, $pointD->y );
        }elseif( $v2 ) {
          // CD is vertical
          $aAB = ($pointB->y - $pointA->y) / ($pointB->x - $pointA->x);
          $bAB = $pointA->y - $aAB * $pointA->x;

          $commonY= $aAB * $pointC->x + $bAB;
          
          $isCrossing =
            $commonY > min( $pointA->y, $pointB->y ) &&
            $commonY < max( $pointA->y, $pointB->y ) &&
            $commonY > min( $pointC->y, $pointD->y ) &&
            $commonY < max( $pointC->y, $pointD->y );
        }else {
          // a : directional coefficient / b : origin oodonnée
          $aAB = ($pointB->y - $pointA->y) / ($pointB->x - $pointA->x);
          $aCD = ($pointD->y - $pointC->y) / ($pointD->x - $pointC->x);
          
          // AB and CD are not parallel
          if( $aAB != $aCD ) {
            $bAB = $pointA->y - $aAB * $pointA->x;
            $bCD = $pointC->y - $aCD * $pointC->x;
            // X coordinate of the extended line crossing
            $commonX = ( $bCD - $bAB ) / ( $aAB - $aCD );
            
            // Lines are crossing between the given points
            $isCrossing =
              $commonX > min( $pointA->x, $pointB->x ) &&
              $commonX < max( $pointA->x, $pointB->x ) &&
              $commonX > min( $pointC->x, $pointD->x ) &&
              $commonX < max( $pointC->x, $pointD->x );
          }
        }
      }
    }
    //var_dump( $isCrossing );
    //if( $isCrossing ) var_dump( "isCrossing", $pointA->guid, $pointB->guid, $pointC->guid, $pointD->guid );
    return $isCrossing;
  }  
}