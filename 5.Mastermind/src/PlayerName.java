/*******************************************************************************
 *
 * Group Project
 *
 * Name ordered by the initial of the last names
 *
 * Code by Tengchao Zhou
 * 
 * V22.0101-003
 * 
 * Course: Introduction to Computer Science I (JAVA) 
 * Professor: Sana' Odeh
 *
 * December 16, 2009
 * 
 * Master Mind
 * 
 *******************************************************************************/
public class PlayerName implements Comparable<PlayerName> {

    	int score;
        String username;
        String firstname;
        String lastname ;
        String indexname;



    public PlayerName(int s, String u, String f, String l, int i) {
        score = s;
        username = u;
        firstname = f;
        lastname = l;
        switch(i){
        case 1: indexname = u;break;
        case 2: indexname = f;break;
        case 3: indexname = l;break;
        default: indexname = u;break;
        }
    }

    public String toString() {
        return firstname +"  "+ lastname +"  "+ username+"  " + score;
    }
    public int compareTo(PlayerName o1) {
		return this.indexname.compareToIgnoreCase(((PlayerName) o1).indexname);
	}
}