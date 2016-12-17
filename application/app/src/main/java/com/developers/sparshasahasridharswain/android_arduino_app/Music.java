package com.developers.sparshasahasridharswain.android_arduino_app;

/**
 * Created by Sparsha Saha on 12/3/2016.
 */

public class Music {         //ID=1
    public boolean play;  //ID=1
    public boolean pause;  //ID=2
    public boolean next;  //ID=3
    public boolean previous;  //ID=4

    public String convert_to_String(int id)
    {
        String result_string="";
        if(id<=4 && id>=0)
            result_string="1"+id;
        return result_string;
    }

    public static Music convert_from_string(String k)
    {
        Music temp=new Music();
        int n=0;
        if(k.charAt(0)=='1')
        {
            n=Integer.parseInt(k.charAt(1)+"");
        }

       switch(n)
       {
           case 1:
               temp.play=true;
               break;
           case 2:
               temp.pause=true;
               break;
           case 3:
               temp.next=true;
               break;
           case 4:
               temp.previous=true;
               break;
       }

        return temp;
    }
}

class Call_Outgoing  //ID=2
{
    public String phone_number;

    public Call_Outgoing convert_from_string(String call)
    {
        Call_Outgoing c=new Call_Outgoing();
        if(call.charAt(0)=='2')
        {
            c.phone_number=call.substring(2);
        }

        return c;
    }

}



