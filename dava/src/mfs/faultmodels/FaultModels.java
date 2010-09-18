package mfs.faultmodels;

import org.aspectj.lang.NoAspectBoundException;
import org.aspectj.lang.JoinPoint;
import java.io.PrintStream;

public class FaultModels
{
    public static final FaultModels abc$perSingletonInstance;
    private static Throwable abc$initFailureCause;

    public static FaultModels aspectOf() throws org.aspectj.lang.NoAspectBoundException
    {

        FaultModels theAspect;
        theAspect = abc$perSingletonInstance;

        if (theAspect != null)
        {
            return theAspect;
        }

        throw new NoAspectBoundException("mfs.faultmodels.FaultModels", abc$initFailureCause);
    }

    public static boolean hasAspect()
    {


        if (abc$perSingletonInstance == null)
        {
            return false;
        }

        return true;
    }

    static
    {

        FaultModels DavaTemp_abc$perSingletonInstance;
        DavaTemp_abc$perSingletonInstance = null;

        try
        {

            DavaTemp_abc$perSingletonInstance = new FaultModels();
            return;
        }
        catch (Throwable catchLocal$3)
        {
            abc$initFailureCause = catchLocal$3;
        }

        abc$perSingletonInstance = DavaTemp_abc$perSingletonInstance;
    }

    private static void abc$postClinit()
    {

        throw new RuntimeException("This method used to have a definition of a final variable. Dava inlined the definition into the static initializer");
    }

    public static final void inline$0$around$1(JoinPoint  $r0)
    {


        System.out.println($r0.getThis().toString());
        UserManager.shadow$6();
    }
}
