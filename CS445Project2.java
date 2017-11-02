/*******************************
* File: CS445Project2.java
* Author: Alfredo Ceballos
* Class: CS 445 - Computer Graphics
* 
* Assignment: Project 2
* Date last modified: 11//17
*
* Purpose: This program will load information from a text file
* which specify a polygon
*******************************/
package cs445.project2;

/**
 *
 * @author Alfredo
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

    /**
     * @param args the command line arguments
     */
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
            glColor3f(0.5f,0.5f,1.0f);
            
        }
    }
    
    
    public static void main(String[] args) {
        try{
            File file = new File("src\\cs445\\project2\\coordinates.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line, allData = "";
            while((line = br.readLine()) != null) {
                allData += (line + " ");
            }
            fileData = allData.split("[, ]");
            
            for(int i = 0; i < fileData.length; i ++){
                String currData = fileData[i].toUpperCase();
                switch(currData) {
                    case "P":
                        Polygon newPoly = new Polygon();
                        newPoly.setColor(Float.parseFloat(fileData[i + 1]),
                                         Float.parseFloat(fileData[i + 2]),
                                         Float.parseFloat(fileData[i + 3]));
                        while(true) {
                            System.out.println("Careful in loop");
                            int j = i + 4;
                            try {
                                newPoly.addVertex(Float.parseFloat(fileData[j]),
                                              Float.parseFloat(fileData[j + 1]));
                            } catch(NumberFormatException e) {
                                
                            }
                        }
                }
                System.out.println(fileData[i]);
            
            }
            
        }catch(Exception e) {
            System.out.println("The file was not found...");
        }
    }
    
}
