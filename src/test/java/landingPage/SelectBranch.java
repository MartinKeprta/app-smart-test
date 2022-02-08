package landingPage;

import com.appSmart.LandingPage;
import com.appSmart.errors.AdressErrorMessages;
import org.testng.annotations.Test;

public class SelectBranch {

    String testBranch="Enjoy+Pizza+Bremen";

    @Test
    public void selectBranch(){
        new LandingPage(true)
                .chooseBranch("Enjoy+Pizza+Bremen");
    }
}
