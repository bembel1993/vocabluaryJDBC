import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Assambly {
    public static void showAssemly() throws IOException, ClassNotFoundException {
        String select;

        for (; ; ) {
            System.out.println("Enter \n1. to Word for Add, \n2. to show list the Word, \n" +
                    "3. to search needed Word \n4. Show add words in Data Base" +
                    "\n5. Search Words from DataBse\n enter 'q' for exit: ");
            BufferedReader choice = new BufferedReader(new InputStreamReader(System.in));
            select = choice.readLine();
            if (select.equals("1")) {

                BufferedReader enterRussWord = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter Russ: ");
                String russWord = enterRussWord.readLine();

                BufferedReader enterEngWord = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter English: ");
                String engWord = enterEngWord.readLine();

                BufferedReader enterDeWord = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter Deutsch: ");
                String deWord = enterDeWord.readLine();

                ActiveWord activeWord = new ActiveWord(russWord, engWord, deWord);
                System.out.println("---------------------------------");
                activeWord.addInputWord();
                activeWord.showAddWord();
                System.out.println("---------------------------------");
                //  activeWord.writeAddWordInFile();
                activeWord.addToDataBase();
                //   activeWord.showWriteAddWordInFile();
                System.out.println("---------------------------------");
            } else if (select.equals("q")) {
                System.out.println("exit");
                break;
            } else if (select.equals("2")) {
                ActiveWord.showWriteAddWordInFile();
            } else if (select.equals("3")) {
                ActiveWord.searchWordInAddFile();
            } else if (select.equals("4")) {
                ActiveWord.ShowAddWordsToDataBase();
            } else if (select.equals("5")) {
                ActiveWord.SearchWordsFromDataBase();
            }
        }
    }
}
