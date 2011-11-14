import rpc.AnnotatedObject;
import rpc.Synchronous;


public class MasterAO extends AnnotatedObject implements iMaster
{

	@Synchronous
	public double divideByFour(int value) 
	{
		//No need to implement in an annotated object, since the annotated
		//object is used by the network to create packages; the only important
		//thing is the parameters, which are shipped over the network to the
		//corresponding recipient, invoked in the corresponding method there,
		//and then returned, causing this method to appear as though it returns
		//a value to the user.
		return 0; //This will never actually be returned; this method is hijacked with method injection.
	}

}
