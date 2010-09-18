package mfs.faultmodels;

import org.aspectj.lang.JoinPoint;
import org.aspectbench.runtime.reflect.Factory;
import java.io.PrintStream;
import org.aspectbench.eaj.runtime.reflect.EajFactory;
import org.aspectj.lang.JoinPoint$StaticPart;

public class UserManager
{
    public static int numberOfOnlineUsers;
    private String managerName;
    private static final JoinPoint$StaticPart SJP0$method_execution$initLibrary;

    public static void initLibrary()
    {

        JoinPoint adviceformal$22;
        adviceformal$22 = Factory.makeJP(SJP0$method_execution$initLibrary, null, null, new Object[0]);
        FaultModels.aspectOf();
        FaultModels.inline$0$around$1(adviceformal$22);
    }

    public String getManagerName()
    {


        return managerName;
    }

    public static void main(String[]  args)
    {


        System.out.println("UserManagers.main()");

        try
        {
            (new UserManager()).getManagerName();
            System.out.println("UserManager.main() 2");
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
        }
    }

    static
    {

        EajFactory $r1;
        $r1 = new EajFactory("mfs\\faultmodels\\UserManager.java", Class.forName("mfs.faultmodels.UserManager"));
        SJP0$method_execution$initLibrary = $r1.makeSJP("method-execution", $r1.makeMethodSig("9-initLibrary-mfs.faultmodels.UserManager----void-"), 15, 15, -1);
        numberOfOnlineUsers = 0;
    }

    public static final void shadow$6()
    {


        System.out.println("UserManagers.initLibrary()");
    }
}
