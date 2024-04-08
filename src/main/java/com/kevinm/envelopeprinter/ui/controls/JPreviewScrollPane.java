package com.kevinm.envelopeprinter.ui.controls;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
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
	final JEnvelopePreviewPanel previewPanel = new JEnvelopePreviewPanel();

	public JPreviewScrollPane() {
		this.setBorder(BorderFactory.createLineBorder(getForeground()));

		this.setPreferredSize(new Dimension(0, 500));
		this.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		this.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);

		this.setViewportView(previewPanel);

		this.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (e.isControlDown()) {
					zoom(e.getPoint(), e.getWheelRotation());
				}
			}

		});
	}

	private void zoom(Point point, int wheelRotation) {
		double zoomFactor = wheelRotation > 0 ? 0.9 : 1.1;
		this.previewPanel.zoom = (this.previewPanel.zoom * zoomFactor);
		Point pos = this.getViewport().getViewPosition();

		int newX = (int) (point.x * (zoomFactor - 1) + zoomFactor * pos.x);
		int newY = (int) (point.y * (zoomFactor - 1) + zoomFactor * pos.y);
		this.getViewport().setViewPosition(new Point(newX, newY));

		this.previewPanel.repaint();
		this.previewPanel.revalidate();
	}

	public class JEnvelopePreviewPanel extends JPanel {
		public double zoom = 1;
		public double prevZoom = 0;

		public JEnvelopePreviewPanel() {

			this.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent e) {
				}

				@Override
				public void keyReleased(KeyEvent e) {
				}

				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_R) {
						zoom = 1;
						prevZoom = 0;
						JEnvelopePreviewPanel.this.repaint();
						JEnvelopePreviewPanel.this.revalidate();
						JEnvelopePreviewPanel.this.getParent().revalidate();
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
			this.setFocusable(true);
			this.setRequestFocusEnabled(true);
			this.setAutoscrolls(true);
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
