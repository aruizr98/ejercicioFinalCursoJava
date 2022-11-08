package curso.java.ejerciciofinal.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import curso.java.ejerciciofinal.Ejercicio;
import curso.java.ejerciciofinal.entity.interfaces.Luchable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="guerreros")
public class Guerrero implements Luchable {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String tipo;
	private int puntos_ataque;
	private int puntos_defensa;
	private int puntos_vida;
	
	private static final Logger logger = LogManager.getLogger(Ejercicio.class);
	public Guerrero() {}
	public Guerrero(String nombre, String tipo, int puntos_ataque, int puntos_defensa, int puntos_vida) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.puntos_ataque = puntos_ataque;
		this.puntos_defensa = puntos_defensa;
		this.puntos_vida = puntos_vida;
	}
	/**
	 * Devuelve los puntos de ataque multiplicados por un valor aleatorio de 1 a 10
	 */
	public int atacar() {
		int danyo=this.getPuntos_ataque()*(int)(Math.random()*(10-1)+1);
		logger.debug(this.getNombre()+" ha generado "+danyo+" puntos de daño.");
		return danyo;
	}
	/**
	 * Recibe puntos de daño y los resta a los puntos de defensa del guerrero que se defiende multiplicado por un valor aleatorio entre 1 y 5.
	 * Si el resultado es mayor a 0, se le baja la vida al guerrero que se está defendiendo. De lo contrario, no se le baja la vida.
	 */
	public void defender(int daño) {

		int resultado=daño-this.getPuntos_defensa()*(int)(Math.random()*(5-1)+1);
		logger.debug(this.getNombre()+" intenta defenderse...");
		if (resultado > 0) {
			this.puntos_vida=this.puntos_vida-resultado;
			logger.debug(this.getNombre()+" sufre "+resultado+" puntos de daño. Le quedan "+this.getPuntos_vida()+" puntos de vida.");
		}else {
			logger.debug(this.getNombre()+" se ha defendido y no ha sufrido ningún daño.");
		}
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getPuntos_ataque() {
		return puntos_ataque;
	}
	public void setPuntos_ataque(int puntos_ataque) {
		this.puntos_ataque = puntos_ataque;
	}
	public int getPuntos_defensa() {
		return puntos_defensa;
	}
	public void setPuntos_defensa(int puntos_defensa) {
		this.puntos_defensa = puntos_defensa;
	}
	public int getPuntos_vida() {
		return puntos_vida;
	}
	public void setPuntos_vida(int puntos_vida) {
		this.puntos_vida = puntos_vida;
	}
	@Override
	public String toString() {
		return "Guerrero [id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", puntos_ataque=" + puntos_ataque
				+ ", puntos_defensa=" + puntos_defensa + ", puntos_vida=" + puntos_vida + "]";
	}

}
