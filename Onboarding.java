import java.util.*;
import java.io.*;
import java.math.*;

/**
 * The code below will read all the game information for you.
 * On each game turn, information will be available on the standard input, you will be sent:
 * -> the total number of visible enemies
 * -> for each enemy, its name and distance from you
 * The system will wait for you to write an enemy name on the standard output.
 * Once you have designated a target:
 * -> the cannon will shoot
 * -> the enemies will move
 * -> new info will be available for you to read on the standard input.
 **/
class Enemy{
    
    public int distance;
    public String name;
    public int speed;
    
    public Enemy(String name,int distance){
        this.distance = distance;
        this.name = name;
        speed = 0;
    }
    public void calculateSpeed(int newdistance){
        speed = distance - newdistance;
        distance = newdistance;
    }
    
    public int danger(){
        return 100 - distance + speed ;
    }
    
}
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        HashMap<String,Enemy> enemies = new HashMap<String,Enemy>();
        
        // game loop
        while (true) {
           
            int count = in.nextInt(); // The number of current enemy ships within range
            String target = "";
            int maxDanger = 0;
            ArrayList<String> names = new <String> ArrayList();
            
            for (int i = 0; i < count; i++) {
                String enemy = in.next(); // The name of this enemy
                int dist = in.nextInt(); // The distance to your cannon of this enemy
                names.add(enemy);
                if(enemies.containsKey(enemy)){
                    enemies.get(enemy).calculateSpeed(dist);
                }
                else{
                    enemies.put(enemy,new Enemy(enemy,dist));
                }
                
            }
            
            for (String name : names) {
                if(maxDanger < enemies.get(name).danger()){
                    target = name;
                    maxDanger = enemies.get(name).danger();
                }
            }
            
            System.out.println(target);
          
        }
    }
}
