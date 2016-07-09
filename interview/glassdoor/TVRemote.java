package com.glassdoor;

public class TVRemote {
	public void printPath(String str)
    {
        int start_row = 0;
        int start_col = 0;
        int diff = 0;
        int end_row, end_col;
        for(int i=0;i<str.length();i++)
        {
            if((str.charAt(i)>=65)&&(str.charAt(i)<=90))
                diff = str.charAt(i) - 'A';
            else
                diff = str.charAt(i) - 'a';

            end_row = diff/3;
            end_col = diff%3;

            if(end_row>start_row)
            {
                for(int j = 0;j<(end_row-start_row);j++)
                    System.out.println("Down");
            }
            else if(end_row<start_row)
            {
                for(int j = 0;j<(start_row-end_row);j++)
                    System.out.println("Up");
            }
            if(end_col>start_col)
            {
                for(int j = 0;j<(end_col-start_col);j++)
                    System.out.println("Right");
            }
            else if(end_col<start_col)
            {
                for(int j = 0;j<(start_col-end_col);j++)
                    System.out.println("Left");
            }
            System.out.println("Press");
            start_row = end_row;
            start_col = end_col;

        }
    }
	
	public static void main(String[] args) {
		TVRemote tr = new TVRemote();
		tr.printPath("feed");
	}

}
