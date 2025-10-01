package entities.enumerazioni;

public enum Sede {
	MonteSantAngelo, ViaClaudio, ViaMezzocannone, PiazzaleTecchio, Policlinico, SedeCentrale;

	public String toString() {
		switch (this) {
			case MonteSantAngelo:
				return "Monte Sant'Angelo";
			case ViaClaudio:
				return "Via Claudio";
			case ViaMezzocannone:
				return "Via Mezzocannone";
			case PiazzaleTecchio:
				return "Piazzale Tecchio";
			case Policlinico:
				return "Policlinico";
			case SedeCentrale:
				return "Sede Centrale";
				
			default:
				return "";
		}
	}
}
