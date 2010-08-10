def points(player, dictionary)
  (player & dictionary).collect { |word| word.length * word.length }.inject(0) { |sum, n| sum + n}
end

raise "Test failed!" unless points( [ "apple", "orange", "strawberry" ],  [ "strawberry", "orange", "grapefruit", "watermelon" ]) == 136
raise "Test failed!" unless points( [ "apple" ], [ "strawberry", "orange", "grapefruit", "watermelon" ]) == 0
raise "Test failed!" unless points( [ "orange", "orange" ], [ "strawberry", "orange", "grapefruit", "watermelon" ]) == 36
raise "Test failed!" unless points( [ "lidi", "o", "lidi", "gnbewjzb", "kten", "ebnelff", "gptsvqx", "rkauxq", "rkauxq", "kfkcdn"],
                                    [ "nava", "wk", "kfkcdn", "lidi", "gptsvqx", "ebnelff", "hgsppdezet", "ulf", "rkauxq", "wcicx" ]) == 186

