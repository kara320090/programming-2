package FrontGUI;

import java.awt.event.*;
import CoreEngine.*;

/**
 * LibraryApplication을 사용하는 외부 이벤트 리스너
 * - "실행" / "Clear" 버튼 처리
 * - LA의 6개 UC 메서드 호출
 */
public class LibraryActionHandler implements ActionListener {

    private LibraryApplication libApp;
    private MyPanel panel;

    public LibraryActionHandler(LibraryApplication libApp, MyPanel panel) {
        this.libApp = libApp;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == panel.getRunButton()) {
            handleRun();
        } else if (src == panel.getClearButton()) {
            handleClear();
        }
    }

    // "실행" 버튼: 선택된 UC에 따라 LA 메서드 호출
    private void handleRun() {
        int ucIndex = panel.getSelectedUseCaseIndex();

        String borrowerName = panel.getBorrowerNameField().getText();
        String title        = panel.getBookTitleField().getText();
        String author       = panel.getBookAuthorField().getText();
        String bookID       = panel.getBookIDField().getText();

        String result;

        switch (ucIndex) {
            case 0: // UC1: 이용자 등록
                result = libApp.registerOneBorrower(borrowerName);
                break;
            case 1: // UC2: 책 등록
                result = libApp.registerOneBook(title, author, bookID);
                break;
            case 2: // UC3: 대출가능 책 목록 Display
                result = libApp.displayBookForLoan();
                break;
            case 3: // UC4: 대출중인 책 목록 Display
                result = libApp.displayBookOnLoan();
                break;
            case 4: // UC5: 책 1권 대출
                result = libApp.loanOneBook(borrowerName, bookID);
                break;
            case 5: // UC6: 책 1권 반납
                result = libApp.returnOneBook(bookID);
                break;
            default:
                result = "지원되지 않는 Use Case입니다.";
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
          .append(" - 책 제목: ").append(title).append("\n")
          .append(" - 책 저자: ").append(author).append("\n")
          .append(" - 책 등록번호: ").append(bookID).append("\n")
          .append("----------------------------------------\n");

        panel.getOutputArea().append(sb.toString());
    }

    // "Clear" 버튼: 입력 필드 초기화
    private void handleClear() {
        panel.clearInputFields();
        // 필요하면 출력까지 지우고 싶을 때:
        // panel.getOutputArea().setText("");
    }
}
