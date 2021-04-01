public class Room {
    public int x, y;
    public int value = 0;//0 is uncreated, 1 is normal room, 2 is spawn room, 3 is next-level room.
    public final Path[] path = new Path[4];

    /*
       path[0] is path on the right.
       path[1] is path on the left.
       path[2] is path in the bottom.
        */
    public Room(){
        for(int i = 0 ; i < path.length ; i ++){
            path[i] = new Path();
        }
    }
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
