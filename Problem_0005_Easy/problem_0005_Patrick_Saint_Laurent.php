<?PHP


define('TEST', true);

class SimpleWordGame 
{
	

	public function __constructor() {}

	public function points(array $player, array $dictionary) {

	   return (int) array_reduce(array_unique(array_intersect($player, $dictionary)), function ($agg_val, $nval) { 
												return $agg_val + pow(strlen($nval), 2); 
											  });
	}

	public function __destructor() {}

}


/** Test **/ 

if(TEST) {

	$swg = new SimpleWordgame();
	$x=1;
	
	$p = array("apple", "orange", "strawberry");
	$d = array("strawberry", "orange", "grapefruit", "watermelon");
	echo "\n  Test Run ".$x++.": ".$swg->points($p, $d)."\n";
	
	$p = array("apple");
	$d = array("strawberry", "orange", "grapefruit", "watermelon");
	echo "\n  Test Run ".$x++.": ".$swg->points($p, $d)."\n";

	$p = array("orange", "orange");
	$d = array("strawberry", "orange", "grapefruit", "watermelon");
	echo "\n  Test Run ".$x++.": ".$swg->points($p, $d)."\n";
	
	$p = array("lidi", "o", "lidi", "gnbewjzb", "kten", "ebnelff", "gptsvqx", "rkauxq", "rkauxq", "kfkcdn");
	$d = array("nava", "wk", "kfkcdn", "lidi", "gptsvqx", "ebnelff", "hgsppdezet", "ulf", "rkauxq", "wcicx");

	echo "\n  Test Run ".$x++.": ".$swg->points($p, $d)."\n";
}



?>
