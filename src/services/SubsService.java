/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import entities.Subscription;
import entities.typeSub;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tools.MaConnection;

/**
 *
 * @author uSER
 */
public class SubsService implements subInterface<Subscription> {

    Connection cnx;

    public SubsService() {
        cnx = MaConnection.getInstance().getCnx();
    }

  public ObservableList<Subscription> getAllSubscriptions() {
    ObservableList<Subscription> subscriptions = FXCollections.observableArrayList();
    try {
        String sql = "SELECT * FROM subscription";
        Statement stmt = cnx.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Subscription p = new Subscription();

            int typeId = rs.getInt("type_sub_id");
            typeSub c = getTypeSubById(typeId);
            p.setTypeSubs(c);

            p.setId(rs.getInt("id"));
            p.setDescription(rs.getString("description"));
            p.setPrice(rs.getInt("price"));
            p.setStart_date(rs.getDate("start_date"));
            p.setEnd_date(rs.getDate("end_date"));
            p.setPeriod(rs.getInt("period"));

            subscriptions.add(p);
        }

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return subscriptions;
}


public typeSub getTypeSubById(int id) {
    typeSub t = null;
    try {
        String sql = "SELECT * FROM type_sub WHERE id = ?";
        PreparedStatement stmt = cnx.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            t = new typeSub();
            t.setId(rs.getInt("id"));
            t.setName(rs.getString("type_name"));
            t.setDescription(rs.getString("desc_t"));
            t.setEtat(rs.getString("etat"));
        }

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return t;
}



public void addSubscription(Subscription s) {
    String sql = "INSERT INTO subscription(description, price, start_date, end_date, period, type_sub_id) VALUES (?, ?, ?, ?, ?, ?)";
    try (PreparedStatement ps = cnx.prepareStatement(sql)) {
        ps.setString(1, s.getDescription());
        ps.setInt(2, s.getPrice());
        ps.setDate(3, new java.sql.Date(s.getStart_date().getTime()));
        ps.setDate(4, new java.sql.Date(s.getEnd_date().getTime()));
        ps.setInt(5, s.getPeriod());
        ps.setInt(6, s.getType_sub_id());

        ps.executeUpdate();
        System.out.println("Subscription added successfully.");
    } catch (SQLException ex) {
        System.out.println("Error while adding subscription: " + ex.getMessage());
        ex.printStackTrace();
    }
}



    @Override
    public void supprimer(int id) {
        try {
            PreparedStatement ps = cnx.prepareStatement("DELETE FROM subscription WHERE id = ?");
            ps.setInt(1, id);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Abonnement supprimé avec succès");
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression de l'abonnement dd: " + ex.getMessage());
        }
    }
public int getTypeSubIdByName(String typeName) {
    int typeId = -1;
    try {
        String sql = "SELECT id FROM type_sub WHERE type_name=?";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setString(1, typeName);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            typeId = rs.getInt("id");
        }
    } catch (SQLException ex) {
        System.out.println("Error while getting type sub ID: " + ex.getMessage());
    }
    return typeId;
}
public List<String> getAllTypeSubNames() {
    List<String> typeSubNames = new ArrayList<>();
    try {
        String sql = "SELECT type_name FROM type_sub";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String typeName = rs.getString("type_name");
            typeSubNames.add(typeName);
        }
    } catch (SQLException ex) {
        System.out.println("Error while getting type sub names: " + ex.getMessage());
    }
    return typeSubNames;
}


public void modifier(Subscription a) {
    String sql = "UPDATE subscription SET description = ?, price = ?, start_date = ?, end_date = ?, period = ?, type_sub_id = ? WHERE id = ?";
    PreparedStatement ste;
    try {
        ste = cnx.prepareStatement(sql);
        ste.setString(1, a.getDescription());
        ste.setDouble(2, a.getPrice());
        ste.setDate(3, a.getStart_date());
        ste.setDate(4, a.getEnd_date());
        ste.setInt(5, a.getPeriod());
        ste.setInt(6, a.getType_sub_id());
        ste.setInt(7, a.getId()); // assuming "id" is an integer field in the database
      
        int rowsAffected = ste.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Abonnement modifié");
        } else {
            System.out.println("Aucun abonnement modifié");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

public ObservableList<Subscription> search(String searchText) {
    ObservableList<Subscription> subscriptions = FXCollections.observableArrayList();
    try {
        String sql = "SELECT * FROM subscription WHERE description LIKE ?";
        PreparedStatement stmt = cnx.prepareStatement(sql);
        stmt.setString(1, "%" + searchText + "%");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Subscription p = new Subscription();

            int typeId = rs.getInt("type_sub_id");
            typeSub c = getTypeSubById(typeId);
            p.setTypeSubs(c);

            p.setId(rs.getInt("id"));
            p.setDescription(rs.getString("description"));
            p.setPrice(rs.getInt("price"));
            p.setStart_date(rs.getDate("start_date"));
            p.setEnd_date(rs.getDate("end_date"));
            p.setPeriod(rs.getInt("period"));

            subscriptions.add(p);
        }

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return subscriptions;
}
public List<Subscription> ordredbyPrice() {
         
          ObservableList<Subscription> subscriptions = FXCollections.observableArrayList();
    try {
      String req="select * from subscription order BY price DESC";
        PreparedStatement stmt = cnx.prepareStatement(req);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Subscription p = new Subscription();

            int typeId = rs.getInt("type_sub_id");
            typeSub c = getTypeSubById(typeId);
            p.setTypeSubs(c);

            p.setId(rs.getInt("id"));
            p.setDescription(rs.getString("description"));
            p.setPrice(rs.getInt("price"));
            p.setStart_date(rs.getDate("start_date"));
            p.setEnd_date(rs.getDate("end_date"));
            p.setPeriod(rs.getInt("period"));

            subscriptions.add(p);
        }

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return subscriptions;
} 
public List<Subscription> ordredbyDate() {
         
          ObservableList<Subscription> subscriptions = FXCollections.observableArrayList();
    try {
      String req="select * from subscription order BY start_date";
        PreparedStatement stmt = cnx.prepareStatement(req);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Subscription p = new Subscription();

            int typeId = rs.getInt("type_sub_id");
            typeSub c = getTypeSubById(typeId);
            p.setTypeSubs(c);

            p.setId(rs.getInt("id"));
            p.setDescription(rs.getString("description"));
            p.setPrice(rs.getInt("price"));
            p.setStart_date(rs.getDate("start_date"));
            p.setEnd_date(rs.getDate("end_date"));
            p.setPeriod(rs.getInt("period"));

            subscriptions.add(p);
        }

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return subscriptions;
} 

@Override
    public void Qr( Stage primaryStage,Subscription p) {
         //Stage primaryStage = null;
    QRCodeWriter qrCodeWriter = new QRCodeWriter();

        String myWeb = p.toString() ;

        int width = 300;
        int height = 300;
        String fileType = "png";
        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();

            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            System.out.println("Success...");

        } catch (WriterException ex) {
            //Logger.getLogger(JavaFX_QRCo.class.getName()).log(Level.SEVERE, null, ex);
        }

        ImageView qrView = new ImageView();
        qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));

        StackPane root = new StackPane();
        root.getChildren().add(qrView);

        Scene scene = new Scene(root, 350, 350);

        primaryStage.setTitle("Subscription QR");
        primaryStage.setScene(scene);
       primaryStage.show();
    }
    public List<Subscription> SUPPRIME() {
    String req = "select * from subscription ";
          ObservableList<Subscription> subscriptions = FXCollections.observableArrayList();
    Date currentDate = new Date();
    SubsService pdao=new SubsService();
    try {
       
        Statement stmt = cnx.createStatement();
        ResultSet rs = stmt.executeQuery(req);

        while (rs.next()) {
            Subscription p = new Subscription();

            int typeId = rs.getInt("type_sub_id");
            typeSub c = getTypeSubById(typeId);
            p.setTypeSubs(c);

            p.setId(rs.getInt("id"));
            p.setDescription(rs.getString("description"));
            p.setPrice(rs.getInt("price"));
            p.setStart_date(rs.getDate("start_date"));
            p.setEnd_date(rs.getDate("end_date"));
            p.setPeriod(rs.getInt("period"));

            subscriptions.add(p);
              Date end_date=rs.getDate("end_date");
               if (end_date.before(currentDate)) {
                   
                System.out.println("kbal"); //pdao.delete(rs.getInt("id"));
                pdao.supprimer(rs.getInt("id"));

            } else {
                System.out.println("mezelet");
            }
        }

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return subscriptions;
}
}
