using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ConsoleApplication1
{
    class Program
    {
        static void Main(string[] args)
        {
        }


    }

    class MergeSortArray
    {
        int [] _backingArray;


        public MergeSortArray(int size)
        {
            this._backingArray = new int[size];
        }

        

        MergeSortArray _left;
        MergeSortArray _right;

        public MergeSortArray Left
        {
            get
            {
                if(_left == null)
                {
                    _left = new MergeSortArray(LeftSize);
                }
                return _left;
            }
        }

        public MergeSortArray Right
        {
            get
            {
                if(_right == null)
                {
                    _right = new MergeSortArray(RightSize);
                }
                return _right;
            }
        }

        int BackingArraySize
        {
            get
            {
                return _backingArray.Length;                   
            }
        }

        int LeftSize
        {
            get
            {
                return BackingArraySize/ 2;
            }
        }

        int RightSize
        {
            get
            {
                return _backingArray.Length - LeftSize;
            }
        }

        int _currentIndex = 0;

        public bool HasNext
        {
            get
            {
                return _currentIndex < BackingArraySize;
            }
        }

        public int Peek
        {
            get
            {
                return this._backingArray[_currentIndex];
            }
        }

        public int Take
        {
            get
            {
                int headValue = Peek;
                _currentIndex++;
                return headValue;
            }
        }
    }
}
