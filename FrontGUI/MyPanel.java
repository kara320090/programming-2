package FrontGUI;

import javax.swing.*;
import java.awt.*;

/**
 * LibraryApplication의 패널
 * - 화면 구성 + 필드 상태 업데이트 메서드 제공
 * - 이벤트 처리는 외부 클래스에서 담당
 */
public class MyPanel extends JPanel {

    private JLabel lblBorrowerName;
    private JLabel lblBorrowerNumber;   // 추가: 이용자 번호
    private JLabel lblBookTitle;
    private JLabel lblBookAuthor;
    private JLabel lblBookID;
    private JLabel lblUseCase;

    private JTextField tfBorrowerName;
    private JTextField tfBorrowerNumber; // 추가
    private JTextField tfBookTitle;
    private JTextField tfBookAuthor;
    private JTextField tfBookID;

    private JButton btnRun;
    private JButton btnClear;

    private JTextArea taOutput;

    private JComboBox<String> cbUseCase;
    private String[] useCases = {
        "UC1. 이용자 등록",
        "UC2. 책 등록",
        "UC3. 대출가능 책 목록 보기",
        "UC4. 대출중 책 목록 보기",
        "UC5. 책 대출",
        "UC6. 책 반납",
        "UC7. 반납 이력 조회"
    };

    private final Color requiredColor = new Color(255, 255, 200); // 연한 노랑
    private final Color disabledColor = new Color(230, 230, 230); // 연한 회색
    private final Color normalColor   = Color.WHITE;
    /**
     * MyPanel 생성자.
     * 모든 UI 컴포넌트를 생성하고 배치합니다.
     */
    public MyPanel() {
        setLayout(new BorderLayout(5, 5));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2, 5, 5));

        lblUseCase        = new JLabel("Use Case 선택");
        cbUseCase         = new JComboBox<>(useCases);
        lblBorrowerName   = new JLabel("이용자 이름");
        lblBorrowerNumber = new JLabel("이용자 번호");
        lblBookTitle      = new JLabel("책 제목");
        lblBookAuthor     = new JLabel("책 저자이름");
        lblBookID         = new JLabel("책 등록번호");

        tfBorrowerName   = new JTextField(20);
        tfBorrowerNumber = new JTextField(20);
        tfBookTitle      = new JTextField(20);
        tfBookAuthor     = new JTextField(20);
        tfBookID         = new JTextField(20);

        inputPanel.add(lblUseCase);
        inputPanel.add(cbUseCase);

        inputPanel.add(lblBorrowerName);
        inputPanel.add(tfBorrowerName);

        inputPanel.add(lblBorrowerNumber);
        inputPanel.add(tfBorrowerNumber);

        inputPanel.add(lblBookTitle);
        inputPanel.add(tfBookTitle);

        inputPanel.add(lblBookAuthor);
        inputPanel.add(tfBookAuthor);

        inputPanel.add(lblBookID);
        inputPanel.add(tfBookID);

        btnRun = new JButton("실행");
        btnClear = new JButton("Clear");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnRun);
        buttonPanel.add(btnClear);

        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(inputPanel, BorderLayout.CENTER);
        northPanel.add(buttonPanel, BorderLayout.SOUTH);

        taOutput = new JTextArea(15, 30);
        taOutput.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(taOutput);

        add(northPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public JTextField getBorrowerNameField(){ 
        return tfBorrowerName; 
    }
    public JTextField getBorrowerNumberField(){ 
        return tfBorrowerNumber; 
    }
    public JTextField getBookTitleField(){ 
        return tfBookTitle; 
    }
    public JTextField getBookAuthorField(){ 
        return tfBookAuthor; 
    }
    public JTextField getBookIDField(){ 
        return tfBookID; 
    }

    public JButton getRunButton(){ 
        return btnRun; 
    }
    public JButton getClearButton(){ 
        return btnClear; 
    }

    public JComboBox<String> getUcComboBox(){ 
        return cbUseCase; 
    }
    public int getSelectedUseCaseIndex(){ 
        return cbUseCase.getSelectedIndex(); 
    }

    public JTextArea getOutputArea(){ 
        return taOutput; 
    }

    /**
     * 모든 입력 필드의 내용을 초기화합니다.
     */
    public void clearInputFields() {
        tfBorrowerName.setText("");
        tfBorrowerNumber.setText("");
        tfBookTitle.setText("");
        tfBookAuthor.setText("");
        tfBookID.setText("");
    }
    /**
     * Use Case 선택에 따라 입력 필드의 활성화 상태와 배경색을 업데이트합니다.
     *
     * @param ucIndex 현재 선택된 Use Case의 인덱스
     */
    public void updateUseCaseUI(int ucIndex) {
        setFieldState(tfBorrowerName, false, disabledColor);
        setFieldState(tfBorrowerNumber, false, disabledColor);
        setFieldState(tfBookTitle, false, disabledColor);
        setFieldState(tfBookAuthor, false, disabledColor);
        setFieldState(tfBookID, false, disabledColor);

        switch (ucIndex) {
            case 0: // UC1. 이용자 등록 : 이용자 이름, 이용자 번호
                setFieldState(tfBorrowerName,   true, requiredColor);
                setFieldState(tfBorrowerNumber, true, requiredColor);
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

            case 4: // UC5. 책 대출 : 이용자 번호, 책 등록번호
                setFieldState(tfBorrowerNumber, true, requiredColor);
                setFieldState(tfBookID,         true, requiredColor);
                break;

            case 5: // UC6. 책 반납 : 책 등록번호만
                setFieldState(tfBookID, true, requiredColor);
                break;

            case 6: // UC7. 반납 이력 조회 : 이용자 번호만
                setFieldState(tfBorrowerNumber, true, requiredColor);
                break;

            default:
                // 혹시 모를 예외상황에서는 모두 활성화 (기본)
                setFieldState(tfBorrowerName,   true, normalColor);
                setFieldState(tfBorrowerNumber, true, normalColor);
                setFieldState(tfBookTitle,      true, normalColor);
                setFieldState(tfBookAuthor,     true, normalColor);
                setFieldState(tfBookID,         true, normalColor);
        }
    }
    /**
     * 텍스트 필드의 상태(편집 가능 여부, 배경색)를 설정합니다.
     *
     * @param field    상태를 변경할 {@link JTextField} 객체
     * @param editable 필드의 **편집 가능 여부** (true: 활성화, false: 비활성화)
     * @param bg       필드에 적용할 **배경색**
     */
    private void setFieldState(JTextField field, boolean editable, Color bg) {
        field.setEditable(editable);
        field.setBackground(bg);
    }
}
