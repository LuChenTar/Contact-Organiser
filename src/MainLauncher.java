import java.awt.*;
import java.io.IOException;

/**
 * Created by luchen on 6/03/15.
 */
public class MainLauncher {
// store the main() method.
// invoke the GUI for the program, (MainLauncher.java).

    public static void main(String[] args) throws IOException {
//      print log in information
        System.out.println("Hello.. Starting the program");
        System.out.println("Launching GUI ... ");

//		Launch The Contact GUI from the presentation layer
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
