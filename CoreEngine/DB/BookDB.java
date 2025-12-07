package CoreEngine.DB;
import Object.Book;
import java.util.TreeSet;
import java.util.Iterator;
/**
 * 책 관리(등록, 삭제 등)를 위한 동작을 가지고 있는 클래스이다
 * 책을 객체 단위로 생성하며 이를 ArrayList 형태로 저장하여 책 객체들을 관리한다.
 * 
 * @author (1팀)
 * @version (2025.12.5)
 */
public class BookDB
{
    
    private TreeSet<Book> books;
    
    /** 책 관리를 위한 객체 생성자이다.
     * 공용 스캐너 사용으로 값을 받고 이후 다른 메소드에서 사용할 수 있도록 한다.
     * 책을 저장, 검색, 등록, 삭제 등 관리하기 위해서 리스트 형태로 객체의 속성을 지정해준다.
     */
    public BookDB(){
        this.books = new TreeSet<>();
    }
    
    /** BookDB에 Book 객체를 추가하는 메소드이다. 
     * 
     * @param  Book 객체 1개
     */
    public void add(Book book) {
        books.add(book);
    }
    
    /** 목록번호를 사용해서 책을 찾는 메소드이다.
     * 메소드가 실행되면 받아온 책 리스트에서 책 객체를 하나씩 꺼내고 
     * 받아온 목록번호와 저장된 책의 목록 번호를 비교하여 같으면 해당 책을 반환하고 다르면 NULL을 반환한다.
     * 책의 저장유무 확인, 대출, 반납 등의 기능에서 사용할 수 있다.
     */
        // catalogueNumber로 Book 찾기
    public Book findBook(int catalogueNumber){
        Iterator<Book> it = books.iterator();
        while(it.hasNext()){
            Book b = it.next();
            if(b.getCatalogueNumber() == catalogueNumber){
                return b;
            }
        }
        return null;
    }

    // LibraryApplication에서 책 목록을 돌릴 때 사용
    public Iterator<Book> iterator(){
        return books.iterator();
    }
    
    /**
     * 임시 데이터셋
     */
    public void addSampleBooks() {
        books.add(new Book("자바의 정석", "남궁성", 1));
        books.add(new Book("파이썬 완벽 가이드", "이강성", 2));
        books.add(new Book("자바의 정석", "남궁성", 3)); // 동일 제목 테스트용
        books.add(new Book("C언어 기초", "김철수", 4));
        books.add(new Book("자료구조", "박지민", 5));
        
        books.add(new Book("Java Programming", "홍길동", 6));
        books.add(new Book("Software Analysis and Design", "profsHwang", 6));
        books.add(new Book("명품 자바프로그래밍", "황기태", 8));
        books.add(new Book("소프트웨어테스트", "profsHwang", 9));
        
    }
}
