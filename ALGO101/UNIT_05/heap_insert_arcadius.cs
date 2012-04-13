using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace HeapImplement
{
    public partial class Form1 : Form
    {
        //init
        int[] a = { 4, 5, 8, 11, 20, 0 };


        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

            displayArray();

            // insert 1
            a[a.Length-1] = 1;
            displayArray();

            FindParentOfThisPositionAndBubbleUp(a.Length - 1);
            


        }

        public void FindParentOfThisPositionAndBubbleUp(int passedVariablePosition)
        {
            //bubble up
            //calculate parent of 1ariable
            int ParentPosition = (passedVariablePosition-1) / 2;
            //textBox1.Text = ParentPosition.ToString() + " value: " + a[ParentPosition];

            // compare to find out if violates heap property
            if (a[ParentPosition] > a[passedVariablePosition])
            {
                //violates so we swap
                int tmp = a[passedVariablePosition];
                a[passedVariablePosition] = a[ParentPosition];
                a[ParentPosition] = tmp;
               // displayArray();
            }
            else
            {
                //doesn't violate
            }

            if (ParentPosition == 0)
            {
                displayArray();
            }
            else
            {
            FindParentOfThisPositionAndBubbleUp(ParentPosition);
            }
        }


        public void displayArray()
        {
            for( int i=0; i<a.Length; i++)
            {
                textBox1.Text += a[i].ToString() + ",";
            }
            textBox1.Text += Environment.NewLine;
        }



    }
}
