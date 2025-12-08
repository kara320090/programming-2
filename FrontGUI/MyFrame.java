package FrontGUI;

import javax.swing.*;
import CoreEngine.*;

/**
 * LibraryApplication의 프레임
 */
public class MyFrame extends JFrame {

    private LibraryApplication libApp; 
    private MyPanel panel;            
    /**
     * LibraryActionHandler 생성자.
     *
     * @param libApp 핵심 로직 처리를 위한 인스턴스
     * @param panel UI 상태 접근 및 업데이트를 위한 인스턴스
     */
    public MyFrame() {
        libApp = new LibraryApplication("선문대학교 중앙도서관");

        this.setTitle("도서관 관리 시스템");
        this.setSize(350, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // 화면 중앙 배치

        panel = new MyPanel();
        this.add(panel);

        LibraryActionHandler actionHandler = new LibraryActionHandler(libApp, panel);
        panel.getRunButton().addActionListener(actionHandler);
        panel.getClearButton().addActionListener(actionHandler);

        UseCaseSelectionListener ucListener = new UseCaseSelectionListener(panel);
        panel.getUcComboBox().addActionListener(ucListener);

        panel.updateUseCaseUI(panel.getSelectedUseCaseIndex());

        this.setVisible(true);
    }
}
