/*******************************
* File: CS445Project2.java
* Author: Alfredo Ceballos
* Class: CS 445 - Computer Graphics
* 
* Assignment: Project 2
* Date last modified: 11//17
*
* Purpose: This program will load information from a text file
* which specifies a polygon along with transformation to be made 
* to that polygon before it is filled
*******************************/
package cs445.project2;

/**
 *
 * @author Alfredo
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.lwjgl.LWJGLException;
import static org.lwjgl.opengl.GL11.*;
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import java.util.ArrayList;

public class CS445Project2 {

    // Setting up width, height, and 
    public static final int DISPLAY_HEIGHT = 480;
    public static final int DISPLAY_WIDTH = 640;
    private static String[] fileData;
    private static ArrayList<Polygon> polygons;
    
    
    // Method: createWindow
    // Purpose: Method in charge of creating window
    private void createWindow() throws Exception{
        try {
            Display.setFullscreen(false);
            Display.setDisplayMode(new DisplayMode(DISPLAY_WIDTH, DISPLAY_HEIGHT));
            Display.setTitle("CS445 Project 1");
            Display.create();
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
     } // End of createWindow

	 
    // Method: initGL()
    // Purpose: Method in charge of initiatin
    private void initGL() {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        glOrtho(0, 640, 0, 480, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
    } // End of initGL

    
    // Method: start
    // Purpose: Thread that handles running all other methods
    public void start() {
        try {
            createWindow();
            initGL();
            render();
        } catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }
        Display.destroy();
    } // End of start
    
    
    // Method: render
    // Purpose: Renders out background, reads info from file, and draws
    // out appropriate shapes and colors
    private void render(){
        while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            // Clear screen and depth buffers
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        
            // Setting of background
            glColor3f(0.0f,0.0f,0.0f);
            
        }
    }
    
    
    // Method:
    // Purpose:
    public static void main(String[] args){
        try{
            File file = new File("src\\cs445\\project2\\coordinates.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line, allData = "";
            while((line = br.readLine()) != null) {
                allData += (line + " ");
            }
            fileData = allData.split("[, ]");
            
            for(int i = 0; i < fileData.length; i++) {
                
                String currData = fileData[i].toUpperCase();
                switch(currData) {
                    case "P":
                        System.out.println("Polygon created..");
                        Polygon newPoly = new Polygon();
                        // Next 3 elements are RGB values
                        newPoly.setColor(Float.parseFloat(fileData[i + 1]),
                                         Float.parseFloat(fileData[i + 2]),
                                         Float.parseFloat(fileData[i + 3]));
                        
                        // After that all elements until a 'T' are vertices
                        // which are added to the polygon in pairs. J is used
                        // as an index value while numVerts is a sort of offset
                        // used when the loops breaks out to take in transformation
                        // commands
                        int numVerts = 0;
                        
                        // The 'P' and RGB values take up 4 elements
                        int j = i + 4; 
                        
                        while(true) {
                            System.out.println("Careful in loop");//delete
                            try {
                                newPoly.addVertex(Float.parseFloat(fileData[j]),
                                              Float.parseFloat(fileData[j + 1]));
                                numVerts++;
                                j +=2;
                            } catch(NumberFormatException e) {
                                System.out.println("Transformation command detected");
                                break;
                            }
                        }// End of while loop
                        
                        // j kept track of things inside while loop, so i has
                        // to be updated. numVerts has to jump i past all the
                        // vertices and to the element that begins the commands
                        // for transformations
                        i += (numVerts * 2);
                        
                    case "T":
                        System.out.println("Beginning trasnformations...");
                        i++; // move forward 1 element since 'T' was detected
                        int k = i;
                        // After a 'T' this loop will continue until the next 'p'
                        // It will save the transformation commands and add them
                        // to the polygon object which will take care of what
                        // to do with them
                        ArrayList<String> temp = new ArrayList();
                        while(!fileData[k].equals("P")) {
                            temp.add(fileData[k]);
                            k++;
                            /*if(fileData[i].charAt(0) == 'r') {
                                i++;// r character detected so move up 1
                                float degrees, x1, y1, x2, y2;
                                degrees = Float.parseFloat(fileData[i]); // next number is degrees to rotate
                                x1 = Float.parseFloat(fileData[i + 1]); // next 2 are coordinates to rotate around
                                y1 = Float.parseFloat(fileData[i + 2]);
                                x2 = Float.parseFloat(fileData[i + 3]);
                                y2 = Float.parseFloat(fileData[i + 4]);
                            } else if(fileData[i].charAt(0) == 's') {
                                i++;// s character detected so move up 1
                                // next 2 numbers are x and y scaling cals respectiv
                                // next 2 nums are the pivot point
                            } else if(fileData[i].charAt(0) == 't') {
                                i++;// t char, move up 1
                                // next 2 nums are translation coordinates
                            }*/
                        }
                        
                }// End of switch
                System.out.println(fileData[i]);
            }// End of for loop
            
        }catch(FileNotFoundException e) {
            System.out.println("The file was not found...");
        }catch(IOException e){
            System.out.println("Error occurred while reading file...");
            e.printStackTrace();
        }// End of try catch
        
    }// End of main
    
}
