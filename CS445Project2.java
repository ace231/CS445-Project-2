/*******************************
* File: CS445Project2.java
* Author: Alfredo Ceballos
* Class: CS 445 - Computer Graphics
* 
* Assignment: Project 2
* Date last modified: 11/6/17
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

    // Setting up width, height, fileData to hold data from file, and an
    // ArrayList to hold onto polygons that need to be drawn
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
            Display.setTitle("CS445 Project 2");
            Display.create();
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
     } // End of createWindow

	 
    // Method: initGL()
    // Purpose: Method in charge of initiating openGL
    private void initGL() {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(-320, 320, -240, 240, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
    } // End of initGL

    
    // Method: start
    // Purpose: Thread that handles running all other methods
    public void start() {
        try {
            createPolygons();
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
    // Purpose: Renders out background
    private void render(){
        while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            // Clear screen and depth buffers
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            // Setting of background
            glColor3f(0.0f,0.0f,0.0f);
            drawPolygons();
            Display.update();
            Display.sync(60);
        }
    }
    
    private void drawPolygons(){
        for(Polygon p : polygons){
                p.draw();
            }
    }
    
    
    public static void createPolygons(){
        polygons = new ArrayList<Polygon>();
        Polygon newPoly;
        try{
            File file = new File("src\\cs445\\project2\\coordinates.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line, allData = "";
            while((line = br.readLine()) != null) {
                allData += (line + " ");
            }
            fileData = allData.split("[, ]");
            
            for(int i = 0; i < fileData.length; i++) {
                String currData = fileData[i];
                
                if(currData.equals("P")){
                        System.out.println("Polygon created..");
                        newPoly = new Polygon();
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
                        i += 4;
                        
                        while(true) {
                            try {
                                newPoly.addVertex(Float.parseFloat(fileData[j]),
                                              Float.parseFloat(fileData[j + 1]));
                                numVerts++;
                                j += 2;
                            } catch(NumberFormatException e) {
                                break;
                            }
                        }// End of while loop
                        
                        // j kept track of things inside while loop, so i has
                        // to be updated. numVerts has to jump the index i 
                        // past all the vertices and to the element that begins 
                        // the commands for transformations
                        i += (numVerts * 2);
                        
                        if(fileData[i].equals("T")){
                            
                            i++; // move forward 1 element since 'T' was detected
                            int k = i;
                            // Will apply transformation as they're read in to the polygon
                            while(!fileData[k].equals("P")){
                                if(fileData[k].equals("r")) {
                                    newPoly.rotate(Float.parseFloat(fileData[k + 1]), // degrees
                                           Float.parseFloat(fileData[k + 2]), // pivot point
                                           Float.parseFloat(fileData[k + 3]));
                                    k += 3;
                                } else if(fileData[k].equals("s")) {
                                    newPoly.scale(Float.parseFloat(fileData[k+1]), // x factor
                                        Float.parseFloat(fileData[k+2]), // y factor
                                        Float.parseFloat(fileData[k+3]), // pivot point
                                        Float.parseFloat(fileData[k+4]));
                                    k += 4;
                                } else if(fileData[k].equals("t")) { // next 2 are parameters
                                    newPoly.translate(Float.parseFloat(fileData[k+1]), // x change 
                                                Float.parseFloat(fileData[k+2]));// y change
                                    k += 2;
                                }
                                if(k == fileData.length - 1){break;}
                                k++;
                            } // End of while loop
                            i = k - 1; // Update i but keep it on the "P"
                            // Update polygon edges then add it to the polygons ArrayList
                            newPoly.updateEdges();
                            polygons.add(newPoly);
                        }// End of if(fileData[i].equals("T"))
                         
                } // End of if(currData.equals("P"))
                
            }// End of for loop
            
        }catch(FileNotFoundException e) {
            System.out.println("The file was not found...");
        }catch(IOException e){
            System.out.println("Error occurred while reading file...");
            e.printStackTrace();
        }// End of try catch
        
       
    }// End of createPolygons
    
    // Method: main
    // Purpose: Program execution begins here. Also handles importing data from
    // file coordinates.txt
    public static void main(String[] args){
        CS445Project2 main = new CS445Project2();
        main.start();
    }// End of main
    
}
