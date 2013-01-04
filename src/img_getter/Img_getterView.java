/*
 * Img_getterView.java
 */
package img_getter;

import img_getter.img.ClipboardListener;
import img_getter.img.handler.BaseHandler;
import img_getter.img.handler.SwingHandler;
import img_getter.img.ImgSeqGetter;
import img_getter.img.handler.HtmlHandler;
import img_getter.img.handler.ContentHandler;
import img_getter.img.handler.MsnImgHandler;
import img_getter.img.parser.BaseParser;
import img_getter.img.parser.HtmlParser;
import img_getter.img.parser.ContentParser;
import img_getter.img.parser.SwingParser;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import org.jdesktop.application.Action;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * The application's main frame.
 */
public class Img_getterView extends FrameView {

    private BaseParser parser;
    private BaseHandler handler;

    public Img_getterView(SingleFrameApplication app) {
        super(app);

        initComponents();
        setDefaultParser();
        Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
        ClipboardListener cl = new ClipboardListener(this, sysClip);
        sysClip.addFlavorListener(cl);
    }

    public void setDefaultParser() {
        if (!(parser instanceof ContentParser)) {
            parser = new ContentParser(this);
            log("已设解析器为: ContentParser。");
        }
        handler = new ContentHandler();
        parser.setHandler(handler);
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = Img_getterApp.getApplication().getMainFrame();
            aboutBox = new Img_getterAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        Img_getterApp.getApplication().show(aboutBox);
    }

    public void updateRex() {
        if (fromText.getText().length() == 0 && toText.getText().length() == 0) {
            rex.setSelected(false);
        } else {
            rex.setSelected(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        urlText = new javax.swing.JTextField();
        downloadButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        logTA = new javax.swing.JTextArea();
        rex = new javax.swing.JCheckBox();
        fromText = new javax.swing.JTextField();
        toText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        imgBeginUrl = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        imgFrom = new javax.swing.JTextField();
        imgTo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        imgEndUrl = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        seqLogTA = new javax.swing.JTextArea();
        jButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        pathText = new javax.swing.JTextField();
        widthText = new javax.swing.JTextField();
        heightText = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        imgFormats = new javax.swing.JTextField();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        swingParserMenu = new javax.swing.JMenuItem();
        msnSwingParserMenu = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        htmlParserMenu = new javax.swing.JMenuItem();
        contentParserMenu = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();

        mainPanel.setName("mainPanel"); // NOI18N

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(img_getter.Img_getterApp.class).getContext().getResourceMap(Img_getterView.class);
        urlText.setText(resourceMap.getString("urlText.text")); // NOI18N
        urlText.setName("urlText"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(img_getter.Img_getterApp.class).getContext().getActionMap(Img_getterView.class, this);
        downloadButton.setAction(actionMap.get("listImg")); // NOI18N
        downloadButton.setText(resourceMap.getString("downloadButton.text")); // NOI18N
        downloadButton.setName("downloadButton"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        logTA.setColumns(20);
        logTA.setRows(5);
        logTA.setName("logTA"); // NOI18N
        jScrollPane1.setViewportView(logTA);

        rex.setText(resourceMap.getString("rex.text")); // NOI18N
        rex.setName("rex"); // NOI18N

        fromText.setText(resourceMap.getString("fromText.text")); // NOI18N
        fromText.setName("fromText"); // NOI18N
        fromText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fromTextKeyTyped(evt);
            }
        });

        toText.setName("toText"); // NOI18N
        toText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                toTextKeyTyped(evt);
            }
        });

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jButton3.setAction(actionMap.get("emptyTA")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(fromText, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(toText, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(rex)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 310, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(urlText, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(downloadButton)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(urlText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(downloadButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rex)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fromText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(toText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        jPanel2.setName("jPanel2"); // NOI18N

        imgBeginUrl.setText(resourceMap.getString("imgBeginUrl.text")); // NOI18N
        imgBeginUrl.setName("imgBeginUrl"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        imgFrom.setText(resourceMap.getString("imgFrom.text")); // NOI18N
        imgFrom.setName("imgFrom"); // NOI18N

        imgTo.setText(resourceMap.getString("imgTo.text")); // NOI18N
        imgTo.setName("imgTo"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        imgEndUrl.setText(resourceMap.getString("imgEndUrl.text")); // NOI18N
        imgEndUrl.setName("imgEndUrl"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jButton4.setAction(actionMap.get("listSeqImg")); // NOI18N
        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        seqLogTA.setColumns(20);
        seqLogTA.setRows(5);
        seqLogTA.setName("seqLogTA"); // NOI18N
        jScrollPane2.setViewportView(seqLogTA);

        jButton5.setAction(actionMap.get("emptyTA")); // NOI18N
        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setName("jButton5"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 284, Short.MAX_VALUE)
                                .addComponent(jButton5))
                            .addComponent(imgBeginUrl, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(imgFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(imgTo, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(imgEndUrl, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(imgBeginUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(imgFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(imgTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imgEndUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jPanel3.setName("jPanel3"); // NOI18N

        jButton2.setAction(actionMap.get("chosePath")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N

        pathText.setText(resourceMap.getString("pathText.text")); // NOI18N
        pathText.setName("pathText"); // NOI18N

        widthText.setText(resourceMap.getString("widthText.text")); // NOI18N
        widthText.setName("widthText"); // NOI18N

        heightText.setText(resourceMap.getString("heightText.text")); // NOI18N
        heightText.setName("heightText"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        imgFormats.setText(resourceMap.getString("imgFormats.text")); // NOI18N
        imgFormats.setName("imgFormats"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imgFormats, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(pathText, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(widthText, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(heightText, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addComponent(jLabel9))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pathText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(widthText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(heightText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imgFormats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(302, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        jMenu1.setText(resourceMap.getString("jMenu1.text")); // NOI18N
        jMenu1.setName("jMenu1"); // NOI18N

        swingParserMenu.setText(resourceMap.getString("swingParserMenu.text")); // NOI18N
        swingParserMenu.setName("swingParserMenu"); // NOI18N
        swingParserMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                swingParserMenuActionPerformed(evt);
            }
        });
        jMenu1.add(swingParserMenu);

        msnSwingParserMenu.setText(resourceMap.getString("msnSwingParserMenu.text")); // NOI18N
        msnSwingParserMenu.setName("msnSwingParserMenu"); // NOI18N
        msnSwingParserMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msnSwingParserMenuActionPerformed(evt);
            }
        });
        jMenu1.add(msnSwingParserMenu);

        jSeparator1.setName("jSeparator1"); // NOI18N
        jMenu1.add(jSeparator1);

        htmlParserMenu.setText(resourceMap.getString("htmlParserMenu.text")); // NOI18N
        htmlParserMenu.setName("htmlParserMenu"); // NOI18N
        htmlParserMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                htmlParserMenuActionPerformed(evt);
            }
        });
        jMenu1.add(htmlParserMenu);

        contentParserMenu.setText(resourceMap.getString("contentParserMenu.text")); // NOI18N
        contentParserMenu.setName("contentParserMenu"); // NOI18N
        contentParserMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contentParserMenuActionPerformed(evt);
            }
        });
        jMenu1.add(contentParserMenu);

        menuBar.add(jMenu1);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setComponent(mainPanel);
        setMenuBar(menuBar);
    }// </editor-fold>//GEN-END:initComponents

    private void msnSwingParserMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_msnSwingParserMenuActionPerformed
        if (!(parser instanceof SwingParser)) {
            parser = new SwingParser(this);
        }
        handler = new MsnImgHandler();
        parser.setHandler(handler);
        log("已设置图片过滤器为：Msn 汽车。适用于解析");
}//GEN-LAST:event_msnSwingParserMenuActionPerformed

    private void swingParserMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_swingParserMenuActionPerformed
        if (!(parser instanceof SwingParser)) {
            parser = new SwingParser(this);
        }
        handler = new SwingHandler();
        parser.setHandler(handler);
        log("已设解析器为: SwingParser。适用于快速解析，不能解析xhtml 以及 不规范的html");
}//GEN-LAST:event_swingParserMenuActionPerformed

    private void htmlParserMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_htmlParserMenuActionPerformed
        if (!(parser instanceof HtmlParser)) {
            parser = new HtmlParser(this);
            log("已设解析器为: HtmlParser。适用于xhtml和不规范的网页，解析速度较SwingParser慢。");
        }
        handler = new HtmlHandler();
        parser.setHandler(handler);
    }//GEN-LAST:event_htmlParserMenuActionPerformed

    private void fromTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fromTextKeyTyped
        updateRex();
    }//GEN-LAST:event_fromTextKeyTyped

    private void toTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_toTextKeyTyped
        updateRex();
    }//GEN-LAST:event_toTextKeyTyped

    private void contentParserMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contentParserMenuActionPerformed
        if (!(parser instanceof ContentParser)) {
            parser = new ContentParser(this);
            log("已设解析器为: ContentParser。");
        }
        handler = new ContentHandler();
        parser.setHandler(handler);
    }//GEN-LAST:event_contentParserMenuActionPerformed

    public void enableDownloadButton(boolean val) {
        downloadButton.setEnabled(val);
    }

    @Action
    public void listImg() {        
        enableDownloadButton(false);
        Thread t = new Thread(parser);
        t.start();
    }

    public void selectRegx() {
        rex.setSelected(true);
    }

    public void unSelectRegx() {
        rex.setSelected(false);
    }

    public boolean enableRex() {
        if (rex.getSelectedObjects() == null) {
            return false;
        }
        return rex.getSelectedObjects().length == 1;
    }

    public String getFromPattern() {
        return fromText.getText();
    }

    public String getToPattern() {
        return toText.getText();
    }

    public synchronized void log(String text) {
        logTA.setText(text + "\r\n" + logTA.getText());
        seqLogTA.setText(text + "\r\n" + seqLogTA.getText());
    }

    public String getMinWidth() {
        return widthText.getText();
    }

    public String getMinHeight() {
        return heightText.getText();
    }

    public String getPath() {
        return pathText.getText() + "/";
    }

    public String getUrl() {
        return urlText.getText();
    }

    public void setUrl(String _url) {
        urlText.setText(_url);
        urlText.requestFocus();       
    }
    
    public String getImgBeginUrl() {
        return imgBeginUrl.getText();
    }

    public String getImgFrom() {
        return imgFrom.getText();
    }

    public String getImgTo() {
        return imgTo.getText();
    }

    public String getImgEndUrl() {
        return imgEndUrl.getText();
    }

    public String getImgFormats() {
        return imgFormats.getText();
    }

    @Action
    public void emptyTA() {
        logTA.setText("");
        seqLogTA.setText("");
    }

    @Action
    public void chosePath() {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = jfc.showOpenDialog(jPanel1);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            pathText.setText(jfc.getSelectedFile().getAbsolutePath());
        }
    }

    @Action
    public void listSeqImg() {
        ImgSeqGetter isg = new ImgSeqGetter(this);
        isg.start();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem contentParserMenu;
    private javax.swing.JButton downloadButton;
    private javax.swing.JTextField fromText;
    private javax.swing.JTextField heightText;
    private javax.swing.JMenuItem htmlParserMenu;
    private javax.swing.JTextField imgBeginUrl;
    private javax.swing.JTextField imgEndUrl;
    private javax.swing.JTextField imgFormats;
    private javax.swing.JTextField imgFrom;
    private javax.swing.JTextField imgTo;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea logTA;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem msnSwingParserMenu;
    private javax.swing.JTextField pathText;
    private javax.swing.JCheckBox rex;
    private javax.swing.JTextArea seqLogTA;
    private javax.swing.JMenuItem swingParserMenu;
    private javax.swing.JTextField toText;
    private javax.swing.JTextField urlText;
    private javax.swing.JTextField widthText;
    // End of variables declaration//GEN-END:variables
    private JDialog aboutBox;
}
