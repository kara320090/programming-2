package CoreEngine;
import Object.Borrower;
import Object.Book;
import Object.Loan;
import Object.History;

import DB.BorrowerDB;
import DB.BookDB;
import DB.LoanDB;
import DB.HisDB;

import java.util.Iterator;
/**
 * 도서관 시스템의 메인 애플리케이션 클래스이다.
 * 이용자 관리, 책 관리, 대출/반납 관리, 반납 이력 관리 기능을 통합하여 제공한다.
 * 각 데이터베이스(BorrowerDB, BookDB, LoanDB, HisDB)를 통해 전체 도서관 업무를 처리한다.
 *
 * @author (1팀)
 * @version (2025.12.8)
 */
public class LibraryApplication 
{
    private String libraryName;
    private BorrowerDB borrowerDB;
    private BookDB bookDB;

    private LoanDB loanDB;
    private HisDB hisDB;
    
    /**
     * 도서관 애플리케이션 객체를 생성하는 생성자이다.
     * 도서관 이름을 설정하고, 각 데이터베이스(이용자, 책, 대출, 반납이력)를 초기화한다.
     * 
     * @param name 도서관 이름
     */
    public LibraryApplication(String name){
        this.libraryName = name;
        this.borrowerDB = new BorrowerDB();
        this.bookDB = new BookDB();
        this.loanDB = new LoanDB();
        this.hisDB = new HisDB();
        
        this.bookDB.addSampleBooks();
        this.borrowerDB.addSampleBorrowers();
    }
    
    /**
     * UC#1 : 이용자를 등록하는 메소드이다.
     * 입력받은 이름과 고유번호로 새로운 이용자 객체를 생성하고 이용자 데이터베이스에 추가한다.
     * 
     * @param name 이용자 이름
     * @param number 이용자 고유번호
     * @return 등록 완료 메시지
     */
    public String registerOneBorrower(String name, int number){
        if(borrowerDB.findBorrower(number)==null){
            Borrower user = new Borrower(name, number);
            borrowerDB.add(user);
            return "이용자(" + name + ") 등록작업을 완료하였습니다.";
        }else{
            return "해당 고유번호은 이미 등록되어 있습니다. 다른 고유번호를 사용해주세요.";
        }
    }
    /**
     * UC#2 : 책을 등록하는 메소드이다.
     * 입력받은 제목, 저자, 목록번호로 새로운 책 객체를 생성하고 책 데이터베이스에 추가한다.
     * 
     * @param title 책 제목
     * @param author 저자
     * @param catalogueNumber 목록번호
     * @return 등록 완료 메시지
     */
    public String registerOneBook(String title, String author, int catalogueNumber){
        if(bookDB.findBook(catalogueNumber)==null){
            Book book = new Book(title, author, catalogueNumber);
            bookDB.add(book);
            return "책(" + title + ", " + author + ", " + catalogueNumber + ") 등록작업을 완료하였습니다.";
        }else{
            return "해당 고유번호은 이미 등록되어 있습니다. 다른 고유번호를 사용해주세요.";
        }
    }
    
    /**
     * UC#3 : 대출 가능한 책 목록을 출력하는 메소드이다.
     * Iterator를 사용하여 모든 책을 순회하고, 대출 가능 상태(true)인 책만 목록에 포함한다.
     * 
     * @return 대출 가능한 책 목록 문자열
     */
    public String displayBookForLoan(){
        String result = "=== 대출 가능한 책 목록 ===\n";

        Iterator<Book> it = bookDB.iterator();

        while(it.hasNext()){
            Book book = it.next();
            if (Boolean.TRUE.equals(book.getState())) {
                result = result + book.toString() + "\n";
            }
        }

        // 제목 줄만 있다면 → 아무것도 출력된 것이 없음
        if(result.equals("=== 대출 가능한 책 목록 ===\n")){
            result = result + "현재 대출 가능한 책이 없습니다.\n";
        }

        return result + "대출가능한 책 목록을 Display하였습니다.";
    }
    
    /**
     * UC#4 : 대출 중인 책 목록을 출력하는 메소드이다.
     * Iterator를 사용하여 모든 책을 순회하고, 대출 중 상태(false)인 책만 목록에 포함한다.
     * 
     * @return 대출 중인 책 목록 문자열
     */
    public String displayBookOnLoan(){
        String result = "=== 대출 중인 책 목록 ===\n";

        Iterator<Book> it = bookDB.iterator();

        while(it.hasNext()){
            Book book = it.next();
            if (Boolean.FALSE.equals(book.getState())) {
                result = result + book.toString() + "\n";
            }
        }

        if(result.equals("=== 대출 중인 책 목록 ===\n")){
            result = result + "현재 대출 중인 책이 없습니다.\n";
        }

        return result;
    }
    
    /**
     * UC#5 : 책 1권을 대출하는 메소드이다.
     * 이용자와 책의 존재 여부, 이용자의 대출 가능 권수, 책의 대출 가능 상태를 확인한 후
     * 대출 객체를 생성하고 대출 데이터베이스에 등록한다.
     * 
     * @param number 이용자 고유번호
     * @param catalogueNumber 책 목록번호
     * @return 대출 완료 메시지 또는 오류 메시지
     */
    public String loanOneBook(int number, int catalogueNumber){

        Borrower borrower = borrowerDB.findBorrower(number);
        if(borrower == null){
            return "[오류] 이용자 " + number + " 없음.";
        }

        Book book = bookDB.findBook(catalogueNumber);
        if(book == null){
            return "[오류] 책 " + catalogueNumber + " 없음.";
        }

        if(!borrower.countCheck()){
            return "[오류] 이용자 대출 가능 권수 초과.";
        }

        if(Boolean.FALSE.equals(book.getState())){
            return "[오류] 이미 대출 중인 책: " + book.toString();
        }

        Loan loan = new Loan(book, borrower);
        loanDB.registerOneLoan(loan);
        borrower.bookLoanCount();

        return "대출 완료: " + borrower.toString() + " → " + book.toString();
    }

    /**
     * UC#6 : 책을 반납하는 메소드이다.
     * Iterator를 사용하여 목록번호로 대출 기록을 찾고,
     * 반납 이력을 생성한 후 이용자의 대출 권수를 감소시키고 책의 상태를 대출 가능으로 변경한다.
     * 마지막으로 대출 기록을 삭제한다.
     * 
     * @param catalogueNumber 반납할 책의 목록번호
     * @return 반납 완료 메시지 또는 오류 메시지
     */
    public String returnOneBook(int catalogueNumber){

        Loan loan = null;
        Iterator<Loan> it = loanDB.iterator();

        while(it.hasNext()){
            Loan temp = it.next();
            if(temp.getBook().getCatalogueNumber() == catalogueNumber){
                loan = temp;
                break;
            }
        }

        if(loan == null){
            return "[오류] 책 " + catalogueNumber + " 은(는) 대출 중이 아님.";
        }
        Borrower borrower = loan.getBorrower();
        Book book = loan.getBook();

        History history = new History(loan);
        hisDB.RegisterOneHis(history);

        borrower.returnCount();
        book.changeStatus(true);
        loanDB.removeLoan(loan);

        return "반납 완료: " + borrower.toString() + " → " + book.toString();
    }
    
    /**
     * UC#7 : 이용자의 반납 이력을 조회하는 메소드이다.
     * Iterator를 사용하여 반납 이력을 순회하고, 입력받은 고유번호와 일치하는 이용자의 최근 반납 이력을 반환한다.
     * 
     * @param number 조회할 이용자의 고유번호
     * @return 최근 반납 이력 정보 또는 이력 없음 메시지
     */
    public String displayReturnHistory(int number){

        Iterator<History> it = hisDB.iterator();

        while(it.hasNext()){
            History h = it.next();
            if(h.getLoan().getBorrower().getNumber() == number){
                return "최근 반납 이력: " + h.toString();
            }
        }

        return "이용자(" + number + ")의 반납 이력이 없습니다.";
    }
}