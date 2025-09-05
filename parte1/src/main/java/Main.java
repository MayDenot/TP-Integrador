import utils.HelperMySQL;

public class Main {
    public static void main(String[] args) throws Exception {
        HelperMySQL helperMySQL = new HelperMySQL();

        helperMySQL.dropTables();
        helperMySQL.createTables();
        helperMySQL.populateDB();
        helperMySQL.closeConnection();
    }
}