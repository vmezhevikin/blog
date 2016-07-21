package com.vmezhevikin.blog;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Random;
import java.util.UUID;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import net.coobird.thumbnailator.Thumbnails;

public class DataGenerator {

	private static final String DB_URL = "jdbc:mysql://localhost/blog";
	private static final String USER = "root";
	private static final String PASS = "password";

	private static final String AVATAR_DIR = "external/test-data/avatars/";
	private static final String TITLES_DIR = "external/test-data/titles/";
	private static final String MEDIA_DIR = "src/main/webapp/media/";

	private static final String[] CATEGORIES = { "Sport", "Business", "Life", "Fashion", "Music", "Movies" };
	private static final String PASSWORD_HASH = "$2a$10$q7732w6Rj3kZGhfDYSIXI.wFp.uwTSi2inB2rYHvm1iDIAf1J1eVq";
	private static final String[] DUMMY_TITLE = { "Lorem ipsum dolor sit amet, consectetuer adipiscing elit.", "Aenean commodo ligula eget dolor. Aenean massa.",
			"Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.",
			"Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem.",
			"Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu.",
			"In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo." };
	private static final String[] DUMMY_PAR = { "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo "
			+ "inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur "
			+ "magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam "
			+ "eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, "
			+ "nisi ut aliquid ex ea commodi consequatur?", 
			"Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur? "
			+ "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint "
			+ "occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita "
			+ "distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, "
			+ "omnis dolor repellendus.", 
			"Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic "
			+ "tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.Sed ut perspiciatis unde omnis iste natus "
			+ "error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.",
			"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur "
			+ "ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, "
			+ "arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. "
			+ "Aenean vulputate eleifend tellus.",
			"Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. "
			+ "Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, "
			+ "sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec "
			+ "vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna.",
			"Sed consequat, leo eget bibendum sodales, augue velit cursus nunc, quis gravida magna mi a libero. Fusce vulputate eleifend sapien. Vestibulum purus quam, scelerisque ut, mollis sed, "
			+ "nonummy id, metus. Nullam accumsan lorem in dui. Cras ultricies mi eu turpis hendrerit fringilla. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia "
			+ "Curae; In ac dui quis mi consectetuer lacinia. Nam pretium turpis et arcu. Duis arcu tortor, suscipit eget, imperdiet nec, imperdiet iaculis, ipsum. Sed aliquam ultrices mauris. "
			+ "Integer ante arcu, accumsan a, consectetuer eget, posuere ut, mauris. Praesent adipiscing. Phasellus ullamcorper ipsum rutrum nunc."};

	private static final Random RAND = new Random();
	private static int totalCategories, totalAuthors, totalArticles;

	private static void clearDestinationFolder() throws IOException {
		System.out.println("Clearing destination folder");
		Path path = Paths.get(MEDIA_DIR);
		if (Files.exists(path)) {
			Files.walkFileTree(path, new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					String fileName = file.toFile().getName();
					if (!fileName.contains("img") && !fileName.contains("loading")) {
						Files.delete(file);
					}
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
					return FileVisitResult.CONTINUE;
				}
			});
		}
		System.out.println("Destination folder has been cleared");
	}

	private static void clearDB(Connection connection) throws SQLException {
		System.out.println("Clearing DB");
		Statement statement = connection.createStatement();
		statement.executeUpdate("delete from comment");
		statement.executeUpdate("delete from article");
		statement.executeUpdate("delete from author");
		statement.executeUpdate("delete from category");
		statement.executeUpdate("alter table category auto_increment = 1");
		statement.executeUpdate("alter table author auto_increment = 1");
		statement.executeUpdate("alter table article auto_increment = 1");
		statement.executeUpdate("alter table comment auto_increment = 1");
		System.out.println("DB has been cleared");
	}
	
	private static void insertData(Connection connection) throws SQLException, IOException {
		System.out.println("Inserting data");
		insertCategories(connection);
		insertAuthors(connection);
		insertArticles(connection);
		insertComments(connection);
		System.out.println("Data have been inserted");
	}

	private static void insertCategories(Connection connection) throws SQLException {
		String sql = "insert into category (name) values (?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		totalCategories = CATEGORIES.length;
		for (String category : CATEGORIES) {
			statement.setString(1, category);
			statement.addBatch();
		}
		statement.executeBatch();
		statement.close();
	}

	private static void insertAuthors(Connection connection) throws SQLException, IOException {
		String sql = "insert into author (name, img, email, password) values (?,?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		File[] files = new File(AVATAR_DIR).listFiles();
		totalAuthors = files.length;
		for (File file : files) {
			String name = file.getName().replace(".jpg", "");	
			statement.setString(1, name);
			String uuid = copyImage("avatar", file);
			String img = "/media/avatar/" + uuid;
			statement.setString(2, img);
			String email = file.getName().toLowerCase().replace(".jpg", "").replace(" ", ".").concat("@mail.com");
			statement.setString(3, email);
			statement.setString(4, PASSWORD_HASH);
			statement.addBatch();
		}
		statement.executeBatch();
		statement.close();
	}
	
	private static String copyImage(String folder, File sourceFile) throws IOException {
		String uuid = UUID.randomUUID().toString() + ".jpg";
		File image = new File(MEDIA_DIR + folder + "/" + uuid);
		if (!image.getParentFile().exists())
			image.getParentFile().mkdirs();
		if ("avatar".equals(folder)) {
			Thumbnails.of(sourceFile).size(100, 100).toFile(image);
		} else {
			Files.copy(sourceFile.toPath(), image.toPath());
		}
		return uuid;
	}

	private static void insertArticles(Connection connection) throws SQLException, IOException {
		String sql = "insert into article (id_author, id_category, name, description, text, date, img) "
				+ "values (?,?,?,?,?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		totalArticles = 0;
		for (int i = 0; i < 3; i++) {
			File[] files = new File(TITLES_DIR).listFiles();
			totalArticles += files.length;
			for (File file : files) {
				statement.setLong(1, RAND.nextInt(totalAuthors) + 1);
				statement.setShort(2, (short) (RAND.nextInt(totalCategories) + 1));
				statement.setString(3, DUMMY_TITLE[RAND.nextInt(DUMMY_TITLE.length)]);
				String desc = DUMMY_PAR[RAND.nextInt(DUMMY_PAR.length)];
				statement.setString(4, desc);
				statement.setString(5, generateText(desc));
				statement.setTimestamp(6, generatDate());
				String uuid = copyImage("title", file);
				String img = "/media/title/" + uuid;
				statement.setString(7, img);
				statement.addBatch();
			}
		}
		statement.executeBatch();
		statement.close();
	}
	
	private static String generateText(String desc) {
		String text = desc;
		int count = RAND.nextInt(5) + 2;
		for (int i = 0; i < count; i++) {
			text += DUMMY_PAR[RAND.nextInt(DUMMY_PAR.length)];
		}
		return text;
	}
	
	private static Timestamp generatDate() {
		int year = LocalDate.now().getYear() - RAND.nextInt(3);
		int monthOfYear = RAND.nextInt(12) + 1;
		int dayOfMonth = RAND.nextInt(29) + 1;
		int hours = RAND.nextInt(24);
		int mins = RAND.nextInt(60);
		DateTime date = new DateTime(year, monthOfYear, dayOfMonth, hours, mins);
		return new Timestamp(date.toDate().getTime());
	}

	private static void insertComments(Connection connection) throws SQLException {
		String sql = "insert into comment (id_author, id_article, text, date) values (?,?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		int count = RAND.nextInt(30) + 10;
		for (int i = 0; i < count; i++) {
			statement.setLong(1, RAND.nextInt(totalAuthors) + 1);
			statement.setLong(2, RAND.nextInt(totalArticles) + 1);
			statement.setString(3, DUMMY_PAR[RAND.nextInt(DUMMY_PAR.length)]);
			statement.setTimestamp(4, generatDate());
			statement.addBatch();
		}
		statement.executeBatch();
		statement.close();
	}

	public static void main(String[] args) {
		System.out.println("Start generating");
		Connection connection = null;
		try {
			clearDestinationFolder();
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			connection.setAutoCommit(false);
			clearDB(connection);
			insertData(connection);
			connection.commit();
			System.out.println("Data have been generated");
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
					System.out.println("Connection has been closed");
				} catch (SQLException e) {
					System.out.println("Connection has not been closed");
					e.printStackTrace();
				}
			}
		}
	}
}