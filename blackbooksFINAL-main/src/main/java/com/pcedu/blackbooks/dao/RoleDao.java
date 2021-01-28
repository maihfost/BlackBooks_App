package com.pcedu.blackbooks.dao;

import com.pcedu.blackbooks.entities.Role;
import com.pcedu.blackbooks.entities.User;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("roleDao")
@Transactional
public class RoleDao extends AbstractDao<Integer, Role>{

    public Role findById(int id) {
        Role r = getByKey(id);
        if(r != null) {
            return r;
        }
        return null;
    }
    
    public List<Role> findAll() {
         Criteria criteria = createEntityCriteria();
         return(criteria.list());
    }

    public Role findByName(String name) {
        for (Role role : findAll()) {
            if(role.getName().equals(name)) return(role);
        }
        return(new Role());
    } 
   
    public List<Role> findAllByUser(User user){
        Criteria criteria = createEntityCriteria();
        criteria.createCriteria("userRoleList").add(Restrictions.eq("userId", user));
        return (criteria.list());
    } 
   
    public boolean save(Role role) {
        return(persist(role));
    }
    
    public boolean update(Role role) {
        if(findById(role.getId()) != null) return(save(role));
        return(false);
    }
    
    public boolean deleteRole(Role role) {
        if(role != null) {
            delete(role);
            return(true);
        }
        return(false);
    }
}
