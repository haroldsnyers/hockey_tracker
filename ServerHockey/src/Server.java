import org.hibernate.*;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Server {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            configuration.addAnnotatedClass(Match.class);

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void main(final String[] args) throws Exception {

        // Example of a distant calculator
        int port = 9876;
        ServerSocket ssock = new ServerSocket(port);

        while (true) { // infinite loop
            System.out.println("Server is listening on port " + port);
            Socket comm = ssock.accept();
            System.out.println(comm);
            System.out.println("connection established");

            new Thread((Runnable) new ServerRunnable(comm)).start();

        }

        }

    /* Method to CREATE an Match in the database */
    public void addMatch(String homeTeam, String awayTeam, int scoreTeamHome, int scoreTeamAway, String date){
        System.out.println("adding matche");
        Session session = ourSessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Match Match = new Match();
            Match.setHomeTeam(homeTeam);
            Match.setAwayTeam(awayTeam);
            Match.setScorehometeam(scoreTeamHome);
            Match.setScoreawayteam(scoreTeamAway);
            Match.setDate(date);
            session.save(Match);
            tx.commit();
            System.out.println("added");
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to READ ONE Match */
    private Match getMatch(Integer MatchID) {
        Session session = ourSessionFactory.openSession();
        Transaction tx = null;
        Match Match = null;

        try {
            Match = (Match) session.get(Match.class, MatchID);
            System.out.print("  Home: " + Match.getHomeTeam());
            System.out.println("  Away: " + Match.getAwayTeam());
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return Match;
        }
    }

    private Match getMatchQuery(Integer MatchID) {
        Session session = ourSessionFactory.openSession();
        Transaction tx = null;

        Match Match = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Match P WHERE P.id = " + MatchID);
            List results = query.list();

            for (Iterator iterator = results.iterator(); iterator.hasNext();){
                Match = (Match) iterator.next();
                System.out.print("  Home: " + Match.getHomeTeam());
                System.out.println("  Away: " + Match.getAwayTeam());

            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return Match;
        }
    }

    /* Method to READ ALL the Matchs */
    public List<Match> listMatchs(){
        Session session = ourSessionFactory.openSession();
        Transaction tx = null;

        List<Match> Matchs = new ArrayList<>();

        try {
            tx = session.beginTransaction();
            Matchs = (List<Match>) session.createQuery("FROM Match").list();
            for (Iterator iterator = Matchs.iterator(); iterator.hasNext();){
                Match Match = (Match) iterator.next();
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return Matchs;
    }

//    /* Method to UPDATE salary for an Match */
//    private void updateMatch(Integer MatchID, int price){
//        Session session = factory.openSession();
//        Transaction tx = null;
//
//        try {
//            tx = session.beginTransaction();
//            Match Match = (Match) session.get(Match.class, MatchID);
//            Match.setPrice( price );
//            session.update(Match);
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx!=null) tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//    }
//
//    /* Method to DELETE an Match from the records */
//    public void deleteMatch(Integer MatchID){
//        Session session = factory.openSession();
//        Transaction tx = null;
//
//        try {
//            tx = session.beginTransaction();
//            Match Match = (Match)session.get(Match.class, MatchID);
//            session.delete(Match);
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx!=null) tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//    }
}