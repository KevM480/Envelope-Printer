package com.kevinm.envelopeprinter.ui.controls;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
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
					JPreviewScrollPane.this.setWheelScrollingEnabled(false);
					JPreviewScrollPane.this.zoom(e.getPoint(), e.getWheelRotation());
				} else
					JPreviewScrollPane.this.setWheelScrollingEnabled(true);
			}
		});
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
					JPreviewScrollPane.this.previewPanel.setDefualtZoom();
					JPreviewScrollPane.this.previewPanel.repaint();
					JPreviewScrollPane.this.previewPanel.revalidate();
					Rectangle bounds = JPreviewScrollPane.this.getViewport().getViewRect();
					Dimension size = JPreviewScrollPane.this.previewPanel.getSize();
					int x = (size.width - bounds.width) / 2;
					int y = (size.height - bounds.height) / 2;
					JPreviewScrollPane.this.getViewport().setViewPosition(new Point(x, y));
					System.out.println(size);
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
				JPreviewScrollPane.this.requestFocus();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}

	private void zoom(Point point, int wheelRotation) {
		double zoomFactor = wheelRotation > 0 ? 0.9 : 1.1;

		if (this.previewPanel.modifyZoomBy(zoomFactor)) {
			Point pos = this.getViewport().getViewPosition();

			int newX = (int) (point.x * (zoomFactor - 1) + zoomFactor * pos.x);
			int newY = (int) (point.y * (zoomFactor - 1) + zoomFactor * pos.y);
			this.getViewport().setViewPosition(new Point(newX, newY));

		}
	}

	public class JEnvelopePreviewPanel extends JPanel {
		private double zoom = 1;
		private double prevZoom = 0;
		private Dimension envelopeSize = new Dimension();

		public JEnvelopePreviewPanel() {
			this.setEnvelopeSize(5, 5);
		}

		public void setEnvelopeSize(double inchWidth, double inchHeight) {
			this.envelopeSize.width = (int) Math.round(inchWidth * 72D);
			this.envelopeSize.height = (int) Math.round(inchHeight * 72D);
		}

		public Dimension getEnvelopeSize() {
			return this.envelopeSize;
		}

		public void setDefualtZoom() {
			this.zoom = 1;
			this.prevZoom = 0;
			modifyZoomBy(1);
		}

		public boolean modifyZoomBy(double zoomFactor) {
			double zoomed = this.zoom * zoomFactor;
			this.zoom = (zoomed > 0.5 && zoomed < 2) ? zoomed : this.zoom;
			boolean canZoom = this.canZoom();
			if (canZoom) {
				Dimension dim = new Dimension((int) (envelopeSize.width * zoom) * 3,
						(int) (envelopeSize.height * zoom) * 3);
				this.setPreferredSize(dim);
				this.setSize(dim);
				this.repaint();
				this.revalidate();
			}
			this.setPrevZoom(this.zoom);
			return canZoom;
		}

		public double getZoom() {
			return this.zoom;
		}

		public void setPrevZoom(double prevZoom) {
			this.prevZoom = prevZoom;
		}

		public double getPrevZoom() {
			return this.prevZoom;
		}

		private boolean canZoom() {
			return this.zoom != this.prevZoom;
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			DrawEnvelope.drawExampleEnvelope(g, this.zoom, this.envelopeSize, getFont(), getFont());

		}

	}

}
