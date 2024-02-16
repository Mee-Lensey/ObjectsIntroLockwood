//Game Example
//Lockwood Version 2023-24
// Learning goals:
// make an object class to go with this main class
// the object class should have a printInfo()
//input picture for background
//input picture for object and paint the object on the screen at a given point
//create move method for the object, and use it
// create a wrapping move method and a bouncing move method
//create a second object class
//give objects rectangles
//start interactions/collisions

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Sections

public class GameLand implements Runnable {

    //Variable Declaration Section
    //Declare the variables used in the program


    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    //Declare the objects used in the program below
    /**STEP 1 Declare your object and give it a name**/
public Hero astro;
public Hero TimmyTurner;
public Hero Spongebob;
public Hero RileyFreeman;
public Hero Gru;
/**STEP 2 Declare an image for your object **/
 public Image astroPic;
 public Image backgroundpic;
 public Image TimmyTurnerPic;
 public Image Trees;


 public Image SpongebobPic;
 public Image RileyFreemanPic;
 public Image GruPic;

 public boolean TimmyturnerIsIntersectingRileyFreeman;




    // Main method definition: PSVM
    // This is the code that runs first and automatically
    public static void main(String[] args) {
       GameLand ex = new GameLand();   //creates a new instance of the game and tells GameLand() method to run
        new Thread(ex).start();       //creates a thread & starts up the code in the run( ) method
    }

    // Constructor Method
    // This has no return type and has the same name as the class
    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public GameLand() {
        setUpGraphics(); //this calls the setUpGraphics() method

        //create (construct) the objects needed for the game below
        //for each object that has a picture, load in images as well
        /**Construct a specific Hero**/
        astro= new Hero(400, 500,15,22);
        Spongebob= new Hero(300, 200, 6, 9);
       RileyFreeman= new Hero(600, 500, 6, 9);
       Gru=new Hero(300, 400, 5, 9);
       TimmyTurner=new Hero(900, 700, 15, 22);
        /**STEP 4 load in the image for your object **/


        GruPic=Toolkit.getDefaultToolkit().getImage("Gru.png");
        astroPic = Toolkit.getDefaultToolkit().getImage("astronaut.png");
        TimmyTurnerPic = Toolkit.getDefaultToolkit().getImage("TimmyTurner.png");
        SpongebobPic= Toolkit.getDefaultToolkit().getImage("Spongebob.png");
        RileyFreemanPic= Toolkit.getDefaultToolkit().getImage("RileyFreeman.png");
        Trees=Toolkit.getDefaultToolkit().getImage("Trees.png");


        astro.printInfo();
        Gru.printInfo();


    }// GameLand()

//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever using a while loop
        while (true) {
            moveThings();  //move all the game objects
            collisions(); //checks for rec intersections
            render();  // paint the graphics
            pause(20); // sleep for 20 ms
        }
    }

    //paints things on the screen using bufferStrategy
    public void collisions(){
        if(Gru.rec.intersects(Spongebob.rec) && TimmyturnerIsIntersectingRileyFreeman==false ){
           TimmyturnerIsIntersectingRileyFreeman=true;



            System.out.println("ouch");




        }




    }
    public void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //draw the image of your objects below:

        /**Step 5 draw the image of your object to the screen**/
        g.drawImage(Trees,0, 0, 1000, 700, null);
        g.drawImage(GruPic,Gru.xpos,Gru.ypos, Gru.width, Gru.height, null);
        g.drawImage(TimmyTurnerPic, astro.xpos, astro.ypos,astro.width,astro.height, null);
        g.drawImage(SpongebobPic, astro.xpos, Spongebob.ypos,Spongebob.width,Spongebob.height, null);
        g.drawImage(RileyFreemanPic, RileyFreeman.xpos, RileyFreeman.ypos,RileyFreeman.width,RileyFreeman.height, null);
        //dispose the images each time(this allows for the illusion of movement).
        g.dispose();

        bufferStrategy.show();
    }

    public void moveThings() {
        //call the move() method code from your object class
        astro.bouncingMove();

        RileyFreeman.bouncingMove();
        Spongebob.teleport();



        Gru.bouncingMove();
        Gru.growingMove(3);


    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Game Land");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");
    }



}
