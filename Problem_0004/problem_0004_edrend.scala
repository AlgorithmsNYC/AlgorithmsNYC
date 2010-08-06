package home

//import scala.testing.SUnit._
 
object Problem4 {
  def main(args : Array[String]) : Unit = {
	val tests = List(
	(9.803799620759714, Array(16,23,85,3,35,72,96,88,2,14,63), 30),
	(26.73924541044106, Array(6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,5), 12),		
	(7.1111111111111125, Array(8,8), 3),
	(0.7192306901503684, Array(3,1,3,1,3),12)
	)
    tests.map(test => {
    	val (r,h,t) = test
    	assert(r == IncredibleMachineEasy.gravitationalAcceleration (h, t), "Failed test")
    })
  }
}

object IncredibleMachineEasy{
	def gravitationalAcceleration(height:Array[Int], t:Int):Double = {
		val d = height.foldLeft(0.0) (_ + Math.sqrt(_))
		2*d*d/(t*t)
	}
}