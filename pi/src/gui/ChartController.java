package gui;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.scenario.effect.ImageData;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import tools.Connect;

import javax.imageio.ImageIO;
 

 
public class ChartController implements Initializable {
	   Connection conn;
	    public ChartController() {
	        conn = Connect.getInstance().getConnection();
	    }
    @FXML
    private AnchorPane chartNode;
    @FXML
    private HBox chartHBox;
    public static int numeroPDF = 0;
    Document doc = new Document();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        detaillePrix();
    }
 


    public ObservableList buildDataNbPB() {
//     public  ObservableList<PieChart.Data> buildData() {
        List<PieChart.Data> myList = new ArrayList<PieChart.Data>();
        ResultSet rs = null;
        PieChart.Data d;
        ObservableList observableList = null;

        try {
 
            String requete = "SELECT status, COUNT(*) FROM commande GROUP BY status;";
            		 

            Statement pst =conn.prepareStatement(requete); // import java.sql.Statement
            rs = pst.executeQuery(requete);
            while (rs.next()) {

                if (rs.getObject(1) == null) {
                    System.out.println(rs.getString(1));
                    d = new PieChart.Data("Autre ", rs.getInt(2));
                } else {
                    d = new PieChart.Data(rs.getString(1), rs.getInt(2));
                }
//                System.out.println(rs.getString(1));
//                System.out.println(rs.getInt(2));
                myList.add(d);

            }
            observableList = FXCollections.observableArrayList(myList);

            return observableList;

        } catch (Exception e) {

            System.out.println("Error on DB connection BuildData");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());

        }
        return observableList;
    }
    
    
    public ObservableList buildDataNbEP() {
//     public  ObservableList<PieChart.Data> buildData() {
        List<PieChart.Data> myList = new ArrayList<PieChart.Data>();
        ResultSet rs = null;
        PieChart.Data d;
        ObservableList observableList = null;

        try {
 //nbr  commande par addrese :

        	String requete = "SELECT adresse_livraison, COUNT(*) AS nombre_commandes "
        		    + "FROM commande "
        		    + "GROUP BY adresse_livraison;";

            Statement pst = conn.prepareStatement(requete); // import java.sql.Statement
            rs = pst.executeQuery(requete);
            while (rs.next()) {

                if (rs.getObject(1) == null) {
                    System.out.println(rs.getString(1));
                    d = new PieChart.Data("Autre ", rs.getInt(2));
                } else {
                    d = new PieChart.Data(rs.getString(1), rs.getInt(2));
                }
//                System.out.println(rs.getString(1));
//                System.out.println(rs.getInt(2));
                myList.add(d);

            }
            observableList = FXCollections.observableArrayList(myList);

            return observableList;

        } catch (Exception e) {

            System.out.println("Error on DB connection BuildDataBonPlan");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());

        }
        return observableList;
    }
    
    
     public XYChart.Series buildDataLineChart() {
    	 XYChart.Series<String, Integer> series = new XYChart.Series<>();
    	 series.setName("Prix Moyenne Par date:");


        ResultSet rs = null;
        XYChart.Series d;
        try {
        	String requete = "SELECT date_commande, AVG(prix_total) AS prix_moyen "
        	        + "FROM commande "
        	        + "GROUP BY date_commande;";

            Statement pst = conn.prepareStatement(requete); // import java.sql.Statement
            rs = pst.executeQuery(requete);
            while (rs.next()) 
            {
                series.getData().add(new XYChart.Data<>(rs.getString("date_commande"), rs.getInt("prix_moyen")));
            }
            
            return series;

        } catch (Exception e) {

            System.out.println("Error on DB connection BuildDataLineChart");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());

        }
        return series;
    }

      
      
     
    @FXML
    private void detaillePrix() {
        double total = 0;
        DecimalFormat df2 = new DecimalFormat(".##");
        Stage stage = new Stage();
        Scene scene = new Scene(new Group());
        stage.setTitle("Nombre de commandes par statut");
        stage.setWidth(600);
        stage.setHeight(600);

        final PieChart chart = new PieChart(buildDataNbPB());
        final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");

        for (final PieChart.Data data : chart.getData()) {
            total = total + data.getPieValue();
        }
        final double totalFinal = total;

        for (final PieChart.Data data : chart.getData()) {
            data.setName(((data.getName() + " " + df2.format((data.getPieValue() / totalFinal) * 100))) + "%");
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {

                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY());
                    caption.setText(String.valueOf(df2.format((data.getPieValue() / totalFinal) * 100)) + "%");
                    if (!((Group) scene.getRoot()).getChildren().contains(caption)) {
                        ((Group) scene.getRoot()).getChildren().add(caption);
                    }
                }
            });
        }

        chart.setTitle("Nombre de commandes par statut");
        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        chartNode.getChildren().clear();
        chartNode.getChildren().setAll(chart);

    }

 
    @FXML
    private void globalChart(ActionEvent event) {

    		detaillePrix();
        

    }

    @FXML
    private void detailleEtat(ActionEvent event) {
                 double total = 0;
        DecimalFormat df2 = new DecimalFormat(".##");
        Stage stage = new Stage();
        Scene scene = new Scene(new Group());
        stage.setTitle("nommbre  commande par addrese :");
        stage.setWidth(600);
        stage.setHeight(600);
        
        System.out.println(buildDataNbEP());
        final PieChart chart = new PieChart(buildDataNbEP());
        final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");

        for (final PieChart.Data data : chart.getData()) {
            total = total + data.getPieValue();
        }
        final double totalFinalCompte = total;

        for (final PieChart.Data data : chart.getData()) {
            data.setName(((data.getName() + " " + df2.format((data.getPieValue() / totalFinalCompte) * 100))) + "%");
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {

                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY());
                    caption.setText(String.valueOf(df2.format((data.getPieValue() / totalFinalCompte) * 100)) + "%");
                    if (!((Group) scene.getRoot()).getChildren().contains(caption)) {
                        ((Group) scene.getRoot()).getChildren().add(caption);
                    }
                }
            });
        }

        chart.setTitle("nommbre  commande par addrese :");
        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        chartNode.getChildren().clear();
        chartNode.getChildren().setAll(chart);
    }

    @FXML
    private void lineChart(ActionEvent event) {
        
       
      double total = 0;
        DecimalFormat df2 = new DecimalFormat(".##");
        Stage stage = new Stage();
        Scene scene = new Scene(new Group());
        stage.setTitle(" PRIX mOYENNE PAR DATE :"
        		+ "");
        stage.setWidth(600);
        stage.setHeight(600);


        //defining the axes
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Produit");
        //creating the chart
        final LineChart<String,Number> lineChart = 
                new LineChart<String,Number>(xAxis,yAxis);
                
 
        
        lineChart.getData().add(buildDataLineChart());
       ((Group) scene.getRoot()).getChildren().add(lineChart);
        stage.setScene(scene);
        chartNode.getChildren().clear();
        chartNode.getChildren().setAll(lineChart);
        
    }
 
    @FXML
    private void convertirPDF(ActionEvent event) throws FileNotFoundException, DocumentException, BadElementException, IOException {
        numeroPDF = numeroPDF + 1;
        String nom = "Graph statistique " + numeroPDF + ".pdf";
        try {
            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat Heure = new SimpleDateFormat("hh:mm:ss");
            //Font f = new Font(FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.GRAYWHITE);

            WritableImage wimg = chartNode.snapshot(new SnapshotParameters(), null);
            File file = new File("ChartSnapshot.png");
            ImageIO.write(SwingFXUtils.fromFXImage(wimg, null), "png", file);

            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(System.getProperty("user.home") + "\\Desktop\\" + nom));
            doc.open();
//            Image img = Image.getInstance("C:\\xampp\\htdocs\\TunisiaBonPlan2-Integration\\web\\uploads\\images\\Sanstitre.png");
            Image img = Image.getInstance("ChartSnapshot.png");
            doc.add(img);
 
            doc.close();
            Desktop.getDesktop().open(new File(System.getProperty("user.home") + "\\Desktop\\" + nom));
            writer.close();

        } catch (Exception e) {

            System.out.println("Error PDF");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());

        }

    } 
}
