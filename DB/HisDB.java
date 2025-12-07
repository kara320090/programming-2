package DB;
import Object.History;
import java.util.TreeSet;
import java.util.Iterator;
/**
 * HisDb 클래스의 설명을 작성하세요.
 *
 * @author (1팀)
 * @version (2025.12.5)
 */
public class HisDB
{
    private TreeSet<History> histories;
    
    /**
     * 반납 이력 관리를 위한 객체 생성자이다.
     * 반납 이력 객체들을 TreeSet 형태로 저장하여 관리한다.
     */
    public HisDB(){
        this.histories = new TreeSet<>();

    }
    
    /**
     * 새로운 반납 이력을 등록하는 메소드이다.
     * 
     * @param history 등록할 반납 이력 객체
     */
    public void RegisterOneHis(History history){
        histories.add(history);
    }
    
    /**
     * 이용자 고유번호를 사용해서 반납 이력을 찾는 메소드이다.
     * 이력 리스트에서 반납 이력 객체를 하나씩 꺼내고
     * 받아온 고유번호와 저장된 이용자의 고유번호를 비교하여 같으면 해당 이력을 반환하고 다르면 NULL을 반환한다.
     * 
     * @param number 검색할 이용자의 고유번호
     * @return 찾은 반납 이력 객체, 없으면 null
     */
    public History findHistory(int number){
        Iterator<History> it = histories.iterator();
        while(it.hasNext()){
            History h = it.next();
            if(h.getLoan().getBorrower().getNumber() == number){
                return h;
            }
        }
        return null;
    }

    /**
     * LibraryApplication에서 반납 이력 목록을 순회하기 위한 iterator를 반환한다.
     * 
     * @return 반납 이력 컬렉션의 iterator
     */
    public Iterator<History> iterator(){
        return histories.iterator();
    }
}