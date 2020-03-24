import org.hibernate.*;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;


import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main {
    private static SessionFactory factory;


    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            configuration.addAnnotatedClass(Product.class);

            factory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void main(final String[] args) throws Exception {

        Main ME = new Main();

        /* Add few Product records in database */
        System.out.println("adding products");
        Integer productID1 = ME.addProduct("IPhone XS", 1200);
        Integer productID2 = ME.addProduct("OnePlus", 600);

        /* List down all the Products */
        System.out.println("list products");
        ME.listProducts();

        System.out.println("get products");
        ME.getProduct(1);
        ME.getProductQuery(1);

        /* Update Product's records */
        System.out.println("update products");
        ME.updateProduct(productID1, 5000);

        /* Delete an Product from the database */
        ME.deleteProduct(productID2);

        /* List down new list of the Products */
        ME.listProducts();
    }

    /* Method to CREATE an Product in the database */
    private Integer addProduct(String name, int price){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer productID = null;

        try {
            tx = session.beginTransaction();
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            productID = (Integer) session.save(product);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return productID;
    }

    /* Method to READ ONE product */
    private Product getProduct(Integer ProductID) {
        Session session = factory.openSession();
        Transaction tx = null;
        Product product = null;

        try {
             product = (Product) session.get(Product.class, ProductID);
             System.out.print("  Name: " + product.getName());
             System.out.println("  Price: " + product.getPrice());
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return product;
        }
    }

    private Product getProductQuery(Integer ProductID) {
        Session session = factory.openSession();
        Transaction tx = null;

        Product product = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Product P WHERE P.id = " + ProductID);
            List results = query.list();

            for (Iterator iterator = results.iterator(); iterator.hasNext();){
                product = (Product) iterator.next();
                System.out.print("  Name: " + product.getName());
                System.out.println("  Price: " + product.getPrice());

            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return product;
        }
    }

    /* Method to READ ALL the Products */
    private void listProducts(){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List products = session.createQuery("FROM Product").list();
            for (Iterator iterator = products.iterator(); iterator.hasNext();){
                Product product = (Product) iterator.next();
                System.out.print("  Name: " + product.getName());
                System.out.println("  Price: " + product.getPrice());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to UPDATE salary for an Product */
    private void updateProduct(Integer ProductID, int price){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Product product = (Product) session.get(Product.class, ProductID);
            product.setPrice( price );
            session.update(product);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to DELETE an Product from the records */
    public void deleteProduct(Integer ProductID){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Product product = (Product)session.get(Product.class, ProductID);
            session.delete(product);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}