package dao;


import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import product.Product;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao{

    private final SessionFactory sessionFactory;

    public ProductDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long save(Product product) {
            Session session= sessionFactory.getCurrentSession();
            session.save(product);
            return product.getDbId();
    }

    @Override
    public Product getProductById(Long id) {
        return sessionFactory.getCurrentSession().get(Product.class, id, LockMode.NONE);
    }

    @Override
    public List getProductList() {
            Query query = sessionFactory.getCurrentSession().createQuery("from Product ");
            return query.getResultList();
    }

    @Override
    public Product update(Product product) {
        Session session= sessionFactory.getCurrentSession();
        session.saveOrUpdate(product);
        return product;
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Product product= (Product) session.load(Product.class, id);
        session.delete(product);
    }
}
