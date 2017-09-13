/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package romdz09;

import SevenZip.LzmaAlone;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class ris1 extends javax.swing.JFrame {

    /**
     * Creates new form ris1
     */
    class GPanel extends JPanel {

        public GPanel() {
            setPreferredSize(new Dimension(300, 300));
            addMouseWheelListener(new java.awt.event.MouseWheelListener() {
                public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                    int i = (Integer) jcd1.getValue() - evt.getWheelRotation();
                    if (i < 0) {
                        i = 0;
                    }
                    jcd1.setValue(i);

                    /*   pany+=-evt.getWheelRotation()*pan.getHeight()/10;
                     if (pany>0) pany=0;
                     ris( x10,  x20,  type0);*/
                }
            });
            addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3
                            || evt.getButton() == java.awt.event.MouseEvent.BUTTON2) {
                        jcd1.setValue(0);
                    }
                }
            });
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (!repaintda) {
                return;
            }
            //rectangle originated at 10,10 and end at 240,240
            //g.drawRect(10, 10, 240, 240);
            //filled Rectangle with rounded corners.    
            //g.fillRoundRect(50, 50, 100, 100, 80, 80);
            if (bb != null) {
                //myLog.Systemoutprintln("ris2222bb--null");
                ris2(g, x10, x20, type0, false);
            } else {
                //myLog.Systemoutprintln("bb--null");
            }
            if (m2 != null && type0 < 100) {
                for (int i = 0; i < m2.size(); i++) {
                    g.setColor((Color) m2.get(i));
                    int k10 = 10;
                    if (i < m2a.size()) {
                        k10 = (Integer) m2a.get(i) * 10;
                    }
                    g.fillRect(this.getWidth() - k10, i * 4 + pany, k10, 4);
                }
            }
        }
    }
    boolean repaintda = true;
    JPanel pan;

    public ris1() {

        initComponents();

        if (frtxt1 == null) {
            frtxt1 = new txt1();
        }
        frtxt1.fr = fr;
        jprogress.setVisible(false);
        int ii = jb3.getComponentCount();
        for (int i = 0; i < ii; i++) {
            jb3.getComponent(i).addMouseListener(
                    new java.awt.event.MouseAdapter() {
                        public void mouseReleased(final MouseEvent e) {
                            // add code here
                            if ((e.getModifiers() & java.awt.event.ActionEvent.SHIFT_MASK) != 0) {
                                ris(0, pan.getWidth(), type0);
                            }

                        }
                    }
            );
        }
        ii = jxm.getComponentCount();
        for (int i = 0; i < ii; i++) {
            jxm.getComponent(i).addMouseListener(
                    new java.awt.event.MouseAdapter() {
                        public void mouseReleased(final MouseEvent e) {
                            // add code here
                            if ((e.getModifiers() & java.awt.event.ActionEvent.SHIFT_MASK) != 0) {
                                ris(0, pan.getWidth(), type0);
                            }

                        }
                    }
            );
        }
        pan = new GPanel();
        panp.setLayout(new BoxLayout(panp, BoxLayout.PAGE_AXIS));
        panp.add(pan);
        jScrollPane4.setVisible(false);
        pan.setVisible(true);
        //jpanadv.setVisible(false);
        jextract.setVisible(false);
        jfromf.setVisible(false);
        jfromf1.setVisible(false);
        jnegative.setVisible(false);
        jfsize.setVisible(false);
        tim = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (type0 == 300) {
                    pan.repaint();
                    // myLog.Systemoutprintln("rrrrrrrrrrrrrrrr=="+iimg);
                } else {
                    tim.stop();
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        //pan.setBackground(Color.red);   
    }

    public void setlang(int ii) {
        if (ii == 0) {
            jextract.setText("extract");
            jextract.setToolTipText("extract resource to file");
            jfromf.setText("import");
            jfromf.setToolTipText("import resource from file");
            jfromf1.setText("from file");
            jfromf1.setToolTipText("import resource from file");
            jnegative.setText("to negative");
            jnegative.setToolTipText("convert picture to negative");
            jfsize.setToolTipText("set font size");
            jtofile.setText("to file");
            jtofile.setToolTipText("save changes to file with extention .b");
            jtorom.setText("to rom");
            jtorom.setToolTipText("to changes to rom");
            jcommands.setText("commands");
            jcommands.setToolTipText("get commands window");
            jrestore.setText("restore");
            jrestore.setToolTipText("restore file with extention .b from file .a");
            fils.setToolTipText("rom parts on disk");
            jef.setToolTipText("pictures and other resources");
            jtablo.setToolTipText("info");
            jcd00.setToolTipText("scroll to default place");
            jcd1.setToolTipText("scroll");
            jtab.setToolTipText("you can modify txt column. Size of text is in column width");
        } else {
            jextract.setText("извлечь");
            jextract.setToolTipText("извлечь ресурс в файл");
            jfromf.setText("импорт");
            jfromf.setToolTipText("импорт ресурса из файла");
            jfromf1.setText("из файла");
            jfromf1.setToolTipText("импорт ресурса из файла");
            jnegative.setText("негатив");
            jnegative.setToolTipText("преобразовать картинку в негатив");
            jfsize.setToolTipText("задать размер шрифта");
            jtofile.setText("в файл");
            jtofile.setToolTipText("сохранить изменения в файл с расширением .b");
            jtorom.setText("в rom");
            jtorom.setToolTipText("сохранить изменения в rom");
            jcommands.setText("комманды");
            jcommands.setToolTipText("вызвать окно комманд");
            jrestore.setText("восстановить");
            jrestore.setToolTipText("восстановить файл с расширением .b из файла с расширением .a");
            fils.setToolTipText("части rom на диске");
            jef.setToolTipText("рисунки и другие ресурсы");
            jtablo.setToolTipText("инфо");
            jcd00.setToolTipText("прокрутка к месту по умолчанию");
            jcd1.setToolTipText("прокрутка");
            jtab.setToolTipText("вы можете изменить текст в колонке txt. Размер текста находится в колонке width");
        }
    }

    public void setadvance(boolean adv) {
        jpanadv.setVisible(adv);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtablo = new javax.swing.JTextArea();
        jcd00 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jef = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        fils = new javax.swing.JList();
        panp = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtab = new javax.swing.JTable();
        jcd1 = new javax.swing.JSpinner();
        jpanadv = new javax.swing.JPanel();
        jxm = new javax.swing.JSpinner();
        jb3 = new javax.swing.JSpinner();
        jddd = new javax.swing.JTextField();
        jd6 = new javax.swing.JSpinner();
        jddm = new javax.swing.JTextField();
        jcc = new javax.swing.JTextField();
        jcd = new javax.swing.JSpinner();
        jneg = new javax.swing.JCheckBox();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        intt = new javax.swing.JTextField();
        hexx = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jtextda = new javax.swing.JCheckBox();
        jcd2 = new javax.swing.JSpinner();
        jButton12 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jextract = new javax.swing.JButton();
        jfromf = new javax.swing.JButton();
        jnegative = new javax.swing.JButton();
        jfsize = new javax.swing.JSpinner();
        jcommands = new javax.swing.JButton();
        jtorom = new javax.swing.JButton();
        jtofile = new javax.swing.JButton();
        jprogress = new javax.swing.JProgressBar();
        jfromf1 = new javax.swing.JButton();
        jrestore = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jtablo.setEditable(false);
        jtablo.setColumns(20);
        jtablo.setLineWrap(true);
        jtablo.setRows(5);
        jScrollPane1.setViewportView(jtablo);

        jcd00.setText("=");
        jcd00.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcd00ActionPerformed(evt);
            }
        });

        jef.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jefMouseClicked(evt);
            }
        });
        jef.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jefKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jefKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jefKeyReleased(evt);
            }
        });
        jef.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jefValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jef);

        fils.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                filsValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(fils);

        jtab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "N", "place", "width", "txt"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtab.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jtabPropertyChange(evt);
            }
        });
        jScrollPane4.setViewportView(jtab);

        javax.swing.GroupLayout panpLayout = new javax.swing.GroupLayout(panp);
        panp.setLayout(panpLayout);
        panpLayout.setHorizontalGroup(
            panpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panpLayout.createSequentialGroup()
                .addComponent(jScrollPane4)
                .addGap(0, 0, 0))
        );
        panpLayout.setVerticalGroup(
            panpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
        );

        jcd1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jcd1StateChanged(evt);
            }
        });
        jcd1.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                jcd1VetoableChange(evt);
            }
        });

        jxm.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(12), null, null, Integer.valueOf(1)));
        jxm.setValue(12);
        jxm.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jxmStateChanged(evt);
            }
        });
        jxm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jxmMouseReleased(evt);
            }
        });
        jxm.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                jxmVetoableChange(evt);
            }
        });

        jb3.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(3), null, null, Integer.valueOf(1)));
        jb3.setValue(3);
        jb3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jb3StateChanged(evt);
            }
        });
        jb3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jb3MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jb3MouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jb3MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jb3MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jb3MouseEntered(evt);
            }
        });
        jb3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jb3PropertyChange(evt);
            }
        });
        jb3.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                jb3VetoableChange(evt);
            }
        });

        jddd.setText("0");
        jddd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdddActionPerformed(evt);
            }
        });

        jd6.setModel(new javax.swing.SpinnerNumberModel());
        jd6.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jd6StateChanged(evt);
            }
        });
        jd6.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                jd6VetoableChange(evt);
            }
        });

        jddm.setText("0");
        jddm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jddmActionPerformed(evt);
            }
        });

        jcc.setText("3");
        jcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jccActionPerformed(evt);
            }
        });

        jcd.setModel(new javax.swing.SpinnerNumberModel());
        jcd.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jcdStateChanged(evt);
            }
        });
        jcd.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                jcdVetoableChange(evt);
            }
        });

        jneg.setText("negative");
        jneg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jnegActionPerformed(evt);
            }
        });

        jButton5.setText("txth");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("txt");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton14.setText("txt");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton1.setText("ris1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("ris2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("ris3");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton7.setText("ris4");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("ris5");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("ris15");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton3.setText("<");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton10.setText(">");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        intt.setText("0");
        intt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inttActionPerformed(evt);
            }
        });

        hexx.setText("e -lc3 -d16 -fb32 -mfbt2 -i80");
        hexx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hexxActionPerformed(evt);
            }
        });

        jButton11.setText("<");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton19.setText(">");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setText("as txt");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jtextda.setText("textda");
        jtextda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtextdaActionPerformed(evt);
            }
        });

        jcd2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jcd2StateChanged(evt);
            }
        });
        jcd2.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                jcd2VetoableChange(evt);
            }
        });

        jButton12.setText("strings");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpanadvLayout = new javax.swing.GroupLayout(jpanadv);
        jpanadv.setLayout(jpanadvLayout);
        jpanadvLayout.setHorizontalGroup(
            jpanadvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanadvLayout.createSequentialGroup()
                .addGroup(jpanadvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanadvLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(jpanadvLayout.createSequentialGroup()
                        .addComponent(jxm, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jb3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpanadvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanadvLayout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jddd, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jd6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jddm, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpanadvLayout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpanadvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton20)
                    .addGroup(jpanadvLayout.createSequentialGroup()
                        .addComponent(jcc, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcd, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcd2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jpanadvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpanadvLayout.createSequentialGroup()
                        .addComponent(intt, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11))
                    .addComponent(jButton12))
                .addGroup(jpanadvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanadvLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hexx, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jneg))
                    .addGroup(jpanadvLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtextda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addGap(38, 38, 38)
                        .addComponent(jButton14)
                        .addGap(6, 6, 6))))
        );
        jpanadvLayout.setVerticalGroup(
            jpanadvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanadvLayout.createSequentialGroup()
                .addGroup(jpanadvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jddd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jddm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jneg)
                    .addComponent(jcd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jb3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jxm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jd6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jButton10)
                    .addComponent(intt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hexx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11)
                    .addComponent(jButton19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpanadvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton4)
                    .addComponent(jButton7)
                    .addComponent(jButton8)
                    .addComponent(jButton9)
                    .addComponent(jButton14)
                    .addComponent(jButton6)
                    .addComponent(jButton5)
                    .addComponent(jButton20)
                    .addComponent(jtextda)
                    .addComponent(jButton12)))
            .addComponent(jcd2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jextract.setText("extract");
        jextract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jextractActionPerformed(evt);
            }
        });

        jfromf.setText("import");
        jfromf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfromfActionPerformed(evt);
            }
        });

        jnegative.setText("to negative");
        jnegative.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jnegativeActionPerformed(evt);
            }
        });

        jfsize.setModel(new javax.swing.SpinnerNumberModel());
        jfsize.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jfsizeStateChanged(evt);
            }
        });
        jfsize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jfsizeMouseClicked(evt);
            }
        });
        jfsize.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                jfsizeVetoableChange(evt);
            }
        });

        jcommands.setText("comands");
        jcommands.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcommandsActionPerformed(evt);
            }
        });

        jtorom.setText("to rom");
        jtorom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtoromActionPerformed(evt);
            }
        });

        jtofile.setText("to file");
        jtofile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtofileActionPerformed(evt);
            }
        });

        jfromf1.setText("from file");
        jfromf1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfromf1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jextract)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jfromf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jfromf1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jnegative)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jfsize, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jprogress, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jtofile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtorom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcommands)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jextract)
                .addComponent(jfromf)
                .addComponent(jnegative)
                .addComponent(jfsize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jcommands)
                .addComponent(jtorom)
                .addComponent(jtofile)
                .addComponent(jfromf1))
            .addComponent(jprogress, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jrestore.setText("restore file");
        jrestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrestoreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpanadv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcd00, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jcd1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jrestore)))
                .addGap(6, 6, 6))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpanadv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jrestore))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jcd00)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jcd1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
NewJFrame fr;
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        //  String ss=fr.getpath()+"/_ROM.extracted/"+fils.getSelectedItem().toString();
        ris(pan.getWidth() / 2, pan.getWidth(), 11);

    }//GEN-LAST:event_jButton1ActionPerformed
    public void updatefills() {
        //   fils.removeAll();
        //fils.removeAllItems();
        File ff = new File(fr.getpath());// + "/_ROM.extracted");
        File[] f2 = ff.listFiles();
        Arrays.sort(f2);
        int n = 0;
        for (int i = 0; i < f2.length; i++) {
            if (((f2[i].getName().endsWith(".b") && f2[i].isFile())
                    || (f2[i].getName().endsWith(".aa") && f2[i].isFile())
                    || (f2[i].getName().endsWith(".bin") && f2[i].isFile())
                    || (!f2[i].getName().contains(".") && f2[i].isFile())) && (!f2[i].getName().toLowerCase().contains("boot"))) {
                n++;
            }
        }
        String[] s2 = new String[n];
        n = 0;
        for (int i = 0; i < f2.length; i++) {
            if (((f2[i].getName().endsWith(".b") && f2[i].isFile())
                    || (f2[i].getName().endsWith(".aa") && f2[i].isFile())
                    || (f2[i].getName().endsWith(".bin") && f2[i].isFile())
                    || (!f2[i].getName().contains(".") && f2[i].isFile())) && (!f2[i].getName().toLowerCase().contains("boot"))) {
                s2[n] = f2[i].getName();
                n++;
            }
        }
        fils.setListData(s2);

    }
boolean ris200=true;
boolean ris111=true;
boolean ris1=true;
boolean ris5=true;
boolean ris999=false;
boolean ris122=true;
boolean ris133=true;
boolean ris300=true;
boolean ris400=true;
boolean ris401=true;
boolean ris402=true;
boolean ris500=true;
boolean ris501=true;
boolean ris600=true;
boolean ris700=true;
boolean risFFFF=false;
boolean risFEFF=false;
void noris(){
 ris200=false;
 ris111=false;
 ris1=false;
 ris5=false;
 ris999=false;
 ris122=false;
 ris133=false;
 ris300=false;
 ris400=false;
 ris401=false;
 ris402=false;
 ris500=false;
 ris501=false;
 ris700=false;
 risFFFF=false;
 risFEFF=false;    
}

    public void updatejef() {
//noris();
//ris1=true;
        //   jef.removeAll();
        if (fils.getModel().getSize() == 0) {
            return;
        }
        /*       if (fils.getSelectedIndex()<0) {
         fils.setSelectedIndex(0);
         }
         */
        if (fils.getSelectedValue() == null) {
            fils.setSelectedIndex(0);
        }
        bb = null;
        functions.getmemoryinfo();
        String ff2 = fr.getpath() //+ "/_ROM.extracted/" 
                + fils.getSelectedValue().toString();
        bb = functions.file2byte(ff2);

        Vector s2 = new Vector();

        /*       for (int i = 0; i < bb.length; i++) {
         try {

         MagicMatch match = parser.getMagicMatch(bb,i);
         if (match!=null){
         myLog.Systemoutprintln(match.getMimeType()+match.getLength());
            
         myLog.Systemoutprintln(match.getType()+match.getOffset());
         myLog.Systemoutprintln(match.getDescription());
         s2.add(
         String.format("%02X 555 "+match.getExtension()+" "+match.getLength(), i)); 
         }
         } catch (Exception e) {
         //e.printStackTrace();
         }        
         }
         */
        int[] nt = new int[256];
        if (ff2.endsWith(".b")) {
            if (ris200 && bb.length > 200) {
                for (int i = 1; i < 200; i += 2) {
                    int k = bb[i] & 0xFF;
                    nt[k]++;
                }
                //for (int i = 0; i < 256; i++) myLog.Systemoutprintln(""+i+"======="+nt[i]);
                for (int i = 0; i < 256; i++) {
                    if (nt[i] > 70) {
                        s2.add("0 200");
                        jef.setListData(s2);
                        if (jef.getModel().getSize() > 0) {
                            jef.setSelectedIndex(0);
                        }
                        return;
                    }
                }
            }
        }
int k23=0;
        for (int i = 0; i < bb.length - 1; i++) {

            /*            if (i%10000==0){

             jprogress.setValue(i*100/bb.length);
                
             myLog.Systemoutprintln(""+i*100/bb.length);
             }*/
            if (i < bb.length - 20) {

                if (i % 10000 == 0) {

                    jprogress.setValue(i * 100 / bb.length);

                    myLog.Systemoutprintln("" + i * 100 / bb.length);
                }

                if (ris300 && bb[i] == 0x47 &&//47 49 46 38
                        bb[i + 1] == 0x49
                        && bb[i + 2] == 0x46
                        && bb[i + 3] == 0x38) {
                    s2.add(
                            String.format("%02X 300 gif", i));

                } else if (ris400 && (bb[i] & 0xFF) == 0xFF &&//FF D8 FF DB?E8
                        (bb[i + 1] & 0xFF) == 0xD8
                        && (bb[i + 2] & 0xFF) == 0xFF
                        && (bb[i + 3] & 0xFF) == 0xE8) {
                    s2.add(
                            String.format("%02X 400 jpg", i));

                } else if (ris401 && (bb[i] & 0xFF) == 0xFF &&//FF D8 FF E0 nn nn 4A 46 49 46 00 01
                        (bb[i + 1] & 0xFF) == 0xD8
                        && (bb[i + 2] & 0xFF) == 0xFF
                        && (bb[i + 3] & 0xFF) == 0xE0) {
                    s2.add(
                            String.format("%02X 401 jpg", i));

                } else if (ris402 && (bb[i] & 0xFF) == 0xFF &&//FF D8 FF E1 nn nn 45 7869 66 00 00
                        (bb[i + 1] & 0xFF) == 0xD8
                        && (bb[i + 2] & 0xFF) == 0xFF
                        && (bb[i + 3] & 0xFF) == 0xE1) {
                    s2.add(
                            String.format("%02X 402 jpg", i));
                } else if (ris500 && bb[i] == 0x52 &&//52 49 46 46 nn nn nn nn 57 41 56 45
                        bb[i + 1] == 0x49
                        && bb[i + 2] == 0x46
                        && bb[i + 3] == 0x46) {
                    s2.add(
                            String.format("%02X 500 wav", i));
                } else if (ris501 && (bb[i] & 0xFF) == 0xFF &&//ff f3 28 c4 00
                        (bb[i + 1] & 0xFF) == 0xF3
                        && ((bb[i + 2] & 0xFF) == 0x28 || (bb[i + 2] & 0xFF) == 0x38)
                        && (bb[i + 3] & 0xFF) == 0xC4
                        && bb[i + 4] == 0x00) {
                    s2.add(
                            String.format("%02X 501 mp3", i));
                } else if (ris600 && bb[i] == 0x42 &&//42 4D
                        bb[i + 1] == 0x4D) {

                    int bb2 = bb[i + 2] & 0xFF;
                    int bb3 = bb[i + 3] & 0xFF;
                    int bb4 = bb[i + 4] & 0xFF;
                    int bb5 = bb[i + 5] & 0xFF;
                    int ll = bb2 + 256 * bb3 + 256 * 256 * bb4 + 256 * 256 * 256 * bb5;
                    if (i + ll < bb.length && ll > 0) {
                        s2.add(
                                String.format("%02X 600 bmp", i));
                    }
                } else if (ris700 && (bb[i] & 0xFF) == 0x89 &&//89 50 4E 47 0D 0A 1A 0A
                        bb[i + 1] == 0x50
                        && bb[i + 2] == 0x4E
                        && bb[i + 3] == 0x47
                        && bb[i + 4] == 0x0D
                        && bb[i + 5] == 0x0A
                        && bb[i + 6] == 0x1A
                        && bb[i + 7] == 0x0A) {
                    s2.add(
                            String.format("%02X 700 png", i));
                } else if (bb[i] == 0x4D &&//4D 5A
                        bb[i + 1] == 0x5A
                        && jpanadv.isVisible()) {
                    //       s2.add(String.format("%02X 808 fon?", i)); 

                } else if (ris111 && //(bb[i]) == 0 &&
                        (bb[i + 1]) == 0
                        && (bb[i + 2]) == 0
                        && (bb[i + 3]) == 0
                        && (bb[i + 4]) == 0
                        && (bb[i + 5]) != 0
                        && (bb[i + 6]) == 0
                        && (bb[i + 7]) == 0
                        && (bb[i + 8]) == 0
                        && (bb[i + 9]) != 0 /* && (bb[i + 10] ) == 0   
                         && (bb[i + 11] ) == 0   
                         && (bb[i + 12] ) == 0   
                         && (bb[i + 13] ) != 0
                         && (bb[i + 14] ) == 0   
                         && (bb[i + 15] ) == 0   
                         && (bb[i + 16] ) == 0   
                         && (bb[i + 17] ) != 0
                         && (bb[i + 18] ) == 0   
                         && (bb[i + 19] ) == 0   
                         && (bb[i + 20] ) == 0*/) {
                    if ((bb[i + 17] & 0xFF) > (bb[i + 13] & 0xFF) && (bb[i + 13] & 0xFF) > (bb[i + 9] & 0xFF)
                            && (bb[i + 9] & 0xFF) > (bb[i + 5] & 0xFF)) {
                        s2.add(
                                String.format("%02X 111 font", i));
                    }
                } else if (ris122 && i < bb.length - 100
                        && (bb[i] & 0xFF) != 0xFF
                        && (bb[i + 15] & 0xFF) == 0xFF
                        && (bb[i + 30] & 0xFF) == 0xFF
                        && (bb[i + 45] & 0xFF) == 0xFF
                        && (bb[i + 60] & 0xFF) == 0xFF) {
                    int ii = 0;
                    for (int j = 0; j < 59; j++) {
                        if ((bb[i + j] & 0xFF) == 0xFF) {
                            ii++;
                        }
                    }
                    if (ii < 10) {
                        s2.add(
                                String.format("%02X 122 font2", i));
                    }
                } else if (ris133 && (jpanadv.isVisible())
                        && i < bb.length - 300
                        && bb[i] == 0
                        && bb[i + 1] == 0
                        && bb[i + 2] > 0 && bb[i + 2] < 32
                        && bb[i + 3] > 0 && bb[i + 3] < 32
                        && bb[i + 4] > 0 && bb[i + 4] < 32) {
                    boolean da = false;
                    int k = 0;
                    for (k = 4; k < 1000; k++) {
                        if (i + k >= bb.length) {
                            da = false;
                            break;
                        }
                        if ((bb[i + k] & 0xFF) >= 32) {
                            da = false;
                            break;
                        }
                        if (bb[i + k] == 0) {
                            da = true;
                            break;
                        }
                    }
                    if (da) {
                        for (int k2 = 1; k2 < 10; k2++) {
                            if (i + k + k2 >= bb.length) {
                                da = false;
                                break;
                            }
                            if ((bb[i + k + k2 - 1] & 0xFF) >= (bb[i + k + k2] & 0xFF)) {
                                da = false;
                                break;
                            }
                        }
                    }

                    if (da) {
                        s2.add(
                                String.format("%02X 133 font3=" + k, i));
                    }

                } else if (ris999 && jpanadv.isVisible()&&i < bb.length - 100&&i>=k23){
                    int k2=0;
                    if(bb[i]==bb[i + 2])
                    for (int k=0;k<30;k++){
                        if (bb[i + 2*k]==bb[i + 2*k+2]&&bb[i + 2*k]!=bb[i + 2*k+1]
                                &&bb[i + 2*k+3]!=bb[i + 2*k+1]) k2++;
                    }
                    if (k2>20) {s2.add( String.format("%02X 999 rep2 "+k2, i));k23=i+60;}
                    int k3=0;
                    if(bb[i]==bb[i + 3])
                    for (int k=0;k<30;k++){
                        if (bb[i + 3*k]==bb[i + 3*k+2]&&bb[i + 3*k]!=bb[i + 3*k+1]) k3++;
                    }
                    if (k3>20) {s2.add( String.format("%02X 999 rep3 "+k3, i));k23=i+90;}
                    
                }
            }

            if (i < 24) {
                continue;
            }

            if (ris5 && //bb.length < 700000 && 
                    ris(-1, i, 5)) {
                s2.add(
                        String.format("%02X 5", i));
            } /*else  if (ris(-1, pan.getWidth(), 1)){
             jef.addItem(
             String.format("%02X 1", i));   
             }*/ /*   if ((bb[i] & 0xFF) == 0xFE
             && (bb[i + 1] & 0xFF) == 0xFF) {
             jef.addItem(
             String.format("%02X ", i));
             }*/ else if (ris1 && //bb.length < 700000 && 
                    ris(-1, i, 1)) {//i20==3*16+4 && i19==1)|| i5==19
                s2.add(
                        String.format("%02X 1 " //+ functions.tohex(bb[i - 5] & 0xFF) + "=" + functions.tohex(bb[i - 19] & 0xFF) + "=" + functions.tohex(bb[i - 20] & 0xFF)
                                , i));
            } else if (risFFFF && (bb[i] & 0xFF) == 0xFF
                    && (bb[i + 1] & 0xFF) == 0xFF) {
                s2.add(String.format("%02X 1 ff", i));
            } else if (risFEFF && (bb[i] & 0xFF) == 0xFE
                    && (bb[i + 1] & 0xFF) == 0xFF) {
                s2.add(String.format("%02X 5 ff", i));
            }

        }
        jef.setListData(s2);
        if (jef.getModel().getSize() > 0) {
            jef.setSelectedIndex(0);
        }
    }

    int dd = 0;//0xb97;//27b

    //b52/b98/c7a
    byte bb[];

    int ym0 = 0;

    Vector m2;
    Vector m2a;
//    Vector m2s;

    public int getkd6(int d6) {
        int kd6 = 1;
        if (d6 == 2) {
            kd6 = 64;
        } else if (d6 == 3) {
            kd6 = 32;
        } else if (d6 == 4) {
            kd6 = 16;
        } else if (d6 == 5) {
            kd6 = 8;
        } else if (d6 == 6) {
            kd6 = 4;
        } else if (d6 == 7) {
            kd6 = 2;
        } else if (d6 == 8) {
            kd6 = 1;
        }
        return kd6;
    }

    public void colorneg(int dd, int ii, int d6, int i4, int i2) {
        myLog.Systemoutprintln("negggggg==d6=" + d6);
        if (d6 == 23) {
            for (int i = 0; i < i4 * 2; i += 2) {
                if (dd + i + 3 >= bb.length) {
                    break;
                }
                int b1 = bb[dd + i] & 0xFF;
                int b2 = bb[dd + i + 1] & 0xFF;
                int b12 = b1 + 256 * b2;
                int c1 = b12 % 32;
                b12 = b12 / 32;
                int c2 = b12 % 64;
                b12 = b12 / 64;
                int c3 = b12 % 32;

                int k1 = c1 * 256 / 32;
                int k2 = c2 * 256 / 64;
                int k3 = c3 * 256 / 32;

                k1 = 255 - k1;
                k2 = 255 - k2;
                k3 = 255 - k3;

                c1 = k1 * 32 / 256;
                c2 = k2 * 64 / 256;
                c3 = k3 * 32 / 256;
                b12 = c1 + 32 * (c2 + 64 * c3);
                b1 = b12 % 256;
                b2 = b12 / 256;
                bb[dd + i] = (byte) b1;
                bb[dd + i + 1] = (byte) b2;
//-------------------------

            }

            for (int i = i4 * 2 - 1; i + 3 < ii; i += 3) {
                if (dd + i + 3 >= bb.length) {
                    break;
                }
                int b1 = bb[dd + i + 1] & 0xFF;
                int b2 = bb[dd + i + 2] & 0xFF;
                int b3 = bb[dd + i + 3] & 0xFF;

                int b12 = b1 + 256 * b2;
                //myLog.Systemoutprintln(NewJFrame.tohex(b12)+"="+NewJFrame.tohex(b3));
                //String ss="";
                //for (int k=0;k<8;k++) ss+=getbit(bb[dd+i+1], k);
                //for (int k=0;k<8;k++) ss+=getbit(bb[dd+i+2], k);
                //    myLog.Systemoutprintln(ss);
                int c1 = b12 % 32;
                b12 = b12 / 32;
                int c2 = b12 % 64;
                b12 = b12 / 64;
                int c3 = b12 % 32;
                //myLog.Systemoutprintln(""+c1+"="+c2+"="+c3);

                int k1 = c1 * 256 / 32;
                int k2 = c2 * 256 / 64;
                int k3 = c3 * 256 / 32;
                int k4 = 255 - b3;

                k1 = 255 - k1;
                k2 = 255 - k2;
                k3 = 255 - k3;

                c1 = k1 * 32 / 256;
                c2 = k2 * 64 / 256;
                c3 = k3 * 32 / 256;
                b12 = c1 + 32 * (c2 + 64 * c3);
                b1 = b12 % 256;
                b2 = b12 / 256;
                bb[dd + i + 1] = (byte) b1;
                bb[dd + i + 2] = (byte) b2;
//-------------------------             

            }
            return;
        } else if (d6 == 0) {
            for (int i = 0; i < ii; i += 2) {
                int b1 = bb[dd + i] & 0xFF;
                int b2 = bb[dd + i + 1] & 0xFF;
                int b12 = b1 + 256 * b2;
                int c1 = b12 % 32;
                b12 = b12 / 32;
                int c2 = b12 % 64;
                b12 = b12 / 64;
                int c3 = b12 % 32;
                myLog.Systemoutprintln("" + c1 + "=" + c2 + "=" + c3);

                int k1 = c1 * 256 / 32;
                int k2 = c2 * 256 / 64;
                int k3 = c3 * 256 / 32;

                k1 = 255 - k1;
                k2 = 255 - k2;
                k3 = 255 - k3;

                c1 = k1 * 32 / 256;
                c2 = k2 * 64 / 256;
                c3 = k3 * 32 / 256;
                b12 = c1 + 32 * (c2 + 64 * c3);
                b1 = b12 % 256;
                b2 = b12 / 256;
                bb[dd + i] = (byte) b1;
                bb[dd + i + 1] = (byte) b2;

                //cc=new Color(k3,k2,k1);
                //m2.add(cc);
                //m2s.add(NewJFrame.tohex(b1)+NewJFrame.tohex(b2)+" "+this.tobit(bb[dd+i+1])+" "+this.tobit(bb[dd+i])
                //+" "+c3+" "+c2+" "+c1+" = "+k3+" "+k2+" "+k1);
                //            myLog.Systemoutprintln(String.format("%02X ", bb[dd+i])+String.format("%02X ", bb[dd+i+1]));
                //          myLog.Systemoutprintln(""+i+"="+NewJFrame.tohex(b1)+NewJFrame.tohex(b2)+"="+NewJFrame.tohex(b12));
                //        myLog.Systemoutprintln("============="+hex2str(k1)+" "+hex2str(k2)+" "+hex2str(k3));
                //p5++;
            }
            return;
        } else if (d6 == 1) {

        } else {

        }
    }

    public Vector getcolors(int dd, int ii, int i4, int i2, int type) {
        //if (ii>4096) return null;
        int d6 = functions.str2int(jcc.getText().trim());
        if (type0 / 10000 == 1) {
            this.colorneg(dd, ii, d6, i4, i2);
        }
        Vector m2 = new Vector();
        m2a = new Vector();
//        m2s = new Vector();
        Color cc = new Color(0, 0, 0, 0);
        m2.add(cc);
        Color cp=cc;
        m2a.add(1);
//        m2s.add("0");
        int c = 0;
        int pp = 0;
//        int p5 = 0;
        int kc1 = 0, kc2 = 0, kc3 = 0, kc13 = 0;
        if (d6 == 33) {
            for (int i = 0; i < ii * 2 / 3; i += 2) {
                if (dd + ii * 2 / 3 + i >= bb.length) {
                    break;
                }
                int b1 = bb[dd + i] & 0xFF;
                int b2 = bb[dd + i + 1] & 0xFF;
                int b3 = bb[dd + ii * 2 / 3 + i / 2] & 0xFF;

                int b12 = b1 + 256 * b2;
                //myLog.Systemoutprintln(NewJFrame.tohex(b12)+"="+NewJFrame.tohex(b3));
                //String ss="";
                //for (int k=0;k<8;k++) ss+=getbit(bb[dd+i+1], k);
                //for (int k=0;k<8;k++) ss+=getbit(bb[dd+i+2], k);
                //    myLog.Systemoutprintln(ss);
                int c1 = b12 % 32;
                b12 = b12 / 32;
                int c2 = b12 % 64;
                b12 = b12 / 64;
                int c3 = b12 % 32;
                myLog.Systemoutprintln("" + c1 + "=" + c2 + "=" + c3 + "=====" + b3);

                int k1 = c1 * 256 / 32;
                int k2 = c2 * 256 / 64;
                int k3 = c3 * 256 / 32;
                int k4 = 255 - b3;
                if (jneg.isSelected()) {
                    k1 = 255 - k1;
                    k2 = 255 - k2;
                    k3 = 255 - k3;
                }

//                 myLog.Systemoutprintln("============================"+NewJFrame.tohex(b1&0xFF)+"="+NewJFrame.tohex(b2&0xFF)+"="+NewJFrame.tohex(b3&0xFF)+"="+k3+"="+k2+"="+k1+"="+b3);
                cc = new Color(k3, k2, k1, k4);
                m2a.add(3);
                //cc=new Color(0,0,0,0);
//                if (cc.equals(cp)) return null;
                m2.add(cc);
                //            myLog.Systemoutprintln(String.format("%02X ", bb[dd+i])+String.format("%02X ", bb[dd+i+1]));
                //          myLog.Systemoutprintln(""+i+"="+NewJFrame.tohex(b1)+NewJFrame.tohex(b2)+"="+NewJFrame.tohex(b12));
                //        myLog.Systemoutprintln("============="+hex2str(k1)+" "+hex2str(k2)+" "+hex2str(k3));
//                p5++;

            }
            return m2;

        }
        if (d6 == 23) {
            //myLog.Systemoutprintln("--------------------------------------------------");
            for (int i = 0; i < i4 * 2; i += 2) {
                if (dd + i + 3 >= bb.length) {
                    break;
                }
                int b1 = bb[dd + i] & 0xFF;
                int b2 = bb[dd + i + 1] & 0xFF;
                int b12 = b1 + 256 * b2;
                int c1 = b12 % 32;
                b12 = b12 / 32;
                int c2 = b12 % 64;
                b12 = b12 / 64;
                int c3 = b12 % 32;

                int k1 = c1 * 256 / 32;
                int k2 = c2 * 256 / 64;
                int k3 = c3 * 256 / 32;
                if (jneg.isSelected()) {
                    k1 = 255 - k1;
                    k2 = 255 - k2;
                    k3 = 255 - k3;
                }
                cc = new Color(k3, k2, k1);
//if (cc.equals(cp)) return null;
                m2.add(cc);
                m2a.add(2);
                //            myLog.Systemoutprintln(String.format("%02X ", bb[dd+i])+String.format("%02X ", bb[dd+i+1]));
                //          myLog.Systemoutprintln(""+i+"="+NewJFrame.tohex(b1)+NewJFrame.tohex(b2)+"="+NewJFrame.tohex(b12));
                //        myLog.Systemoutprintln("============="+hex2str(k1)+" "+hex2str(k2)+" "+hex2str(k3));
//                p5++;

            }

            for (int i = i4 * 2 - 1; i + 3 < ii; i += 3) {
                if (dd + i + 3 >= bb.length) {
                    break;
                }
                int b1 = bb[dd + i + 1] & 0xFF;
                int b2 = bb[dd + i + 2] & 0xFF;
                int b3 = bb[dd + i + 3] & 0xFF;

                int b12 = b1 + 256 * b2;
                //myLog.Systemoutprintln(NewJFrame.tohex(b12)+"="+NewJFrame.tohex(b3));
                //String ss="";
                //for (int k=0;k<8;k++) ss+=getbit(bb[dd+i+1], k);
                //for (int k=0;k<8;k++) ss+=getbit(bb[dd+i+2], k);
                //    myLog.Systemoutprintln(ss);
                int c1 = b12 % 32;
                b12 = b12 / 32;
                int c2 = b12 % 64;
                b12 = b12 / 64;
                int c3 = b12 % 32;
                //myLog.Systemoutprintln(""+c1+"="+c2+"="+c3);

                int k1 = c1 * 256 / 32;
                int k2 = c2 * 256 / 64;
                int k3 = c3 * 256 / 32;
                int k4 = 255 - b3;
                if (jneg.isSelected()) {
                    k1 = 255 - k1;
                    k2 = 255 - k2;
                    k3 = 255 - k3;
                }

//                 myLog.Systemoutprintln("============================"+NewJFrame.tohex(b1&0xFF)+"="+NewJFrame.tohex(b2&0xFF)+"="+NewJFrame.tohex(b3&0xFF)+"="+k3+"="+k2+"="+k1+"="+b3);
                cc = new Color(k3, k2, k1, k4);
//                if (cc.equals(cp)) return null;
                m2a.add(3);
                //cc=new Color(0,0,0,0);
                m2.add(cc);
                //            myLog.Systemoutprintln(String.format("%02X ", bb[dd+i])+String.format("%02X ", bb[dd+i+1]));
                //          myLog.Systemoutprintln(""+i+"="+NewJFrame.tohex(b1)+NewJFrame.tohex(b2)+"="+NewJFrame.tohex(b12));
                //        myLog.Systemoutprintln("============="+hex2str(k1)+" "+hex2str(k2)+" "+hex2str(k3));
//                p5++;

            }
            return m2;

        }
        if (d6 == 0) {

            for (int i = 0; i < ii; i += 2) {
                if (dd + i + 1 >= bb.length) {
                    break;
                }
                int b1 = bb[dd + i] & 0xFF;
                int b2 = bb[dd + i + 1] & 0xFF;
                int b12 = b1 + 256 * b2;
                int c1 = b12 % 32;
                b12 = b12 / 32;
                int c2 = b12 % 64;
                b12 = b12 / 64;
                int c3 = b12 % 32;
                myLog.Systemoutprintln("" + c1 + "=" + c2 + "=" + c3);

                int k1 = c1 * 256 / 32;
                int k2 = c2 * 256 / 64;
                int k3 = c3 * 256 / 32;

                if (jneg.isSelected()) {
                    k1 = 255 - k1;
                    k2 = 255 - k2;
                    k3 = 255 - k3;
                }
                cc = new Color(k3, k2, k1);
//if (cc.equals(cp)) return null;
                m2.add(cc);
//                m2s.add(functions.tohex(b1) + functions.tohex(b2) + " " + functions.tobit(bb[dd + i + 1]) + " " + functions.tobit(bb[dd + i])
  //                      + " " + c3 + " " + c2 + " " + c1 + " = " + k3 + " " + k2 + " " + k1);
                //            myLog.Systemoutprintln(String.format("%02X ", bb[dd+i])+String.format("%02X ", bb[dd+i+1]));
                //          myLog.Systemoutprintln(""+i+"="+NewJFrame.tohex(b1)+NewJFrame.tohex(b2)+"="+NewJFrame.tohex(b12));
                //        myLog.Systemoutprintln("============="+hex2str(k1)+" "+hex2str(k2)+" "+hex2str(k3));
//                p5++;

            }
            return m2;
        }
        if (d6 == 1) {

            for (int i = 0; i < ii; i += 3) {
                int k1 = bb[dd + i + 1] & 0xFF;
                int k2 = bb[dd + i + 2] & 0xFF;
                int k3 = bb[dd + i + 3] & 0xFF;
                if (jneg.isSelected()) {
                    k1 = 255 - k1;
                    k2 = 255 - k2;
                    k3 = 255 - k3;
                }

                cc = new Color(k1, k2, k3);
//if (cc.equals(cp)) return null;
                m2.add(cc);
                //            myLog.Systemoutprintln(String.format("%02X ", bb[dd+i])+String.format("%02X ", bb[dd+i+1]));
                //          myLog.Systemoutprintln(""+i+"="+NewJFrame.tohex(b1)+NewJFrame.tohex(b2)+"="+NewJFrame.tohex(b12));
                //        myLog.Systemoutprintln("============="+hex2str(k1)+" "+hex2str(k2)+" "+hex2str(k3));
//                p5++;

            }
            return m2;
        }

        int kd6 = getkd6(d6);

        for (int i = dd; i < dd + ii; i++) {//0xc7a
            if (i >= bb.length) {
                break;
            }
            for (byte k = 0; k < 8; k++) {

                c = 2 * c + functions.getbit(bb[i], k);
                //myLog.Systemoutprintln(""+pp+"="+c);
                if (pp % d6 == d6 - 1) {
                    if (kc13 == 0) {
                        kc1 = c;
                    } else if (kc13 == 1) {
                        kc2 = c;
                    } else if (kc13 == 2) {
                        kc3 = c;
                        int k1 = kc1 * kd6;
                        int k2 = kc2 * kd6;
                        int k3 = kc3 * kd6;
                        if (jneg.isSelected()) {
                            k1 = 255 - k1;
                            k2 = 255 - k2;
                            k3 = 255 - k3;
                        }

                        cc = new Color(k3, k2, k1);
                        //gr.setColor(cc);
                        //grfillRect(300, p5*6, 44, 6);
//                        if (cc.equals(cp)) return null;
                        m2.add(cc);
//                        p5++;
                    }

                    kc13 = (kc13 + 1) % 3;
                    c = 0;
                }
                pp++;
            }
        }
        return m2;
    }

    
    public int[] getm2d(int b3,int ddm,int d6,int xm,int ym,int m2){

        int c = 0;
        int pp = 0;

  

        int x = 0;
        int y = 0;

     
//------------------------------------------------
        int[] m2b = new int[1024];
        //int im2b=0;
        int c2 = 1;
        for (int i = dd + b3; i < bb.length; i++) {//0xc7a
            if (i > ddm && ddm > 0) {
                break;
            }
            boolean breakda = false;
            for (byte k = 0; k < 8; k++) {
                //ss+=""+getbit(bb[i],k);
                //c = 2 * c + getbit(bb[i], k);
                c = c + functions.getbit(bb[i], k) * c2;
                c2 *= 2;
                if (pp % d6 == d6 - 1) {

                    m2b[c] = 1;

                    //im2b++;
                    x++;
                    c = 0;
                    c2 = 1;

                }
                pp++;

                if (x >= xm) {
                    x = 0;
                    y++;
                    if (y > ym - 1) {
                        breakda = true;
                        break;
                    }
                    pp = 0;
                    c = 0;
                    c2 = 1;
                }
            }
            if (breakda) {
                break;
            }

        }
        int[] m2c = new int[1025];
        int im2c = 0;
        for (int i = 0; i < 1024; i++) {
            m2c[i] = im2c;
            if (m2b[i] == 1) {
                im2c++;
            }
        }
        m2c[1024]=im2c;
        String ss = "";
        for (int i = 0; i < 256; i++) {
            ss += m2b[i] + " ";
        }
        myLog.Systemoutprintln("m2b=" + ss);
        ss = "";
        for (int i = 0; i < 256; i++) {
            ss += m2c[i] + " ";
        }
        myLog.Systemoutprintln("m2c=" + ss);
        
        System.out.println("----------------==="+im2c+"<="+m2+
                "====="+functions.log2i(im2c)+"<="+functions.log2i(m2));
 //       if (functions.log2i(im2c)>functions.log2i(m2)) return null;
      //  if (im2c>m2) return null;
        
        // for (int i=1;i<256;i++)m2c[i]=im2c-m2c[i];
        // ss="";for (int i=0;i<256;i++) ss+=m2c[i]+" ";myLog.Systemoutprintln("m2c="+ss);
//---------------------------------------------------------------        
        return m2c;
    }
    
    
    public int ris55(Graphics gr, int x1, int x2, int d6, int b3, int ddm, int xm, int type0, int cd) {
        int type = type0 % 10000;
        if (dd < 24) {
            return 1;
        }
        int ym;
        int cc1 = 255;
        int cc2 = 0;
        /*
         (bb[i-5] & 0xFF) == 0x00 &&
         (bb[i-6] & 0xFF) == 0x00 &&
         (bb[i-7] & 0xFF) == 0x00 &&
         (bb[i-8] & 0xFF) >0 &&
         (bb[i-9] & 0xFF) == 0x00*/

        int dd0 = dd;
        //xm=bb[dd]& 0xFF;
        //int ym=bb[dd+2]& 0xFF;
        int ii = bb[dd - 8] & 0xFF;
        int i4 = bb[dd - 4] & 0xFF;
        int i3 = bb[dd - 3] & 0xFF;
        int i2 = bb[dd - 2] & 0xFF;
        int i1 = bb[dd - 1] & 0xFF;

        int i5 = bb[dd - 5] & 0xFF;
        int i6 = bb[dd - 6] & 0xFF;
        int i7 = bb[dd - 7] & 0xFF;
        int i8 = bb[dd - 8] & 0xFF;
        int i9 = bb[dd - 9] & 0xFF;
        int i11 = bb[dd - 11] & 0xFF;
        int i20 = bb[dd - 20] & 0xFF;

        int i0 = bb[dd - 0] & 0xFF;
        if (type == 5) {
            if (i5 > 0 && x1 < 0) {
                return 2;
            }
            //if (i6>0 && x1<0) return false;
            if (i7 > 0 && x1 < 0) {
                return 3;
            }
            if (i8 == 0 && x1 < 0) {
                return 4;
            }
            if (i9 > 0 && x1 < 0) {
                return 5;
            }
            if (i11 > 0 && i11!= 128 && x1 < 0) {
                return 6;
            }
        }
//            myLog.Systemoutprintln("dd========" + dd + "==" + bb.length);
//                        myLog.Systemoutprintln("i5-9-"+i5+i6+i7+i8+i9+"-"+i0);
        //if (i0!=0xFE && x1<0) return false;

        int i12 = bb[dd - 12] & 0xFF;
        int i10 = bb[dd - 10] & 0xFF;
        int dl = 0;
        /*            if (ii==3)       {d6=2;dl=4;}
         else if (ii==4)  {d6=2;dl=6;}
            
         else if (ii==5)  {d6=3;dl=8;}
         else if (ii==6)  {d6=3;dl=10;}
            
         else if (ii==10) {d6=4;dl=18;}        
         else if (ii==13) {d6=4;dl=24;}
         else if (ii==16) {d6=4;dl=30;}
            
         else if (ii==19) {d6=5;dl=36;}
         else if (ii==32) {d6=5;dl=62;}
            
         else if (ii==36) {d6=6;dl=70;}
         else if (ii==44) {d6=6;dl=86;}
         else if (ii==45) {d6=6;dl=88;} 
         else if (ii==53) {d6=6;dl=104;}
            
         else if (ii==86) {d6=7;dl=0;}
         //else if (ii==32) {d6=5;dl=20000000;}
         //else if (ii==35) {d6=6;dl=104;}
         else myLog.Systemoutprintln("?????????????iiiii??????????????????????????");
         */

        if (type == 5) {
            dl = ii * 2 - 2;
            if (i6 > 0) {
                dl = i6 * 3 + 2;

                if (dl % 2 == 1) {
                    dl++;
                }
                if (i8 == 1) {
                    dl -= 2;

                }
                if (i8 == 3) {
                    dl += 2;
                }
                if (i8 >10) {
                    //28=40 76
                    //2a=42 80
                    //37=55 106
                    //2e=46 92
                    dl += 2+(i8-3)*2;//78;
                }                
                
                ii = i6;

                /*    int d6d4 = 0, d6d5 = 0;
                 if (dl % 2 == 1) {
                 dl++;
                 }
                 if (i8 == 1) {
                 dl -= 2;
                 d6d4 = 1;
                 }
                 if (i8 == 2) {
                 d6d5 = 1;
                 }
                 if (i8 == 3) {
                 dl += 2;
                 }
                 ii = i6;

                 if (ii <= 4) {
                 d6 = 2;
                 } else if (ii <= 6) {//6
                 d6 = 3;
                 } else if (ii <= 14 + d6d4) {
                 d6 = 4;
                 } else if (ii <= 29 + d6d5) {
                 d6 = 5;
                 } else if (ii <= 62) {//60
                 d6 = 6;
                 } else if (ii <= 126) {//124
                 d6 = 7;
                 } else {
                 d6 = 8;
                 }*/
            } else {
                /*   if (ii <= 4) {
                 d6 = 2;
                 } else if (ii <= 8) {
                 d6 = 3;
                 } else if (ii <= 16) {
                 d6 = 4;
                 } else if (ii <= 30) {//32
                 d6 = 5;
                 } else if (ii <= 64) {
                 d6 = 6;
                 } else if (ii <= 128) {
                 d6 = 7;
                 } else if (ii <= 256) {
                 d6 = 8;
                 } else if (ii <= 512) {
                 d6 = 9;
                 } else {
                 d6 = 8;
                 }*/
            }

        } else {
            ii = 0;
        }
        if (type == 5) {
            b3 = dl;
        }
        jb3.setValue(b3);
        m2 = getcolors(dd, b3, 0, 0, type0);
        if (m2==null) return 99;
        if (type == 5) {
            int iim2 = m2.size();
            System.out.println("============================================"+iim2+"=="+(i6+i8-1));            
            if (i6 > 0) {
                if (i6+i8<m2.size())  iim2=i6+i8-1;
                if (iim2 == 32 && i8 == 3) {
                    d6 = 6;
                } else if (iim2 <= 2) {
                    d6 = 1;
                } else if (iim2 <= 4) {
                    d6 = 2;
                } else if (iim2 <= 8) {//6
                    d6 = 3;
                } else if (iim2 <= 16) {
                    d6 = 4;
                } else if (iim2 <= 32) {
                    d6 = 5;
                } else if (iim2 <= 64) {//60
                    d6 = 6;
                } else if (iim2 <= 128) {//124
                    d6 = 7;
                } else {
                    d6 = 8;
                }
            } else {
                if (iim2 <= 2) {
                    d6 = 1;
                } else if (iim2 <= 4) {
                    d6 = 2;
                } else if (iim2 <= 8) {
                    d6 = 3;
                } else if (iim2 <= 16) {
                    d6 = 4;
                } else if (iim2 <= 32) {//32
                    d6 = 5;
                } else if (iim2 <= 64) {
                    d6 = 6;
                } else if (iim2 <= 128) {
                    d6 = 7;
                } else if (iim2 <= 256) {
                    d6 = 8;
                } else if (iim2 <= 512) {
                    d6 = 9;
                } else {
                    d6 = 8;
                }
            }

        }

        /*
         if (i4 == 2) {
         if (i3 == 0) {
         xm = 18;
         } else if (i3 == 1 && i1 == 2) {
         xm = 15;
         } else if (i3 == 1 && i1 == 1) {
         xm = 16;//i4==2
         } else if (i3 == 1) {
         xm = 17;
         } else if (i3 == 2) {
         xm = 14;
         } else if (i3 == 3 && i1 == 3) {
         xm = 12;
         } else if (i3 == 3) {
         xm = 13;
         } else if (i3 == 4) {
         xm = 11;
         } else if (i3 == 5) {
         xm = 9;
         } else {
         myLog.Systemoutprintln("????????????xxxmmm???????????????????????????");
         }
         } else if (i4==0){
         if (i3 == 12) {
         xm = 14;    //0=1=12=0==14
         } else if (i3 == 9) {
         xm = 10;    //0=1=9=0==10
         } else if (i3 == 6) {
         xm = 13;    //0=1=6=0==13
         } else if (i3 == 3) {
         xm = 16;    //0=1=3=0==16
         } else {
         //1=2=1=0==16
         //0=1=0=2==18?--19
         myLog.Systemoutprintln("????????????xxxmmm???????????????????????????");
         }
         }else {
         myLog.Systemoutprintln("????????????xxxmmm???????????????????????????");
         }
         */
        ym = 100000000;
        if (type == 5) {
            xm = i12 - i4 - i2;
//int ym=i12-(i12 % 2)-i2-i4;
            ym = i10 - i3 - i1;//+1
            if (xm <= 1 && x1 < 0) {
                return 7;
            }
            if (ym <= 1 && x1 < 0) {
                return 8;
            }
            if (x1 < 0 && i6 > 0 && i8 > 10) {
                //return 9;
            }
            if (x1 < 0 && i0 == 0 && i20 != 0x28) {
                return 1;
            }
        }
        if (x1 >= 0) {
            myLog.Systemoutprintln("===========================================" + i0 + "================" + i20 + "=" + 0x28);
            //if (i8>5) {h2txt(false);}
//B6E80.b 87C 	04  0A  1A  08  1B  00  1E  00  03  00  20  00  3C  03  C0  20  00  00  B4  01  28  BC  00  00   
//BB154.b 294 	A2  0A  19  08  1A  00  1E  00  02  00  1F  00  3C  03  C0  1F  00  00  B2  01  28  BA  00  00                 
        } else {
            h2txt("5", false);
        }
        if (x1 < 0) {
            return 0;
        }
        myLog.Systemoutprintln("dd========" + dd + "==" + bb.length);
        myLog.Systemoutprintln("i5-9-" + i5 + i6 + i7 + i8 + i9 + "-" + i0);

        myLog.Systemoutprintln("ii========" + ii + "==i12=" + i12 + "====i1234==" + i1 + "=" + i2 + "=" + i3 + "=" + i4
                + "==" + (i12 - i4 - i2) + "=" + (i10 - i1 - i3 + 1) + "=" + (i12 - (i12 % 2) - i2 - i4));

        /*       if (frtxt1 == null) {
         frtxt1 = new txt1();
         }frtxt1.addText("i12="+i12+"====i1234==" + i1 +"="+ i2 +"="+ i3 +"="+ i4+"=="+xm+"=="+ym);
         */
        jxm.setValue(xm);
        myLog.Systemoutprintln("-----------------d6---" + d6);
        jd6.setValue(d6);

        int p5 = 0;
        //ii = 104;
        int c = 0;
        int pp = 0;
        //int kc1 = 0, kc2 = 0, kc3 = 0, kc13 = 0;
            /*
         for (int i=dd;i<dd+ii;i++){//0xc7a

         for (byte k=0;k<8;k++){
                
         c=2*c+getbit(bb[i],k);
         //myLog.Systemoutprintln(""+pp+"="+c);
         if (pp%d6==d6-1) {
         if (kc13==0) kc1=c;
         else if (kc13==1) kc2=c;
         else if (kc13==2) {
         kc3=c;
         int k1=kc1*256/32;
         int k2=kc2*256/32;
         int k3=kc3*256/32;
         Color cc=new Color(k1,k2,k3);
         gr.setColor(cc);
         grfillRect(300, p5*6, 44, 6);
         m2.add(cc);
         p5++;
         }
                
         kc13=(kc13+1)%3;
         c=0;
         }
         pp++;
         }
         }
         
         */

//            myLog.Systemoutprintln("colors=" + p5 + "=" + m2.size());
        p5 = 0;      /*
         for (int i=0;i<ii;i+=2){
         int b1=bb[dd+i]&0xFF;
         int b2=bb[dd+i+1]&0xFF;
         int b12=b1+256*b2;
         int c1=b12 % 32;
         b12=b12/32;
         int c2=b12 % 32;
         b12=b12/32;
         int c3=b12 % 32;


         int k1=c1*256/32;
         int k2=c2*256/32;
         int k3=c3*256/32;
         Color cc=new Color(k1,k2,k3);
         gr.setColor(cc);
         grfillRect(200, p5*6, 44, 6);
         m2.add(cc);
         myLog.Systemoutprintln(String.format("%02X ", bb[dd+i])+String.format("%02X ", bb[dd+i+1]));
         myLog.Systemoutprintln(""+i+"="+NewJFrame.tohex(b1)+NewJFrame.tohex(b2)+"="+NewJFrame.tohex(b12));
         myLog.Systemoutprintln("============="+hex2str(k1)+" "+hex2str(k2)+" "+hex2str(k3));
         p5++;
          
         }*/

//            myLog.Systemoutprintln("colors=" + p5 + "=" + m2.size());

        int x = 0;
        int y = 0;

        pp = 0;
        c = 0;
 /*       int ck = 1;
        if (d6 == 7) {
            ck = 2;
        } else if (d6 == 6) {
            ck = 4;
        } else if (d6 == 5) {
            ck = 8;
        } else if (d6 == 4) {
            ck = 16;
        } else if (d6 == 3) {
            ck = 32;
        } else if (d6 == 2) {
            ck = 64;
        } else if (d6 == 1) {
            ck = 128;
        }*/

        //dd += dl;
//------------------------------------------------
        int[] m2c =getm2d( b3, ddm, d6, xm, ym,m2.size());
        if (m2c==null) {System.out.println("paniccccccccccccccccccc");
           // d6--; jd6.setValue(d6);
           // m2c =getm2d( b3, ddm, d6, xm, ym,10000000);
        }
        int im2c=m2c[1024];
        System.out.println("******************"+i6+"+"+i8+"="+(i6+i8)+"="+im2c);

//---------------------------------------------------------------
        x = 0;
        y = 0;
        pp = 0;
        c = 0;

//        m2 = getcolors(dd, b3,  0, 0, type0);
        imgx = xm;
        imgy = ym;
        /* if (txtda) 
         for (int i=0;i<m2s.size();i++){
         frtxt1.addText2(""+i+"="+(String)m2s.get(i)+"\n");
         myLog.Systemoutprintln("-=-=-=-=-=-="+i+"="+(String)m2s.get(i));
         }*/
        int c2 = 1;
        int im = 0;
        for (int i = dd + b3; i < bb.length; i++) {//0xc7a
            if (i > ddm && ddm > 0) {
                break;
            }
            boolean breakda = false;
            for (byte k = 0; k < 8; k++) {
                //ss+=""+getbit(bb[i],k);
//                    c = 2 * c + getbit(bb[i], k);
                c = c + functions.getbit(bb[i], k) * c2;
                im = i;
                c2 *= 2;
                //myLog.Systemoutprintln(""+pp+"="+c);
                if (pp % d6 == d6 - 1) {
                    //ss+=" ";
                    //myLog.Systemoutprintln("c=="+c+"=="+ck);
                    //myLog.Systemoutprintln("col===="+c);
                    c = m2c[c];
                    if (cc1 > c && c > 0) {
                        cc1 = c;
                    }
                    if (cc2 < c && c > 0) {
                        cc2 = c;
                    }

                    //c=255-c*ck;
                    Color cc;
                    if (c > 0) {
                        c = (c + cd - 1) % (im2c - 1) + 1;
                    }
                    if (c >= m2.size()) {
                        /*c = 255 - c * ck;
                         if (c < 0 || c > 255) {
                         cc = new Color(0, 0, 0);
                         } else {
                         cc = new Color(c, c, c);
                         }*/
                        //cc=Color.red;

                        c = m2.size() - 1;
                        cc = (Color) m2.get(c);
                    } else {
                        //    if (c>0){
                        //      c=(c+cd-1)%(im2c-1)+1;
                        //  }
                        cc = (Color) m2.get(c);//new Color(c,c,c);
                    }
                    //c = 255 - c * ck;
                    //Color cc = new Color(c, c, c);

//if (txtda) frtxt1.addText2(" "+functions.tohex(c));
                    grfillRect(cc, gr, x1 + x * p4, y * p4, p4, p4);
                    x++;
                    c = 0;
                    c2 = 1;

                }
                pp++;

                if (x >= xm) {
                    x = 0;
                    y++;//if (txtda) frtxt1.addText2("\n");
                    if (y > ym - 1) {
                        breakda = true;
                        break;
                    }
                    pp = 0;
                    c = 0;
                    c2 = 1;
                }
            }
            if (breakda) {
                break;
            }
            //ss+=" ";
            //if ((i-dd-b3) % xm ==(xm-1)) {x=0;y++;pp=0;c=0;}
        }
        myLog.Systemoutprintln("col====" + cc1 + "=" + cc2 + "=" + p5 + "=" + ii);

        piclength = im - dd;
        jtablo.setText("begin=" + jddd.getText().trim().split(" ")[0] + "\nxm=" + xm + "\nym=" + ym
                + "\ncolortable=" + functions.tohex(b3)
                + "\ncolorh=" + jd6.getValue()
                + "\ncolormin=" + cc1
                + "\ncolormax=" + cc2
                + "\ncolorm2=" + m2.size()
                + "\npiclength=" + piclength + tabdop(piclength)
        );

        return 0;
    }

    public String tabdop(int len) {
        String ss = "";
        int i = jef.getSelectedIndex();
        if (i < 0) {
            return "";
        }
        String s1 = jef.getSelectedValue().toString().split(" ")[0];
        int d1 = functions.str2int("#" + s1);
        String s11 = fils.getSelectedValue().toString().replace("a.b", "").replace(".b", "");
        int d11 = functions.str2int("#" + s11);
        boolean da = true;
        for (int j = 1; i + j < jef.getModel().getSize(); j++) {
            if (jef.getModel().getElementAt(i + j).toString().endsWith("ff")) {
                continue;
            }
            String s2 = jef.getModel().getElementAt(i + j).toString().split(" ")[0];
            int d2 = functions.str2int("#" + s2);
            ss = "\nplace=" + (d2 - d1 - len) + "=" + d1 + "=" + d2 + "/" + functions.tohex(d1 + len)
                    + "\n" + d11;
            if (d2 - d1 - len < 0 || d2 - d1 - len > 100) {

                jtablo.setBackground(Color.yellow);
            } else {

                jtablo.setBackground(Color.white);
            }
            da = false;
            break;
        }

        if (da) {
            File f = new File(fr.getpath() + fils.getSelectedValue().toString());
            int d2 = (int) f.length();
            ss = "\nplace=" + (d2 - d1 - len) + "=" + d1 + "=" + d2 + "/" + functions.tohex(d1 + len);
            if (d2 - d1 - len < 0 || d2 - d1 - len > 30) {

                jtablo.setBackground(Color.yellow);
            } else {

                jtablo.setBackground(Color.white);
            }
        }
        return ss;
    }
    int p4 = 4;
    int im = 0;

    int getnextbyte(int i, int d6) {
        int c = 0;
        if (d6 == 8) {
            for (byte k = 0; k < d6; k++) {
                c = 2 * c + getnextbit(i + d6 - k - 1);
            }
        } else {
            for (byte k = 0; k < d6; k++) {
                c = 2 * c + getnextbit(i + d6 - k - 1);
            }
        }
        return c;
    }

    public int ris11(Graphics gr, int x1, int x2, int d6, int b3, int ddm, int xm, int type0, int cd) {
        int type = type0 % 10000;
        //--------------------------------------------------------------------------------------
        if (dd < 24) {
            return 10;
        }
        if (dd >= bb.length) {
            return 1;
        }
        int d8 = 8;
        if (cd > 0) {
            d8 = cd;
        }
        int ym = 0;
        int xx1, yy1;
        int i9 = bb[dd - 9] & 0xFF;
        int i8 = bb[dd - 8] & 0xFF;
        int i7 = bb[dd - 7] & 0xFF;
        int i6 = bb[dd - 6] & 0xFF;

        int i2 = bb[dd - 2] & 0xFF;
        int i4 = bb[dd - 4] & 0xFF;
        int i12 = bb[dd - 12] & 0xFF;
        int i15 = bb[dd - 15] & 0xFF;
        int i24 = bb[dd - 24] & 0xFF;
        int i23 = bb[dd - 23] & 0xFF;
        int i20 = bb[dd - 20] & 0xFF;
        int i19 = bb[dd - 19] & 0xFF;
        int i1 = bb[dd - 1] & 0xFF;
        int i3 = bb[dd - 3] & 0xFF;
        int i5 = bb[dd - 5] & 0xFF;
        int i10 = bb[dd - 10] & 0xFF;
        int i11 = bb[dd - 11] & 0xFF;
        if (type == 1) {
            b3 = 2 * (i4 + i3 * 256) + 3 * (i2 + i1 * 256);
            if (b3 % 2 == 1) {
                b3++;
            }
            if (b3==0) return 12;
            if ((i4 + i3 * 256) + (i2 + i1 * 256) > 102400 && x1 < 0) {//10240
                return 2;
            }
            xm = i12 - i7 - i9;

            if (xm <= 1 && x1 < 0) {
                return 3;
            }
            if (i15 > 0) {
                ym = i15 - i6 - i8;
            } else {
                ym = i10 * 16 + i11 / 16 - i6 - i8;
            }
//if (ym<=1 && x1<0) return false;

            /*            
             if (jddd.getText().trim().split(" ")[0].equals("FBE")){
             myLog.Systemoutprintln("====555555==================="+i5);
             }*/
//if (i24>0 && x1<0) return false;
  /*          if (i23 > 0 && x1 < 0) {
             return false;
             }
             */
            if (x1 < 0) {
//if (i20!=3*16+4) return false;
//if (i19!=1) return false;
//if (i5!=19) return false;
                //i20=34 && i19=1
                //i5=13 && i19=0
                //i5=11 && i19=0
                //i5=15 && i19=0
                if (!((i20 == 3 * 16 + 4 && i19 == 1) || (i5 == 19 && i19 == 0) || (i5 == 17 && i19 == 0) || (i5 == 21 && i19 == 0))) {
//                if (!( (i5%2==1 && i19 == 0))) {//i5 %2==1 
                    return 4;
                }

//D6874.b 51A 	FF  00  00  00  7D  15  00  00  00  00  02  E0  11  00  00  00  10  00  00  00  09  00  00  00  
            }

//if (i1>0 && x1<0) return false;
//if (i3>0 && x1<0) return false;
            if (x1 >= 0) {
                //myLog.Systemoutprintln("==========================================="+i6+"================"+dl);
                //if (i8>5) {h2txt(false);}
//B6E80.b 87C 	04  0A  1A  08  1B  00  1E  00  03  00  20  00  3C  03  C0  20  00  00  B4  01  28  BC  00  00   
//BB154.b 294 	A2  0A  19  08  1A  00  1E  00  02  00  1F  00  3C  03  C0  1F  00  00  B2  01  28  BA  00  00                 
            } else {
                h2txt("1", false);
            }

         m2 = getcolors(dd, b3, i4 + 256 * i3, i2 + 256 * i1, type0);

            jxm.setValue(xm);
            jb3.setValue(b3);
if (m2==null) return 99;           
            
            if (x1 < 0) {
                return 0;
            }
//if (ym<=1 && i20!=3*16+4 && x1<0) return false; //?????????????       
            //jxm.setValue(xm);
            //jb3.setValue(b3);

        }else{
            

        m2 = getcolors(dd, b3, i4 + 256 * i3, i2 + 256 * i1, type0);
  
if (m2==null) return 99;
        }
        imgx = xm;
        imgy = ym;
        xx1 = yy1 = 0;
        int cc1 = 255;
        int cc2 = 0;
        int i = 0;
        String ss = "";
        //myLog.Systemoutprintln("dd=b3="+dd+"="+b3);
        if (type == 1 && x1 >= 0) {
            d6 = functions.log2(i4 + 256 * i3 + i2 + 256 * i1 + 1);
            jd6.setValue(d6);
        }
        //myLog.Systemoutprintln("d666666666666666=========="+d6+"="+m2.size());
        im = 0;

        int d128 = 128;
        if (d8 == 7) {
            d128 = 64;
        } else if (d8 == 6) {
            d128 = 32;
        } else if (d8 == 5) {
            d128 = 16;
        } else if (d8 == 4) {
            d128 = 8;
        } else if (d8 == 3) {
            d128 = 4;
        }
        if (d6 > 0) {//----------------------------<<<<<<<<<<<<<<<<<<<<<<<<8------------------
            while (i / 8 < (bb.length - dd - b3)) {
                if (dd + b3 + i / 8 > ddm && ddm > 0) {
                    break;
                }
                if (yy1 > ym - 1 && type == 1) {
                    break;
                }
                if ((yy1 * p4 + pany > pan.getHeight() && type != 1) && !risall) {

                    break;
                }
                int b = getnextbyte(i + (dd + b3) * 8, d8);//bb[dd + b3 + i] & 0xFF;//???????????????????????????????????????
                //myLog.Systemoutprintln("000000000000000000====="+NewJFrame.tohex(b));
                i += d8;
                if (b >= d128) {
                    //myLog.Systemoutprintln(""+(i/8)+"="+ss);
                    ss += "#" + functions.tohex(b);
                    boolean breakda = false;
                    for (int i22 = 0; i22 < b - d128 + 1; i22++) {
//                    if (dd + b3 + (i+i22*d6)/8+1>=bb.length) {breakda=true;myLog.Systemoutprintln("2222");break;}
                        if (dd + b3 + (i + d6 - 1) / 8 >= bb.length) {
                            breakda = true;
                            break;
                        }
                        int b2 = getnextbyte(i + (dd + b3) * 8, d6);//bb[dd + b3 + i+i22+1] & 0xFF;//???????????????????????????????????????
                        i += d6;
                        if (cc1 > b2 && b2 > 0) {
                            cc1 = b2;
                        }
                        if (cc2 < b2 && b2 > 0) {
                            cc2 = b2;
                        }
                        Color cc;
                        if (b2 >= m2.size()) {
                            if (b2 > 255) {
                                cc = new Color(0, 0, 0);
                            } else {
                                cc = new Color(b2, b2, b2);
                            }
                        } else {
                            cc = (Color) m2.get(b2);
                        }

                        //if (b2==0) b2=255;
                        //Color c = new Color(b2,b2,b2);
                        if (xx1 >= xm - 1) {
                            grfillRect(cc, gr, x1 + xx1 * p4, yy1 * p4, p4, p4);

                            myLog.Systemoutprintln("." + yy1 + "=" + (i / 8) + "=" + ss);
                            ss = "";
                            //if (yy1==9){
                            //   grfillRect(Color.red,gr,x1 +xx1*p4+p4, yy1*p4, p4, p4);
                            //}

                            xx1 = 0;
                            ss += functions.tohex(b2);
                            yy1++;

                            //grfillRect(cc,gr,x1 +xx1*p4, yy1*p4, p4, p4);
                        } else {
                            ss += functions.tohex(b2);
                            grfillRect(cc, gr, x1 + xx1 * p4, yy1 * p4, p4, p4);
                            xx1++;
                        }

                    }
                    if (breakda) {
                        break;
                    }
                    ss += " | ";
                    //i+=b-128+2;
                } else {
                    b++;//b+=10;
                    if (dd + b3 + i / 8 + 1 >= bb.length) {
                        break;
                    }
                    int b2 = getnextbyte(i + (dd + b3) * 8, d6);//bb[dd + b3 + i+1] & 0xFF;
                    i += d6;
                    if (cc1 > b2 && b2 > 0) {
                        cc1 = b2;
                    }
                    if (cc2 < b2 && b2 > 0) {
                        cc2 = b2;
                    }
                    Color cc;
                    if (b2 >= m2.size()) {
                        if (b2 > 255) {
                            cc = new Color(0, 0, 0);
                        } else {
                            cc = new Color(b2, b2, b2);
                        }
                    } else {
                        cc = (Color) m2.get(b2);
                    }
                    //               if (b2==0) b2=255;
                    //             Color c = new Color(b2,b2,b2);

                    if (xx1 + b >= xm) {
                        int k = (xx1 + b) / xm;
                        grfillRect(cc, gr, x1 + xx1 * p4, yy1 * p4, (xm - xx1) * p4, p4);
                        for (int i77 = 1; i77 < k; i77++) {
                            myLog.Systemoutprintln("=" + yy1 + "=" + (i / 8) + "=" + ss);
                            ss = "";
                            yy1++;
                            grfillRect(cc, gr, x1, yy1 * p4, xm * p4, p4);
                        }
                        xx1 = xx1 + b - xm * k;
                        myLog.Systemoutprintln("=" + yy1 + "=" + (i / 8) + "=" + ss);
                        ss = "";
                        yy1++;
                        grfillRect(cc, gr, x1, yy1 * p4, xx1 * p4, p4);
                    } else {
                        grfillRect(cc, gr, x1 + xx1 * p4, yy1 * p4, b * p4, p4);
                        xx1 += b;
                    }
                    ss += functions.tohex(b - 1) + "=" + functions.tohex(b2) + "  ";
                    //i+=2;
                }

            }
            if (type != 1) {
                //grfillRect(Color.red,gr,x1 + 8+xm*p4,y1+ y * p4, p4, p4);//??????????
                gr.drawString(String.format("%02X ", i / 8 + dd + b3),
                        x1 + xm * p4 + 16, pan.getHeight() - 20);
            }
        } else {//-----------------------------------------------------8------------------ 

            while (i < (bb.length - dd - b3)) {
                if (dd + b3 + i > ddm && ddm > 0) {
                    break;
                }
                if (yy1 > ym - 1 && type == 1) {
                    break;
                }
                int b = bb[dd + b3 + i] & 0xFF;
                im = dd + b3 + i;
                if (b >= 128) {
                    myLog.Systemoutprintln("" + i + "=" + ss);
                    ss = functions.tohex(b);
                    boolean breakda = false;
                    for (int i22 = 0; i22 < b - 128 + 1; i22++) {
                        if (dd + b3 + i + i22 + 1 >= bb.length) {
                            breakda = true;
                            break;
                        }
                        int b2 = bb[dd + b3 + i + i22 + 1] & 0xFF;
                        im = dd + b3 + i + i22 + 1;

                        if (cc1 > b2 && b2 > 0) {
                            cc1 = b2;
                        }
                        if (cc2 < b2 && b2 > 0) {
                            cc2 = b2;
                        }
                        Color cc;
                        if (b2 >= m2.size()) {

                            cc = new Color(b2, b2, b2);
                        } else {
                            cc = (Color) m2.get(b2);
                        }

                        //if (b2==0) b2=255;
                        //Color c = new Color(b2,b2,b2);
                        if (xx1 >= xm - 1) {
                            grfillRect(cc, gr, x1 + xx1 * p4, yy1 * p4, p4, p4);
                            xx1 = 0;
                            yy1++;
                        } else {
                            grfillRect(cc, gr, x1 + xx1 * p4, yy1 * p4, p4, p4);
                            xx1++;
                        }
                        ss += functions.tohex(b2);

                    }
                    if (breakda) {
                        break;
                    }
                    ss += " | ";
                    i += b - 128 + 2;
                } else {
                    b++;
                    if (dd + b3 + i + 1 >= bb.length) {
                        break;
                    }
                    int b2 = bb[dd + b3 + i + 1] & 0xFF;
                    im = dd + b3 + i + 1;
                    if (cc1 > b2 && b2 > 0) {
                        cc1 = b2;
                    }
                    if (cc2 < b2 && b2 > 0) {
                        cc2 = b2;
                    }
                    Color cc;
                    if (b2 >= m2.size()) {

                        cc = new Color(b2, b2, b2);
                    } else {
                        cc = (Color) m2.get(b2);
                    }
                    //               if (b2==0) b2=255;
                    //             Color c = new Color(b2,b2,b2);

                    if (xx1 + b >= xm) {
                        int k = (xx1 + b) / xm;
                        grfillRect(cc, gr, x1 + xx1 * p4, yy1 * p4, (xm - xx1) * p4, p4);
                        for (int i77 = 1; i77 < k; i77++) {
                            yy1++;
                            grfillRect(cc, gr, x1, yy1 * p4, xm * p4, p4);
                        }
                        xx1 = xx1 + b - xm * k;
                        yy1++;
                        grfillRect(cc, gr, x1, yy1 * p4, xx1 * p4, p4);
                    } else {
                        grfillRect(cc, gr, x1 + xx1 * p4, yy1 * p4, b * p4, p4);
                        xx1 += b;
                    }
                    ss += functions.tohex(b - 1) + "=" + functions.tohex(b2) + "  ";
                    i += 2;
                }

            }
        }

        jtablo.setText("begin=" + jddd.getText().trim().split(" ")[0] + "\nxm=" + xm + "\nym=" + ym + "=" + yy1
                + "\ncolortable=" + functions.tohex(b3)
                + "\ncolormin=" + cc1
                + "\ncolormax=" + cc2
                + "\ncolorm2=" + m2.size()
                + "\ncolor2=" + i4
                + "\ncolor3=" + i2
                + "\npiclength=" + (im - dd) + tabdop(im - dd)
        );
        piclength = im - dd;

        myLog.Systemoutprintln(ss);
        /*
         for (int i = 0; i < (bb.length - dd - b3); i++) {
            
            
            
         Color c = new Color(bb[dd + b3 + i * 2] & 0xFF, bb[dd + b3 + i * 2] & 0xFF, bb[dd + b3 + i * 2] & 0xFF);
         gr.setColor(c);

         int x5 = bb[dd + b3 + i * 2 + 1] & 0xFF;
         xx2 = xx1 + x5;
         int k = xx2 / xm;
         xx2 = xx2 - k * xm;
         yy2 = yy1 + k;
         if (xx2 > xx1) {
         gr.drawRect(x1 + xx1, yy1, xx2 - xx1, yy2 - yy1);
         } else {
         gr.drawRect(x1 + xx1, yy1, xm - xx1, 1);
         gr.drawRect(x1 + 0, yy2, xx1, 1);
         }

         xx1 = xx2;
         yy1 = yy2;
         }
         */
        return 0;
    }

    public int ris22(Graphics gr, int x1, int x2, int d6, int b3, int ddm, int xm) {
        int xx1 = 0;
        int yy1 = 0;
        Color c = Color.black;
        int nn = 0;
        for (int i = 0; i < 15 * 10;// (bb.length - dd);
                i++) {
            byte b = bb[dd + i + 1];
            //b=(byte)0x0F;
            for (int i8 = 0; i8 < 8; i8++) {
                int bt = functions.getbit(b, i8);//myLog.Systemoutprintln("=="+bt);

                if (bt > 0) {
                    grfillRect(c, gr, x1 + xx1 * p4, (yy1 + nn) * p4, p4, p4);
                } else {
                    grfillRect(Color.white, gr, x1 + xx1 * p4, (yy1 + nn) * p4, p4, p4);
                }
                if (i8 % 2 == 1) {
                    if (yy1 >= xm) {
                        xx1 += 1;
                        yy1 = 0;
                    } else {
                        xx1--;
                        yy1++;
                    }
                } else {
                    xx1++;
                }
            }
            myLog.Systemoutprintln("=====" + functions.tohex(b & 0xFF));
            if (i % 15 == 14) {
                nn += 20;
                xx1 = 0;
                myLog.Systemoutprintln("==---------------===" + functions.tohex(b & 0xFF));
            }

        }
        return 0;
    }

    public int ris33(Graphics gr, int x1, int x2, int d6, int b3, int ddm, int xm) {
        int ym = 0;
        //pan.setSize(pan.getSize().width, ym);
        if (xm == 0 || b3 == 0) {
            return 1;
        }
        for (int y = 0; y < bb.length / xm / b3; y++) {

            for (int x = 0; x < xm; x++) {
                if ((y * xm + x) * b3 < dd) {
                    continue;
                }
                if ((y * xm + x) * b3 > ddm && ddm > 0) {
                    continue;
                }
                int i1 = 0;
                int i2 = 0;
                int i0 = 0;
                if (b3 >= 2) {
                    i1 = 1;
                }
                if (b3 == 3) {
                    i2 = 2;
                }

                //i1=i2=i0=2;
                Color c = new Color(bb[(y * xm + x) * b3 + i0] & 0xFF, bb[(y * xm + x) * b3 + i1] & 0xFF, bb[(y * xm + x) * b3 + i2] & 0xFF);

                grfillRect(c, gr, x1 + x * p4, y * p4, p4, p4);
                if ((y % 8) == 0 && x == xm - 1) {
                    grfillRect(c, gr, x1 + xm * p4 + 8, y * p4, p4, p4);//??????????
                    gr.drawString(String.format("%02X ", (y * xm + x) * b3) + "=" + String.format("%02X ", bb[(y * xm + x) * b3]),
                            x1 + xm * p4 + 16, y * p4 + 16 + pany);
                }
            }
            ym = y * p4;
        }

        ym0 = ym;
        return 0;
    }

    public int ris44(Graphics gr, int x1, int x2, int d6, int b3, int ddm, int xm, int y1, int ym, boolean rect) {
        boolean st = false;
        //for (int y=0;y<bb.length/xm;y++){
        //for (int x=0;x<xm;x++)
        if (d6 < 1) {
            d6 = 1;
        }
        int x = 0;
        int y = 0;

        int pp = 0;
        int c = 0;
        double ck = 1;
        if (rect) {
            if (st) {
                gr.setColor(Color.white);
                gr.fillRect(x1 - 1, pany + y1 - 1, xm * p4 + 1, ym * p4 + 1);
            }
            gr.setColor(Color.red);
            gr.drawRect(x1 - 1, pany + y1 - 1, xm * p4 + 1, ym * p4 + 1);
        }

        if (d6 == 200) {
            for (int i = dd + b3; i < bb.length - 1; i += 2) {//0xc7a
                if (i > ddm && ddm > 0) {
                    break;
                }

                int c1 = bb[i] & 0xFF;
                int c2 = bb[i + 1] & 0xFF;

                int kk = y1 + y * p4 + 16 + pany;

                if ((kk >= 0 && kk < pan.getHeight()) || risall) {
                    Color cc = new Color(c1, c2, c);

                    //myLog.Systemoutprintln(""+c);
                    grfillRect(cc, gr, x1 + x * p4, y1 + y * p4, p4, p4);
                    if (!rect) {
                        if (y % 8 == 0 && x == xm - 1) {
                            if (kk < 65000) {
                                grfillRect(Color.red, gr, x1 + 8 + xm * p4, y1 + y * p4, p4, p4);//??????????
                                gr.drawString(String.format("%02X ", i),
                                        x1 + xm * p4 + 16, kk + 8);
                            }
                        }
                    }

                }

                x++;

                if (x >= xm) {
                    x = 0;
                    y++;
                    if (y >= ym && ym > 0) {
                        return 0;
                    }
                    pp = 0;
                    c = 0;
                }

            }

            return 0;
        }

        if (d6 == 16) {
            ck = 1.f / 256;
        } else if (d6 == 15) {
            ck = 1.f / 128;
        } else if (d6 == 14) {
            ck = 1.f / 64;
        } else if (d6 == 13) {
            ck = 1.f / 32;
        } else if (d6 == 12) {
            ck = 1.f / 16;
        } else if (d6 == 11) {
            ck = 1.f / 8;
        } else if (d6 == 10) {
            ck = 1.f / 4;
        } else if (d6 == 9) {
            ck = 1.f / 2;
        } else if (d6 == 8) {
            ck = 1;
        } else if (d6 == 7) {
            ck = 2;
        } else if (d6 == 6) {
            ck = 4;
        } else if (d6 == 5) {
            ck = 8;
        } else if (d6 == 4) {
            ck = 16;
        } else if (d6 == 3) {
            ck = 32;
        } else if (d6 == 2) {
            ck = 64;
        } else if (d6 == 1) {
            ck = 255;//128;
        }

        for (int i = dd + b3; i < bb.length; i++) {//0xc7a
            if (i > ddm && ddm > 0) {
                break;
            }
            for (byte k = 0; k < 8; k++) {
                //ss+=""+getbit(bb[i],k);
                c = 2 * c + functions.getbit(bb[i], k);
                //myLog.Systemoutprintln(""+pp+"="+c);
                if (pp % d6 == d6 - 1) {
                    //myLog.Systemoutprintln("=="+c + "=="+functions.tohex(c));
                    c = 255 - (int) (c * ck);
                    int kk = y1 + y * p4 + 16 + pany;

                    if ((kk >= 0 && kk < pan.getHeight()) || risall) {
                        Color cc = new Color(c, c, c);

                        //myLog.Systemoutprintln(""+c);
                        //if (c==0)
                        if (st) {
                            if (c == 0) {
                                grfillRect(cc, gr, x1 + x * p4, y1 + ((int) (y * 1.4 - 7)) * p4, p4, p4 * 2
                                );
                            }
                        } else {
                            grfillRect(cc, gr, x1 + x * p4, y1 + //(int)(y*1.4-7) 
                                    y * p4, p4, p4//*2
                            );
                        }

                        if (!rect) {
                            if (y % 8 == 0 && x == xm - 1) {
                                if (kk < 65000) {
                                    grfillRect(Color.red, gr, x1 + 8 + xm * p4, y1 + y * p4, p4, p4);//??????????
                                    gr.drawString(String.format("%02X ", i),
                                            x1 + xm * p4 + 16, kk + 8);
                                }
                            }
                        }

                    }

                    x++;
                    c = 0;

                }
                pp++;

                if (x >= xm) {
                    x = 0;
                    y++;

                    if (y >= ym && ym > 0) {
                        return 0;
                    }
                    pp = 0;
                    c = 0;
                }
            }
            //ss+=" ";
            //if ((i-dd-b3) % xm ==(xm-1)) {x=0;y++;pp=0;c=0;}
        }
        //myLog.Systemoutprintln("col====" + cc1 + "=" + cc2);

        /*
         for (int i=dd;i<0xc7a;i++)
         {
         //  if ((y*xm+x)<dd) continue;
            
         //if ((y*xm+x)>0xc7a) continue;

            
         //i1=i2=i0=2;
            
         x=(i-dd+2) % xm;
         y=(i-dd+2) / xm;
            
         Color c=Color.BLACK;
         gr.setColor(c);
         for (byte k=0;k<8;k++)
         if (getbit(bb[i],k)==1)
         grfillRect(x1+x*8+k, y, 1, 1);
        

         }*/
        return 0;
    }

    public int risimg(Graphics gr, int x1, int x2) {
        try {
            gr.drawImage(img, 0, 0, 240, 240, null);
        } catch (Exception e) {
            myLog.Systemoutprintln(e.toString());
        }
        return 0;
    }

    public int risgif(Graphics gr, int x1, int x2) {
        BufferedImage img = null;

        /*   ImageReader reader = ImageIO.getImageReadersBySuffix("GIF").next();
         ImageInputStream in = ImageIO.createImageInputStream(gifFile);
         reader.setInput(in);
         for (int i = 0, count = reader.getNumImages(true); i < count; i++)
         {
         BufferedImage image = reader.read(i);
         ImageIO.write(image, "PNG", new File("output" + i + ".png"));
         }*/
        try {

            if (nimg < 2) {
                iimg = 0;
            }
            img = reader.read(iimg);
            iimg++;
            iimg = iimg % nimg;

            //img = ImageIO.read(new File(fr.getpath()+fils.getSelectedValue().toString()+"_"+jddd.getText().trim().split(" ")[0]+".gif"));
            gr.drawImage(img, 0, 0, 240, 240, null);
            // myLog.Systemoutprintln("--------------====="+iimg);
//    Thread.sleep(100);

        } catch (Exception e) {
            myLog.Systemoutprintln(e.toString());
        }
        return 0;
    }

    BufferedImage img = null;

    ImageReader reader;
    int iimg = 0;
    int nimg;
    int piclength = 0;
    String fname = "";
    Timer tim;

    public void extract(int type, String tt) {
        reader = null;
        img = null;
        try {
            File ff = new File(fr.getpath() + "files");
            ff.mkdirs();
            String ss = "";
            if (type == 300) {
                ss = ".gif";
                dd = functions.str2int("#" + jddd.getText().trim().split(" ")[0]);
                for (int i = dd; i < bb.length; i++) {
                    if (i == bb.length - 1 || (bb[i] == 0X00 && bb[i + 1] == 0X3B)) {
                        fname = getsel() + tt + ".gif";
                        functions.byte2file(fr.getpath() + "files/" + fname, bb, dd, i - dd + 2);
                        piclength = i - dd + 2;
                        try {

                            reader = ImageIO.getImageReadersBySuffix("GIF").next();
                            ImageInputStream in = ImageIO.createImageInputStream(new File(fr.getpath() + "files/" + fname));
                            reader.setInput(in);
                            nimg = reader.getNumImages(true);
                            //BufferedImage im=
                            reader.read(0);
                            //im=null;
                            myLog.Systemoutprintln("nimg===" + nimg);
                            if (!tim.isRunning() && nimg > 1) {
                                tim.start();
                            }
                            break;
                        } catch (Exception e) {
                            myLog.Systemoutprintln("err=" + e.toString());
                        }
                        if (i == bb.length - 1) {
                            break;
                        }
                    }
                }
            } else if (type == 600) {
                dd = functions.str2int("#" + jddd.getText().trim().split(" ")[0]);
                int bb2 = bb[dd + 2] & 0xFF;
                int bb3 = bb[dd + 3] & 0xFF;
                int bb4 = bb[dd + 4] & 0xFF;
                int bb5 = bb[dd + 5] & 0xFF;
                int ll = bb2 + 256 * bb3 + 256 * 256 * bb4 + 256 * 256 * 256 * bb5;
                fname = getsel() + tt + ".bmp";
                myLog.Systemoutprintln("bmp==size=" + ll + "=bb=" + bb2 + "=" + bb3 + "=" + bb4 + "=" + bb5);
                functions.byte2file(fr.getpath() + "files/" + fname,
                        bb, dd, ll);
                piclength = ll;

                img = ImageIO.read(new File(
                        fr.getpath() + "files/" + fname));

            } else {
                dd = functions.str2int("#" + jddd.getText().trim().split(" ")[0]);

                if (type == 400 || type == 401 || type == 402) {
                    ss = ".jpg";
                } else if (type == 700) {
                    ss = ".png";
                } else if (type == 500) {
                    ss = ".wav";
                } else if (type == 501) {
                    ss = ".mp3";
                } else if (type == 800) {
                    ss = ".fon";
                } else if (type == 555) {
                    String[] s2 = jddd.getText().trim().split(" ");
                    if (s2.length > 2) {
                        ss = "." + s2[2];
                    }
                }
                int ll = bb.length - dd - 1;
                fname = getsel() + tt + ss;
                functions.byte2file(fr.getpath() + "files/" + fname,
                        bb, dd, ll);
                piclength = ll;
                if (type == 400 || type == 401 || type == 402 || type == 700) {
                    try {
                        img = ImageIO.read(new File(
                                fr.getpath() + "files/" + fname));
                    } catch (Exception e) {
                        myLog.Systemoutprintln("err=" + e.toString());
                    }

                }
            }

            jtablo.setText(fname + " file extract\nlength=" + piclength + tabdop(piclength));
        } catch (Exception e) {
            jtablo.setText("err extract==" + e.toString());
            myLog.Systemoutprintln("err extract==" + e.toString());
            e.printStackTrace();
        }
        fname = fname.replace(tt + ".", ".");
    }

    int type0 = 0;
    int x10 = 0;
    int x20 = 0;

    public boolean ris(int x1, int x2, int type) {

        if (x1 < 0) {
                    if (type == 1)             jcc.setText("23");
            //Graphics gr = pan.getGraphics();

            boolean bb = ris2(null, x1, x2, type, false)==0;
            pan.repaint();
            return bb;
        } else {
            type0 = type;
            x10 = x1;
            x20 = x2;
            if (type == 300
                    || type == 600
                    || type == 400
                    || type == 401
                    || type == 402
                    || type == 500 || type == 501
                    || type == 700
                    || type == 800 || type == 555) {
                extract(type, "t");
            }
            pan.repaint();
        }
        return true;
    }
    String ff0 = "";
    int imgx = 0;
    int imgy = 0;

    public int ris2(Graphics gr, int x1, int x2, int type, boolean _risall) {
        risall = _risall;
        //if (txtda) frtxt1.setText("");
        //       if (type==114)
//        myLog.Systemoutprintln("==================xm2====3======="+xm2.size());
        type0 = type;
        x10 = x1;
        x20 = x2;
        if (fils.getSelectedValue() == null) {
            return 0;
        }
        String ff = fr.getpath() //+ "/_ROM.extracted/" 
                + fils.getSelectedValue().toString();
        ///_ROM.extracted/D0764
        if (x1 >= 0) {
            dd = functions.str2int("#" + jddd.getText().trim().split(" ")[0]);
        } else {
            dd = x2;
        }
        //if (dd<24) return false;
        int ddm = functions.str2int("#" + jddm.getText().trim());
        if (!ff.equals(ff0)) {
            bb = functions.file2byte(ff);
            ff0 = ff;
        }
        int xm = (Integer) jxm.getValue();
        int b3 = (Integer) jb3.getValue();
        int d6 = (Integer) jd6.getValue();
        int cd = (Integer) jcd.getValue();

        if (type == 5 || type == 15 || type == 10005) {
            return ris55(gr, x1, x2, d6, b3, ddm, xm, type, cd);
        }
        if (type == 4) {
            return ris44(gr, x1, x2, d6, b3, ddm, xm, 0, 0, false);
        }
        if (type == 3) {
            return ris33(gr, x1, x2, d6, b3, ddm, xm);
        }
        if (type == 2) {
            return ris22(gr, x1, x2, d6, b3, ddm, xm);
        }
        if (type == 114) {

            myLog.Systemoutprintln("==================xm2====4=======" + xm2.size());
            return risfonts(gr, x1, x2, d6, b3, ddm, xm);
        }
        if (type == 133) {

            return ris44(gr, x1, x2, d6, b3, ddm, xm, 0, 0, false);
        }
        if (type == 300) {
            return risgif(gr, x1, x2);
        }
        if (type == 600 || type == 400 || type == 401 || type == 402 || type == 700) {
            return risimg(gr, x1, x2);
        }
        if (type == 500 || type == 501 || type == 555) {
            return 0;
        }
        return ris11(gr, x1, x2, d6, b3, ddm, xm, type, cd);

    }
    int pany = 0;
    boolean risall = false;

    public boolean grfillRect(Color cc, Graphics gr, int x1, int y1, int dx, int dy) {
        if (y1 + pany < 0) {
            return true;
        }
        if (y1 + pany > pan.getHeight() && !risall) {
            return false;
        }
        gr.setColor(cc);
        gr.fillRect(x1, y1 + pany, dx, dy);
        return true;
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //String ss=fr.getpath()+"/_ROM.extracted/"+fils.getSelectedItem().toString();
        ris(pan.getWidth() / 2, pan.getWidth(), 2);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        //String ss=fr.getpath()+"/_ROM.extracted/"+fils.getSelectedItem().toString();
        ris(pan.getWidth() / 2, pan.getWidth(), 3);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        txtda = true;
        String[] s2=jddd.getText().trim().split(" ");
        int dd = functions.str2int("#" + s2[0]);
        int typ=5;
        if (s2.length>1) typ= functions.str2int("#" + s2[1]);
        //ris(-1, dd, 5);
        int k=ris2(null, -1, dd, typ, false);        
        h2txt("?"+k, true);
        txtda = jtextda.isSelected();

    }

    void h2txt(String ss0, boolean visible) {
        if (!txtda) {
            return;
        }

        tt.stop();
        if (frtxt1 == null) {
            frtxt1 = new txt1();
            frtxt1.fr = fr;
        }
        if (visible) {
            frtxt1.setVisible(visible);
        }
        if (fils.getSelectedValue() == null) {
            return;
        }

        //int i0 = 0;
        //int nn = 0;
        String ss = "\n" + ss0 + " " + fils.getSelectedValue().toString() + " " + functions.tohex(dd) + "\t";
        for (int i = 0; i < 24; i++) {
            if (dd - i >= 0) {
                ss += String.format("%02X ", bb[dd - i]) + " ";

            }
        }

        /*       for (int i = dd; i < bb.length; i++) {
         if (i < bb.length - 25) {
         if ((bb[i + 24] & 0xFF) == 0xFF
         && (bb[i + 25] & 0xFF) == 0xFF) {
         ss += "\n" + NewJFrame.tohex(i0) + " " + NewJFrame.tohex(nn) + "\n";
         nn = 0;
         i0 = i;
         }
         }
         ss += String.format("%02X ", bb[i]) + " ";
         nn++;
         }
         ss += "\n" + NewJFrame.tohex(i0) + " " + NewJFrame.tohex(nn) + "\n";*/
        frtxt1.addText2(ss);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if (frtxt1 == null) {
            frtxt1 = new txt1();
        }
        frtxt1.fr = fr;
        frtxt1.setVisible(true);
        dd = functions.str2int("#" + jddd.getText().trim().split(" ")[0]);
        int xm = (Integer) jxm.getValue();
        int d6 = (Integer) jd6.getValue();
        String ss = "";
        int pp = 0;
        for (int i = dd; i < bb.length; i++) {
            for (byte k = 0; k < 8; k++) {
                ss += "" + functions.getbit(bb[i], k);
                if (pp % d6 == d6 - 1) {
                    ss += " ";
                }
                pp++;
            }
            //ss+=" ";
            if ((i - dd) % xm == (xm - 1)) {
                ss += "\n";
                pp = 0;
            }
        }
        frtxt1.setText(ss);
    }//GEN-LAST:event_jButton6ActionPerformed
    public txt1 frtxt1 = null;
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        //String ss=fr.getpath()+"/_ROM.extracted/"+fils.getSelectedItem().toString();
        ris(pan.getWidth() / 2, pan.getWidth(), 4);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jdddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jdddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jdddActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        //String ss=fr.getpath()+"/_ROM.extracted/"+fils.getSelectedItem().toString();
        ris(pan.getWidth() / 2, pan.getWidth(), 15);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jddmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jddmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jddmActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        ris15(0);
    }

    public void ris15(int comm) {
//        myLog.Systemoutprintln("============ris15==="+jddd.getText());

        String[] sddd = jddd.getText().trim().split(" ");
        if (sddd.length < 2) {
            return;
        }
        int ddd = functions.str2int("#" + sddd[0]);
        //if (bb==null) return;

//        myLog.Systemoutprintln(""+ddd+"="+(bb[ddd] & 0xFF));
 /*       String typ=sddd[1];

         if ((bb[ddd] & 0xFF) == 0xFF){
         ris(0, pan.getWidth(), 1);typ="1";
         }else{
         ris(0, pan.getWidth(), 5);typ="5";
         }*/
        int ityp = functions.str2int(sddd[1]);
        //myLog.Systemoutprintln("ityp====" + ityp);
        if (ityp == 200) {
            jScrollPane4.setVisible(true);
            pan.setVisible(false);
            jextract.setVisible(false);
            jfromf.setVisible(false);
            jfromf1.setVisible(false);
            jnegative.setVisible(false);
            jfsize.setVisible(false);

        } else {
            jScrollPane4.setVisible(false);
            pan.setVisible(true);
            jextract.setVisible(true);
            jfromf.setVisible(true);//System.out.println(""+ityp);
            if (ityp == 1 || ityp == 5) {
                jfromf1.setVisible(true);
            }
            jnegative.setVisible(true);
            if (ityp == 111) {
                jfsize.setVisible(true);
            } else {
                jfsize.setVisible(false);
            }
        }
//myLog.Systemoutprintln("============ris15==2222222222="+jddd.getText()+"==="+ityp);
        if (ityp == 1) {
            jcc.setText("23");
            ris(0, pan.getWidth(), ityp + comm);
        } else if (ityp == 5) {
            if ((bb[ddd - 6] & 0xFF) > 0&&(bb[ddd - 8] & 0xFF) <11) {
                jcc.setText("33");
            } else {
                jcc.setText("0");
            }
            ris(0, pan.getWidth(), ityp + comm);
        } else if (ityp == 133) {
            ris(0, pan.getWidth(), ityp);
        } else if (ityp == 111) {
            jxm.setValue(32);
            jb3.setValue(1);
            jd6.setValue(1);
            getfonts();
        } else if (ityp == 200) {
            gettxts();
            return;
        } else if (ityp == 300
                || ityp == 600
                || ityp == 400
                || ityp == 401
                || ityp == 402
                || ityp == 500 || ityp == 501
                || ityp == 700
                || ityp == 800
                || ityp == 555) {
            ris(0, pan.getWidth(), ityp);
            return;
        }
        //else if (ityp==114) {ris(0, pan.getWidth(), ityp);return;}
        /*String ss="";
         if (ddd>=24)
         for (int i=0;i<24;i++){
         if (ddd-(23-i)-1<bb.length)
         ss+=NewJFrame.tohex(bb[ddd-(23-i)-1] & 0xFF);
         }
         if (frtxt1 == null) {
         frtxt1 = new txt1();
         }
         frtxt1.addText(ss+" =="+typ);*/
//            myLog.Systemoutprintln("111111111111111==="+ss);
    }//GEN-LAST:event_jButton9ActionPerformed
    public void gettxts() {
        byte[] b2 = new byte[2];
        String ss = "";
        DefaultTableModel dtm = (DefaultTableModel) jtab.getModel();
        dtm.setRowCount(0);
        int in = 0;
        int n = 0;
        if (bb == null) {
            System.out.println("bb-null");
            return;
        }
        for (int i = 0; i < bb.length - 1; i += 2) {
            if (bb[i] == 0 && bb[i + 1] == 0) {

                if (ss.length() > 0) {
                    n++;
                    String[] s3 = new String[4];
                    s3[0] = "" + n;
                    s3[1] = "" + in;
                    s3[2] = "" + ((i - in - 2) / 2);
                    s3[3] = ss;
                    dtm.addRow(s3);
                }
                ss = "";
                in = i;
            } else {
                b2[1] = bb[i];
                b2[0] = bb[i + 1];
                ss += new String(b2, Charset.forName("UTF16"));
            }
        }
    }

    public void getfonts() {

        if (fils.getSelectedValue() == null) {
            return;
        }
        String ff = fr.getpath() //+ "/_ROM.extracted/" 
                + fils.getSelectedValue().toString();
//        myLog.Systemoutprintln("====getfonts====xm2===fffff=="+fils.getSelectedValue().toString());
        ///_ROM.extracted/D0764

        dd = functions.str2int("#" + jddd.getText().trim().split(" ")[0]);

        int ddm = functions.str2int("#" + jddm.getText().trim());

        //ff=fr.getpath()+"/_ROM.extracted/"+"D13AC";//"D0764";//DE7D0";
        bb = functions.file2byte(ff);
        //for (int i=0;i<1000;i++) bb[i]=(byte)(bb[2*i+2]&0xFF-bb[2*i]&0xFF);
        //myLog.Systemoutprintln("dd========" + dd + "==" + bb.length);
        int xm = (Integer) jxm.getValue();
        int bb3 = (Integer) jb3.getValue();
        int d6 = (Integer) jd6.getValue();
        int cd = (Integer) jcd.getValue();
        int in = dd + bb3;
        int in0 = in;
//        myLog.Systemoutprintln("==getfonts===xm2==="+dd+"="+bb3);

        xm2 = new Vector();
        int lm = 0;
        for (int i = in0; i < bb.length; i += 4) {

            int b1 = bb[i] & 0xFF;
            int b2 = bb[i + 1] & 0xFF;
            int b3 = bb[i + 2] & 0xFF;
            int b4 = bb[i + 3] & 0xFF;

            in = i + 4;
            if (((b1 == 0 && b2 == 0) || b3 > 0 || b4 > 0) && i > in0) {
                break;
            }
            xm2.add(b1 + 256 * b2);
            lm = b1 + 256 * b2;
            //myLog.Systemoutprintln(NewJFrame.tohex(b1)+NewJFrame.tohex(b2)+NewJFrame.tohex(b3)+NewJFrame.tohex(b4)+"="+(b1+256*b2)+"="+xm2.size());

        }

        //       myLog.Systemoutprintln("===xm2======================="+xm2.size());
        in0 = in;
        Vector xm3 = new Vector();

        for (int i = in0; i < bb.length - 4; i += 2) {
            int b1 = bb[i] & 0xFF;
            int b2 = bb[i + 1] & 0xFF;
            int b3 = bb[i + 2] & 0xFF;
            int b4 = bb[i + 3] & 0xFF;
            if ((b3 + 256 * b4 <= b1 + 256 * b2 || b4 > b3 + 1) && i == in0) {
                break;
            }
            in = i + 2;
            if ((b3 + 256 * b4 <= b1 + 256 * b2 || b4 > b3 + 1) && i > in0) {
                break;
            }

            xm3.add(b1 + 256 * b2);
            //myLog.Systemoutprintln(NewJFrame.tohex(b1)+NewJFrame.tohex(b2));
        }
//        myLog.Systemoutprintln("===xm2=======22================"+xm2.size());
        jddd.setText(functions.tohex(in) + "114");

        if (cd + 1 >= xm2.size()) {
            return;
        }
        int l1 = ((Integer) xm2.get(cd));
        int l2 = ((Integer) xm2.get(cd + 1));
        jb3.setValue(l1);

        int l = (l2 - l1) * 8 / 14;
        if ((Integer) jfsize.getValue() < 4) {
            jfsize.setValue(14);
        }
        myLog.Systemoutprintln("lll=" + xm2.get(1) + "=" + l + "=====" + xm2.size() + "===" + xm3.size() + "==" + functions.tohex(dd));

        int dd0 = 0;
        xms = new int[xm2.size()];
        int i2 = 0;
        int ist = 0;
        for (int i = 0; i < xm2.size() + 100; i++) {//5
            if (dd - i < 0) {
                break;
            }

            if (bb[dd - i] == 0 && ist == 0) {
                continue;
            }
            if (bb[dd - i] > 0 && ist == 0) {
                ist = 1;
            }

            if (bb[dd - i] == 0) {
                dd0 = dd - i;
                break;
            }
            myLog.Systemoutprintln("" + (xm2.size() - i2 - 2) + "=" + bb[dd - i]);
            if (xm2.size() - i2 - 2 < xms.length && xm2.size() - i2 - 2 >= 0) {
                xms[xm2.size() - i2 - 2] = bb[dd - i];
            }
            i2++;
        }
        /*
         for (int i=0;i<xms.length;i++){
         System.out.println("xms="+xms[i]);
         }*/

        myLog.Systemoutprintln("kk====" + i2);
        jtablo.setText("font count=" + xm2.size()
                + "\nbegin=" + functions.tohex(dd0)//+"="+functions.tohex(in)
                + "\nlength=" + functions.tohex(in + lm - dd0)
                + "\nend=" + functions.tohex(in + lm)
        );

        jxm.setValue(l);
        if (l == 0) {
            return;
        }
        jcd.setValue(0);
        ris(0, pan.getWidth(), 114);

    }
    Vector xm2;
    int[] xms;

    public int risfonts(Graphics gr, int x1, int x2, int d6, int b3, int ddm, int xm) {
//    myLog.Systemoutprintln("ris fonts===="+xm2.size()+"==="+jddd.getText().trim().split(" ")[0]);
        int cc = (Integer) jfsize.getValue();
        imgx = 40;
        imgy = (xm2.size() + 1) * (cc + 2) + 1;
        dd = functions.str2int("#" + jddd.getText().trim().split(" ")[0]);
        double rcc = 0;
        int icc = 0;
        for (int i = 0; i < xms.length - 1; i++) {
            if (xms[i] <= 0) {
                continue;
            }
            int l1 = ((Integer) xm2.get(i));
            int l2 = ((Integer) xm2.get(i + 1));
            double cc1 = 1.0 * (l2 - l1) * 8 / xms[i];
                //if (i<20)

            if (xms[i] > 5) {
                myLog.Systemoutprintln("cc1==" + i + "==" + cc1 + "=" + xms[i] + "=" + (l2 - l1));
                icc++;
                rcc += cc1;

            }
        }
        if (icc > 0) {
            cc = (int) Math.round(rcc * 1.0 / icc);
        } else {
            cc = (Integer) jfsize.getValue();
        }
        jfsize.setValue(cc);
        //System.out.println("cc======="+cc);
        if (cc > 0) {
            for (int i = 0; i < xm2.size() - 1; i++) {//myLog.Systemoutprintln("ffffffffffffffffffffff1111");

                int cd = i;
                if (cd < 0) {
                    cd = 0;
                    jcd.setValue(0);
                }
                int l1 = ((Integer) xm2.get(cd));
                int l2 = ((Integer) xm2.get(cd + 1));
                //jb3.setText(""+l1);

                ddm = dd + l2 - 1;
                //jddm.setText(NewJFrame.tohex(ddm));

                int l = (l2 - l1) * 8 / cc;
                //if (i==0) System.out.println("=====!!!!====="+(l2-l1)+"="+l);
                l = xms[i];
            //myLog.Systemoutprintln("lll="+xm2.get(1)+"="+l+"====="+xm2.size()+"==="+xm3.size());
                //jxm.setText(""+l);
                if (l == 0) {
                    continue;
                    //return true;
                }
                //ris44(gr, x1 + 1, x2, d6, l1, ddm, l, i * (cc * p4 + p4 * 2) + 1, cc, true);
                ris44(gr, x1 + 1, x2, d6, l1, ddm, l, i * (cc * p4 + p4 * 2) + 1, cc, true);
                //ris(0, pan.getWidth(), 4);

            }
        }
        return 0;
    }

    public void risfont() {
        int cc = Integer.decode(jcc.getText());
        int cd = (Integer) jcd.getValue();
        if (cd + 1 >= xm2.size()) {
            return;
        }
        if (cd < 0) {
            cd = 0;
            jcd.setValue(0);
        }
        int l1 = ((Integer) xm2.get(cd));
        int l2 = ((Integer) xm2.get(cd + 1));
        jb3.setValue(l1);
        dd = functions.str2int("#" + jddd.getText().trim().split(" ")[0]);
        int ddm = dd + l2 - 1;
        jddm.setText(functions.tohex(ddm));
        int l = (l2 - l1) * 8 / cc;
        //myLog.Systemoutprintln("lll="+xm2.get(1)+"="+l+"====="+xm2.size()+"==="+xm3.size());
        jxm.setValue(l);
        if (l == 0) {
            return;
        }

        ris(0, pan.getWidth(), 4);
    }
    private void jcd00ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcd00ActionPerformed
        // TODO add your handling code here:
        jcd1.setValue(0);
        pany = 0;
        ris(x10, x20, type0);
    }//GEN-LAST:event_jcd00ActionPerformed

    private void filsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_filsValueChanged
        // TODO add your handling code here:
        updatejef0();
    }

    void updatejef0() {
       //updatejef();
        //jprogress.setIndeterminate(true);
        TwoWorker task = new TwoWorker();
        task.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent e) {
                if ("progress".equals(e.getPropertyName())) {
                    //jprogress.setIndeterminate(false);
                    jprogress.setValue((Integer) e.getNewValue());
                }
            }
        });
        task.execute();
        //ris15a();
    }//GEN-LAST:event_filsValueChanged

    private class TwoWorker extends SwingWorker<Double, Double> {

        @Override
        protected Double doInBackground() throws Exception {
            /*          for (int i = 1; i <= N; i++) {
             x = x - (((x * x - 2) / (2 * x)));
             setProgress(i * (100 / N));
             publish(Double.valueOf(x));
             Thread.sleep(1000); // simulate latency
             }*/
            if (repaintda) {
                repaintda = false;
                jprogress.setVisible(true);
                updatejef();
                ris15a();
                jprogress.setVisible(false);
                repaintda = true;
            }
            return null;//Double.valueOf(100);
        }

        @Override
        protected void process(List<Double> chunks) {
            /*      for (double d : chunks) {
             label.setText(df.format(d));
             }*/
        }
    }


    private void jefValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jefValueChanged
        // TODO add your handling code here:
        /*
         if (key==40){               
         if (jef.getModel().getSize()==0)
         {
         int i=fils.getSelectedIndex();
         if (i>=0&&i<fils.getModel().getSize()-1) {
         fils.setSelectedIndex(i+1);
         fils.ensureIndexIsVisible(fils.getSelectedIndex());
         jef.setSelectedIndex(0);
         }
        
         } else      
         if (seli+1==jef.getModel().getSize()){
            
         int i=fils.getSelectedIndex();
         if (i>=0&&i<fils.getModel().getSize()-1) {
         fils.setSelectedIndex(i+1);
         fils.ensureIndexIsVisible(fils.getSelectedIndex());
         jef.setSelectedIndex(0);
         }
         }
         }else
         if (key==38){               
         if (jef.getModel().getSize()==0)
         {
         int i=fils.getSelectedIndex();
         if (i>0&&i<fils.getModel().getSize()-1){
         fils.setSelectedIndex(i-1);
         fils.ensureIndexIsVisible(fils.getSelectedIndex());
         jef.setSelectedIndex(jef.getModel().getSize()-1);
         }
        
         } else      
         if (seli==0){
            
         int i=fils.getSelectedIndex();
         if (i>=0&&i<fils.getModel().getSize()-1) {
         fils.setSelectedIndex(i-1);
         fils.ensureIndexIsVisible(fils.getSelectedIndex());
         jef.setSelectedIndex(jef.getModel().getSize()-1);
         }
         }
         }      
         */

//        ris15a();
//        myLog.Systemoutprintln("================================================--------------------------"
//                + "============================"+key+"="+jef.getSelectedIndex());

    }//GEN-LAST:event_jefValueChanged

    private void jccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jccActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jccActionPerformed
    boolean txtda = false;
    private void jcdVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_jcdVetoableChange
        // TODO add your handling code here:

    }//GEN-LAST:event_jcdVetoableChange

    private void jcdStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jcdStateChanged
        // TODO add your handling code here:
        if (jddd.getText().trim().endsWith(" 112")) {
            risfont();
        } else {
            //ris15(0);
        }
    }//GEN-LAST:event_jcdStateChanged

    private void jnegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jnegActionPerformed
        // TODO add your handling code here:
        ris15(0);
    }//GEN-LAST:event_jnegActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:

        for (int i = 0; i < 100; i++) {
            int i1 = bb[2 * i] & 0xFF;
            int i2 = bb[2 * i + 2] & 0xFF;
            frtxt1.addText("" + (i2 - i1) + " " + functions.tohex(i2) + "=" + functions.tohex(i1));
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jcd1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jcd1StateChanged

        int i = (Integer) jcd1.getValue();
        if (i < 0) {
            jcd1.setValue(0);
            i = 0;
        }
        pany = -i * pan.getHeight() / 10;
        ris(x10, x20, type0);

    }//GEN-LAST:event_jcd1StateChanged

    private void jcd1VetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_jcd1VetoableChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jcd1VetoableChange

    private void jefMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jefMouseClicked
        // TODO add your handling code here:
        //myLog.Systemoutprintln("mouse click==");    
        ris15a();
        if (evt.getClickCount() > 1) {
            String[] ss = jef.getSelectedValue().toString().split(" ");
            if (ss.length < 3) {
                return;
            }

            File file = new File(fr.getpath() + "files/" + getsel() + "t." + ss[2]);
            try {
                Desktop.getDesktop().open(file);
            } catch (Exception e) {
                myLog.Systemoutprintln("err=" + e.toString());
            }
        }

    }//GEN-LAST:event_jefMouseClicked

    private void jb3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jb3StateChanged
        // TODO add your handling code here:
        //ris15();

    }//GEN-LAST:event_jb3StateChanged

    private void jb3VetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_jb3VetoableChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jb3VetoableChange

    private void jxmStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jxmStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jxmStateChanged

    private void jxmVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_jxmVetoableChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jxmVetoableChange

    private void jd6StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jd6StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jd6StateChanged

    private void jd6VetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_jd6VetoableChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jd6VetoableChange
    int key = -1;
    Timer tt = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            myLog.Systemoutprintln("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++key+++++++++++++++++++++++++++++++++++++++");
            if (key >= 0) {
                mykey(key, 1);
            }
            if (!isactiv() || !jef.isFocusOwner()) {
                tt.stop();
            }
        }
    });

    public boolean isactiv() {
        return this.isActive();
    }
    private void jefKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jefKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 40 || evt.getKeyCode() == 38) {
            seli = jef.getSelectedIndex();
            key = evt.getKeyCode();
            tt.start();
        }
        //myLog.Systemoutprintln("iii=ppp=="+key);

    }//GEN-LAST:event_jefKeyPressed

    private void jefKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jefKeyReleased
        // TODO add your handling code here:
        //            myLog.Systemoutprintln("keyyyyyyyyyyyyyyyy=========="+evt.getKeyCode()+"="+evt.getKeyChar()
        //   +"="+seli+"="+jef.getModel().getSize());

        mykey(key, 0);//evt.getKeyCode());
        tt.stop();
//        ris15a();
        //     myLog.Systemoutprintln("================================================--------------------------============================"+evt.getKeyCode());

    }//GEN-LAST:event_jefKeyReleased
    public void mykey(int key, int d) {
//        myLog.Systemoutprintln("mykey=="+d+"="+key+"=="+fils.getSelectedIndex());
        if (key == 40) {
            if (jef.getModel().getSize() == 0) {
                int i = fils.getSelectedIndex();
                if (i >= 0 && i < fils.getModel().getSize() - 1) {
                    fils.setSelectedIndex(i + 1);
                    fils.ensureIndexIsVisible(fils.getSelectedIndex());
                    jef.setSelectedIndex(0);
                }

            } else if (seli + 1 == jef.getModel().getSize()) {

                int i = fils.getSelectedIndex();
                if (i >= 0 && i < fils.getModel().getSize() - 1) {
                    fils.setSelectedIndex(i + 1);
                    fils.ensureIndexIsVisible(fils.getSelectedIndex());
                    jef.setSelectedIndex(0);
                }
            }
        } else if (key == 38) {
            if (jef.getModel().getSize() == 0) {
                int i = fils.getSelectedIndex();
                if (i > 0 && i < fils.getModel().getSize() - 1) {
                    fils.setSelectedIndex(i - 1);
                    fils.ensureIndexIsVisible(fils.getSelectedIndex());
                    jef.setSelectedIndex(jef.getModel().getSize() - 1);
                }

            } else if (seli == 0) {

                int i = fils.getSelectedIndex();
                if (i >= 0 && i < fils.getModel().getSize() - 1) {
                    fils.setSelectedIndex(i - 1);
                    fils.ensureIndexIsVisible(fils.getSelectedIndex());
                    jef.setSelectedIndex(jef.getModel().getSize() - 1);
                }
            }
        }
        key = -1;//myLog.Systemoutprintln("key=-1");
        ris15a();
    }

    public void ris15a() {//myLog.Systemoutprintln("ris15a---------------");
        if (jtab.isEditing()) {
            jtab.getCellEditor().stopCellEditing();
        }
        if (jef.getModel().getSize() > 0 && jef.getSelectedValue() != null) {
            jddd.setText(jef.getSelectedValue().toString());

            jScrollPane4.setVisible(true);
            pan.setVisible(true);
            //  jextract.setVisible(true);
            //  jfromf.setVisible(true);
            //  jnegative.setVisible(true); 
            ris15(0);

        } else {

            jextract.setVisible(false);
            jfromf.setVisible(false);
            jfromf1.setVisible(false);
            jnegative.setVisible(false);

            jScrollPane4.setVisible(false);
            //pan.setVisible(false);

            //jddd.setText("18 1");
            //jScrollPane4.setVisible(true);
            pan.setVisible(true);
            //  jextract.setVisible(true);
            //  jfromf.setVisible(true);
            //  jnegative.setVisible(true); 

            jxm.setValue(32);
            jb3.setValue(0);
            jddd.setText("0");
            jd6.setValue(0);
            jcc.setText("0");

            //ris15(0);
        }
    }
    int seli = 0;
    private void jefKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jefKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jefKeyTyped

    private void jextractActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jextractActionPerformed
        // TODO add your handling code here:extract()
        //    String writerNames[] = ImageIO.getWriterFormatNames();
        //    for (String ss:writerNames)myLog.Systemoutprintln(ss);
        File ff = new File(fr.getpath() + "files");
        ff.mkdirs();
        String[] sddd = jddd.getText().trim().split(" ");
        if (sddd.length < 2) {
            return;
        }

        //if (bb==null) return;
//        myLog.Systemoutprintln(""+ddd+"="+(bb[ddd] & 0xFF));
 /*       String typ=sddd[1];

         if ((bb[ddd] & 0xFF) == 0xFF){
         ris(0, pan.getWidth(), 1);typ="1";
         }else{
         ris(0, pan.getWidth(), 5);typ="5";
         }*/
        int ityp = functions.str2int(sddd[1]);
        if (ityp == 500 || ityp == 501 || ityp == 300 || ityp == 400 || ityp == 401 || ityp == 402 || ityp == 700 || ityp == 555) {
            extract(ityp, "");
            return;
        }
        int xx = imgx;
        int yy = imgy;

        BufferedImage bImage = new BufferedImage(xx, yy,
                BufferedImage.TYPE_INT_ARGB);// .TYPE_INT_ARGB);// .TYPE_INT_ARGB);

        myLog.Systemoutprintln("xxyy11==" + xx + "=" + yy);

        Graphics2D g2 = bImage.createGraphics();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
        g2.fillRect(0, 0, xx, yy);

//reset composite
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));// .SRC_OVER));

        int pany0 = pany;
        int p40 = p4;
        pany = 0;
        p4 = 1;
        ris2(g2, 0, 240, ityp, true);
        pany = pany0;
        p4 = p40;
        /*     g2.setColor(Color.BLUE);
         //g2d.setStroke(LABEL_DRAW_STROKE);
         g2.drawLine(0,0,22,222);
         g2.setColor(Color.red);
         //g2d.setStroke(LABEL_DRAW_STROKE);
         g2.drawLine(22,222,155,0);
         //ris2(Graphics gr,int x1, int x2, int type)*/

        //g2.setColor(new Color(55,55,55,55));
        //g2.fillRect(55, 55, 111, 111);
        myLog.Systemoutprintln("xxyy22==" + bImage.getWidth() + "=" + bImage.getHeight());
        String fname = getsel();
        saveImage(bImage, fr.getpath() + "files/" + fname);

        //pan.getGraphics().drawImage(bImage, 44, 44, null);
        g2.dispose();
    }//GEN-LAST:event_jextractActionPerformed

    public String getsel() {
        return (fils.getSelectedValue().toString().replace(".", "") + "_" + jddd.getText().trim().split(" ")[0]).replace(".", "");
    }

    private void saveImage(BufferedImage bImage, String ff) {

        File saveFile = new File(ff + "." + imext);

        try {
            ImageIO.write(bImage, imext, saveFile);

            /*             AnimatedGIFWriter writer = new AnimatedGIFWriter(true);
             OutputStream os = new FileOutputStream(ff+"."+imext);
             // Grab the BufferedImage whatever way you can
             BufferedImage frame;
             // Use -1 for both logical screen width and height to use the first frame dimension
             writer.prepareForWrite(os, -1, -1);
             writer.writeFrame(os, bImage);
             // Keep adding frame here
             writer.finishWrite(os);*/
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void jnegativeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jnegativeActionPerformed
        // TODO add your handling code here:
        ris15(10000);
    }//GEN-LAST:event_jnegativeActionPerformed

    private void jtofileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtofileActionPerformed
        // TODO add your handling code here:
        if (fils.getSelectedValue() == null) {
            return;
        }
        String ff2 = fr.getpath() //+ "/_ROM.extracted/" 
                + fils.getSelectedValue().toString();
        functions.byte2file(ff2, bb);
    }//GEN-LAST:event_jtofileActionPerformed

    private void jtoromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtoromActionPerformed
        // TODO add your handling code here:
        if (fils.getSelectedValue() == null || fr.bb == null) {
            return;
        }

        String ff2 = fr.getpath() //+ "/_ROM.extracted/" 
                + fils.getSelectedValue().toString();
        String ff2c = ff2 + "c";
        String ff2ad;
        byte[] tbb;
        if (fils.getSelectedValue().toString().endsWith("a.b")) {
            ff2ad = fils.getSelectedValue().toString().replace("a.b", "").trim();
            tbb = functions.file2byte(ff2);
            functions.byte2file(ff2c, tbb);
        } else {
            ff2ad = fils.getSelectedValue().toString().replace(".b", "").trim();
            String ss = "e -lc2 -d22 \"" + ff2 + "\" \"" + ff2c + "\"";
            if (hexx.getText().startsWith("e ")){
                 ss = hexx.getText()+" \"" + ff2 + "\" \"" + ff2c + "\"";
            }
                
            LzmaAlone.exec(ss);
        }
        //--------------------------------------------------------------
        int add2 = functions.str2int("#" + ff2ad);
        myLog.Systemoutprintln("=====" + ff2ad);
        if (add2 < 0) {
            jtablo.setText("error");
            return;
        }
        File ffa = new File(fr.getpath() + ff2ad + ".a");
        tbb = functions.file2byte(ff2c);
        for (int i = 0; i < tbb.length; i++) {
            if (ffa.exists() && i >= ffa.length()) {
                break;
            }
            fr.bb[add2 + i] = tbb[i];
        }
        if (ffa.exists()) {
            if (tbb.length > ffa.length()) {
                jtablo.setText("file is big\nfile=" + tbb.length + "\nplace=" + ffa.length());
            } else {
                jtablo.setText("file=" + tbb.length + "\nplace=" + ffa.length());
            }
        }

    }//GEN-LAST:event_jtoromActionPerformed
    private long[][] convertTo2DWithoutUsingGetRGB(BufferedImage image) {//static

        //image.getData().getDataBuffer()
        final int width = image.getWidth();
        final int height = image.getHeight();

        long[][] result = new long[height][width];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                int c1 = image.getRGB(x, y);
                long cr = ((c1 >> 16) & 0xFF);
                long cg = ((c1 >> 8) & 0xFF);
                long cb = ((c1 >> 0) & 0xFF);
                long ca = (c1 >> 24) & 0xFF;
                long argb = 0;
                argb += ca << 24; // alpha
                argb += cb; // blue
                argb += (cg << 8); // green
                argb += (cr << 16); // red
                result[y][x] = argb;

            }
        }
        /*   //     if (1==1) return result;
         final boolean hasAlphaChannel = image.getAlphaRaster() != null;
         final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        
         if (hasAlphaChannel) {
         final int pixelLength = 4;
         for (int pixel = 0, row = 0, col = 0; pixel < pixels.length-3; pixel += pixelLength) {
         long argb = 0;
         argb += (((long) pixels[pixel] & 0xff) << 24); // alpha
         argb += ((long) pixels[pixel + 1] & 0xff); // blue
         argb += (((long) pixels[pixel + 2] & 0xff) << 8); // green
         argb += (((long) pixels[pixel + 3] & 0xff) << 16); // red
         result[row][col] = argb;
                
                
               
                
         col++;
         if (col == width) {
         col = 0;
         row++;
         }
         }
         } else {
         final int pixelLength = 3;
         for (int pixel = 0, row = 0, col = 0; pixel < pixels.length-2; pixel += pixelLength) {
         long argb = 0;
         argb += 255L << 24;//0xff000000;//-16777216; // 255 alpha
         argb += ((long) pixels[pixel] & 0xff); // blue
         argb += (((long) pixels[pixel + 1] & 0xff) << 8); // green
         argb += (((long) pixels[pixel + 2] & 0xff) << 16); // red
         result[row][col] = argb;//myLog.Systemoutprintln("bbbbbbbbb===="+);
         col++;
         if (col == width) {
         col = 0;
         row++;
         }
         }
         }
         */
        return result;
    }

    public void getcolors(BufferedImage bimg) {
        if (bimg.getColorModel() instanceof IndexColorModel) {
            IndexColorModel colorModel = (IndexColorModel) bimg.getColorModel();
            int colorCount = colorModel.getMapSize();
            byte[] reds = new byte[colorCount];
            byte[] greens = new byte[colorCount];
            byte[] blues = new byte[colorCount];
            byte[] alpha = new byte[colorCount];
            colorModel.getReds(reds);
            colorModel.getGreens(greens);
            colorModel.getBlues(blues);
            colorModel.getAlphas(alpha);
            for (int i = 0; i < colorCount; i++) {
                myLog.Systemoutprintln("" + alpha[i] + "=" + reds[i] + "=" + greens[i] + "=" + blues[i]);
            }
            myLog.Systemoutprintln("=====" + colorCount);

        } else {
            myLog.Systemoutprintln("not indexed");
        }
    }
    String imext = "png";

    public void fromgif(int typ) {
        byte[] bt = functions.file2byte(fr.getpath() + "files/" + getsel() + ".gif");
        for (int j = 0; j < bt.length; j++) {
            if ((j + dd - 11) >= bb.length) {
                break;
            }
            bb[j + dd - 11] = bt[j];
        }
        int i = bt.length;
        String sdop = "";
        if (piclength < i + 11) {
            sdop = "\nfile is big\nfile=" + i + "\nplace=" + piclength;
        } else {
            sdop = "\nfile=" + i + "\nplace=" + piclength;
            //for (int it=i;it<piclength;it++) bb[ddd+b3+it]=0;

        }
        jtablo.setText("begin=" + jddd.getText().trim().split(" ")[0]
                + sdop + tabdop(i));
        piclength = i;
    }

    public void from1(int typ, String fname0) {
        try {

            int ddd = functions.str2int("#" + jddd.getText().trim().split(" ")[0]);

            BufferedImage bimg;
            if (fname0 == null) {
                bimg = ImageIO.read(new File(fr.getpath() + "files/" + getsel() + "." + imext));
            } else {
                bimg = ImageIO.read(new File(fname0));
            }

//            pan.getGraphics().drawImage(bimg, 0, 0, null);
//            if (1==1) return;
            HashMap<Long, Integer> cc1 = new HashMap<Long, Integer>();

            long[][] p2 = convertTo2DWithoutUsingGetRGB(bimg);
            int ic = 1;
            for (int y = 0; y < p2.length; y++) {
                for (int x = 0; x < p2[y].length; x++) {
                    if (!cc1.containsKey(p2[y][x])
                            && p2[y][x] != 0) {
                        cc1.put(p2[y][x], ic);
                        ic++;
                    }
                }
            }
            Object[] ob = cc1.keySet().toArray();
            Long[] obi = new Long[ob.length];
            for (int i = 0; i < ob.length; i++) {
                obi[i] = (Long) ob[i];
            }
            Arrays.sort(obi);
            HashMap<Long, Integer> cc = new HashMap<Long, Integer>();
            for (int i = 0; i < ob.length; i++) {
                cc.put(obi[i], i);
                //myLog.Systemoutprintln(""+i+"="+NewJFrame.tohex(obi[i])+"="+obi[i]);
            }
            int lg = functions.log2(cc.size() + 1);
            myLog.Systemoutprintln("lg======" + lg + "=cc.size=" + cc.size());
            int b3 = 0;
            int ii2 = 0;
            for (int i = 0; i < cc.size(); i++) {
                //int c1=(Integer)cc.keySet().toArray()[i];
                long c1 = obi[cc.size() - 1 - i];
                //myLog.Systemoutprintln(NewJFrame.tohex(c1));

                long ca = (c1 >> 24) & 0xFF;
                if (ca != 255) {
                    continue;
                }

                long cr = ((c1 >> 16) & 0xFF) / 8;
                long cg = ((c1 >> 8) & 0xFF) / 4;
                long cb = ((c1 >> 0) & 0xFF) / 8;

                long c22 = cb;
                c22 += cg << 5;
                c22 += cr << 11;

                byte b1 = (byte) (c22 % 256);
                byte b2 = (byte) (c22 / 256);
                bb[ddd + b3] = b1;
                bb[ddd + b3 + 1] = b2;
                b3 += 2;
                ii2++;
//                 myLog.Systemoutprintln(""+NewJFrame.tohex(b1&0xFF)+"="+NewJFrame.tohex(b2&0xFF)+"="+cr+"="+cg+"="+cb);

            }
            int ii3 = 0;
            for (int i = 0; i < cc.size(); i++) {
                //int c1=(Integer)cc.keySet().toArray()[i];
                long c1 = obi[cc.size() - 1 - i];
                //myLog.Systemoutprintln(NewJFrame.tohex(c1));

                long ca = (c1 >> 24) & 0xFF;
                if (ca == 255) {
                    continue;
                }

                long cr = ((c1 >> 16) & 0xFF) / 8;
                long cg = ((c1 >> 8) & 0xFF) / 4;
                long cb = ((c1 >> 0) & 0xFF) / 8;

                long c22 = cb;
                c22 += cg << 5;
                c22 += cr << 11;

                byte b1 = (byte) (c22 % 256);
                byte b2 = (byte) (c22 / 256);
                byte b33 = (byte) (255 - ca);
                bb[ddd + b3] = b1;
                bb[ddd + b3 + 1] = b2;
                bb[ddd + b3 + 2] = b33;
                b3 += 3;
                ii3++;
                //23
                //               myLog.Systemoutprintln(NewJFrame.tohex(c1)+"===================="+NewJFrame.tohex(b1&0xFF)+"="+NewJFrame.tohex(b2&0xFF)+"="+NewJFrame.tohex(b33&0xFF)+"="+cr+"="+cg+"="+cb+"="+ca);

            }

            bb[ddd - 4] = (byte) ii2;
            bb[ddd - 2] = (byte) ii3;

            myLog.Systemoutprintln("ii2=" + ii2 + "=ii3=" + ii3 + "=b3=" + b3);

            b3 = 2 * ii2 + 3 * ii3;

            if (b3 % 2 == 1) {
                b3++;
            }
            myLog.Systemoutprintln("ii2=" + ii2 + "=ii3=" + ii3 + "=b3=" + b3);
//        xm=i12-i7-i9;

//if (xm<=1 && x1<0) return false;
            //      ym=i15-i6-i8;           
            bb[ddd - 12] = (byte) (bimg.getWidth() + bb[ddd - 7] + bb[ddd - 9]);
            bb[ddd - 15] = (byte) (bimg.getHeight() + bb[ddd - 6] + bb[ddd - 8]);

            ArrayList<Integer> bb1 = new ArrayList<Integer>();

            for (int y = 0; y < p2.length; y++) {
                for (int x = 0; x < p2[y].length; x++) {
                    Integer ii = cc.get(p2[y][x]);
                    int k = 0;
                    if (ii != null) {
                        k = ii;//cc.size()-ii;
                    }
                    bb1.add(k);

                }
            }
            int i = 0;
//for (i=1200;i<bb1.size();i++) myLog.Systemoutprintln(""+NewJFrame.tohex(cc.size()-bb1.get(i)%1000)+"="+bb1.get(i)+"="+NewJFrame.tohex(bb1.get(i)%1000));

            i = 0;
            while (i < bb1.size()) {
                if (bb1.get(i) == -1) {
                    i++;
                    continue;
                }
                if (bb1.get(i) >= 1000) {
                    i++;
                    continue;
                }
                int i1 = 0;
                while (i + i1 < bb1.size()) {
                    if (!bb1.get(i + i1).equals(bb1.get(i))) {
                        break;
                    }
                    i1++;
                }

                if (i1 > 128) {
                    i1 = 128;
                }
                if (i1 > 2) {
                    for (int i11 = 2; i11 < i1; i11++) {
                        bb1.set(i + i11, -1);
                    }
                    bb1.set(i, i1 - 1 + 10000);
                    bb1.set(i + 1, bb1.get(i + 1) + 1000);

                    i++;
                }
                i++;
            }
            //              myLog.Systemoutprintln("-----------111-----------");
//for (i=1200;i<bb1.size();i++)  myLog.Systemoutprintln(""+NewJFrame.tohex(cc.size()-bb1.get(i)%1000)+"="+bb1.get(i)+"="+NewJFrame.tohex(bb1.get(i)%1000));

            i = bb1.size() - 1;
            int i2 = i;
            int in = 0;
            while (i >= 0) {
                if (in < 127) {
                    if (bb1.get(i) >= 0 && bb1.get(i) < 1000) {
                        in++;
                        i--;
                        continue;
                    }
                    if (in == 0) {
                        i--;
                        continue;
                    }
                    bb1.add(i + 1, in + 128 - 1 + 20000);
                } else {
                    in++;
                    bb1.add(i, in + 128 - 1 + 20000);
                }
                if (i + 3 < bb1.size()) //                myLog.Systemoutprintln("aaaaaa==="+(i+1)+"="+NewJFrame.tohex(in+128)+"="+NewJFrame.tohex(cc.size()-bb1.get(i+2)%1000)+"="+NewJFrame.tohex(cc.size()-bb1.get(i+3)%1000));
                {
                    i--;
                }
                i2 = i;
                in = 0;
            }
            if (in > 0) {
                bb1.add(0, in + 128 - 1 + 20000);
            }
//int kk=NewJFrame.str2int(jddm.getText());
//for (i=kk+200;i<bb1.size();i++) bb1.set(i, 0);
//                myLog.Systemoutprintln("-----------222-------------");
//for (i=2000;i<bb1.size();i++)  myLog.Systemoutprintln(""+i+"="+NewJFrame.tohex(cc.size()-bb1.get(i)%1000)+"="+bb1.get(i)+"="+NewJFrame.tohex(bb1.get(i)%1000));            

            i = 0;
            int i8 = 0;
            int b1 = 0;
            boolean da = false;
            if (lg == 0) {
                lg = 8;
            }
            myLog.Systemoutprintln("lg=" + lg);
            for (int x = 0; x < bb1.size(); x++) {

                Integer ii = bb1.get(x) % 1000;
                if (ii < 0) {
                    continue;
                }
                Integer ii22 = bb1.get(x) / 10000;
                int k = 0;
                int i80 = i8 - 8 * i;
                if (ii22 > 0) {
                    k = ii;
                    b1 += k << i80;
                    i8 += 8;
                    i80 += 8;
                } else {
                    if (ii > 0) {
                        k = cc.size() - ii;
                    }
                    //int b1=bb[ddd+b3+i]&0xFF;

                    b1 += k << i80;
                    i8 += lg;
                    i80 += lg;
                }
                //myLog.Systemoutprintln("i8="+i8+"=i="+i);
                //if (i80>15) myLog.Systemoutprintln("=================i80=============="+i80);
                if (i80 < 8) {
                    if (ddd + b3 + i >= bb.length) {
                        myLog.Systemoutprintln("break11 x=" + x);
                        break;
                    }
                    bb[ddd + b3 + i] = (byte) (b1 & 0xFF);
                    da = true;
                    //myLog.Systemoutprintln(""+(b1&0xFF));
                } else {
                    int b2 = b1 >> 8;
                    b1 = b1 & 0xFF;
                    if (ddd + b3 + i >= bb.length) {
                        myLog.Systemoutprintln("break22 x=" + x);
                        break;
                    }
                    bb[ddd + b3 + i] = (byte) b1;
                    da = false;
                    //myLog.Systemoutprintln(""+(b1&0xFF));
                    b1 = b2;
                    i++;
                    if (b2 > 255) {//myLog.Systemoutprintln("=================i80=============="+i80+"=="+b2);
                        b2 = b1 >> 8;
                        b1 = b1 & 0xFF;
                        if (ddd + b3 + i >= bb.length) {
                            myLog.Systemoutprintln("break22 x=" + x);
                            break;
                        }
                        bb[ddd + b3 + i] = (byte) b1;
                        da = false;
                        //myLog.Systemoutprintln(""+(b1&0xFF));
                        b1 = b2;
                        i++;
                    }
                }

            }
            if (da) {
                bb[ddd + b3 + i] = (byte) (b1 & 0xFF);
            }
            myLog.Systemoutprintln("i==========" + i);
            int im = i;
//for (i=0;i<im;i++)  myLog.Systemoutprintln("1111===="+i+"="+NewJFrame.tohex(bb[ddd+b3+i]&0xFF));            

            String sdop = "";
            if (piclength < i) {
                sdop = "\nfile is big\nfile=" + (b3 + i) + "\nplace=" + piclength;
            } else {
                sdop = "\nfile=" + (b3 + i) + "\nplace=" + piclength;
                //for (int it=i;it<piclength;it++) bb[ddd+b3+it]=0;

            }
            jtablo.setText("begin=" + jddd.getText().trim().split(" ")[0] + "\nxm=" + bimg.getWidth()
                    + "\nym=" + bimg.getHeight()
                    + "\ncolortable=" + functions.tohex(b3)
                    + "\ncolor2=" + ii2
                    + "\ncolor3=" + ii3 + sdop + tabdop(i + b3));
            piclength = i + b3;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void from5(int typ, String fname0) {
        try {

            int ddd = functions.str2int("#" + jddd.getText().trim().split(" ")[0]);

            BufferedImage bimg;
            if (fname0 == null) {
                bimg = ImageIO.read(new File(fr.getpath() + "files/" + getsel() + "." + imext));
            } else {
                bimg = ImageIO.read(new File(fname0));
            }
            HashMap<Long, Integer> cc1 = new HashMap<Long, Integer>();

            long[][] p2 = convertTo2DWithoutUsingGetRGB(bimg);
            int ic = 1;
            for (int y = 0; y < p2.length; y++) {
                for (int x = 0; x < p2[y].length; x++) {
                    if (!cc1.containsKey(p2[y][x])
                            && p2[y][x] != 0) {
                        cc1.put(p2[y][x], ic);
                        ic++;
                    }
                }
            }
            Object[] ob = cc1.keySet().toArray();
            Long[] obi = new Long[ob.length];
            for (int i = 0; i < ob.length; i++) {
                obi[i] = (Long) ob[i];
            }
            Arrays.sort(obi);
            HashMap<Long, Integer> cc = new HashMap<Long, Integer>();
            for (int i = 0; i < ob.length; i++) {
                cc.put(obi[i], i);
                myLog.Systemoutprintln("" + i + "=" + functions.tohex(obi[i]) + "=" + obi[i]);
            }
            int lg = functions.log2(cc.size() + 1);
            myLog.Systemoutprintln("lg======" + lg + "=cc.size=" + cc.size());
            int b3 = 0;
            for (int i = 0; i < cc.size(); i++) {
                //int c1=(Integer)cc.keySet().toArray()[i];
                Long c1 = obi[cc.size() - 1 - i];
                myLog.Systemoutprintln(functions.tohex(c1));

                long ca = (c1 >> 24) & 0xFF;

                long cr = ((c1 >> 16) & 0xFF) / 8;
                long cg = ((c1 >> 8) & 0xFF) / 4;
                long cb = ((c1 >> 0) & 0xFF) / 8;

                long c22 = cb;
                c22 += cg << 5;
                c22 += cr << 11;

                byte b1 = (byte) (c22 % 256);
                byte b2 = (byte) (c22 / 256);
                bb[ddd + 2 * i] = b1;
                bb[ddd + 2 * i + 1] = b2;
                b3 += 2;
                myLog.Systemoutprintln("" + cr + "=" + cg + "=" + cb);
                myLog.Systemoutprintln("" + functions.tohex(b1 & 0xFF) + "=" + functions.tohex(b2 & 0xFF));

            }

            int i = 0;
            int i8 = 0;
            int b1 = 0;
            boolean da = false;
            for (int y = 0; y < p2.length; y++) {
                for (int x = 0; x < p2[y].length; x++) {
                    Integer ii = cc.get(p2[y][x]);
                    int k = 0;
                    if (ii != null) {
                        k = cc.size() - ii;
                    }
                    //int b1=bb[ddd+b3+i]&0xFF;
                    int i80 = i8 - 8 * i;

                    b1 += k << i80;
                    i8 += lg;
                    i80 += lg;
                    if (i80 < 8) {
                        bb[ddd + b3 + i] = (byte) (b1 & 0xFF);
                        da = true;
                    } else {
                        int b2 = b1 >> 8;
                        b1 = b1 & 0xFF;
                        bb[ddd + b3 + i] = (byte) b1;
                        da = false;
                        b1 = b2;
                        i++;
                    }

                }
            }
            if (da) {
                bb[ddd + b3 + i] = (byte) (b1 & 0xFF);
            }

            myLog.Systemoutprintln("nax addr=" + functions.tohex(ddd + b3 + i) + "==" + i + "=" + b3);
            bb[ddd - 8] = (byte) (b3 / 2 + 1);
            int i1 = bb[ddd - 1] & 0xFF;
            int i2 = bb[ddd - 2] & 0xFF;
            int i3 = bb[ddd - 3] & 0xFF;
            int i4 = bb[ddd - 4] & 0xFF;
            bb[ddd - 12] = (byte) (bimg.getWidth() + i2 + i4);
            bb[ddd - 10] = (byte) (bimg.getHeight() + i1 + i3);//-1
            myLog.Systemoutprintln("cols-i8=" + (bb[ddd - 8] & 0xFF));
            myLog.Systemoutprintln("xm-i12=" + (bb[ddd - 12] & 0xFF));
            myLog.Systemoutprintln("ym-i10=" + (bb[ddd - 10] & 0xFF));
            myLog.Systemoutprintln("colors================" + cc.size() + "=" + lg + "=" + b3);
            String sdop = "";
            if (piclength < i) {
                sdop = "\nfile is big\nfile=" + (b3 + i) + "\nplace=" + piclength;
            } else {
                sdop = "\nfile=" + (b3 + i) + "\nplace=" + piclength;
                //for (int it=i;it<piclength;i++) bb[ddd+it]=0;
            }
            jtablo.setText("begin=" + jddd.getText().trim().split(" ")[0]
                    + "\nxm=" + bimg.getWidth()
                    + "\nym=" + bimg.getHeight()
                    + "\ncolortable=" + functions.tohex(b3)
                    + "\ncolor2=" + (bb[ddd - 8] & 0xFF)
                    + sdop + tabdop(i + b3));
            piclength = i + b3;
        } catch (Exception e) {
            e.printStackTrace();
        }
//BufferedImage bimg=new BufferedImage(bimg0.getWidth(), bimg0.getHeight(),BufferedImage.TYPE_BYTE_INDEXED);
//bimg.getGraphics().drawImage(bimg0, 0, 0, null);
    }

    public void from114(int typ) {

        int cc = (Integer) jfsize.getValue();
        imgx = 40;
        imgy = (xm2.size() + 1) * (cc + 2) + 1;
        dd = functions.str2int("#" + jddd.getText().trim().split(" ")[0]);

        //int xm = (Integer)jxm.getValue();
        //int b3 = (Integer)jb3.getValue();
        int d6 = 1;
        //int cd = (Integer)jcd.getValue();
        long[][] p2 = null;
        try {
            BufferedImage bimg = ImageIO.read(new File(fr.getpath() + "files/" + getsel() + "." + imext));
            p2 = convertTo2DWithoutUsingGetRGB(bimg);
        } catch (Exception e) {
            e.printStackTrace();
            jtablo.setText("error " + e.toString() + "\n" + fr.getpath() + "files/" + getsel() + "." + imext);
            return;
        }

        int ic = 1;

        /*          String ss="";
         for (int y=0;y<p2.length;y++){
         for (int x=0;x<p2[y].length;x++){
         if (p2[y][x]==0xFFFF0000L) ss+="r ";
         else if (p2[y][x]==0xFFFFFFFFL) ss+="  ";
         else if (p2[y][x]==0xFF000000L) ss+="b  ";
         else if (p2[y][x]==0) ss+="  ";
         else  ss+=p2[y][x]+" ";
         }
         myLog.Systemoutprintln(ss);ss="";
         }
         */
        for (int cd = 0; cd < xm2.size() - 1; cd++) {//myLog.Systemoutprintln("ffffffffffffffffffffff1111");

            if (cd < 0) {
                cd = 0;
                jcd.setValue(0);
            }
            int l1 = ((Integer) xm2.get(cd));
            int l2 = ((Integer) xm2.get(cd + 1));
            //jb3.setText(""+l1);

            int ddm = dd + l2 - 1;
            //jddm.setText(NewJFrame.tohex(ddm));
            int l = (l2 - l1) * 8 / cc;
            //myLog.Systemoutprintln("lll="+xm2.get(1)+"="+l+"====="+xm2.size()+"==="+xm3.size());
            //jxm.setText(""+l);
            if (l == 0) {
                return;
            }
            int xm = l;
            int y1 = cd * (cc + 2) + 1;
            int ym = cc;
            int b3 = l1;

            int x = 0;
            int y = 0;

            byte[] bt = new byte[xm * ym];

            for (y = 0; y < ym; y++) {
                for (x = 0; x < xm; x++) {
                    if (p2[y1 + y][x + 1] == 0xFF000000L) {
                        bt[y * xm + x] = 1;
                    } else {
                        bt[y * xm + x] = 0;
                    }
                }
            }
            int it = 0;
            myLog.Systemoutprintln("lllll=====" + bt.length);
            for (int i = dd + b3; i < bb.length; i++) {//0xc7a
                if (i > ddm && ddm > 0) {
                    break;
                }
                int b1 = 0;
                int k8 = 1;
                for (byte k = 0; k < 8; k++) {
                    if (it < bt.length) {
                        b1 += bt[it] * k8;
                    }
                    it++;
                    k8 *= 2;
                }
                bb[i] = (byte) b1;

            }

        }
    }
    private void jfromfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfromfActionPerformed
        // TODO add your handling code here:
        myimport(null);
    }

    void myimport(String fname0) {
        int typ = functions.str2int(jddd.getText().trim().split(" ")[1]);

        if (typ == 5) {
            for (int i = 0; i < piclength; i++) {
                bb[dd + i] = 0;
            }
            from5(typ, fname0);

        } else if (typ == 1) {//==========================================================================================================
            for (int i = 0; i < piclength; i++) {
                bb[dd + i] = 0;
            }
            from1(typ, fname0);
        } else if (typ == 114) {
            from114(typ);
        } else if (typ == 700
                || typ == 300
                || typ == 400
                || typ == 401
                || typ == 402
                || typ == 500 || typ == 501
                || typ == 800) {
            byte[] bt = functions.file2byte(fr.getpath() + "files/" + fname);
            dd = functions.str2int("#" + jddd.getText().trim().split(" ")[0]);
            if (piclength > bt.length) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                JOptionPane.showConfirmDialog(null, "file is big\nfile=" + bt.length + "\nplace=" + piclength + "\nWrite?", "Warning", dialogButton);

                jtablo.setText("file is big\nfile=" + bt.length + "\nplace=" + piclength);
                if (dialogButton != JOptionPane.YES_OPTION) {
                    return;
                }
            }
            jtablo.setText("file=" + bt.length + "\nplace=" + piclength);
            for (int i = 0; i < piclength; i++) {
                if (i < bt.length) {
                    bb[dd + i] = bt[i];
                } else {
                    bb[dd + i] = 0;
                }
            }
        }
        pan.repaint();
    }//GEN-LAST:event_jfromfActionPerformed

    private void jfsizeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jfsizeStateChanged
        // TODO add your handling code here:
        //ris15a();
    }//GEN-LAST:event_jfsizeStateChanged

    private void jfsizeVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_jfsizeVetoableChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jfsizeVetoableChange

    private void jtabPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jtabPropertyChange
        // TODO add your handling code here:

        int i = jtab.getSelectedRow();
        if (i < 0) {
            return;
        }
        String ss = jtab.getModel().getValueAt(i, 3).toString();
        int dd = Integer.decode(jtab.getModel().getValueAt(i, 1).toString());
        int len = Integer.decode(jtab.getModel().getValueAt(i, 2).toString());
        if (dd < 0 || len < 0) {
            return;
        }
        if (len < ss.length()) {
            ss = ss.substring(0, len);
            jtab.getModel().setValueAt(ss, i, 3);
        }

        myLog.Systemoutprintln("" + ss);

        byte[] b2 = ss.getBytes(Charset.forName("UTF16"));

        //for (i=2;i<b2.length;i++) bb[dd+i]=b2[i];
        for (i = 2; i < b2.length; i += 2) {
            myLog.Systemoutprintln("" + bb[dd + i + 1] + "==" + b2[i]);
        }
        for (i = 3; i < b2.length; i += 2) {
            myLog.Systemoutprintln("" + bb[dd + i - 1] + "==" + b2[i]);
        }

        for (i = 2; i < b2.length; i += 2) {
            bb[dd + i + 1] = b2[i];
        }
        for (i = 3; i < b2.length; i += 2) {
            bb[dd + i - 1] = b2[i];
        }

    }//GEN-LAST:event_jtabPropertyChange

    private void jrestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrestoreActionPerformed
        // TODO add your handling code here:
        if (fils.getSelectedValue().toString().contains(".b")) {
            String s20 = fils.getSelectedValue().toString().replace(".b", "");
            String ss = "d \"" + fr.getpath() + s20 + ".a\" \"" + fr.getpath() + s20 + ".b\"";
            myLog.Systemoutprintln("=========================" + ss);
            LzmaAlone.exec(ss);
            File f = new File(fr.getpath() + s20);
            if (f.exists()) {
                if (f.length() == 0) {
                    f.delete();
                }
            }
        }
        updatejef0();
    }//GEN-LAST:event_jrestoreActionPerformed

    private void jcommandsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcommandsActionPerformed
        // TODO add your handling code here:
        if (frtxt1 == null) {
            frtxt1 = new txt1();
        }
        frtxt1.fr = fr;
        frtxt1.setVisible(true);
    }//GEN-LAST:event_jcommandsActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String[] s2 = jddd.getText().trim().split(" ");
        dd = functions.str2int("#" + s2[0]);
        if (dd > 0) {
            dd--;
        }
        String ss = String.format("%02X", dd);
        for (int i = 1; i < s2.length; i++) {
            ss += " " + s2[i];
        }
        jddd.setText(ss);
        if ((evt.getModifiers() & java.awt.event.ActionEvent.SHIFT_MASK) != 0) {
            ris(0, pan.getWidth(), type0);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        String[] s2 = jddd.getText().trim().split(" ");
        dd = functions.str2int("#" + s2[0]);
        dd++;
        String ss = String.format("%02X", dd);
        for (int i = 1; i < s2.length; i++) {
            ss += " " + s2[i];
        }
        jddd.setText(ss);
        if ((evt.getModifiers() & java.awt.event.ActionEvent.SHIFT_MASK) != 0) {
            ris(0, pan.getWidth(), type0);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jb3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb3MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jb3MouseReleased

    private void jxmMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jxmMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jxmMouseReleased

    private void jb3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb3MouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_jb3MouseExited

    private void jb3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb3MouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_jb3MouseEntered

    private void jb3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb3MousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jb3MousePressed

    private void jb3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jb3MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jb3MouseClicked

    private void jb3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jb3PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jb3PropertyChange

    private void inttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inttActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inttActionPerformed

    private void hexxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hexxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hexxActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        String[] s2 = hexx.getText().replace("-", " - ").replace("+", " + ").split(" ");
        int ii = 0;
        String sp = "";
        for (int i = 0; i < s2.length; i++) {
            if (s2[i].equals("-")) {
                sp = s2[i];
                continue;
            }
            if (s2[i].equals("+")) {
                sp = s2[i];
                continue;
            }
            if (s2[i].equals("")) {
                sp = s2[i];
                continue;
            }
            int it = functions.str2int("#" + s2[i], -1);
            if (it == -1) {
                intt.setText("err");
                return;
            }
            if (sp.equals("")) {
                ii = it;
            } else if (sp.equals("+")) {
                ii += it;
            } else if (sp.equals("-")) {
                ii -= it;
            }
        }
        intt.setText("" + ii);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        String[] s2 = intt.getText().replace("-", " - ").replace("+", " + ").split(" ");
        int ii = 0;
        String sp = "";
        for (int i = 0; i < s2.length; i++) {
            if (s2[i].equals("-")) {
                sp = s2[i];
                continue;
            }
            if (s2[i].equals("+")) {
                sp = s2[i];
                continue;
            }
            if (s2[i].equals("")) {
                sp = s2[i];
                continue;
            }
            int it = functions.str2int(s2[i], -1);
            if (it == -1) {
                intt.setText("err");
                return;
            }
            if (sp.equals("")) {
                ii = it;
            } else if (sp.equals("+")) {
                ii += it;
            } else if (sp.equals("-")) {
                ii -= it;
            }
        }
        hexx.setText(functions.tohex(ii));
    }//GEN-LAST:event_jButton19ActionPerformed
    int cd22 = 0;
    private void jcd2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jcd2StateChanged
        // TODO add your handling code here:
        String[] s2 = jddd.getText().trim().split(" ");
        dd = functions.str2int("#" + s2[0]);
        int cd22a = (Integer) jcd2.getValue();
        if (cd22a > cd22) {
            dd += 64;
        } else {
            dd -= 64;
        }
        cd22 = cd22a;
        String ss = String.format("%02X", dd);
        for (int i = 1; i < s2.length; i++) {
            ss += " " + s2[i];
        }
        jddd.setText(ss);

        ris(x10, x20, type0);
    }//GEN-LAST:event_jcd2StateChanged

    private void jcd2VetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_jcd2VetoableChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jcd2VetoableChange

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
        //? 00  ?  ?  ?  13  ?  ?  ?  ?  ?  ?  ?  ?  ?  ?  00  ?  ?  01  34  ?  ?  00 

        fr.readtable(jddd.getText().trim().split(" ")[0], fils.getSelectedValue().toString());
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jtextdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtextdaActionPerformed
        // TODO add your handling code here:
        txtda = jtextda.isSelected();
    }//GEN-LAST:event_jtextdaActionPerformed

    private void jfromf1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfromf1ActionPerformed
        // TODO add your handling code here:
        String ff = fr.getpath() + "files";

        final JFileChooser fc = new JFileChooser();

        fc.setCurrentDirectory(new File(ff));
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            myimport(fc.getSelectedFile().getPath());
        }
        /*int typ = functions.str2int(jddd.getText().trim().split(" ")[1]);

         if (typ == 5) {
         for (int i = 0; i < piclength; i++) {
         bb[dd + i] = 0;
         }
         fromgif(typ);

         } else if (typ == 1) {//==========================================================================================================
         for (int i = 0; i < piclength; i++) {
         bb[dd + i] = 0;
         }
         fromgif(typ);
         }*/
    }//GEN-LAST:event_jfromf1ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        int ist = 0;
        int k = 0;
        String ss = "";
        for (int i = 0; i < bb.length; i++) {

            if (bb[i] > 31 && bb[i] < 127) {

                char c = (char) bb[i];
                ss += c;
                if (Character.isLetterOrDigit(c)) {
                    k++;
                }
            } else {
                if (k > 4) {
                    myLog.Systemoutprintln(functions.tohex(i) + "\t" + ss);
                }
                k = 0;
                ss = "";
            }

        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jfsizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jfsizeMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jfsizeMouseClicked
    /*
     private void panMouseClicked(java.awt.event.MouseEvent evt) {                                 
     // TODO add your handling code here:
     //gr.drawString(String.format("%02X ", (y*xm+x)*b3)+"="+String.format("%02X ", bb[(y*xm+x)*b3]),
     int x = evt.getX() / d4;
     int y = evt.getY() / d4;
     int xm = Integer.decode(jxm.getText());
     int b3 = Integer.decode(jb3.getText());
     int ii = (y * xm + x) * b3;
     String ss="";
     if (ii<bb.length)
     ss = String.format("%02X ", ii) + "=" + String.format("%02X ", bb[ii]);
     this.setTitle(ss);
     }   
     */

//int kk=0;
    byte getnextbit(int n) {
        int n1 = n / 8;
        int n2 = n % 8;
        if (n1 > im) {
            im = n1;
        }
        //if (da) n2=7-n2;
        int x = 0xFF & bb[n1];//0x01;//bb[n1];
        //if (kk<8) x = 0xFF & 0x88;
        for (int i = 0; i < n2; i++) {
            x = x / 2;
        }
//        kk++;
//myLog.Systemoutprintln("000000000000000000====="+n1+"="+NewJFrame.tohex(x)+"="+(x%2));
        return (byte) (x % 2);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ris1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ris1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ris1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ris1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ris1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList fils;
    private javax.swing.JTextField hexx;
    private javax.swing.JTextField intt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSpinner jb3;
    private javax.swing.JTextField jcc;
    private javax.swing.JSpinner jcd;
    private javax.swing.JButton jcd00;
    private javax.swing.JSpinner jcd1;
    private javax.swing.JSpinner jcd2;
    private javax.swing.JButton jcommands;
    private javax.swing.JSpinner jd6;
    private javax.swing.JTextField jddd;
    private javax.swing.JTextField jddm;
    private javax.swing.JList jef;
    private javax.swing.JButton jextract;
    private javax.swing.JButton jfromf;
    private javax.swing.JButton jfromf1;
    private javax.swing.JSpinner jfsize;
    private javax.swing.JCheckBox jneg;
    private javax.swing.JButton jnegative;
    private javax.swing.JPanel jpanadv;
    private javax.swing.JProgressBar jprogress;
    private javax.swing.JButton jrestore;
    private javax.swing.JTable jtab;
    private javax.swing.JTextArea jtablo;
    private javax.swing.JCheckBox jtextda;
    private javax.swing.JButton jtofile;
    private javax.swing.JButton jtorom;
    private javax.swing.JSpinner jxm;
    private javax.swing.JPanel panp;
    // End of variables declaration//GEN-END:variables
}
