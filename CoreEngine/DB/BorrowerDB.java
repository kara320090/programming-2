package DB;
import Object.Borrower;
import java.util.TreeSet;
import java.util.Iterator;
/**
 * 이용자 관리(등록, 삭제 등)를 위한 동작을 가지고 있는 클래스이다
 * 이용자를 객체 단위로 생성하며 이를 ArrayList 형태로 저장하여 이용자 객체들을 관리한다.
 *
 * @author (1팀)
 * @version (2025.12.5)
 */
public class BorrowerDB
{
    private TreeSet<Borrower> borrowers;
    /** 이용자 관리를 위한 객체 생성자이다.
     * 공용 스캐너 사용으로 값을 받고 이후 다른 메소드에서 사용할 수 있도록 한다.
     * 이용자 객체들의 저장, 검색, 등록, 삭제 등을 관리하기 위해서 리스트 형태로 객체의 속성을 지정해준다.
     * 
     */
    public BorrowerDB(){
        this.borrowers = new TreeSet<>();
    }
    
    /** BorrowerDB에 Borrower 객체를 추가하는 메소드이다. 
     * 
     * @param  Borrower 객체 1개
     */
    public void add(Borrower borrower) {
        borrowers.add(borrower);
    }

    /** 고유번호를 사용해서 이용자를 찾는 메소드이다.
     * 메소드가 실행되면 받아온 이용자 리스트에서 이용자 객체를 하나씩 꺼내고 
     * 받아온 고유번호와 저장된 이용자의 고유 번호를 비교하여 같으면 해당 이용자를 반환하고 다르면 NULL을 반환한다.
     * 이용자의 등록유무 확인, 대출, 반납 등의 기능에서 사용할 수 있다.
     */
    public Borrower findBorrower(int number){
        Iterator<Borrower> it = borrowers.iterator();
        while(it.hasNext()){
            Borrower b = it.next();
            if(b.getNumber() == number){
                return b;
            }
        }
        return null;
    }

    /**
     * 임시 데이터셋
     */
    public void addSampleBorrowers(){
        this.borrowers.add(new Borrower("홍길동", 1));
        this.borrowers.add(new Borrower("김철수", 2));
        this.borrowers.add(new Borrower("이영희", 3));
        this.borrowers.add(new Borrower("박지민", 4));
        this.borrowers.add(new Borrower("박동길", 5));
    }
}
