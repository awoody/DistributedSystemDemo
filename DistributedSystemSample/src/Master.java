import java.util.ArrayList;
import java.util.List;

import api.DistributedSystemFactory;

import rpc.AnnotatedObject;
import communication.Recipient;
import communication.iPortal;


public class Master extends Recipient implements iMaster, Runnable
{
	private List<iClient> connectedClients;
	private final iPortal portal;
	private final static int threadSleepTime = 3000;
	
	public static void main(String [] args)
	{
		//Threads a master off and starts it.
		Master t = new Master(); //get it?
		Thread th = new Thread(t);
		th.start();
	}
	
	public Master()
	{
		SampleConstants constants = new SampleConstants();
		portal = DistributedSystemFactory.newServer(10001, this, constants);
		connectedClients = new ArrayList<iClient>();
	}
	
	//Indicates that a new annotated object has connected to this
	//recipient (which means it is using a server as it's portal),
	//and provides the information needed about the new annotated object.
	@Override
	public void newObjectHasConnected(AnnotatedObject newObject)
	{
		//Add the new connection to the list of connected clients.
		connectedClients.add((iClient) newObject);
	}

	/**
	 * Returns a string representing the resource name of this 
	 * recipient's corresponding annotated object.  This MUST
	 * be the same as the string used to map the annotated object
	 * in the constants file.
	 */
	@Override
	public String getResourceName() 
	{
		// TODO Auto-generated method stub
		return "Master";
	}

	@Override
	public void run()
	{
		System.out.println("Master is running.");
		
		while(true)
		{
			long currentTime = System.currentTimeMillis();
			
			System.out.println(currentTime + "] Master broadcasting current time to clients.");
			
			for(int i = 0; i < connectedClients.size(); i++)
			{
				iClient client = connectedClients.get(i);
				client.receiveCurrentTimeFromMaster(currentTime);
			}
			
			try
			{
				Thread.sleep(threadSleepTime);
			}
			catch(InterruptedException e)
			{
				//NOP
			}
		}
		
	}

	@Override
	public double divideByFour(int value) 
	{
		System.out.println("Master dividing by four.");
		return (double) value / 4.0;
	}

}
