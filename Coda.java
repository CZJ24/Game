/* Coda.java
 * Zijian Chen
 * Stan
 * zchen24
 * Section 4
 */
import java.util.Random;
import java.util.Scanner;
import java.io.*;
import javax.swing.JOptionPane;

public class Coda
{
  public static void main(String[] args) throws IOException
  {
    //AR!!!!!!!!!***********************
   Card[] deck = new Card[24];//to declare a array for the whole deck
   Card[] humanHand = new Card[12];//to declare a array for human's cards
   Card[] computerHand = new Card[12];//to declare a array for computer's cards
   setValue(deck,humanHand,computerHand);//to give 3 arrays:deck, humanHand, computerHand,original values.
   
   record[] r = new record[50];//to declare a array to record each player.
   
   int indexR = 0;//the index of the array r to record each player
   int tfOutput;//to identify if the player played this game before.
   indexR = outputTxt(r,indexR);//call this method to read the file Record.txt
   
   PrintWriter outputFile = new PrintWriter("Record.txt");//to write to the file Record.txt
   outputFile.printf("%-15s%15s%15s","Name","Win","Lose");//write the name, win and lose at the top for the file Record.txt
   outputFile.println();
   
    String playerName;//player's name
    String input;//used for asking if the player wants a instruction
    int indexPlayer = 0;//used for the index of array-humanHand
    int indexComputer = 0;//used for the index of array-computerHand
    int search=0;//to identify if the all the cards of human or the computer face up
    boolean win = false;//for the while loop, if some cards of human or the computer still face down,contine to play the game
    instruct output = new instruct();//to declare a object
    
         
    JOptionPane.showMessageDialog( null,"Welcome to Coda!");
    playerName=JOptionPane.showInputDialog("Please enter your first name:");//to let player enter the name
    tfOutput = searchPlayer(r,playerName);//to search if the player played this game before.
    input=JOptionPane.showInputDialog("Do you want a instruction? (Yes: 1  No: 2)");//to ask if the player wants a instruction
    int instruction=Integer.parseInt(input);
    if(instruction==1)//if the player wants a instruction, it will print out the instruction from the instruct class
     JOptionPane.showMessageDialog( null,output.forString()); //use the toString method in the instruct class
    
    indexPlayer = beginHuman(humanHand,deck,indexPlayer); //to give the human 3 random cards form the deck at the begining
    indexComputer = beginComputer(computerHand,deck,indexComputer);//to give the computer 3 random cards form the deck at the begining
    //BOOL!!!!!!*****************************************
    while(win==false)//let the game can continue until all the cards of human or the computer face up
  {
  indexPlayer = playerGuess(deck,humanHand,computerHand,indexPlayer);//for human's turn, give human a random new card
  search=0;
  search = searching(computerHand,false,sortMore(computerHand));//to search if the all the cards of computer face up
  if(search==-1)//search=-1 means all the cards of computer face up 
  {
    if(tfOutput==-1)//tfOutput=-1 means this player never played this game before and give the player a new index to print out for the file Record.txt
      r[indexR].setValue(playerName,1,0,1);//give the player a new index to print out for the file Record.txt
    else//if the player played this game before, give the number of win plus 1
      r[tfOutput].setWin(r[tfOutput].getWin()+1);
    win = true;//to let the while loop stop
    System.out.println("You win! Congratulations!");
  }
  else// if human does not win this turn, let computer play his turn
  {
  indexComputer = computerGuess(deck,humanHand,computerHand,indexComputer);//for computer's turn, give computer a random new card
  search=0;
  search = searching(humanHand,false,sortMore(humanHand));//to search if the all the cards of human face up
  if(search==-1)//search=-1 means all the cards of human face up 
  {
    if(tfOutput==-1)//tfOutput=-1 means this player never played this game before and give the player a new index to print out for the file Record.txt
      r[indexR].setValue(playerName,0,1,1);//give the player a new index to print out for the file Record.txt
    else//if the player played this game before, give the number of lose plus 1
      r[tfOutput].setWin(r[tfOutput].getWin()+1);
    win = true;//to let the while loop stop
    System.out.println("You lose.");
  }
  }
  }
  for(int i=0;i<50;i++)//to print out each of the player's name and number of win and lose to the file Record.txt
  {
    //O!!!!!!**********************************
    if(r[i].getTf()==1)
      outputFile.printf("%-15s%15d%15d",r[i].getName(),r[i].getWin(),r[i].getLose());
    outputFile.println();
  }
  outputFile.close();
}
  /*****************************************************************************
   *****************************************************************************
   *****************************************************************************/ 
  //MYMETH!!!!!!!!!!!!!***************
  //MYMETH(O)!!!!!!!!!!!!!****************
  public static int searchPlayer(record[] R,String name)//to search if the player played this game before
  {
    for(int i=0;i<50;i++)
    {
      if(R[i].getTf()==1)
        {
        if(name.compareTo(R[i].getName())==0)//if the player played this game before, print out a message to welcome the player play again
      {
          JOptionPane.showMessageDialog( null,name + "! Thank you for playing again!");       
        return i;//if the player played this game before,return the index of his name in the array-r
             
      }
      }
    }
    return -1;//if the player never played this game before,return -1
  }
  /*****************************************************************************
   *****************************************************************************
   *****************************************************************************/  
  public static int outputTxt(record[] R,int index) throws IOException//to read the file Record.txt
  {
    Scanner filescan=new Scanner(new File("Record.txt"));
    String str;//to read the first line of the file Record.txt
   String name;//to read the player's name for each line
   int win;// to read the number of win for each line
   int lose;//to read the number of lose for each line
  
   for(int i=0;i<50;i++)
   {
     R[i] = new record();
   }
   str = filescan.nextLine();//to read the first line of the file Record.txt
   while(filescan.hasNext())
    {
     name = filescan.next();//to read the player's name for each line
     win = filescan.nextInt();// to read the number of win for each line
     lose = filescan.nextInt();//to read the number of lose for each line
     R[index].setValue(name,win,lose,1);// to set value for the array-r
     index++;
   }
   filescan.close();
   return index;
  }
  
   /*****************************************************************************
   *****************************************************************************
   *****************************************************************************/  
  public static int beginComputer(Card[] Handc,Card[] Deck,int index)//to give the compuer 3 random cards at the beginning
  {
    //to give the computer 3 random cards
    for(int n=0;n<3;n++)
    {
      
      index = getCard(Deck,Handc,index);
      }
    
    //to make the computer's cards in order
    select_sort(Handc,sortMore(Handc));
  //to print out the computer's cards
   System.out.println("The computer's code:");
   showcard2(Handc);
   System.out.println();
   return index;//return the index of the array-computerHand
  }
   /*****************************************************************************
   *****************************************************************************
   *****************************************************************************/  
  public static int beginHuman(Card[] Handh, Card[] Deck,int index)//to give the human 3 random cards at the beginning
  {
    System.out.println("###########################################");
    System.out.println("Let's play the game!");
    
    //to give the player 3 random cards
    for(int n=0;n<3;n++)
    {
      index = getCard(Deck,Handh,index); 
      }
    
    //to make the player's cards in order
     select_sort(Handh,sortMore(Handh));
    
    System.out.println("Now your code:");
    //to show player's card
    showcard1(Handh);
    System.out.println();

    System.out.println("The computer will see:");
    //to show what the computer will see
    showcard2(Handh);
    System.out.println();
    return index;//return the index of the array-humanHand
  }
  /*****************************************************************************
   *****************************************************************************
   *****************************************************************************/  
  public static void setValue(Card[] Deck, Card[] Handh,Card[] Handc)//to set the original value for the array: deck,humanHand, compuerHand
  {
    for(int i=0;i<12;i++)
   {
     Deck[i] = new Card(i+1,'b',false);
   }
   for(int i=12;i<24;i++)
   {
     Deck[i] = new Card(i-11,'w',false);
   }
    for(int i=0;i<12;i++)
   {
     Handh[i] = new Card();
   }
    for(int i=0;i<12;i++)
   {
     Handc[i] = new Card();
   }    
  }
  
  /*****************************************************************************
   *****************************************************************************
   *****************************************************************************/  
  //SORT!!!!!!**************************************
   public static void select_sort (Card[] Hand,int length)// make list to be sorted 
   {         
    int min;   //index of smallest element of sublist
  
    Card temp;//for swapping 
    for (int startIndex=0; startIndex <length; startIndex++)
    {
      min=startIndex;
      
      for (int i=startIndex+1; i<length; i++)
      { 
        if (Hand[i].compareTo(Hand[min])<0)
      {min=i;}
     
      }
      //swap the values 
      temp = Hand[min];
      Hand[min] = Hand[startIndex];
      Hand[startIndex] = temp;          
    }//end for startIndex    
}
    /*****************************************************************************
   *****************************************************************************
   *****************************************************************************/
   public static int sortMore(Card[] Hand)//to search the length of index has the value we need in a array
   {
     int findLength=0;
     for(int i=0;i<Hand.length;i++)
     {
       if(Hand[i].getNumber()==100)         
          return i;
       findLength = i;
     }
     return findLength;
   }
   /*****************************************************************************
   *****************************************************************************
   *****************************************************************************/
   public static int getCard(Card[] Deck,Card[] Hand,int index)//get a random new card from the deck
   {
      Random ran = new Random();//to get a random card
     
    int num;//to get a random number from the deck
    for(int i=0;i<1;i++)
    {
    
      num = ran.nextInt(24);
     
        if(Deck[num].getNumber()==100)//to identify if this card has been picked, if the card has been picked, it will get random card again
          i--;
        else
        { //to give the value of the card to the human's or compuer's array     
          Hand[index].setNumber(Deck[num].getNumber());
          Hand[index].setColor(Deck[num].getColor());
         Deck[num].setNumber(100);  
          index++;
        }
      }
     return index;
   }
   /*****************************************************************************
   *****************************************************************************
   *****************************************************************************/
   public static void showcard1(Card[] Hand)//to print out the human's cards what human can see
   {
     instruct listOutput = new instruct();
     for(int i=0;i<Hand.length;i++)
    {
      if(Hand[i].getNumber()!=100)
      { listOutput.pictureCard(Hand[i].getNumber(),Hand[i].getColor());
        }
      
    }
   }
   /*****************************************************************************
   *****************************************************************************
   *****************************************************************************/
   public static void showcard2(Card[] Hand)//to print out card that others can see
   {
     instruct listOutput = new instruct();
     for(int i=0;i<Hand.length;i++)
    { 
      if(Hand[i].getNumber()!=100)
      {
       if(Hand[i].getFace()==false)
         listOutput.facedownCard(Hand[i].getColor());
       else if(Hand[i].getFace()==true)
         listOutput.pictureCard(Hand[i].getNumber(),Hand[i].getColor());
      }
    }                        
   }
   /*****************************************************************************
   *****************************************************************************
   *****************************************************************************/
   public static int playerGuess(Card[] Deck,Card[] Handh,Card[] Handc,int index)//let the player guess the computer's card
   {
     boolean tf=true;//if the player guess the right card and wants to guess again,let the player guess again.
     instruct listoutput = new instruct();
    int guessPlace = 0;//used to get which card the player wants to guess
    int guessNumber = 0;//used to get the number the player guessed for the card
    int guessAgain = 0;//to identify if the player wants to guess again if the player guess the right card
    int search=0;
      System.out.println("###########################################");
  System.out.println("Your turn!");  
  Scanner scan = new Scanner(System.in); 
  //give player a new card
  System.out.println("You get a new card:");
  index = getCard(Deck,Handh,index); //use the method to get a new card
  //output the card
   listoutput.pictureCard(Handh[index-1].getNumber(),Handh[index-1].getColor());
   System.out.println();
    while(tf==true)//to let the human guess the card until human guess the wrong card or does not want to guess again
    {
  System.out.println("You guess:(In the form of x y, for example, if I guess the second card is 8, I will enter 2 8)");
  guessPlace = scan.nextInt();
  guessNumber = scan.nextInt();
  scan.nextLine();
  if(Handc[guessPlace-1].getNumber()==guessNumber)//if guess the right card
  {
    Handc[guessPlace-1].setFace(true);//make this computer's card face up
     System.out.println("It is correct! This card will reveal!");     
    System.out.println("Now the computer's code:");
     showcard2(Handc);//to print out computer's card
   System.out.println();
   search = searching(Handc,false,sortMore(Handc));
   if(search==-1)//if all of the computer's cards face up, skip the guess again step
     tf=false;
   else//to ask if the human wants to guess again
   {
    System.out.println("Do you want to guess again?(Yes: 1  No: 2)");
    guessAgain = scan.nextInt();
    if(guessAgain==2)//if the player does not want to play again
    {
      tf=false;//to make the while loop stop
      //to make the player's cards in order
   select_sort(Handh,sortMore(Handh));
      System.out.println("Now your code:");
    //to show player's card
     showcard1(Handh);
    System.out.println();
    System.out.println("The computer will see:");
    //to show what the computer will see
    showcard2(Handh);
    System.out.println();
    }
   }
  }
  else//if guess the wrong card
  {    
    Handh[index-1].setFace(true);////make this computer's new card face up
    System.out.println("Ops! It's incorrect, your card will reveal");
    //to make the player's cards in order
   select_sort(Handh,sortMore(Handh));
     System.out.println("Now your code:");
    //to show player's card
     showcard1(Handh);
    System.out.println();
    System.out.println("The computer will see:");
    //to show what the computer will see
    showcard2(Handh);
    System.out.println();
    tf=false;//to make the while loop stop
   }   
  }          
  return index;//return the index of the array
   }
    /*****************************************************************************
   *****************************************************************************
   *****************************************************************************/
   //SEARCH!!!!!!!!!!!!!!!!!!!**************************
   public static int searching(Card[] Hand,boolean target,int length)//to search if all the cards of human or computer face up
   {    
    for(int i=0; i<length; i++)
    {
      if(Hand[i].getFace()==target)
      return i;    
    }
   return -1;
   }
   /*****************************************************************************
   *****************************************************************************
   *****************************************************************************/
   public static int computerGuess(Card[] Deck,Card[] Handh,Card[] Handc,int index)//let the computer guess the computer's card
   {
     int[] Avoid = new int[12];//to store the card that computer should not pick
     Random ran = new Random();
     boolean tf=true;
     boolean tf2=true;
     instruct listoutput = new instruct();    
     int avoidLength;     
    int guessPlace = 0;//used to get which card the computer wants to guess
    int guessNumber = 0;//used to get the number the computer guessed for the card
    int guessAgain = 0;//to identify if the computer wants to guess again if the computer guess the right card
      System.out.println("###########################################");
  System.out.println("Computer's turn!");  
  //give player a new card
  System.out.println("The computer get a new card:");
  index = getCard(Deck,Handc,index);
  //output the card
   listoutput.facedownCard(Handc[index-1].getColor());
   System.out.println();
    while(tf==true)//to let the human guess the card until human guess the wrong card or does not want to guess again
    {
     avoidLength = avoidNumber(Handh,Handc,Avoid,sortMore(Handh),sortMore(Handc));//to store the card that computer should not pick and get the length of array: Avoid that has the value we need
     tf2=true;
     while(tf2==true)//to let the computer guess until avoid the number the computer should not guess
     {
       //RANDOM!!!!!!!!!!!!*************
      guessPlace = ran.nextInt(sortMore(Handh))+1;
      guessNumber = ran.nextInt(12)+1;
      tf2 = search(avoidLength,guessNumber,Avoid);
      if(Handh[guessPlace-1].getFace()==true)
        tf2=true;
     }
  if(Handh[guessPlace-1].getNumber()==guessNumber)//if guess the right card
  {
    Handh[guessPlace-1].setFace(true);//make human's card computer guessed face up
     System.out.println("The computer guess: " + guessPlace + " " + guessNumber + " ,it is correct! This card will reveal");         
    System.out.println("Now your code:");
    //to show player's card
     showcard1(Handh);
    System.out.println();
    System.out.println("The computer will see:");
    //to show what the computer will see
    showcard2(Handh);
    System.out.println();
    guessAgain = ran.nextInt(2)+1;//to decide if the computer wants to guess again
    if(guessAgain==2)//if the computer does not want to guess again
    {
      tf=false;
      select_sort(Handc,sortMore(Handc));
      System.out.println("Computer don't want to guess again.");
      System.out.println("The computer's code:");
      //to show computer's card
      showcard2(Handc);
      System.out.println();
    }
    else if(guessAgain==1)//if the computer wants to guess again
    {
      System.out.println("The computer wants to guess again.");
    } 
  }
  else//if guess the wrong card
  {
    Handc[index-1].setFace(true);//make the computer's new card face up
    System.out.println("The computer guess: " + guessPlace + " " + guessNumber + " ,it is incorrect! The computer's card will reveal");
    select_sort(Handc,sortMore(Handc));      
      System.out.println("The computer's code:");
      //to show computer's card
      showcard2(Handc);
      System.out.println();
    tf=false;
   }   
  }          
  return index;
   }
  /*****************************************************************************
   *****************************************************************************
   *****************************************************************************/
   public static int avoidNumber(Card[] handh,Card[] handc,int[] avoid,int lengthHuman,int lengthComputer)//to store the number computer should not guess in a array
   { 
     int n=0;
     for(int start=0;start<lengthHuman;start++)
     {
       for(int i=0;i<lengthComputer;i++)//for the condition that both computer and human have the card with same number but different color
       {
         if(handh[start].getNumber()==handc[i].getNumber())
         {
           avoid[n]=handh[start].getNumber();
           n++;
         }
       }
       for(int x=start+1;x<lengthHuman;x++)//for the condition that player itself has two cards with same number but different color
     {
       if(handh[start].getNumber()==handh[x].getNumber())
       {
         avoid[n]=handh[start].getNumber();
           n++;
         } 
       }
     }
     for(int i=0;i<lengthComputer;i++)//for the condition that computer itself has two cards with same number but different color
     {
       for(int x=i+1;x<lengthComputer;x++)
       {
         if(handh[i].getNumber()==handh[x].getNumber())
       {
         avoid[n]=handh[i].getNumber();
           n++;
         } 
       }
     }
     return n;
   }
   /*****************************************************************************
   *****************************************************************************
   *****************************************************************************/
   public static boolean search(int length,int number,int[] avoid)//to search if the computer should guess the number
   {
     for(int i=0;i<length;i++)
      {
        if(avoid[i]==number)
          return true;
      }
         return false;
   }
}