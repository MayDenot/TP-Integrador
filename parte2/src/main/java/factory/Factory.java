package factory;

import repository.DaoCarrera;
import repository.DaoEstudiante;
import repository.DaoEstudianteCarrera;

public abstract class Factory {
    public static final int MYSQL_JPA = 1;
    public static Factory getAbstractFactory(int whichFactory) {
        switch(whichFactory) {
            case MYSQL_JPA: {
                return MySQLFactory.getInstance();
            }
            default:
                return null;
        }
    }

    //Métodos abstractos para devolver DAOs
    public abstract DaoEstudiante getDaoEstudiante();
    public abstract DaoCarrera getDaoCarrera();
    public abstract DaoEstudianteCarrera getDaoEstudianteCarrera();


}
