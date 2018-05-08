/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adatbazis;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author eszti
 */
public class Lekerdezesek implements LekerdezesekIF<BuildPyramid>{
    protected EntityManagerFactory ef;
    protected EntityManager em;
    
    public Lekerdezesek(EntityManagerFactory emf) {
        ef= emf;
        em= ef.createEntityManager();
    }
    
    @Override
    public void insert(BuildPyramid obj) {
        em.getTransaction().begin();
            em.persist(obj);
        em.getTransaction().commit();
    }

    @Override
    public void delete(BuildPyramid obj) {
        em.getTransaction().begin();
            em.remove(obj);
        em.getTransaction().commit();
    }

    @Override
    public void delete(int ID) {
        BuildPyramid toDelete= this.getByID(ID);
        this.delete(toDelete);
    }

    @Override
    public void deleteAll() {
        List<BuildPyramid> toDelete= this.getAll();

        em.getTransaction().begin();
            toDelete.forEach(obj -> em.remove(obj));
        em.getTransaction().commit();
    }

    @Override
    public BuildPyramid getByID(int ID) {
        return this.em.find(BuildPyramid.class, ID);
    }

    @Override
    public List<BuildPyramid> getAll() {
        Query qu= em.createQuery("SELECT r FROM BuildPyramid r");
        return (List<BuildPyramid>) qu.getResultList();
    }

    @Override
    public List<BuildPyramid> get(Predicate<BuildPyramid> condition) {
        return this.getAll().stream().filter(condition).collect(Collectors.toList());
    }

    @Override
    public void closeConnection() {
        this.em.close();
        this.ef.close();
    }
    
}
