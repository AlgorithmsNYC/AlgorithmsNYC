#!/usr/bin/ruby

def points(player, dictionary)
  score = 0
  player.uniq!
  player.each{|item|
    if dictionary.include?(item)
      score += item.length**2
    end
  }
  return score.to_int
end

# Case0
player = [ "apple", "orange", "strawberry" ]
dictionary = [ "strawberry", "orange", "grapefruit", "watermelon" ]
puts "Case0: #{points(player, dictionary)}"

# Case1
player = [ "apple" ]
dictionary = [  "strawberry", "orange", "grapefruit", "watermelon" ]
puts "Case1: #{points(player, dictionary)}"

# Case2
player = [ "orange", "orange" ]
dictionary = [  "strawberry", "orange", "grapefruit", "watermelon" ]
puts "Case2: #{points(player, dictionary)}"

# Case3
player = [ "lidi", "o", "lidi", "gnbewjzb", "kten", "ebnelff", "gptsvqx", "rkauxq", "rkauxq", "kfkcdn"]
dictionary = [  "nava", "wk", "kfkcdn", "lidi", "gptsvqx", "ebnelff", "hgsppdezet", "ulf", "rkauxq", "wcicx"]
puts "Case3: #{points(player, dictionary)}"
