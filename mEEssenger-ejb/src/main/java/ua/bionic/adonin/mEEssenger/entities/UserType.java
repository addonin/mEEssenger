package ua.bionic.adonin.mEEssenger.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author adonin
 */
@Entity
@Table(name = "UserTypes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserType.findAll", query = "SELECT u FROM UserType u"),
    @NamedQuery(name = "UserType.findByTypeId", query = "SELECT u FROM UserType u WHERE u.typeId = :typeId"),
    @NamedQuery(name = "UserType.findByType", query = "SELECT u FROM UserType u WHERE u.type = :type")})
public class UserType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "type_id")
    private Integer typeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "type")
    private String type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeId")
    private Collection<User> userCollection;

    public UserType() {
    }

    public UserType(Integer typeId) {
        this.typeId = typeId;
    }

    public UserType(Integer typeId, String type) {
        this.typeId = typeId;
        this.type = type;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }
    
    @Override
    public int hashCode() {
            int result = 17;
            result = result * 31 + typeId; 
            result = result * 31 + (type == null ? 0 : type.hashCode());
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
        if (!(that instanceof UserType)) {
                return false;
        }
        UserType castedThat = (UserType) that;
        return (typeId.equals(castedThat.typeId)) && (type.equals(castedThat.type));
    }
    
    @Override
    public String toString() {
        return type;
    }
    
}
