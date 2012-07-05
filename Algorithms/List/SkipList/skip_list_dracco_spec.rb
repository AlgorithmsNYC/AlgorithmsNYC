require_relative 'skip_list_dracco'

describe LinkedList do
  describe "#tail" do
    context "when list is empty" do
      it "should be nil" do
        subject.tail.should be_nil
      end
    end
  end

  describe "#add" do
    it "should add the element at the end of the list" do
      list = LinkedList.new
      list.add(1)
      list.tail.value.should == 1
    end

    it "should add the two elements" do
      list = LinkedList.new
      list.add(1)
      list.add(2)
      list.tail.value.should == 2
      list.size.should == 2
    end
  end

  describe "#predecessor" do
    context "when list is empty" do
      it "should return nil" do
        list = LinkedList.new
        list.predecessor(1).should be_nil
      end
    end

    context "when list contains one element" do
      context "and the element is less then value in the list" do
        it "should retrun nil" do
          list = LinkedList.new
          list.add(2)
          list.predecessor(1).should be_nil
        end
      end

      context "and the element is equal then value in the list" do
        it "should retrun nil" do
          list = LinkedList.new
          list.add(2)
          list.predecessor(2).value.should == 2
        end
      end

      context "and the element is greater then value in the list" do
        it "should retrun nil" do
          list = LinkedList.new
          list.add(2)
          list.predecessor(3).value.should == 2
        end
      end
    end

    context "when list contains more than one element" do
      context "and elements are larger then the value" do
        it "should retrun nil" do
          list = LinkedList.new
          list.add(2)
          list.add(3)
          list.add(3)
          list.predecessor(1).should be_nil
        end
      end

      context "and elements are smaller then the value" do
        it "should retrun nil" do
          list = LinkedList.new
          list.add(2)
          list.add(3)
          list.add(3)
          list.predecessor(5).value.should == 3
        end
      end
    end
  end
end

describe "subject" do
  describe "#insert" do
    context "when the list is empty" do
      it "should insert the first element" do
        slist = SkipList.new
        node = slist.insert(1)
        slist.size.should == 1
        node.value.should == 1
        node.previous.should be_nil
        node.next.should be_nil
      end
    end

    context "when the list contains one smaller element" do
      it "should insert the last element" do
        slist = SkipList.new
        following = slist.insert(1)
        node = slist.insert(2)
        slist.size.should == 2
        node.value.should == 2
        node.previous.should == following
        node.next.should be_nil
      end
    end

    context "when the list contains one equal element" do
      it "should insert the first element" do
        slist = SkipList.new
        following = slist.insert(1)
        node = slist.insert(1)
        slist.size.should == 2
        node.value.should == 1
        node.previous.should == following
        node.next.should be_nil
      end
    end

    context "when the list contains one larger element" do
      it "should insert the first element" do
        slist = SkipList.new
        following = slist.insert(2)
        node = slist.insert(1)
        slist.size.should == 2
        node.value.should == 1
        node.previous.should be_nil
        node.next.should == following
      end
    end

    context "when the list contains two elements" do
      it "should do something" do
        slist = SkipList.new
        first = slist.insert(1)
        second = slist.insert(3)
        node = slist.insert(2)
        slist.size.should == 3
        node.value.should == 2
        node.previous.should == first
        node.next.should == second
      end
    end

    context "when adding multiple elements" do
      it "should insert them in order" do
        slist = SkipList.new
        slist.insert(5)
        slist.insert(3)
        slist.insert(2)
        slist.insert(4)
        node = slist.insert(1)
        node.next.value.should == 2
        node.next.next.value.should == 3
        node.next.next.next.value.should == 4
        node.next.next.next.next.value.should == 5
      end
    end

    context "when adding node divisible by 2" do
      it "should add the node the list above" do
        slist = SkipList.new
        node = slist.insert(2)

        above = node.up
        above.should_not be_nil
        above.down.should == node
      end
    end

    context "when adding node divisible by 4" do
      it "is should add the node to the 2 lists above" do
        slist = SkipList.new
        node = slist.insert(4)

        above = node.up
        above.should_not be_nil
        above.down.should == node

        above2 = above.up
        above2.should_not be_nil
        above2.down.should == above
      end
    end

    context "when adding multiple elements" do
      it "should insert them in order" do
        slist = SkipList.new
        slist.insert(5)
        slist.insert(3)
        slist.insert(2)
        slist.insert(4)
        node = slist.insert(1)
        node.next.value.should == 2
        node.next.next.value.should == 3
        node.next.next.next.value.should == 4
        node.next.next.next.next.value.should == 5
      end
    end
  end

  describe "#search" do
    context "when list is empty" do
      it "should return nil" do
        slist = SkipList.new
        slist.search(1).should be_nil
      end
    end

    context "when list is not empty" do
      it "should return the first element" do
        slist = SkipList.new
        slist.insert(1)
        slist.search(1).value.should == 1
      end

      it "should return the last element" do
        slist = SkipList.new
        slist.insert(1)
        slist.insert(2)
        slist.search(2).value.should == 2
      end
    end
  end

  describe "#path" do
    it "should do something" do
      slist = SkipList.new
      slist.insert(2)
      path = slist.path(1)
      path.should == [nil, nil, nil]
    end
  end

  describe "#search" do
    context "when list is empty" do
      it "should return nil" do
        slist = SkipList.new
        slist.search(1).should be_nil
      end
    end

    context "when list is not empty" do
      it "should return the first element" do
        slist = SkipList.new
        slist.insert(1)
        slist.search(1).value.should == 1
      end

      it "should return the last element" do
        slist = SkipList.new
        slist.insert(1)
        slist.insert(2)
        slist.search(2).value.should == 2
      end
    end
  end

  describe "#path" do
    it "should do something" do
      slist = SkipList.new
      slist.insert(2)
      path = slist.path(1)
      path.should == [nil, nil, nil]
    end

    it "should return the path that includes path" do
      slist = SkipList.new
      slist.insert(1)
      slist.insert(2)
      node = slist.insert(4)
      path = slist.path(4)
      path[2].up.should be_nil
      path[2].down.should_not be_nil
      path[1].up.should == path[2]
      path[1].down.should == node
      puts slist
    end
  end
end
