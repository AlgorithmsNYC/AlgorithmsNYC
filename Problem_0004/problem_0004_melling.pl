#!/usr/bin/perl

# Author: melling
# Date: 8/1/2010
# Problem #4
#
# The idea is that we have two unknowns so we are going to guess 'g' using a binary search between 
# values (0.25, 50).  If our guessed value of 'g' gives us the known total drop time of all the objects,
# then we guessed right.

use warnings;
use strict;
use Carp::Assert;

# d = 0.5 * g * t^2

my $epsilon0=1e-12; # Need more precision on time comparison to get 'g' within epsilon
my $epsilon=1e-9;
my ($debug)=0;

#
# Calculate the total drop time for all the objects for our guess of g
#
sub calcTotalTimeForG {
  my ($object_heights,$g_guess)=@_;
  my $calc_total_time=0;

  foreach my $distance (@$object_heights) {
   $calc_total_time+=sqrt(2*$distance/$g_guess);
  }
  print "Calculated:f(g)=total time : f($g_guess)=$calc_total_time\n" if ($debug);
  return $calc_total_time;
}

#
# Do a binary search over our 'g' range: (0.25,50) until we guess the right 'g' that produces the correct
# total time.
#
sub gravitationalAcceleration {
  my ($object_heights,$total_observed_time)=@_;
  my ($guess_total_time, $time_diff, $g);
  my ($min_g, $max_g)=(0.25,50);
  my ($done)=0;
  my ($i)=0;

  while (!$done) {
    print "===== Iteration # $i =====\n" if ($debug);
    $g=($min_g + $max_g)/2.0;

    print "Observed total time: $total_observed_time\n" if ($debug);
    $guess_total_time = calcTotalTimeForG($object_heights,$g);
    $time_diff = $total_observed_time - $guess_total_time;
    print "Time Difference: $time_diff\n" if ($debug);
    $i++;
    if (abs($time_diff) < $epsilon0) {
      $done = 1;
    } elsif ($time_diff < 0) { # g isn't big enough
      $min_g = $g;
    } else {  # g is too big
      $max_g = $g;
    }
  }
  print <<EOF;
=== Solved in ${i} iterations =====
Inputs:
 Heights=@$object_heights
 Give Time: $total_observed_time seconds
Outputs:
 Calc Total Time=$guess_total_time seconds
  g=$g

EOF
  return $g;
}

#
# Test for our known cases
#
sub test {

  my (@heights,$total_time, $g);
 
  # Test #0
  @heights=(16,23,85,3,35,72,96,88,2,14,63);
  $total_time=30;
  $g=gravitationalAcceleration(\@heights, $total_time);
  assert(abs($g - 9.803799620759717) <= $epsilon);

  # Test #1
  @heights=(6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,5);
  $total_time=12;
  $g=gravitationalAcceleration(\@heights, $total_time);
  assert(abs($g - 26.73924541044107) <= $epsilon);

  # Test #2
  @heights=(8,8);
  $total_time=3;
  $g=gravitationalAcceleration(\@heights, $total_time);
  assert(abs($g - 7.111111111111111) <= $epsilon);

  # Test #3
  @heights=(3,1,3,1,3);
  $total_time=12;
  $g=gravitationalAcceleration(\@heights, $total_time);
  assert(abs($g - 0.7192306901503684) <= $epsilon);

}

test();


