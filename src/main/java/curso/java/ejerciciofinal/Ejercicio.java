package curso.java.ejerciciofinal;

import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import curso.java.ejerciciofinal.entity.Guerrero;
import curso.java.ejerciciofinal.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class Ejercicio {
	private static final Logger logger = LogManager.getLogger(Ejercicio.class);

	public static void main(String[] args) {
		// logger.debug("Creando guerreros e insertandolos en la BBDD.");
		EntityManager em = JpaUtil.getEntityManager();
		//Descomentar y cambiar <property name="hibernate.hbm2ddl.auto" value="update"/> a <property name="hibernate.hbm2ddl.auto" value="create"/> en persistence.xml para crear guerreros automáticamente.
//		Guerrero g1 = new Guerrero("Guerrero 1", "terrícola", 15, 10, 100);
//		Guerrero g2 = new Guerrero("Guerrero 2", "alienígena", 10, 15, 100);
//		em.getTransaction().begin();
//		em.persist(g1);
//		em.persist(g2);
//		em.getTransaction().commit();

		logger.debug("Se recogen los guerreros de la BBDD.");
		List<Guerrero> guerreros = em.createQuery("from Guerrero", Guerrero.class).getResultList();
		boolean turno = true;
		Guerrero ganador = null;
		Guerrero perdedor = null;
		logger.debug("Comienza la guerra.");
		do {
			logger.debug(guerreros.get(0).getNombre().toUpperCase()+" VS "+guerreros.get(1).getNombre().toUpperCase());
			do {
				if (turno) {
					logger.debug("Turno de " + guerreros.get(0).getNombre());
					guerreros.get(1).defender(guerreros.get(0).atacar());
					turno = false;
				} else {
					logger.debug("Turno de " + guerreros.get(1).getNombre());
					guerreros.get(0).defender(guerreros.get(1).atacar());
					turno = true;
				}
				if (guerreros.get(0).getPuntos_vida() > 0 && guerreros.get(1).getPuntos_vida() < 0) {
					ganador = guerreros.get(0);
					perdedor = guerreros.get(1);
					logger.debug(guerreros.get(1).getNombre() + " ha muerto.");
				} else if (guerreros.get(0).getPuntos_vida() < 0 && guerreros.get(1).getPuntos_vida() > 0) {
					ganador = guerreros.get(1);
					perdedor = guerreros.get(0);
					logger.debug(guerreros.get(0).getNombre() + " ha muerto.");
				}

			} while (guerreros.get(0).getPuntos_vida() > 0 && guerreros.get(1).getPuntos_vida() > 0);
			logger.debug("Ha ganado " + ganador.getNombre());
			guerreros.remove(perdedor);
			if(guerreros.size() > 1) {
				logger.debug("Todavía quedan guerreros. Nueva batalla.");
			}else {
				logger.debug("Ya no quedan guerreros.");
			}
		} while (guerreros.size() > 1);
		logger.debug("Fin de la partida.");

	}
}
