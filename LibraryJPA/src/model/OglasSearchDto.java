package model;

import java.util.ArrayList;
import java.util.List;

public class OglasSearchDto {
	
	private int izabranBrend;
	private String boja;
	private String model;
	private String naslov;

	public OglasSearchDto() {
		
	}
	
	public OglasSearchDto(int izabranBrend, String boja, String model, String naslov) {
		this.izabranBrend = izabranBrend;
		this.boja = boja;
		this.model = model;
		this.naslov = naslov;
	}

	public int getIzabranBrend() {
		return izabranBrend;
	}

	public void setIzabranBrend(int izabranBrend) {
		this.izabranBrend = izabranBrend;
	}

	public String getBoja() {
		return boja;
	}

	public void setBoja(String boja) {
		this.boja = boja;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String generateQueryExtensionForOglas() {
		return generateQueryFromSearchCriteria(getSearchCriterias());
	}

	private String generateQueryFromSearchCriteria(List<SearchCriteria> searchCriterias) {
		if(searchCriterias.isEmpty()) {
			return "";
		}
		
		SearchCriteria lastSearchCriteria = searchCriterias.remove(searchCriterias.size() - 1);
		String query = " and ";
		for (SearchCriteria sc : searchCriterias) {
			query += "o." + sc.toString() + " and ";
		}

		query += lastSearchCriteria.toString();
		return query;
	}

	private List<SearchCriteria> getSearchCriterias() {
		List<SearchCriteria> searchCriteria = new ArrayList<SearchCriteria>();
		if (this.boja != null && !this.boja.isEmpty()) {
			searchCriteria.add(new SearchCriteria(SearchOption.BOJA, boja));
		}

		if (this.naslov != null && !this.naslov.isEmpty()) {
			searchCriteria.add(new SearchCriteria(SearchOption.NASLOV, naslov));
		}

		if (this.model != null && !this.model.isEmpty()) {
			searchCriteria.add(new SearchCriteria(SearchOption.MODEL, model));
		}

		if (this.izabranBrend != 0) {
			searchCriteria.add(new SearchCriteria(SearchOption.ID_BRENDA, "" + izabranBrend));
		}

		return searchCriteria;
	}

	private class SearchCriteria {
		SearchOption searchOption;
		String value;

		protected SearchCriteria(SearchOption searchOption, String value) {
			this.searchOption = searchOption;
			this.value = value;
		}

		@Override
		public String toString() {
			String valueFormated = "";
			if (searchOption.isId()) {
				valueFormated += " = " + this.value;
			} else {
				valueFormated += " like '%" + this.value + "%'";
			}

			return searchOption.getColumn() + valueFormated;
		}
	}
	
	private enum SearchOption {
		ID_BRENDA("automobil.brend.idBrenda", true), BOJA("automobil.boja", false), MODEL("automobil.model", false), NASLOV("naslov", false);

		private String column;
		private boolean isId;

		SearchOption(String column, boolean isId) {
			this.column = column;
			this.isId = isId;
		}

		String getColumn() {
			return column;
		}

		boolean isId() {
			return isId;
		}
	}
}