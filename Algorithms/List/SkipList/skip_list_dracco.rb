class Node
  attr_accessor :value, :previous, :up, :down, :next

  def initialize(options = {})
    @value = options[:value]
    @previous = options[:previous]
    @up = options[:up]
    @next = options[:next]
  end
end

class LinkedList
  def initialize
    @head = nil
    @size = 0
  end

  def add(x)
    if empty?
      @head = Node.new(:value => x)
    else
      tail.next = Node.new(:value => x)
    end
    @size += 1
  end

  def insert(x, predecessor = nil)
    predecessor ||= predecessor(x)
    @size += 1
    if predecessor.nil?
      if empty?
        node = Node.new(:value => x)
        @head = node
      else
        node = Node.new(:value => x, :next => @head)
        @head.previous = node
        @head = node
      end
    else
        node = Node.new(:value => x, :previous => predecessor, :next => predecessor.next)
        predecessor.next = node
    end
    node
  end

  def empty?
    @head.nil?
  end

  def head
    @head
  end

  def tail
    cursor = @head
    if not cursor.nil?
      cursor = cursor.next while not cursor.next.nil?
    end
    cursor
  end

  def predecessor(x, start = nil)
    cursor = start
    if empty?
      nil
    else
      cursor = head
      if cursor.value > x
        return nil
      else
        while cursor.next != nil && cursor.next.value <= x
          cursor = cursor.next
        end
        cursor
      end
    end
  end

  def size
    @size
  end

  def to_s
    cur = @head
    s = ""
    until cur.nil?
      s += "," + cur.value.to_s
      cur = cur.next
    end
    s
  end
end

class SkipList
  def initialize
    @lists = [LinkedList.new, LinkedList.new, LinkedList.new]
  end

  def path(x)
    p2 = @lists[2].predecessor(x)
    p1 = @lists[1].predecessor(x, p2)
    p0 = @lists[0].predecessor(x, p1)
    [p0, p1, p2]
  end

  def insert(x)
    n = 0
    path = path(x)
    node = @lists[0].insert(x, path[0])
    if x % 2 == 0
      node1 = @lists[1].insert(x, path[1])
      node.up = node1
      node1.down = node
      if x % 4 == 0
        node2 = @lists[2].insert(x, path[2])
        node1.up = node2
        node2.down = node1
      end
    end
    node
  end

  def size
    @lists[0].size
  end

  def search(x)
    predecessor = path(x)[0]
    if predecessor.nil?
      nil
    else
      if not predecessor.nil? && predecessor.value == x
        predecessor
      else
        nil
      end
    end
  end

  def to_s
    "#{@lists[2].to_s}\n#{@lists[1].to_s}\n#{@lists[0].to_s}"
  end
end

