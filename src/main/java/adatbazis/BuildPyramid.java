package adatbazis;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "buildPyramid")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BuildPyramid.findAll", query = "SELECT b FROM BuildPyramid b")
    , @NamedQuery(name = "BuildPyramid.findByKockak", query = "SELECT b FROM BuildPyramid b WHERE b.kockak = :kockak")
    , @NamedQuery(name = "BuildPyramid.findByKihezTartozik", query = "SELECT b FROM BuildPyramid b WHERE b.kihezTartozik = :kihezTartozik")})
/**
 * BuildPyramid reprezentál egy egy entitást a JPA-nak.
 */
public class BuildPyramid implements Serializable {

    private static final long serialVersionUID = 1L;
 
    /**
     * A játék kockáit reprezentáló Id.
     */
    @Id
    @Basic(optional = false)
    @Column(name = "Kockak")
    private Integer kockak;
    
    /**
     * A játék kockáinak tulajdonosát reprezentáló szám. 
     *
     */
    @Basic(optional = false)
    @Column(name = "KihezTartozik")
    private int kihezTartozik;
    
    /**
     * Az osztály konstruktora.
     */
    public BuildPyramid() {
    }

    /**
     * Az osztály konstrktora.
     * 
     * @param kockak 
     */
    public BuildPyramid(Integer kockak) {
        this.kockak = kockak;
    }

    /**
     * Az osztály konstruktora.
     * 
     * @param kockak
     * @param kihezTartozik 
     */
    public BuildPyramid(Integer kockak, int kihezTartozik) {
        this.kockak = kockak;
        this.kihezTartozik = kihezTartozik;
    }

    /**
     * Az Id gettere.
     * 
     * @return 
     */
    public Integer getKockak() {
        return kockak;
    }

    /**
     * Az Id settere.
     * 
     * @param kockak 
     */
    public void setKockak(Integer kockak) {
        this.kockak = kockak;
    }

    /**
     * <code>kihezTartozik</code> gettere.
     * 
     * @return 
     */
    public int getKihezTartozik() {
        return kihezTartozik;
    }

    /**
     * <code>kihezTartozik</code> settere.
     * 
     * @param kihezTartozik
     */
    public void setKihezTartozik(int kihezTartozik) {
        this.kihezTartozik = kihezTartozik;
    }

    /**
     * Az osztály tartalmát egydarab 32 bites számmá alakítja.
     * 
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kockak != null ? kockak.hashCode() : 0);
        return hash;
    }

    /**
     * Összehasonlítja a paraméterül az osztálytipusú objektumot és az osztálytipusú aktuális objektum.
     * 
     * @param object
     * @return 
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BuildPyramid)) {
            return false;
        }
        BuildPyramid other = (BuildPyramid) object;
        if ((this.kockak == null && other.kockak != null) || (this.kockak != null && !this.kockak.equals(other.kockak))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "adatbazis.BuildPyramid[ kockak=" + kockak + " ]";
    }
    
}
