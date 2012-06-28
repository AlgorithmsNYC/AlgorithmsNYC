class Node
  attr_accessor :value, :left, :right

  def initialize(args = {})
    self.value = args[:value]
    self.left = args[:left]
    self.right = args[:right]
  end

  def to_s
    "Node value: #{value}\n  left: #{left}\n  right: #{right}"
  end
end

n2 = Node.new(value: 2)
n5 = Node.new(value: 5)
n5a= Node.new(value: 5, left: n2, right: n5)

n8 = Node.new(value: 8)
n7 = Node.new(value: 7, right: n8)

n6 = Node.new(value: 6, left: n5a, right: n7)


#puts n6

def in_order_tree_walk(node)
  if not node.nil?
    in_tree_walk(node.left)
    puts node.value
    in_tree_walk(node.right)
  end
end

#in_order_tree_walk(n6)

def prefix_tree_walk(node)
  if not node.nil?
    puts node.value
    prefix_tree_walk(node.left)
    prefix_tree_walk(node.right)
  end
end

#prefix_tree_walk(n6)

def postfix_tree_walk(node)
  if not node.nil?
    puts node.value
    postfix_tree_walk(node.left)
    postfix_tree_walk(node.right)
  end
end

#postfix_tree_walk(n6)

def tree_search(node, value)
  if node.nil? or node.value == value
    return node
  else
    if value < node.value
      return tree_search(node.left, value)
    else
      return tree_search(node.right, value)
    end
  end
end

#puts tree_search(n6, 1)

def insert(root, node)
  y = nil
  x = root
  while x != nil
    y = x
    if node.value < x.value
      x = x.left
    else
      x = x.right
    end
  end

  if y == nil
    return node
  elsif node.value < y.value
    y.left = node
  else
    y.right = node
  end
  return root
end

puts insert(n6, Node.new(value: 1))

