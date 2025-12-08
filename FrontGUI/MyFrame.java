package FrontGUI;

import javax.swing.*;
import CoreEngine.*;

/**
 * LibraryApplication의 프레임
 */
public class MyFrame extends JFrame {

    private LibraryApplication libApp; // LA 인스턴스
    private MyPanel panel;            // 메인 패널
    /**
     * LibraryActionHandler 생성자.
     *
     * @param libApp 핵심 로직 처리를 위한 인스턴스
     * @param panel UI 상태 접근 및 업데이트를 위한 인스턴스
     */
    public MyFrame() {
        // 1) LA 인스턴스 생성 (CoreEngine/LA는 변경 없음)
        libApp = new LibraryApplication("선문대학교 중앙도서관");

        // 2) 프레임 기본 설정
        this.setTitle("도서관 관리 시스템");
        this.setSize(350, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // 화면 중앙 배치

        // 3) 패널 생성 및 부착
        panel = new MyPanel();
        this.add(panel);

        // 4) 외부 리스너 생성 및 각 컴포넌트에 등록
        LibraryActionHandler actionHandler = new LibraryActionHandler(libApp, panel);
        panel.getRunButton().addActionListener(actionHandler);
        panel.getClearButton().addActionListener(actionHandler);

        UseCaseSelectionListener ucListener = new UseCaseSelectionListener(panel);
        panel.getUcComboBox().addActionListener(ucListener);

        // 최초 UC0 기준으로 필드 구성
        panel.updateUseCaseUI(panel.getSelectedUseCaseIndex());

        // 5) 최종 표시
        this.setVisible(true);
    }
}
