package npc.ntut.hw4_1;

/**
 * Created by Admin on 2018/3/25.
 */

public class MarriageSuggestion {
    public String getSuggestion(String strSex, int iAgeRange) {
        String strSug = "";
        if (strSex.equals("male")) {
            switch (iAgeRange) {
                case 1:
                    strSug = "還不急";
                    break;
                case 2:
                    strSug = "趕快結婚";
                    break;
                case 3:
                    strSug = "開始找對象";
                    break;
            }
        } else {
            switch (iAgeRange) {
                case 1:
                    strSug = "還不急";
                    break;
                case 2:
                    strSug = "趕快結婚";
                    break;
                case 3:
                    strSug = "開始找對象";
                    break;
            }
        }
        return strSug;
    }
}
