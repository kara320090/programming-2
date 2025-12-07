package Object;
import java.util.Calendar;
/**
 * 반납 이력을 관리하는 클래스이다.
 * 대출 객체와 반납일자를 속성으로 가지며,
 * 반납된 책의 정보를 기록하여 이용자의 반납 이력을 추적할 수 있다.
 *
 * @author (1팀)
 * @version (2025.12.7)
 */
public class History implements Comparable<History>
{
    private Calendar returnDate;
    private Loan loan;

    /**
     * 반납 이력 객체를 생성하는 생성자이다.
     * 대출 객체를 받아 반납 이력을 생성하고, 현재 시간을 반납일자로 저장한다.
     * 
     * @param loan 반납된 대출 객체
     */
    public History(Loan loan){
        this.loan = loan;
        this.returnDate = Calendar.getInstance();
    }

    /**
     * 반납일자를 반환하는 메소드이다.
     * 
     * @return 반납일자 (Calendar 객체)
     */
    public Calendar getReturnDate() {
        return returnDate;
    }
    
    /**
     * 반납 이력 정보를 문자열로 반환하는 메소드이다.
     * 대출일시, 대출자, 대출서적 정보를 포함한다.
     * 
     * @return 반납 이력 정보 문자열
     */
    @Override
    public String toString() {
        return "대출일시: " + this.loan.getLoanDate() + "대출자: " + loan.getBorrower().toString() + "대출서적: " + loan.getBook().toString() ;
    }
    
    /**
     * TreeSet에서 정렬 기준을 정의하는 메소드이다.
     * 반납 이력은 책의 목록번호를 기준으로 오름차순 정렬된다.
     * 
     * @param other 비교할 다른 반납 이력 객체
     * @return 비교 결과 (음수: 작음, 0: 같음, 양수: 큼)
     */
    @Override
    public int compareTo(History other) {
        return other.returnDate.compareTo(this.returnDate);
    }
    
    /**
     * 대출 객체를 반환하는 메소드이다.
     * 
     * @return 대출 객체
     */
    public Loan getLoan() {  
        return loan;
    }
}