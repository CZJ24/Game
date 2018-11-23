/* Coda.java
 * Zijian Chen
 * Stan
 * zchen24
 * Section 4
 */
//used to give each card number, color and face up or down
public class Card
{
  private int number;
  private char color;
  private boolean face;

public Card()
{
  number = 100;
  face = false;
}

public Card(int n,char c,boolean f)//to store the number, color and face up or down
{
  number = n;
  color = c;
  face = f;
}
public void setNumber(int n)//to set the number
{
  number = n;
}
public int getNumber()//to get the number
{
  return number;
}
public void setColor(char c)//to set the color
{
  color = c;
}
public char getColor()//to get the color
{
  return color;
}
public void setFace(boolean f)//to set the face up or down
{
  face = f;
}
public boolean getFace()//to get the face
{
  return face;
}
public int compareTo(Card c)//used to compare two cards , and if two cards have same number, the black card should be smaller
{
  if (this.getNumber()<c.getNumber())
    {return -1;}
    else if(this.getNumber()==c.getNumber())
    {
      if(c.getColor()=='w')
      {return -1;}
    }
      return 0;
}
}