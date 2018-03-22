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

    int LESS_IMPORTANT = 1;
    int EQUAL = 0;
    int MORE_IMPORTANT = -1;
    
	public int compare(Mail mail1, Mail mail2) {
		
		if (oneOfTheMailIsNull(mail1, mail2)) {
			throw new IllegalArgumentException("Can't compare with a null value");
			//return EQUAL;
		}
		if (notTheSameImportance(mail1,mail2)) {
			return mostImportantMail(mail1,mail2);
		}
		
		if (notTheSameStatut(mail1,mail2)) {
			return sortByStatut(mail1,mail2);			
		}
		if (notTheSameSubject(mail1,mail2)) {
			return compareMailSubjet(mail1,mail2) ;
		}
		return mail1.getDate().compareTo(mail2.getDate());
	}

	private boolean notTheSameSubject(Mail mail1, Mail mail2) {
		return !mail1.getSujet().equals(mail2.getSujet());
	}

	private int sortByStatut(Mail mail1, Mail mail2) {
		int compareOrder = compareOrderStatus(mail1,mail2);
		return compareOrder < 0 ? LESS_IMPORTANT : MORE_IMPORTANT;
	}
	
	private boolean notTheSameStatut(Mail mail1, Mail mail2) {
		return mail1.getStatut() != mail2.getStatut();
	}

	private boolean notTheSameImportance(Mail mail1, Mail mail2) {
		return mail1.isImportant() != mail2.isImportant();
	}

	private boolean oneOfTheMailIsNull(Mail mail1, Mail mail2) {
		return mail1 == null || mail2 == null ;
	}

	private int mostImportantMail(Mail mail, Mail otherMail) {
		if (mail.isImportant() && !otherMail.isImportant()) {
			return MORE_IMPORTANT;
		} else {
			return LESS_IMPORTANT;
		}
	}

	private int compareMailSubjet(Mail mail1, Mail mail2) {
		return mail1.getSujet().compareTo(mail2.getSujet());
	}

	private int compareOrderStatus(Mail mail1, Mail mail2) {
		return mail1.getStatut().ordinal() - mail2.getStatut().ordinal();
	}
	

}
