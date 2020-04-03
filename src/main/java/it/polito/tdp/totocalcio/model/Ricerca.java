package it.polito.tdp.totocalcio.model;

import java.util.ArrayList;
import java.util.List;

public class Ricerca {

	private Pronostico pronostico;
	private Integer N;
	private List<Risultato> soluzione;
	
	public List<Risultato> cerca(Pronostico pronostico) {
		this.pronostico=pronostico;
		this.N=pronostico.size();
		
		List<RisultatoPartita> parziale= new ArrayList<>();
		int livello=0;
		
		this.soluzione=new ArrayList<Risultato>();
		
		ricorsiva(parziale,livello);
		
		return this.soluzione;
	}

	private void ricorsiva(List<RisultatoPartita> parziale,Integer livello) {
		
		//sono al caso terminale??
		if(livello==N) {
			//questa soluzione parziale è una soluzione completa
			//System.out.println(parziale);
			this.soluzione.add(new Risultato(parziale));
		}else {
			//caso generale
			PronosticoPartita pp= pronostico.get(livello);
			//pp sono i sotto-problemi da provare, guardo da cosa è composto e 
			//provo tutte le possibilità
			for(RisultatoPartita ris:pp.getRisultati()) {
				//provo a mettere 'ris' nella posizione 'livello' della soluzione parziale
				
				//costruzione soluzione parziale(sottoproblema)
				parziale.add(ris);
				//chiamata ricorsiva
				ricorsiva(parziale,livello+1);
				//backtracking
				parziale.remove(parziale.size()-1);
			}
		}
		
	}
}
/*
 * Livello= indica il numero di partita che sto considerando
 * le partite da 0 a livello-1 sono già state decise
 * la partita di indice livello la devo decidere io
 * le partite da livello+1 in poi le decideranno le procedure ricorsive sottostanti
 * 
 * Soluzione parziale: un elenco di RisultatoPartita di lunghezza pari al livello
 * 
 * Soluzione totale: h N risultati
 * 
 * Condizione di terminazione: livello==N
 * 
 * Generazione delle nuove soluzioni del livello: provando tutti i pronostici 
 * 													definiti per quel livello
 * 
 */