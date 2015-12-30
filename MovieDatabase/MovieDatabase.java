import java.io.*;

public class MovieDatabase
{
	static Node head = null;
	static Node prev = new Node(null, null);
	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while (true)
		{
			try
			{
				String input = br.readLine();
				if (input.compareTo("QUIT") == 0)
					break;

				command(input);
			}
			catch (Exception e)
			{
				System.out.println("�Է��� �߸��Ǿ����ϴ�. ���� : " + e.toString());
			}
		}
	}

	private static void command(String input)
	{	
		//input�� ��ɾ�� �帣, ��ȭ Ȥ�� ��ɾ�� �˻���� ������ �迭�� ����
		String[] array = input.split("%");
		String command = array[0].trim();
		
		if (command.equals("INSERT") == true)
		{
			insert(array[1], array[3]);
		}
		else if (command.equals("DELETE") == true)
		{
			delete(array[1], array[3]);
		}
		else if (command.equals("SEARCH") == true)
		{
			search(array[1]);
		}
		else if (command.equals("PRINT") == true)
		{
			//list ���, ������� ���� EMPTY
			if (head == null)
			{
				System.out.println("EMPTY");
			}
			else
			{
				for (Node curr = head; curr != null; curr = curr.getNext())
				{
					System.out.print("("+curr.getGenre()+", "+curr.getMovie()+")\n");
				}
			}
		}
	}
	
	private static void insert(String genre, String movie)
	{
		Node newNode = new Node(genre, movie);
		//list�� ��������� node�� �׳� ����
		if (head == null)
		{
			head = newNode;
		}
		else
		{
			for (Node curr = head; curr != null; curr = curr.getNext())
			{
				//�´� �ڸ��� ã���� �� �� node�� ����
				if (genre.compareTo(curr.getGenre()) == 0)
				{
					if (movie.compareTo(curr.getMovie()) == 0)
					{
						break;
					}
					else if (movie.compareTo(curr.getMovie()) < 0)
					{
						if (curr == head)
						{
							newNode.setNext(head);
							head = newNode;
							break;
						}
						else
						{
							newNode.setNext(curr);
							prev.getNext().setNext(newNode);
							break;
						}
					}
				}
				else if (genre.compareTo(curr.getGenre()) < 0)
				{
					if (curr == head)
					{
						newNode.setNext(head);
						head = newNode;
						break;
					}
					else
					{
						newNode.setNext(curr);
						prev.getNext().setNext(newNode);
						break;
					}
				}
				prev.setNext(curr);
				if (curr.getNext() == null)
				{
					curr.setNext(newNode);
					break;
				}
			}
		}
	}
	
	private static void delete(String genre, String movie)
	{
		//�Է��� �帣�� ��ȭ ��θ� ������ node�� ����
		for (Node curr = head; curr != null; curr = curr.getNext())
		{
			if (genre.compareTo(curr.getGenre()) == 0)
			{
				if (movie.compareTo(curr.getMovie()) == 0)
				{
					if (curr == head)
					{
						head = curr.getNext();
					}
					else
					{
						prev.getNext().setNext(curr.getNext( ));
						break;
					}
				}
			}
			prev.setNext(curr);
		}
	}
	
	private static void search(String search_word)
	{
		//list�� ������� ��� EMPTY ���
		if (head == null)
		{
			System.out.println("EMPTY");
		}
		else
		{
			//list�� ó������ ������ �� �����鼭 search_word�� ���ԵǾ��ִ��� Ȯ�� �� ������ ���
			int a = 0;
			for (Node curr = head; curr != null; curr = curr.getNext())
			{
				if (((String) curr.getMovie()).contains(search_word) == true)
				{
					System.out.print("("+curr.getGenre()+", "+curr.getMovie()+")\n");
					a = 1;
				}
			}
			//�ƹ��͵� ������� �ʾ����� EMPTY ���
			if (a == 0)
				System.out.println("EMPTY");
		}
	}
}
//���ҷ� �帣, ��ȭ�� ������ node ����
class Node
{
	private String genre;
	private String movie;
	private Node next;
	public Node(String newGenre, String newMovie)
	{
		genre = newGenre;
		movie = newMovie;
		next = null;
	}
	public Node(String newGenre, String newMovie, Node nextNode)
	{
		genre = newGenre;
		movie = newMovie;
		next = nextNode;
	}
	public String getGenre()
	{
		return genre;
	}
	public String getMovie()
	{
		return movie;
	}
	public Node getNext()
	{
		return next;
	}
	public void setNext(Node nextNode)
	{
		next = nextNode;
	}
}