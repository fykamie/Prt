package adatbazis;

import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author eszti
 */
public interface LekerdezesekIF<BuildPyramid> {
    public void insert(BuildPyramid obj);
    public void delete(BuildPyramid obj);
    public void delete(int ID);
    public void deleteAll();
    public BuildPyramid getByID(int ID);
    public List<BuildPyramid> getAll();
    public List<BuildPyramid> get(Predicate<BuildPyramid> condition);
    public void closeConnection();
}
