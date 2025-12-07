package CoreEngine.DB;

import CoreEngine.Object.Borrower;
import CoreEngine.Object.Book;
import CoreEngine.Object.Loan;

import java.util.TreeSet;
import java.util.Iterator;
/**
 * 대출 관리를 위한 클래스이다.
 * 대출 기록을 TreeSet 형태로 저장하여 관리하며, 대출 등록, 검색, 삭제 기능을 제공한다.
 * 목록번호를 기준으로 오름차순 정렬되어 저장된다.
 * 
 * @author (1팀)
 * @version (2025.12.5)
 */
public class LoanDB {
    private TreeSet<Loan> loanDB;
    
    /**
     * 대출 관리를 위한 객체 생성자이다.
     * 대출 객체들을 TreeSet 형태로 저장하여 관리한다.
     */
    public LoanDB() {
        this.loanDB = new TreeSet<>();
    }
    
    /**
     * 새로운 대출을 등록하는 메소드이다.
     * 
     * @param loan 등록할 대출 객체
     */
    public void registerOneLoan(Loan loan){
        loanDB.add(loan);
    }
    
    /**
     * 책의 목록번호를 사용해서 대출 기록을 찾는 메소드이다.
     * 대출 리스트에서 대출 객체를 하나씩 꺼내고
     * 받아온 목록번호와 저장된 책의 목록번호를 비교하여 같으면 해당 대출 객체를 반환하고 다르면 NULL을 반환한다.
     * 
     * @param catalogueNumber 검색할 책의 목록번호
     * @return 찾은 대출 객체, 없으면 null
     */
    public Loan findLoan(int catalogueNumber){
        Iterator<Loan> it = loanDB.iterator();
        while(it.hasNext()){
            Loan l = it.next();
            if(l.getBook().getCatalogueNumber() == catalogueNumber){
                return l;
            }
        }
        return null;
    }

    /**
     * LibraryApplication에서 대출 목록을 순회하기 위한 iterator를 반환한다.
     * 
     * @return 대출 컬렉션의 iterator
     */
    public Iterator<Loan> iterator(){
        return loanDB.iterator();
    }

    /**
     * 대출 기록을 삭제하는 메소드이다.
     * 책 반납 시 호출되어 대출 목록에서 해당 대출을 제거한다.
     * 
     * @param loan 삭제할 대출 객체
     */
    public void removeLoan(Loan loan){
        loanDB.remove(loan);
    }
    
}
