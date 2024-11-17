package kafka;


public interface ITaskProducer extends ITask{
	void send(String topic, String data);
}
