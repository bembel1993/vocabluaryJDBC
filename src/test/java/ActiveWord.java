
import java.io.*;
import java.sql.*;
import java.util.Arrays;

public class ActiveWord extends Word {

    public String russInputWord;
    public String deutschInputWord;
    public String englishInputWord;
    public static File writeWordInVocabulary = new File("Vocabulary.txt");
    public static char[] buf;

    public ActiveWord(String rusW, String engW, String deW) {
        super(rusW, engW, deW);
        russInputWord = rusW;
        deutschInputWord = deW;
        englishInputWord = engW;
    }

    public void addToDataBase() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/vocabluary",
                    "root", "root");
            System.out.println("Successfully connected");
            if (connection == null) {
                System.out.println("Not successfully connected");
                System.exit(0);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ///////////////////////////////////////////
       // Statement statement = null;
        try {
           // statement = connection.createStatement();
          /*  BufferedReader enterRussWord = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter Russ: ");
            String russWord = enterRussWord.readLine();

            BufferedReader enterEngWord = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter English: ");
            String engWord = enterEngWord.readLine();

            BufferedReader enterDeWord = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter Deutsch: ");
            String deWord = enterDeWord.readLine();*/
            ///////AD TO DATABASE/////////////////
            String sql = "INSERT INTO words(russ, eng, ger) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, russInputWord);
            preparedStatement.setString(2, englishInputWord);
            preparedStatement.setString(3, deutschInputWord);

            int rows = preparedStatement.executeUpdate();

            System.out.printf("Added %d object", rows);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void ShowAddWordsToDataBase() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/vocabluary",
                    "root", "root");
            System.out.println("Successfully connected");
            if (connection == null) {
                System.out.println("Not successfully connected");
                System.exit(0);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //////SHOW WORDS FROM DATABASE////////////////////////////
        Statement statement = null;
        try {
            statement = connection.createStatement();
            headerForTable();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM words");
            while(resultSet.next()){
                String russ = resultSet.getString(1);
                String eng = resultSet.getString(2);
                String ger = resultSet.getString(3);
                String ger1 = resultSet.getString(4);
                System.out.println(russ + "||" + eng + "||" + ger+"||"+ger1);
            }
        } catch (Exception exception){
            System.out.println("Not this word in Data Base");
        }
    }

    public static void headerForTable(){
        System.out.println("+--------+-------+-------+");
        System.out.println("|--Russ--|--Eng--|--Ger--|");
        System.out.println("+--------+-------+-------+");
    }

    public static void SearchWordsFromDataBase() throws ClassNotFoundException {
        String russ;
        String eng;
        String ger;

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/vocabluary",
                    "root", "root");
            System.out.println("Successfully connected");
            if (connection == null) {
                System.out.println("Not successfully connected");
                System.exit(0);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //////FIND WORDS FROM DATABASE////////////////////////////
        Statement statement = null;
        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM words");
            while(resultSet.next()){
                russ = resultSet.getString(1);
                eng = resultSet.getString(2);
                ger = resultSet.getString(3);
                BufferedReader enterWhichWord = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter any Word: ");
                String wordForSearch = enterWhichWord.readLine();
                if (russ == wordForSearch || eng == wordForSearch || ger == wordForSearch){
                System.out.println(russ + "||" + eng + "||" + ger);
                } else {
                    System.out.println("This word is absent in Data Base");
                    break;
                }
            }
        } catch (Exception exception){
            System.out.println("Not this word in Data Base");
        }
    }

        public void addInputWord() throws IOException {
        if (russInputWord == deutschInputWord || russInputWord == englishInputWord ||
                englishInputWord == russInputWord || englishInputWord == deutschInputWord ||
                deutschInputWord == russInputWord || deutschInputWord == englishInputWord) {
            System.out.println("Not correct input");
        } else {
            System.out.println("Word is Add");
            System.out.println("     -     ");
        }
    }

    public void showAddWord() {
        System.out.println("Russ: " + russInputWord);
        System.out.println("Eng: " + englishInputWord);
        System.out.println("De: " + deutschInputWord);
    }

    public void writeAddWordInFile() throws IOException {
        FileWriter fileWriter = new FileWriter(writeWordInVocabulary, true);
        BufferedWriter writeWordInFile = new BufferedWriter(fileWriter);
        System.out.println("Words is Write");
        System.out.println("      -       ");
        writeWordInFile.write(" Russ: " + russInputWord + "||" + "Eng: " + englishInputWord + "||" +
                "De: " + deutschInputWord + "\n");
        writeWordInFile.flush();
    }

    public static void showWriteAddWordInFile() throws IOException {
        FileReader fileReadAddToVocabulary = new FileReader(writeWordInVocabulary);
        buf = new char[10000];
        int c;// посимвольное чтение - by character reading
        try {
                    c = fileReadAddToVocabulary.read(buf);
                    buf = Arrays.copyOf(buf, c);
                System.out.print(buf);
                System.out.print("\n");
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }

    public static void searchWordInAddFile() throws IOException {
        FileReader fileReadAddToVocabulary = new FileReader(writeWordInVocabulary);
        buf = new char[10000];
        int c;// посимвольное чтение - by character reading
            c = fileReadAddToVocabulary.read(buf);
            buf = Arrays.copyOf(buf, c);
        BufferedReader searchWord = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the required Word: ");
        String neededSearchWord = searchWord.readLine();
        //resultSearchWord.read
        char[] forEqual = neededSearchWord.toCharArray();

        if (forEqual.equals(buf)) {
            forEqual = buf;
            String arrayToString = Arrays.toString(buf);
            System.out.println("Show searching Word: ");
            System.out.println(writeWordInVocabulary+"||"+forEqual);
        } else {
            System.out.println("Word not found.");
        }
    }
}

