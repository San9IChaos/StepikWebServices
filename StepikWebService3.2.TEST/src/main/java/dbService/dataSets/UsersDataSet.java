package dbService.dataSets;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class UsersDataSet implements Serializable, Cloneable {
    private static final long serialVersionUID = -8706689714326132798L;
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "my_table_seq")
    @SequenceGenerator(name = "my_table_seq", sequenceName = "my_table_seq")
    @Column (name = "my_table_id", nullable = false)
    private long id;
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="myseq")
//    @SequenceGenerator(name="users_id_seq", sequenceName="users_id_seq", allocationSize=1)
//    private long id;

    @Column(name = "login", unique = true, updatable = false)
    private String login;

    @Column(name = "password", updatable = false)
    private String password;

    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet(long id,String login) {
        this.setId(id);
        this.setLogin(login);
    }

    public UsersDataSet(String login, String password) {
        this.setId(id);
        this.setLogin(login);
        this.setPassword(password);
    }

    public long getId() {
        return id;
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
    public void setId(long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }
}