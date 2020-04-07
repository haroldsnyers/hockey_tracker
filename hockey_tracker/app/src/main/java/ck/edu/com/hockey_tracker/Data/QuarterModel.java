package ck.edu.com.hockey_tracker.Data;

public class QuarterModel {
    private int ID;

    private int quarterNumber;

    private int goalsHome;
    private int goalsAway;

    private int shotsHome;
    private int shotsHomeMissed;
    private int shotsHomeMissedKeeper;
    private int shotsAway;
    private int shotsAwayMissed;
    private int shotsAwayMissedKeeper;

    private int homeGreenCards;
    private int homeYellowCards;
    private int homeRedCards;
    private int awayGreenCards;
    private int awayYellowCards;
    private int awayRedCards;

    private int strokeConvertedHome;
    private int strokeNotConvertedHome;
    private int strokeConvertedAway;
    private int strokeNotConvertedAway;

    private int faultHomeBackstick;
    private int faultHomeKick;
    private int faultHomeUndercutting;
    private int faultHomeStick;
    private int faultHomeObstruction;
    private int faultAwayBackstick;
    private int faultAwayKick;
    private int faultAwayUndercutting;
    private int faultAwayStick;
    private int faultAwayObstruction;

    private int pcConvertedHome;
    private int pcNotConvertedHome;
    private int pcConvertedAway;
    private int pcNotConvertedAway;

    private int faultPosition25Home;
    private int faultPosition50Home;
    private int faultPosition75Home;
    private int faultPosition100Home;
    private int faultPosition25Away;
    private int faultPosition50Away;
    private int faultPosition75Away;
    private int faultPosition100Away;

    private int outsideHomeSide;
    private int outsideHomeClearance;
    private int outsideHomeCorner;
    private int outsideAwaySide;
    private int outsideAwayClearance;
    private int outsideAwayCorner;

    private int ID_MATCH;

    public QuarterModel() {}

    public QuarterModel(int quarter, int goalsHome, int goalsAway, int shotsHome, int shotsHomeMissed,
                   int shotsHomeMissedKeeper, int shotsAway, int shotsAwayMissed, int shotsAwayMissedKeeper,
                   int homeGreenCards, int homeYellowCards, int homeRedCards, int awayGreenCards,
                   int awayYellowCards, int awayRedCards, int strokeConvertedHome, int strokeNotConvertedHome,
                   int strokeConvertedAway, int strokeNotConvertedAway, int faultHomeBackstick, int faultHomeKick,
                   int faultHomeUndercutting, int faultHomeStick, int faultHomeObstruction, int faultAwayBackstick,
                   int faultAwayKick, int faultAwayUndercutting, int faultAwayStick, int faultAwayObstruction,
                   int pcConvertedHome, int pcNotConvertedHome, int pcConvertedAway, int pcNotConvertedAway,
                   int faultPosition25Home, int faultPosition50Home, int faultPosition75Home, int faultPosition100Home,
                   int faultPosition25Away, int faultPosition50Away, int faultPosition75Away, int faultPosition100Away,
                   int outsideHomeSide, int outsideHomeClearance, int outsideHomeCorner, int outsideAwaySide,
                   int outsideAwayClearance, int outsideAwayCorner) {
        this.quarterNumber = quarter;
        this.goalsHome = goalsHome;
        this.goalsAway = goalsAway;
        this.shotsHome = shotsHome;
        this.shotsHomeMissed = shotsHomeMissed;
        this.shotsHomeMissedKeeper = shotsHomeMissedKeeper;
        this.shotsAway = shotsAway;
        this.shotsAwayMissed = shotsAwayMissed;
        this.shotsAwayMissedKeeper = shotsAwayMissedKeeper;
        this.homeGreenCards = homeGreenCards;
        this.homeYellowCards = homeYellowCards;
        this.homeRedCards = homeRedCards;
        this.awayGreenCards = awayGreenCards;
        this.awayYellowCards = awayYellowCards;
        this.awayRedCards = awayRedCards;
        this.strokeConvertedHome = strokeConvertedHome;
        this.strokeNotConvertedHome = strokeNotConvertedHome;
        this.strokeConvertedAway = strokeConvertedAway;
        this.strokeNotConvertedAway = strokeNotConvertedAway;
        this.faultHomeBackstick = faultHomeBackstick;
        this.faultHomeKick = faultHomeKick;
        this.faultHomeUndercutting = faultHomeUndercutting;
        this.faultHomeStick = faultHomeStick;
        this.faultHomeObstruction = faultHomeObstruction;
        this.faultAwayBackstick = faultAwayBackstick;
        this.faultAwayKick = faultAwayKick;
        this.faultAwayUndercutting = faultAwayUndercutting;
        this.faultAwayStick = faultAwayStick;
        this.faultAwayObstruction = faultAwayObstruction;
        this.pcConvertedHome = pcConvertedHome;
        this.pcNotConvertedHome = pcNotConvertedHome;
        this.pcConvertedAway = pcConvertedAway;
        this.pcNotConvertedAway = pcNotConvertedAway;
        this.faultPosition25Home = faultPosition25Home;
        this.faultPosition50Home = faultPosition50Home;
        this.faultPosition75Home = faultPosition75Home;
        this.faultPosition100Home = faultPosition100Home;
        this.faultPosition25Away = faultPosition25Away;
        this.faultPosition50Away = faultPosition50Away;
        this.faultPosition75Away = faultPosition75Away;
        this.faultPosition100Away = faultPosition100Away;
        this.outsideHomeSide = outsideHomeSide;
        this.outsideHomeClearance = outsideHomeClearance;
        this.outsideHomeCorner = outsideHomeCorner;
        this.outsideAwaySide = outsideAwaySide;
        this.outsideAwayClearance = outsideAwayClearance;
        this.outsideAwayCorner = outsideAwayCorner;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getGoalsHome() {
        return goalsHome;
    }

    public void setGoalsHome(int goalsHome) {
        this.goalsHome = goalsHome;
    }

    public int getGoalsAway() {
        return goalsAway;
    }

    public void setGoalsAway(int goalsAway) {
        this.goalsAway = goalsAway;
    }

    public int getShotsHome() {
        return shotsHome;
    }

    public void setShotsHome(int shotsHome) {
        this.shotsHome = shotsHome;
    }

    public int getShotsHomeMissed() {
        return shotsHomeMissed;
    }

    public void setShotsHomeMissed(int shotsHomeMissed) {
        this.shotsHomeMissed = shotsHomeMissed;
    }

    public int getShotsHomeMissedKeeper() {
        return shotsHomeMissedKeeper;
    }

    public void setShotsHomeMissedKeeper(int shotsHomeMissedKeeper) {
        this.shotsHomeMissedKeeper = shotsHomeMissedKeeper;
    }

    public int getShotsAway() {
        return shotsAway;
    }

    public void setShotsAway(int shotsAway) {
        this.shotsAway = shotsAway;
    }

    public int getShotsAwayMissed() {
        return shotsAwayMissed;
    }

    public void setShotsAwayMissed(int shotsAwayMissed) {
        this.shotsAwayMissed = shotsAwayMissed;
    }

    public int getShotsAwayMissedKeeper() {
        return shotsAwayMissedKeeper;
    }

    public void setShotsAwayMissedKeeper(int shotsAwayMissedKeeper) {
        this.shotsAwayMissedKeeper = shotsAwayMissedKeeper;
    }

    public int getHomeGreenCards() {
        return homeGreenCards;
    }

    public void setHomeGreenCards(int homeGreenCards) {
        this.homeGreenCards = homeGreenCards;
    }

    public int getHomeYellowCards() {
        return homeYellowCards;
    }

    public void setHomeYellowCards(int homeYellowCards) {
        this.homeYellowCards = homeYellowCards;
    }

    public int getHomeRedCards() {
        return homeRedCards;
    }

    public void setHomeRedCards(int homeRedCards) {
        this.homeRedCards = homeRedCards;
    }

    public int getAwayGreenCards() {
        return awayGreenCards;
    }

    public void setAwayGreenCards(int awayGreenCards) {
        this.awayGreenCards = awayGreenCards;
    }

    public int getAwayYellowCards() {
        return awayYellowCards;
    }

    public void setAwayYellowCards(int awayYellowCards) {
        this.awayYellowCards = awayYellowCards;
    }

    public int getAwayRedCards() {
        return awayRedCards;
    }

    public void setAwayRedCards(int awayRedCards) {
        this.awayRedCards = awayRedCards;
    }

    public int getStrokeConvertedHome() {
        return strokeConvertedHome;
    }

    public void setStrokeConvertedHome(int strokeConvertedHome) {
        this.strokeConvertedHome = strokeConvertedHome;
    }

    public int getStrokeNotConvertedHome() {
        return strokeNotConvertedHome;
    }

    public void setStrokeNotConvertedHome(int strokeNotConvertedHome) {
        this.strokeNotConvertedHome = strokeNotConvertedHome;
    }

    public int getStrokeConvertedAway() {
        return strokeConvertedAway;
    }

    public void setStrokeConvertedAway(int strokeConvertedAway) {
        this.strokeConvertedAway = strokeConvertedAway;
    }

    public int getStrokeNotConvertedAway() {
        return strokeNotConvertedAway;
    }

    public void setStrokeNotConvertedAway(int strokeNotConvertedAway) {
        this.strokeNotConvertedAway = strokeNotConvertedAway;
    }

    public int getFaultHomeBackstick() {
        return faultHomeBackstick;
    }

    public void setFaultHomeBackstick(int faultHomeBackstick) {
        this.faultHomeBackstick = faultHomeBackstick;
    }

    public int getFaultHomeKick() {
        return faultHomeKick;
    }

    public void setFaultHomeKick(int faultHomeKick) {
        this.faultHomeKick = faultHomeKick;
    }

    public int getFaultHomeUndercutting() {
        return faultHomeUndercutting;
    }

    public void setFaultHomeUndercutting(int faultHomeUndercutting) {
        this.faultHomeUndercutting = faultHomeUndercutting;
    }

    public int getFaultHomeStick() {
        return faultHomeStick;
    }

    public void setFaultHomeStick(int faultHomeStick) {
        this.faultHomeStick = faultHomeStick;
    }

    public int getFaultHomeObstruction() {
        return faultHomeObstruction;
    }

    public void setFaultHomeObstruction(int faultHomeObstruction) {
        this.faultHomeObstruction = faultHomeObstruction;
    }

    public int getFaultAwayBackstick() {
        return faultAwayBackstick;
    }

    public void setFaultAwayBackstick(int faultAwayBackstick) {
        this.faultAwayBackstick = faultAwayBackstick;
    }

    public int getFaultAwayKick() {
        return faultAwayKick;
    }

    public void setFaultAwayKick(int faultAwayKick) {
        this.faultAwayKick = faultAwayKick;
    }

    public int getFaultAwayUndercutting() {
        return faultAwayUndercutting;
    }

    public void setFaultAwayUndercutting(int faultAwayUndercutting) {
        this.faultAwayUndercutting = faultAwayUndercutting;
    }

    public int getFaultAwayStick() {
        return faultAwayStick;
    }

    public void setFaultAwayStick(int faultAwayStick) {
        this.faultAwayStick = faultAwayStick;
    }

    public int getFaultAwayObstruction() {
        return faultAwayObstruction;
    }

    public void setFaultAwayObstruction(int faultAwayObstruction) {
        this.faultAwayObstruction = faultAwayObstruction;
    }

    public int getPcConvertedHome() {
        return pcConvertedHome;
    }

    public void setPcConvertedHome(int pcConvertedHome) {
        this.pcConvertedHome = pcConvertedHome;
    }

    public int getPcNotConvertedHome() {
        return pcNotConvertedHome;
    }

    public void setPcNotConvertedHome(int pcNotConvertedHome) {
        this.pcNotConvertedHome = pcNotConvertedHome;
    }

    public int getPcConvertedAway() {
        return pcConvertedAway;
    }

    public void setPcConvertedAway(int pcConvertedAway) {
        this.pcConvertedAway = pcConvertedAway;
    }

    public int getPcNotConvertedAway() {
        return pcNotConvertedAway;
    }

    public void setPcNotConvertedAway(int pcNotConvertedAway) {
        this.pcNotConvertedAway = pcNotConvertedAway;
    }

    public int getFaultPosition25Home() {
        return faultPosition25Home;
    }

    public void setFaultPosition25Home(int faultPosition25Home) {
        this.faultPosition25Home = faultPosition25Home;
    }

    public int getFaultPosition50Home() {
        return faultPosition50Home;
    }

    public void setFaultPosition50Home(int faultPosition50Home) {
        this.faultPosition50Home = faultPosition50Home;
    }

    public int getFaultPosition75Home() {
        return faultPosition75Home;
    }

    public void setFaultPosition75Home(int faultPosition75Home) {
        this.faultPosition75Home = faultPosition75Home;
    }

    public int getFaultPosition100Home() {
        return faultPosition100Home;
    }

    public void setFaultPosition100Home(int faultPosition100Home) {
        this.faultPosition100Home = faultPosition100Home;
    }

    public int getFaultPosition25Away() {
        return faultPosition25Away;
    }

    public void setFaultPosition25Away(int faultPosition25Away) {
        this.faultPosition25Away = faultPosition25Away;
    }

    public int getFaultPosition50Away() {
        return faultPosition50Away;
    }

    public void setFaultPosition50Away(int faultPosition50Away) {
        this.faultPosition50Away = faultPosition50Away;
    }

    public int getFaultPosition75Away() {
        return faultPosition75Away;
    }

    public void setFaultPosition75Away(int faultPosition75Away) {
        this.faultPosition75Away = faultPosition75Away;
    }

    public int getFaultPosition100Away() {
        return faultPosition100Away;
    }

    public void setFaultPosition100Away(int faultPosition100Away) {
        this.faultPosition100Away = faultPosition100Away;
    }

    public int getOutsideHomeSide() {
        return outsideHomeSide;
    }

    public void setOutsideHomeSide(int outsideHomeSide) {
        this.outsideHomeSide = outsideHomeSide;
    }

    public int getOutsideHomeClearance() {
        return outsideHomeClearance;
    }

    public void setOutsideHomeClearance(int outsideHomeClearance) {
        this.outsideHomeClearance = outsideHomeClearance;
    }

    public int getOutsideHomeCorner() {
        return outsideHomeCorner;
    }

    public void setOutsideHomeCorner(int outsideHomeCorner) {
        this.outsideHomeCorner = outsideHomeCorner;
    }

    public int getOutsideAwaySide() {
        return outsideAwaySide;
    }

    public void setOutsideAwaySide(int outsideAwaySide) {
        this.outsideAwaySide = outsideAwaySide;
    }

    public int getOutsideAwayClearance() {
        return outsideAwayClearance;
    }

    public void setOutsideAwayClearance(int outsideAwayClearance) {
        this.outsideAwayClearance = outsideAwayClearance;
    }

    public int getOutsideAwayCorner() {
        return outsideAwayCorner;
    }

    public void setOutsideAwayCorner(int outsideAwayCorner) {
        this.outsideAwayCorner = outsideAwayCorner;
    }

    public int getID_MATCH() {
        return ID_MATCH;
    }

    public void setID_MATCH(int ID_MATCH) {
        this.ID_MATCH = ID_MATCH;
    }

    public int getQuarterNumber() {
        return quarterNumber;
    }

    public void setQuarterNumber(int quarterNumber) {
        this.quarterNumber = quarterNumber;
    }
}
