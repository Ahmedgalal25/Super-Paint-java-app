/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint;

/**
 *
 * @author Galal1
 */
public class ShapeFactory {
    
    private static ShapeFactory factory;
    
    private ShapeFactory(){
    };
    
    public static ShapeFactory getInstance(){
        if(factory == null){
            factory = new ShapeFactory();
        }
        
        return factory;
    }
    public MyShape getShape(ShapeType type){
        if(type == ShapeType.CIRCLE){
            return new MyCircle();
        }
        if(type == ShapeType.RECTANGLE){
            return new MyRectangle();
        }
        if(type == ShapeType.LINE){
            return new MyLine();
        }
        if(type == ShapeType.ELLIPSE){
            return new MyEllipse();
        }
        return null;
    }
}
