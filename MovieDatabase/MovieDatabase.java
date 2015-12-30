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
				System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
			}
		}
	}

	private static void command(String input)
	{	
		//input을 명령어와 장르, 영화 혹은 명령어와 검색어로 나누어 배열에 저장
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
			//list 출력, 비어있을 때는 EMPTY
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
		//list가 비어있으면 node를 그냥 삽입
		if (head == null)
		{
			head = newNode;
		}
		else
		{
			for (Node curr = head; curr != null; curr = curr.getNext())
			{
				//맞는 자리를 찾으면 그 때 node를 삽입
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
		//입력한 장르와 영화 모두를 가지는 node를 삭제
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
		//list가 비어있을 경우 EMPTY 출력
		if (head == null)
		{
			System.out.println("EMPTY");
		}
		else
		{
			//list의 처음부터 끝까지 쭉 훑으면서 search_word가 포함되어있는지 확인 후 있으면 출력
			int a = 0;
			for (Node curr = head; curr != null; curr = curr.getNext())
			{
				if (((String) curr.getMovie()).contains(search_word) == true)
				{
					System.out.print("("+curr.getGenre()+", "+curr.getMovie()+")\n");
					a = 1;
				}
			}
			//아무것도 출력하지 않았으면 EMPTY 출력
			if (a == 0)
				System.out.println("EMPTY");
		}
	}
}
//원소로 장르, 영화를 가지는 node 생성
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