package CoreEngine.Object;

/**
 * 이용자 객체를 생성하기 위한 클래스
 * 이름과 고유번호를 가진다.
 *
 * @author (1팀)
 * @version (2025.12.5 )
 */
public class Borrower implements Comparable<Borrower>
{   
    private String name;
    private int number;
    private int bookLoanCount = 0;
    private final int bookLoanCount_MAX = 10;
    /** Borrower 클래스의 객체 생성자이다.
     * 이름, 고유번호를 받아 이용자(객체)를 생성한다.
     */
    public Borrower(String name, int number){
        this.name = name;
        this.number = number;
    }
    /** 이용자의 고유번호를 반환하는 동작을 수행한다.
     * 
     */
    public int getNumber(){
        return this.number;
    }
    /**
     * 화면에 책의 속성값(제목, 저자, 목록번호)을 문자열로 반환한다.
     */
    @Override
    public String toString() {
        return "이름: " + this.name + ", 고유번호: " + this.number;
    }
    /** 책 대출 권수를 카운트하기 위한 메소드이다.
     * 책을 대출할 때 실행되며 값을 1씩 더한다.
     */
    public void bookLoanCount(){
        this.bookLoanCount++;
    }
    /** 책 대출 권수를 카운트하기 위한 메소드이다.
     * 책을 반납할 때 실행되며 값을 1씩 뺀다.
     * 오류를 방지하기 위해 음수값이 아닐 때 실행되도록한다. (최소 카운트 0권)
     */
    public void returnCount(){
        if(bookLoanCount>0){
            this.bookLoanCount--;
        }
    }
    /** 카운트한 책 대출 권수를 통해서 추가 대출 가능 여부를 확인하기 위한 메소드이다.
     * 책을 대출할 때 사용된다.
     */
    public boolean countCheck(){
        if(bookLoanCount<bookLoanCount_MAX){
            return true;
        }else{
            return false;
        }
    }
    /** 고유번호(number) 기준으로 이용자 객체를 오름차순 정렬하기 위한 메소드이다.
     *
     */
    @Override
    public int compareTo(Borrower other) {
        return Integer.compare(this.number, other.number);
    }
}
