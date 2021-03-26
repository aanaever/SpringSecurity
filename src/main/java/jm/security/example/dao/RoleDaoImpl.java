package jm.security.example.dao;

import jm.security.example.model.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Role> listRole() {
        return em.createQuery("from Role", Role.class).getResultList();
    }

    @Override
    public void addRole(Role role) {
        em.persist(role);
    }

    @Override
    public void editRole(Role role) {
        em.merge(role);
    }

    @Override
    public void removeRole(Long id) {
        em.remove(em.find(Role.class, id));
    }

    @Override
    public Role getRoleById(Long id) {
        TypedQuery<Role> query = em.createQuery("select u from Role u where u.id = :id", Role.class);
        return query.setParameter("id", id).getSingleResult();
    }

    @Override
    public Role getRoleByName(String name) {
        TypedQuery<Role> query = em.createQuery("select u from Role u where u.role =:role", Role.class);
        return query.setParameter("role", name).getSingleResult();
    }
}
