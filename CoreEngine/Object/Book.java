package Object;

/**
 * 책 객체를 생성하기 위한 클래스
 * 한개의 제목, 한개 이상의 저자, 한개의 고유번호, 상태(대출중/대출가능)가지고 있는 Book 객체를 생성한다.
 *
 * @author (1팀)
 * @version (2025.12.5)
 */
public class Book implements Comparable<Book>
{
    private String title;
    private int catalogueNumber;
    private String author;
    private Boolean state = true;  //True는 대출 가능, False는 대출 중을 의미한다. null은 미소장으로 정의할 수 있다.
    
    /** Book 클래스의 객체 생성자이다.
     * 제목, 저자, 목록 번호를 받아 책(객체)를 생성하는 동작을 수행한다.
     */
    public Book(String title, String author, int catalogueNumber)
    {
        this.title = title;
        this.author = author;
        this.catalogueNumber = catalogueNumber;
    }
    
    /**
     * 책의 목록번호(catalogueNumber)를 반환한다.
     */
    public int getCatalogueNumber(){
        return this.catalogueNumber;
    }
    
    /**
     * 책의 대출 여부를 반환하는 동작을 수행한다.
     */
    public Boolean getState(){
        return this.state;
    }
    
    /**
     * 책 객체의 대출 가능 여부에 따라 이후 동작을 수행하기 위한 조건을 바꾸는 동작이다.
     * 책 객체의 상태에 접근해서 대출 가능하면 True, 대출 불가능(대출중)이면 False를 가지도록 변경한다.
     * 변경하는 논리값은 파라미터로 전달받는다.
     */
    public void changeStatus(Boolean newstate){
        this.state = newstate;
    }
    
    /**
     * 화면에 책의 속성값(제목, 저자, 목록번호)을 문자열로 반환한다.
     */
    @Override
    public String toString() {
        return "제목: " + this.title + ", 저자: " + this.author + ", 목록 번호: " + this.catalogueNumber;
    }
    
    /** 목록번호(catalogueNumber) 기준으로 책 객체를 오름차순 정렬하기 위한 메소드이다.
     *
     */
    @Override
    public int compareTo(Book other) {
        return Integer.compare(this.catalogueNumber, other.catalogueNumber);
    }
}