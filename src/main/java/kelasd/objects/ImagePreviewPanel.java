/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kelasd.objects;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

/**
 *
 * @author mnish
 */
public class ImagePreviewPanel extends JPanel implements PropertyChangeListener {
        private ImageIcon thumbnail;
        private File file;
        int w = 242;

        public ImagePreviewPanel(JFileChooser fileChooser) {
            setPreferredSize(new Dimension(this.w, this.w));
            fileChooser.addPropertyChangeListener(ImagePreviewPanel.this);
        }

        private void loadImage() {
            if (file == null) {
                thumbnail = null;
                return;
            }

            ImageIcon tmpIcon = new ImageIcon(file.getPath());
            if (tmpIcon.getIconWidth() > this.w) {
                thumbnail = new ImageIcon(
                        tmpIcon.getImage().getScaledInstance(this.w, -1, Image.SCALE_SMOOTH)
                );
            } else {
                thumbnail = tmpIcon;
            }
        }

        @Override
        public void propertyChange(PropertyChangeEvent e) {
            boolean update = false;
            String prop = e.getPropertyName();

            if (JFileChooser.DIRECTORY_CHANGED_PROPERTY.equals(prop)) {
                file = null;
                update = true;
            } else if (JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(prop)) {
                file = (File) e.getNewValue();
                update = true;
            }

            if (update) {
                thumbnail = null;
                if (isShowing()) {
                    loadImage();
                    repaint();
                }
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (thumbnail != null) {
                int x = (getWidth() - thumbnail.getIconWidth()) / 2;
                int y = (getHeight() - thumbnail.getIconHeight()) / 2;
                thumbnail.paintIcon(this, g, x, y);
            } else {
                g.drawString("No Preview", 30, getHeight() / 2);
            }
        }
    }
