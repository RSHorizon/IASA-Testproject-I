package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleView {
    // Author: Robin Steinwarz
    private static BufferedReader reader;

    public ConsoleView(){
        reader = new BufferedReader( new InputStreamReader(System.in ) );
    }

    public String readLine(){
        String line = null;

        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

        return line;
    }
}
