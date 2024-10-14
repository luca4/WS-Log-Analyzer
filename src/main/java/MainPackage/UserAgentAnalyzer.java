package MainPackage;

//Implements Singleton pattern. Manage the user agent analyzer library
public class UserAgentAnalyzer {

    private static nl.basjes.parse.useragent.UserAgentAnalyzer analyzer = null;

    private UserAgentAnalyzer()
    {
        analyzer = nl.basjes.parse.useragent.UserAgentAnalyzer
                .newBuilder()
                .hideMatcherLoadStats()
                .withField("AgentName")
                .withCache(10000)
                .build();
    }

    public static nl.basjes.parse.useragent.UserAgentAnalyzer getInstance()
    {
        if(analyzer == null)
            new UserAgentAnalyzer();

        return analyzer;
    }
}
