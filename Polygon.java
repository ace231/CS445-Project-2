/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs445.project2;

/**
 *
 * @author Alfredo
 */
import java.util.ArrayList;


public class Polygon {
    private ArrayList<Vertex> vertices;
    private ArrayList<Edge> edges;
    private float[] color;
    
    public Polygon(){
        vertices = new ArrayList();
        edges = new ArrayList();
        color = new float[3]; // Color values in OpenGL are 3 float values
    }
    
    
    public void setColor(float r, float g, float b) {
        color[0] = r;
        color[1] = g;
        color[2] = b;
    }
    
    // Method: addVertex
    // Purpose: Adds new vertex to vertex list and also creates a new edge from
    // that new vertex to the previous vertex, as long as the vertex does not
    // already exist
    public void addVertex(float x, float y) {
        Vertex newVert = new Vertex(x, y);
        System.out.println("Adding " + newVert);//delete
        // Only add the vertex to the list if it's the first, do nothing else
        if(vertices.size() == 0 || vertices.isEmpty()) {
                vertices.add(newVert);
        } else if(!vertices.contains(newVert)) {// Check if vertex exists
           vertices.add(newVert);
           
           // If the vertices list has only 2 vertices, only 1 edge exists
           // which is the one between those two points. However once a third
           // vertex is added the polygon must contain 3 vertices and so on. The
           // updateEdges method handles this once at least 3 vertices exist.
           //if(vertices.size() >= 3){ 
             //  updateEdges(newVert);
           //}
        } else {
            System.out.printf("(%f,%f) already exists for this polygon ", x, y);
        }
    }
    
    private void updateEdges(Vertex newVert) {
        // The new vertex is now the last and indices start a 0. So attach the
        // previous vertex to the new one and then the new one to the first
        int prevLastVert = vertices.size() - 2;
        edges.add(new Edge(vertices.get(prevLastVert), newVert));
        edges.add(new Edge(newVert, vertices.get(0)));
    }
    
}
