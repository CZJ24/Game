/* Coda.java
 * Zijian Chen
 * Stan
 * zchen24
 * Section 4
 */
import java.util.Scanner;
import java.io.*;
public class instruct 
{
  private String str;
  public String forString() throws IOException//used to print out the instruction
  {
    //I!!!!!!!*********************************
    Scanner filescan=new Scanner(new File("instruction.txt"));//read the file instruction.txt
    String str="";
    while(filescan.hasNext())
    {
      str+=filescan.nextLine();
      str+=" \n ";
    }
    filescan.close();
    return str;  
  }
  //to ouput each kind of card
  public void pictureCard(int a,char b)
  {
    switch(a)
    {
      case 1:
      {if(b=='b')
        System.out.print(" *1*");
      else if(b=='w')
        System.out.print(" |1|");
      break;
      }
      case 2:
      {if(b=='b')
        System.out.print(" *2*");
      else if(b=='w')
        System.out.print(" |2|");
      break;
      }
      case 3:
      {if(b=='b')
        System.out.print(" *3*");
      else if(b=='w')
        System.out.print(" |3|");
      break;
      }
      case 4:
      {if(b=='b')
        System.out.print(" *4*");
      else if(b=='w')
        System.out.print(" |4|");
      break;
      }
      case 5:
      {if(b=='b')
        System.out.print(" *5*");
      else if(b=='w')
        System.out.print(" |5|");
      break;
      }
      case 6:
      {if(b=='b')
        System.out.print(" *6*");
      else if(b=='w')
        System.out.print(" |6|");
      break;
      }
      case 7:
      {if(b=='b')
        System.out.print(" *7*");
      else if(b=='w')
        System.out.print(" |7|");
      break;
      }
      case 8:
      {if(b=='b')
        System.out.print(" *8*");
      else if(b=='w')
        System.out.print(" |8|");
      break;
      }
      case 9:
      {if(b=='b')
        System.out.print(" *9*");
      else if(b=='w')
        System.out.print(" |9|");
      break;
      }
      case 10:
      {if(b=='b')
        System.out.print(" *10*");
      else if(b=='w')
        System.out.println(" |10|");
      break;
      }
      case 11:
      {if(b=='b')
        System.out.print(" *11*");
      else if(b=='w')
        System.out.print(" |11|");
      break;
      }
      case 12:
      {if(b=='b')
        System.out.print(" *12*");
      else if(b=='w')
        System.out.print(" |12|");
      break;
      }
    }
  }
  //to output the face down card
   public void facedownCard(char bw)
   {
     if(bw=='b')
       System.out.print(" * *");
     else if(bw=='w')
       System.out.print(" | |");
   }
}
        

   