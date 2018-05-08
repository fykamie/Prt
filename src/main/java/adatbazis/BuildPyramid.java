/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author eszti
 */
@Entity
@Table(name = "buildPyramid")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BuildPyramid.findAll", query = "SELECT b FROM BuildPyramid b")
    , @NamedQuery(name = "BuildPyramid.findByKockak", query = "SELECT b FROM BuildPyramid b WHERE b.kockak = :kockak")
    , @NamedQuery(name = "BuildPyramid.findByKihezTartozik", query = "SELECT b FROM BuildPyramid b WHERE b.kihezTartozik = :kihezTartozik")})
public class BuildPyramid implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Kockak")
    private Integer kockak;
    @Basic(optional = false)
    @Column(name = "KihezTartozik")
    private int kihezTartozik;

    public BuildPyramid() {
    }

    public BuildPyramid(Integer kockak) {
        this.kockak = kockak;
    }

    public BuildPyramid(Integer kockak, int kihezTartozik) {
        this.kockak = kockak;
        this.kihezTartozik = kihezTartozik;
    }

    public Integer getKockak() {
        return kockak;
    }

    public void setKockak(Integer kockak) {
        this.kockak = kockak;
    }

    public int getKihezTartozik() {
        return kihezTartozik;
    }

    public void setKihezTartozik(int kihezTartozik) {
        this.kihezTartozik = kihezTartozik;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kockak != null ? kockak.hashCode() : 0);
        return hash;
    }

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
