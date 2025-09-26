package entities.enumerazioni;

public enum Stato {
	Accettata, Rifiutata, InAttesa;
	
	public String toString() {
		switch(this) {
			case Accettata:
				return "Accettata";
			case Rifiutata:
				return "Rifiutata";
			case InAttesa:
				return "In attesa";
			default:
				return "";
		}
	}
}


	
