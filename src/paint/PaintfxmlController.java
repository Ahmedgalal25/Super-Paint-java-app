
package paint;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javafx.stage.Stage;
import static javax.swing.text.StyleConstants.getBackground;


/**
 *
 * @author Galal1
 */
public class PaintfxmlController implements Initializable {
      
    
    
    
   public int counter = 0;
    
    //******* Fxml ******////
   //*******Toggle buttons******/
   public ToggleButton drawbtn;
   public ToggleButton rectbtn;
   public ToggleButton rubberbtn;
   public ToggleButton circlebtn;
   public ToggleButton linebtn;
   public ToggleButton elipsebtn;
   public ToggleButton savebtn;
   public ToggleButton loadbtn;
   public Button clearbtn;
   
   //*******Color pickers and Text field for size /****//
   public ColorPicker lineCol ;
   public ColorPicker fillCol;
   public TextField bsize;
    
   //**** Factory Design Pattern for different shapes*****//
   public ShapeFactory shapeFactory = ShapeFactory.getInstance();
   public MyRectangle rect = (MyRectangle)shapeFactory.getShape(ShapeType.RECTANGLE);
   public MyCircle circ =  (MyCircle)shapeFactory.getShape(ShapeType.CIRCLE);
   public MyLine line = (MyLine)shapeFactory.getShape(ShapeType.LINE);
   public MyEllipse elps = (MyEllipse)shapeFactory.getShape(ShapeType.ELLIPSE);
   
   
    
    
    
    

    
    @FXML
    Canvas canvas = new Canvas();
    
    GraphicsContext brushTool;
   
   
    
    
    
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
         ToggleButton[] toolsArr = {drawbtn, rubberbtn, rectbtn, circlebtn,savebtn,elipsebtn,linebtn,loadbtn};
        
        ToggleGroup tools = new ToggleGroup();
        
        for (ToggleButton tool : toolsArr) {
            tool.setToggleGroup(tools);
            tool.setCursor(Cursor.HAND);
        }
        

        brushTool = canvas.getGraphicsContext2D();
        
        
        canvas.setOnMousePressed(e->{
            if(drawbtn.isSelected()&&!bsize.getText().isEmpty()) {
                brushTool.setStroke(lineCol.getValue());
                brushTool.beginPath();
                brushTool.lineTo(e.getX(), e.getY());
                int x = Integer.parseInt(bsize.getText());
                brushTool.setLineWidth(x);

            }
            else if(rubberbtn.isSelected()) {
                 
                
                double lineWidth = Double.parseDouble(bsize.getText());
                
                brushTool.clearRect(e.getX() - lineWidth / 2, e.getY() - lineWidth / 2, lineWidth, lineWidth);
                                

            }
            else if(rectbtn.isSelected()&&!bsize.getText().isEmpty()) {
                System.out.println("qqqqqqqqqqqqqqqqqqqqqqq");
                brushTool.beginPath();

                brushTool.setStroke(lineCol.getValue());
                brushTool.setFill(fillCol.getValue());
                rect.setX(e.getX());                
                rect.setY(e.getY());
                int x = Integer.parseInt(bsize.getText());
                brushTool.setLineWidth(x);
            }
            else if(circlebtn.isSelected()) {
               
                brushTool.setStroke(lineCol.getValue());
                brushTool.setFill(fillCol.getValue());
                circ.setCenterX(e.getX());
                circ.setCenterY(e.getY());
                int x = Integer.parseInt(bsize.getText());
                brushTool.setLineWidth(x);
            }
            else if(linebtn.isSelected()) {
                brushTool.setStroke(lineCol.getValue());
                line.setStartX(e.getX());
                line.setStartY(e.getY());
                int x = Integer.parseInt(bsize.getText());
                brushTool.setLineWidth(x);
            }
            else if(elipsebtn.isSelected()) {
                brushTool.setStroke(lineCol.getValue());
                brushTool.setFill(fillCol.getValue());
                elps.setCenterX(e.getX());
                elps.setCenterY(e.getY());
                int x = Integer.parseInt(bsize.getText());
                brushTool.setLineWidth(x);
            }
            
            else if(savebtn.isSelected()){
                
                counter++;
                System.out.println("ANA MIN!");

                  File file= new File("new"+counter+".png");
            try {
                    WritableImage writableImage = new WritableImage(1080, 790);
                    canvas.snapshot(null, writableImage);
                    RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                    ImageIO.write(renderedImage, "png", file);
                    System.out.println("ANA AMIN!");

                } catch (IOException ex) {
                    System.out.println("Error!");
                }
                }
            else if (loadbtn.isSelected()){
            FileChooser openFile = new FileChooser();
            openFile.setTitle("Open File");
            File file = openFile.showOpenDialog(null);
            if (file != null) {
                try {
                    InputStream io = new FileInputStream(file);
                    Image img = new Image(io);
                    brushTool.drawImage(img, 0, 0);
                } catch (IOException ex) {
                    System.out.println("Error!");
                }
            }
                
            }
            
            
            
            });
            
        canvas.setOnMouseDragged(e ->{
           if(drawbtn.isSelected()&&!bsize.getText().isEmpty()) {
                brushTool.lineTo(e.getX(), e.getY());
                brushTool.stroke();
            }
           else if(rubberbtn.isSelected()){
               
                double lineWidth = Double.parseDouble(bsize.getText());
                               

                brushTool.clearRect(e.getX() - lineWidth / 2, e.getY() - lineWidth / 2, lineWidth, lineWidth);
                
           }
           
           
           
            /*
            
            double size =Double.parseDouble(bsize.getText());
            double x = e.getX() - size /2;
            double y = e.getY() - size /2;
            
            
            if(toolSelected && !bsize.getText().isEmpty()){
                brushTool.setFill(colorpicker.getValue());
                brushTool.fillRoundRect(x, y, size, size, size, size);
            }*/
        });
        canvas.setOnMouseReleased(e ->{
            
           if(drawbtn.isSelected()&&!bsize.getText().isEmpty()) {
                brushTool.lineTo(e.getX(), e.getY());
                brushTool.stroke();
                brushTool.closePath();
            }
           else if(rubberbtn.isSelected()) {
               
                double lineWidth = Double.parseDouble(bsize.getText());
                brushTool.clearRect(e.getX() - lineWidth / 2, e.getY() - lineWidth / 2, lineWidth, lineWidth);
                
            }
           else if(rectbtn.isSelected()) {
                rect.setWidth(Math.abs((e.getX() - rect.getX())));
                rect.setHeight(Math.abs((e.getY() - rect.getY())));
                //rect.setX((rect.getX() > e.getX()) ? e.getX(): rect.getX());
                if(rect.getX() > e.getX()) {
                    rect.setX(e.getX());
                }
                //rect.setY((rect.getY() > e.getY()) ? e.getY(): rect.getY());
                if(rect.getY() > e.getY()) {
                    rect.setY(e.getY());
                }
                brushTool.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
                brushTool.strokeRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
            }
           else if(circlebtn.isSelected()) {
                circ.setRadius((Math.abs(e.getX() - circ.getCenterX()) + Math.abs(e.getY() - circ.getCenterY())) / 2);
                
                if(circ.getCenterX() > e.getX()) {
                    circ.setCenterX(e.getX());
                }
                if(circ.getCenterY() > e.getY()) {
                    circ.setCenterY(e.getY());
                }
                
                brushTool.fillOval(circ.getCenterX(), circ.getCenterY(), circ.getRadius(), circ.getRadius());
                brushTool.strokeOval(circ.getCenterX(), circ.getCenterY(), circ.getRadius(), circ.getRadius());
                
                
            }
           else if(linebtn.isSelected()) {
                line.setEndX(e.getX());
                line.setEndY(e.getY());
                brushTool.strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
                
               
            }
           else if(elipsebtn.isSelected()) {
                elps.setRadiusX(Math.abs(e.getX() - elps.getCenterX()));
                elps.setRadiusY(Math.abs(e.getY() - elps.getCenterY()));
                
                if(elps.getCenterX() > e.getX()) {
                    elps.setCenterX(e.getX());
                }
                if(elps.getCenterY() > e.getY()) {
                    elps.setCenterY(e.getY());
                }
                
                brushTool.strokeOval(elps.getCenterX(), elps.getCenterY(), elps.getRadiusX(), elps.getRadiusY());
                brushTool.fillOval(elps.getCenterX(), elps.getCenterY(), elps.getRadiusX(), elps.getRadiusY());
                
                
            }
           });
           clearbtn.setOnAction(e->{
                brushTool.clearRect(0, 0, brushTool.getCanvas().getWidth(), brushTool.getCanvas().getHeight());
        });
          
        
        
        
        
        
        
        
      
    }

 
    
    
   
        
        
    
   
}
