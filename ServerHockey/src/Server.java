import org.hibernate.*;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

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
    public void addMatch(String homeTeam, String awayTeam, int scoreTeamHome, int scoreTeamAway, String date,
                         Quarter quarter1, Quarter quarter2, Quarter quarter3, Quarter quarter4){
        System.out.println("adding matche");
        Session session = ourSessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Match match = new Match(homeTeam, awayTeam, scoreTeamHome, scoreTeamAway, date);;

            Set subQuarters = new HashSet<Quarter>();
            for (int q = 0; q < 4; q++) {
                Quarter quarter;
                if (q == 0) {
                    quarter = quarter1;
                } else if (q == 1) {
                    quarter = quarter2;
                } else if (q == 2) {
                    quarter = quarter3;
                } else {
                    quarter = quarter4;
                }
                quarter.setID_MATCH(match);
                subQuarters.add(quarter);
            }
            match.setSubQuarter(subQuarters);
            session.save(match);
            tx.commit();
            System.out.println("added");
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

//    private Quarter addQuarter(int quarter, int goalsHome, int goalsAway, int shotsHome, int shotsHomeMissed,
//                            int shotsHomeMissedKeeper, int shotsAway, int shotsAwayMissed, int shotsAwayMissedKeeper,
//                            int homeGreenCards, int homeYellowCards, int homeRedCards, int awayGreenCards,
//                            int awayYellowCards, int awayRedCards, int strokeConvertedHome, int strokeNotConvertedHome,
//                            int strokeConvertedAway, int strokeNotConvertedAway, int faultHomeBackstick, int faultHomeKick,
//                            int faultHomeUndercutting, int faultHomeStick, int faultHomeObstruction, int faultAwayBackstick,
//                            int faultAwayKick, int faultAwayUndercutting, int faultAwayStick, int faultAwayObstruction,
//                            int pcConvertedHome, int pcNotConvertedHome, int pcConvertedAway, int pcNotConvertedAway,
//                            int faultPosition25Home, int faultPosition50Home, int faultPosition75Home, int faultPosition100Home,
//                            int faultPosition25Away, int faultPosition50Away, int faultPosition75Away, int faultPosition100Away,
//                            int outsideHomeSide, int outsideHomeClearance, int outsideHomeCorner, int outsideAwaySide,
//                            int outsideAwayClearance, int outsideAwayCorner) {
//
//        final Quarter quarterMatch = new Quarter(quarter, goalsHome, goalsAway, shotsHome, shotsHomeMissed,
//                shotsHomeMissedKeeper, shotsAway, shotsAwayMissed, shotsAwayMissedKeeper,
//                homeGreenCards, homeYellowCards, homeRedCards, awayGreenCards,
//                awayYellowCards, awayRedCards, strokeConvertedHome, strokeNotConvertedHome,
//                strokeConvertedAway, strokeNotConvertedAway, faultHomeBackstick, faultHomeKick,
//                faultHomeUndercutting, faultHomeStick, faultHomeObstruction, faultAwayBackstick,
//                faultAwayKick, faultAwayUndercutting, faultAwayStick, faultAwayObstruction,
//                pcConvertedHome, pcNotConvertedHome, pcConvertedAway, pcNotConvertedAway,
//                faultPosition25Home, faultPosition50Home, faultPosition75Home, faultPosition100Home,
//                faultPosition25Away, faultPosition50Away, faultPosition75Away, faultPosition100Away,
//                outsideHomeSide, outsideHomeClearance, outsideHomeCorner, outsideAwaySide,
//                outsideAwayClearance, outsideAwayCorner);
//        return quarterMatch;
//    }

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