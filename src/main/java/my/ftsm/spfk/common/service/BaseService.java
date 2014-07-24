package my.ftsm.spfk.common.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;

/**
 * @author Norian
 */
public abstract class BaseService {

    @PersistenceContext(unitName = "spfkPU")
    protected EntityManager entityManager;

    @SuppressWarnings("unchecked")
    protected static <T> List<T> listAndCast(Criteria criteria) {
        return criteria.list();
    }

}
