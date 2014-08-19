package ua.bionic.adonin.mEEssenger.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author adonin
 */
@Entity
@Table(name = "Users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByFirstname", query = "SELECT u FROM User u WHERE u.firstname = :firstname"),
    @NamedQuery(name = "User.findByLastname", query = "SELECT u FROM User u WHERE u.lastname = :lastname"),
    @NamedQuery(name = "User.findByActivity", query = "SELECT u FROM User u WHERE u.activity = :activity"),
    @NamedQuery(name = "User.findByIsBlocked", query = "SELECT u FROM User u WHERE u.isBlocked = :isBlocked"),
    @NamedQuery(name = "User.findByInfo", query = "SELECT u FROM User u WHERE u.info = :info"),
    @NamedQuery(name = "User.findByPhoto", query = "SELECT u FROM User u WHERE u.photo = :photo"),
    @NamedQuery(name = "User.findUsersByLastname", query = "SELECT u FROM User u WHERE u.lastname LIKE :lastname")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "password")
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "firstname")
    private String firstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "lastname")
    private String lastname;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "activity")
    private boolean activity;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "is_blocked")
    private boolean isBlocked;
    @Basic(optional = false)
    @NotNull
    @Size(min = 0, max = 1000)
    @Column(name = "info")
    private String info;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "photo")
    private String photo;
    @JoinColumn(name = "type_id", referencedColumnName = "type_id")
    @ManyToOne(optional = false)
    private UserType typeId;

    public User() {
    }

    public User(Integer userId) {
        this.userId = userId;
    }

    public User(Integer userId, String username, String password, String email, String firstname, String lastname, boolean activity, boolean isBlocked, String info, String photo) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.activity = activity;
        this.isBlocked = isBlocked;
        this.info = info;
        this.photo = photo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean getActivity() {
        return activity;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
    }

    public boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public UserType getTypeId() {
        return typeId;
    }

    public void setTypeId(UserType typeId) {
        this.typeId = typeId;
    }
    
    @Override
    public int hashCode() {
        int result = 17;
        result = result * 31 + userId;
        result = result * 31 + (username == null ? 0 : username.hashCode());
        result = result * 31 + (password == null ? 0 : password.hashCode());
        result = result * 31 + (email == null ? 0 : email.hashCode());		
        result = result * 31 + (firstname == null ? 0 : firstname.hashCode());
        result = result * 31 + (lastname == null ? 0 : lastname.hashCode());
        result = result * 31 + (activity ? 1 : 0);
        result = result * 31 + (info == null ? 0 : info.hashCode());
        result = result * 31 + (photo == null ? 0 : photo.hashCode());
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
        if (!(that instanceof User)) {
                return false;
        }
        User castedThat = (User) that;
        return (userId.equals(castedThat.userId)) &&
            (username.equals(castedThat.username)) &&
            (password.equals(castedThat.password)) &&
            (email.equals(castedThat.email)) &&            
            (firstname.equals(castedThat.firstname)) &&
            (lastname.equals(castedThat.lastname)) &&
            (activity == castedThat.activity) &&
            (info.equals(castedThat.info)) &&
            (photo.equals(castedThat.photo));
    }

    @Override
    public String toString() {
        return "ua.bionic.socialnetwork.User[ userId=" + userId + " ]";
    }
    
}
