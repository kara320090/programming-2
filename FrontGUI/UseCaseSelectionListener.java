package FrontGUI;

import java.awt.event.*;

/**
 * UC 콤보박스 선택 변경 전용 리스너
 * - 선택된 UC에 따라 MyPanel의 입력칸 상태/색상 변경
 */
public class UseCaseSelectionListener implements ActionListener {

    private MyPanel panel;
    
    /**
     * UseCaseSelectionListener 생성자.
     *
     * @param panel UI를 업데이트할 대상 {@link MyPanel} 객체
     */
    public UseCaseSelectionListener(MyPanel panel) {
        this.panel = panel;
    }
    /**
     * 콤보박스 선택 변경 시 호출됩니다.
     * 입력 필드를 초기화하고, 새로운 UC에 맞춰 UI 상태를 재설정합니다.
     *
     * @param e 액션 이벤트 객체
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int ucIndex = panel.getSelectedUseCaseIndex();

        // UC가 바뀌면 입력칸 내용은 초기화
        panel.clearInputFields();
        // UC에 따라 활성화/색상 재설정
        panel.updateUseCaseUI(ucIndex);
    }
}