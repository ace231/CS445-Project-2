/*******************************
* File: Polygon.java
* Author: Alfredo Ceballos
* Class: CS 445 - Computer Graphics
* 
* Assignment: Project 2
* Date last modified: 11/6/17
*
* Purpose: Class holds all data necessary for applying the scan line algorithm,
* is also in charge of drawing each polygons
*******************************/
package cs445.project2;

/**
 *
 * @author Alfredo
 */
import java.util.ArrayList;
import java.util.Collections;
import static org.lwjgl.opengl.GL11.*;

public class Polygon {
    
    private ArrayList<Vertex> vertices;
    private ArrayList<Edge> edges; // Works as all_edges table
    private float[] color;
    
    public Polygon(){
        vertices = new ArrayList();
        edges = new ArrayList();
        color = new float[3]; // Color values in OpenGL are 3 float values
    }
    
    
    // Method: setColor
    // Purpose: Sets RGB values for the polygon
    public void setColor(float r, float g, float b) {
        color[0] = r;
        color[1] = g;
        color[2] = b;
    }// End of setColor
    

    // Method: addVertex
    // Purpose: Adds new vertex to vertex list and also creates a new edge from
    // that new vertex to the previous vertex, as long as the vertex does not
    // already exist
    public void addVertex(float x, float y) {
        Vertex newVert = new Vertex(x, y);
        // Only add the vertex to the list if it's the first, do nothing else
        if(vertices.size() == 0 || vertices.isEmpty()) {
                vertices.add(newVert);
        } else if(!vertices.contains(newVert)) {// Check if vertex exists
           vertices.add(newVert);
        } else {
            System.out.printf("(%f,%f) already exists for this polygon ", x, y);
        }
    }// End of addVertex
    
    
    // Method: updateEdges
    // Purpose: Used to create edges after all vertices are saved and manupulated
    public void updateEdges() {
        Vertex prevVert = vertices.get(0);
        for(int i = 1; i < vertices.size(); i++){
            edges.add(new Edge(prevVert, vertices.get(i)));
            prevVert = vertices.get(i);
        }
        // At the end of the for loop, prevVert will get update to the last
        // vertex in the polygon, so just attach that one to the first
        edges.add(new Edge(prevVert, vertices.get(0)));
    }// End of updateEdges
    
    // Method: rotate
    // Purpose: Applies rotation to Polygon's vertices
    public void rotate(float degrees, float xPivot, float yPivot) {
        float radians = (float)Math.toRadians(degrees);
        for(Vertex vert : vertices) {
            vert.setX((float)((vert.getX()- xPivot) * Math.cos(radians)) 
                    - (float)((vert.getY() - yPivot) * Math.sin(radians)));
					
            vert.setY((float)((vert.getX() - xPivot) * Math.sin(radians)) 
                    + (float)((vert.getY() - yPivot) * Math.cos(radians)));
        }
    }// End of rotate
    
    // Method: scale
    // Purpose: Applies scaling transformation to Polygon's vertices
    public void scale(float xScale, float yScale, float xPivot, float yPivot) {
        for(Vertex vert : vertices) {
            vert.setX(vert.getX() * xScale + xPivot); // Scaling
            vert.setY(vert.getY() * yScale + yPivot);
        }
    }// End of scale
    
    // Method: translate
    // Purpose: Applies translation to Polygon's vertices
    public void translate(float x, float y) {
        for(Vertex vert : vertices) {
            vert.setX(vert.getX() + x);
            vert.setY(vert.getY() + y);
        }
    }// End of trasnlate
    
 
    
    // Method: draw
    // Purpose: Draws out polygon by first carrying out transformations then
    // applying the scan line algorithm to all the edges.
    public void draw() {
        // Transformations were done as they were read in. Edges update right after.
        // All edges are in the "edges" instance variable which acts as the
        // all_edges table
        
        // Moving appriopriate edges to global edge table whose slope does not
        // equal 0
        /*
        ArrayList<Edge> globalEdge = new ArrayList();
        for(int i = 0; i < edges.size(); i++) {
            if(edges.get(i).getSlope() != 0) {               
                globalEdge.add(edges.get(i));
            }
        }
        Collections.sort(globalEdge);
        
        float scanLine = globalEdge.get(0).getyMin();
	float currY = scanLine;
	float currX;
	boolean parity = false;
		
	ArrayList<Edge> activeEdges = new ArrayList<Edge>();
	while(!globalEdge.isEmpty()) {
            for(Edge e : globalEdge) {
                if(e.getyMin() == scanLine) {
                    activeEdges.add(e);
                    globalEdge.remove(e);
                }
            }
            
            // Sorting activeEdges
            for(int i=0;i< activeEdges.size();i++){
                
                Edge curr = activeEdges.get(i);
                
		for(int k=i+1; k<activeEdges.size();k++){
                    if(curr.getxVal() > activeEdges.get(k).getxVal()){
                        Edge temp = activeEdges.get(k);
			activeEdges.set(i,temp);
			activeEdges.set(k, curr);
                    }
		}
                
            }
            
            if(!activeEdges.isEmpty()) {
                currX = activeEdges.get(0).getxVal();
		while(currX <= activeEdges.get(activeEdges.size() -1).getxVal()) {
		
		}
            }
		
        }//End of while globalEdge is not empty 
	*/
        
        // Couldn't finish so i just drew the outlines of what i got >.<
        glColor3f(color[0], color[1], color[2]);
        glBegin(GL_LINE_LOOP);
            for(Vertex vert : vertices) {
                System.out.println(vert);
                glVertex3f(vert.getX(), vert.getY(), 0);
            }
        glEnd();
    }// End of draw
    

    // Method: printVertices
    // Purpose: Prints infor about polygon's vertices
    public void printVertices() {
        System.out.println("Printing vertices...");
        for(Vertex v : vertices) {
            System.out.println(v);
        }
    }
    
    // Method: printEdges
    // Purpose: print elements in edges Arraylist
    public void printEdges() {
        System.out.println("Printing edges...");
        for (Edge e: edges) {
            System.out.println(e.toString());
        }
    }
    
}// End of class