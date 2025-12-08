package FrontGUI;

import java.awt.event.*;
import CoreEngine.*;

/**
 * LibraryApplication을 사용하는 외부 이벤트 리스너
 * - "실행" / "Clear" 버튼 처리
 * - LA의 7개 UC 메서드 호출
 */
public class LibraryActionHandler implements ActionListener {

    private LibraryApplication libApp;
    private MyPanel panel;
    /**
     * LibraryActionHandler 생성자.
     *
     * @param libApp 핵심 로직 객체
     * @param panel UI 패널 객체
     */
    public LibraryActionHandler(LibraryApplication libApp, MyPanel panel) {
        this.libApp = libApp;
        this.panel = panel;
    }
    /**
     * '실행' 또는 'Clear' 버튼 클릭 시 호출됩니다.
     *
     * @param e 액션 이벤트 객체
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == panel.getRunButton()) {
            handleRun();
        } else if (src == panel.getClearButton()) {
            handleClear();
        }
    }

    /**
     * * UC에 따른 입력값을 받아 메서드를 호출하고 결과를 출력창에 표시합니다.
     */
    private void handleRun() {
        int ucIndex = panel.getSelectedUseCaseIndex();

        String borrowerName   = panel.getBorrowerNameField().getText();
        String borrowerNumber = panel.getBorrowerNumberField().getText();
        String title          = panel.getBookTitleField().getText();
        String author         = panel.getBookAuthorField().getText();
        String bookID         = panel.getBookIDField().getText();

        String result;

        try {
            switch (ucIndex) {
                case 0: { // UC1: 이용자 등록 (이용자 이름 + 이용자 번호)
                    int number = Integer.parseInt(borrowerNumber);
                    result = libApp.registerOneBorrower(borrowerName, number);
                    break;
                }
                case 1: { // UC2: 책 등록 (제목, 저자, 책 등록번호)
                    int catalogueNumber = Integer.parseInt(bookID);
                    result = libApp.registerOneBook(title, author, catalogueNumber);
                    break;
                }
                case 2: // UC3: 대출가능 책 목록 Display
                    result = libApp.displayBookForLoan();
                    break;
                case 3: // UC4: 대출중인 책 목록 Display
                    result = libApp.displayBookOnLoan();
                    break;
                case 4: { // UC5: 책 1권 대출 (이용자 번호, 책 목록번호)
                    int number = Integer.parseInt(borrowerNumber);
                    int catalogueNumber = Integer.parseInt(bookID);
                    result = libApp.loanOneBook(number, catalogueNumber);
                    break;
                }
                case 5: { // UC6: 책 1권 반납 (책 목록번호)
                    int catalogueNumber = Integer.parseInt(bookID);
                    result = libApp.returnOneBook(catalogueNumber);
                    break;
                }
                case 6: { // UC7: 이용자 반납 이력 조회 (이용자 번호)
                    int number = Integer.parseInt(borrowerNumber);
                    result = libApp.displayReturnHistory(number);
                    break;
                }
                default:
                    result = "지원되지 않는 Use Case입니다.";
            }
        } catch (NumberFormatException ex) {
            result = "[입력 오류] 숫자 형식이 올바르지 않습니다.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[선택된 UC] ")
          .append(panel.getUcComboBox().getSelectedItem())
          .append("\n");
        sb.append("[LibraryApplication 메시지]\n")
          .append(result)
          .append("\n");
        sb.append("입력값 요약:\n")
          .append(" - 이용자 이름: ").append(borrowerName).append("\n")
          .append(" - 이용자 번호: ").append(borrowerNumber).append("\n")
          .append(" - 책 제목: ").append(title).append("\n")
          .append(" - 책 저자: ").append(author).append("\n")
          .append(" - 책 등록번호: ").append(bookID).append("\n")
          .append("----------------------------------------\n");

        panel.getOutputArea().append(sb.toString());
    }
    /**
     * * 입력 필드 내용을 초기화합니다.
     */ 
    private void handleClear() {
        panel.clearInputFields();
    }
}
