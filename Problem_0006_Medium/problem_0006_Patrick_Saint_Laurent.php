<?PHP

define('TEST', true);
define('PRIME_START', 3);


class Underprimes 
{

	private $underprimes=array();
	private $prime_cache=array(2);

	public function __constructor() {}

	public function howMany($A, $B) {

		$fc = 0;
		while($A <= $B) {

			$fc += (in_array(count($this->prime_factorization($A++)), $this->prime_cache)) ? 1 : 0;
		}
		return $fc;
	}

	private function prime_factorization($finish) {
		
		$dividend = $finish;
		$inner_finish = ceil(sqrt($finish+2);
		//start generating primes where we left off if this method has been run before
		$highest_generated_prime = $this->prime_cache[count($this->prime_cache)-1];
		$next_odd = ($highest_generated_prime != 2) ? $highest_generated_prime + 2 : PRIME_START;

		for($x=$next_odd;$x<=$finish;$x=$x+2) {

			$prime_flag = true;
			for($y=PRIME_START;$y<=$inner_finish;$y=$y+2) {

				if($x%$y == 0 && $x != $y) $prime_flag = false;
			}
			if($prime_flag) $this->prime_cache[] = $x;
		}

		$res = $dividend;
		$ret = array(); 
		$z = -1;

		while(!in_array($res, $this->prime_cache)) {

			if(($res%$this->prime_cache[++$z]) == 0) {

				$res = $res/$this->prime_cache[$z];
				$ret[] = $this->prime_cache[$z];
				$z= -1;
			}
		}

		if(in_array($res, $this->prime_cache)) {

			$ret[] = $res;
		}

		return $ret;
	}

	public function __destructor() {}
}


if(TEST) {

	$up = new Underprimes();
	echo "\n There are ".$up->howMany(2, 10)." underprimes. \n\n";
	echo "\n There are ".$up->howMany(100, 105)." underprimes. \n\n";
	echo "\n There are ".$up->howMany(17,17)." underprimes. \n\n";
	echo "\n There are ".$up->howMany(123, 456)." underprimes. \n\n";

}

?>
