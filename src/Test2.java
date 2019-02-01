import java.util.*;
import java.io.*;
import java.text.*;
import java.nio.*;

public class Test2
{
    public static void main(String args[])throws IOException
    {
	Scanner sc = new Scanner(System.in);
	System.out.print("Enter file name to open: ");
 	String inFile = sc.nextLine();
 	System.out.print("Enter file name to write to: ");
 	String outFile = sc.nextLine();

        DataInputStream din = new DataInputStream(new FileInputStream(inFile));
        DataOutputStream dout = new DataOutputStream(new FileOutputStream(outFile));
	boolean eof = false;
        while(!eof) 
        {
            try
            {
                int num = din.readInt();
                String pad = "00000000000000000000000000000000";
                String bin = Integer.toBinaryString(num);
                String res = pad + bin;
                res = res.substring(res.length()-32, res.length());
                char[] ch = res.toCharArray();

                String lowBits = "";
                int lowInt = 0;
                String highBits = "";
                int highInt = 0;
                String output = "";

                for(int i= 16; i < ch.length; i++)
                {
                        lowBits += ch[i];
                }
                lowInt = Integer.parseInt(lowBits, 2);

                for(int j= 10; j < 16; j++)
                {
                        highBits += ch[j];
                }
                highInt = Integer.parseInt(highBits, 2);
                System.out.printf("Bin string:%s \tHex:%08x \tBits 21-16: %d \tBits 15-0: %d\n", res,num, highInt, lowInt);
                output += "Bin string:"+res+"\tHex:"+num+" \tBits 21-16: "+highInt+" \tBits 15-0: "+lowInt+"\n";
                dout.writeBytes(output);
            }
            catch (EOFException e)
            {
                eof = true;
            }
        }
        din.close();
        dout.close();
    }
}