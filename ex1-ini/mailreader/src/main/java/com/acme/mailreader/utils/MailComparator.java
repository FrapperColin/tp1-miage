package com.acme.mailreader.utils;

import java.util.Comparator;

import com.acme.mailreader.model.Mail;

/**
 * Comparateur de mails
 * 
 * Comme on désire afficher les mails les plus importants en premier, l'element le plus grand retourne une valeur négative
 *
 */
// D�gager tous ces commentaires
public class MailComparator implements Comparator<Mail> {

    final int LESS_IMPORTANT = 1;
    final int EQUAL = 0;
    int MORE_IMPORTANT = -1;
    
	public int compare(Mail mail, Mail otherMail) {
		
//		if (mail == null || otherMail == null) {
//			return EQUAL;
//		}
		
		if (mail == null || otherMail == null) {
			throw new IllegalArgumentException("Can't compare with a null value");
		}
		
		
		
		
//		original
		if (mail.isImportant() != otherMail.isImportant()) {
			if (mail.isImportant() && !otherMail.isImportant()) {
				return MORE_IMPORTANT;
			} else {
				return LESS_IMPORTANT;
			}
		}
		

		// original
//		if (mail.getStatut() != otherMail.getStatut()) {
//			int comp = mail.getStatut().ordinal()
//					- otherMail.getStatut().ordinal();
//			return comp > 0 ? -1 : 1;
//		}
		
		// utiliser des méthodes courtes page 90 livre
		
		if (!(mail.getStatut().equals(otherMail.getStatut()))) {
			int compareOrder = compareOrderStatus(mail,otherMail);
			if(compareOrder > 0) {
				return MORE_IMPORTANT ;
			} else {
				return LESS_IMPORTANT;
			}
		}
		
		
		
//		if (mail.getSujet() != otherMail.getSujet()) {
//			return otherMail.getSujet().compareTo(mail.getSujet());
//		}
		
		if (!(mail.getSujet().equals(otherMail.getSujet()))) {
			return compareMailSubjet(mail,otherMail) ;
		}
		
		return otherMail.getDate().compareTo(mail.getDate());
	}

	private int compareMailSubjet(Mail mail, Mail otherMail) {
		return otherMail.getSujet().compareTo(mail.getSujet());
	}

	private int compareOrderStatus(Mail mail, Mail otherMail) {
		return mail.getStatut().ordinal() - otherMail.getStatut().ordinal();
	}
	

}
