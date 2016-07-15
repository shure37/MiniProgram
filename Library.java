import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

class Book {
	int num;
	String author;
	String name;

	Book(int num, String name, String author) {
		this.num = num;
		this.name = name;
		this.author = author;
		// System.out.print("Book" +num+ " wrote by " +author);
		// System.out.println();
	}
}

public class Library {
	static ArrayList book = new ArrayList();
	static Iterator it;

	public static void show() {
		System.out.println("Books that currently in the library is: ");
		it = book.iterator();
		while (it.hasNext()) {
			System.out.print(((Book) it.next()).name + " ");
		}
	}

	public static void main(String args[]) throws Exception {

		book.add(new Book(1, "book1", "author1"));
		book.add(new Book(2, "book2", "author2"));
		book.add(new Book(3, "book3", "author3"));

		while (true) {
			show();

			System.out.println();
			System.out.println("==============================");
			System.out.println(
					"Please enter a command: " + "\n" + "//insert + 'book number' + 'book name' + 'book author'" + "\n"
							+ "//update + 'old name' + to + 'new name'" + "\n" + "//delete + 'book name'" + "\n"
							+ "//search + 'book number' or 'book name' or 'book author'" + "\n" + "*No plus sign in your input");
			Scanner in = new Scanner(System.in);
			String str = in.next();
			String str1 = in.nextLine();
			String strs[] = str1.split("\\s+");
			// System.out.println(strs[1]);

			if (str.equals("insert")) {
				book.add(new Book(Integer.parseInt(strs[1]), strs[2], strs[3]));
				// show();
				System.out.println("==============================");
			} else if (str.equals("update")) {
				for (int i = 0; i < book.size(); i++) {
					if (((Book) book.get(i)).name.equals(strs[1])) {
						book.add(new Book(((Book) book.get(i)).num, strs[3], ((Book) book.get(i)).author));
						book.remove(i);
					}
				}
				System.out.println("==============================");
			} else if (str.equals("delete")) {
				for (int i = 0; i < book.size(); i++) {
					if (((Book) book.get(i)).name.equals(strs[1])) {
						book.remove(i);
					}
				}
				System.out.println("==============================");
			} else if (str.equals("search")) {
				Iterator it = book.iterator();
				while (it.hasNext()) {
					Book a = (Book) it.next();
					try {
						if (a.num == Integer.parseInt(strs[1])) {
							System.out.println("Book number is " + a.num + ", book title is " + a.name + ", author is "
									+ a.author);
						}
					} catch (Exception e) {
						if (a.name.equals(strs[1]) || a.author.equals(strs[1])) {
							System.out.println("Book number is " + a.num + ", book title is " + a.name + ", author is "
									+ a.author);
						}
					}
				}

				System.out.println("==============================");
			}
		}

	}

}
