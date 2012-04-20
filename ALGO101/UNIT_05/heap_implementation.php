<?php

/*
 * PHP implementation of the heap algorithm 
 */

class Heap {
  // heap is stored as a binary tree-shaped array
  // Rules :
  // - the binary tree is balanced
  // - the children's values must be equal or greater to the parent's value
  var $keys = array();
  
  /**
   * Basic function to add a value to the heap
   */
  function insert( $value ) {
    // Add the new value to the end
    $this->keys[] = $value;
    
    $new_index = count( $this->keys ) - 1;
    
    // Check tree rules, specifically the parent/children value rule
    // While there is a rule break between the new value and its parent, we swap its value with his parent and work upward (bubblize up the value)
    while(
      self::node_parent( $new_index ) !== false
      && $this->keys[ $new_index ] < $this->keys[ self::node_parent( $new_index ) ]
    ) {
      $parent_index = self::node_parent( $new_index );
      
      //Swaping
      $tmp = $this->keys[ $new_index ];
      $this->keys[ $new_index ] = $this->keys[ $parent_index ];
      $this->keys[ $parent_index ] = $tmp;
      
      //Working upward
      $new_index = $parent_index;
    }
  }
  
  /**
   * Function to extract the minimum value of the heap
   */
  function extract_min() {
    // According to rules, minimum is always the root of the heap
    $return = $this->keys[0];
    
    // We overwrite the root's value with the last value of the heap to keep the tree's balance
    $this->keys[0] = array_pop( $this->keys );
    
    $cur_index = 0;

    // Working downward, we swap the new value with the lowest children value if the value rule is broken
    while( !is_null( $min_key = $this->smallest_child_key( $cur_index ) )  ) {
      $min_child = $this->smallest_child_value( $cur_index );

      if( $this->keys[ $cur_index ] > $min_child ) {
        // Swaping value
        $tmp = $this->keys[ $cur_index ];
        $this->keys[ $cur_index ] = $this->keys[ $min_key ];
        $this->keys[ $min_key ] = $tmp;
      }
      
      $cur_index = $min_key;
    }

    return $return;
  }
  
  /**
   * Gets the lowest value of the children of a given node
   */
  function smallest_child_value( $index ) {
    $left_child_key = self::node_child_left( $index );
    $right_child_key = self::node_child_right( $index );
    
    if( isset($this->keys[ $left_child_key ]) ) {
      $left_child = $this->keys[ $left_child_key ];
    }else {
      $left_child = null;
    }
    
    if( isset($this->keys[ $right_child_key ]) ) {
      $right_child = $this->keys[ $right_child_key ];
    }else {
      $right_child = null;
    }

    return min( $left_child, $right_child );
  }
  
  /**
   * Gets the child key of the lowest value of the children of a given node
   */
  function smallest_child_key( $index ) {
    $return = null;
    $left_child_key = self::node_child_left( $index );
    $right_child_key = self::node_child_right( $index );
    if( isset($this->keys[ $left_child_key ]) ) {
      $left_child = $this->keys[ $left_child_key ];
    }else {
      $left_child_key = null;
      $left_child = null;
    }
    
    if( isset($this->keys[ $right_child_key ]) ) {
      $right_child = $this->keys[ $right_child_key ];
    }else {
      $right_child_key = null;
      $right_child = null;
    }
    
    if( $left_child <= $right_child ) {
      $return = $left_child_key;
    }else {
      $return = $right_child_key;
    }

    return $return;
  }
  
  /**
   * Gets the parent's key of a given node
   */
  static function node_parent( $key ) {
    if( $key == 0 ) return false;
    return floor( ($key - 1) / 2 ); 
  }

  /**
   * Gets the left child's key of a given node
   */
  static function node_child_left( $key ) {
    return $key * 2 + 1;
  }
  
  /**
   * Gets the right child's key of a given node
   */
  static function node_child_right( $key ) {
    return $key * 2 + 2;
  }

  function toString() {
    foreach( $this->keys as $key ) echo "[$key]";
    echo "<br/>";
  }
}

$heap = new Heap();

for( $i = 0; $i < 10; $i++ ) {
  $heap->insert( rand( 1, 15 ) );
  $heap->toString();
}
$heap->toString();

for( $i = 0; $i < 10; $i++ ) {
  echo "<strong>".$heap->extract_min( )."</strong><br/>";
  $heap->toString();
}

?>