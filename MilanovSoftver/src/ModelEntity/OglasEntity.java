package ModelEntity;

public class OglasEntity {
	
	public int idKategorije;
	public int idGrupe;
	public String nazivOglasa;
	public String stanje;
	public int cena;
	public boolean isDin;
	public boolean zamena;
	public boolean cenaFixna;
	public boolean obnavljanje;
	public boolean kpIzlogDodavanje;
	public String textOglasa;
	public String[] imagesPath;
	
	public OglasEntity(){
		
	}

	public int getIdKategorije() {
		return idKategorije;
	}

	public void setIdKategorije(int idKategorije) {
		this.idKategorije = idKategorije;
	}

	public int getIdGrupe() {
		return idGrupe;
	}

	public void setIdGrupe(int idGrupe) {
		this.idGrupe = idGrupe;
	}

	public String getNazivOglasa() {
		return nazivOglasa;
	}

	public void setNazivOglasa(String nazivOglasa) {
		this.nazivOglasa = nazivOglasa;
	}

	public String getStanje() {
		return stanje;
	}

	public void setStanje(String stanje) {
		this.stanje = stanje;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public boolean isDin() {
		return isDin;
	}

	public void setDin(boolean isDin) {
		this.isDin = isDin;
	}

	public boolean isZamena() {
		return zamena;
	}

	public void setZamena(boolean zamena) {
		this.zamena = zamena;
	}

	public boolean isCenaFixna() {
		return cenaFixna;
	}

	public void setCenaFixna(boolean cenaFixna) {
		this.cenaFixna = cenaFixna;
	}

	public String getTextOglasa() {
		return textOglasa;
	}

	public void setTextOglasa(String textOglasa) {
		this.textOglasa = textOglasa;
	}

	public String[] getImagesPath() {
		return imagesPath;
	}

	public void setImagesPath(String[] imagesPath) {
		this.imagesPath = imagesPath;
	}

	public boolean isObnavljanje() {
		return obnavljanje;
	}

	public void setObnavljanje(boolean obnavljanje) {
		this.obnavljanje = obnavljanje;
	}

	public boolean isKpIzlogDodavanje() {
		return kpIzlogDodavanje;
	}

	public void setKpIzlogDodavanje(boolean kpIzlogDodavanje) {
		this.kpIzlogDodavanje = kpIzlogDodavanje;
	}

}

/*
 *      public int idKategorije { get; set; }
        public int idGrupe { get; set; }
        public String nazivOglasa { get; set; }
        public String stanje { get; set; }
        public int cena { get; set; }
        public Boolean isDin { get; set; }
        public Boolean zamena { get; set; }
        public Boolean cenaFixna { get; set; }
        public String textOglasa { get; set; }
        public List<String> imagesPath { get; set; }
 * */
 