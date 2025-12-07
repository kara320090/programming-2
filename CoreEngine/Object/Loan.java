package CoreEngine.Object;
import java.util.Calendar; 
/**
 * 대출 정보를 관리하는 클래스이다.
 * 책 객체, 이용자 객체, 대출일자, 반납예정일자를 속성으로 가지며,
 * 대출 기간(10일)을 기준으로 반납예정일자를 자동으로 계산한다.
 * 대출 객체 생성 시 책의 상태를 대출중으로 변경한다.
 *
 * @author (1팀)
 * @version (2025.12.5)
 */
public class Loan implements Comparable<Loan>
{
    private Calendar loanDate;
    private Calendar dueDate;
    private final int returnPeriodDate = 10; // 대출 기간을 저장해두는 변수. 초기값 = 10일
    private Book book;
    private Borrower borrower;
    
    /**
     * 대출 객체를 생성하는 생성자이다.
     * 객체를 생성하면서 대출일자와 반납예정일자를 계산하고,
     * 책의 상태를 대출중으로 변경한다.
     * 
     * @param book 대출할 책 객체
     * @param borrower 대출하는 이용자 객체
     */
    public Loan(Book book, Borrower borrower){
        this.book = book;
        this.borrower = borrower;
        setReturnDate();
    }
    
    /**
     * 대출 일자를 반환하는 메소드이다.
     * 
     * @return 대출 일자 (Calendar 객체)
     */
    public Calendar getLoanDate() {
        return loanDate;
    }
    
    /**
     * 반납 예정일자를 반환하는 메소드이다.
     * 
     * @return 반납 예정일자 (Calendar 객체)
     */
    public Calendar getDueDate() {
        return dueDate;
    }
    
    /**
     * 대출일자와 반납예정일자를 설정하는 메소드이다.
     * 현재 날짜를 대출일자로 설정하고, 대출 기간(10일)을 더해 반납예정일자를 계산한다.
     * 동시에 책의 상태를 대출중(false)으로 변경한다.
     */
    public void setReturnDate(){
        this.loanDate = Calendar.getInstance();
        this.dueDate = (Calendar) loanDate.clone();
        dueDate.add(Calendar.DAY_OF_MONTH, returnPeriodDate);
        this.book.changeStatus(false);
    }
    
    /**
     * 대출된 책 객체를 반환하는 메소드이다.
     * 
     * @return 대출된 책 객체
     */
    public Book getBook() {
        return this.book;
    }
    
    /**
     * 대출한 이용자 객체를 반환하는 메소드이다.
     * 
     * @return 대출한 이용자 객체
     */
    public Borrower getBorrower(){
        return this.borrower;
    }
    
    /**
     * TreeSet에서 정렬 기준을 정의하는 메소드이다.
     * 대출 객체는 해당 대출이 가진 책의 목록번호를 기준으로 오름차순 정렬된다.
     * 
     * @param other 비교할 다른 대출 객체
     * @return 비교 결과 (음수: 작음, 0: 같음, 양수: 큼)
     */
    @Override
    public int compareTo(Loan other) {
        return Integer.compare(
            this.book.getCatalogueNumber(),
            other.book.getCatalogueNumber()
        );
    }
}
