Coverage: 34%
# IMS Project

This is my IMS project where I have created a Inventory management system with the CRUD functions: Create, Read, Add, Update and Delete for the user in order for a user to use these functions when working with the Customers, Items, Orders and OrderItems tables. This project consisted me of creating these tables in SQL and using Java on eclipse for backend coding.  

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them


•	Version Control System: Git
•	Source Code Management: GitHub
•	Kanban Board: Jira
•	Database Management System: MySQL Server 5.7+ (local or GCP instance)
•	Back-End Programming Language: Java
•	Build Tool: Maven
•	Unit Testing: JUnit
IDE = eclipse



### Installing
1. Clone the link into your files and install it in your eclipse IDE.
2. Make sure you git clone it down and create your own branch to work on.
3. Once installed you can navigate through the methods and tables and input data and  manipulate it accordingly
A step by step series of examples that tell you how to get a development env running

Example : Item Create in DAO
@Override
public Item create(Item item) {
	try (Connection connection = DBUtils.getInstance().getConnection();
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO items(item_id, item_name, price) VALUES (?, ?, ?)");) {
		statement.setLong(1, item.getItemId());
		statement.setString(2, item.getItemName());
		statement.setDouble(3, item.getPrice());
		statement.executeUpdate();
		return readLatest();
	} catch (Exception e) {
		LOGGER.debug(e);
		LOGGER.error(e.getMessage());
	return null;

## Running the tests


There are 9 tests categorised into Controller tests, DAO tests and Domain tests. These classes test the CRUD functions.
If you right click on each test and then click on run as JUnit test the test should run and it will show you the coverage of each test indovidually whether its passed for failed and why it has failed. 

### Unit Tests 
These test the functionality of individual methods for customer,Item and orders to make sure they work using a Junit test.
This tests must pass to ensure that the application runs correctly.
@Test
	public void testCreate() {
		final Item created = new Item(2L, "7 Up", 5.00);
		assertEquals(created, DAO.create(created));
	}


### Integration Tests 
Many integration tests have been created for my application using Mockito. Mockito (or any other mocking tool) is a framework that you specifically use to efficiently write certain kind of tests. Here is a test that tests the readAll() method in the Customer controller.

	@Test
	public void testReadAll() {
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(1L, "jordan", "harrison"));

		Mockito.when(dao.readAll()).thenReturn(customers);

		assertEquals(customers, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}


### And coding style tests
There are many coding style tests I have used coverage as JUnit test where you right click file and run covereage as JUnit.
This gives a % of coverage
example 
Coverage as JUNIT




## Deployment
Run as a fatJAR file for deployment on a live system


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Hamza Shah** - *Initial work* - [hamza12618](https://github.com/hamza12618)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
