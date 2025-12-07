package FrontGUI;

import java.awt.event.*;

/**
 * UC 콤보박스 선택 변경 전용 리스너
 * - 선택된 UC에 따라 MyPanel의 입력칸 상태/색상 변경
 */
public class UseCaseSelectionListener implements ActionListener {

    private MyPanel panel;

    public UseCaseSelectionListener(MyPanel panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int ucIndex = panel.getSelectedUseCaseIndex();

        // UC가 바뀌면 입력칸 내용은 초기화
        panel.clearInputFields();
        // UC에 따라 활성화/색상 재설정
        panel.updateUseCaseUI(ucIndex);
    }
}