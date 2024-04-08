package com.kevinm.envelopeprinter.ui.controls;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.kevinm.envelopeprinter.draw.DrawEnvelope;

public class JPreviewScrollPane extends JScrollPane {

	public JPreviewScrollPane() {
		super();
		this.setBorder(BorderFactory.createLineBorder(getForeground()));

		this.setPreferredSize(new Dimension(0, 500));
		this.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		this.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(2000, 2000));
		panel.setLayout(new GridBagLayout());
		panel.add(new JEnvelopePreviewPanel());
		this.setViewportView(panel);

	}

	public class JEnvelopePreviewPanel extends JPanel {
		private double zoom = 1;
		private double prevZoom = 0;

		public JEnvelopePreviewPanel() {

			this.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void keyPressed(KeyEvent e) {
					System.out.println("s");
					if (e.getKeyCode() == KeyEvent.VK_R) {
						zoom = 1;
						prevZoom = 0;
						JEnvelopePreviewPanel.this.repaint();
						JEnvelopePreviewPanel.this.revalidate();
						JEnvelopePreviewPanel.this.getParent().revalidate();
						e.consume();
					}

				}
			});

			this.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
				}

				@Override
				public void mousePressed(MouseEvent e) {
				}

				@Override
				public void mouseExited(MouseEvent e) {
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					JEnvelopePreviewPanel.this.requestFocusInWindow();
				}

				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});
			this.addMouseWheelListener(new MouseWheelListener() {

				@Override
				public void mouseWheelMoved(MouseWheelEvent e) {
					double scale = 0;
					if (e.getPreciseWheelRotation() < 0)
						scale = 0.1;
					else
						scale = -0.1;
					zoom = Math.clamp(zoom + scale, 0.5, 2);
					if (zoom != prevZoom) {
						JEnvelopePreviewPanel.this.revalidate();
						JEnvelopePreviewPanel.this.repaint();
						JEnvelopePreviewPanel.this.getParent().revalidate();
					}
					prevZoom = zoom;
				}
			});
			this.setFocusable(true);
			this.setRequestFocusEnabled(true);
			this.setBorder(BorderFactory.createLineBorder(getForeground()));
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			Dimension dim = DrawEnvelope.drawExampleEnvelope(g, zoom, 5, 5, getFont(), getFont());
			this.setPreferredSize(dim);
			this.setSize(dim);
		}

	}

}
