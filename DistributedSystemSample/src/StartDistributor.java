import api.DistributedSystemFactory;


public class StartDistributor
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		SampleConstants constants = new SampleConstants();
		DistributedSystemFactory.startDistributor(constants);
	}

}
