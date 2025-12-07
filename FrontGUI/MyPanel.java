package FrontGUI;

import javax.swing.*;
import java.awt.*;

/**
 * LibraryApplication의 패널
 * - 화면 구성 + 필드 상태 업데이트 메서드 제공
 * - 이벤트 처리는 외부 클래스에서 담당
 */
public class MyPanel extends JPanel {

    // 라벨
    private JLabel lblBorrowerName;
    private JLabel lblBookTitle;
    private JLabel lblBookAuthor;
    private JLabel lblBookID;
    private JLabel lblUseCase;

    // 입력 필드
    private JTextField tfBorrowerName;
    private JTextField tfBookTitle;
    private JTextField tfBookAuthor;
    private JTextField tfBookID;

    // 버튼
    private JButton btnRun;
    private JButton btnClear;

    // 출력 영역
    private JTextArea taOutput;

    // UC 선택 콤보박스
    private JComboBox<String> cbUseCase;
    private String[] useCases = {
        "UC1. 이용자 등록",
        "UC2. 책 등록",
        "UC3. 대출가능 책 목록 보기",
        "UC4. 대출중 책 목록 보기",
        "UC5. 책 대출",
        "UC6. 책 반납"
    };

    // 색상 정의
    private final Color requiredColor = new Color(255, 255, 200); // 연한 노랑
    private final Color disabledColor = new Color(230, 230, 230); // 연한 회색
    private final Color normalColor   = Color.WHITE;

    public MyPanel() {
        setLayout(new BorderLayout(5, 5));

        // ---------- 상단 입력 영역 ----------
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2, 5, 5)); // UseCase + 4개 필드

        lblUseCase = new JLabel("Use Case 선택");
        cbUseCase = new JComboBox<>(useCases);

        lblBorrowerName = new JLabel("이용자 이름");
        lblBookTitle    = new JLabel("책 제목");
        lblBookAuthor   = new JLabel("책 저자이름");
        lblBookID       = new JLabel("책 등록번호");

        tfBorrowerName = new JTextField(20);
        tfBookTitle    = new JTextField(20);
        tfBookAuthor   = new JTextField(20);
        tfBookID       = new JTextField(20);

        inputPanel.add(lblUseCase);
        inputPanel.add(cbUseCase);
        inputPanel.add(lblBorrowerName);
        inputPanel.add(tfBorrowerName);
        inputPanel.add(lblBookTitle);
        inputPanel.add(tfBookTitle);
        inputPanel.add(lblBookAuthor);
        inputPanel.add(tfBookAuthor);
        inputPanel.add(lblBookID);
        inputPanel.add(tfBookID);

        // ---------- 버튼 영역 ----------
        btnRun = new JButton("실행");
        btnClear = new JButton("Clear");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnRun);
        buttonPanel.add(btnClear);

        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(inputPanel, BorderLayout.CENTER);
        northPanel.add(buttonPanel, BorderLayout.SOUTH);

        // ---------- 출력 영역 ----------
        taOutput = new JTextArea(15, 30);
        taOutput.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(taOutput);

        add(northPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    // ===== 리스너에서 사용할 getter =====
    public JTextField getBorrowerNameField() { return tfBorrowerName; }
    public JTextField getBookTitleField()    { return tfBookTitle; }
    public JTextField getBookAuthorField()   { return tfBookAuthor; }
    public JTextField getBookIDField()       { return tfBookID; }

    public JButton getRunButton()            { return btnRun; }
    public JButton getClearButton()          { return btnClear; }

    public JComboBox<String> getUcComboBox() { return cbUseCase; }
    public int getSelectedUseCaseIndex()     { return cbUseCase.getSelectedIndex(); }

    public JTextArea getOutputArea()         { return taOutput; }

    // ===== 공통 유틸 =====

    public void clearInputFields() {
        tfBorrowerName.setText("");
        tfBookTitle.setText("");
        tfBookAuthor.setText("");
        tfBookID.setText("");
    }

    // UC 선택에 따라 입력칸 활성화/색상 변경
    public void updateUseCaseUI(int ucIndex) {
        // 우선 모두 비활성 상태로 초기화
        setFieldState(tfBorrowerName, false, disabledColor);
        setFieldState(tfBookTitle,    false, disabledColor);
        setFieldState(tfBookAuthor,   false, disabledColor);
        setFieldState(tfBookID,       false, disabledColor);

        // UC별로 필요한 필드만 활성화 + 색상 하이라이트
        switch (ucIndex) {
            case 0: // UC1. 이용자 등록 : 이용자 이름만
                setFieldState(tfBorrowerName, true, requiredColor);
                break;

            case 1: // UC2. 책 등록 : 책 제목, 책 저자, 책 등록번호
                setFieldState(tfBookTitle,  true, requiredColor);
                setFieldState(tfBookAuthor, true, requiredColor);
                setFieldState(tfBookID,     true, requiredColor);
                break;

            case 2: // UC3. 대출가능 책 목록 보기 : 입력 필요 없음
            case 3: // UC4. 대출중 책 목록 보기 : 입력 필요 없음
                // 모두 비활성 상태 유지
                break;

            case 4: // UC5. 책 대출 : 이용자 이름, 책 등록번호
                setFieldState(tfBorrowerName, true, requiredColor);
                setFieldState(tfBookID,       true, requiredColor);
                break;

            case 5: // UC6. 책 반납 : 책 등록번호만
                setFieldState(tfBookID, true, requiredColor);
                break;

            default:
                // 혹시 모를 예외상황에서는 모두 활성화 (기본)
                setFieldState(tfBorrowerName, true, normalColor);
                setFieldState(tfBookTitle,    true, normalColor);
                setFieldState(tfBookAuthor,   true, normalColor);
                setFieldState(tfBookID,       true, normalColor);
        }
    }

    private void setFieldState(JTextField field, boolean editable, Color bg) {
        field.setEditable(editable);
        field.setBackground(bg);
    }
}
