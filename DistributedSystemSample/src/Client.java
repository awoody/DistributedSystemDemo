import api.DistributedSystemFactory;
import rpc.AnnotatedObject;
import utilities.A;
import communication.Recipient;
import communication.iPortal;


public class Client extends Recipient implements iClient, Runnable
{
	private final iMaster master;
	private final iPortal networkPortal;
	private final static long threadSleepTime = 1000;
	
	public static void main(String [] args)
	{
		int clientsToStart = 50;
		
		for(int i=0; i < clientsToStart; i++)
		{
			Client c = new Client();
			Thread t = new Thread(c);
			t.start();
		}
	}
	
	public Client()
	{
		SampleConstants constants = new SampleConstants();
		networkPortal = DistributedSystemFactory.newClient(this, constants);
		master = (iMaster) networkPortal.makeNewConnection("Master");
	}
	
	
	@Override
	public void newObjectHasConnected(AnnotatedObject newObject) 
	{
		// The client won't receive incoming connections in this case, since it's
		// not using a server.
	}

	@Override
	public String getResourceName() 
	{
		return "Client";
	}


	@Override
	public void run() 
	{
		while(true)
		{
			int randomInt = A.randomIntFromZeroToBound(2000);
			
			double result = master.divideByFour(randomInt);
			
			System.out.println(randomInt + " divided by 4 is: " + result);
			
			try 
			{
				Thread.sleep(threadSleepTime);
			} 
			catch (InterruptedException e)
			{
				//NOP
			}
		}
	}

	@Override
	public void receiveCurrentTimeFromMaster(long currentTime) 
	{
		System.out.println("Client received time from master: " + currentTime);
	}

}
