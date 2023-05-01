package services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Properties;

import entities.Commande;
import jakarta.mail.Authenticator;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.Connect;

 
 
public class CommandeService {
	private Connection connection = Connect.getInstance().getConnection();;

    public CommandeService() {
     }

    public void createCommande(Commande commande) throws SQLException {
    	LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);
    	String query = "INSERT INTO commande (panier_id, date_commande, adresse_livraison, prix_total, status) VALUES (?, ?, ?, ?, ?)";
    	try (PreparedStatement stmt = connection.prepareStatement(query)) {
    	    stmt.setInt(1, commande.getPanierId());
    	    stmt.setTimestamp(2, timestamp);
    	    stmt.setString(3, commande.getAdresseLivraison());
    	    stmt.setDouble(4, commande.getPrixTotal());
    	    stmt.setString(5, commande.getStatus());
    	    stmt.executeUpdate();
    	    System.out.println("ok ajouter");
            String subject = "Nouvelle compte ";
            String body = "Bonjour,\n\n Votre Commande est en cours   .\n\nCordialement,\n l'equipe de support";
            sendEmail("mohammedamine.aouadi@esprit.tn", subject, body);
     }catch (SQLException ex) {
     	   System.out.println("errr "+ex);
     	
     }
	}
     

    private void sendEmail(String to, String subject, String body) {
        String username = "mohammedamine.aouadi@esprit.tn";
        String password = "cgiqyuzphfrgrmqt"
        		 ;
         Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); // Change this to your SMTP server host(yahoo...)
            props.put("mail.smtp.port", "587"); // Change this to your SMTP server port
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            Session session;
            session = Session.getInstance(props,new Authenticator() {
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(username, password);
            }
        });
           
           
               try {
                // Create a MimeMessage object
     
    // Create a new message
                MimeMessage message = new MimeMessage(session);
                // Set the From, To, Subject, and Text fields of the message
                message.setFrom(new InternetAddress(username));
                message.addRecipient(jakarta.mail.Message.RecipientType.TO, new InternetAddress(to));
                message.setSubject(subject);
                message.setText(body);

                // Send the message using Transport.send
                Transport.send(message);

                System.out.println("Email sent successfully");
            } catch (MessagingException ex) {
                System.err.println("Failed to send email: " + ex.getMessage());
            }
 }  



	public ObservableList<Commande> afficherTous() {
		ObservableList<Commande> Commandes = FXCollections.observableArrayList();
		try {

			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM commande");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int panierId  = resultSet.getInt("panier_id");
				Timestamp timestamp = resultSet.getTimestamp("date_commande");
				LocalDateTime localDateTime = timestamp.toLocalDateTime();
				String adresse_livraison = resultSet.getString("adresse_livraison");
				float prix_total= resultSet.getFloat("prix_total");
				String status = resultSet.getString("status");

				Commande Commande = new Commande(id, panierId, localDateTime, adresse_livraison, prix_total,status);
				Commandes.add(Commande);
				System.out.println(Commande.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Commandes;
	}

    

    public void updateCommande(Commande commande) throws SQLException {
        String query = "UPDATE commande SET prix_total = ?, panier_id = ?,  adresse_livraison = ?, status = ?, date_commande = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
        	LocalDateTime now = commande.getDateCommande();
    		Timestamp timestamp = Timestamp.valueOf(now);
            stmt.setDouble(1, commande.getPrixTotal());
            stmt.setInt(2, commande.getPanierId());
            stmt.setString(3, commande.getAdresseLivraison());
            stmt.setString(4, commande.getStatus());
    	    stmt.setTimestamp(5, timestamp);
            stmt.setInt(6, commande.getId());
            stmt.executeUpdate();
        }
    }

	public void supprimer(int id) {
		try {
			String query = "DELETE FROM commande WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			System.out.println("commande a été supprimé avec succ�s !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur lors de la suppression de commande.");
		}
	}

}