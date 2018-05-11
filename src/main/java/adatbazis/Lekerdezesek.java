package adatbazis;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;


public class Lekerdezesek implements LekerdezesekIF<BuildPyramid>{
    protected EntityManagerFactory ef;
    protected EntityManager em;
    
    /**
     * Az osztály konstruktora.
     * 
     * @param emf 
     */
    public Lekerdezesek(EntityManagerFactory emf) {
        ef= emf;
        em= ef.createEntityManager();
    }
    
    /**
     * Adatbázisba illeszt be objektumot.
     * 
     * @param obj 
     */
    @Override
    public void insert(BuildPyramid obj) {
        em.getTransaction().begin();
            em.persist(obj);
        em.getTransaction().commit();
    }

    /**
     * Adatbázisból töröl objektumot.
     * 
     * @param obj 
     */
    @Override
    public void delete(BuildPyramid obj) {
        em.getTransaction().begin();
            em.remove(obj);
        em.getTransaction().commit();

    }

    /**
     * Adatbázisból töröl Id alapján.
     * 
     * @param ID 
     */
    @Override
    public void delete(int ID) {
        BuildPyramid toDelete= this.getByID(ID);
        this.delete(toDelete);

    }

    /**
     * Törli az adatbázis összes rekordját.
     * 
     */
    @Override
    public void deleteAll() {
        List<BuildPyramid> toDelete= this.getAll();

        em.getTransaction().begin();
            toDelete.forEach(obj -> em.remove(obj));
        em.getTransaction().commit();

    }

    /**
     * Megkeresi az átadott Id alapján a megfelelő rekordot és visszatér vele.
     * 
     * @param ID
     * @return 
     */
    @Override
    public BuildPyramid getByID(int ID) {
        return this.em.find(BuildPyramid.class, ID);
    }

    /**
     * Lekéri az adatbázis összes rekordját és visszatér velük egy listában.
     * 
     * @return 
     */
    @Override
    public List<BuildPyramid> getAll() {
        Query qu= em.createQuery("SELECT r FROM BuildPyramid r");
        return (List<BuildPyramid>) qu.getResultList();
    }

    /**
     * Megkeresi az adatbázisban azokat a rekordokat, melyek megfelelnek az átadott feltételnek és visszatér velük.
     * 
     * @param condition
     * @return 
     */
    @Override
    public List<BuildPyramid> get(Predicate<BuildPyramid> condition) {
        return this.getAll().stream().filter(condition).collect(Collectors.toList());
    }

    /**
     * Lezárja az adatbázis kommunikációját.
     */
    @Override
    public void closeConnection() {
        this.em.close();
        this.ef.close();
    }
    
}
