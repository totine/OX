package akademia.ox;

import java.util.Locale;
import java.util.ResourceBundle;

public class OxGame {
    Locale currentLocale;
    ResourceBundle messages;

    OxGame(Locale locale) {
        currentLocale = locale;
        messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
    }


}
