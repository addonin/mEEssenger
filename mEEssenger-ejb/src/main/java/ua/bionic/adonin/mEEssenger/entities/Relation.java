package ua.bionic.adonin.mEEssenger.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author adonin
 */
@Entity
@Table(name = "Relations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Relation.findAll", query = "SELECT r FROM Relation r"),
    @NamedQuery(name = "Relation.findByRelationId", query = "SELECT r FROM Relation r WHERE r.relationId = :relationId"),
    @NamedQuery(name = "Relation.findByFromId", query = "SELECT r FROM Relation r WHERE r.fromId = :fromId"),
    @NamedQuery(name = "Relation.findByToId", query = "SELECT r FROM Relation r WHERE r.toId = :toId"),
    @NamedQuery(name = "Relation.findRelation", query = "SELECT r FROM Relation r WHERE r.fromId = :fromId AND r.toId = :toId")})
public class Relation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "relation_id")
    private Integer relationId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "from_id")
    private int fromId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "to_id")
    private int toId;

    public Relation() {
    }

    public Relation(Integer relationId) {
        this.relationId = relationId;
    }

    public Relation(Integer relationId, int fromId, int toId) {
        this.relationId = relationId;
        this.fromId = fromId;
        this.toId = toId;
    }
    
    public Relation(int fromId, int toId) {
        this.fromId = fromId;
        this.toId = toId;
    }

    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }
    
    @Override
    public int hashCode() {
            int result = 17;
            result = result * 31 + fromId; 
            result = result * 31 + toId;
            return result;
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
                return true;
        }
        if (that == null) {
                return false;
        }		
        if (!(that instanceof Relation)) {
                return false;
        }
        Relation castedThat = (Relation) that;
        return (fromId == castedThat.fromId) &&
                (toId == castedThat.toId);
    }    

    @Override
    public String toString() {
        return "ua.bionic.socialnetwork.Relation[ relationId=" + relationId + " ]";
    }
    
}
