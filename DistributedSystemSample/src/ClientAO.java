import rpc.AnnotatedObject;
import rpc.Asynchronous;


public class ClientAO extends AnnotatedObject implements iClient
{

	@Asynchronous
	public void receiveCurrentTimeFromMaster(long currentTime) 
	{
		//No need to implement in an annotated object, since the annotated
		//object is used by the network to create packages; the only important
		//thing is the parameters, which are shipped over the network to the
		//corresponding recipient, invoked in the corresponding method there,
		//and then returned, causing this method to appear as though it returns
		//a value to the user.
	}

}
