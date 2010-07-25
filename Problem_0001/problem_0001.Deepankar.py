from collections import defaultdict
import itertools


def index(seq, f):
        """Return the index of the first item in seq where f(item) == True."""
            return next((i for i in xrange(len(seq)) if seq[i] == f), None)

        # returns [(num,coord),(num,coord)....]
        def line_two_pairs (line):
                bag = []
                    d = defaultdict(list)
                        count = 0
                        for char,count in zip(line,range(0,len(line))):
                                    d[char].append(count)
                                    for k,v in d.items():
                                        for perm in itertools.combinations(v,2):
                                                        bag.append((k, perm))
                                                            return bag

                                                        #prior_lines looks like
                                                        # [(line, [(num, coord], (num,coord)...]), ...]
                                                        def max_for_line (line_no,line_pairs, prior_lines):
                                                                line_max = 1
                                                                for num, coord in line_pairs:
                                                                            side = coord[1] - coord[0] + 1
                                                                                    prior_line = line_no - side + 1
                                                                                    if prior_line >= 0:
                                                                                                    prior_line_no, prior_line_pairs = prior_lines[prior_line]
                                                                                                                match = index(prior_line_pairs,(num,coord))
                                                                                                                if match is not None:
                                                                                                                                    line_max = max(line_max, side*side)
                                                                                                                                        return line_max

                                                                                                                                    def get_max (alist):
                                                                                                                                            max_size = 1
                                                                                                                                                line_pairs = []
                                                                                                                                                for count in range(0, len(alist)):
                                                                                                                                                            line = alist[count]
                                                                                                                                                                    curr_line_pairs = line_two_pairs(line)
                                                                                                                                                                            max_size = max(max_for_line(count,curr_line_pairs, line_pairs),
                                                                                                                                                                                                                  max_size)
                                                                                                                                                                                    line_pairs.append((count,curr_line_pairs))
                                                                                                                                                                                        return max_size

                                                                                                                                                                                    
