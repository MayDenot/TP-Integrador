package factory;

public abstract class Factory {
    public static final int MYSQL_JDBC = 1;
    public static Factory getAbstractFactory(int whichFactory) {
        switch(whichFactory) {
            case MYSQL_JDBC: {
                return MySQLDAOFactory.getInstance();
            }
            default:
                return null;
        }
    }


}
