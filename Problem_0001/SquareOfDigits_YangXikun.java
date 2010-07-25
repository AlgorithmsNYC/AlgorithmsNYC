class SquareOfDigits_YangXikun {
   
    public int getMax(String[]data){
	int result =0;
	int rowNumber = data.length;
	int colNumber = data[0].length();
   
	int max = 0; // the maximum possible number of the square side.
	if (rowNumber > colNumber)
	    max = colNumber;
	else
	    max = rowNumber;
   
	for (int i=0; i< rowNumber; i++)
	    for (int j=0; j< colNumber; j++)
		for (int k = colNumber-1; k > j; k--)
		    if (data[i].charAt(j)==data[i].charAt(k)){
			if ((k-j+1)>max||(k-j+1+i)>rowNumber)
			    continue;
			else if (data[i+k-j].charAt(j)==data[i+k-j].charAt(k)&&data[i+k-j].charAt(j)==data[i].charAt(j) )
			    {
				if ((k-j+1)>result)
				    result = k-j+1;
			    }
		    }

	return result*result;
   
    }    