package jm.security.example.dao;

import jm.security.example.model.Role;
import jm.security.example.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUser() {
        return em.createQuery("from User").getResultList();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    @Transactional
    public void editUser(User user) {
        em.merge(user);
    }

    @Override
    @Transactional
    public void removeUser(Long id) {
        em.remove(em.find(User.class, id));
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User getUserByName(String name) {
        return em.createQuery("select u from User u where u.name = :name", User.class)
                .setParameter("name", name).getSingleResult();
    }

    @Override
    public Role getRoleByName(String name) {
        return em.createQuery("select u from User u where u.name = :name", Role.class)
                .setParameter("name", name).getSingleResult();
    }
}

