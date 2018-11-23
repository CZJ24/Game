/* Coda.java
 * Zijian Chen
 * Stan
 * zchen24
 * Section 4
 */
//to store imformation for each player
public class record
{
  private String name;
  private int win;//the number of win
  private int lose;//the number of lose
  private int tf;//used to if this player exist since I have arrar with 50 index
  
public record()//to give the original value
{
  win = 0;
  lose = 0;
  tf = 0;
}
  
public void setValue(String n,int w,int l,int t)//to set value for the name,win,lose, tf
{
  name = n;
  win = w;
  lose = l;
  tf = t;
}


public void setName(String n)//to set the name
{
  name = n;
}
public String getName()//to get the name
{
  return name;
}

public void setWin(int w)//to set the win
{
  win = w;
}
public int getWin()//to get the win
{
  return win;
}

public void setLose(int l)//to set the lose
{
  lose = l;
}
public int getLose()//to get the lose
{
  return lose;
}
public int getTf()//to get the tf
{
  return tf;
}
public void setTf(int t)//to set the tf
{
  tf = t;
}

}