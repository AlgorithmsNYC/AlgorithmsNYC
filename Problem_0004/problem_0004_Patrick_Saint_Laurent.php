<?PHP


define('TEST', true);

class IncredibleMachineEasy
{

	public function __destructor() {}

	public function gravitationalAcceleration(array $height, $T=0) {

		$accel = null;
		array_walk($height, function($elem, $key, $t) {
					$t['accel'] += (float) sqrt(2*$elem)/$t['time'];
				    }, array('time' => $T, 'accel' => &$accel));
		return (float) pow($accel, 2);
	}

	public function __constructor() {}
}


if(TEST) {
	$ime = new IncredibleMachineEasy();

	$h = array(16,23,85,3,35,72,96,88,2,14,63);
	$t = 30;
	echo "\n The acceleration of gravity in this system is: ".$ime->gravitationalAcceleration($h, $t)."\n";

	$h = array(6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,5);
	$t = 12;
	echo "\n The acceleration of gravity in this system is: ".$ime->gravitationalAcceleration($h, $t)."\n";

	$h = array(8,8);
	$t = 3;
	echo "\n The acceleration of gravity in this system is: ".$ime->gravitationalAcceleration($h, $t)."\n";

	$h = array(3,1,3,1,3);
	$t = 12;
	echo "\n The acceleration of gravity in this system is: ".$ime->gravitationalAcceleration($h, $t)."\n";
}

?>
