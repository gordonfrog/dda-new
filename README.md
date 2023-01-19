## RabbitMQ SpringBoot and SSE Example

Example of how to create reader and writer SpringBoot applications implementing rabbitMQ and SSE(Server-side-events).  

Dependencies:


```xml

<dependencies>
     <dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
     </dependency>
     <dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web-services</artifactId>
     </dependency>
     <dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-amqp</artifactId>
     </dependency>
     <dependency>
	<groupId>com.sh</groupId>
	<artifactId>rabbitBeanMessages</artifactId>
	<version>0.0.1</version>
     </dependency>
</dependencies>

```

We have 3 modules:
* [rabbit Bean Messages](#rabbit-bean-messages) - Common Beans shared  between both modules
* [rabbit Publisher](#rabbit-publisher) - write in rabbit queues
* [rabbit Reader](#rabbit-reader)  - read the rabbit queues and SSE demo

## startup rabbit
```
brew update
brew install rabbitmq
ls /usr/local/sbin/rabbitmq-server
brew services start rabbitmq
http://localhost:15672/    guest/guest

or 

create a docker-compose.yml

rabbitmq:
  image: rabbitmq:management
  ports:
    - "5672:5672"
    - "15672:15672"

docker-compose up

OR

https://www.onlinetutorialspoint.com/windows/how-to-install-rabbitmq-on-windows-10.html
```

## rabbit Bean Messages

This module have 2 POJO Beans,for customers and for shops.  SpringBoot send and receive this type of objects.  

```java

public class CustomerMsg{
	
	private String custName;
	private String custOccupation;
	private String description;
	private String type;
	
	public CustomerMsg() {
		this.type = "CUSTOMER";
	}
	
	public CustomerMsg(String custName, String custOccupation, String description) {
		super();
		this.custName = custName;
		this.custOccupation = custOccupation;
		this.description = description;
		this.type = "CUSTOMER";
	}

	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustOccupation() {
		return custOccupation;
	}
	public void setCustOccupation(String custOccupation) {
		this.custOccupation = custOccupation;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "CustomerMsg [custName=" + custName + ", custOccupation=" + custOccupation + ", description="
				+ description + ", type=" + type + "]";
	}

}

public class ShopMsg {

	private String shopName;

	private String city;

	private int sales;



	public ShopMsg() {
		super();

	}
	public ShopMsg(String shopName, String city, int sales) {
		super();
		this.shopName = shopName;
		this.city = city;
		this.sales = sales;

	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getSales() {
		return sales;
	}
	public void setSales(int sales) {
		this.sales = sales;
	}
}
```
## rabbitShop (The rabbit Publisher)

This module has the functionality of publish messages onto rabbitMQ.  
When the RabbitController is hit a message is sent via the MsgProducer onto rabbitMQ.
The rabbitCustomer (The rabbit Reciever) receives this message.

Project structure:

```
│   pom.xml
├───src
    ├───main
    ├───java
    │   └───com
    │       └───sh
    │           ├───app
    │           │       AppRabbitPublisher.java
    │           │
    │           ├───ctrl
    │           │       RabbitController.java
    │           │
    │           └───producers
    │                   MsgProducer.java
    │
    └───resources
            application.properties

```


Look the configuration.

With this we declare a queue,a topic exchange and a binder of both:

```java

	@Bean(name ="queueCustomer")
	Queue queueCustomer() {
		return new Queue(qCustomer, true);
	}
	@Bean(name="exchangeCustomer")
	TopicExchange exchangeCustomer() {
		return new TopicExchange(topicName);
	}
	@Bean(name="bindingCustomer")
	Binding bindingCustomer(Queue queueCustomer, TopicExchange exchangeCustomer) {
		return BindingBuilder.bind(queueCustomer).to(exchangeCustomer).with(qCustomer);
	}

```

Next step is declare a connectionFactory with the rabbitMQ host(brokerUrl) and configure a json message converter:

```java
        @Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(brokerUrl);
		connectionFactory.setUsername(user);
		connectionFactory.setPassword(pwd);

		return connectionFactory;
	}
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
```
Last step is configure the rabbitTemplate:

```java
        @Bean(name="rabbitTemplateCustomer")
	public RabbitTemplate rabbitTemplateCustomer() {
		RabbitTemplate template = new RabbitTemplate(connectionFactory());
		template.setRoutingKey(qCustomer);
		template.setMessageConverter(jsonMessageConverter());
		return template;
	}
```
This configuration is in [AppRabbitPublisher.java](https://github.com/shoecillo/rabbitMQSpringBoot/blob/master/rabbitPublisher/src/main/java/com/sh/app/AppRabbitPublisher.java)


Class MsgProducer is the component that publishes to rabbitMQ:

```java

        @Autowired
	@Qualifier("rabbitTemplateCustomer")
	private RabbitTemplate rabbitCustomer;

	private static final Logger LOGGER = LoggerFactory.getLogger(MsgProducer.class);

	/**
	 * Convert and send CustomerMsg object to Rabbit
	 * @param msg - CustomerMsg
	 * @see CustomerMsg
	 */
	public void sendCustomerMsg(CustomerMsg msg)
	{
		try {
			LOGGER.debug("<<<<<< SENDING MESSAGE");
			rabbitCustomer.convertAndSend(msg);
			LOGGER.debug(MessageFormat.format("MESSAGE SENT TO {0} >>>>>>", rabbitCustomer.getRoutingKey()));

		} catch (AmqpException e) {
			LOGGER.error("Error sending Customer: ",e);
		}
	}

```

The operation [rabbitCustomer.convertAndSend(msg)] converts the msg into JSON and sends to rabbitMQ queue.

The other class is a REST controller with an API for publishing messages.

## rabbitShop (The rabbit Reader)

This module has the functionality of listen for messages from rabbitMQ and send event to a website via SSE.  
Create 2 listeners for 2 different queues but sharing topic exchange.

Project structure:

```
│   pom.xml
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───sh
│   │   │           ├───app
│   │   │           │       AppRabbitReader.java
│   │   │           │
│   │   │           ├───controller
│   │   │           │       StreamCtrl.java
│   │   │           │
│   │   │           ├───events
│   │   │           │   │   CustomerEvent.java
│   │   │           │   │
│   │   │           │   └───publisher
│   │   │           │           EventsPublisher.java
│   │   │           │
│   │   │           └───listener
│   │   │               │   RabbitListener.java
│   │   │               │
│   │   │               └───impl
│   │   │                       ListenerCustomer.java
│   │   │                       ListenerShop.java
│   │   │
│   │   └───resources
│   │       │   application.properties
│   │       │
│   │       └───static
│   │           │   index.html
│   │           │
│   │           ├───app
│   │           │       app.js
│   │           │
│   │           ├───css
│   │           │       animations.css
│   │           │       bootstrap.css
│   │           │       bootstrap.min.css
│   │           │       font-awesome.css.map
│   │           │       font-awesome.min.css
│   │           │       master.css
│   │           │
│   │           ├───fonts
│   │           │       fontawesome-webfont.eot
│   │           │       fontawesome-webfont.svg
│   │           │       fontawesome-webfont.ttf
│   │           │       fontawesome-webfont.woff
│   │           │       fontawesome-webfont.woff2
│   │           │       FontAwesome.otf
│   │           │
│   │           ├───img
│   │           │       banner.svg
│   │           │
│   │           └───jslib
│   │                   angular.min.js
│   │                   bootstrap.min.js
│   │                   jquery.min.js
│   │                   moment-with-locales.min.js
│   │                   ui-bootstrap-tpls-2.5.0.min.js
│   │                   ui-bootstrap-tpls.js
│   │                   underscore-min.js
```

To configure the listeners we have to declare the same that was done in publisher (Queue,Topic exchange,bind and connectionFactory configured for rabbitMQ host).  
Listener configuration:

```java
        @Bean(name="containerCustomer")
	SimpleMessageListenerContainer containerCustomer(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapterCustomer) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setMessageConverter(jsonMessageConverter());
		container.setQueueNames(qCustomer);
		container.setMessageListener(listenerAdapterCustomer);
		return container;
	}

	@Bean(name="listenerAdapterCustomer")
	public MessageListenerAdapter listenerAdapterCustomer(ListenerCustomer receiver) {
		MessageListenerAdapter msgAdapter = new MessageListenerAdapter(receiver);
		msgAdapter.setMessageConverter(jsonMessageConverter());
		msgAdapter.setDefaultListenerMethod(LISTENER_METHOD);

		return msgAdapter;
	}
```

It's easy to configure listeners but we need a container that configures destination,converter,connectionFactory and listener.
we need a message listener adapter,where we configure conversion and inject the class that implements the message reception operation, and finally we set which is the method listener,in my case a constant.

Now look at the interface that all my receivers must implement:

```java

@FunctionalInterface
public interface RabbitListener<T> {

	public void receiveMessage(T msg);
}

```

Using generic types, we can implements any bean as Message.  
RabbitListener<CustomerMsg> assign message bean type,in this case CustomerMsg (in the common project).  

## SSE with SpringBoot

SSE (Server-Side-Event) is a functionality that creates an event listener in the client side to receive from the server side.  
Usually we send request to server and wait a response, but in this case it's the opposite. We receive a request from server when an event occurs (this event in our project is a rabbitMQ message reception).  
For this purpose we have to use Spring ApplicationEvent events engine - 1. read a rabbit message, 2. send event to controller.  
Reception message implementation for CustomerMsg Bean:  

```java
@Component
public class ListenerCustomer implements RabbitListener<CustomerMsg>{

    private static final Logger LOGGER = LoggerFactory.getLogger(ListenerCustomer.class);

    @Autowired
    private EventsPublisher publisher;

    @Override
    public void receiveMessage(CustomerMsg message) {

    	try {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);			
			LOGGER.debug("Receive Nessage: \n"+json);

			publisher.publishCustomer(message);

		} catch (JsonProcessingException e) {
			LOGGER.error("Error: ", e);
		}

    }
}
```

EventsPublisher creates an ApplicationEvent and publishes an object when Listener receives the message off rabbitMQ.  (Method receiveMessage is configured to listen for customer messages.)  

Controller that reacts to EventsPublisher's ApplicationEvent:

```java
@Controller
public class StreamCtrl {

	private List<SseEmitter> lsEmitters = new ArrayList<SseEmitter>();

	private static final Logger LOGGER = LoggerFactory.getLogger(StreamCtrl.class);

	@RequestMapping("/stream.action")
	public SseEmitter stream()
	{
		SseEmitter emitter = new SseEmitter();
		lsEmitters.add(emitter);

		emitter.onCompletion(()->lsEmitters.remove(emitter));
		emitter.onTimeout(()->lsEmitters.remove(emitter));


		return emitter;
	}

	@EventListener({CustomerEvent.class})
	public void handleCustomerEvt(CustomerEvent evt)
	{
	    System.out.println("EVENT RECEIVED: "+evt.getMsg().getCustName());
	    List<SseEmitter> deadEmitters = new ArrayList<SseEmitter>();
	    this.lsEmitters.forEach(emitter -> {
	      try {
	        emitter.send(evt.getMsg());
	      }
	      catch (Exception e) {
	    	  LOGGER.error("Error ",e);
	        deadEmitters.add(emitter);
	      }
	    });

	    this.lsEmitters.removeAll(deadEmitters);
	}

}
```

The Controller listens for ApplicationEvents via the @EventListener annotation.  Since we only want to listen to certain events, we set the Beans that we want to receive (ShopEvent/CustomerEvent).  

To receive the sent SSE event, the client side has to declare a EventSource object and configure it:

```javascript

const eventSource = new EventSource('/stream.action');
eventSource.onmessage = e => {
const msg = JSON.parse(e.data);		
// do something...
}

eventSource.onopen = e => console.log('open');
eventSource.onerror = e => {
        if (e.readyState == EventSource.CLOSED) {
	   console.log('close');
        }
	else {
	   console.log(e);
	}
};


```

EventSource open a connection with '/stream.action',server side,and wait for events,not is a loop,is reactive.  
[Here Event Source MDN](https://developer.mozilla.org/es/docs/Web/API/EventSource)  
When declare this object the connection is opened, then in server side,controller '/stream.action' create a new SseEmitter and add it to list.
When @EventListener receive a rabbit message, send the object via SseEmitter.send(),and parse the Bean into JSON,then we'll receive a JSON object in client side function 'onmessage'.


***

